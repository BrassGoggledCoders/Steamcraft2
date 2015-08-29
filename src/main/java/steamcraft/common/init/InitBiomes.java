/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
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
