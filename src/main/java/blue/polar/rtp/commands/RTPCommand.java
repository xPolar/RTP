package blue.polar.rtp.commands;

import blue.polar.rtp.RTP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static blue.polar.rtp.utilities.GenerateLocation.generateLocation;

public class RTPCommand implements CommandExecutor {

    private final RTP plugin;

    public RTPCommand(RTP rtp) {
        this.plugin = rtp;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location generatedLocation = generateLocation(player.getPlayer().getWorld());
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 10));
            player.teleport(generatedLocation);
            System.out.printf("[RTP] %s has joined and been randomly teleported to %s, %s, %s!%n", player.getName(), generatedLocation.getBlockX(), generatedLocation.getBlockY(), generatedLocation.getBlockZ());
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&6&lWelcome... Home?"), "You've been teleported to a random place!");
        } else System.out.println("[RTP] This command can only be run by Players");

        return true;
    }
}
