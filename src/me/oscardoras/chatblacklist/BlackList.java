package me.oscardoras.chatblacklist;

import java.util.List;

import me.oscardoras.bungeeutils.io.DataFile;

public final class BlackList {
	private BlackList() {}
	
	private final static DataFile file = new DataFile("plugins/ChatBlackList/blacklist.yml");
	
	public static List<String> get() {
		return file.getAsYaml().getStringList("");
	}
	
	public static void add(String word) {
		word = word.toLowerCase();
		List<String> words = get();
		if (!words.contains(word)) {
			words.add(word);
			file.getAsYaml().set("", words);
			file.save();
		}
	}
	
	public static void remove(String word) {
		word = word.toLowerCase();
		List<String> words = get();
		if (words.contains(word)) {
			words.remove(word);
			file.getAsYaml().set("", words);
			file.save();
		}
	}
	
}