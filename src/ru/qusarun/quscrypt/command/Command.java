package ru.qusarun.quscrypt.command;

import lombok.Getter;
import ru.qusarun.flogger.Logger;

public abstract class Command {
    @Getter private final String name = this.getClass().getSimpleName().replace("Command", "").toLowerCase(), usage, description;

    public Command() {
        final CommandInfo info = this.getClass().getAnnotation(CommandInfo.class);

        this.usage = info.usage().replace("<", "%R<%b").replace(">", "%R>%b").replace("[", "%R[%b").replace("]", "%R]%b");
        this.description = info.description();
    }

    public abstract boolean execute(final String[] args);

    public boolean invalidUsage() { return Logger.log("%bUsage %R> %b" + usage); }
}
