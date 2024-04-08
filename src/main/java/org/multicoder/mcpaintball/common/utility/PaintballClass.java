package org.multicoder.mcpaintball.common.utility;

public enum PaintballClass
{
    STANDARD,
    HEAVY,
    MEDICAL,
    ENGINEER,
    SNIPER,
    GRENADIER;

    public String GetTKey(){
        return "type." + this.name().toLowerCase();
    }
}
