package ru.qusarun.quscrypt.command.impl;

import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.QusCrypt;
import ru.qusarun.quscrypt.command.Command;
import ru.qusarun.quscrypt.command.CommandInfo;

@CommandInfo(usage = "ciphers", description = "shows list of available ciphers")
public class CiphersCommand extends Command {
    @Override
    public boolean execute(final String[] args) {
        if (args.length != 0)
            return invalidUsage();
        QusCrypt.INSTANCE.getCipherManager().getCiphers().forEach(cipher -> Logger.log("%b" + cipher.getName() + " %R(" + cipher.getType() + ")"));
        return true;
    }
}
