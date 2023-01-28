package com.habbo.builderessentials.events;

import com.habbo.builderessentials.commands.ForceActCommand;
import com.habbo.game.players.types.Player;
import com.habbo.game.rooms.objects.items.RoomItemFloor;
import com.habbo.plugin.events.room.item.FloorItemMovedEvent;
import com.habbo.plugin.events.room.item.FloorItemPlacedEvent;
import com.interfaces.plugin.IEventHandler;
import com.interfaces.plugin.IEventListener;

public class SetState implements IEventListener {

    @IEventHandler
    public static void onFloorItemPlaced(FloorItemPlacedEvent event) throws Exception {

        RoomItemFloor floorItem = event.item;
        Player player = event.player;

        if(player.getEntity().hasAttribute(ForceActCommand.FORCE_ACT_KEY)) {
            floorItem.setExtraData((int) player.getEntity().getAttribute(ForceActCommand.FORCE_ACT_KEY));
        }

    }

    @IEventHandler
    public static void onFloorItemMoved(FloorItemMovedEvent event) throws Exception {

        RoomItemFloor floorItem = event.item;
        Player player = event.player;

        if(player.getEntity().hasAttribute(ForceActCommand.FORCE_ACT_KEY)) {
            floorItem.setExtraData((int) player.getEntity().getAttribute(ForceActCommand.FORCE_ACT_KEY));
        }

    }

}
