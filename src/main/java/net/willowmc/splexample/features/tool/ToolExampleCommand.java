package net.willowmc.splexample.features.tool;

import net.willowmc.spl.command.Command;
import net.willowmc.spl.command.CommandContext;
import net.willowmc.spl.command.CommandData;
import net.willowmc.spl.tool.ToolData;
import net.willowmc.spl.tool.ToolDataType;

/**
 * Command to give tools
 */
@CommandData(name = "tool", description = "get a test tool", completion = "num1 num2")
public class ToolExampleCommand extends Command {
    @Override
    protected boolean doCommand(CommandContext ctx) {
        Integer num1 = getIntArg(ctx, 0);
        Integer num2 = getIntArg(ctx, 1);
        if (num1 == null || num2 == null) {
            ctx.respond("Invalid argument(s)");
            return true;
        }
        // Always create tool items after the plugin and spl has been initialized
        ctx.getPlayer().getInventory().addItem(ToolExample.getTool().getItem(new ToolData<>("data1", ToolDataType.INTEGER, num1 + num2)), ToolExample.getTool2().getItem());
        return true;
    }
}
