package com.habbo.builderessentials.commands;

import com.habbo.config.Locale;
import com.habbo.game.commands.ChatCommand;
import com.habbo.network.sessions.Session;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class ForceRotCommand extends ChatCommand {

    public static String FORCE_ROTATION_KEY = "be.force_rotation";

    @Override
    public void execute(Session client, String[] params) {

        if (params.length != 1) {
            sendWhisper(Locale.get("command.forcerot.invalidformat"), client);
            return;
        }

        if(params[0].equals("stop")) {
            if(client.getPlayer().getEntity().hasAttribute(FORCE_ROTATION_KEY)) {
                client.getPlayer().getEntity().removeAttribute(FORCE_ROTATION_KEY);
                sendWhisper(Locale.get("command.forcerot.disabled"), client);
                return;
            }
            sendWhisper(Locale.get("command.forcerot.disabled"), client);
            return;
        }

        if (client.getPlayer().getEntity() != null && client.getPlayer().getEntity().getRoom() != null) {
            if(isNumeric(params[0])) {
                try {
                    int rotation = Integer.parseInt(params[0]);

                    if ( rotation > 7 || rotation < 0) {
                        sendWhisper(Locale.get("command.forcerot.invalidrotation"), client);
                        return;
                    }


                    sendWhisper(Locale.get("command.forcerot.changed").replace("%rotation%", String.valueOf(rotation)), client);
                    client.getPlayer().getEntity().setAttribute(FORCE_ROTATION_KEY, rotation);

                } catch (Exception e) {
                    sendWhisper(Locale.get("command.forcerot.invalidformat"), client);
                }
            }
        }


    }

    @Override
    public String getPermission() {
        return "forcerot_command";
    }

    @Override
    public String getParameter() {
        return Locale.get("command.parameter.number");
    }
}
