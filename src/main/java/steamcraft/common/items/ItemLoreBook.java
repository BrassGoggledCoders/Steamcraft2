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

import boilerplate.api.IOpenableGUI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

import java.util.List;

public class ItemLoreBook extends BaseItem implements IOpenableGUI
{
	String author;
	String title;
	String[] pages;

	public ItemLoreBook(String author, String title, String[] pages)
	{
		super();
		this.setMaxStackSize(1);
		this.author = author;
		this.title = title;
		this.pages = pages;
	}

	public static boolean validBookTagContents(NBTTagCompound p_77828_0_)
	{
		if(!ItemWritableBook.func_150930_a(p_77828_0_))
		{
			return false;
		}
		else if(!p_77828_0_.hasKey("title", 8))
		{
			return false;
		}
		else
		{
			String s = p_77828_0_.getString("title");
			return (s != null) && (s.length() <= 16) && p_77828_0_.hasKey("author", 8);
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_)
	{
		if(p_77653_1_.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = p_77653_1_.getTagCompound();
			String s = nbttagcompound.getString("title");

			if(!StringUtils.isNullOrEmpty(s))
			{
				return s;
			}
		}

		return super.getItemStackDisplayName(p_77653_1_);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		if(stack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String s = nbttagcompound.getString("author");

			if(!StringUtils.isNullOrEmpty(s))
			{
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocalFormatted("book.byAuthor", s));
			}
		}
		else
		{
			super.addInformation(stack, player, list, bool);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(world.isRemote)
		{
			player.openGui(Steamcraft.instance, GuiIDs.LORE, world, 0, 0, 0);
		}
		if(stack.getTagCompound() == null)
		{
			NBTTagList bookPages = new NBTTagList();
			for(String page2 : this.pages)
			{
				NBTTagString page = new NBTTagString(page2);
				bookPages.appendTag(page);
			}
			stack.setTagInfo("pages", bookPages);
			stack.setTagInfo("author", new NBTTagString(this.author));
			stack.setTagInfo("title", new NBTTagString("Lore Book: " + this.title));
		}
		return stack;
	}

	@Override
	public boolean getShareTag()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemLoreBook");
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiScreenBook(player, player.getHeldItem(), false);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
}
