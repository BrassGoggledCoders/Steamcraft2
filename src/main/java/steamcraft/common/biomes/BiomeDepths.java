package steamcraft.common.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeDepths extends BiomeGenBase
{

	public BiomeDepths(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.topBlock = Blocks.stone;
		this.fillerBlock = Blocks.obsidian;
		this.setBiomeName("Depths");
	}

}
