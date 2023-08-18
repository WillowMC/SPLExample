package net.willowmc.splexample;

import lombok.experimental.UtilityClass;
import net.willowmc.spl.SimplePluginLibrary;
import net.willowmc.spl.command.Command;
import net.willowmc.spl.command.completion.CompletionRegistry;
import net.willowmc.spl.config.Config;
import net.willowmc.spl.config.ConfigCompletion;
import net.willowmc.splexample.commands.ConfigCommand;
import net.willowmc.splexample.commands.ExampleCommand;
import net.willowmc.splexample.commands.TestCommand;
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

        // store these configs in a way they can be accessed where they are needed
        // you can get them again by name, from the SimplePluginLibrary.getCfg.get() method, but it is faster to store it yourself
        Config config = new Config("config");
        Config messages = new Config("messages", new ConfigCompletion("placeholder", () -> "test"));

        // register commands
        Command[] commands = {
                new TestCommand(),
                new ExampleCommand(),
                new ConfigCommand(config, messages)
        };

        // create spl instance
        // if you have a lot of config files, put them into an array instead and pass the array as the past argument
        new SimplePluginLibrary(plugin, commands, config, messages);
    }
}
