
package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockCustomDoubleSlab extends BlockSlab
{
	String type;
	Block block;
	Block singleslab;

	public BlockCustomDoubleSlab(String type, Block block, Material mat, Block singleslab)
	{
		super(true, mat);
		this.type = type;
		this.block = block;
		this.singleslab = singleslab;
		this.useNeighborBrightness = true;
	}

	@Override
	public String func_150002_b(int p_150002_1_)
	{
		return ModInfo.PREFIX + this.type;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.block.getIcon(side, meta);
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return Item.getItemFromBlock(this.singleslab);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		return new ItemStack(this.singleslab, 1, world.getBlockMetadata(x, y, z));
	}
}
