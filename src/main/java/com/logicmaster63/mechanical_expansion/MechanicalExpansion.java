package com.logicmaster63.mechanical_expansion;

import com.logicmaster63.mechanical_expansion.init.*;
import com.logicmaster63.mechanical_expansion.proxy.CommonProxy;
import jline.internal.Log;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.MODNAME)
public class MechanicalExpansion {

    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
    public final String[] MACHINE_LIST = new String[] {"combustion_generator" , "electric_furnace"};

    @Mod.Instance("me")
    public static MechanicalExpansion instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.CLIENT_SERVER_CLASS)
    public static CommonProxy proxy;
    public static final CreativeTab MECHANICAL_TAB = new CreativeTab("mechanical_tab");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Blocks.init();
        Items.init();
        Recipes.init();
        TileEntities.init();
        Entities.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerFuelHandler(new FuelHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        for (String modid : Reference.IMPLEMENTABLE_MODIDS) {
            if (Loader.isModLoaded(modid))
                Log.info(Log.Level.INFO, modid + " implementation in use");
        }
        //MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText("Sent to all players"));
    }
}
