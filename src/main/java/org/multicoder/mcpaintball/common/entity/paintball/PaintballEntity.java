package org.multicoder.mcpaintball.common.entity.paintball;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.multicoder.mcpaintball.common.MCPaintballSounds;
import org.multicoder.mcpaintball.common.data.MCPaintballWorldData;
import org.multicoder.mcpaintball.common.utility.FormattingManagers;
import org.multicoder.mcpaintball.common.utility.PaintballTeam;

@SuppressWarnings("all")
public class PaintballEntity extends AbstractArrow
{
    public PaintballEntity(EntityType<?> entityType, Level level) {
        super((EntityType<? extends AbstractArrow>) entityType, level,ItemStack.EMPTY);
    }
    public PaintballEntity(EntityType<?> entityType, LivingEntity shooter, Level level) {
        super((EntityType<? extends AbstractArrow>) entityType, shooter, level,ItemStack.EMPTY);
    }


    @Override
    protected void onHitEntity(EntityHitResult hitResult)
    {
        if(!level().isClientSide())
        {
            String TN = getTypeName().getString();
            PaintballTeam EntityTeam = FormattingManagers.FormatTypeToTeam(TN);
            if(hitResult.getEntity() instanceof Player player)
            {
                CompoundTag Persist = player.getPersistentData();
                if(Persist.contains("mcpaintball.teamsTag"))
                {
                    CompoundTag TeamsData = Persist.getCompound("mcpaintball.teamsTag");
                    PaintballTeam T = PaintballTeam.values()[TeamsData.getInt("team")];
                    if(EntityTeam != T)
                    {
                        MCPaintballWorldData.IncrementByTranslationKey(TN);
                    }
                }
            }
        }
        this.kill();
        this.discard();
    }

    @Override
    public void tick()
    {
        super.tick();
        if(this.inGroundTime == 100) {
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
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
}
