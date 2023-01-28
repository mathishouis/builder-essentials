package com.habbo.builderessentials;

import com.habbo.builderessentials.commands.ForceActCommand;
import com.habbo.builderessentials.commands.ForceRotCommand;
import com.habbo.builderessentials.commands.SetzCommand;
import com.habbo.builderessentials.events.SetRotation;
import com.habbo.builderessentials.events.SetSetz;
import com.habbo.builderessentials.events.SetState;
import com.habbo.config.Locale;
import com.habbo.game.commands.CommandManager;
import com.habbo.game.permissions.PermissionsManager;
import com.habbo.game.permissions.types.CommandPermission;
import com.habbo.plugin.PluginManager;
import com.habbo.plugin.types.Plugin;
import com.interfaces.plugin.IEventListener;
import org.apache.log4j.Logger;

public class BuilderEssentials extends Plugin implements IEventListener {

    Logger log = Logger.getLogger(BuilderEssentials.class.getName());

    @Override
    public void onEnable() throws Exception {
        PluginManager.getInstance().registerEvents(this, this);
        this.initializeLocales();
        this.initializePermissions();
        this.initializeCommands();
        this.initializeEvents();
    }

    @Override
    public void onDisable() throws Exception {}

    public void initializeCommands() {
        CommandManager.getInstance().registerCommand(Locale.get("command.setz.name"), new SetzCommand());
        CommandManager.getInstance().registerCommand(Locale.get("command.forceact.name"), new ForceActCommand());
        CommandManager.getInstance().registerCommand(Locale.get("command.forcerot.name"), new ForceRotCommand());
    }

    public void initializeEvents() {
        PluginManager.getInstance().registerEvents(this, new SetSetz());
        PluginManager.getInstance().registerEvents(this, new SetState());
        PluginManager.getInstance().registerEvents(this, new SetRotation());
    }

    public void initializeLocales() {
        Locale.registerLocale("command.forceact.changed", "État des mobis posés mis à %state%.");
        Locale.registerLocale("command.forceact.disabled", "Forceact désactivé.");
        Locale.registerLocale("command.forceact.invalidformat", "Vous avez mal formulé la commande. :forceact [valeur] ~ :forceact stop.");
        Locale.registerLocale("command.forceact.invalidstate", "L'état doit être compris entre 0 et 100.");
        Locale.registerLocale("command.forceact.name", "forceact,fa,setstate,ss");
        Locale.registerLocale("command.forcerot.changed", "Rotation des mobis posés mis à %rotation%.");
        Locale.registerLocale("command.forcerot.disabled", "Forcerot désactivé.");
        Locale.registerLocale("command.forcerot.invalidformat", "Vous avez mal formulé la commande. :forcerot [valeur] ~ :forcerot stop.");
        Locale.registerLocale("command.forcerot.invalidstate", "La rotation doit être comprise entre 0 et 7.");
        Locale.registerLocale("command.forcerot.name", "forcerot,fr,rot,rotation,setrotation,sr");
        Locale.registerLocale("command.setz.changed", "Taille du setz mise à %height%.");
        Locale.registerLocale("command.setz.disabled", "Setz désactivé.");
        Locale.registerLocale("command.setz.invalidformat", "Vous avez mal formulé la commande. :setz [valeur] ~ :setz stop.");
        Locale.registerLocale("command.setz.invalidheight", "La taille du setz doit être comprise entre -100 et 100.");
        Locale.registerLocale("command.setz.name", "setz,buildheight,bh");
    }

    public void initializePermissions() {
        PermissionsManager.getInstance().registerCommandPermission(new CommandPermission("forceact_command", 1, false, CommandPermission.RoomRights.RIGHTS));
        PermissionsManager.getInstance().registerCommandPermission(new CommandPermission("forcerot_command", 1, false, CommandPermission.RoomRights.RIGHTS));
        PermissionsManager.getInstance().registerCommandPermission(new CommandPermission("setz_command", 1, false, CommandPermission.RoomRights.RIGHTS));
    }

    public static void main(String[] args)
    {
        System.out.println("Don't run this separately, you need Asteroid Emulator to get this working.");
    }
}
