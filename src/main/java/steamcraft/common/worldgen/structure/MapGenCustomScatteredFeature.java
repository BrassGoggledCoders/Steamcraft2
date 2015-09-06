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
package steamcraft.common.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureStart;

import steamcraft.common.init.InitBiomes;

public class MapGenCustomScatteredFeature extends MapGenScatteredFeature
{

	public static class Start extends StructureStart
	{

		public Start()
		{
		}

		public Start(World p_i2060_1_, Random p_i2060_2_, int p_i2060_3_, int p_i2060_4_)
		{
			super(p_i2060_3_, p_i2060_4_);
			BiomeGenBase biomegenbase = p_i2060_1_.getBiomeGenForCoords((p_i2060_3_ * 16) + 8, (p_i2060_4_ * 16) + 8);

			if (biomegenbase == InitBiomes.biomeDepthsSW)
			{
				ComponentScatteredFeaturePieces.SwampHut swamphut = new ComponentScatteredFeaturePieces.SwampHut(p_i2060_2_, p_i2060_3_ * 16,
						p_i2060_4_ * 16);
				this.components.add(swamphut);
			}

			this.updateBoundingBox();
		}
	}
}