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
 * File created @ [Jun 23, 2014, 10:51:48 PM]
 */
package steamcraft.common.items.armor;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.items.ItemCanister;
import boilerplate.client.ClientHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Decebaldecebal
 */
public class ItemSteamJetpack extends BaseArmor
{
	private final byte steamPerTick;

	public ItemSteamJetpack(ArmorMaterial mat, int renderIndex, int armorType, byte steam)
	{
		super(mat, renderIndex, armorType);
		this.setMaxDamage(0);
		this.steamPerTick = steam;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());

		list.add("Canister Detected: " + String.valueOf(itemStack.getTagCompound().getBoolean("hasCanister")));
		
		if (descNeedsShift)
		{
			if (ClientHelper.isShiftKeyDown())
			{
				getWrappedDesc(list);
			}
			else
				list.add(ClientHelper.shiftForInfo);
		}
		else
		{
			getWrappedDesc(list);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		NBTTagCompound tag = itemStack.getTagCompound();
		
		boolean hasCanister = false;

		if (this.hasCanister(player))
			hasCanister = true;

		if(hasCanister!=tag.getBoolean("hasCanister"))
		{
			tag.setBoolean("hasCanister", hasCanister);
			itemStack.setTagCompound(tag);
		}
		
		if (!player.capabilities.allowFlying && hasCanister)
		{
			if ((Minecraft.getMinecraft().currentScreen == null) && player.posY < 200 && Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()))
			{
				consumeSteamFromCanister(player, steamPerTick);
				
				if (player.motionY > 0.0D)
				{
					player.motionY += 0.08499999910593033D;
				}
				else
				{
					player.motionY += 0.11699999910593033D;
				}

				world.spawnParticle("smoke", player.posX, player.posY - 0.25D, player.posZ, 0.0D, 0.0D, 0.0D);

			}
			
			if (this == ConfigItems.itemSteamWingpack && player.motionY < 0.0D && player.isSneaking())
			{
				consumeSteamFromCanister(player, (byte)(steamPerTick/2));
				player.motionY /= 1.4D;
				
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
			}
			
			if (!player.onGround)
			{
				player.motionX *= 1.04D;
				player.motionZ *= 1.04D;
			}

			if (player.fallDistance > 0)
			{
				consumeSteamFromCanister(player, (byte)(steamPerTick/4));
				player.fallDistance = 0;
			}
		}
	}
	
	protected void consumeSteamFromCanister(EntityPlayer player, byte steam)
	{
		ItemStack[] mainInv = player.inventory.mainInventory;

		for (ItemStack element : mainInv)
		{
			if ((element != null) && (element.getItem() == ConfigItems.itemCanisterSteam))
			{
				ItemCanister canister = (ItemCanister) element.getItem();
				
				if (canister.getFluidAmount(element) > steam)
				{
					canister.drain(element, steam, true);

					return ;
				}
			}
		}
	}

	protected boolean isCanisterEmpty(ItemStack stack)
	{
		ItemCanister canister = (ItemCanister) stack.getItem();

		if (canister.getFluidAmount(stack) <= steamPerTick)
			return true;
		else
			return false;
	}

	protected boolean hasCanister(EntityPlayer player)
	{
		boolean hasCanister = false;
		for (int i = 0; i != player.inventory.mainInventory.length; i++)
		{
			ItemStack[] mainInv = player.inventory.mainInventory;
			if ((mainInv[i] != null) && (mainInv[i].getItem() == ConfigItems.itemCanisterSteam))
			{
				hasCanister = hasCanister || !this.isCanisterEmpty(mainInv[i]);
			}
		}
		return hasCanister;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) 
	{
		ModelBiped armorModel = new ModelBiped();

		if(itemStack != null)
		{
			if(this == ConfigItems.itemSteamJetpack)
			{
				armorModel = Steamcraft.proxy.getWingsArmorModel(0);
			} 
			else
			{
				armorModel = Steamcraft.proxy.getWingsArmorModel(1);
			}
			
			if(armorModel != null)
			{
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				
				//armorModel.heldItemRight = entityLiving.getCurrentItemOrArmor(0) != null ? 1 :0;

				if(entityLiving instanceof EntityPlayer)
				{
					armorModel.aimedBow = ((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
				}

				return armorModel;
			}
		}

		return null;
	}
}
