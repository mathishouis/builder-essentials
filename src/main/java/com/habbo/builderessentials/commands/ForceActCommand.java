package com.habbo.builderessentials.commands;

import com.habbo.config.Locale;
import com.habbo.game.commands.ChatCommand;
import com.habbo.network.sessions.Session;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class ForceActCommand extends ChatCommand {

    public static String FORCE_ACT_KEY = "be.force_act";

    @Override
    public void execute(Session client, String[] params) {

        if (params.length != 1) {
            sendWhisper(Locale.get("command.forceact.invalidformat"), client);
            return;
        }

        if(params[0].equals("stop")) {
            if(client.getPlayer().getEntity().hasAttribute(FORCE_ACT_KEY)) {
                client.getPlayer().getEntity().removeAttribute(FORCE_ACT_KEY);
                sendWhisper(Locale.get("command.forceact.disabled"), client);
                return;
            }
            sendWhisper(Locale.get("command.forceact.disabled"), client);
            return;
        }

        if (client.getPlayer().getEntity() != null && client.getPlayer().getEntity().getRoom() != null) {
            if(isNumeric(params[0])) {
                try {
                    int state = Integer.parseInt(params[0]);

                    if ( state > 100 || state < 0) {
                        sendWhisper(Locale.get("command.forceact.invalidstate"), client);
                        return;
                    }


                    sendWhisper(Locale.get("command.forceact.changed").replace("%state%", String.valueOf(state)), client);
                    client.getPlayer().getEntity().setAttribute(FORCE_ACT_KEY, state);

                } catch (Exception e) {
                    sendWhisper(Locale.get("command.forceact.invalidformat"), client);
                }
            }
        }


    }

    @Override
    public String getPermission() {
        return "forceact_command";
    }

    @Override
    public String getParameter() {
        return Locale.get("command.parameter.number");
    }
}
