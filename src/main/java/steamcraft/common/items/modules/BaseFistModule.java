
package steamcraft.common.items.modules;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.items.BaseItem;
import steamcraft.api.item.IFistModule;
import steamcraft.api.item.ModuleRegistry;

public abstract class BaseFistModule extends BaseItem implements IFistModule
{
	public BaseFistModule()
	{
		super();
		ModuleRegistry.setModuleIncompatibilities(this);
	}

	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (ClientHelper.isShiftKeyDown())
		{
			list.add("Module ID: " + this.getModuleId());
			if (!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
			{
				list.add("Module Effect: ");
				this.getWrappedDesc(list, stack);
			}
			/*
			 * if (this.getEnergyConsumedOnEffect() != 0) list.add(
			 * "Energy Usage on Effect: " + this.getEnergyConsumedOnEffect());
			 * if (this.getSteamConsumedOnEffect() != 0) list.add(
			 * "Steam Usage on Effect: " + this.getSteamConsumedOnEffect());
			 */
		}
		else
			list.add(ClientHelper.shiftForInfo);
	}

	public String getEffectTypeStringFromEnum(EnumModuleEffectType type)
	{
		if (type == EnumModuleEffectType.RIGHTCLICK)
			return "On Right-click";
		else if (type == EnumModuleEffectType.ATTACK)
			return "On Attack";
		else
			return "Error!";
	}
}
