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
package steamcraft.common.worldgen.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import steamcraft.common.InitBlocks;

public class WorldGenFissurePortal extends WorldGenerator
{
	private boolean locationIsValidSpawn(World world, int i, int j, int k)
	{
		Block check = world.getBlock(i, j, k);

		if(/* check == Blocks.stone || */check == Blocks.bedrock)
			return true;
		else
			return false;
	}

	public WorldGenFissurePortal()
	{
	}

	private void setBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		world.setBlock(x, y, z, block, metadata, 2);
	}

	@Override
	public boolean generate(World world, Random rand, int i, int j, int k)
	{
		// FMLLog.bigWarning("Gen at: " + i + j + k, "");
		if(!this.locationIsValidSpawn(world, i, j, k))
		{
			return false;
		}

		k = k - 10;
		i = i - 10;

		this.setBlock(world, i + 0, j + 1, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 0, j + 1, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 0, j + 1, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 0, j + 1, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 9, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 2, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 2, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 2, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 2, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 3, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 1, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 1, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 1, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 1, k + 6, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 2, j + 1, k + 7, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 2, j + 1, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 1, k + 9, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 2, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 2, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 2, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 2, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 3, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 3, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 3, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 3, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 4, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 1, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 1, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 1, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 1, k + 5, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 6, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 7, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 1, k + 9, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 2, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 2, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 2, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 3, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 3, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 3, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 4, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 1, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 1, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 1, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 1, k + 4, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 5, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 6, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 7, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 2, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 2, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 2, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 2, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 3, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 3, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 3, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 4, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 1, k + 0, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 1, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 1, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 1, k + 3, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 5, j + 1, k + 4, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 5, j + 1, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 1, k + 6, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 5, j + 1, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 1, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 2, k + 0, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 2, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 2, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 2, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 2, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 3, k + 0, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 3, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 3, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 4, k + 0, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 4, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 1, k + 0, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 1, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 1, k + 2, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 6, j + 1, k + 3, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 6, j + 1, k + 4, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 6, j + 1, k + 5, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 6, j + 1, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 1, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 2, k + 0, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 2, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 2, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 2, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 3, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 3, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 4, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 1, k + 0, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 1, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 1, k + 2, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 7, j + 1, k + 3, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 7, j + 1, k + 4, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 7, j + 1, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 1, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 2, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 2, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 3, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 3, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 8, j + 1, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 8, j + 1, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 8, j + 1, k + 3, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 8, j + 1, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 8, j + 1, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 8, j + 2, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 8, j + 2, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 8, j + 3, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 9, j + 1, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 9, j + 1, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 9, j + 1, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 9, j + 2, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 9, j + 2, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 9, j + 3, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 10, j + 1, k + 3, Blocks.bedrock, 0);

		return true;
	}
}
