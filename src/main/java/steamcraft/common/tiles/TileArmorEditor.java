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
import java.util.Collections;
import java.util.Iterator;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import steamcraft.common.items.armor.ItemBrassArmor;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import boilerplate.steamapi.item.IArmorModule;
import boilerplate.steamapi.item.ModuleRegistry;

/**
 * @author warlordjones
 * 
 */
public class TileArmorEditor extends BaseTileWithInventory implements IInventory
{
	public TileArmorEditor()
	{
		super(3);
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
	public void updateEntity()
	{
		/*
		 * if(!worldObj.isRemote) { int[] ids = new int[this.inventory.length]; for(int i = 0; i < this.inventory.length; i++) { if(this.inventory[i] != null)
		 * ids[i] = Item.getIdFromItem(this.inventory[i].getItem()); } InitPackets.network.sendToAllAround(new UpdateClientsideInventoryPacket(xCoord, yCoord,
		 * zCoord, ids), new TargetPoint( worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 10)); }
		 */
		ArrayList<String> installedModules = new ArrayList<String>();

		if(this.worldObj.isRemote)
			return;
		// Addition
		if((this.inventory[0] != null) && (this.inventory[0].getItem() instanceof ItemBrassArmor) && (this.inventory[2] != null))
		{
			NBTTagCompound tagCompound = ItemBrassArmor.getOrCreateTagCompound(this.inventory[0]);
			Item armor = this.inventory[0].getItem();
			ItemBrassArmor brassarmor = (ItemBrassArmor) armor;

			for(int f = 0; f < tagCompound.getInteger("moduleCount"); f++)
			{
				installedModules.add(tagCompound.getString("module" + f));
				tagCompound.removeTag("module" + f);
			}
			if((this.inventory[2] != null) && (this.inventory[2].getItem() instanceof IArmorModule))
			{
				IArmorModule module = (IArmorModule) this.inventory[2].getItem();
				ArrayList moduleIncompatibilities = ModuleRegistry.getModuleIncompatibilities(module.getModuleId());
				System.out.print("Incompats:" + moduleIncompatibilities);
				System.out.print("Installed Modules:" + installedModules);
				if(!installedModules.contains(module.getModuleId()))
				{
					if(moduleIncompatibilities == null)
					{
						if(module.getApplicablePiece() == -1)
						{
							installedModules.add(module.getModuleId());
							this.setInventorySlotContents(2, null);
						}
						else if(module.getApplicablePiece() == brassarmor.armorType)
						{
							installedModules.add(module.getModuleId());
							this.setInventorySlotContents(2, null);
						}
					}
					else if(Collections.disjoint(installedModules, moduleIncompatibilities))
					{
						if(module.getApplicablePiece() == -1)
						{
							installedModules.add(module.getModuleId());
							this.setInventorySlotContents(2, null);
						}
						else if(module.getApplicablePiece() == brassarmor.armorType)
						{
							installedModules.add(module.getModuleId());
							this.setInventorySlotContents(2, null);
						}
					}
				}
			}
			Iterator<String> iterator = installedModules.iterator();
			int objects = 0;
			while(iterator.hasNext())
			{
				tagCompound.setString("module" + objects, iterator.next());
				objects++;
			}
			tagCompound.setInteger("moduleCount", objects);
		}
		// Removal
		if((this.inventory[1] != null) && (this.inventory[1].getItem() instanceof ItemBrassArmor))
		{
			NBTTagCompound tagCompound = ItemBrassArmor.getOrCreateTagCompound(this.inventory[1]);
			for(int f = 0; f < tagCompound.getInteger("moduleCount"); f++)
			{
				installedModules.add(tagCompound.getString("module" + f));
				tagCompound.removeTag("module" + f);
			}
			for(int i = 0; i < installedModules.size(); i++)
			{
				Item module = (Item) ModuleRegistry.getModule(installedModules.get(i));
				if((this.inventory[2] == null) && (module != null))
				{
					this.setInventorySlotContents(2, new ItemStack(module));
					installedModules.remove(i);
				}
			}
			Iterator<String> iterator = installedModules.iterator();
			int objects = 0;
			while(iterator.hasNext())
			{
				tagCompound.setString("module" + objects, iterator.next());
				objects++;
			}
			tagCompound.setInteger("moduleCount", objects);
		}
	}
}