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

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import steamcraft.common.items.armor.ItemBrassArmor;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import boilerplate.steamapi.item.IArmorModule;

/**
 * @author warlordjones
 *
 */
public class TileArmorEditor extends BaseTileWithInventory implements IInventory
{
	public TileArmorEditor()
	{
		super(22);
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
		if(this.worldObj.isRemote) return;
		if(this.inventory[0] != null && this.inventory[0].getItem() instanceof ItemBrassArmor && inventory[1] != null)
		{
			Item armor = inventory[0].getItem();
			NBTTagCompound tagCompound = ItemBrassArmor.getOrCreateTagCompound(inventory[0]);

			ArrayList<String> installedModules = new ArrayList<String>();

			for(int f = 0; f < tagCompound.getInteger("moduleCount"); f++)
			{
				installedModules.add(tagCompound.getString("module" + f));
				tagCompound.removeTag("module" + f);
			}

			for(int i = 1; i < 17; i++)
			{
				if(this.inventory[i] != null && inventory[i].getItem() instanceof IArmorModule)
				{
					IArmorModule module = (IArmorModule)inventory[i].getItem();

					if (module instanceof IArmorModule && !installedModules.contains(module.getModuleId()))
					{
						installedModules.add(module.getModuleId());
						setInventorySlotContents(i, null);
					}
				}
			}

			Iterator<String> iterator = installedModules.iterator();
			int objects = 0;
			while (iterator.hasNext())
			{
				tagCompound.setString("module" + objects, iterator.next());
				objects++;
			}
			tagCompound.setInteger("moduleCount", objects);
		}
	}

}