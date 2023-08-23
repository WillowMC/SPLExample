package net.willowmc.splexample.features;

import net.willowmc.spl.command.Command;
import net.willowmc.spl.command.CommandContext;
import net.willowmc.spl.command.CommandData;
import net.willowmc.spl.event.Listener;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Multiple features in the same class
 */
public class MultiFeature {
    /**
     * Declare command and event as inner classes
     */
    @CommandData(name = "mfc")
    public static class MultiFeatureCommand extends Command {

        @Override
        protected boolean doCommand(CommandContext ctx) {
            ctx.respond("MFC");
            return true;
        }
    }

    /**
     * SPL Event Listener is just a wrapper for spigot listener
     */
    public static class MultiFeatureListener extends Listener {
        @EventHandler(priority = EventPriority.HIGH)
        public void onBlockPlace(BlockPlaceEvent event) {
            if (event.getBlockPlaced().getBlockData().getMaterial() == Material.ENCHANTING_TABLE) {
                event.getPlayer().sendMessage("MFC");
            }
        }
    }
}
