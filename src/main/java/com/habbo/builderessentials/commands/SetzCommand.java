package com.habbo.builderessentials.commands;

import com.habbo.builderessentials.messages.outgoing.HeightmapMessageComposer;
import com.habbo.config.Locale;
import com.habbo.game.commands.ChatCommand;
import com.habbo.game.rooms.types.mapping.RoomTile;
import com.habbo.network.messages.outgoing.room.engine.UpdateStackMapMessageComposer;
import com.habbo.network.sessions.Session;

public class SetzCommand extends ChatCommand {

    public static String SETZ_KEY = "be.setz";

    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) {
            sendWhisper(Locale.get("command.setz.invalidformat"), client);
            return;
        }

        if(params[0].equals("stop")) {
            if(client.getPlayer().getEntity().hasAttribute(SETZ_KEY)) {
                client.getPlayer().getEntity().removeAttribute(SETZ_KEY);
                sendWhisper(Locale.get("command.setz.disabled"), client);
                for (RoomTile[] pTile : client.getPlayer().getEntity().getRoom().getMapping().getTiles()) {

                    for (RoomTile tile : pTile) {

                        if (tile != null) {
                            tile.reload();

                            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new UpdateStackMapMessageComposer(tile));
                        }
                    }
                }
                client.sendQueue(new com.habbo.network.messages.outgoing.room.engine.HeightmapMessageComposer(client.getPlayer().getEntity().getRoom()));
                client.sendQueue(client.getPlayer().getEntity().getRoom().getModel().getRelativeHeightmapMessage());
                client.flush();
                return;
            }
            sendWhisper(Locale.get("command.setz.disabled"), client);
            return;
        }

        if (client.getPlayer().getEntity() != null && client.getPlayer().getEntity().getRoom() != null) {
            try {
                double height = Double.parseDouble(params[0]);

                if (height > 100 || height < -100) {
                    sendWhisper(Locale.get("command.setz.invalidheight"), client);
                    return;
                }

                for (RoomTile[] pTile : client.getPlayer().getEntity().getRoom().getMapping().getTiles()) {

                    for (RoomTile tile : pTile) {

                        if (tile != null) {
                            tile.setMagicTile(true);

                            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new UpdateStackMapMessageComposer(tile));
                        }
                    }
                }


                sendWhisper(Locale.get("command.setz.changed").replace("%height%", String.valueOf(height)), client);
                client.getPlayer().getEntity().setAttribute(SETZ_KEY, height);
                client.sendQueue(new HeightmapMessageComposer(client.getPlayer().getEntity().getRoom(), height));
                client.sendQueue(client.getPlayer().getEntity().getRoom().getModel().getRelativeHeightmapMessage());
                client.flush();


            } catch (Exception e) {
                sendWhisper(Locale.get("be.command.setz.invalidformat"), client);
            }
        }
    }

    @Override
    public String getPermission() {
        return "setz_command";
    }

    @Override
    public String getParameter() {
        return Locale.get("command.parameter.number");
    }
}
