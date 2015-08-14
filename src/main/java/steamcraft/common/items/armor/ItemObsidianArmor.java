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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import boilerplate.common.baseclasses.items.BaseArmor;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitMaterials;
import steamcraft.common.lib.ModInfo;

/**
 * @author Decebaldecebal
 *
 */
public class ItemObsidianArmor extends BaseArmor
{
	public ItemObsidianArmor(int armorType)
	{
		super(InitMaterials.ARMOR_OBSIDIAN, armorType, "obsidian", ModInfo.PREFIX);
		this.setCreativeTab(Steamcraft.tabSC2);
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
		if (player.isInWater())
			player.motionY--;
	}

}
