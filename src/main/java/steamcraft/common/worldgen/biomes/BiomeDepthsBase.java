package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import steamcraft.common.InitBlocks;
import steamcraft.common.entities.living.EntityGrub;
import steamcraft.common.entities.living.EntityLostMiner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeDepthsBase extends BiomeGenBase
{

	public BiomeDepthsBase(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityLostMiner.class, 2, 1, 2));
		this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityGrub.class, 5, 3, 6));
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.enableRain = false;
		this.setHeight(new Height(0.5F, 0.8F));
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.name"));
		this.rootHeight = 0.6F;
		this.func_76733_a(8045877);
		this.setColor(8045877);
		this.waterColorMultiplier = 6860222;
	}

	@Override
	public void decorate(World world, Random rand, int p_76728_3_, int p_76728_4_)
	{
		super.decorate(world, rand, p_76728_3_, p_76728_4_);
		int rarity = 6 + rand.nextInt(9);

		for(int a = 0; a < rarity; ++a)
		{
			int i1 = p_76728_3_ + rand.nextInt(16);
			int j1 = rand.nextInt(28) + 4;
			int k1 = p_76728_4_ + rand.nextInt(16);

			if(world.getBlock(i1, j1, k1).isReplaceableOreGen(world, i1, j1, k1, Blocks.stone))
			{
				new WorldGenMinable(InitBlocks.blockCompressedStone, rarity);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
	{
		return 8045877;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
	{
		return 8045877;
	}
}
