package net.willowmc.splexample.features.single;

import net.willowmc.spl.event.Listener;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Listener in separate class
 */
public class SingleFeatureListener extends Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getBlockData().getMaterial() == Material.BEACON) {
            event.getPlayer().sendMessage("SFC");
        }
    }
}
