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
import org.multicoder.mcpaintball.common.MCPaintballSounds;
import org.multicoder.mcpaintball.common.data.MCPaintballWorldData;
import org.multicoder.mcpaintball.common.data.capability.PaintballPlayerProvider;
import org.multicoder.mcpaintball.common.entity.paintball.PaintballEntity;
import org.multicoder.mcpaintball.common.utility.PaintballTeam;
import org.multicoder.mcpaintball.common.utility.ReloadManager;

import java.rmi.AccessException;

public class ShotgunItem extends Item {
    public ShotgunItem() {
        super(new Properties().durability(12).setNoRepair());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            if (!Screen.hasControlDown()) {
                ServerPlayer SP = (ServerPlayer) player;
                SP.getCapability(PaintballPlayerProvider.CAPABILITY).ifPresent(cap ->
                {
                    try {
                        if (MCPaintballWorldData.INSTANCE.StartedByName(cap.getName(ShotgunItem.class), ShotgunItem.class)) {
                            if (SP.getItemInHand(hand).getDamageValue() < 4) {
                                PaintballTeam Team = cap.GetTeam(ShotgunItem.class);
                                AbstractArrow Paintball1 = null;
                                AbstractArrow Paintball3 = null;
                                AbstractArrow Paintball2 = null;
                                try
                                {
                                    Paintball1 = new PaintballEntity(Team.getPaintball(ShotgunItem.class), player, level);
                                    Paintball2 = new PaintballEntity(Team.getPaintball(ShotgunItem.class), player, level);
                                    Paintball3 = new PaintballEntity(Team.getPaintball(ShotgunItem.class), player, level);
                                } catch (AccessException e) {}
                                Paintball1.shootFromRotation(player, player.getXRot(), player.getYRot() + 16, 0f, 5f, 0f);
                                Paintball2.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, 5f, 0f);
                                Paintball3.shootFromRotation(player, player.getXRot(), player.getYRot() - 16, 0f, 5f, 0f);
                                level.addFreshEntity(Paintball1);
                                level.addFreshEntity(Paintball2);
                                level.addFreshEntity(Paintball3);
                                level.playSound(null, player.blockPosition(), MCPaintballSounds.SHOT.get(), SoundSource.PLAYERS, 1f, 1f);
                                SP.getItemInHand(hand).setDamageValue(SP.getItemInHand(hand).getDamageValue() + 1);
                                SP.getCooldowns().addCooldown(this, 25);
                            } else {
                                SP.displayClientMessage(Component.translatable("mcpaintball.response.reload").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED), true);
                            }
                        }
                    } catch (AccessException e) {}
                });
            } else {
                ServerPlayer SP = (ServerPlayer) player;
                ItemStack Weapon = SP.getItemInHand(hand);
                try {
                    ReloadManager.ReloadWeapon(Weapon, SP, ShotgunItem.class);
                } catch (AccessException e) {}
            }
        }
        return super.use(level, player, hand);
    }
}
