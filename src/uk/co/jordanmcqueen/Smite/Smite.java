package uk.co.jordanmcqueen.Smite;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Smite extends JavaPlugin {
	public static Smite plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is now disabled.");
	}
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is now enabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		World world = player.getWorld();
		if(commandLabel.equalsIgnoreCase("smite") && player.isOp()) {
			if(args.length == 0) {
				Block targetblock = player.getTargetBlock(null, 50);
				Location location = targetblock.getLocation();
				world.strikeLightning(location);
				world.createExplosion(location, 10);
				player.sendMessage(ChatColor.GRAY + "By the power of Thor!");
			} else if (args.length == 1) {
				if(player.getServer().getPlayer(args[0]) != null) {
					Player targetplayer = player.getServer().getPlayer(args[0]);
					Location location = targetplayer.getLocation();
					world.strikeLightning(location);
					world.createExplosion(location, 10);
					player.sendMessage(ChatColor.GRAY + "Smiting player " + targetplayer.getDisplayName());
				} else {
					player.sendMessage(ChatColor.RED + "Error: The player is offline.");
				}
			} else if (args.length > 1) {
				player.sendMessage(ChatColor.RED + "Error: Too many arguments!");
			}
		}
		return false;
	}
}
