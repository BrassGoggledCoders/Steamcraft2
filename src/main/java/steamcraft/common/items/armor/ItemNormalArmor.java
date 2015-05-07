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

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.MaterialHelper;
import steamcraft.common.lib.ModInfo;

/**
 * @author Decebaldecebal
 *
 */
public class ItemNormalArmor extends BaseArmor
{
	public ItemNormalArmor(ArmorMaterial mat, int renderIndex, int armorType)
	{
		super(mat, renderIndex, armorType);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		this.itemIcon = icon.registerIcon(ModInfo.PREFIX + "armor/" + this.getUnlocalizedName().substring(5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		if(this.getArmorMaterial() == MaterialHelper.ARMOR_ETHERIUM)
			return slot == 2 ? ModInfo.PREFIX + "textures/armor/etherium_2.png" : ModInfo.PREFIX + "textures/armor/etherium_1.png";
		else
			return slot == 2 ? ModInfo.PREFIX + "textures/armor/obsidian_2.png" : ModInfo.PREFIX + "textures/armor/obsidian_1.png";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{
		if((this.getArmorMaterial() == MaterialHelper.ARMOR_OBSIDIAN) && !player.capabilities.isCreativeMode)
		{
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 3));
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20, 1));
			if(player.isInWater())
			{
				player.motionY--;
			}
		}
	}

}
