package net.willowmc.splexample.commands;

import net.willowmc.spl.command.Command;
import net.willowmc.spl.command.CommandContext;
import net.willowmc.spl.command.CommandData;
import net.willowmc.spl.config.Config;

/**
 * Spl config test command.
 */
@CommandData(name = "config", description = "Test spl configs.")
public class ConfigCommand extends Command {
    private final Config config;
    private final Config messages;

    public ConfigCommand(Config config, Config messages) {
        this.config = config;
        this.messages = messages;
    }

    @Override
    protected boolean doCommand(CommandContext ctx) {
        ctx.respond("" + config.getInt("test"));
        ctx.respond(messages.getStringFormatted("test"));
        return true;
    }
}
