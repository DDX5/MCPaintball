package org.multicoder.mcpaintball.item.armor.boots;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.multicoder.mcpaintball.init.tabinit;
import org.multicoder.mcpaintball.utility.PaintballArmor;

public class GreenBoots extends ArmorItem
{

    public GreenBoots()
    {
        super(PaintballArmor.Green, EquipmentSlot.FEET, new FabricItemSettings().group(tabinit.paintball));
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks)
    {
        stack.setDamage(0);
        super.usageTick(world, user, stack, remainingUseTicks);
    }
}
