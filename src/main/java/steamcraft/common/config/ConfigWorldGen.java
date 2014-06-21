/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ 23-May-2014
 */

package steamcraft.common.config;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class SteamcraftWorldGenerator.
 *
 * @authors Surseance & warlordjones
 */
public class ConfigWorldGen implements IWorldGenerator
{

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.IWorldGenerator#generate(java.util.Random, int, int, net.minecraft.world.World, net.minecraft.world.chunk.IChunkProvider, net.minecraft.world.chunk.IChunkProvider)
	 */
	@Override
	public void generate(Random random, int chunkX,
			 int chunkZ,  World world,
			 IChunkProvider generator,  IChunkProvider chunk)
	{
		 int blockChunkX = chunkX * 16;
		 int blockChunkZ = chunkZ * 16;

		switch (world.provider.dimensionId)
		{
		case -1:
			if (Config.netherGenerationEnabled)
			{
				generateNether(world, random, blockChunkX, blockChunkZ);
				break;
			}
		case 0:
			if (Config.overworldGenerationEnabled)
			{
				generateSurface(world, random, blockChunkX, blockChunkZ);
			}
			break;
		case 1:
			if (Config.endGenerationEnabled)
			{
				generateEnd(world, random, blockChunkX, blockChunkZ);
				break;
			}
		}
	}

	// TODO: Add Config Options for ore vein size
	/**
	 * Generate surface.
	 *
	 * @param world the world
	 * @param random the random
	 * @param chunkX the chunk x
	 * @param chunkZ the chunk z
	 */
	private void generateSurface(World world, Random random,
			 int chunkX,  int chunkZ)
	{
		if (Config.aluminumOreGenEnabled)
		generateOre(world, random, chunkX, chunkZ, Config.aluminumChance, Config.aluminumHeight, 6, ConfigBlocks.blockCustomOre, 0, Blocks.stone);
		if (Config.copperOreGenEnabled)
		generateOre(world, random, chunkX, chunkZ, Config.copperChance, Config.copperHeight, 4, ConfigBlocks.blockCustomOre, 1, Blocks.stone);
		if (Config.tinOreGenEnabled)
		generateOre(world, random, chunkX, chunkZ, Config.tinChance, Config.tinHeight, 4, ConfigBlocks.blockCustomOre, 2, Blocks.stone);
		if (Config.zincOreGenEnabled)
		generateOre(world, random, chunkX, chunkZ, Config.zincChance, Config.zincHeight, 3, ConfigBlocks.blockCustomOre, 3, Blocks.stone);
		if (Config.uraniumOreGenEnabled)
		generateOre(world, random, chunkX, chunkZ, Config.uraniumChance, Config.uraniumHeight, 1, ConfigBlocks.blockCustomOre, 4, Blocks.stone);
		if (Config.brimstoneOreGenEnabled)
		generateOre(world, random, chunkX, chunkZ, Config.brimstoneChance, Config.brimstoneHeight, 4, ConfigBlocks.blockCustomOre, 5, Blocks.stone);
		if (Config.phosphateOreGenEnabled)
		generateOre(world, random, chunkX, chunkZ, Config.phosphateChance, Config.phosphateHeight, 3, ConfigBlocks.blockCustomOre, 6, Blocks.stone);
		if(Config.slateGenEnabled)
		{
			generateOre(world, random, chunkX, chunkZ, Config.slateChance, Config.slateHeight, 15, ConfigBlocks.blockSlate, 0, Blocks.stone);
			generateOre(world, random, chunkX, chunkZ, Config.slateChance, Config.slateHeight, 15, ConfigBlocks.blockSlate, 1, Blocks.stone);
			generateOre(world, random, chunkX, chunkZ, Config.slateChance, Config.slateHeight, 15, ConfigBlocks.blockSlate, 2, Blocks.stone);
		}
	}
	private void generateOre(World world, Random random, int chunkX, int chunkZ, int chance, int height, int blocks, Block ore, int meta, Block blockToGenIn)
	{
		for (int i = 0; i < chance; i++)
		{
			 int oreXCoord = chunkX + random.nextInt(16);
			 int oreYCoord = random.nextInt(height);
			 int oreZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ore, meta, random.nextInt(blocks), blockToGenIn).generate(world, random, oreXCoord, oreYCoord, oreZCoord);
		}
	}

	private void generateNether(World world,  Random random,
			 int chunkX,  int chunkZ)
	{
		if(Config.netherEtheriumCrystalGenEnabled)
		generateOre(world, random, chunkX, chunkZ, Config.netherEtheriumCrystalChance, Config.netherEtheriumCrystalHeight, 15, ConfigBlocks.blockCrystal, 0, Blocks.netherrack);
	}
	private void generateEnd(final World world, final Random random,
			final int chunkX, final int chunkZ)
	{
		if(Config.endEtheriumCrystalGenEnabled)
		generateOre(world, random, chunkX, chunkZ, Config.endEtheriumCrystalChance, Config.endEtheriumCrystalHeight, 15, ConfigBlocks.blockCrystal, 0, Blocks.end_stone);
	}
}