package steamcraft.common.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeDepthsBase extends BiomeGenBase
{

	public BiomeDepthsBase(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.enableRain = false;
		this.setHeight(new Height(0.8F, 1.4F));
		this.setBiomeName("Inner Earth");
		this.rootHeight = 2;
	}
}
