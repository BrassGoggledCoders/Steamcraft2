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
package steamcraft.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

/**
 * Created by Skylar on 8/31/2015.
 */
public interface IModuleContainer
{
	boolean isModuleAllowed(IModule iModule, ItemStack itemStack);

	final class Helper
	{
		public static ArrayList<IModule> getAllModulesEquipped(ItemStack itemStack)
		{
			NBTTagCompound nbtTagCompound = getOrCreateTagCompound(itemStack);

			ArrayList<IModule> moduleList = new ArrayList<IModule>();

			for (int i = 0; i < nbtTagCompound.getInteger("moduleCount"); i++)
			{
				IModule module = ModuleRegistry.getModule(nbtTagCompound.getString("module" + i));

				if (module != null)
				{
					moduleList.add(module);
				}
			}
			return moduleList;
		}

		public static void setModulesEquipped(ArrayList<IModule> iModules, ItemStack itemStack)
		{
			NBTTagCompound nbtTagCompound = getOrCreateTagCompound(itemStack);

			nbtTagCompound.setInteger("moduleCount", iModules.size());
			for (int i = 0; i < iModules.size(); i++)
			{
				nbtTagCompound.setString("module" + i, iModules.get(i).getModuleId());
			}
		}

		private static NBTTagCompound getOrCreateTagCompound(ItemStack is)
		{
			if (!is.hasTagCompound())
			{
				is.setTagCompound(new NBTTagCompound());
				is.getTagCompound().setInteger("moduleCount", 0);
			}

			return is.getTagCompound();
		}
	}

}
