package ru.qusarun.quscrypt.command.impl;

import ru.qusarun.quscrypt.command.Command;
import ru.qusarun.quscrypt.command.CommandInfo;

@CommandInfo(usage = "exit", description = "exits the program")
public class ExitCommand extends Command {
    @Override
    public boolean execute(String[] args) {
        if (args.length > 0)
            return invalidUsage();
        System.exit(0);
        return true;
    }
}
