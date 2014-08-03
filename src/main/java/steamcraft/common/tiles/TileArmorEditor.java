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
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import steamcraft.common.items.armor.ItemBrassArmor;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import boilerplate.steamapi.IModule;

/**
 * @author warlordjones
 *
 */
public class TileArmorEditor extends BaseTileWithInventory implements IInventory
{
	public TileArmorEditor()
	{
		super((byte)44);
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
		{
			for(int i=1; i<17;i++)
			{
				if(inventory[i] != null)
				{
					ItemBrassArmor armor = (ItemBrassArmor) this.inventory[0].getItem();
					IModule module = (IModule) this.inventory[i].getItem();
					if(!armor.modules.contains(module))
					armor.modules.add(module);
						//inventory[0].stackTagCompound.setString(/*"module" + i, module.getName()*/"potato", "potato");
							//.setString("module" +i,module.getName());
					//inventory[i] = null;
				}
			}
		}
    }


}
