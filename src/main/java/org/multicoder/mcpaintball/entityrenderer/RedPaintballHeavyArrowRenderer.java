package org.multicoder.mcpaintball.entityrenderer;


import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.multicoder.mcpaintball.entity.heavy.RedPaintballHeavyArrowEntity;

@OnlyIn(Dist.CLIENT)
public class RedPaintballHeavyArrowRenderer extends ArrowRenderer<RedPaintballHeavyArrowEntity>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation("mcpaintball:textures/entity/projectiles/red_paintball_heavy.png");

    public RedPaintballHeavyArrowRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(RedPaintballHeavyArrowEntity p_114482_)
    {
        return TEXTURE;
    }
}
