
package steamcraft.common.tiles.container.slot;

import boilerplate.common.containers.slots.SlotChanged;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import steamcraft.api.item.IModuleContainer;

/**
 * @author decebaldecebal
 *
 */
public class SlotModuleContainer extends SlotChanged
{
	public SlotModuleContainer(IInventory inv, int par2, int par3, int par4)
	{
		super(inv, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack.getItem() instanceof IModuleContainer;
	}
}
