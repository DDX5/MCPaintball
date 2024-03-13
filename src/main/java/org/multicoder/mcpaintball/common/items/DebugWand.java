package org.multicoder.mcpaintball.common.items;

import com.mojang.blaze3d.platform.ScreenManager;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.multicoder.mcpaintball.common.entity.MCPaintballEntities;
import org.multicoder.mcpaintball.common.entity.paintball.HeavyPaintballEntity;
import org.multicoder.mcpaintball.common.entity.paintball.PaintballEntity;

public class DebugWand extends Item
{
    public DebugWand()
    {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_)
    {
        if(!p_41432_.isClientSide() && !Screen.hasShiftDown())
        {
            AbstractArrow R = new PaintballEntity(MCPaintballEntities.RED_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow G = new PaintballEntity(MCPaintballEntities.GREEN_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow B = new PaintballEntity(MCPaintballEntities.BLUE_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow C = new PaintballEntity(MCPaintballEntities.CYAN_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow M = new PaintballEntity(MCPaintballEntities.MAGENTA_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow Y = new PaintballEntity(MCPaintballEntities.YELLOW_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow LM = new PaintballEntity(MCPaintballEntities.LIME_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow LB = new PaintballEntity(MCPaintballEntities.LIGHT_BLUE_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow PI = new PaintballEntity(MCPaintballEntities.PINK_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow PU = new PaintballEntity(MCPaintballEntities.PURPLE_PAINTBALL.get(),p_41433_,p_41432_);
            R.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+1,0f,3f,0f);
            G.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+2,0f,3f,0f);
            B.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+3,0f,3f,0f);
            C.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+4,0f,3f,0f);
            M.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+5,0f,3f,0f);
            Y.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+6,0f,3f,0f);
            LM.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+7,0f,3f,0f);
            LB.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+8,0f,3f,0f);
            PI.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+9,0f,3f,0f);
            PU.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+10,0f,3f,0f);
            p_41432_.addFreshEntity(R);
            p_41432_.addFreshEntity(G);
            p_41432_.addFreshEntity(B);
            p_41432_.addFreshEntity(C);
            p_41432_.addFreshEntity(M);
            p_41432_.addFreshEntity(Y);
            p_41432_.addFreshEntity(LM);
            p_41432_.addFreshEntity(LB);
            p_41432_.addFreshEntity(PI);
            p_41432_.addFreshEntity(PU);
        } else if (!p_41432_.isClientSide() && Screen.hasShiftDown())
        {
            AbstractArrow R = new HeavyPaintballEntity(MCPaintballEntities.RED_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow G = new HeavyPaintballEntity(MCPaintballEntities.GREEN_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow B = new HeavyPaintballEntity(MCPaintballEntities.BLUE_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow C = new HeavyPaintballEntity(MCPaintballEntities.CYAN_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow M = new HeavyPaintballEntity(MCPaintballEntities.MAGENTA_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow Y = new HeavyPaintballEntity(MCPaintballEntities.YELLOW_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow LM = new HeavyPaintballEntity(MCPaintballEntities.LIME_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow LB = new HeavyPaintballEntity(MCPaintballEntities.LIGHT_BLUE_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow PI = new HeavyPaintballEntity(MCPaintballEntities.PINK_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            AbstractArrow PU = new HeavyPaintballEntity(MCPaintballEntities.PURPLE_HEAVY_PAINTBALL.get(),p_41433_,p_41432_);
            R.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+1,0f,3f,0f);
            G.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+2,0f,3f,0f);
            B.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+3,0f,3f,0f);
            C.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+4,0f,3f,0f);
            M.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+5,0f,3f,0f);
            Y.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+6,0f,3f,0f);
            LM.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+7,0f,3f,0f);
            LB.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+8,0f,3f,0f);
            PI.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+9,0f,3f,0f);
            PU.shootFromRotation(p_41433_,p_41433_.getXRot(),p_41433_.getYRot()+10,0f,3f,0f);
            p_41432_.addFreshEntity(R);
            p_41432_.addFreshEntity(G);
            p_41432_.addFreshEntity(B);
            p_41432_.addFreshEntity(C);
            p_41432_.addFreshEntity(M);
            p_41432_.addFreshEntity(Y);
            p_41432_.addFreshEntity(LM);
            p_41432_.addFreshEntity(LB);
            p_41432_.addFreshEntity(PI);
            p_41432_.addFreshEntity(PU);
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }
}
