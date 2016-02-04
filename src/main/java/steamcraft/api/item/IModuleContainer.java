
package steamcraft.api.item;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
