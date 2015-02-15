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
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.entities.living.EntityGrub;
import steamcraft.common.entities.living.EntityLostMiner;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.worldgen.WorldGenRandomUnderground;

public class BiomeDepthsBase extends BiomeGenBase
{
	// private DepthsBiomeDecorator decorator = (DepthsBiomeDecorator) this.theBiomeDecorator;

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
	public void decorate(World worldObj, Random rand, int chunkX, int chunkZ)
	{
		{
			int xCoord = chunkX + rand.nextInt(16);
			int yCoord = 10 + rand.nextInt(40);
			int zCoord = chunkZ + rand.nextInt(16);
			new WorldGenRandomUnderground(Blocks.web).generate(worldObj, rand, xCoord, yCoord, zCoord);
		}

		{
			int xCoord = chunkX + rand.nextInt(16);
			int yCoord = 10 + rand.nextInt(40);
			int zCoord = chunkZ + rand.nextInt(16);
			new WorldGenRandomUnderground(Blocks.skull).generate(worldObj, rand, xCoord, yCoord, zCoord);
		}
		super.decorate(worldObj, rand, chunkX, chunkZ);
		{
			int xCoord = chunkX + rand.nextInt(16);
			int yCoord = 10 + rand.nextInt(40);
			int zCoord = chunkZ + rand.nextInt(16);
			new WorldGenRandomUnderground(Blocks.vine).generate(worldObj, rand, xCoord, yCoord, zCoord);
		}
		{
			int xCoord = chunkX + rand.nextInt(16);
			int yCoord = 10 + rand.nextInt(40);
			int zCoord = chunkZ + rand.nextInt(16);
			new WorldGenRandomUnderground(InitBlocks.blockMushroom, 0).generate(worldObj, rand, xCoord, yCoord, zCoord);
		}
		{
			int xCoord = chunkX + rand.nextInt(16);
			int yCoord = 10 + rand.nextInt(40);
			int zCoord = chunkZ + rand.nextInt(16);
			new WorldGenRandomUnderground(InitBlocks.blockMushroom, 1).generate(worldObj, rand, xCoord, yCoord, zCoord);
		}
		{
			int xCoord = chunkX + rand.nextInt(16);
			int yCoord = 10 + rand.nextInt(40);
			int zCoord = chunkZ + rand.nextInt(16);
			new WorldGenRandomUnderground(InitBlocks.blockMushroom, 2).generate(worldObj, rand, xCoord, yCoord, zCoord);
		}

		for(int a = 0; a < 6 + rand.nextInt(9); ++a)
		{
			int x = chunkX + rand.nextInt(16);
			int y = rand.nextInt(28) + 4;
			int z = chunkZ + rand.nextInt(16);
			if(worldObj.getBlock(x, y, z).isReplaceableOreGen(worldObj, x, y, z, Blocks.stone))
			{
				new WorldGenMinable(InitBlocks.blockCompressedStone, 6 +
						rand.nextInt(9)).generate(worldObj, rand, x, y, z);
			}
		}
		for(int a = 0; a < 10; ++a)
		{
			int x = chunkX + rand.nextInt(16);
			int y = rand.nextInt(60);
			int z = chunkZ + rand.nextInt(16);
			if(worldObj.getBlock(x, y, z).isReplaceableOreGen(worldObj, x, y, z, Blocks.stone))
			{
				new WorldGenMinable(Blocks.emerald_ore, 3).generate(worldObj, rand, x, y, z);
			}
		}
		for(int i3 = 0; i3 < 3; i3++)
		{
			int oreXCoord = chunkX +
					rand.nextInt(16);
			int oreYCoord = 10 + rand.nextInt(40);
			int oreZCoord = chunkZ + rand.nextInt(16);
			new WorldGenMinable(InitBlocks.blockBoulder, 0,
					1, Blocks.stone).generate(worldObj, rand, oreXCoord, oreYCoord, oreZCoord);
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
