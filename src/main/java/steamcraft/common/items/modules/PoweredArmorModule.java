package steamcraft.common.items.modules;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import steamcraft.common.init.InitItems;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.items.electric.ElectricItem;
import boilerplate.common.baseclasses.BaseArmorModule;
import boilerplate.steamapi.item.IPoweredModuleHelper;

public abstract class PoweredArmorModule extends BaseArmorModule implements IPoweredModuleHelper
{
	private int steamToConsume = 1;
	private int rfToConsume = 1;

	@Override
	public void setSteamToConsume(int steamToSet)
	{
		steamToConsume = steamToSet;
	}

	@Override
	public void setRFToConsume(int rfToSet)
	{
		rfToConsume = rfToSet;
	}

	@Override
	public boolean doConsumption(EntityPlayer player, ItemStack stack)
	{
		if(isSteamAvailable(player, steamToConsume))
		{
			consumeSteamFromCanister(player, steamToConsume);
			return true;
		}
		if(isRFAvailable(player, rfToConsume))
		{
			consumeRFFromJar(player, rfToConsume);
			return true;
		}
		return false;
	}

	protected void consumeSteamFromCanister(EntityPlayer player, int steamToDrain)
	{
		ItemStack[] mainInv = player.inventory.mainInventory;

		for(ItemStack element : mainInv)
			if((element != null) && (element.getItem() == InitItems.itemCanisterSteam))
			{
				ItemCanister canister = (ItemCanister) element.getItem();

				if(canister.getFluidAmount(element) > steamToDrain)
				{
					canister.drain(element, steamToDrain, true);
				}
			}
	}

	protected boolean isSteamAvailable(EntityPlayer player, int steamToDrain)
	{
		ItemStack[] mainInv = player.inventory.mainInventory;

		for(ItemStack element : mainInv)
		{
			if((element != null) && (element.getItem() == InitItems.itemCanisterSteam))
			{
				ItemCanister canister = (ItemCanister) element.getItem();

				return canister.getFluidAmount(element) >= steamToDrain;
			}
		}
		return false;
	}

	protected void consumeRFFromJar(EntityPlayer player, int rfToDrain)
	{
		ItemStack[] mainInv = player.inventory.mainInventory;

		for(ItemStack element : mainInv)
			if((element != null) && (element.getItem() instanceof ElectricItem))
			{
				ElectricItem jar = (ElectricItem) element.getItem();

				if(jar.getEnergyStored(element) >= rfToDrain)
				{
					jar.extractEnergy(element, rfToDrain, false);
				}
			}
	}

	protected boolean isRFAvailable(EntityPlayer player, int rfToDrain)
	{
		ItemStack[] mainInv = player.inventory.mainInventory;

		for(ItemStack element : mainInv)
		{
			if((element != null) && (element.getItem() instanceof ElectricItem))
			{
				ElectricItem jar = (ElectricItem) element.getItem();

				return jar.getEnergyStored(element) >= rfToDrain;
			}
		}
		return false;
	}
}
