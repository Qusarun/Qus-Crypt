package ru.qusarun.quscrypt.command.impl;

import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.QusCrypt;
import ru.qusarun.quscrypt.command.Command;
import ru.qusarun.quscrypt.command.CommandInfo;
import ru.qusarun.quscrypt.util.StringUtil;

@CommandInfo(usage = "decrypt <message>", description = "decrypts a message with the selected cipher")
public class DecryptCommand extends Command {
    @Override public boolean execute(final String[] args) { return args.length < 1? invalidUsage() : QusCrypt.INSTANCE.getCipherManager().getCipher() == null? Logger.log("Please select a cipher first") : Logger.log(QusCrypt.INSTANCE.getCipherManager().getCipher()._decrypt(StringUtil.join(args))); }
}
