/*
 *
 */
package steamcraft.common.itemblocks;

import java.util.List;

import boilerplate.client.ClientHelper;
import boilerplate.common.utils.StringUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseItemBlockWithMetadata.
 */
public class BaseItemBlockWithMetadata extends ItemBlockWithMetadata
{
	/**
	 * Instantiates a new base item block with metadata.
	 *
	 * @param p_i45328_1_
	 *            the p_i45328_1_
	 * @param block
	 *            the block
	 */
	public BaseItemBlockWithMetadata(Block p_i45328_1_, Block block)
	{
		super(p_i45328_1_, block);
		setHasSubtypes(true);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.item.Item#getMetadata(int)
	 */
	@Override
	public int getMetadata(int metadata)
	{
		return metadata;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.item.ItemBlock#getUnlocalizedName(net.minecraft.item.ItemStack
	 * )
	 */
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}
}
