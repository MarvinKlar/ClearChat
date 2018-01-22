package mr.minecraft15.ClearChat.Commands;

import mr.minecraft15.ClearChat.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class ClearChat implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if(s instanceof Player) {
			if(a.length == 0) {
				if(s.hasPermission("clearchat.use")) {
					clearChat(s);
				} else {
					s.sendMessage(Main.getMessage("No_Permission"));
				}
			} else {
				if(a[0].equalsIgnoreCase("reload")) {
					if(s.hasPermission("clearchat.reload")) {
						Main.loadConfig();
						s.sendMessage(Main.prefix + Main.getMessage("Reloaded"));
					} else {
						s.sendMessage(Main.getMessage("No_Permission"));
					}
				} else {
					PluginDescriptionFile pdf = Main.getInstance().getDescription();
					s.sendMessage(Main.prefix + "Version " + pdf.getVersion() + "(" + Main.getFileSize(pdf.getName()) + "kB) by " + pdf.getAuthors().get(0) + ".");
					s.sendMessage("§7Use §b/ClearChat §7to clear the chat or §b/ClearChat reload §7to reload the config.");
				}
			}
		} else {
			clearChat(Main.getMessage("Server"));
		}
		return true;
	}

	private void clearChat(CommandSender s) {
		clearChat(s.getName());
	}

	private void clearChat(String name) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!p.hasPermission("clearchat.except")) {
				for(int i = 0; i < 100; i++) {
					p.sendMessage("");
				}
			}
		}
		Bukkit.broadcastMessage(Main.prefix + Main.getMessage("Cleared_Chat").replace("%player%", name));
	}
}
