package com.tamashenning.goatslack.proxies;

import com.tamashenning.goatslack.GoatSlackMod;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());

		config.load();

		GoatSlackMod.SlackBotName = config
				.get(Configuration.CATEGORY_GENERAL, "SlackBotName", GoatSlackMod.SlackBotName).getString();
		GoatSlackMod.SlackURL = config
				.get(Configuration.CATEGORY_GENERAL, "SlackIncomingHookUrl", GoatSlackMod.SlackURL).getString();
		GoatSlackMod.SlackBotEmoji = config
				.get(Configuration.CATEGORY_GENERAL, "SlackBotEmoji", GoatSlackMod.SlackBotEmoji).getString();
		GoatSlackMod.SlackChannel = config
				.get(Configuration.CATEGORY_GENERAL, "SlackChannel", GoatSlackMod.SlackChannel).getString();

		config.save();
	}

	public void init(FMLInitializationEvent e) {

	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}