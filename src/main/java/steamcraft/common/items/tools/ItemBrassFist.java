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
package steamcraft.common.items.tools;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import steamcraft.api.item.IFistModule;
import steamcraft.api.item.ModuleRegistry;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitItems;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.items.electric.ElectricItem;
import steamcraft.common.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBrassFist extends ItemModTool
{
	public ItemBrassFist(ToolMaterial mat)
	{
		super(mat.getDamageVsEntity(), mat);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(ModInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		if(stack != null)
		{
			list.add("Shift-Right-Click to switch between active right click modules");
			list.add("Modules:");
			NBTTagCompound nbt = getOrCreateTagCompound(stack);

			for(int i = 0; i < nbt.getInteger("moduleCount"); i++)
			{
				IFistModule module = (IFistModule) ModuleRegistry.getModule(nbt.getString("module" + i));

				if(module != null)
				{
					list.add(module.getName());
				}
			}
		}
	}

	public static NBTTagCompound getOrCreateTagCompound(ItemStack is)
	{
		if(!is.hasTagCompound())
		{
			is.setTagCompound(new NBTTagCompound());
			is.getTagCompound().setInteger("moduleCount", 0);
		}

		return is.getTagCompound();
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
