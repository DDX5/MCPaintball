package org.multicoder.mcpaintball.common.items.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
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
import org.multicoder.mcpaintball.common.entity.paintball.HeavyPaintballEntity;
import org.multicoder.mcpaintball.common.entity.paintball.PaintballEntity;
import org.multicoder.mcpaintball.common.utility.PaintballTeam;
import org.multicoder.mcpaintball.common.utility.ReloadManager;

public class BazookaItem extends Item
{
    public BazookaItem()
    {
        super(new Properties().durability(4).setNoRepair());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        if(!level.isClientSide())
        {
            if(!Screen.hasControlDown())
            {
                ServerPlayer SP = (ServerPlayer) player;
                SP.getCapability(PaintballPlayerProvider.CAPABILITY).ifPresent(cap ->
                {
                    if(MCPaintballWorldData.INSTANCE.StartedByName(cap.getName()))
                    {
                        if(SP.getItemInHand(hand).getDamageValue() < 4)
                        {
                            PaintballTeam Team = cap.GetTeam();
                            AbstractArrow Paintball = new HeavyPaintballEntity(Team.getHeavyPaintball(),player,level);
                            Paintball.shootFromRotation(player,player.getXRot(),player.getYRot(),0f,5f,0f);
                            level.addFreshEntity(Paintball);
                            level.playSound(null,player.blockPosition(), MCPaintballSounds.BAZOOKA.get(), SoundSource.PLAYERS,1f,1f);
                            SP.getItemInHand(hand).setDamageValue(SP.getItemInHand(hand).getDamageValue() + 1);
                            SP.getCooldowns().addCooldown(this,40);
                        }
                        else
                        {
                            SP.displayClientMessage(Component.translatable("mcpaintball.response.reload").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED),true);
                        }
                    }
                });
            }
            else
            {
                ServerPlayer SP = (ServerPlayer) player;
                ItemStack Weapon = SP.getItemInHand(hand);
                ReloadManager.ReloadWeapon(Weapon,SP);
            }
        }
        return super.use(level, player, hand);
    }
}
