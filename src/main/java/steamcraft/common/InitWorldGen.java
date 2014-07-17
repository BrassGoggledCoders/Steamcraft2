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
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.aluminumChance, ConfigWorldGen.aluminumHeight, 6, InitBlocks.blockCustomOre, 0, Blocks.stone);
		if (ConfigWorldGen.copperOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.copperChance, ConfigWorldGen.copperHeight, 4, InitBlocks.blockCustomOre, 1, Blocks.stone);
		if (ConfigWorldGen.tinOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.tinChance, ConfigWorldGen.tinHeight, 4, InitBlocks.blockCustomOre, 2, Blocks.stone);
		if (ConfigWorldGen.zincOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.zincChance, ConfigWorldGen.zincHeight, 3, InitBlocks.blockCustomOre, 3, Blocks.stone);
		if (ConfigWorldGen.uraniumOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.uraniumChance, ConfigWorldGen.uraniumHeight, 1, InitBlocks.blockCustomOre, 4, Blocks.stone);
		if (ConfigWorldGen.brimstoneOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.brimstoneChance, ConfigWorldGen.brimstoneHeight, 4, InitBlocks.blockCustomOre, 5,
					Blocks.stone);
		if (ConfigWorldGen.phosphateOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.phosphateChance, ConfigWorldGen.phosphateHeight, 3, InitBlocks.blockCustomOre, 6,
					Blocks.stone);
		if (ConfigWorldGen.slateGenEnabled)
		{
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.slateChance, ConfigWorldGen.slateHeight, 30, InitBlocks.blockSlate, 0,
					Blocks.stone);
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.slateChance, ConfigWorldGen.slateHeight, 30, InitBlocks.blockSlate, 1,
					Blocks.stone);
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.slateChance, ConfigWorldGen.slateHeight, 30, InitBlocks.blockSlate, 2,
					Blocks.stone);
		}
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
		if (ConfigWorldGen.brassTreeGenEnabled && BiomeDictionary.isBiomeOfType(biome, Type.FOREST))
		{
			Random rand = new Random();
			int n = rand.nextInt(100);
			if(n == ConfigWorldGen.brassTreeGenChance)
			{
				int X = chunkX + random.nextInt(16);
				int Z = chunkZ + random.nextInt(16);
				int Y = random.nextInt(70);

				new WorldGenBrassTree(false).generate(world, random, X, Y, Z);
			}
		}
	}

	private void generateOre(World world, Random random, int chunkX, int chunkZ, int chance, int height, int blocks, Block ore, int meta,
			Block blockToGenIn)
	{
		Random rand = new Random();
		int n = rand.nextInt(100);
		if(n == chance)
		{
			int oreXCoord = chunkX + random.nextInt(16);
			int oreYCoord = random.nextInt(height);
			int oreZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ore, meta, random.nextInt(blocks), blockToGenIn).generate(world, random, oreXCoord, oreYCoord, oreZCoord);
		}
	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ)
	{
		if (ConfigWorldGen.netherEtheriumCrystalGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.netherEtheriumCrystalChance, ConfigWorldGen.netherEtheriumCrystalHeight, 15,
					InitBlocks.blockCrystal, 0, Blocks.netherrack);
	}

	private void generateEnd(final World world, final Random random, final int chunkX, final int chunkZ)
	{
		if (ConfigWorldGen.endEtheriumCrystalGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, ConfigWorldGen.endEtheriumCrystalChance, ConfigWorldGen.endEtheriumCrystalHeight, 15,
					InitBlocks.blockCrystal, 0, Blocks.end_stone);
	}
}