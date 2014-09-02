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

import net.minecraft.inventory.IInventory;
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
		super((byte) 44);
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
		if(this.inventory[0] != null && this.inventory[0].getItem() instanceof ItemBrassArmor)
			for(int i = 1; i < 17; i++)
				if(this.inventory[i] != null)
				{
					ItemBrassArmor armor = (ItemBrassArmor) this.inventory[0].getItem();
					ItemStack armorstack = this.inventory[0];
					IArmorModule module = (IArmorModule) this.inventory[i].getItem();
					if(!armor.modules.contains(module))
						if(module.getApplicablePiece() != -1)
						{
							if(armor.armorType == module.getApplicablePiece())
								armor.modules.add(module);
								armor.moduleNames.add(module.getName());
								if(!armorstack.hasTagCompound())
									armorstack.setTagCompound(new NBTTagCompound());
								armor.writeToNBT(armorstack.stackTagCompound);
								armor.readFromNBT(armorstack.stackTagCompound);
						}
						else
							armor.modules.add(module);
							armor.moduleNames.add(module.getName());
				}
	}

}
