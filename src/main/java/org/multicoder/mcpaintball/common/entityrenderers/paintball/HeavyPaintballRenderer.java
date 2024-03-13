package org.multicoder.mcpaintball.common.entityrenderers.paintball;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.multicoder.mcpaintball.common.entity.MCPaintballEntities;
import org.multicoder.mcpaintball.common.entity.paintball.HeavyPaintballEntity;

public class HeavyPaintballRenderer extends ArrowRenderer<HeavyPaintballEntity>
{
    public static final ResourceLocation R = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/red_paintball_heavy.png");
    public static final ResourceLocation G = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/green_paintball_heavy.png");
    public static final ResourceLocation B = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/blue_paintball_heavy.png");
    public static final ResourceLocation C = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/cyan_paintball_heavy.png");
    public static final ResourceLocation M = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/magenta_paintball_heavy.png");
    public static final ResourceLocation Y = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/yellow_paintball_heavy.png");
    public static final ResourceLocation LI = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/lime_paintball_heavy.png");
    public static final ResourceLocation LB = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/light_blue_paintball_heavy.png");
    public static final ResourceLocation PI = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/pink_paintball_heavy.png");
    public static final ResourceLocation PU = new ResourceLocation("mcpaintball:textures/entity/projectiles/paintball_heavy/purple_paintball_heavy.png");

    public HeavyPaintballRenderer(EntityRendererProvider.Context p_173917_)
    {
        super(p_173917_);
    }

    @Override
    public ResourceLocation getTextureLocation(HeavyPaintballEntity entity)
    {
        if(entity.getType().equals(MCPaintballEntities.RED_HEAVY_PAINTBALL.get())){
            return R;
        } else if (entity.getType().equals(MCPaintballEntities.GREEN_HEAVY_PAINTBALL.get())) {
            return G;
        }else if (entity.getType().equals(MCPaintballEntities.BLUE_HEAVY_PAINTBALL.get())) {
            return B;
        }else if (entity.getType().equals(MCPaintballEntities.CYAN_HEAVY_PAINTBALL.get())) {
            return C;
        }else if (entity.getType().equals(MCPaintballEntities.MAGENTA_HEAVY_PAINTBALL.get())) {
            return M;
        }else if (entity.getType().equals(MCPaintballEntities.YELLOW_HEAVY_PAINTBALL.get())) {
            return Y;
        }else if (entity.getType().equals(MCPaintballEntities.LIME_HEAVY_PAINTBALL.get())) {
            return LI;
        }else if (entity.getType().equals(MCPaintballEntities.LIGHT_BLUE_HEAVY_PAINTBALL.get())) {
            return LB;
        }else if (entity.getType().equals(MCPaintballEntities.PINK_HEAVY_PAINTBALL.get())) {
            return PI;
        }else if (entity.getType().equals(MCPaintballEntities.PURPLE_HEAVY_PAINTBALL.get())) {
            return PU;
        }else{
            return null;
        }
    }
}
