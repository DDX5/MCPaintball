package org.multicoder.mcpaintball.common.items.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.MarkerManager;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.common.MCPaintballSounds;
import org.multicoder.mcpaintball.common.data.MCPaintballWorldData;
import org.multicoder.mcpaintball.common.data.capability.PaintballPlayerProvider;
import org.multicoder.mcpaintball.common.entity.paintball.PaintballEntity;
import org.multicoder.mcpaintball.common.utility.PaintballTeam;
import org.multicoder.mcpaintball.common.utility.ReloadManager;

import java.rmi.AccessException;

public class PistolItem extends Item {
    public PistolItem() {
        super(new Properties().durability(16).setNoRepair());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            if (!Screen.hasControlDown()) {
                ServerPlayer SP = (ServerPlayer) player;
                SP.getCapability(PaintballPlayerProvider.CAPABILITY).ifPresent(cap ->
                {
                    try {
                        if (MCPaintballWorldData.INSTANCE.StartedByName(cap.getName(PistolItem.class), PistolItem.class)) {
                            if (SP.getItemInHand(hand).getDamageValue() < 16) {
                                PaintballTeam Team = cap.GetTeam(PistolItem.class);
                                AbstractArrow Paintball = null;
                                try
                                {
                                    Paintball = new PaintballEntity(Team.getPaintball(PistolItem.class), player, level);
                                } catch (AccessException e) {MCPaintball.SECURITY_LOG.fatal(MarkerManager.getMarker("MCPaintball"),"An attempt Was made to access an anti-cheat protected class");}
                                Paintball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, 5f, 0f);
                                level.addFreshEntity(Paintball);
                                level.playSound(null, player.blockPosition(), MCPaintballSounds.SHOT.get(), SoundSource.PLAYERS, 1f, 1f);
                                SP.getItemInHand(hand).setDamageValue(SP.getItemInHand(hand).getDamageValue() + 1);
                                SP.getCooldowns().addCooldown(this, 20);
                            } else {
                                SP.displayClientMessage(Component.translatable("mcpaintball.response.reload").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED), true);
                            }
                        }
                    } catch (AccessException e) {
                        MCPaintball.SECURITY_LOG.fatal(MarkerManager.getMarker("MCPaintball"),"An attempt Was made to access an anti-cheat protected class");}
                });
            } else {
                ServerPlayer SP = (ServerPlayer) player;
                ItemStack Weapon = SP.getItemInHand(hand);
                try {
                    ReloadManager.ReloadWeapon(Weapon, SP, PistolItem.class);
                } catch (AccessException e) {MCPaintball.SECURITY_LOG.fatal(MarkerManager.getMarker("MCPaintball"),"An attempt Was made to access an anti-cheat protected class");}
            }
        }
        return super.use(level, player, hand);
    }
}
