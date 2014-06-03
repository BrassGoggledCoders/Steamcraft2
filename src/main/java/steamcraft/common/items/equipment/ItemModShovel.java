package steamcraft.common.items.equipment;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ItemModShovel extends ItemModTool
{
	public static final Block[] blocksEffectiveAgainst = new Block[] {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow, Blocks.snow_layer, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium};

	public ItemModShovel(ToolMaterial toolMat)
	{
		super(1.0F, toolMat, blocksEffectiveAgainst);
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemstack)
	{
		return block == Blocks.snow ? true : block == Blocks.snow_layer;
	}
}