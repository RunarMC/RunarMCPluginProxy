package net.runarmc.runarmcpluginproxy.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public abstract class AbstractCommand extends Command {

    protected String FOX_TAG = ChatColor.YELLOW + "[" + ChatColor.RED + "FoxGuard" + ChatColor.YELLOW + "]";

    public AbstractCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        this.onExecute(sender, args);
    }

    public abstract void onExecute(CommandSender sender, String[] arguments);
}
