package com.habbo.builderessentials.events;

import com.habbo.builderessentials.commands.SetzCommand;
import com.habbo.builderessentials.messages.outgoing.HeightmapMessageComposer;
import com.habbo.game.players.types.Player;
import com.habbo.game.rooms.objects.items.RoomItemFloor;
import com.habbo.network.messages.outgoing.room.items.UpdateFloorItemMessageComposer;
import com.habbo.plugin.events.room.item.FloorItemMovedEvent;
import com.habbo.plugin.events.room.item.FloorItemPlacedEvent;
import com.interfaces.plugin.IEventHandler;
import com.interfaces.plugin.IEventListener;

public class SetSetz implements IEventListener {

    @IEventHandler
    public static void onFloorItemPlaced(FloorItemPlacedEvent event) throws Exception {

        RoomItemFloor floorItem = event.item;
        Player player = event.player;

        if(player.getEntity().hasAttribute(SetzCommand.SETZ_KEY)) {
            floorItem.getPosition().setZ((double) player.getEntity().getAttribute(SetzCommand.SETZ_KEY) + floorItem.getTile().getTileHeight());
            floorItem.getRoom().getEntities().broadcastMessage(new UpdateFloorItemMessageComposer(floorItem));
            player.getSession().sendQueue(new HeightmapMessageComposer(player.getEntity().getRoom(), (double) player.getEntity().getAttribute(SetzCommand.SETZ_KEY)));
        }

    }

    @IEventHandler
    public static void onFloorItemMoved(FloorItemMovedEvent event) throws Exception {

        RoomItemFloor floorItem = event.item;
        Player player = event.player;

        if(player.getEntity().hasAttribute(SetzCommand.SETZ_KEY)) {
            floorItem.getPosition().setZ((double) player.getEntity().getAttribute(SetzCommand.SETZ_KEY) + floorItem.getTile().getTileHeight());
            floorItem.getRoom().getEntities().broadcastMessage(new UpdateFloorItemMessageComposer(floorItem));
            player.getSession().sendQueue(new HeightmapMessageComposer(player.getEntity().getRoom(), (double) player.getEntity().getAttribute(SetzCommand.SETZ_KEY)));
        }

    }

}
