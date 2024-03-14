package org.multicoder.mcpaintball.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import org.multicoder.mcpaintball.common.data.MCPaintballWorldData;

public class MatchCommands
{
    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("mcpaintball").then(Commands.literal("match").then(Commands.literal("start").executes(MatchCommands::MatchStart)))).createBuilder().build();
        dispatcher.register(Commands.literal("mcpaintball").then(Commands.literal("match").then(Commands.literal("stop").executes(MatchCommands::MatchStop)))).createBuilder().build();
        dispatcher.register(Commands.literal("mcpaintball").then(Commands.literal("game").then(Commands.literal("start").executes(MatchCommands::GameStart)))).createBuilder().build();
        dispatcher.register(Commands.literal("mcpaintball").then(Commands.literal("game").then(Commands.literal("stop").executes(MatchCommands::GameStop)))).createBuilder().build();
    }

    private static int MatchStart(CommandContext<CommandSourceStack> context)
    {
        context.getSource().getServer().getPlayerList().broadcastSystemMessage(Component.translatable("mcpaintball.command.response.match_start"),true);
        MCPaintballWorldData.INSTANCE.MatchStarted = true;
        return 1;
    }
    private static int MatchStop(CommandContext<CommandSourceStack> context)
    {
        context.getSource().getServer().getPlayerList().broadcastSystemMessage(Component.translatable("mcpaintball.command.response.match_stop"),true);
        MCPaintballWorldData.INSTANCE.MatchStarted = false;
        return 1;
    }

    private static int GameStart(CommandContext<CommandSourceStack> context)
    {
        context.getSource().getServer().getPlayerList().broadcastSystemMessage(Component.translatable("mcpaintball.command.response.game_start"),true);
        MCPaintballWorldData.INSTANCE.MatchStarted = true;
        MCPaintballWorldData.INSTANCE.GameStarted = true;
        return 1;
    }
    private static int GameStop(CommandContext<CommandSourceStack> context)
    {
        context.getSource().getServer().getPlayerList().broadcastSystemMessage(Component.translatable("mcpaintball.command.response.game_stop"),true);
        MCPaintballWorldData.INSTANCE.MatchStarted = false;
        MCPaintballWorldData.INSTANCE.GameStarted = false;
        context.getSource().getServer().getPlayerList().getPlayers().forEach(serverPlayer ->
        {
            CompoundTag Persist = serverPlayer.getPersistentData();
            if(Persist.contains("mcpaintball.teamsTag"))
            {
                Persist.remove("mcpaintball.teamsTag");
            }
        });
        return 1;
    }
}
