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
package steamcraft.common.config;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import steamcraft.common.worldgen.WorldGenBrassTree;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * @authors Surseance & warlordjones
 * 
 */
public class ConfigWorldGen implements IWorldGenerator
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider generator, IChunkProvider chunk)
	{
		int blockChunkX = chunkX * 16;
		int blockChunkZ = chunkZ * 16;

		switch (world.provider.dimensionId)
		{
		case -1:
			if (Config.netherGenerationEnabled)
				this.generateNether(world, random, blockChunkX, blockChunkZ);
			break;
		case 0:
			if (Config.overworldGenerationEnabled)
				this.generateSurface(world, random, blockChunkX, blockChunkZ);
			break;
		case 1:
			if (Config.endGenerationEnabled)
				this.generateEnd(world, random, blockChunkX, blockChunkZ);
			break;
		}
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		if (Config.aluminumOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, Config.aluminumChance, Config.aluminumHeight, 6, ConfigBlocks.blockCustomOre, 0, Blocks.stone);
		if (Config.copperOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, Config.copperChance, Config.copperHeight, 4, ConfigBlocks.blockCustomOre, 1, Blocks.stone);
		if (Config.tinOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, Config.tinChance, Config.tinHeight, 4, ConfigBlocks.blockCustomOre, 2, Blocks.stone);
		if (Config.zincOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, Config.zincChance, Config.zincHeight, 3, ConfigBlocks.blockCustomOre, 3, Blocks.stone);
		if (Config.uraniumOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, Config.uraniumChance, Config.uraniumHeight, 1, ConfigBlocks.blockCustomOre, 4, Blocks.stone);
		if (Config.brimstoneOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, Config.brimstoneChance, Config.brimstoneHeight, 4, ConfigBlocks.blockCustomOre, 5,
					Blocks.stone);
		if (Config.phosphateOreGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, Config.phosphateChance, Config.phosphateHeight, 3, ConfigBlocks.blockCustomOre, 6,
					Blocks.stone);
		if (Config.slateGenEnabled)
		{
			this.generateOre(world, random, chunkX, chunkZ, Config.slateChance, Config.slateHeight, 30, ConfigBlocks.blockSlate, 0,
					Blocks.stone);
			this.generateOre(world, random, chunkX, chunkZ, Config.slateChance, Config.slateHeight, 30, ConfigBlocks.blockSlate, 1,
					Blocks.stone);
			this.generateOre(world, random, chunkX, chunkZ, Config.slateChance, Config.slateHeight, 30, ConfigBlocks.blockSlate, 2,
					Blocks.stone);
		}
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
		if (Config.brassTreeGenEnabled && BiomeDictionary.isBiomeOfType(biome, Type.FOREST))
		{
			for (int n = 0; n < Config.brassTreeGenChance; n++)
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
		for (int i = 0; i < chance; i++)
		{
			int oreXCoord = chunkX + random.nextInt(16);
			int oreYCoord = random.nextInt(height);
			int oreZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ore, meta, random.nextInt(blocks), blockToGenIn).generate(world, random, oreXCoord, oreYCoord, oreZCoord);
		}
	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ)
	{
		if (Config.netherEtheriumCrystalGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, Config.netherEtheriumCrystalChance, Config.netherEtheriumCrystalHeight, 15,
					ConfigBlocks.blockCrystal, 0, Blocks.netherrack);
	}

	private void generateEnd(final World world, final Random random, final int chunkX, final int chunkZ)
	{
		if (Config.endEtheriumCrystalGenEnabled)
			this.generateOre(world, random, chunkX, chunkZ, Config.endEtheriumCrystalChance, Config.endEtheriumCrystalHeight, 15,
					ConfigBlocks.blockCrystal, 0, Blocks.end_stone);
	}
}