package ninja.moarpower;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import ninja.moarpower.init.MPBlocks;

/** @author ViolentNinjaD
    LGPLv3
**/
public class CreativeTabMP extends CreativeTabs
{
    public CreativeTabMP(int tabID)
    {
        super(tabID, MoarPower.modid);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return MPBlocks.STONE_FANCIFIER_ID;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return "MOAR Power";
    }
}
