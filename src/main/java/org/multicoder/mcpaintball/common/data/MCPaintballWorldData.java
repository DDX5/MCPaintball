package org.multicoder.mcpaintball.common.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import org.lwjgl.openal.SOFTEffectTarget;

public class MCPaintballWorldData extends SavedData
{
    public static final String SAVE_NAME = "mcpaintball";

    public static MCPaintballWorldData INSTANCE;
    public int RED_POINTS;
    public int GREEN_POINTS;
    public int BLUE_POINTS;
    public int CYAN_POINTS;
    public int MAGENTA_POINTS;
    public int YELLOW_POINTS;
    public int LIME_POINTS;
    public int LIGHT_BLUE_POINTS;
    public int PINK_POINTS;
    public int PURPLE_POINTS;

    public static void IncrementByTranslationKey(String Key)
    {
        if(Key.contains("red")){
            INSTANCE.RED_POINTS++;
        } else if (Key.contains("green")) {
            INSTANCE.GREEN_POINTS++;
        } else if (Key.contains("blue")) {
            INSTANCE.BLUE_POINTS++;
        } else if (Key.contains("cyan")) {
            INSTANCE.CYAN_POINTS++;
        } else if (Key.contains("magenta")) {
            INSTANCE.MAGENTA_POINTS++;
        } else if (Key.contains("yellow")) {
            INSTANCE.YELLOW_POINTS++;
        } else if (Key.contains("lime")) {
            INSTANCE.LIME_POINTS++;
        } else if (Key.contains("light_blue")) {
            INSTANCE.LIGHT_BLUE_POINTS++;
        } else if (Key.contains("pink")) {
            INSTANCE.PINK_POINTS++;
        } else if (Key.contains("purple")) {
            INSTANCE.PURPLE_POINTS++;
        }
    }
    public static MCPaintballWorldData create()
    {
        MCPaintballWorldData data =  new MCPaintballWorldData();
        data.PURPLE_POINTS = 0;
        data.PINK_POINTS = 0;
        data.LIGHT_BLUE_POINTS = 0;
        data.LIME_POINTS = 0;
        data.MAGENTA_POINTS = 0;
        data.CYAN_POINTS = 0;
        data.YELLOW_POINTS = 0;
        data.BLUE_POINTS = 0;
        data.GREEN_POINTS = 0;
        data.RED_POINTS = 0;
        return data;
    }
    public static MCPaintballWorldData load(CompoundTag nbt)
    {
        MCPaintballWorldData data = new MCPaintballWorldData();
        data.RED_POINTS = nbt.getInt("red");
        data.BLUE_POINTS = nbt.getInt("blue");
        data.GREEN_POINTS = nbt.getInt("green");
        data.CYAN_POINTS= nbt.getInt("cyan");
        data.MAGENTA_POINTS = nbt.getInt("magenta");
        data.YELLOW_POINTS = nbt.getInt("yellow");
        data.LIME_POINTS = nbt.getInt("lime");
        data.LIGHT_BLUE_POINTS = nbt.getInt("light_blue");
        data.PINK_POINTS = nbt.getInt("pink");
        data.PURPLE_POINTS = nbt.getInt("purple");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag)
    {
        tag.putInt("red",RED_POINTS);
        tag.putInt("green",GREEN_POINTS);
        tag.putInt("blue",BLUE_POINTS);
        tag.putInt("cyan",CYAN_POINTS);
        tag.putInt("magenta",MAGENTA_POINTS);
        tag.putInt("yellow",YELLOW_POINTS);
        tag.putInt("lime",LIME_POINTS);
        tag.putInt("light_blue",LIGHT_BLUE_POINTS);
        tag.putInt("pink",PINK_POINTS);
        tag.putInt("purple",PURPLE_POINTS);
        return tag;
    }
}
