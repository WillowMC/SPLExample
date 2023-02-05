package net.mcaurora.splexample;

import lombok.experimental.UtilityClass;
import net.mcaurora.spl.SimplePluginLibrary;
import net.mcaurora.spl.command.Command;
import net.mcaurora.spl.command.completion.CompletionRegistry;
import net.mcaurora.splexample.commands.ExampleCommand;
import net.mcaurora.splexample.commands.TestCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
public class Features {
    public void init(SPLExample plugin) {
        // register tab completions
        CompletionRegistry.registerDouble("x", user -> user.getLocation().getX());
        CompletionRegistry.registerDouble("y", user -> user.getLocation().getY());
        CompletionRegistry.registerDouble("z", user -> user.getLocation().getZ());
        CompletionRegistry.registerString("self", Player::getName);
        CompletionRegistry.register("player", user -> Bukkit.getOnlinePlayers().stream().map(Player::getName).toList());
        CompletionRegistry.registerEnum("enum", TestEnum.class);

        // register commands and instance spl
        Command[] commands = {
                new TestCommand(),
                new ExampleCommand()
        };
        new SimplePluginLibrary(plugin, commands);
    }
}
