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
 * File created @ [3 Mar 2014, 07:16:41]
 */
package common.steamcraft.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author warlordjones
 *
 * 3 Mar 201407:16:41
 */
public class SlotBoots extends Slot
{
public SlotBoots(IInventory inventory, int par2, int par3, int par4)
{
super(inventory, par2, par3, par4);
}

/**
* Check if the stack is a valid item for this slot. Always true beside for the armor slots
* (and now also not always true for our custom inventory slots)
*/
@Override
public boolean isItemValid(ItemStack itemstack)
{

return itemstack.getItem() instanceof Item;//instanceof ItemBoots;
}
/**
* Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
* of armor slots)
*/
public int getSlotStackLimit()
{
return 1;
}
}