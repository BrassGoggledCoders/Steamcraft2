/**
 * This class was created by <MrArcane111> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 18, 2014, 7:47:01 PM]
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

// TODO: Auto-generated Javadoc
/**
 * The Class ItemGuideBook.
 * 
 * @author MrArcane111
 */
public class ItemGuideBook extends BaseItem
{

	/**
	 * Instantiates a new item guide book.
	 */
	public ItemGuideBook()
	{
		super();
		setMaxStackSize(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * boilerplate.common.RootItem#addInformation(net.minecraft.item.ItemStack,
	 * net.minecraft.entity.player.EntityPlayer, java.util.List, boolean)
	 */
	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		list.add("Don't worry, this item");
		list.add("doesn't explode ;)");
		list.add("(probably)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.item.Item#onItemRightClick(net.minecraft.item.ItemStack,
	 * net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer)
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (world.isRemote)
		{
			if (player.getCommandSenderName() == "Surseance")
			{
				world.createExplosion(player, player.posX, player.posY, player.posZ, 0, false);
			}
			player.openGui(Steamcraft.instance, GuiIDs.GUI_ID_GUIDE_BOOK, player.worldObj, 0, 0, 0);
		}

		return stack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#getShareTag()
	 */
	@Override
	/** Sends NBT Tag to client. */
	public boolean getShareTag()
	{
		return true;
	}

	/**
	 * Valid book tag pages.
	 * 
	 * @param tagCompound
	 *            the tag compound
	 * @return true, if successful
	 */
	public static boolean validBookTagPages(NBTTagCompound tagCompound)
	{
		if (tagCompound == null)
		{
			return false;
		}
		else if (!tagCompound.hasKey("pages"))
		{
			return false;
		}
		else
		{
			NBTTagList nbttaglist = (NBTTagList) tagCompound.getTag("pages");

			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				// Broken:
				/*
				 * NBTTagString nbttagstring =
				 * (NBTTagString)nbttaglist.tagAt(i);
				 * 
				 * if (nbttagstring.data == null) { return false; }
				 * 
				 * if (nbttagstring.data.length() > 256) { return false; }
				 */
			}
			return true;
		}
	}
}
