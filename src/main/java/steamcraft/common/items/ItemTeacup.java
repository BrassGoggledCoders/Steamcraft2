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
 * File created @ [Apr 8, 2014, 6:43:07 PM]
 */
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemTeacup extends ItemFood
{
	private final int healAmount;
	private final float saturation;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		if (this.itemID == ConfigItems.itemTeacupEmpty.itemID)
		{
			this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "teacupempty");
		}
		else if(this.itemID == ConfigItems.itemTeacupFull.itemID)
		{
			this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "teacupfull");
		}
	}

	public ItemTeacup(int id, int healAmount, float saturation, boolean flag)
	{
		super(id, healAmount, saturation, false);
		this.healAmount = healAmount;
		this.saturation = saturation;
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	public ItemTeacup(int id, int amount, boolean flag)
	{
		this(id, amount, 0.6F, flag);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
	{
		if ((player.getHealth() < 20) && (is.getItem() == ConfigItems.itemTeacupFull))
		{
			if (player.getHealth() < (20 - healAmount))
			{
				player.setHealth(healAmount); //+= healAmount;
			} 
			else
			{
				player.setHealth(20);
			}

			is = new ItemStack(ConfigItems.itemTeacupEmpty, 1);
		}

		return is;
	}

	@Override
	public int getHealAmount()
	{
		return this.healAmount;
	}
	
	@Override
	public float getSaturationModifier()
	{
		return this.saturation;
	}
}
