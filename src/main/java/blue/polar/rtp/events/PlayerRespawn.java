package blue.polar.rtp.events;

import blue.polar.rtp.RTP;
import blue.polar.rtp.utilities.FormatString;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import static blue.polar.rtp.utilities.GenerateLocation.generateLocation;
import static blue.polar.rtp.utilities.FormatString.formatString;

public class PlayerRespawn implements Listener {

    private final RTP plugin;

    public PlayerRespawn(RTP rtp) {
        this.plugin = rtp;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (!event.isBedSpawn() && this.plugin.getConfig().getBoolean("rtpOn.respawn")) {
            Location generatedLocation = generateLocation(this.plugin, event.getPlayer().getWorld());
            event.setRespawnLocation(generatedLocation);
            System.out.printf("[RTP] %s has respawned and been randomly teleported to %s, %s, %s!%n", event.getPlayer().getName(), generatedLocation.getBlockX(), generatedLocation.getBlockY(), generatedLocation.getBlockZ());
            if (this.plugin.getConfig().getBoolean("message.title.enabled"))
                event.getPlayer().sendTitle(ChatColor.translateAlternateColorCodes('&', formatString(generatedLocation, this.plugin.getConfig().getString("message.title.firstLine"))), ChatColor.translateAlternateColorCodes('&', formatString(generatedLocation, this.plugin.getConfig().getString("message.title.secondLine"))));
            if (this.plugin.getConfig().getBoolean("message.chat.enabled"))
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', formatString(generatedLocation, this.plugin.getConfig().getString("message.chat.message"))));
        }
    }

}