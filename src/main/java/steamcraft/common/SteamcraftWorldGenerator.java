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

package steamcraft.common;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigBlocks;
import cpw.mods.fml.common.IWorldGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class SteamcraftWorldGenerator.
 *
 * @authors Surseance & warlordjones
 */
public class SteamcraftWorldGenerator implements IWorldGenerator
{

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.IWorldGenerator#generate(java.util.Random, int, int, net.minecraft.world.World, net.minecraft.world.chunk.IChunkProvider, net.minecraft.world.chunk.IChunkProvider)
	 */
	@Override
	public void generate( Random random,  int chunkX,
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
	private void generateSurface( World world,  Random random,
			 int chunkX,  int chunkZ)
	{
		if (Config.borniteOreGenEnabled)
		{
			for (int chance = 0; chance < Config.borniteChance; chance++)
			{
				 int borniteXCoord = chunkX + random.nextInt(16);
				 int borniteYCoord = random.nextInt(Config.borniteHeight);
				 int borniteZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 0,
						random.nextInt(3), Blocks.stone).generate(world,
						random, borniteXCoord, borniteYCoord, borniteZCoord);
			}
			if (Config.brimstoneOreGenEnabled)
			{
				for (int chance = 0; chance < Config.brimstoneChance; chance++)
				{
					 int brimstoneXCoord = chunkX + random.nextInt(16);
					 int brimstoneYCoord = random
							.nextInt(Config.brimstoneHeight);
					 int brimstoneZCoord = chunkZ + random.nextInt(16);

					new WorldGenMinable(ConfigBlocks.blockCustomOre, 1,
							random.nextInt(3), Blocks.stone).generate(world,
							random, brimstoneXCoord, brimstoneYCoord,
							brimstoneZCoord);
				}
			}
		}
		if (Config.phosphateOreGenEnabled)
		{
			for (int chance = 0; chance < Config.phosphateChance; chance++)
			{
				 int zincXCoord = chunkX + random.nextInt(16);
				 int zincYCoord = random.nextInt(Config.phosphateHeight);
				 int zincZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 2,
						random.nextInt(3), Blocks.stone).generate(world,
						random, zincXCoord, zincYCoord, zincZCoord);
			}
			if (Config.uraniumOreGenEnabled)
			{
				for (int chance = 0; chance < Config.uraniumChance; chance++)
				{
					 int uraniteXCoord = chunkX + random.nextInt(16);
					 int uraniteYCoord = random
							.nextInt(Config.uraniumHeight);
					 int uraniteZCoord = chunkZ + random.nextInt(16);

					new WorldGenMinable(ConfigBlocks.blockCustomOre, 3,
							random.nextInt(3), Blocks.stone)
							.generate(world, random, uraniteXCoord,
									uraniteYCoord, uraniteZCoord);
				}
			}
		}
		if (Config.aluminumOreGenEnabled)
		{
			for (int chance = 0; chance < Config.aluminumChance; chance++)
			{
				 int aluminumXCoord = chunkX + random.nextInt(16);
				 int aluminumYCoord = random
						.nextInt(Config.aluminumHeight);
				 int aluminumZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 4,
						random.nextInt(3), Blocks.stone).generate(world,
						random, aluminumXCoord, aluminumYCoord, aluminumZCoord);
			}
		}
		if (Config.copperOreGenEnabled)
		{
			for (int chance = 0; chance < Config.copperChance; chance++)
			{
				 int copperXCoord = chunkX + random.nextInt(16);
				 int copperYCoord = random.nextInt(Config.copperHeight);
				 int copperZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 5,
						random.nextInt(3), Blocks.stone).generate(world,
						random, copperXCoord, copperYCoord, copperZCoord);
			}
		}
		if (Config.tinOreGenEnabled)
		{
			for (int chance = 0; chance < Config.tinChance; chance++)
			{
				 int tinXCoord = chunkX + random.nextInt(16);
				 int tinYCoord = random.nextInt(Config.tinHeight);
				 int tinZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 6,
						random.nextInt(3), Blocks.stone).generate(world,
						random, tinXCoord, tinYCoord, tinZCoord);
			}
		}
		if (Config.zincOreGenEnabled)
		{
			for (int chance = 0; chance < Config.zincChance; chance++)
			{
				 int zincXCoord = chunkX + random.nextInt(16);
				 int zincYCoord = random.nextInt(Config.zincHeight);
				 int zincZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 7, random.nextInt(3), Blocks.stone).generate(world, random, zincXCoord, zincYCoord, zincZCoord);
			}
		}
		if(Config.slateGenEnabled)
		{
			for (int chance = 0; chance < Config.slateChance; chance++)
			{
				 int slateXCoord = chunkX + random.nextInt(16);
				 int slateYCoord = random.nextInt(Config.slateHeight);
				 int slateZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockSlate, 0, random.nextInt(10), Blocks.stone).generate(world,
						random, slateXCoord, slateYCoord, slateZCoord);
				System.out.print("SlateGen at" + String.valueOf(slateXCoord) + String.valueOf(slateYCoord) + String.valueOf(slateZCoord));
			}
			for (int chance = 0; chance < Config.slateChance; chance++)
			{
				 int slateXCoord = chunkX + random.nextInt(16);
				 int slateYCoord = random.nextInt(Config.slateHeight);
				 int slateZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockSlate, 1, random.nextInt(10), Blocks.stone).generate(world, random, slateXCoord, slateYCoord, slateZCoord);
				System.out.print("SlateGen at" + String.valueOf(slateXCoord) + String.valueOf(slateYCoord) + String.valueOf(slateZCoord));
			}
			for (int chance = 0; chance < Config.slateChance; chance++)
			{
				 int slateXCoord = chunkX + random.nextInt(16);
				 int slateYCoord = random.nextInt(Config.slateHeight);
				 int slateZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockSlate, 2, random.nextInt(10), Blocks.stone).generate(world, random, slateXCoord, slateYCoord, slateZCoord);
				System.out.print("SlateGen at" + String.valueOf(slateXCoord) + String.valueOf(slateYCoord) + String.valueOf(slateZCoord));
			}
		}
	}
	private void generateNether( World world,  Random random,
			 int chunkX,  int chunkZ)
	{
		if(Config.netherEtheriumCrystalGenEnabled)
		{
		for (int chance = 0; chance < Config.netherEtheriumCrystalChance; chance++)
		{
			 int etheriumXCoord = chunkX + random.nextInt(16);
			 int etheriumYCoord = random.nextInt(Config.netherEtheriumCrystalHeight);
			 int etheriumZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ConfigBlocks.blockCrystal,
					1, Blocks.netherrack).generate(world, random, etheriumXCoord, etheriumYCoord, etheriumZCoord);
		}
		}
	}
	private void generateEnd(final World world, final Random random,
			final int chunkX, final int chunkZ)
	{
		if(Config.endEtheriumCrystalGenEnabled)
		{
		for (int chance = 0; chance < Config.endEtheriumCrystalChance; chance++)
		{
			 int etheriumXCoord = chunkX + random.nextInt(16);
			 int etheriumYCoord = random.nextInt(Config.endEtheriumCrystalHeight);
			 int etheriumZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ConfigBlocks.blockCrystal,
					1, Blocks.end_stone).generate(world,
					random, etheriumXCoord, etheriumYCoord, etheriumZCoord);
		}
		}
	}
}