package ru.qusarun.quscrypt.command;

import lombok.Getter;
import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.command.impl.*;
import ru.qusarun.quscrypt.util.ClassUtil;

import java.util.List;

public class CommandManager {
    @Getter private final List<Command> commands = ClassUtil.createInstances(List.of(
            BreakDeCommand.class,
            BreakEnCommand.class,
            BreakRuCommand.class,
            CipherCommand.class,
            CiphersCommand.class,
            ClearCommand.class,
            DecryptCommand.class,
            DecryptFileCommand.class,
            EncryptCommand.class,
            EncryptFileCommand.class,
            ExitCommand.class,
            HelpCommand.class
    ));

    public final void execute(final String line) { ClassUtil.execute(getCommandByName(line.split(" ")[0]), command -> command == null? Logger.log("Can't find command with name %b" + line.split(" ")[0]) : command.execute(line.contains(" ")? line.substring(line.split(" ")[0].length() + 1).split(" ") : new String[0])); }

    public final Command getCommandByName(final String name) { return commands.stream().filter(command -> command.getName().equalsIgnoreCase(name)).findAny().orElse(null); }
}
