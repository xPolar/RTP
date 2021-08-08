package blue.polar.rtp.utilities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Random;

public class GenerateLocation {

    public static Location generateLocation(Plugin plugin, World world) {
        List<String> unsafeMaterials = plugin.getConfig().getStringList("unsafeBlocks");
        boolean negative = plugin.getConfig().getBoolean("rtpLimit.negative");
        boolean worldBorder = plugin.getConfig().getBoolean("rtpLimit.worldBorder");
        int xLimit = worldBorder ? (int) world.getWorldBorder().getSize() / 2 : plugin.getConfig().getInt("rtpLimit.xLimit");
        int zLimit = worldBorder ? (int) world.getWorldBorder().getSize() / 2 : plugin.getConfig().getInt("rtpLimit.zLimit");
        Random random = new Random(); while (true) {
            int x = negative ? random.nextInt(xLimit * 2) - xLimit : random.nextInt(xLimit);
            int z = negative ? random.nextInt(zLimit * 2) - zLimit : random.nextInt(zLimit);
            Block generatedBlock = world.getHighestBlockAt(x, z);
            Block higherBlock = world.getBlockAt(x, generatedBlock.getY() + 2, z);
            if (unsafeMaterials.contains(generatedBlock.getBlockData().getMaterial().name()) || unsafeMaterials.contains(higherBlock.getBlockData().getMaterial().name()))
                continue; return new Location(world, x + .5, generatedBlock.getY() + 1, z + .5);
        }
    }

}
