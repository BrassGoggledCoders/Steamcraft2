/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [13 Apr 2014, 09:44:47]
 */
package steamcraft.common.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import steamcraft.common.config.ConfigBlocks;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockSlateItem.
 *
 * @author warlordjones
 */
public class BlockSlateItem extends ItemBlock
{
	
	/**
	 * Instantiates a new block slate item.
	 */
	public BlockSlateItem()
	{
		super(ConfigBlocks.blockSlate);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getMetadata(int)
	 */
	@Override
	public int getMetadata(final int metadata)
	{
		return metadata;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.ItemBlock#getUnlocalizedName(net.minecraft.item.ItemStack)
	 */
	@Override
	public String getUnlocalizedName(final ItemStack is)
	{
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}
}
