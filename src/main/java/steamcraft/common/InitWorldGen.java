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
package steamcraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import steamcraft.common.config.ConfigWorldGen;
import steamcraft.common.worldgen.WorldGenBrassTree;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * @authors Surseance & warlordjones
 * 
 */
public class InitWorldGen implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider generator, IChunkProvider chunk)
	{
		int blockChunkX = chunkX * 16;
		int blockChunkZ = chunkZ * 16;

		switch (world.provider.dimensionId)
		{
		case -1:
			if (ConfigWorldGen.netherGenerationEnabled)
				this.generateNether(world, random, blockChunkX, blockChunkZ);
			break;
		case 0:
			if (ConfigWorldGen.overworldGenerationEnabled)
				this.generateSurface(world, random, blockChunkX, blockChunkZ);
			break;
		case 1:
			if (ConfigWorldGen.endGenerationEnabled)
				this.generateEnd(world, random, blockChunkX, blockChunkZ);
			break;
		}
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		if (ConfigWorldGen.aluminumOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, 10, 20, 40, 
				ConfigWorldGen.aluminumCluster, InitBlocks.blockCustomOre, 0, Blocks.stone);

		if (ConfigWorldGen.copperOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, 15, 22, 45, 
				ConfigWorldGen.copperCluster, InitBlocks.blockCustomOre, 1, Blocks.stone);

		if (ConfigWorldGen.tinOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, 15, 22, 45,
				ConfigWorldGen.tinCluster, InitBlocks.blockCustomOre, 2, Blocks.stone);

		if (ConfigWorldGen.zincOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, 12, 20, 36,
				ConfigWorldGen.zincCluster, InitBlocks.blockCustomOre, 3, Blocks.stone);

		if (ConfigWorldGen.uraniumOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, 1, 4, 24, 
				ConfigWorldGen.uraniumCluster, InitBlocks.blockCustomOre, 4, Blocks.stone);

		if (ConfigWorldGen.brimstoneOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, 5, 20, 64, 
				ConfigWorldGen.brimstoneCluster, InitBlocks.blockCustomOre, 5, Blocks.stone);

		if (ConfigWorldGen.phosphateOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, 7, 20, 36,
				ConfigWorldGen.phosphateCluster, InitBlocks.blockCustomOre, 6, Blocks.stone);

		if (ConfigWorldGen.slateGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, 1, 40, 70,
				ConfigWorldGen.slateCluster, InitBlocks.blockSlate, 0, Blocks.stone);

		if (ConfigWorldGen.brassTreeGenEnabled)
		{
			BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);

			if (BiomeDictionary.isBiomeOfType(biome, Type.FOREST))
				for (int i = 0; i < ConfigWorldGen.brassTreeGenCluster; i++)
				{
					int X = chunkX + random.nextInt(16);
					int Z = chunkZ + random.nextInt(16);
					int Y = random.nextInt(70);

					new WorldGenBrassTree(false).generate(world, random, X, Y, Z);
				}
		}
	}

	private void generateOre(World world, Random random, int chunkX, int chunkZ, int blockPerChunk, int minHeight, int maxHeight, int blocks,
			Block ore, int meta, Block blockToGenIn)
	{
		for (int i = 0; i < blockPerChunk; i++)
		{
			int oreXCoord = chunkX + random.nextInt(16);
			int oreYCoord = minHeight + random.nextInt(maxHeight - minHeight);
			int oreZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ore, meta, blocks, blockToGenIn).generate(world, random, oreXCoord, oreYCoord, oreZCoord);
		}
	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ)
	{
		if (ConfigWorldGen.netherEtheriumCrystalGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.netherEtheriumCrystalCluster, 30, 120, 
					15, InitBlocks.blockCrystal, 0, Blocks.netherrack);
	}

	private void generateEnd(final World world, final Random random, final int chunkX, final int chunkZ)
	{
		if (ConfigWorldGen.endEtheriumCrystalGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.endEtheriumCrystalCluster, 20, 100,
					15, InitBlocks.blockCrystal, 0, Blocks.end_stone);
	}
}