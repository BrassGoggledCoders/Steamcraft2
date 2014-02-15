/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Jan 30, 2014, 5:19:11 PM]
 */
package common.steamcraft.mod.common.core.handler;

import common.steamcraft.mod.common.block.ModOres;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * 
 * @author MrArcane111
 *
 */
public class SC2_GenerationHandler implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider generator, IChunkProvider chunk) 
	{
        // For ore generation
		switch(world.provider.dimensionId)
		{
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
	}
	
	private void generateSurface(World world, Random random, int i, int j) 
	{
        //TODO: Change ore rarity
		for(int chance = 0; chance < 1; chance++) 
		{
			int etheriumXCoord = i + random.nextInt(16);
			int etheriumYCoord = 8 + random.nextInt(12);
			int etheriumZCoord = j + random.nextInt(16);
			
			new WorldGenMinable(ModOres.oreVolucite.blockID, 13).generate(world, random, etheriumXCoord, etheriumYCoord, etheriumZCoord);
		}
		for(int chance = 0; chance < 2; chance++) 
		{
			int uraniteXCoord = i + random.nextInt(16);
			int uraniteYCoord = random.nextInt(24);
			int uraniteZCoord = j + random.nextInt(16);
			
			new WorldGenMinable(ModOres.oreUranite.blockID, 13).generate(world, random, uraniteXCoord, uraniteYCoord, uraniteZCoord);
		}
		for(int chance = 0; chance < 12; chance++) 
		{
			int brimstoneXCoord = i + random.nextInt(16);
			int brimstoneYCoord = random.nextInt(64);
			int brimstoneZCoord = j + random.nextInt(16);
			
			new WorldGenMinable(ModOres.oreBrimstone.blockID, 13).generate(world, random, brimstoneXCoord, brimstoneYCoord, brimstoneZCoord);
		}
		for(int chance = 0; chance < 16; chance++) 
		{
			int borniteXCoord = i + random.nextInt(16);
			int borniteYCoord = random.nextInt(48);
			int borniteZCoord = j + random.nextInt(16);
			
			new WorldGenMinable(ModOres.oreBornite.blockID, 13).generate(world, random, borniteXCoord, borniteYCoord, borniteZCoord);
		}
		for(int chance = 0; chance < 3; chance++) 
		{
			int phosphateXCoord = i + random.nextInt(16);
			int phosphateYCoord = random.nextInt(36);
			int phosphateZCoord = j + random.nextInt(16);
			
			new WorldGenMinable(ModOres.orePhosphate.blockID, 13).generate(world, random, phosphateXCoord, phosphateYCoord, phosphateZCoord);
		}
	}

	private void generateEnd(World world, Random random, int i, int j) {}
	
	private void generateNether(World world, Random random, int i, int j) {}
}