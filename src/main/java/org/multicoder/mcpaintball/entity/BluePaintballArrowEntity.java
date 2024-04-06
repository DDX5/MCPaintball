package org.multicoder.mcpaintball.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.multicoder.mcpaintball.init.entityinit;
import org.multicoder.mcpaintball.init.soundinit;

public class BluePaintballArrowEntity extends PersistentProjectileEntity
{

    public BluePaintballArrowEntity(EntityType<?> entityType, World world) {
        super((EntityType<? extends PersistentProjectileEntity>) entityType, world);
    }

    public BluePaintballArrowEntity(World world, PlayerEntity user)
    {
        super((EntityType<? extends PersistentProjectileEntity>) entityinit.BLUE_PAINTBALL,user,world);
    }



    @Override
    protected SoundEvent getHitSound() {
        return soundinit.SPLAT;
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
}
