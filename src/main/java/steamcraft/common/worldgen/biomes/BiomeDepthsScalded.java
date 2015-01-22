package steamcraft.common.worldgen.biomes;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeDepthsScalded extends BiomeDepthsBase
{
	public BiomeDepthsScalded(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.scalded.name"));
		this.setHeight(new Height(0.5F, 0.5F));
		this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBlaze.class, 1, 1, 1));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 1, 1, 1));
		this.topBlock = Blocks.dirt;
		this.fillerBlock = Blocks.dirt;
	}
}
