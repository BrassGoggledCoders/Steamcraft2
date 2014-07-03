/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Mar 20, 2014, 12:54:45 PM]
 */
package steamcraft.common.items.armor;

import net.minecraft.item.ItemArmor;
import steamcraft.common.Steamcraft;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemMonocle.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ItemMonocle extends BaseArmor
{

	/**
	 * Instantiates a new item monocle.
	 *
	 * @param armorMat
	 *            the armor mat
	 * @param armorType
	 *            the armor type
	 * @param renderIndex
	 *            the render index
	 */
	public ItemMonocle(final ItemArmor.ArmorMaterial armorMat, final int armorType, final int renderIndex)
	{
		super(armorMat, armorType, renderIndex);
		setMaxStackSize(1);
	}

	/*
	 * @Override
	 *
	 * @SideOnly(Side.CLIENT) public ModelBiped getArmorModel(EntityLivingBase
	 * living, ItemStack is, int slot) { ModelBiped armorModel = new
	 * ModelBiped();
	 *
	 * if (is != null) { if (is.getItem() instanceof ItemMonocle) { int type =
	 * ((ItemArmor)is.getItem()).armorType;
	 *
	 * if (type == 1 || type == 3) { armorModel =
	 * Steamcraft.proxy.getMonocleArmorModel(0); } else { armorModel =
	 * Steamcraft.proxy.getMonocleArmorModel(1); } }
	 *
	 * if (armorModel != null) { armorModel.bipedHead.showModel = slot == 0;
	 * armorModel.bipedHeadwear.showModel = slot == 0;
	 * armorModel.bipedBody.showModel = slot == 1 || slot == 2;
	 * armorModel.bipedRightArm.showModel = slot == 1;
	 * armorModel.bipedLeftArm.showModel = slot == 1;
	 * armorModel.bipedRightLeg.showModel = slot == 2 || slot == 3;
	 * armorModel.bipedLeftLeg.showModel = slot == 2 || slot == 3;
	 * armorModel.isSneak = living.isSneaking(); armorModel.isRiding =
	 * living.isRiding(); armorModel.isChild = living.isChild();
	 * armorModel.heldItemRight = living.getCurrentItemOrArmor(0) != null ? 1 :
	 * 0;
	 *
	 * if (living instanceof EntityPlayer) { armorModel.aimedBow =
	 * ((EntityPlayer)living).getItemInUseDuration() > 2; }
	 *
	 * return armorModel; } }
	 *
	 * return null; }
	 */
}
