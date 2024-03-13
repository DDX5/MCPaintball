package org.multicoder.mcpaintball.common;

import net.minecraft.client.resources.sounds.SoundEventRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.multicoder.mcpaintball.MCPaintball;

public class MCPaintballSounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, MCPaintball.MOD_ID);

    public static final DeferredHolder<SoundEvent,SoundEvent> SHOT = SOUNDS.register("shot",() -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MCPaintball.MOD_ID,"shot"),15f));
    public static final DeferredHolder<SoundEvent,SoundEvent> HIT = SOUNDS.register("hit",() -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MCPaintball.MOD_ID,"hit"),15f));
    public static final DeferredHolder<SoundEvent,SoundEvent> SPLAT = SOUNDS.register("splat",() -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MCPaintball.MOD_ID,"splat"),15f));
    public static final DeferredHolder<SoundEvent,SoundEvent> GRENADE = SOUNDS.register("grenade",() -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MCPaintball.MOD_ID,"grenade"),15f));
    public static final DeferredHolder<SoundEvent,SoundEvent> C4_ADDED = SOUNDS.register("c4_added",() -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MCPaintball.MOD_ID,"c4_added"),15f));
    public static final DeferredHolder<SoundEvent,SoundEvent> C4_REMOVED = SOUNDS.register("c4_removed",() -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MCPaintball.MOD_ID,"c4_removed"),15f));
    public static final DeferredHolder<SoundEvent,SoundEvent> C4_EXPLODE = SOUNDS.register("c4_explode",() -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MCPaintball.MOD_ID,"c4_explode"),15f));
    public static final DeferredHolder<SoundEvent,SoundEvent> BAZOOKA = SOUNDS.register("bazooka",() -> SoundEvent.createFixedRangeEvent(new ResourceLocation(MCPaintball.MOD_ID,"bazooka"),15f));
}
