package blue.polar.rtp.events;

import blue.polar.rtp.RTP;
import io.papermc.lib.PaperLib;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static blue.polar.rtp.utilities.GenerateLocation.generateLocation;
import static blue.polar.rtp.utilities.FormatString.formatString;

public class PlayerJoin implements Listener {

    private final RTP plugin;

    public PlayerJoin(RTP rtp) {
        this.plugin = rtp;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore() && this.plugin.getConfig().getBoolean("rtpOn.firstJoin")) {
            Location generatedLocation = generateLocation(this.plugin, event.getPlayer().getWorld());
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 10));
            PaperLib.teleportAsync(event.getPlayer(), generatedLocation, PlayerTeleportEvent.TeleportCause.PLUGIN).thenAccept(result -> {
                System.out.printf("[RTP] %s has joined and been randomly teleported to %s, %s, %s!%n", event.getPlayer().getName(), generatedLocation.getBlockX(), generatedLocation.getBlockY(), generatedLocation.getBlockZ());
                if (this.plugin.getConfig().getBoolean("message.title.enabled"))
                    event.getPlayer().sendTitle(ChatColor.translateAlternateColorCodes('&', formatString(generatedLocation, this.plugin.getConfig().getString("message.title.firstLine"))), ChatColor.translateAlternateColorCodes('&', formatString(generatedLocation, this.plugin.getConfig().getString("message.title.secondLine"))));
                if (this.plugin.getConfig().getBoolean("message.chat.enabled"))
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', formatString(generatedLocation, this.plugin.getConfig().getString("message.chat.message"))));
            });
        }
    }

}
