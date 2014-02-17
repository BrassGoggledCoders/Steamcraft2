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
package common.steamcraft.mod.common.core.handler;

import common.steamcraft.mod.common.block.ModOres;

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
		// For ore generation
		switch (world.provider.dimensionId) {
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

	// TODO: Change ores' spawning characteristics
	private void generateSurface(World world, Random random, int i, int j) {
		for (int chance = 0; chance < 1; chance++) {
			int etheriumXCoord = i + random.nextInt(16);
			int etheriumYCoord = 8 + random.nextInt(12);
			int etheriumZCoord = j + random.nextInt(16);

			new WorldGenMinable(ModOres.oreVolucite.blockID, 13).generate(world, random, etheriumXCoord, etheriumYCoord, etheriumZCoord);
		}
		for (int chance = 0; chance < 2; chance++) {
			int uraniteXCoord = i + random.nextInt(16);
			int uraniteYCoord = random.nextInt(24);
			int uraniteZCoord = j + random.nextInt(16);

			new WorldGenMinable(ModOres.oreUranite.blockID, 13).generate(world, random, uraniteXCoord, uraniteYCoord, uraniteZCoord);
		}
		for (int chance = 0; chance < 12; chance++) {
			int brimstoneXCoord = i + random.nextInt(16);
			int brimstoneYCoord = random.nextInt(64);
			int brimstoneZCoord = j + random.nextInt(16);

			new WorldGenMinable(ModOres.oreBrimstone.blockID, 13).generate(world, random, brimstoneXCoord, brimstoneYCoord, brimstoneZCoord);
		}
		for (int chance = 0; chance < 16; chance++) {
			int borniteXCoord = i + random.nextInt(16);
			int borniteYCoord = random.nextInt(48);
			int borniteZCoord = j + random.nextInt(16);

			new WorldGenMinable(ModOres.oreBornite.blockID, 13).generate(world, random, borniteXCoord, borniteYCoord, borniteZCoord);
		}
		for (int chance = 0; chance < 3; chance++) {
			int phosphateXCoord = i + random.nextInt(16);
			int phosphateYCoord = random.nextInt(36);
			int phosphateZCoord = j + random.nextInt(16);

			new WorldGenMinable(ModOres.orePhosphate.blockID, 13).generate(world, random, phosphateXCoord, phosphateYCoord, phosphateZCoord);
		}
		for (int chance = 0; chance < 10; chance++) {
			int aluminumXCoord = i + random.nextInt(16);
			int aluminumYCoord = random.nextInt(36);
			int aluminumZCoord = j + random.nextInt(16);

			new WorldGenMinable(ModOres.oreAluminum.blockID, 13).generate(world, random, aluminumXCoord, aluminumYCoord, aluminumZCoord);
		}
		for (int chance = 0; chance < 10; chance++) {
			int copperXCoord = i + random.nextInt(16);
			int copperYCoord = random.nextInt(36);
			int copperZCoord = j + random.nextInt(16);

			new WorldGenMinable(ModOres.oreCopper.blockID, 13).generate(world, random, copperXCoord, copperYCoord, copperZCoord);
		}
		for (int chance = 0; chance < 10; chance++) {
			int tinXCoord = i + random.nextInt(16);
			int tinYCoord = random.nextInt(36);
			int tinZCoord = j + random.nextInt(16);

			new WorldGenMinable(ModOres.oreTin.blockID, 13).generate(world, random, tinXCoord, tinYCoord, tinZCoord);
		}
		for (int chance = 0; chance < 10; chance++) {
			int zincXCoord = i + random.nextInt(16);
			int zincYCoord = random.nextInt(36);
			int zincZCoord = j + random.nextInt(16);

			new WorldGenMinable(ModOres.oreZinc.blockID, 13).generate(world, random, zincXCoord, zincYCoord, zincZCoord);
		}
	}

	private void generateEnd(World world, Random random, int i, int j) {}

	private void generateNether(World world, Random random, int i, int j) {}
}