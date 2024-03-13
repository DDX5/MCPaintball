package org.multicoder.mcpaintball.common.utility;

import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.EntityType;
import org.multicoder.mcpaintball.common.entity.MCPaintballEntities;

public enum PaintballTeam
{
    RED,
    GREEN,
    BLUE,
    CYAN,
    MAGENTA,
    YELLOW,
    LIME,
    LIGHT_BLUE,
    PINK,
    PURPLE;

    public ChatFormatting getDisplayColor()
    {
        switch(this)
        {
            case RED ->{return ChatFormatting.DARK_RED;}
            case GREEN ->{return ChatFormatting.DARK_GREEN;}
            case BLUE ->{return ChatFormatting.DARK_BLUE;}
            case CYAN ->{return ChatFormatting.DARK_AQUA;}
            case MAGENTA ->{return ChatFormatting.DARK_PURPLE;}
            case YELLOW ->{return ChatFormatting.YELLOW;}
            case LIME ->{return ChatFormatting.GREEN;}
            case LIGHT_BLUE ->{return ChatFormatting.AQUA;}
            case PINK ->{return ChatFormatting.LIGHT_PURPLE;}
            case PURPLE ->{return ChatFormatting.BLUE;}
        }
        return ChatFormatting.BLACK;
    }
    public EntityType<?> getPaintball(){
        switch (this){
            case RED -> {return MCPaintballEntities.RED_PAINTBALL.get();}
            case GREEN -> {return MCPaintballEntities.GREEN_PAINTBALL.get();}
            case BLUE -> {return MCPaintballEntities.BLUE_PAINTBALL.get();}
            case CYAN -> {return MCPaintballEntities.CYAN_PAINTBALL.get();}
            case MAGENTA -> {return MCPaintballEntities.MAGENTA_PAINTBALL.get();}
            case YELLOW -> {return MCPaintballEntities.YELLOW_PAINTBALL.get();}
            case LIME -> {return MCPaintballEntities.LIME_PAINTBALL.get();}
            case LIGHT_BLUE -> {return MCPaintballEntities.LIGHT_BLUE_PAINTBALL.get();}
            case PINK -> {return MCPaintballEntities.PINK_PAINTBALL.get();}
            case PURPLE -> {return MCPaintballEntities.PURPLE_PAINTBALL.get();}
        }
        return null;
    }
    public EntityType<?> getHeavyPaintball(){
        switch (this){
            case RED -> {return MCPaintballEntities.RED_HEAVY_PAINTBALL.get();}
            case GREEN -> {return MCPaintballEntities.GREEN_HEAVY_PAINTBALL.get();}
            case BLUE -> {return MCPaintballEntities.BLUE_HEAVY_PAINTBALL.get();}
            case CYAN -> {return MCPaintballEntities.CYAN_HEAVY_PAINTBALL.get();}
            case MAGENTA -> {return MCPaintballEntities.MAGENTA_HEAVY_PAINTBALL.get();}
            case YELLOW -> {return MCPaintballEntities.YELLOW_HEAVY_PAINTBALL.get();}
            case LIME -> {return MCPaintballEntities.LIME_HEAVY_PAINTBALL.get();}
            case LIGHT_BLUE -> {return MCPaintballEntities.LIGHT_BLUE_HEAVY_PAINTBALL.get();}
            case PINK -> {return MCPaintballEntities.PINK_HEAVY_PAINTBALL.get();}
            case PURPLE -> {return MCPaintballEntities.PURPLE_HEAVY_PAINTBALL.get();}
        }
        return null;
    }
    public String getPaintballTranslation(){
        switch (this){
            case RED -> {return "mcpaintball:red_paintball";}
            case GREEN -> {return "mcpaintball:green_paintball";}
            case BLUE -> {return "mcpaintball:blue_paintball";}
            case CYAN -> {return "mcpaintball:cyan_paintball";}
            case MAGENTA -> {return "mcpaintball:magenta_paintball";}
            case YELLOW -> {return "mcpaintball:yellow_paintball";}
            case LIME -> {return "mcpaintball:lime_paintball";}
            case LIGHT_BLUE -> {return "mcpaintball:light_blue_paintball";}
            case PINK -> {return "mcpaintball:pink_paintball";}
            case PURPLE -> {return "mcpaintball:purple_paintball";}
        }
        return null;
    }
}
