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

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 * 
 */
public class ItemGuideBook extends BaseItem
{
	public ItemGuideBook()
	{
		super();
		this.setMaxStackSize(1);
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		list.add("Don't worry, this item");
		list.add("doesn't explode ;)");
		list.add("(probably)");
	}
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (world.isRemote)
		{
			if (player.getCommandSenderName() == "Surseance")
				world.createExplosion(player, player.posX, player.posY, player.posZ, 0, false);
			player.openGui(Steamcraft.instance, GuiIDs.GUI_ID_GUIDE_BOOK, player.worldObj, 0, 0, 0);
		}

		return stack;
	}


	@Override
	public boolean getShareTag()
	{
		return true;
	}

	public static boolean validBookTagPages(NBTTagCompound tagCompound)
	{
		if (tagCompound == null)
			return false;
		else if (!tagCompound.hasKey("pages"))
			return false;
		else
		{
			NBTTagList nbttaglist = (NBTTagList) tagCompound.getTag("pages");

			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				// Broken:
				/*
				 * NBTTagString nbttagstring = (NBTTagString)nbttaglist.tagAt(i); if (nbttagstring.data == null) { return false; } if
				 * (nbttagstring.data.length() > 256) { return false; }
				 */
			}
			return true;
		}
	}
}
