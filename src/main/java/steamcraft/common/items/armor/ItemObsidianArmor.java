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

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import steamcraft.common.lib.MaterialHelper;
import steamcraft.common.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Decebaldecebal
 *
 */
public class ItemObsidianArmor extends BaseArmor
{
	public ItemObsidianArmor(int armorType)
	{
		super(MaterialHelper.ARMOR_OBSIDIAN, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return slot == 2 ? ModInfo.PREFIX + "textures/models/armor/obsidian_2.png" : ModInfo.PREFIX + "textures/models/armor/obsidian_1.png";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{
		// Slowness
		player.motionX *= 0.4;
		player.motionZ *= 0.4;
		// Fire resist
		player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20, 5));
		player.extinguish();
		// Sinking
		if(player.isInWater())
			player.motionY--;
	}

}
