
package steamcraft.common.tiles.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import boilerplate.api.IEnergyItem;

/**
 * @author decebaldecebal
 *
 */
public class SlotCharger extends Slot
{

	public SlotCharger(IInventory inv, int par2, int par3, int par4)
	{
		super(inv, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack.getItem() instanceof IEnergyItem;
	}

	@Override
	public void onSlotChange(ItemStack stack1, ItemStack stack2)
	{
		super.onSlotChange(stack1, stack2);
	}
}
