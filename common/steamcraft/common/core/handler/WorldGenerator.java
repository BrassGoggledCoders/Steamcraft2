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
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 * 
 * File created @ [Jan 30, 2014, 5:19:11 PM]
 */
package common.steamcraft.common.core.handler;

import common.steamcraft.common.block.ModOres;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * @author MrArcane111
 *
 */
public class WorldGenerator implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider generator, IChunkProvider chunk) {
		int blockChunkX = chunkX * 16;
		int blockChunkZ = chunkZ * 16;
		
		switch (world.provider.dimensionId) {
		case -1:
			generateNether(world, random, blockChunkX, blockChunkZ);
			break;
		case 0:
			generateSurface(world, random, blockChunkX, blockChunkZ);
			break;
		case 1:
			generateEnd(world, random, blockChunkX, blockChunkZ);
			break;
		}
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		for (int chance = 0; chance < 1; chance++) {
			int etheriumXCoord = chunkX + random.nextInt(16);
			int etheriumYCoord = 8 + random.nextInt(12);
			int etheriumZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreVolucite.blockID, random.nextInt(2)).generate(world, random, etheriumXCoord, etheriumYCoord, etheriumZCoord);
		}
		for (int chance = 0; chance < 3; chance++) {
			int uraniteXCoord = chunkX + random.nextInt(16);
			int uraniteYCoord = random.nextInt(24);
			int uraniteZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreUranite.blockID, random.nextInt(5)).generate(world, random, uraniteXCoord, uraniteYCoord, uraniteZCoord);
		}
		for (int chance = 0; chance < 10; chance++) {
			int brimstoneXCoord = chunkX + random.nextInt(16);
			int brimstoneYCoord = random.nextInt(64);
			int brimstoneZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreBrimstone.blockID, random.nextInt(8)).generate(world, random, brimstoneXCoord, brimstoneYCoord, brimstoneZCoord);
		}
		for (int chance = 0; chance < 20; chance++) {
			int borniteXCoord = chunkX + random.nextInt(16);
			int borniteYCoord = random.nextInt(48);
			int borniteZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.oreBornite.blockID, random.nextInt(12)).generate(world, random, borniteXCoord, borniteYCoord, borniteZCoord);
		}
		for (int chance = 0; chance < 3; chance++) {
			int phosphateXCoord = chunkX + random.nextInt(16);
			int phosphateYCoord = random.nextInt(36);
			int phosphateZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(ModOres.orePhosphate.blockID, random.nextInt(6)).generate(world, random, phosphateXCoord, phosphateYCoord, phosphateZCoord);
		}
	}

	private void generateEnd(World world, Random random, int i, int j) {}

	private void generateNether(World world, Random random, int i, int j) {}
}