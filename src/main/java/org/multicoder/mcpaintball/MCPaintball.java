package org.multicoder.mcpaintball;


import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.multicoder.mcpaintball.common.MCPaintballSounds;
import org.multicoder.mcpaintball.common.commands.MatchCommands;
import org.multicoder.mcpaintball.common.commands.TeamCommands;
import org.multicoder.mcpaintball.common.data.MCPaintballWorldData;
import org.multicoder.mcpaintball.common.entity.*;
import org.multicoder.mcpaintball.common.entity.paintball.*;
import org.multicoder.mcpaintball.common.entityrenderers.paintball.*;
import org.multicoder.mcpaintball.common.items.MCPaintballItems;

@Mod(MCPaintball.MOD_ID)
public class MCPaintball
{
    public static final String MOD_ID = "mcpaintball";
    public static Logger LOG = LogManager.getLogger(MOD_ID);

    public MCPaintball(IEventBus eventBus)
    {
        eventBus.addListener(this::buildCreativeTabContents);
        eventBus.addListener(this::registerEntityRenderers);
        MCPaintballItems.ITEMS.register(eventBus);
        MCPaintballEntities.ENTITIES.register(eventBus);
        MCPaintballSounds.SOUNDS.register(eventBus);
    }

    @SuppressWarnings("unchecked")
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.RED_PAINTBALL.get(), PaintballEntityRenderer::new);
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.GREEN_PAINTBALL.get(), PaintballEntityRenderer::new);
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.BLUE_PAINTBALL.get(), PaintballEntityRenderer::new);
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.CYAN_PAINTBALL.get(), PaintballEntityRenderer::new);
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.MAGENTA_PAINTBALL.get(), PaintballEntityRenderer::new);
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.YELLOW_PAINTBALL.get(), PaintballEntityRenderer::new);
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.LIME_PAINTBALL.get(), PaintballEntityRenderer::new);
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.LIGHT_BLUE_PAINTBALL.get(), PaintballEntityRenderer::new);
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.PINK_PAINTBALL.get(), PaintballEntityRenderer::new);
        event.registerEntityRenderer((EntityType<PaintballEntity>) MCPaintballEntities.PURPLE_PAINTBALL.get(), PaintballEntityRenderer::new);

        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.RED_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.GREEN_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.BLUE_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.CYAN_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.MAGENTA_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.YELLOW_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.LIME_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.LIGHT_BLUE_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.PINK_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
        event.registerEntityRenderer((EntityType<HeavyPaintballEntity>) MCPaintballEntities.PURPLE_HEAVY_PAINTBALL.get(), HeavyPaintballRenderer::new);
    }

    public void buildCreativeTabContents(BuildCreativeModeTabContentsEvent event)
    {
        try
        {
            if(event.getTabKey().equals(CreativeModeTabs.COMBAT))
            {
                event.accept(MCPaintballItems.PISTOL.get());
                event.accept(MCPaintballItems.RIFLE.get());
                event.accept(MCPaintballItems.SHOTGUN.get());
                event.accept(MCPaintballItems.SNIPER.get());
                event.accept(MCPaintballItems.BAZOOKA.get());
            } else if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES))
            {
                event.accept(MCPaintballItems.REMOTE.get());
            }
        }
        catch (Exception exception)
        {
            LOG.error(exception);
        }
    }

    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber(modid = MCPaintball.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class NeoEvents
    {
        @SubscribeEvent
        private static void onServerStarted(ServerStartedEvent event)
        {
            ServerLevel overworld = event.getServer().overworld();
            SavedData.Factory<MCPaintballWorldData> F = new SavedData.Factory<>(MCPaintballWorldData::create, MCPaintballWorldData::load);
            MCPaintballWorldData.INSTANCE = overworld.getDataStorage().computeIfAbsent(F,MCPaintballWorldData.SAVE_NAME);
        }
    }
    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber(modid = MCPaintball.MOD_ID)
    public static class ModEvents
    {
        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event)
        {
            CommandDispatcher<CommandSourceStack> Dispatcher = event.getDispatcher();
            TeamCommands.registerCommands(Dispatcher);
            MatchCommands.registerCommands(Dispatcher);
        }
    }
}
