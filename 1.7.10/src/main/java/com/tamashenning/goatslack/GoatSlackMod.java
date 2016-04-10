package com.tamashenning.goatslack;

import org.apache.logging.log4j.Logger;

import com.tamashenning.goatslack.commands.PingAdmin;
import com.tamashenning.goatslack.proxies.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = GoatSlackMod.MODID, name = GoatSlackMod.MODNAME, version = GoatSlackMod.VERSION)
public class GoatSlackMod {

	public static final String MODID = "goatslack";
	public static final String MODNAME = "Goat Slack";
	public static final String VERSION = "1.0";

	public static String SlackURL = "";
	public static String SlackBotName = "GoatSlackBot";
	public static String SlackBotEmoji = ":ghost:";
	public static String SlackChannel = "";
	
    @Instance(GoatSlackMod.MODID)
    public static GoatSlackMod instance;
	
	@SidedProxy(clientSide = "com.tamashenning.goatslack.proxies.ClientProxy", serverSide = "com.tamashenning.goatslack.proxies.ServerProxy")
	public static CommonProxy proxy;

	public static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		logger = e.getModLog();
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
	    // register server commands
		event.registerServerCommand(new PingAdmin());
	}
}
