package net.willowmc.splexample.features.tool;

import lombok.Getter;
import net.willowmc.spl.tool.Tool;
import net.willowmc.spl.tool.ToolDataType;
import org.bukkit.Material;

/**
 * Example tool-holder class
 */
public class ToolExample {
    @Getter
    private static final Tool tool = new Tool(Material.STONE, "Example Tool 1", "Lore Line 1\nLore Line 2")
            .enchanted()
            .consume()
            .data("data1", ToolDataType.INTEGER, 0)
            .registerLeftClick(ctx -> {
                ctx.player().sendMessage("" + ctx.tool().getData("data1", ToolDataType.INTEGER, ctx.event().getItem()));
                return true;
            });
    @Getter
    private static final Tool tool2 = new Tool(Material.EGG, "§6Example Tool 2", "§cLore Line 1\n§aLore Line 2")
            .data("data2", ToolDataType.INTEGER, 500)
            .registerRightClick(ctx -> {
                ctx.player().sendMessage("" + ctx.tool().getData("data2", ToolDataType.INTEGER, ctx.event().getItem()));
                return true;
            });
}
