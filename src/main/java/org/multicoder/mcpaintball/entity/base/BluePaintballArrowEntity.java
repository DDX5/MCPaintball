package org.multicoder.mcpaintball.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;
import org.multicoder.mcpaintball.init.entityinit;
import org.multicoder.mcpaintball.init.soundinit;
import org.multicoder.mcpaintball.network.Networking;
import org.multicoder.mcpaintball.network.packets.TeamPointS2CPacket;
import org.multicoder.mcpaintball.util.BlockHolder;
import org.multicoder.mcpaintball.util.config.MCPaintballConfig;
import org.multicoder.mcpaintball.util.player.TeamUtils;

public class BluePaintballArrowEntity extends AbstractArrow
{

    public BluePaintballArrowEntity(EntityType<? extends Entity> p_36858_, Level p_36859_) {super((EntityType<? extends AbstractArrow>) p_36858_, p_36859_);}

    public BluePaintballArrowEntity(Level p_36861_, double p_36862_, double p_36863_, double p_36864_) {super((EntityType<? extends AbstractArrow>) entityinit.BLUE_PAINTBALL.get(), p_36862_, p_36863_, p_36864_,p_36861_);}

    public BluePaintballArrowEntity(Level p_36866_, LivingEntity p_36867_) {super((EntityType<? extends AbstractArrow>) entityinit.BLUE_PAINTBALL.get(), p_36867_,p_36866_);}

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {return soundinit.SPLAT.get();}

    @Override
    protected ItemStack getPickupItem() {return ItemStack.EMPTY;}


    @Override
    protected void onHitEntity(EntityHitResult p_36757_)
    {
        if(!level.isClientSide)
        {
            if (p_36757_.getEntity() instanceof ServerPlayer)
            {
                ServerPlayer Shooter = (ServerPlayer) getOwner();
                ServerPlayer Target = (ServerPlayer) p_36757_.getEntity();
                ServerLevel level = Shooter.getLevel();
                if(TeamUtils.HasTeam(Shooter,Target))
                {
                    if(TeamUtils.IsSameTeam(Shooter,Target))
                    {
                        if(MCPaintballConfig.FRIENDLY_FIRE.get()) {return;}
                        else {Target.hurt(DamageSource.arrow(this,Shooter),2f);}
                    }
                    else
                    {
                        level.playSound(null,Shooter.blockPosition(),soundinit.DING.get(), SoundSource.PLAYERS,1f,1f);
                        Shooter.getCapability(PlayerTeamCapabilityProvider.CAPABILITY).ifPresent(cap ->{cap.IncPoints();Networking.sendToPlayer(new TeamPointS2CPacket(cap.GetPoints()),Shooter);});
                        Target.hurt(DamageSource.arrow(this,Shooter),2f);
                    }
                }
                else {return;}
            }
            this.kill();
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult)
    {
        if(!level.isClientSide)
        {
            BlockPos position = pResult.getBlockPos();
            Block block = level.getBlockState(position).getBlock();
            if(BlockHolder.BREAKABLES.contains(block))level.setBlockAndUpdate(position,Blocks.AIR.defaultBlockState());
            else super.onHitBlock(pResult);
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if(this.inGround)
        {
            if(this.inGroundTime > 60)
            {
                this.kill();
                this.discard();
            }
        }
    }
}
