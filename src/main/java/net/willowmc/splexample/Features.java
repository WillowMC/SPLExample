package net.willowmc.splexample;

import lombok.experimental.UtilityClass;
import net.willowmc.spl.SimplePluginLibrary;
import net.willowmc.spl.command.completion.CompletionRegistry;
import net.willowmc.spl.config.Config;
import net.willowmc.spl.config.ConfigCompletion;
import net.willowmc.spl.feature.FB;
import net.willowmc.spl.feature.Feature;
import net.willowmc.spl.tool.Tool;
import net.willowmc.spl.tool.ToolEncryption;
import net.willowmc.splexample.features.ConfigCommand;
import net.willowmc.splexample.features.ExampleCommand;
import net.willowmc.splexample.features.MultiFeature;
import net.willowmc.splexample.features.TestCommand;
import net.willowmc.splexample.features.single.SingleFeatureCommand;
import net.willowmc.splexample.features.single.SingleFeatureListener;
import net.willowmc.splexample.features.tool.ToolExample;
import net.willowmc.splexample.features.tool.ToolExampleCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
public class Features {
    public void init(SPLExample plugin) {
        // Set your tool salt for encrypted tool values, note that this won't help much if your plugin is open source
        ToolEncryption.TOOL_SALT = "SECRET_STRING";

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

        // register features
        Feature[] features = {
                new FB().command(new TestCommand()).b(),
                new FB().command(new ExampleCommand()).b(),
                new FB().command(new ConfigCommand(config, messages)).b(),
                new FB().command(new MultiFeature.MultiFeatureCommand()).listener(new MultiFeature.MultiFeatureListener()).b(),
                new FB().command(new SingleFeatureCommand()).listener(new SingleFeatureListener()).b(),
                new FB().command(new ToolExampleCommand()).b(),

        };

        // create spl instance
        // if you have a lot of config files, put them into an array instead and pass the array as the past argument
        SimplePluginLibrary spl = new SimplePluginLibrary(plugin, features, config, messages);

        // register tools
        Tool[] tools = {
                ToolExample.getTool(),
                ToolExample.getTool2()
        };
        spl.getTm().registerAll(tools, plugin);
    }
}
