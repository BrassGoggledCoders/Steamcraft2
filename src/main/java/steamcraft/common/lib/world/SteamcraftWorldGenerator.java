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

package steamcraft.common.lib.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigBlocks;
import cpw.mods.fml.common.IWorldGenerator;

/**
 *
 * @author Surseance
 */
public class SteamcraftWorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider generator,
			IChunkProvider chunk)
	{
		//int blockChunkX = chunkX * 16;
		//int blockChunkZ = chunkZ * 16;

		switch (world.provider.dimensionId)
		{
		case -1:
			if (Config.netherGenerationEnabled)
			{
				this.generateNether(world, random, chunkX, chunkZ);
			}
			break;
		case 0:
			//if (Config.overworldGenerationEnabled)
			//{
				this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
			//}
			break;
		case 1:
			if (Config.endGenerationEnabled)
			{
				this.generateEnd(world, random, chunkX, chunkZ);
			}
			break;
		}
	}

	// TODO: Add Config Options for ore vein size
	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		if(Config.aluminumOreGenEnabled)
			addOreSpawn(ConfigBlocks.blockCustomOre,0, Blocks.stone, world, random, chunkX, chunkZ, 16, 16, 3+random.nextInt(3), Config.aluminumChance, 15, Config.aluminumHeight);
		if (Config.copperOreGenEnabled)
			addOreSpawn(ConfigBlocks.blockCustomOre,1, Blocks.stone,world, random, chunkX, chunkZ, 16, 16, 6+random.nextInt(3), Config.copperChance, 15, Config.copperHeight);
		if (Config.tinOreGenEnabled)
			addOreSpawn(ConfigBlocks.blockCustomOre,2,Blocks.stone, world, random, chunkX, chunkZ, 16, 16, 4+random.nextInt(3), Config.tinChance, 15, Config.tinHeight);
		if (Config.zincOreGenEnabled)
			addOreSpawn(ConfigBlocks.blockCustomOre,3, Blocks.stone,world, random, chunkX, chunkZ, 16, 16, 3+random.nextInt(3), Config.zincChance, 15, Config.zincHeight);
		if (Config.uraniumOreGenEnabled)
			addOreSpawn(ConfigBlocks.blockCustomOre,4,Blocks.stone, world, random, chunkX, chunkZ, 16, 16, 1+random.nextInt(3), Config.uraniumChance, 15, Config.uraniumHeight);
		if (Config.brimstoneOreGenEnabled)
			addOreSpawn(ConfigBlocks.blockCustomOre,5,Blocks.stone, world, random, chunkX, chunkZ, 16, 16, 3+random.nextInt(3), Config.brimstoneChance, 15, Config.brimstoneHeight);
		if (Config.phosphateOreGenEnabled)
			addOreSpawn(ConfigBlocks.blockCustomOre,6,Blocks.stone, world, random, chunkX, chunkZ, 16, 16, 3+random.nextInt(3), Config.phosphateChance, 15, Config.phosphateHeight);
	}

	private void generateEnd(World world, Random random, int chunkX, int chunkZ)
	{
		if (Config.netherEtheriumCrystalGenEnabled)
		{
			for (int chance = 0; chance < Config.netherEtheriumCrystalChance; chance++)
			{
				random.nextInt(Config.netherEtheriumCrystalHeight);
			}
		}
	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ)
	{
		if (Config.endEtheriumCrystalGenEnabled)
		{
			for (int chance = 0; chance < Config.endEtheriumCrystalChance; chance++)
			{
				random.nextInt(Config.endEtheriumCrystalHeight);
			}
		}
	}
	/**
     * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with this method in your Generation method in your IWorldGeneration extending Class
     *
     * @param The Block to spawn
     * @param The World to spawn in
     * @param A Random object for retrieving random positions within the world to spawn the Block
     * @param An int for passing the X-Coordinate for the Generation method
     * @param An int for passing the Z-Coordinate for the Generation method
     * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
     * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
     * @param An int for setting the maximum size of a vein
     * @param An int for the Number of chances available for the Block to spawn per-chunk
     * @param An int for the minimum Y-Coordinate height at which this block may spawn
     * @param An int for the maximum Y-Coordinate height at which this block may spawn
     **/
    public void addOreSpawn(Block block, int meta, Block blockToGenIn, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
    {
        assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
        assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
        assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
        assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
        assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

        int diffBtwnMinMaxY = maxY - minY;
        for (int x = 0; x < chancesToSpawn; x++)
        {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(diffBtwnMinMaxY);
            int posZ = blockZPos + random.nextInt(maxZ);
            (new WorldGenMinable(block, meta, maxVeinSize, blockToGenIn)).generate(world, random, posX, posY, posZ);
        }
    }
}