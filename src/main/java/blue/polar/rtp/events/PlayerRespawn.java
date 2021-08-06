package blue.polar.rtp.events;

import blue.polar.rtp.RTP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import static blue.polar.rtp.utilities.GenerateLocation.generateLocation;

public class PlayerRespawn implements Listener {

    private final RTP plugin;

    public PlayerRespawn(RTP rtp) {
        this.plugin = rtp;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (!event.isBedSpawn()) {
            Location generatedLocation = generateLocation(event.getPlayer().getWorld());
            event.setRespawnLocation(generatedLocation);
            System.out.printf("[RTP] %s has respawned and been randomly teleported to %s, %s, %s!%n", event.getPlayer().getName(), generatedLocation.getBlockX(), generatedLocation.getBlockY(), generatedLocation.getBlockZ());
            event.getPlayer().sendTitle(ChatColor.translateAlternateColorCodes('&', "&6&lWelcome... Home?"), "You've been teleported to a random place!");
        }
    }

}