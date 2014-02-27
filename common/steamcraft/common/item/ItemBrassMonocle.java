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
 * File created @ [Feb 5, 2014, 8:29:30 PM]
 */
package common.steamcraft.common.item;

import common.steamcraft.common.SC2;
import common.steamcraft.common.lib2.CreativeTabsMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author MrArcane111 & general3214
 *
 */

public class ItemBrassMonocle extends ItemArmorMod
{
	public ItemBrassMonocle(int id, EnumArmorMaterial mat, int renderIndex, int armorType, String texture)
	{
		super(id, mat, renderIndex, armorType, texture);
		mat = material;
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabsMod.tabSCItems);
		this.setUnlocalizedName("brassmonocle");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) 
	{
		ModelBiped armorModel = new ModelBiped();
		
		if(itemStack != null)
		{
			if(itemStack.getItem() instanceof ItemBrassMonocle)
			{
				int type = ((ItemArmor)itemStack.getItem()).armorType;

				if(type == 1 || type == 3)
				{
					armorModel = SC2.proxy.getMonocleArmorModel(0);
				} else
				{
					armorModel = SC2.proxy.getMonocleArmorModel(1);
				}
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
				armorModel.heldItemRight = entityLiving.getCurrentItemOrArmor(0) != null ? 1 :0;
				
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