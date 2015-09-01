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
 */
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
		if (stack == null || !(stack.getItem() instanceof IModule) || this.inventory.getStackInSlot(0) == null ||
				this.inventory.getStackInSlot(0).getItem() instanceof IModuleContainer)
		{
			return false;
		}
		IModule iModule = (IModule)stack.getItem();
		IModuleContainer iModuleContainer = (IModuleContainer)this.inventory.getStackInSlot(0).getItem();
		return iModuleContainer.isModuleAllowed(iModule, this.inventory.getStackInSlot(0));
	}
}
