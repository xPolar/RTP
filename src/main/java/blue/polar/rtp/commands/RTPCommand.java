package blue.polar.rtp.commands;

import blue.polar.rtp.RTP;
import io.papermc.lib.PaperLib;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static blue.polar.rtp.utilities.FormatString.formatString;
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
            Location generatedLocation = generateLocation(this.plugin, player.getPlayer().getWorld());
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 10));
            PaperLib.teleportAsync(player, generatedLocation, PlayerTeleportEvent.TeleportCause.COMMAND).thenAccept(result -> {
                System.out.printf("[RTP] %s has joined and been randomly teleported to %s, %s, %s!%n", player.getName(), generatedLocation.getBlockX(), generatedLocation.getBlockY(), generatedLocation.getBlockZ());
                if (this.plugin.getConfig().getBoolean("message.title.enabled"))
                    player.sendTitle(ChatColor.translateAlternateColorCodes('&', formatString(generatedLocation, this.plugin.getConfig().getString("message.title.firstLine"))), ChatColor.translateAlternateColorCodes('&', formatString(generatedLocation, this.plugin.getConfig().getString("message.title.secondLine"))));
                if (this.plugin.getConfig().getBoolean("message.chat.enabled"))
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', formatString(generatedLocation, this.plugin.getConfig().getString("message.chat.message"))));
            });
        } else System.out.println("[RTP] This command can only be run by Players");

        return true;
    }
}
