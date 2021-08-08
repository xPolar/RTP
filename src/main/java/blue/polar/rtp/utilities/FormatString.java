package blue.polar.rtp.utilities;

import org.bukkit.Location;

public class FormatString {

    public static String formatString(Location location, String string) {
        return string.replace("{X}", String.valueOf(location.getBlockX())).replace("{Y}", String.valueOf(location.getBlockY())).replace("{Z}", String.valueOf(location.getBlockZ()));
    }

}
