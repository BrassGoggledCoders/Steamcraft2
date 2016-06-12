
package steamcraft.common.items;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import boilerplate.common.baseclasses.items.BaseItem;
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
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public ItemStack onItemRightClick(final ItemStack is, final World world, final EntityPlayer player)
	{
		if (!world.isRemote)
		{
			final long mcTime = world.getWorldTime();
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
