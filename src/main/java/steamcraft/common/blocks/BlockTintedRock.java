
package steamcraft.common.blocks;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import boilerplate.common.baseclasses.blocks.BaseBlock;

public class BlockTintedRock extends BaseBlock
{
	public BlockTintedRock(Material mat)
	{
		super(mat);
		this.setHarvestLevel("pickaxe", 0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> possibleDrops = new ArrayList<ItemStack>();
		possibleDrops.add(new ItemStack(Items.gold_nugget, world.rand.nextInt(6)));
		possibleDrops.add(new ItemStack(Items.diamond, world.rand.nextInt(2)));
		possibleDrops.add(new ItemStack(Items.skull, world.rand.nextInt(1)));
		possibleDrops.add(new ItemStack(Blocks.tnt, world.rand.nextInt(6)));
		possibleDrops.add(new ItemStack(Items.iron_ingot, world.rand.nextInt(6)));
		possibleDrops.add(new ItemStack(Items.record_11, world.rand.nextInt(1)));
		ArrayList toDrop = new ArrayList();
		toDrop.add(possibleDrops.get(world.rand.nextInt(possibleDrops.size())));
		toDrop.add(possibleDrops.get(world.rand.nextInt(possibleDrops.size())));
		return toDrop;
	}
}
