package org.multicoder.mcpaintball.common.entityrenderers.paintball;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.multicoder.mcpaintball.common.entity.MCPaintballEntities;
import org.multicoder.mcpaintball.common.entity.paintball.PaintballEntity;

public class PaintballEntityRenderer extends ArrowRenderer<PaintballEntity>
{
    public static final ResourceLocation R = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/red_paintball.png");
    public static final ResourceLocation G = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/green_paintball.png");
    public static final ResourceLocation B = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/blue_paintball.png");
    public static final ResourceLocation C = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/cyan_paintball.png");
    public static final ResourceLocation M = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/magenta_paintball.png");
    public static final ResourceLocation Y = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/yellow_paintball.png");
    public static final ResourceLocation LI = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/lime_paintball.png");
    public static final ResourceLocation LB = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/light_blue_paintball.png");
    public static final ResourceLocation PI = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/pink_paintball.png");
    public static final ResourceLocation PU = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball/purple_paintball.png");

    public PaintballEntityRenderer(EntityRendererProvider.Context p_173917_)
    {
        super(p_173917_);
    }

    @Override
    public ResourceLocation getTextureLocation(PaintballEntity entity)
    {
        if(entity.getType().equals(MCPaintballEntities.RED_PAINTBALL.get())){
            return R;
        } else if (entity.getType().equals(MCPaintballEntities.GREEN_PAINTBALL.get())) {
            return G;
        }else if (entity.getType().equals(MCPaintballEntities.BLUE_PAINTBALL.get())) {
            return B;
        }else if (entity.getType().equals(MCPaintballEntities.CYAN_PAINTBALL.get())) {
            return C;
        }else if (entity.getType().equals(MCPaintballEntities.MAGENTA_PAINTBALL.get())) {
            return M;
        }else if (entity.getType().equals(MCPaintballEntities.YELLOW_PAINTBALL.get())) {
            return Y;
        }else if (entity.getType().equals(MCPaintballEntities.LIME_PAINTBALL.get())) {
            return LI;
        }else if (entity.getType().equals(MCPaintballEntities.LIGHT_BLUE_PAINTBALL.get())) {
            return LB;
        }else if (entity.getType().equals(MCPaintballEntities.PINK_PAINTBALL.get())) {
            return PI;
        }else if (entity.getType().equals(MCPaintballEntities.PURPLE_PAINTBALL.get())) {
            return PU;
        }else{
            return null;
        }
    }
}
