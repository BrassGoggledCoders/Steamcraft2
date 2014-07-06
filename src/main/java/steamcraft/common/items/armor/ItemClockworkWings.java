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

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.client.ClientHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author decebaldecebal
 * 
 */
public class ItemClockworkWings extends BaseArmor
{
	private static final float hungerPerTick = 1F;

	public ItemClockworkWings(ArmorMaterial mat, int renderIndex, int type)
	{
		super(mat, renderIndex, type);
		this.setMaxDamage(0);
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (this.descNeedsShift)
		{
			if (ClientHelper.isShiftKeyDown())
				this.getWrappedDesc(list);
			else
				list.add(ClientHelper.shiftForInfo);
		}
		else
			this.getWrappedDesc(list);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if (!player.capabilities.allowFlying && player.getFoodStats().getFoodLevel() != 0)
		{
			if (Minecraft.getMinecraft().currentScreen == null && player.posY < 160 && Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed())
			{
				player.addExhaustion(hungerPerTick);

				if (player.motionY > 0.0D)
					player.motionY += 0.3D;
				else
					player.motionY += 0.4D;
			}

			if (player.motionY < 0.0D && player.isSneaking())
			{
				player.addExhaustion(hungerPerTick / 2);
				player.motionY /= 1.4D;

				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
			}

			if (!player.onGround)
			{
				player.motionX *= 1.04D;
				player.motionZ *= 1.04D;
			}
			
			player.fallDistance = 0;
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
				armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
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
		return LibInfo.PREFIX + "textures/armor/brasswings.png";
	}
}
