package me.oscardoras.chatblacklist;

import java.util.ArrayList;
import java.util.List;

import me.oscardoras.bungeeutils.BungeeCommand;
import me.oscardoras.bungeeutils.io.SendMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;

public class ChatBlackListCommand extends BungeeCommand {
	
	public ChatBlackListCommand() {
		super("chatblacklist", "chatblacklist.command.chatblacklist");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("list")) {
				List<String> list = BlackList.get();
				SendMessage.sendStringList(sender, list);
				SendMessage.send(sender, "Total words in the chat black list: " + list.size());
			} else if (args[0].equalsIgnoreCase("add")) {
				if (args.length >= 2) {
					BlackList.add(args[1]);
					SendMessage.send(sender, "The word has been added to the chat black list");
				} else SendMessage.send(sender, ChatColor.RED + "Not enough arguments, usage: /chatblacklist <add> <word>");
			} else if (args[0].equalsIgnoreCase("remove")) {
				if (args.length >= 2) {
					BlackList.remove(args[1]);
					SendMessage.send(sender, "The word has been removed from the chat black list");
				} else SendMessage.send(sender, ChatColor.RED + "Not enough arguments, usage: /chatblacklist <remove> <word>");
			} else SendMessage.send(sender, ChatColor.RED + "Invalid command, usage: /chatblacklist list OR /chatblacklist <add|remove> <word>");
		} else SendMessage.send(sender, ChatColor.RED + "Not enough arguments, usage: /chatblacklist list OR /chatblacklist <add|remove> <word>");
	}
	
	@Override
	public List<String> complete(CommandSender sender, String[] args) {
		List<String> list = new ArrayList<String>();
		if (args.length == 1) {
			list.add("list");
			list.add("add");
			list.add("remove");
		} else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
			list.addAll(BlackList.get());
		}
		return list;
	}
	
}