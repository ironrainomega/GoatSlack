package com.tamashenning.goatslack;

import com.tamashenning.goatslack.commands.PingAdmin;
import com.tamashenning.goatslack.proxies.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
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
