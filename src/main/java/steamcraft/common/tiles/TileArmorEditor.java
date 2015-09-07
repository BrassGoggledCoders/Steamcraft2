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
package steamcraft.common.tiles;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import boilerplate.api.IOpenableGUI;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import boilerplate.common.tiles.IOnSlotChanged;
import steamcraft.api.item.IArmorModule;
import steamcraft.api.item.IModule;
import steamcraft.api.item.IModuleContainer;
import steamcraft.client.gui.GuiArmorEditor;
import steamcraft.common.tiles.container.ContainerArmorEditor;
import steamcraft.common.tiles.container.slot.SlotModuleContainer;

/**
 * @author warlordjones
 *
 */
public class TileArmorEditor extends BaseTileWithInventory implements IInventory, IOpenableGUI, IOnSlotChanged
{
	public TileArmorEditor()
	{
		super(17);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		return true;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		return true;
	}

	@Override
	public String getInventoryName()
	{
		return "Armor Editor";
	}

	@Override
	public void onSlotChanged(Slot slot)
	{
		if (slot instanceof SlotModuleContainer)
		{
			ItemStack slotItemStack = this.getStackInSlot(slot.getSlotIndex());
			if (slotItemStack == null)
			{
				for (int slotNumber = 1; slotNumber < this.getSizeInventory(); slotNumber++)
				{
					this.setInventorySlotContents(slotNumber, null);
				}
			}
			else if (slotItemStack.getItem() instanceof IModuleContainer)
			{
				ArrayList<IModule> iModules = IModuleContainer.Helper.getAllModulesEquipped(slotItemStack);
				Iterator<IModule> iModuleIterator = iModules.iterator();
				int slotNumber = 1;
				while (iModuleIterator.hasNext())
				{
					this.setInventorySlotContents(slotNumber, new ItemStack((Item) iModuleIterator.next()));
					slotNumber++;
					iModuleIterator.remove();
				}
			}
		}
		else
		{
			ItemStack slotItemStack;
			ArrayList<IModule> iModules = new ArrayList<IModule>();
			for (int slotNumber = 1; slotNumber < this.getSizeInventory(); slotNumber++)
			{
				slotItemStack = this.getStackInSlot(slotNumber);
				if ((slotItemStack != null) && (slotItemStack.getItem() instanceof IModule))
				{
					iModules.add((IModule) slotItemStack.getItem());
				}
			}
			if (this.getStackInSlot(0) != null)
			{
				IModuleContainer.Helper.setModulesEquipped(iModules, this.getStackInSlot(0));
			}
		}
	}

	private void installModule(ArrayList<String> installedModules, IArmorModule module)
	{
		installedModules.add(module.getModuleId());
		module.onModuleAdded(this.worldObj);
		this.setInventorySlotContents(2, null);
	}

	private void removeModule(ArrayList<String> installedModules, IArmorModule module, int modulePos)
	{
		this.setInventorySlotContents(2, new ItemStack((Item) module));
		module.onModuleRemoved(this.worldObj);
		installedModules.remove(modulePos);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiArmorEditor(player.inventory, (TileArmorEditor) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerArmorEditor(player.inventory, (TileArmorEditor) world.getTileEntity(x, y, z));
	}
}