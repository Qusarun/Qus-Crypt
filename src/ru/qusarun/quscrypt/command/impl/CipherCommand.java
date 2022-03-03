package ru.qusarun.quscrypt.command.impl;

import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.QusCrypt;
import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.command.Command;
import ru.qusarun.quscrypt.command.CommandInfo;
import ru.qusarun.quscrypt.util.StringUtil;

@CommandInfo(usage = "cipher <cipher> [keys...]", description = "selects a cipher")
public class CipherCommand extends Command {
    @Override
    public boolean execute(final String[] args) {
        if (args.length < 1)
            return invalidUsage();
        final Cipher cipher = QusCrypt.INSTANCE.getCipherManager().getCipherByName(args[0]);
        return cipher == null? Logger.log("No cipher with name %b" + args[0]) : cipher.setKey(StringUtil.subArray(args, 1))? QusCrypt.INSTANCE.getCipherManager().setCipher(cipher) && Logger.log("Selected %b" + cipher.getName()) : Logger.log("Invalid key for %b" + cipher.getName() + "%R, key should be of type %b" + cipher.getKeyType().name());
    }
}
