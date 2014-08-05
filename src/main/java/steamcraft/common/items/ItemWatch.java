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
package steamcraft.common.items;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;

/**
 * @author Surseance
 * 
 */
public class ItemWatch extends BaseItem
{
	public ItemWatch()
	{
		setMaxStackSize(1);
		setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public ItemStack onItemRightClick(final ItemStack is, final World world, final EntityPlayer player)
	{
		if (!world.isRemote)
		{
			final long mcTime = world.getTotalWorldTime();
			final Calendar cal = Calendar.getInstance();
			cal.getTime();
			final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String message = "MC Time: " + mcTime;
			ChatComponentText component = new ChatComponentText(message);
			component.getChatStyle().setColor(EnumChatFormatting.GOLD);
			player.addChatComponentMessage(component);
			String message1 = "Real-World Time: " + sdf.format(cal.getTime());
			ChatComponentText component1 = new ChatComponentText(message1);
			component1.getChatStyle().setColor(EnumChatFormatting.GOLD);
			player.addChatComponentMessage(component1);
		}

		return is;
	}
}
