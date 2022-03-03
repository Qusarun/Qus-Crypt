package ru.qusarun.quscrypt.command.impl;

import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.QusCrypt;
import ru.qusarun.quscrypt.command.Command;
import ru.qusarun.quscrypt.command.CommandInfo;

@CommandInfo(usage = "help", description = "shows list of available commands")
public class HelpCommand extends Command {
    @Override
    public boolean execute(final String[] args) {
        if (args.length != 0)
            return invalidUsage();
        QusCrypt.INSTANCE.getCommandManager().getCommands().forEach(command -> Logger.log("%b" + command.getUsage() + "%R - " + command.getDescription()));
        return true;
    }
}
