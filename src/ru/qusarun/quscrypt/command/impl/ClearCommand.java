package ru.qusarun.quscrypt.command.impl;

import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.command.Command;
import ru.qusarun.quscrypt.command.CommandInfo;

@CommandInfo(usage = "cipher <cipher> [keys...]", description = "selects a cipher")
public class ClearCommand extends Command {
    @Override public boolean execute(final String[] args) { return args.length != 0? invalidUsage() : Logger.clear(); }
}
