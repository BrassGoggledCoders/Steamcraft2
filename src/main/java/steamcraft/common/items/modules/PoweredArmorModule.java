package steamcraft.common.items.modules;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.init.InitItems;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.items.electric.ElectricItem;
import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.BaseArmorModule;
import boilerplate.steamapi.item.IPoweredModuleHelper;

public abstract class PoweredArmorModule extends BaseArmorModule implements IPoweredModuleHelper
{
	private int steamToConsume = 1;
	private int rfToConsume = 1;

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
			if(!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
			{
				list.add("Module Effect: ");
				this.getWrappedDesc(list, stack);
			}
			if(this instanceof IPoweredModuleHelper)
			{
				PoweredArmorModule module = this;
				list.add("Energy Consumption: " + module.rfToConsume);
				list.add("Steam Consumption: " + module.steamToConsume);
			}
		}
		else
			list.add(ClientHelper.shiftForInfo);
	}

	@Override
	public void setSteamToConsume(int steamToSet)
	{
		this.steamToConsume = steamToSet;
	}

	@Override
	public void setRFToConsume(int rfToSet)
	{
		this.rfToConsume = rfToSet;
	}

	@Override
	public boolean doConsumption(EntityPlayer player, ItemStack stack)
	{
		if(this.isSteamAvailable(player, this.steamToConsume))
		{
			this.consumeSteamFromCanister(player, this.steamToConsume);
			return true;
		}
		if(this.isRFAvailable(player, this.rfToConsume))
		{
			this.consumeRFFromJar(player, this.rfToConsume);
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
