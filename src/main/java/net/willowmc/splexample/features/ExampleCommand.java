package net.willowmc.splexample.features;

import net.willowmc.spl.command.Command;
import net.willowmc.spl.command.CommandContext;
import net.willowmc.spl.command.CommandData;
import net.willowmc.spl.command.Subcommand;
import net.willowmc.splexample.TestEnum;

/**
 * Complex spl example command.
 */
@CommandData(name = "example", description = "This is a command to test completion", completion = "x y z self player enum custom-text", aliases = "ex")
public class ExampleCommand extends Command {
    @Override
    protected boolean doCommand(CommandContext ctx) {
        int x = getIntArg(ctx, 0);
        int y = getIntArg(ctx, 1);
        int z = getIntArg(ctx, 2);
        String self = getArg(ctx, 3);
        String player = getArg(ctx, 4);
        TestEnum testEnum = getEnumArg(ctx, 5, TestEnum.class);
        String custom = getArg(ctx, 6);
        ctx.respond(x + " " + y + " " + z + " " + self + " " + player + " " + testEnum.name() + " " + custom);
        return true;
    }

    /**
     * Declare subcommands with separate logic and completions. Subcommands can be nested infinitely.
     * If writing a lot of logic I recommend using method references instead of lambdas.
     */
    @Override
    protected Subcommand[] declareSubcommands() {
        return new Subcommand[]{
                new Subcommand("subcommand-1", "enum", ctx -> {
                    TestEnum testEnum = getEnumArg(ctx, 1, TestEnum.class);
                    ctx.respond("Subcommand 1 -> " + testEnum);
                    return true;
                }, new Subcommand[]{
                        new Subcommand("nested-subcommand", "player", ctx -> {
                            String player = getArg(ctx, 2);
                            ctx.respond("Nested Subcommand -> " + player);
                            return true;
                        })
                }),
                new Subcommand("subcommand-2", "x y z", ctx -> {
                    int x = getIntArg(ctx, 1);
                    int y = getIntArg(ctx, 2);
                    int z = getIntArg(ctx, 3);
                    ctx.respond("Subcommand 2 -> " + x + " " + y + " " + z);
                    return true;
                })
        };
    }
}