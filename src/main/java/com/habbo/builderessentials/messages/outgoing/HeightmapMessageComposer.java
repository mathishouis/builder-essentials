package com.habbo.builderessentials.messages.outgoing;

import com.habbo.game.rooms.types.Room;
import com.habbo.game.rooms.types.tiles.RoomTileState;
import com.habbo.network.messages.headers.Composers;
import com.habbo.network.messages.outgoing.MessageComposer;
import com.interfaces.network.messages.IComposer;

public class HeightmapMessageComposer extends MessageComposer {

    private final Room room;
    private double setz = -1;

    public HeightmapMessageComposer(final Room room, double setz) {
        this.room = room;
        this.setz = setz;
    }

    @Override
    public short getId() {
        return Composers.HeightMapMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(room.getModel().getSizeX());
        msg.writeInt(room.getModel().getSizeX() * room.getModel().getSizeY());

        for (int y = 0; y < room.getModel().getSizeY(); y++) {
            for (int x = 0; x < room.getModel().getSizeX(); x++) {
                if (room.getModel().getSquareState()[x][y] == RoomTileState.INVALID) {
                    msg.writeShort(16191);
                } else {
                    int height;

                    if(this.setz < 0) {
                        height = ((room.getModel().getSquareHeight()[x][y]) * 256);
                    } else {
                        height = (int) ((room.getModel().getSquareHeight()[x][y] + this.setz) * 256);
                    }
                    msg.writeShort(height);
                }
            }
        }
    }

}
