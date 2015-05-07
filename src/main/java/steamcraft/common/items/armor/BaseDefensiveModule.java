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
package steamcraft.common.items.armor;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.api.item.IDefensiveArmorModule;
import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.RootItem;

public abstract class BaseDefensiveModule extends RootItem implements IDefensiveArmorModule
{
	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if(ClientHelper.isShiftKeyDown())
		{
			list.add("Module ID: " + this.getModuleId());
			list.add("Applicable Piece: " + this.getArmorPieceNameFromNumber(this.getApplicablePiece()));
			list.add("Effect Type: " + this.getEffectTypeStringFromEnum(this.getArmorEffectType()));
			list.add("Damage Absorbtion Ratio: " + this.getDamageAbsorbRatio());
			list.add("Max Absorbtion: " + this.getMaxDamageAbsorb());
			if(!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
			{
				list.add("Module Effect: ");
				this.getWrappedDesc(list, stack);
			}
			/*
			 * if (this.getEnergyConsumedOnEffect() != 0) list.add("Energy Usage on Effect: " + this.getEnergyConsumedOnEffect()); if
			 * (this.getSteamConsumedOnEffect() != 0) list.add("Steam Usage on Effect: " + this.getSteamConsumedOnEffect());
			 */
		}
		else
			list.add(ClientHelper.shiftForInfo);
	}

	public String getArmorPieceNameFromNumber(int number)
	{
		switch(number)
		{
			case -1:
				return "All";
			case 0:
				return "Helmet";
			case 1:
				return "Chestplate";
			case 2:
				return "Leggings";
			case 3:
				return "Boots";
		}
		return "Error!";
	}

	public String getEffectTypeStringFromEnum(EnumArmorEffectType type)
	{
		if(type == EnumArmorEffectType.ONTICK)
			return "On Equipped Tick";
		else if(type == EnumArmorEffectType.HUD)
			return "HUD Element";
		else if(type == EnumArmorEffectType.DEFENSIVE)
			return "Defensive";
		else
			return "Error!";
	}

	@Override
	public int getMaxDamageAbsorb()
	{
		return 0;
	}

	@Override
	public int getDamageAbsorbRatio()
	{
		return 0;
	}

	@Override
	public int getArmorToDisplay()
	{
		return 0;
	}
}
