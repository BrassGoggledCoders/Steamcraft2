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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
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
	public IIcon getIconFromDamage(final int damage)
	{
		return itemIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(final IIconRegister ir)
	{
		itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemWatch");
	}

	public ItemWatch()
	{
		setMaxStackSize(1);
		setCreativeTab(Steamcraft.tabSC2);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(final ItemStack is, final EntityPlayer player,
			@SuppressWarnings("rawtypes") final List list, final boolean flag)
	{
		if (flag)
		{
			list.add("");
		}
		else
		{
			list.add("On right-click, this item");
		}
		list.add("will display the MC time");
		list.add("and the real-world time");
	}

	@Override
	public ItemStack onItemRightClick(final ItemStack is, final World world, final EntityPlayer player)
	{
		if (!world.isRemote)
		{
			final long mcTime = world.getTotalWorldTime();
			final Calendar cal = Calendar.getInstance();
			cal.getTime();
			final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // hours and minutes, subtract twelve, ya pussy!
			// System.out.println(sdf.format(cal.getTime()));
						@SuppressWarnings("unused")
						String message = "MC Time: " + mcTime;
						//player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD));
						message = "Real-World Time: " + sdf.format(cal.getTime());
						//player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD));
		}

		return is;
	}
}
