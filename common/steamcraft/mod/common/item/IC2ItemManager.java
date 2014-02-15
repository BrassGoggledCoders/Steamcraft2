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
 * File created @ [Feb 8, 2014, 3:09:41 PM]
 */
package common.steamcraft.mod.common.item;

import common.steamcraft.mod.common.core.helper.SC2_CompatibilityChecker;

import ic2.api.item.IElectricItemManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * @author Someone else
 *
 */
public class IC2ItemManager implements IElectricItemManager {
	public ItemSC2Electric energizedItem;

	public static IC2ItemManager getManager(ItemSC2Electric item) {
		IC2ItemManager manager = new IC2ItemManager();
		manager.energizedItem = item;
		return manager;
	}

	@Override
	public int charge(ItemStack itemStack, int amount, int tier, boolean ignoreTransferLimit, boolean simulate) {
		if (this.energizedItem.canReceive(itemStack)) {
			double energyNeeded = this.energizedItem.getMaxEnergy(itemStack) - this.energizedItem.getEnergy(itemStack);
			double energyToStore = Math.min(Math.min(amount / SC2_CompatibilityChecker.UE_TO_IC2, this.energizedItem.getMaxEnergy(itemStack) * 0.01D), energyNeeded);

			if (!simulate) {
				this.energizedItem.setEnergy(itemStack, this.energizedItem.getEnergy(itemStack) + energyToStore);
			}

			return (int)Math.round(energyToStore * SC2_CompatibilityChecker.UE_TO_IC2);
		}

		return 0;
	}

	@Override
	public int discharge(ItemStack itemStack, int amount, int tier, boolean ignoreTransferLimit, boolean simulate) {
		if (this.energizedItem.canSend(itemStack)) {
			double energyWanted = amount / SC2_CompatibilityChecker.UE_TO_IC2;
			double energyToGive = Math.min(Math.min(energyWanted, this.energizedItem.getMaxEnergy(itemStack) * 0.01D), this.energizedItem.getEnergy(itemStack));

			if (!simulate) {
				this.energizedItem.setEnergy(itemStack, this.energizedItem.getEnergy(itemStack) - energyToGive);
			}

			return (int)Math.round(energyToGive * SC2_CompatibilityChecker.UE_TO_IC2);
		}

		return 0;
	}

	@Override
	public boolean canUse(ItemStack itemStack, int amount) {
		return this.energizedItem.getEnergy(itemStack) >= amount / SC2_CompatibilityChecker.UE_TO_IC2;
	}

	@Override
	public int getCharge(ItemStack itemStack) {
		return (int)Math.round(this.energizedItem.getEnergy(itemStack) * SC2_CompatibilityChecker.UE_TO_IC2);
	}

	@Override
	public boolean use(ItemStack itemStack, int amount, EntityLivingBase entity) {
		return false;
	}

	@Override
	public void chargeFromArmor(ItemStack itemStack, EntityLivingBase entity) {}

	@Override
	public String getToolTip(ItemStack itemStack) {
		return null;
	}
}
