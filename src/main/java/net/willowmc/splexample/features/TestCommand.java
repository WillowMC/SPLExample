package net.willowmc.splexample.features;

import net.willowmc.spl.command.Command;
import net.willowmc.spl.command.CommandContext;
import net.willowmc.spl.command.CommandData;

/**
 * Simple spl example command.
 */
@CommandData(name = "test", description = "This is a test command.", completion = "player")
public class TestCommand extends Command {
    @Override
    protected boolean doCommand(CommandContext ctx) {
        String player = getArg(ctx, 0);
        if (player == null) return false;
        ctx.respond("Test: " + player);
        return true;
    }
}
