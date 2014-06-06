package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class BaseItemBlockWithMetadata extends ItemBlockWithMetadata
{

	public BaseItemBlockWithMetadata(Block p_i45328_1_, Block block) {
		super(p_i45328_1_, block);
		setHasSubtypes(true);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getMetadata(int)
	 */
	@Override
	public int getMetadata(int metadata)
	{
		return metadata;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.ItemBlock#getUnlocalizedName(net.minecraft.item.ItemStack)
	 */
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}
}
