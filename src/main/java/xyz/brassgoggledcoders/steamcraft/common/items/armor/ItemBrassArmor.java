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
package xyz.brassgoggledcoders.steamcraft.common.items.armor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.ISpecialArmor;

import boilerplate.common.baseclasses.items.BaseArmor;
import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;
import xyz.brassgoggledcoders.steamcraft.api.item.IArmorModule;
import xyz.brassgoggledcoders.steamcraft.api.item.IDefensiveArmorModule;
import xyz.brassgoggledcoders.steamcraft.api.item.IModule;
import xyz.brassgoggledcoders.steamcraft.api.item.IModuleContainer;
import xyz.brassgoggledcoders.steamcraft.api.item.ModuleRegistry;
import xyz.brassgoggledcoders.steamcraft.api.item.IArmorModule.EnumArmorEffectType;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import xyz.brassgoggledcoders.steamcraft.common.init.InitItems;
import xyz.brassgoggledcoders.steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
@Optional.InterfaceList({ @Optional.Interface(iface = "thaumcraft.api.IGoggles", modid = "Thaumcraft"),
		@Optional.Interface(iface = "thaumcraft.api.nodes.IRevealer", modid = "Thaumcraft") })
public class ItemBrassArmor extends BaseArmor implements ISpecialArmor, IGoggles, IRevealer, IModuleContainer
{

	public ItemBrassArmor(ItemArmor.ArmorMaterial armorMat, int renderIndex, int armorType)
	{
		super(armorMat, armorType, "brass", ModInfo.PREFIX);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		if (stack != null)
		{
			list.add("Modules:");
			NBTTagCompound nbt = getOrCreateTagCompound(stack);

			for (int i = 0; i < nbt.getInteger("moduleCount"); i++)
			{
				IArmorModule module = (IArmorModule) ModuleRegistry.getModule(nbt.getString("module" + i));

				if (module != null)
				{
					list.add(module.getName());
				}
			}
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{
		// int combinedModuleWeight = 0;

		NBTTagCompound nbt = getOrCreateTagCompound(is);

		for (int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = (IArmorModule) ModuleRegistry.getModule(nbt.getString("module" + i));

			if ((module != null) && (module.getArmorEffectType() == EnumArmorEffectType.ONTICK))
			{
				module.applyModuleEffect(world, player, is);
				// combinedModuleWeight += module.getModuleWeight();
			}
		}
		// if(this.getSlownessLevelFromWeight(combinedModuleWeight) >= 1)
		// player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 5,
		// this.getSlownessLevelFromWeight(combinedModuleWeight), true));
	}

	public static NBTTagCompound getOrCreateTagCompound(ItemStack is)
	{
		if (!is.hasTagCompound())
		{
			is.setTagCompound(new NBTTagCompound());
			is.getTagCompound().setInteger("moduleCount", 0);
		}

		return is.getTagCompound();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen,
			int mouseX, int mouseY)
	{
		NBTTagCompound nbt = getOrCreateTagCompound(stack);

		for (int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = (IArmorModule) ModuleRegistry.getModule(nbt.getString("module" + i));

			if ((module != null) && (module.getArmorEffectType() == EnumArmorEffectType.HUD))
			{
				module.applyModuleEffect(player.getEntityWorld(), player, stack);
			}
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
	{
		int priority = 0;
		int absorbRatio = 0;
		int absorbMax = 0;
		NBTTagCompound nbt = getOrCreateTagCompound(armor);

		for (int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = (IArmorModule) ModuleRegistry.getModule(nbt.getString("module" + i));

			if ((module != null) && (module.getArmorEffectType() == EnumArmorEffectType.DEFENSIVE))
			{
				IDefensiveArmorModule defmodule = (IDefensiveArmorModule) module;
				absorbRatio = defmodule.getDamageAbsorbRatio();
				absorbMax = defmodule.getMaxDamageAbsorb();
			}
			if (source.isUnblockable())
			{
				absorbMax = 0;
				absorbRatio = 0;
			}
		}
		return new ArmorProperties(priority, absorbRatio, absorbMax);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
	{
		NBTTagCompound nbt = getOrCreateTagCompound(armor);
		for (int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = (IArmorModule) ModuleRegistry.getModule(nbt.getString("module" + i));

			if ((module != null) && (module.getArmorEffectType() == EnumArmorEffectType.DEFENSIVE))
			{
				IDefensiveArmorModule defmodule = (IDefensiveArmorModule) module;
				return defmodule.getArmorToDisplay();
			}
		}
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return slot == 2 ? ModInfo.PREFIX + "textures/models/armor/" + "brass" + "_2.png"
				: ModInfo.PREFIX + "textures/models/armor/" + "brass" + "_1.png";
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
	{
	}

	@Override
	@Optional.Method(modid = "Thaumcraft")
	public boolean showNodes(ItemStack itemstack, EntityLivingBase player)
	{
		NBTTagCompound nbt = getOrCreateTagCompound(itemstack);
		for (int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = (IArmorModule) ModuleRegistry.getModule(nbt.getString("module" + i));

			if ((module != null) && (module.getArmorEffectType() == EnumArmorEffectType.SPECIAL))
			{
				if (module == InitItems.itemThaumicMonocle)
					return true;
			}
		}
		return false;
	}

	@Override
	@Optional.Method(modid = "Thaumcraft")
	public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player)
	{
		NBTTagCompound nbt = getOrCreateTagCompound(itemstack);
		for (int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = (IArmorModule) ModuleRegistry.getModule(nbt.getString("module" + i));

			if ((module != null) && (module.getArmorEffectType() == EnumArmorEffectType.SPECIAL))
			{
				if (module == InitItems.itemThaumicMonocle)
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean isModuleAllowed(IModule iModule, ItemStack itemStack)
	{
		boolean allowed = false;
		if (itemStack.getItem() instanceof ItemBrassArmor)
		{
			ItemBrassArmor brassArmor = (ItemBrassArmor) itemStack.getItem();
			if (iModule instanceof IArmorModule)
			{
				IArmorModule iArmorModule = (IArmorModule) iModule;
				if ((iArmorModule.getApplicablePiece() == -1) || (iArmorModule.getApplicablePiece() == brassArmor.armorType))
				{
					ArrayList<IModule> moduleIncompatibilities = ModuleRegistry.getModuleIncompatibilities(iModule.getModuleId());
					if ((moduleIncompatibilities == null)
							|| Collections.disjoint(IModuleContainer.Helper.getAllModulesEquipped(itemStack), moduleIncompatibilities))
					{
						if (!IModuleContainer.Helper.getAllModulesEquipped(itemStack).contains(iModule))
						{
							allowed = true;
						}
					}
				}
			}
		}

		return allowed;
	}

	public void onArmorEquipped(World world, EntityPlayer player, ItemStack armor)
	{
		NBTTagCompound nbt = getOrCreateTagCompound(armor);

		for (int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = (IArmorModule) ModuleRegistry.getModule(nbt.getString("module" + i));

			if ((module != null))
			{
				module.onArmorEquipped(world, player);
			}
		}
	}

	public void onArmorUnequipped(World world, EntityPlayer player, ItemStack armor)
	{
		NBTTagCompound nbt = getOrCreateTagCompound(armor);

		for (int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = (IArmorModule) ModuleRegistry.getModule(nbt.getString("module" + i));

			if ((module != null))
			{
				module.onArmorUnequipped(world, player);
			}
		}
	}
}