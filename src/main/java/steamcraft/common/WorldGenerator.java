
package steamcraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigBlocks;
import cpw.mods.fml.common.IWorldGenerator;

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
			if(Config.netherGenerationEnabled)
			generateNether(world, random, blockChunkX, blockChunkZ);
			break;
		case 0:
			if(Config.overworldGenerationEnabled)
			generateSurface(world, random, blockChunkX, blockChunkZ);
			break;
		case 1:
			if(Config.endGenerationEnabled)
			generateEnd(world, random, blockChunkX, blockChunkZ);
			break;
		}
	}
	//TODO: Add Config Options for ore vein size
	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		if(Config.etheriumOreGenEnabled)
		{
		for (int chance = 0; chance < Config.etheriumChance; chance++) {
			int etheriumXCoord = chunkX + random.nextInt(16);
			int etheriumYCoord = 8 + random.nextInt(Config.etheriumHeight);
			int etheriumZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCustomOre.blockID, 0, random.nextInt(3), Block.stone.blockID).generate(world, random, etheriumXCoord, etheriumYCoord, etheriumZCoord);
		}
		}
		if(Config.uraniumOreGenEnabled)
		{
		for (int chance = 0; chance < Config.uraniumChance; chance++) {
			int uraniteXCoord = chunkX + random.nextInt(16);
			int uraniteYCoord = random.nextInt(Config.uraniumHeight);
			int uraniteZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCustomOre.blockID, 1, random.nextInt(3), Block.stone.blockID).generate(world, random, uraniteXCoord, uraniteYCoord, uraniteZCoord);
		}
		}
		if(Config.brimstoneOreGenEnabled)
		{
		for (int chance = 0; chance < Config.brimstoneChance; chance++) {
			int brimstoneXCoord = chunkX + random.nextInt(16);
			int brimstoneYCoord = random.nextInt(Config.brimstoneHeight);
			int brimstoneZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCustomOre.blockID, 2, random.nextInt(3), Block.stone.blockID).generate(world, random, brimstoneXCoord, brimstoneYCoord, brimstoneZCoord);
		}
		}
		if(Config.borniteOreGenEnabled)
		{
		for (int chance = 0; chance < Config.borniteChance; chance++) {
			int borniteXCoord = chunkX + random.nextInt(16);
			int borniteYCoord = random.nextInt(Config.borniteHeight);
			int borniteZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCustomOre.blockID, 3, random.nextInt(3), Block.stone.blockID).generate(world, random, borniteXCoord, borniteYCoord, borniteZCoord);
		}
		}
		if(Config.phosphateOreGenEnabled)
		{
		for (int chance = 0; chance < Config.phosphateChance; chance++) {
			int zincXCoord = chunkX + random.nextInt(16);
			int zincYCoord = random.nextInt(Config.phosphateHeight);
			int zincZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCustomOre.blockID, 4, random.nextInt(3), Block.stone.blockID).generate(world, random, zincXCoord, zincYCoord, zincZCoord);
		}
		}
		if(Config.aluminumOreGenEnabled)
		{
		for (int chance = 0; chance < Config.aluminumChance; chance++) {
			int aluminumXCoord = chunkX + random.nextInt(16);
			int aluminumYCoord = random.nextInt(Config.aluminumHeight);
			int aluminumZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCustomOre.blockID, 5, random.nextInt(3), Block.stone.blockID).generate(world, random, aluminumXCoord, aluminumYCoord, aluminumZCoord);
		}
		}
		if(Config.copperOreGenEnabled)
		{
		for (int chance = 0; chance < Config.copperChance; chance++) {
			int copperXCoord = chunkX + random.nextInt(16);
			int copperYCoord = random.nextInt(Config.copperHeight);
			int copperZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCustomOre.blockID, 6, random.nextInt(3), Block.stone.blockID).generate(world, random, copperXCoord, copperYCoord, copperZCoord);
		}
		}
		if(Config.tinOreGenEnabled)
		{
		for (int chance = 0; chance < Config.tinChance; chance++) {
			int tinXCoord = chunkX + random.nextInt(16);
			int tinYCoord = random.nextInt(Config.tinHeight);
			int tinZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCustomOre.blockID, 7, random.nextInt(3), Block.stone.blockID).generate(world, random, tinXCoord, tinYCoord, tinZCoord);
		}
		}
		if(Config.zincOreGenEnabled)
		{
		for (int chance = 0; chance < Config.zincChance; chance++) {
			int zincXCoord = chunkX + random.nextInt(16);
			int zincYCoord = random.nextInt(Config.zincHeight);
			int zincZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCustomOre.blockID, 0, random.nextInt(3), Block.stone.blockID).generate(world, random, zincXCoord, zincYCoord, zincZCoord);
		}
		}
	}

	private void generateEnd(World world, Random random, int chunkX, int chunkZ) {
		if(Config.netherEtheriumCrystalGenEnabled)
		{
		for (int chance = 0; chance < Config.netherEtheriumCrystalChance; chance++) {
			int etheriumXCoord = chunkX + random.nextInt(16);
			int etheriumYCoord = random.nextInt(Config.netherEtheriumCrystalHeight);
			int etheriumZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCrystal.blockID, 0, random.nextInt(3), Block.stone.blockID).generate(world, random, etheriumXCoord, etheriumYCoord, etheriumZCoord);
		}
		}
	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ) {
		if(Config.endEtheriumCrystalGenEnabled)
		{
		for (int chance = 0; chance < Config.endEtheriumCrystalChance; chance++) {
			int phosphateXCoord = chunkX + random.nextInt(16);
			int phosphateYCoord = random.nextInt(Config.endEtheriumCrystalHeight);
			int phosphateZCoord = chunkZ + random.nextInt(16);

			new SmartOreGeneration(ConfigBlocks.blockCrystal.blockID, 0, random.nextInt(3), Block.stone.blockID).generate(world, random, phosphateXCoord, phosphateYCoord, phosphateZCoord);
		}
		}
	}
}