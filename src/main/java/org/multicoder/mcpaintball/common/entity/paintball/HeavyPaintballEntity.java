package org.multicoder.mcpaintball.common.entity.paintball;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.multicoder.mcpaintball.common.MCPaintballSounds;
import org.multicoder.mcpaintball.common.data.MCPaintballTeamsDataHelper;
import org.multicoder.mcpaintball.common.data.MCPaintballWorldData;
import org.multicoder.mcpaintball.common.utility.FormattingManagers;
import org.multicoder.mcpaintball.common.utility.PaintballTeam;

@SuppressWarnings("all")
public class HeavyPaintballEntity extends AbstractArrow
{
    public HeavyPaintballEntity(EntityType<?> p_36721_, Level p_36722_)
    {
        super((EntityType<? extends AbstractArrow>) p_36721_, p_36722_);
    }
    public HeavyPaintballEntity(EntityType<?> p_36717_, LivingEntity p_36718_, Level p_36719_)
    {
        super((EntityType<? extends AbstractArrow>) p_36717_, p_36718_, p_36719_,ItemStack.EMPTY,ItemStack.EMPTY);
    }
    @Override
    protected void onHitBlock(BlockHitResult hitResult)
    {
        if(MCPaintballWorldData.INSTANCE.MatchStarted)
        {
            BlockPos Position = hitResult.getBlockPos();
            Explosion E = level().explode(this,Position.getX(), Position.getY(),Position.getZ(),5f, Level.ExplosionInteraction.MOB);
            E.getHitPlayers().keySet().forEach(player ->
            {
                String TK = getTypeName().getString().toLowerCase();
                PaintballTeam EntityTeam = FormattingManagers.FormatTypeToTeam(TK);
                if(MCPaintballTeamsDataHelper.HasTeam(player))
                {
                    PaintballTeam T = PaintballTeam.values()[MCPaintballTeamsDataHelper.FetchTeam(player)];
                    if(EntityTeam != T)
                    {
                        MCPaintballWorldData.IncrementByTranslationKey(TK);
                    }
                }
            });
        }
        this.kill();
        this.discard();
    }

    @Override
    public void tick()
    {
        super.tick();
        if(this.inGroundTime == 100){
            this.kill();
            this.discard();
        }
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent()
    {
        return MCPaintballSounds.SPLAT.get();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemStack.EMPTY;
    }
}
