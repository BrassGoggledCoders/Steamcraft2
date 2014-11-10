package steamcraft.common;

import net.minecraft.world.biome.BiomeGenBase;
import steamcraft.common.biomes.BiomeDepthsBase;
import steamcraft.common.biomes.BiomeDepthsForest;
import steamcraft.common.biomes.BiomeDepthsInfestation;
import steamcraft.common.biomes.BiomeDepthsMountains;
import steamcraft.common.biomes.BiomeDepthsMushrooms;
import steamcraft.common.config.ConfigGeneral;

public class InitBiomes
{
	public static BiomeGenBase biomeDepths, biomeDepthsF, biomeDepthsM, biomeDepthsS, biomeDepthsI;

	public static void init()
	{
		biomeDepths = new BiomeDepthsBase(ConfigGeneral.depthsBiomeID);
		biomeDepthsF = new BiomeDepthsForest(ConfigGeneral.depthsFBiomeID);
		biomeDepthsM = new BiomeDepthsMountains(ConfigGeneral.depthsMBiomeID);
		biomeDepthsS = new BiomeDepthsMushrooms(ConfigGeneral.depthsSBiomeID);
		biomeDepthsI = new BiomeDepthsInfestation(ConfigGeneral.depthsIBiomeID);
	}
}
