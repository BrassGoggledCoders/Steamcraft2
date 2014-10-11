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

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import steamcraft.common.InitItems;
import steamcraft.common.Steamcraft;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.items.electric.ElectricItem;
import steamcraft.common.lib.LibInfo;
import boilerplate.steamapi.item.IArmorModule;
import boilerplate.steamapi.item.IArmorModule.EnumArmorEffectType;
import boilerplate.steamapi.item.ModuleRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class ItemBrassArmor extends BaseArmor implements ISpecialArmor
{

	public ItemBrassArmor(ItemArmor.ArmorMaterial armorMat, int renderIndex, int armorType)
	{
		super(armorMat, renderIndex, armorType);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "armor/" + this.getUnlocalizedName().substring(5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return type != null ? LibInfo.PREFIX + "textures/armor/" + type + ".png" : null;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		if(stack != null)
		{
			list.add("Modules:");
			NBTTagCompound nbt = getOrCreateTagCompound(stack);

			for(int i = 0; i < nbt.getInteger("moduleCount"); i++)
			{
				IArmorModule module = ModuleRegistry.getModule(nbt.getString("module" + i));

				if(module != null)
				{
					list.add(module.getName());
				}
			}
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{
		NBTTagCompound nbt = getOrCreateTagCompound(is);

		for(int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = ModuleRegistry.getModule(nbt.getString("module" + i));

			if((module != null) && (module.getArmorEffectType() == EnumArmorEffectType.ONTICK))
			{
				if((module.getSteamConsumedOnEffect() != 0) && this.isSteamAvailable(player, module.getSteamConsumedOnEffect()))
				{
					if(module.applyArmorEffect(world, player, is))
						this.consumeSteamFromCanister(player, module.getSteamConsumedOnEffect());
				}
				if((module.getEnergyConsumedOnEffect() != 0) && this.isRFAvailable(player, module.getEnergyConsumedOnEffect()))
				{
					if(module.applyArmorEffect(world, player, is))
						this.consumeRFFromJar(player, module.getEnergyConsumedOnEffect());
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

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen,
			int mouseX, int mouseY)
	{
		NBTTagCompound nbt = getOrCreateTagCompound(stack);

		for(int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = ModuleRegistry.getModule(nbt.getString("module" + i));

			if((module != null) && (module.getArmorEffectType() == EnumArmorEffectType.HUD))
			{
				if((module.getSteamConsumedOnEffect() != 0) && this.isSteamAvailable(player, module.getSteamConsumedOnEffect()))
				{
					if(module.applyArmorEffect(player.getEntityWorld(), player, stack))
						this.consumeSteamFromCanister(player, module.getSteamConsumedOnEffect());
				}
				if((module.getEnergyConsumedOnEffect() != 0) && this.isRFAvailable(player, module.getEnergyConsumedOnEffect()))
				{
					if(module.applyArmorEffect(player.getEntityWorld(), player, stack))
						this.consumeRFFromJar(player, module.getEnergyConsumedOnEffect());
				}
			}
		}
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

				if(canister.getFluidAmount(element) >= steamToDrain)
				{
					return true;
				}
				else
					return false;
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

				if(jar.getEnergyStored(element) >= rfToDrain)
				{
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
	{
		int priority = 0;
		int absorbRatio = 0;
		int absorbMax = 0;
		NBTTagCompound nbt = getOrCreateTagCompound(armor);

		for(int i = 0; i < nbt.getInteger("moduleCount"); i++)
		{
			IArmorModule module = ModuleRegistry.getModule(nbt.getString("module" + i));

			if((module != null) && (module.getArmorEffectType() == EnumArmorEffectType.DEFENSIVE))
			{
				absorbRatio = module.getDefenseModifier();
				absorbMax = 2 * module.getDefenseModifier();
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
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) { }
}