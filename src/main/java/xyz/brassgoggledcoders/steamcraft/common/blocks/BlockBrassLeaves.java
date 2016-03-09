package xyz.brassgoggledcoders.steamcraft.common.blocks;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.steamcraft.common.init.InitItems;

public class BlockBrassLeaves extends BaseBlock
{
	public BlockBrassLeaves(Material mat)
	{
		super(mat);
		this.setHarvestLevel("pickaxe", 2);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

		if (world.rand.nextInt(30) == 0)
		{
			drop.add(new ItemStack(InitItems.itemIngot, 1 + fortune, 4));
		}
		else
			drop.add(new ItemStack(InitItems.itemNugget, 1 + fortune + world.rand.nextInt(2), 4));
		return drop;
	}
}
