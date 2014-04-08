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
 * File created @ [Mar 20, 2014, 9:54:34 AM]
 */
package steamcraft.common.items;

import java.util.Calendar;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;

import com.ibm.icu.text.SimpleDateFormat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemWatch extends Item
{
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage)
	{
		return this.itemIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemWatch");
	}

	public ItemWatch()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
	{
		if (flag)
			list.add("");
		else
			list.add("On right-click, this item"); 
			list.add("will display the MC time");
			list.add("and the real-world time");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			long mcTime = world.getTotalWorldTime();
			Calendar cal = Calendar.getInstance();
			cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // hours and minutes, subtract twelve, ya pussy!
			// System.out.println(sdf.format(cal.getTime()));
			String message = "MC Time: " + mcTime;
			//player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD));
			message = "Real-World Time: " + sdf.format(cal.getTime());
			//player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD));
		}

		return is;
	}
}
