package blue.polar.rtp;

import blue.polar.rtp.commands.RTPCommand;
import blue.polar.rtp.events.PlayerJoin;
import blue.polar.rtp.events.PlayerRespawn;
import io.papermc.lib.PaperLib;
import org.bukkit.plugin.java.JavaPlugin;

public final class RTP extends JavaPlugin {

    @Override
    public void onEnable() {
        PaperLib.suggestPaper(this);

        System.out.println("[RTP] RTP has been enabled!");

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getCommand("rtp").setExecutor(new RTPCommand(this));
    }

    @Override
    public void onDisable() {
        System.out.println("[RTP] RTP has been disabled!");
    }

}
