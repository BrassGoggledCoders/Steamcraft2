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

/**
 * @author Surseance
 * 
 */
public class SteamcraftWorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(final Random random, final int chunkX,
			final int chunkZ, final World world,
			final IChunkProvider generator, final IChunkProvider chunk)
	{
		final int blockChunkX = chunkX * 16;
		final int blockChunkZ = chunkZ * 16;

		switch (world.provider.dimensionId)
		{
		case -1:
			if (Config.netherGenerationEnabled)
			{
				// generateNether(world, random, blockChunkX, blockChunkZ);
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
				// generateEnd(world, random, blockChunkX, blockChunkZ);
				break;
			}
		}
	}

	// TODO: Add Config Options for ore vein size
	private void generateSurface(final World world, final Random random,
			final int chunkX, final int chunkZ)
	{
		if (Config.borniteOreGenEnabled)
		{
			for (int chance = 0; chance < Config.borniteChance; chance++)
			{
				final int borniteXCoord = chunkX + random.nextInt(16);
				final int borniteYCoord = random.nextInt(Config.borniteHeight);
				final int borniteZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 0,
						random.nextInt(3), Blocks.stone).generate(world,
						random, borniteXCoord, borniteYCoord, borniteZCoord);
			}
			if (Config.brimstoneOreGenEnabled)
			{
				for (int chance = 0; chance < Config.brimstoneChance; chance++)
				{
					final int brimstoneXCoord = chunkX + random.nextInt(16);
					final int brimstoneYCoord = random
							.nextInt(Config.brimstoneHeight);
					final int brimstoneZCoord = chunkZ + random.nextInt(16);

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
				final int zincXCoord = chunkX + random.nextInt(16);
				final int zincYCoord = random.nextInt(Config.phosphateHeight);
				final int zincZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 2,
						random.nextInt(3), Blocks.stone).generate(world,
						random, zincXCoord, zincYCoord, zincZCoord);
			}
			if (Config.uraniumOreGenEnabled)
			{
				for (int chance = 0; chance < Config.uraniumChance; chance++)
				{
					final int uraniteXCoord = chunkX + random.nextInt(16);
					final int uraniteYCoord = random
							.nextInt(Config.uraniumHeight);
					final int uraniteZCoord = chunkZ + random.nextInt(16);

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
				final int aluminumXCoord = chunkX + random.nextInt(16);
				final int aluminumYCoord = random
						.nextInt(Config.aluminumHeight);
				final int aluminumZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 4,
						random.nextInt(3), Blocks.stone).generate(world,
						random, aluminumXCoord, aluminumYCoord, aluminumZCoord);
			}
		}
		if (Config.copperOreGenEnabled)
		{
			for (int chance = 0; chance < Config.copperChance; chance++)
			{
				final int copperXCoord = chunkX + random.nextInt(16);
				final int copperYCoord = random.nextInt(Config.copperHeight);
				final int copperZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 5,
						random.nextInt(3), Blocks.stone).generate(world,
						random, copperXCoord, copperYCoord, copperZCoord);
			}
		}
		if (Config.tinOreGenEnabled)
		{
			for (int chance = 0; chance < Config.tinChance; chance++)
			{
				final int tinXCoord = chunkX + random.nextInt(16);
				final int tinYCoord = random.nextInt(Config.tinHeight);
				final int tinZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 6,
						random.nextInt(3), Blocks.stone).generate(world,
						random, tinXCoord, tinYCoord, tinZCoord);
			}
		}
		if (Config.zincOreGenEnabled)
		{
			for (int chance = 0; chance < Config.zincChance; chance++)
			{
				final int zincXCoord = chunkX + random.nextInt(16);
				final int zincYCoord = random.nextInt(Config.zincHeight);
				final int zincZCoord = chunkZ + random.nextInt(16);

				new WorldGenMinable(ConfigBlocks.blockCustomOre, 7,
						random.nextInt(3), Blocks.stone).generate(world,
						random, zincXCoord, zincYCoord, zincZCoord);
			}
		}
	}
	// TODO: Add etherium crystal generation (in all dimensions)
}