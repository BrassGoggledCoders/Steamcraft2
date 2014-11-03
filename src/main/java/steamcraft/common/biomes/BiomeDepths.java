package steamcraft.common.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeDepths extends BiomeGenBase
{

	public BiomeDepths(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.enableRain = false;
		this.setHeight(new Height(1.5f, 1.8F));
		this.setBiomeName("Depths");
		this.rootHeight = 2;
	}

}
