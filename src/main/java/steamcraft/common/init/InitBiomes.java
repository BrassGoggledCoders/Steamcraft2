
package steamcraft.common.init;

import net.minecraft.world.biome.BiomeGenBase;

import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.worldgen.biomes.BiomeDepthsBase;
import steamcraft.common.worldgen.biomes.BiomeDepthsForest;
import steamcraft.common.worldgen.biomes.BiomeDepthsInfestation;
import steamcraft.common.worldgen.biomes.BiomeDepthsMountains;
import steamcraft.common.worldgen.biomes.BiomeDepthsMushrooms;
import steamcraft.common.worldgen.biomes.BiomeDepthsScalded;
import steamcraft.common.worldgen.biomes.BiomeDepthsScorched;
import steamcraft.common.worldgen.biomes.BiomeDepthsSwamp;
import steamcraft.common.worldgen.biomes.BiomeDepthsTallForest;

public class InitBiomes
{
	public static BiomeGenBase biomeDepths, biomeDepthsF, biomeDepthsM, biomeDepthsS, biomeDepthsI,
			/* biomeDepthsO, biomeDepthsB */biomeDepthsSC, biomeDepthsSCH, biomeDepthsSW, biomeDepthsTF, biomeDepthsJ;

	public static void init()
	{
		biomeDepths = new BiomeDepthsBase(ConfigGeneral.depthsBiomeID);
		biomeDepthsF = new BiomeDepthsForest(ConfigGeneral.depthsFBiomeID);
		biomeDepthsM = new BiomeDepthsMountains(ConfigGeneral.depthsMBiomeID);
		biomeDepthsS = new BiomeDepthsMushrooms(ConfigGeneral.depthsSBiomeID);
		biomeDepthsI = new BiomeDepthsInfestation(ConfigGeneral.depthsIBiomeID);
		biomeDepthsSC = new BiomeDepthsScalded(ConfigGeneral.depthsSCBiomeID);
		biomeDepthsSCH = new BiomeDepthsScorched(ConfigGeneral.depthsSCHBiomeID);
		biomeDepthsSW = new BiomeDepthsSwamp(ConfigGeneral.depthsSWBiomeID);
		biomeDepthsTF = new BiomeDepthsTallForest(ConfigGeneral.depthsTFBiomeID);
		biomeDepthsJ = new BiomeDepthsTallForest(ConfigGeneral.depthsJBiomeID);

		// biomeDepthsO = new BiomeDepthsOcean(ConfigGeneral.depthsOBiomeID);
		// biomeDepthsB = new BiomeDepthsBeach(ConfigGeneral.depthsBBiomeID);
	}
}
