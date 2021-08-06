package blue.polar.rtp.events;

import blue.polar.rtp.RTP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static blue.polar.rtp.utilities.GenerateLocation.generateLocation;

public class PlayerJoin implements Listener {

    private final RTP plugin;

    public PlayerJoin(RTP rtp) {
        this.plugin = rtp;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            Location generatedLocation = generateLocation(event.getPlayer().getWorld());
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 10));
            event.getPlayer().teleport(generatedLocation);
            System.out.printf("[RTP] %s has joined and been randomly teleported to %s, %s, %s!%n", event.getPlayer().getName(), generatedLocation.getBlockX(), generatedLocation.getBlockY(), generatedLocation.getBlockZ());
            event.getPlayer().sendTitle(ChatColor.translateAlternateColorCodes('&', "&6&lWelcome... Home?"), "You've been teleported to a random place!");
        }
    }

}
