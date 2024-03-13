package org.multicoder.mcpaintball.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.command.EnumArgument;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.common.utility.PaintballClass;
import org.multicoder.mcpaintball.common.utility.PaintballTeam;

public class TeamCommands
{
    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("mcpaintball").then(Commands.literal("team").then(Commands.literal("set").then(Commands.argument("team",EnumArgument.enumArgument(PaintballTeam.class)).executes(TeamCommands::setTeamCommand))))).createBuilder().build();
        dispatcher.register(Commands.literal("mcpaintball").then(Commands.literal("class").then(Commands.literal("set").then(Commands.argument("class",EnumArgument.enumArgument(PaintballClass.class)).executes(TeamCommands::setClassCommand))))).createBuilder().build();
    }

    private static int setClassCommand(CommandContext<CommandSourceStack> context)
    {
        try
        {
            CommandSourceStack source = context.getSource();
            ServerPlayer player = source.getPlayerOrException();
            PaintballClass selected = context.getArgument("class", PaintballClass.class);
            CompoundTag playerData = player.getPersistentData();
            if(!playerData.contains("mcpaintball.teamsTag"))
            {
                CompoundTag paintball = new CompoundTag();
                paintball.putInt("class",selected.ordinal());
                playerData.put("mcpaintball.teamsTag",paintball);
            }
            else
            {
                CompoundTag paintball = playerData.getCompound("mcpaintball.teamsTag");
                paintball.putInt("class",selected.ordinal());
            }
            player.sendSystemMessage(Component.translatable("mcpaintball.command.response.class.set",selected.name().toLowerCase()));
            return 0;
        }
        catch (Exception exception)
        {
            MCPaintball.LOG.error(exception);
            return -1;
        }
    }

    private static int setTeamCommand(CommandContext<CommandSourceStack> context)
    {
        try
        {
            CommandSourceStack source = context.getSource();
            ServerPlayer player = source.getPlayerOrException();
            PaintballTeam selected = context.getArgument("team", PaintballTeam.class);
            CompoundTag playerData = player.getPersistentData();
            if(!playerData.contains("mcpaintball.teamsTag")) {
                CompoundTag paintball = new CompoundTag();
                paintball.putInt("team",selected.ordinal());
                playerData.put("mcpaintball.teamsTag",paintball);
            }
            else {
                CompoundTag paintball = playerData.getCompound("mcpaintball.teamsTag");
                paintball.putInt("team",selected.ordinal());
            }
            player.getDisplayName().getStyle().applyFormat(selected.getDisplayColor());
            player.sendSystemMessage(Component.translatable("mcpaintball.command.response.team.set",selected.name().toLowerCase()));
            return 0;
        }
        catch (Exception exception)
        {
            MCPaintball.LOG.error(exception);
            return -1;
        }
    }
}
