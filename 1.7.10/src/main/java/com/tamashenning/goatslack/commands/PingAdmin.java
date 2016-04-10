package com.tamashenning.goatslack.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonObject;
import com.tamashenning.goatslack.GoatSlackMod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class PingAdmin extends CommandBase {

	@Override
	public String getCommandName() {
		return "pingadmin";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "pingadmin <message>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] astring) {
		if (astring.length == 0) {
			this.sendMessageTo(sender, "Invalid arguments: Usage: " + this.getCommandUsage(sender));
			return;
		}
		if (astring[0] == "help") {
			this.sendMessageTo(sender, "Usage: " + this.getCommandUsage(sender));
			return;
		}

		if (GoatSlackMod.SlackURL == "") {
			this.sendMessageTo(sender,
					"Slack hook URL is empty in the config file. Please ask an admin to set this up.");
			return;
		}

		if (GoatSlackMod.SlackBotName == "") {
			this.sendMessageTo(sender,
					"Slack Bot name is empty in the config file. Please ask an admin to set this up.");
			return;
		}

		if (GoatSlackMod.SlackChannel == "") {
			this.sendMessageTo(sender,
					"Slack channel is empty in the config file. Please ask an admin to set this up.");
			return;
		}

		EntityPlayer player = null;
		if (sender instanceof EntityPlayer) {
			player = (EntityPlayer) sender;
		}

		String message = String.join(" ", astring);
		if (player != null) {
			message = player.getDisplayName() + ": " + message;
		}

		JsonObject json = new JsonObject();
		json.addProperty("channel", GoatSlackMod.SlackChannel);
		json.addProperty("username", GoatSlackMod.SlackBotName);
		json.addProperty("icon_emoji", GoatSlackMod.SlackBotEmoji);
		json.addProperty("text", message);

		HttpClient httpClient = HttpClientBuilder.create().build();

		try {
			HttpPost request = new HttpPost(GoatSlackMod.SlackURL);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("payload", json.toString()));

			request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpClient.execute(request);
			response.getAllHeaders();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		sender.addChatMessage(new ChatComponentText("Message sent..."));
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	private void sendMessageTo(ICommandSender sender, String text) {
		sender.addChatMessage(new ChatComponentText(text));
	}
}
