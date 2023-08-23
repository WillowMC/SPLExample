package net.willowmc.splexample.features.single;

import net.willowmc.spl.command.Command;
import net.willowmc.spl.command.CommandContext;
import net.willowmc.spl.command.CommandData;

/**
 * Command in separate class
 */
@CommandData(name = "sfc")
public class SingleFeatureCommand extends Command {
    @Override
    protected boolean doCommand(CommandContext ctx) {
        ctx.respond("SFC");
        return true;
    }
}
