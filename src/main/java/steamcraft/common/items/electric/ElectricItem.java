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
package steamcraft.common.items.electric;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.api.IEnergyItem;
import boilerplate.client.utils.GuiColors;
import boilerplate.common.baseclasses.items.BaseElectricItem;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ElectricItem extends BaseElectricItem
{
	public ElectricItem(int maxEnergy, int maxReceive, int maxSend)
	{
		super(maxEnergy, maxReceive, maxSend);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag)
	{
		super.addInformation(stack, entityplayer, list, flag);
		if (this.maxSend > 0)
		{
			if ((stack.getTagCompound() != null) && stack.getTagCompound().getBoolean("canCharge"))
			{
				list.add(GuiColors.GREEN + "Charging items in inventory...");
				list.add(GuiColors.GREEN + "Sneak + Right Click to turn off.");
			}
			else
				list.add("Sneak + Right Click to charge items in your inventory.");
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (player.isSneaking() && (this.maxSend > 0))
		{
			NBTTagCompound tag = stack.getTagCompound();

			if (tag == null)
				tag = new NBTTagCompound();

			tag.setBoolean("canCharge", !tag.getBoolean("canCharge"));

			stack.setTagCompound(tag);
		}
		return stack;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		if (entity instanceof EntityPlayer)
		{
			NBTTagCompound tag = stack.getTagCompound();

			if (tag != null && tag.getBoolean("canCharge"))
			{
				EntityPlayer player = (EntityPlayer) entity;
				if (this.maxSend > 0)
				{
					int energy = Math.min(this.maxSend, this.getEnergyStored(stack));
					int maxEnergy = energy;

					ItemStack[] mainInv = player.inventory.mainInventory;

					for (ItemStack element : mainInv)
						if ((element != null) && (element != stack) && (element.getItem() instanceof IEnergyItem))
						{
							IEnergyItem container = (IEnergyItem) element.getItem();

							energy -= container.receiveEnergy(element, energy, false);

							if (energy == 0)
								break;
						}

					if ((maxEnergy - energy) > 0)
						this.extractEnergy(stack, maxEnergy - energy, false);
				}
			}
		}
	}
}
