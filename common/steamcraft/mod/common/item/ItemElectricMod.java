/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 8, 2014, 3:11:43 PM]
 */
package common.steamcraft.mod.common.item;

import java.util.List;

import mekanism.api.EnumColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import universalelectricity.api.UniversalClass;
import universalelectricity.api.energy.UnitDisplay;
import universalelectricity.api.item.ItemElectric;

/**
 * 
 * @author Someone else
 *
 */
@UniversalClass
public class ItemElectricMod extends ItemElectric
{
	public int MAX_ELECTRICITY;
	public int VOLTAGE;
	public static double ENERGY_PER_REDSTONE = 10000.0D;	

	public ItemElectricMod(int id, int maxEnergy, int voltage) 
	{
		super(id);
		this.MAX_ELECTRICITY = maxEnergy;
		this.VOLTAGE = voltage;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag) {
		list.add(EnumColor.AQUA + "Energy: " + EnumColor.GREY + getEnergyDisplay(getEnergy(stack)));
		list.add(EnumColor.AQUA + "Voltage: " + EnumColor.GREY + getVoltage(stack) + "V");
	}

	public ItemStack getUnchargedItem() {
		ItemStack charged = new ItemStack(this);
		charged.setItemDamage(100);
		return charged;
	}
	
	public static String getEnergyDisplay(double energy) {
		return UnitDisplay.getDisplayShort((float)(energy), UnitDisplay.Unit.JOULES);
	}

    @Override
    public long getVoltage(ItemStack itemStack)
    {
        return 120;
    }
	
	@Override
	public long getEnergyCapacity(ItemStack theItem) 
	{
		return this.MAX_ELECTRICITY;
	}
}
