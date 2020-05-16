package org.bungeeplugin.chatblacklist;

import org.bungeeutils.BungeePlugin;
import org.bungeeutils.io.SendMessage;
import org.bungeeutils.io.TranslatableMessage;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.event.EventHandler;

public final class ChatBlackListPlugin extends BungeePlugin {
	
	public static ChatBlackListPlugin plugin;
	
	public ChatBlackListPlugin() {
		plugin = this;
	}
	
	
	@Override
	public void onEnable() {
		PluginManager manager = ProxyServer.getInstance().getPluginManager();
		manager.registerListener(this, this);
		manager.registerCommand(this, new ChatBlackListCommand());
	}
	
	@EventHandler
	public void onChat(ChatEvent e) {
	    try {
			ProxiedPlayer player = (ProxiedPlayer) e.getSender();
			String msg = e.getMessage().toLowerCase();
			if (!msg.startsWith("/") && !player.hasPermission("chatblacklist.ignore")) {
				for (String word : BlackList.get()) {
					word = word.toLowerCase();
					if (msg.equals(word) || msg.contains(" " + word + " ") || msg.startsWith(word + " ") || msg.endsWith(" " + word)) {
						SendMessage.send(player, new TranslatableMessage(this, "invalid_word").getMessage(player));
						e.setCancelled(true);
						return;
					}
				}
			}
		} catch (ClassCastException ex) {}
	}
	
}