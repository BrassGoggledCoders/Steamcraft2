package steamcraft.common;

import net.minecraft.world.biome.BiomeGenBase;
import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.worldgen.biomes.BiomeDepthsBase;
import steamcraft.common.worldgen.biomes.BiomeDepthsBeach;
import steamcraft.common.worldgen.biomes.BiomeDepthsForest;
import steamcraft.common.worldgen.biomes.BiomeDepthsInfestation;
import steamcraft.common.worldgen.biomes.BiomeDepthsMountains;
import steamcraft.common.worldgen.biomes.BiomeDepthsMushrooms;
import steamcraft.common.worldgen.biomes.BiomeDepthsOcean;

public class InitBiomes
{
	public static BiomeGenBase biomeDepths, biomeDepthsF, biomeDepthsM, biomeDepthsS, biomeDepthsI, biomeDepthsO, biomeDepthsB;

	public static void init()
	{
		biomeDepths = new BiomeDepthsBase(ConfigGeneral.depthsBiomeID);
		biomeDepthsF = new BiomeDepthsForest(ConfigGeneral.depthsFBiomeID);
		biomeDepthsM = new BiomeDepthsMountains(ConfigGeneral.depthsMBiomeID);
		biomeDepthsS = new BiomeDepthsMushrooms(ConfigGeneral.depthsSBiomeID);
		biomeDepthsI = new BiomeDepthsInfestation(ConfigGeneral.depthsIBiomeID);
		biomeDepthsO = new BiomeDepthsOcean(ConfigGeneral.depthsOBiomeID);
		biomeDepthsB = new BiomeDepthsBeach(ConfigGeneral.depthsBBiomeID);
	}
}
