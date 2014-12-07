package ninja.moarpower.helper;

import net.minecraft.util.ResourceLocation;
import ninja.moarpower.MoarPower;

/** @author ViolentNinjaD
    LGPLv3
**/
public class ResourceLocationHelper 
{
    public static ResourceLocation getResourceLocation(String modId, String path)
    {
        return new ResourceLocation(modId, path);
    }
    public static ResourceLocation getResourceLocation(String path)
    {
        return getResourceLocation(MoarPower.modid.toLowerCase(), path);
    }
}
