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

public class WorldGenUndergroundHouse extends WorldGenerator
{
	private boolean locationIsValidSpawn(World world, int i, int j, int k)
	{
		Block check = world.getBlock(i, j, k);

		return check == Blocks.stone;
	}

	public WorldGenUndergroundHouse()
	{
	}

	private void setBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		world.setBlock(x, y, z, block, metadata, 2);
	}

	@Override
	public boolean generate(World world, Random rand, int i, int j, int k)
	{
		if(!this.locationIsValidSpawn(world, i, j, k))
		{
			return false;
		}

		k = k - 10;
		i = i - 10;

		this.setBlock(world, i + 1, j + 1, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 1, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 2, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 2, k + 2, Blocks.stone_brick_stairs, 1);
		this.setBlock(world, i + 1, j + 2, k + 3, Blocks.stone_brick_stairs, 1);
		this.setBlock(world, i + 1, j + 2, k + 4, Blocks.stone_brick_stairs, 1);
		this.setBlock(world, i + 1, j + 2, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 3, k + 1, Blocks.glowstone, 0);
		this.setBlock(world, i + 1, j + 3, k + 2, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 1, j + 3, k + 3, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 1, j + 3, k + 4, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 1, j + 3, k + 5, Blocks.glowstone, 0);
		this.setBlock(world, i + 1, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 4, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 4, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 4, k + 4, Blocks.stonebrick, 1);
		this.setBlock(world, i + 1, j + 4, k + 5, Blocks.stonebrick, 1);
		this.setBlock(world, i + 2, j + 1, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 2, j + 1, k + 2, Blocks.stonebrick, 1);
		this.setBlock(world, i + 2, j + 1, k + 3, Blocks.stonebrick, 1);
		this.setBlock(world, i + 2, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 2, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 2, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		this.setBlock(world, i + 2, j + 2, k + 2, Blocks.bed, 10);
		this.setBlock(world, i + 2, j + 2, k + 3, Blocks.bed, 2);
		this.setBlock(world, i + 2, j + 2, k + 4, Blocks.chest, 5);
		this.setBlock(world, i + 2, j + 2, k + 5, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 2, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 2, j + 3, k + 2);
		world.setBlockToAir(i + 2, j + 3, k + 3);
		world.setBlockToAir(i + 2, j + 3, k + 4);
		this.setBlock(world, i + 2, j + 3, k + 5, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 2, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 2, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 2, j + 4, k + 3, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 2, j + 4, k + 4, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 2, j + 4, k + 5, Blocks.stonebrick, 1);
		this.setBlock(world, i + 3, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 3, j + 1, k + 2, Blocks.stonebrick, 1);
		this.setBlock(world, i + 3, j + 1, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 3, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 3, j + 1, k + 5, Blocks.cobblestone, 0);
		this.setBlock(world, i + 3, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 3, j + 2, k + 2);
		world.setBlockToAir(i + 3, j + 2, k + 3);
		world.setBlockToAir(i + 3, j + 2, k + 4);
		this.setBlock(world, i + 3, j + 2, k + 5, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 3, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 3, j + 3, k + 2);
		world.setBlockToAir(i + 3, j + 3, k + 3);
		world.setBlockToAir(i + 3, j + 3, k + 4);
		this.setBlock(world, i + 3, j + 3, k + 5, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 3, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 3, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 3, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 3, j + 4, k + 4, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 3, j + 4, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 4, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 1, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 1, k + 5, Blocks.cobblestone, 0);
		this.setBlock(world, i + 4, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 4, j + 2, k + 2);
		world.setBlockToAir(i + 4, j + 2, k + 3);
		world.setBlockToAir(i + 4, j + 2, k + 4);
		this.setBlock(world, i + 4, j + 2, k + 5, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 4, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 4, j + 3, k + 2);
		world.setBlockToAir(i + 4, j + 3, k + 3);
		world.setBlockToAir(i + 4, j + 3, k + 4);
		this.setBlock(world, i + 4, j + 3, k + 5, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 4, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 4, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 4, j + 4, k + 4, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 4, j + 4, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 5, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 3, Blocks.stonebrick, 2);
		this.setBlock(world, i + 5, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 6, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 7, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 5, j + 2, k + 2);
		world.setBlockToAir(i + 5, j + 2, k + 3);
		world.setBlockToAir(i + 5, j + 2, k + 4);
		this.setBlock(world, i + 5, j + 2, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 2, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 5, j + 3, k + 2);
		world.setBlockToAir(i + 5, j + 3, k + 3);
		world.setBlockToAir(i + 5, j + 3, k + 4);
		this.setBlock(world, i + 5, j + 3, k + 5, Blocks.glowstone, 0);
		this.setBlock(world, i + 5, j + 3, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 5, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 5, j + 4, k + 4, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 5, j + 4, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 4, k + 6, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 5, j + 4, k + 7, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 5, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 6, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 3, Blocks.cobblestone, 0);
		this.setBlock(world, i + 6, j + 1, k + 4, Blocks.stonebrick, 2);
		this.setBlock(world, i + 6, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 6, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 7, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 8, Blocks.cobblestone, 0);
		this.setBlock(world, i + 6, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 6, j + 2, k + 2);
		world.setBlockToAir(i + 6, j + 2, k + 3);
		world.setBlockToAir(i + 6, j + 2, k + 4);
		world.setBlockToAir(i + 6, j + 2, k + 5);
		this.setBlock(world, i + 6, j + 2, k + 8, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 6, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 6, j + 3, k + 2);
		world.setBlockToAir(i + 6, j + 3, k + 3);
		world.setBlockToAir(i + 6, j + 3, k + 4);
		world.setBlockToAir(i + 6, j + 3, k + 5);
		this.setBlock(world, i + 6, j + 3, k + 8, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 6, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 6, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 6, j + 4, k + 4, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 6, j + 4, k + 5, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 6, j + 4, k + 6, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 6, j + 4, k + 7, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 6, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 7, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 1, k + 3, Blocks.cobblestone, 0);
		this.setBlock(world, i + 7, j + 1, k + 4, Blocks.cobblestone, 0);
		this.setBlock(world, i + 7, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 1, k + 6, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 1, k + 7, Blocks.stonebrick, 1);
		this.setBlock(world, i + 7, j + 1, k + 8, Blocks.mossy_cobblestone, 0);
		this.setBlock(world, i + 7, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 7, j + 2, k + 2);
		world.setBlockToAir(i + 7, j + 2, k + 3);
		world.setBlockToAir(i + 7, j + 2, k + 4);
		world.setBlockToAir(i + 7, j + 2, k + 5);
		world.setBlockToAir(i + 7, j + 2, k + 6);
		world.setBlockToAir(i + 7, j + 2, k + 7);
		this.setBlock(world, i + 7, j + 2, k + 8, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 7, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 7, j + 3, k + 2);
		world.setBlockToAir(i + 7, j + 3, k + 3);
		world.setBlockToAir(i + 7, j + 3, k + 4);
		world.setBlockToAir(i + 7, j + 3, k + 5);
		world.setBlockToAir(i + 7, j + 3, k + 6);
		world.setBlockToAir(i + 7, j + 3, k + 7);
		this.setBlock(world, i + 7, j + 3, k + 8, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 7, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 7, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 7, j + 4, k + 4, Blocks.stone_slab, 8);
		this.setBlock(world, i + 7, j + 4, k + 5, Blocks.stone_slab, 8);
		this.setBlock(world, i + 7, j + 4, k + 6, Blocks.stone_slab, 8);
		this.setBlock(world, i + 7, j + 4, k + 7, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 7, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 8, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 8, j + 1, k + 2, Blocks.cauldron, 0);
		this.setBlock(world, i + 8, j + 1, k + 3, Blocks.stonebrick, 2);
		this.setBlock(world, i + 8, j + 1, k + 4, Blocks.stonebrick, 2);
		this.setBlock(world, i + 8, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 8, j + 1, k + 6, Blocks.stonebrick, 1);
		this.setBlock(world, i + 8, j + 1, k + 7, Blocks.stonebrick, 1);
		this.setBlock(world, i + 8, j + 1, k + 8, Blocks.mossy_cobblestone, 0);
		this.setBlock(world, i + 8, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 8, j + 2, k + 3);
		world.setBlockToAir(i + 8, j + 2, k + 4);
		world.setBlockToAir(i + 8, j + 2, k + 5);
		world.setBlockToAir(i + 8, j + 2, k + 6);
		world.setBlockToAir(i + 8, j + 2, k + 7);
		this.setBlock(world, i + 8, j + 2, k + 8, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 8, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 8, j + 3, k + 2);
		world.setBlockToAir(i + 8, j + 3, k + 3);
		world.setBlockToAir(i + 8, j + 3, k + 4);
		world.setBlockToAir(i + 8, j + 3, k + 5);
		world.setBlockToAir(i + 8, j + 3, k + 6);
		world.setBlockToAir(i + 8, j + 3, k + 7);
		this.setBlock(world, i + 8, j + 3, k + 8, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 8, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 8, j + 4, k + 2, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 3, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 4, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 5, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 6, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 7, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 8, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 6, Blocks.cobblestone, 0);
		this.setBlock(world, i + 9, j + 1, k + 7, Blocks.cobblestone, 0);
		this.setBlock(world, i + 9, j + 1, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 2, k + 1, Blocks.stonebrick, 2);
		this.setBlock(world, i + 9, j + 2, k + 2, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 3, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 4, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 5, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 6, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 7, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 3, k + 1, Blocks.glowstone, 0);
		this.setBlock(world, i + 9, j + 3, k + 2, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 3, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 4, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 5, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 6, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 7, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 8, Blocks.glowstone, 0);
		this.setBlock(world, i + 9, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 6, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 7, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 2, k + 6, Blocks.wooden_door, 0);
		this.setBlock(world, i + 6, j + 2, k + 7, Blocks.wooden_door, 0);
		this.setBlock(world, i + 6, j + 3, k + 6, Blocks.wooden_door, 0);
		this.setBlock(world, i + 6, j + 3, k + 7, Blocks.wooden_door, 0);
		world.setBlockMetadataWithNotify(i + 6, j + 3, k + 6, 8, 2);
		world.setBlockMetadataWithNotify(i + 6, j + 3, k + 7, 9, 2);
		world.setBlockMetadataWithNotify(i + 8, j + 2, k + 2, 2, 2);

		return true;
	}
}
