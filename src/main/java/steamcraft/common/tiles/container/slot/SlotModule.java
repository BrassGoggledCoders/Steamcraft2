
package steamcraft.common.tiles.container.slot;

import boilerplate.common.containers.slots.SlotChanged;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import steamcraft.api.item.IModule;
import steamcraft.api.item.IModuleContainer;

/**
 * @author decebaldecebal
 *
 */
public class SlotModule extends SlotChanged
{
	public SlotModule(IInventory inv, int par2, int par3, int par4)
	{
		super(inv, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		if ((stack == null) || !(stack.getItem() instanceof IModule) || (this.inventory.getStackInSlot(0) == null)
				|| !(this.inventory.getStackInSlot(0).getItem() instanceof IModuleContainer))
		{
			return false;
		}
		IModule iModule = (IModule) stack.getItem();
		IModuleContainer iModuleContainer = (IModuleContainer) this.inventory.getStackInSlot(0).getItem();
		return iModuleContainer.isModuleAllowed(iModule, this.inventory.getStackInSlot(0));
	}
}
