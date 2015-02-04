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
package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.InitBlocks;
import steamcraft.common.entities.living.EntityGrub;
import steamcraft.common.entities.living.EntityLostMiner;
import steamcraft.common.worldgen.structure.WorldGenUndergroundHouse;

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
	public void decorate(World world, Random rand, int blockChunkX, int blockChunkZ)
	{
		int rarity = 6 + rand.nextInt(9);

		for(int a = 0; a < rarity; ++a)
		{
			int x = blockChunkX + rand.nextInt(16);
			int y = rand.nextInt(28) + 4;
			int z = blockChunkZ + rand.nextInt(16);

			if(world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				new WorldGenMinable(InitBlocks.blockCompressedStone, rarity).generate(world, rand, x, y, z);
			}
		}
		for(int a = 0; a < 10; ++a)
		{
			int x = blockChunkX + rand.nextInt(16);
			int y = rand.nextInt(60);
			int z = blockChunkZ + rand.nextInt(16);

			if(world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				new WorldGenMinable(Blocks.emerald_ore, 3).generate(world, rand, x, y, z);
			}
		}

		int X2 = blockChunkX + rand.nextInt(16);
		int Z2 = blockChunkZ + rand.nextInt(16);
		int Y2 = rand.nextInt(50);

		for(int i = 0; i < 5; i++)
		{
			new WorldGenUndergroundHouse().generate(world, rand, X2, Y2, Z2);
		}
		super.decorate(world, rand, blockChunkX, blockChunkZ);
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

	@Override
	public BiomeDecorator getModdedBiomeDecorator(BiomeDecorator original)
	{
		return new DepthsBiomeDecorator(original);
	}
}
