package steamcraft.common.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenWitchHut extends MapGenScatteredFeature
{

	public static class Start extends StructureStart
	{

		public Start()
		{
		}

		public Start(World p_i2060_1_, Random p_i2060_2_, int p_i2060_3_, int p_i2060_4_)
		{
			super(p_i2060_3_, p_i2060_4_);
			BiomeGenBase biomegenbase = p_i2060_1_.getBiomeGenForCoords(p_i2060_3_ * 16 + 8, p_i2060_4_ * 16 + 8);

			ComponentScatteredFeaturePieces.SwampHut swamphut = new ComponentScatteredFeaturePieces.SwampHut(p_i2060_2_, p_i2060_3_ * 16,
					p_i2060_4_ * 16);
			this.components.add(swamphut);

			this.updateBoundingBox();
		}
	}
}