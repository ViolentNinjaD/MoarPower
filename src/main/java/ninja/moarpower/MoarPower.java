package ninja.moarpower;

/**
 * Created by David on 21/11/2014.
 */
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import ninja.moarpower.handler.GuiHandler;
import ninja.moarpower.init.MPBlocks;
import ninja.moarpower.init.MPRecipes;
import ninja.moarpower.proxy.IProxy;

@Mod(modid = MoarPower.modid, name = MoarPower.modname, version = MoarPower.version,dependencies = MoarPower.dependencies)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class MoarPower
{
    @SidedProxy(clientSide = "ninja.moarpower.proxy.ClientProxy", serverSide = "ninja.moarpower.proxy.ServerProxy")
    public static IProxy proxy;

    public static CreativeTabs tabMP = new CreativeTabMP(CreativeTabs.getNextID());

    public static final String modid = "MoarPower";
    public static final String modname = "Moar Power";
    public static final String dependencies = "after:IC2";
    public static final String version = "1.6.4";

    @Mod.Instance(MoarPower.modid)
    public static MoarPower instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MPBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());

        MPRecipes.init();

        proxy.registerTileEntities();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
