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
 * File created @ [Feb 18, 2014, 7:13:44 PM]
 */
package common.steamcraft.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import common.steamcraft.common.item.ModItems;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author MrArcane111
 *
 */
@SideOnly(Side.CLIENT)
public class GuiGuideBook extends GuiScreen {
	private static final ResourceLocation bookTexture = new ResourceLocation(LibInfo.SC2_PREFIX + "/textures/gui/book.png");

	/** The player editing the book. */
	private final EntityPlayer player;
	private final ItemStack stackBook;

	/** The amount of ticks since the gui was opened. */
	private int updateCount;

	private int WIDTH = 90;
	private int HEIGHT = 90;

	//private int bookTotalPages = 1;
	private int currPage;
	private NBTTagList bookPages;
	private String bookTitle = "SC2 Guide";
	private GuiNextButton buttonNextPage;
	private GuiNextButton buttonPreviousPage;
	private GuiButton buttonDone;

	// 
	private int itemsIndex = 0;
	private int featureIndex = 0;
	private int blocksIndex = 0;
	private int bookTotalPages = itemsIndex + featureIndex + blocksIndex;

	public GuiGuideBook(EntityPlayer player, ItemStack stack) {
		this.player = player;
		this.stackBook = stack;

		/*
		if (stack.hasTagCompound()) {
			NBTTagCompound tagCompound = stack.getTagCompound();
			this.bookPages = tagCompound.getTagList("pages");

			if (this.bookPages != null) {
				this.bookPages = (NBTTagList)this.bookPages.copy();
				this.bookTotalPages = this.bookPages.tagCount();

				if (this.bookTotalPages < 1) {
					this.bookTotalPages = 1;
				}
			}
		}
		/*
        if (this.bookPages == null) {
            this.bookPages = new NBTTagList("pages");
            this.bookPages.appendTag(new NBTTagString("1", ""));
            this.bookTotalPages = 1;
        }
		 */
	}

	@Override
	/** Called from the main game loop to update the screen. */
	public void updateScreen() {
		super.updateScreen();
		++this.updateCount;
	}

	@Override
	/** Adds the buttons (and other controls) to the screen in question. */
	public void initGui() {
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		// Adds the done button to exit the book GUI
		this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 - 100, 4 + this.HEIGHT, 200, 20, I18n.getString("gui.done"))); // XXX Localization parameters
		int averageWidth = (this.width - this.WIDTH) / 2;
		byte byte0 = 2;
		this.buttonList.add(this.buttonNextPage = new GuiNextButton(1, averageWidth + 120, byte0 + 154, true));
		this.buttonList.add(this.buttonPreviousPage = new GuiNextButton(2, averageWidth + 38, byte0 + 154, false));
		this.updateButtons();
	}

	@Override
	/** Called when the screen is unloaded. Used to disable keyboard repeat events. */
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	private void updateButtons() {
		this.buttonNextPage.drawButton = this.currPage < this.bookTotalPages - 1;
		this.buttonPreviousPage.drawButton = this.currPage > 0;
		this.buttonDone.drawButton = this.currPage > 0;
	}

	private void sendBookToServer(boolean flag) {
		if (this.bookPages != null) {
			while (this.bookPages.tagCount() > 1) {
				NBTTagString nbtString = (NBTTagString)this.bookPages.tagAt(this.bookPages.tagCount() - 1);

				if (nbtString.data != null && nbtString.data.length() != 0) {
					break;
				}

				this.bookPages.removeTag(this.bookPages.tagCount() - 1);
			}
			if (this.stackBook.hasTagCompound()) {
				NBTTagCompound tagCompound = this.stackBook.getTagCompound();
				tagCompound.setTag("pages", this.bookPages);
			} else {
				this.stackBook.setTagInfo("pages", this.bookPages);
			}

			String s = "MC|BEdit";

			if (flag) {
				s = "MC|BSign";
				this.stackBook.setTagInfo("author", new NBTTagString("author", "Steamcraft 2"));
				this.stackBook.setTagInfo("title", new NBTTagString("title", this.bookTitle.trim()));
				this.stackBook.itemID = ModItems.guideBook.itemID; // XXX
			}

			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream dataStream = new DataOutputStream(byteStream);

			try {
				Packet.writeItemStack(this.stackBook, dataStream);
				this.mc.getNetHandler().addToSendQueue(new Packet250CustomPayload(s, byteStream.toByteArray()));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	/** Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e). */
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			if (button.id == 0) {
				this.mc.displayGuiScreen((GuiScreen)null);
				this.sendBookToServer(false);
			} else if (button.id == 1) {
				if (this.currPage < this.bookTotalPages - 1) {
					++this.currPage;
				}
			} else if (button.id == 2) {
				if (this.currPage > 0) {
					--this.currPage;
				}
			} 

			this.updateButtons();
		}
	}

	private void addNewPage() {
		if (this.bookPages != null && this.bookPages.tagCount() < 50) {
			this.bookPages.appendTag(new NBTTagString("" + (this.bookTotalPages + 1), ""));
			++this.bookTotalPages;
			//this.bookModified = true;
		}
	}

	private void func_74162_c(char char1, int char2) {
		switch (char2) {
		case 14:
			if (!this.bookTitle.isEmpty()) {
				this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
				this.updateButtons();
			}

			return;
		case 28:
		case 156:
			if (!this.bookTitle.isEmpty()) {
				this.sendBookToServer(true);
				this.mc.displayGuiScreen((GuiScreen)null);
			}

			return;
		default:
			if (this.bookTitle.length() < 16 && ChatAllowedCharacters.isAllowedCharacter(char1)) {
				this.bookTitle = this.bookTitle + Character.toString(char1);
				this.updateButtons();
			}
		}
	}

	private String func_74158_i() {
		if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
			NBTTagString nbttagstring = (NBTTagString)this.bookPages.tagAt(this.currPage);
			return nbttagstring.toString();
		} else
		{
			return "";
		}
	}

	private void func_74159_a(String s) {
		if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
			NBTTagString nbttagstring = (NBTTagString)this.bookPages.tagAt(this.currPage);
			nbttagstring.data = s;
		}
	}

	private void func_74160_b(String par1Str) {
		String s1 = this.func_74158_i();
		String s2 = s1 + par1Str;
		int i = this.fontRenderer.splitStringWidth(s2 + "" + EnumChatFormatting.BLACK + "_", 118);

		if (i <= 118 && s2.length() < 256)
		{
			this.func_74159_a(s2);
		}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int par1, int par2, float par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(bookTexture);
		int k = (this.width - this.WIDTH) / 2;
		byte b0 = 2;
		this.drawTexturedModalRect(k, b0, 0, 0, this.WIDTH, this.HEIGHT);
		String s;
		String s1;
		int l;

		s = String.format(I18n.getString("book.pageIndicator"), new Object[] {Integer.valueOf(this.currPage + 1), Integer.valueOf(this.bookTotalPages)});
		s1 = "";

		if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
			NBTTagString nbttagstring = (NBTTagString)this.bookPages.tagAt(this.currPage);
			s1 = nbttagstring.toString();
		}

		l = this.fontRenderer.getStringWidth(s);
		this.fontRenderer.drawString(s, k - l + this.WIDTH - 44, b0 + 16, 0);
		this.fontRenderer.drawSplitString(s1, k + 36, b0 + 16 + 16, 116, 0);


		super.drawScreen(par1, par2, par3);
	}

	static ResourceLocation resource() {
		return bookTexture;
	}
}
