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
 * File created @ [Jun 23, 2014, 11:05:48 PM]
 */
package steamcraft.common.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * @author decebaldecebal
 *
 */
public class ItemBlockIntake extends ItemBlock
{
	public ItemBlockIntake(Block block) 
	{
		super(block);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int damageValue) 
	{
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) 
	{
		return getUnlocalizedName();
	}
	
	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		list.add("\u00A77Generates 10 water/tick if a water source block is below it.");
		list.add("\u00A77Consumes the source block every 10 seconds.");
		list.add("\u00A77Automatically outputs to the block above it.");
	}
}
