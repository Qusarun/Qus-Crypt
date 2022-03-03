package ru.qusarun.quscrypt;

import lombok.Getter;
import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.cipher.CipherManager;
import ru.qusarun.quscrypt.command.CommandManager;

public enum QusCrypt {
    INSTANCE;

    @Getter private final CipherManager  cipherManager  = new CipherManager ();
    @Getter private final CommandManager commandManager = new CommandManager();

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        while (true) commandManager.execute(Logger.input("> %b"));
    }
}
