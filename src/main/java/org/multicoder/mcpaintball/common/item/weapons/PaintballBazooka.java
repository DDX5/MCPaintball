package org.multicoder.mcpaintball.common.item.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.multicoder.mcpaintball.common.capability.PaintballPlayer;
import org.multicoder.mcpaintball.common.capability.PaintballPlayerProvider;
import org.multicoder.mcpaintball.common.config.MCPaintballConfig;
import org.multicoder.mcpaintball.common.entity.HeavyPaintballEntity;
import org.multicoder.mcpaintball.common.init.soundinit;

import java.util.List;

@SuppressWarnings("all")
public class PaintballBazooka extends Item {

    public PaintballBazooka() {
        super(new Properties().durability(4));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide())
        {
            if(pPlayer.getItemInHand(pUsedHand).getDamageValue() < 4)
            {
                AbstractArrow Proj;
                ServerPlayer Player = (ServerPlayer) pPlayer;
                PaintballPlayer PPlayer = Player.getCapability(PaintballPlayerProvider.CAPABILITY).resolve().get();
                Proj = new HeavyPaintballEntity((EntityType<? extends AbstractArrow>) PPlayer.Team.GetHeavy(), Player, pLevel);
                Proj.shootFromRotation(Player, Player.getXRot(), Player.getYRot(), 0f, 3f, MCPaintballConfig.BAZOOKA_INACCURACY.get().floatValue());
                pLevel.addFreshEntity(Proj);
                pLevel.playSound(null, Player.blockPosition(), soundinit.BAZOOKA.get(), SoundSource.PLAYERS, 1f, 1f);
                Player.getCooldowns().addCooldown(this, 40);
                pPlayer.getItemInHand(pUsedHand).setDamageValue(pPlayer.getItemInHand(pUsedHand).getDamageValue() + 1);
                return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
            }
            else
            {
                pPlayer.sendSystemMessage(Component.translatable("text.mcpaintball.reload_needed").withStyle(ChatFormatting.BOLD));
            }
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("text.mcpaintball.bazooka_spec").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD));
        } else {
            pTooltipComponents.add(Component.translatable("text.mcpaintball.press_shift").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED));
        }
    }
}
