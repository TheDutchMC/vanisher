package nl.thedutchmc.vanisher;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandHandler implements CommandExecutor {

	private Vanisher plugin;
	private static List<UUID> currentlyHiding = new ArrayList<>();
	
	public CommandHandler(Vanisher plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final String prefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "V" + ChatColor.GRAY + "] " + ChatColor.GOLD;

		if(!sender.hasPermission("vanisher.use")) {
			sender.sendMessage("You do not have permission to use this command!");
			return true;
		}
		
		final Player p = (Player) sender;

		if(currentlyHiding.contains(p.getUniqueId())) {
			Bukkit.getOnlinePlayers().forEach(player -> {
				player.showPlayer(plugin, p);
			});
			
			currentlyHiding.remove(p.getUniqueId());
			sender.sendMessage(prefix + "You are no longer vanished!");
		} else {
			Bukkit.getOnlinePlayers().forEach(player -> {
				player.hidePlayer(plugin, p);
			});		
			
			currentlyHiding.add(p.getUniqueId());
			sender.sendMessage(prefix + "You are now vanished!");
		}

		return true;
	}

	
	
}
