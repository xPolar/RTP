package blue.polar.rtp.utilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenerateLocation {

    public static Location generateLocation(World world) {
        List<Material> unsafeMaterials = Arrays.asList(Material.ACACIA_LEAVES, Material.AZALEA_LEAVES, Material.BIRCH_LEAVES, Material.CACTUS, Material.DARK_OAK_LEAVES, Material.FLOWERING_AZALEA_LEAVES, Material.FIRE, Material.JUNGLE_LEAVES, Material.LAVA, Material.LAVA_CAULDRON, Material.MAGMA_BLOCK, Material.OAK_LEAVES, Material.SPRUCE_LEAVES, Material.WATER);
        Random random = new Random(); while (true) {
            int x = random.nextInt(20_000) - 10_000; int z = random.nextInt(20_000) - 10_000;
            Block generatedBlock = world.getHighestBlockAt(x, z);
            Block higherBlock = world.getBlockAt(x, generatedBlock.getY() + 2, z);
            if (unsafeMaterials.contains(generatedBlock.getBlockData().getMaterial()) || unsafeMaterials.contains(higherBlock.getBlockData().getMaterial()))
                continue; return new Location(world, x, generatedBlock.getY() + 4, z);
        }
    }

}
