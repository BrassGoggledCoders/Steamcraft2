/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 4, 2014, 5:13:56 PM]
 */
package common.steamcraft.mod.common.item;

import common.steamcraft.mod.client.core.helper.SC2_ClientHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 *
 * @author MrArcane111, EntireCraft & general3214
 *
 */
public class ItemCanister extends ItemSC2
{
    public String gas;

	public ItemCanister(int id, int durability, String energy)
	{
		super(id);
		this.maxStackSize = 1;
		this.setMaxDamage(durability);
        gas = energy;
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) 
	{
		if(!SC2_ClientHelper.isShiftKeyDown())
		{
			list.add(SC2_ClientHelper.shiftForInfo);
			return;
		}
		
		list.add("\u00A77"+ (this.getMaxDamage() - itemStack.getItemDamage()) + "/" + this.getMaxDamage() + " " + gas);
	}
}
