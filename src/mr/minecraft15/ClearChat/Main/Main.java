package mr.minecraft15.ClearChat.Main;

import java.io.File;

import mr.minecraft15.ClearChat.Commands.ClearChat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Plugin instance;
	public static String prefix;
	public static String lang;
	
	public void onEnable() {
		instance = this;
		Configuration cfg = getConfig();
		cfg.options().copyDefaults(true);
		cfg.addDefault("Language", "en");
		cfg.addDefault("Lang.en.No_Permission", "You are not allowed to do this.");
		cfg.addDefault("Lang.de.No_Permission", "Du hast dafür keine Erlaubnis.");
		cfg.addDefault("Lang.en.Cleared_Chat", "%player% cleared the chat.");
		cfg.addDefault("Lang.de.Cleared_Chat", "%player% hat den Chat geleert.");
		cfg.addDefault("Lang.en.Reloaded", "Reloaded configuration.");
		cfg.addDefault("Lang.de.Reloaded", "Konfiguration wurde neu geladen.");
		cfg.addDefault("Lang.en.Server", "The server");
		cfg.addDefault("Lang.de.Server", "Der Server");
		cfg.addDefault("Lang.en.Prefix", "&7[&bClearChat&7] ");
		cfg.addDefault("Lang.de.Prefix", "&7[&bClearChat&7] ");
		saveConfig();
		loadConfig();
		getCommand("ClearChat").setExecutor(new ClearChat());
		System.out.println("Enabled " + getName() + " v" + getDescription().getVersion() + " (" + getFileSize(getName()) + "kB) by " + getDescription().getAuthors().get(0)); 
	}
	
	public void onDisable() {
		System.out.println("Disabled " + getName() + " v" + getDescription().getVersion() + " (" + getFileSize(getName()) + "kB) by " + getDescription().getAuthors().get(0)); 
	}
	
	public static Plugin getInstance() {
		return instance;
	}
	
	public static long getFileSize(String plugin) {
		return new File("plugins", plugin + ".jar").length() / 1024;
	}
	
	public static String getMessage(String message) {
		return ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Lang." + Main.lang + "." + message));
	}

	public static void loadConfig() {
		Main.getInstance().reloadConfig();
		Configuration cfg = Main.getInstance().getConfig();
		lang = cfg.getString("Language");
		prefix = getMessage("Prefix");
	}
}
