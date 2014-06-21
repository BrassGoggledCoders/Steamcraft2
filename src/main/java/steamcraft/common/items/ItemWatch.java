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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemWatch.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public class ItemWatch extends BaseItem {

	/**
	 * Instantiates a new item watch.
	 */
	public ItemWatch() {
		setMaxStackSize(1);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#addInformation(net.minecraft.item.ItemStack,
	 * net.minecraft.entity.player.EntityPlayer, java.util.List, boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(final ItemStack is, final EntityPlayer player,
			@SuppressWarnings("rawtypes") final List list, final boolean flag) {
		if (flag) {
			list.add("");
		} else {
			list.add("On right-click, this item");
		}
		list.add("will display the in-game time");
		list.add("and the real-world time");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.item.Item#onItemRightClick(net.minecraft.item.ItemStack,
	 * net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer)
	 */
	@SuppressWarnings("unused")
	@Override
	public ItemStack onItemRightClick(final ItemStack is, final World world,
			final EntityPlayer player) {
		if (!world.isRemote) {
			final long mcTime = world.getTotalWorldTime();
			final Calendar cal = Calendar.getInstance();
			cal.getTime();
			final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // hours
																		// and
																		// minutes,
																		// subtract
																		// twelve,
																		// ya
																		// pussy!
			// System.out.println(sdf.format(cal.getTime()));
			// formatter:off
			String message = "MC Time: " + mcTime;
			// player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD));
			message = "Real-World Time: " + sdf.format(cal.getTime());
			// player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD));
			// formatter:on
		}

		return is;
	}
}
