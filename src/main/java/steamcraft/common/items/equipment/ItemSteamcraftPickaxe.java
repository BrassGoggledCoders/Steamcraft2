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
 * File created @ [Mar 20, 2014, 9:51:49 AM]
 */
package steamcraft.common.items.equipment;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import steamcraft.api.SteamcraftApi;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemSteamcraftPickaxe extends ItemPickaxe
{
	public ItemSteamcraftPickaxe(int id, EnumToolMaterial toolMat)
	{
		super(id, toolMat);
	}

	@Override
    public float getStrVsBlock(ItemStack is, Block block) // getStrVsBlock
    {
    	if (this.toolMaterial == SteamcraftApi.toolMatSteam)
		{
			return (4.0F - (((float)is.getItemDamage()) * 11 / 320));
		}
		if (block != null && (block.blockMaterial == Material.iron || block.blockMaterial == Material.anvil || block.blockMaterial == Material.rock))
		{
			super.getStrVsBlock(is, block);
		}

		return this.efficiencyOnProperMaterial;
    	
        //return block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock ? super.func_150893_a(is, block) : this.efficiencyOnProperMaterial;
    }
}