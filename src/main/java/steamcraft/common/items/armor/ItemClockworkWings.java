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

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.items.BaseArmor;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author decebaldecebal
 *
 */
public class ItemClockworkWings extends BaseArmor
{
	private static final float hungerPerTick = 100F;

	public ItemClockworkWings(ArmorMaterial mat, int renderIndex, int type)
	{
		super(mat, type, "", ModInfo.PREFIX);
		this.setMaxDamage(0);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (ClientHelper.isShiftKeyDown())
			this.getWrappedDesc(list);
		else
			list.add(ClientHelper.shiftForInfo);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if (!player.capabilities.allowFlying && (player.getFoodStats().getFoodLevel() != 0))
		{
			if (!stack.hasTagCompound())
				stack.setTagCompound(new NBTTagCompound());

			NBTTagCompound tag = stack.getTagCompound();
			boolean shouldBoost = Steamcraft.proxy.isKeyPressed(0);
			boolean wasJumping = tag.getBoolean("isJumping");

			if (shouldBoost)
				if (wasJumping)
					shouldBoost = false;
				else
					tag.setBoolean("isJumping", true);
			else if (wasJumping)
				tag.setBoolean("isJumping", false);

			if ((Steamcraft.proxy.isScreenEmpty()) && (player.posY < 160) && shouldBoost)
			{
				player.addExhaustion(hungerPerTick);

				if (player.motionY > 0.0D)
					player.motionY += 0.3D;
				else
					player.motionY += 0.4D;
			}

			if ((player.motionY < 0.0D) && player.isSneaking() && !player.onGround)
			{
				player.addExhaustion(hungerPerTick / 6);
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
				player.addExhaustion(hungerPerTick / 4);
				player.fallDistance = 0;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		ModelBiped armorModel = new ModelBiped();

		if (itemStack != null)
		{
			armorModel = Steamcraft.proxy.getWingsArmorModel(1);

			if (armorModel != null)
			{
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = (armorSlot == 1) || (armorSlot == 2);
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = (armorSlot == 2) || (armorSlot == 3);
				armorModel.bipedLeftLeg.showModel = (armorSlot == 2) || (armorSlot == 3);
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();

				if (entityLiving instanceof EntityPlayer)
					armorModel.aimedBow = ((EntityPlayer) entityLiving).getItemInUseDuration() > 2;

				return armorModel;
			}
		}

		return null;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return ModInfo.PREFIX + "textures/models/armor/wings.png";
	}
}
