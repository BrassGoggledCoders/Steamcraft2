
package steamcraft.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import boilerplate.common.utils.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import steamcraft.common.items.ItemHandbook;
import steamcraft.common.lib.ModInfo;

public class GuiHandbook extends GuiScreen
{
	/**
	 * TODO List
	 *
	 * - Metadata handling
	 *
	 * - Index Pages
	 *
	 * - Entries for the Deeps, entities, etc. Manually added.
	 *
	 * - Hyperlinks
	 *
	 * - Actual docs :P
	 *
	 * - Intro/Getting Started page
	 *
	 * - Searchbar on index pages
	 *
	 * - More prettier GUI
	 *
	 * - Justification of body text
	 *
	 * - Automatic page splitting
	 *
	 * - Slightly bigger GUI
	 *
	 */
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/book.png");
	private int bookImageWidth = 184;
	private int bookImageHeight = 192;

	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;
	private GuiButton buttonHome;
	private GuiButton buttonLast;
	private ArrayList pageButtons = new ArrayList();

	private int currPage = 0;
	private int prevPage = 0;
	private ArrayList pages = new ArrayList();

	// Note to self: These store ITEMSTACKS not ITEMS or BLOCKS. DUMMY.
	public static List modItems = new ArrayList();
	public static List modBlocks = new ArrayList();

	private ItemStack stack;

	public GuiHandbook(ItemStack stack)
	{
		super();
		this.stack = stack;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui()
	{
		ItemHandbook handbook = (ItemHandbook) this.stack.getItem();
		this.currPage = handbook.getCurrentPage(this.stack);
		this.buttonList.clear();
		byte b0 = 2;
		int i = (this.width - this.bookImageWidth) / 2;
		// Add buttons
		this.buttonList.add(this.buttonNextPage = new NextPageButton(1, i + 140, b0 + 154, true));
		this.buttonList.add(this.buttonPreviousPage = new NextPageButton(2, i + 38, b0 + 154, false));
		this.buttonList.add(this.buttonHome = new GuiButton(3, i + 185, b0 + 10, 40, 20, "Home"));
		this.buttonList.add(this.buttonLast = new GuiButton(4, i + 185, b0 + 30, 50, 20, "Previous"));

		// Manually added pages
		this.pages.add(new HandbookPage(StatCollector.translateToLocal("handbook.intro.title"),
				StatCollector.translateToLocal("handbook.intro.documentation")));
				/*
				 * this.pages .add(new
				 * HandbookPage(StatCollector.translateToLocal(
				 * "handbook.gettingstarted.title"), StatCollector
				 * .translateToLocal("handbook.gettingstarted.documentation")));
				 */

		// Auto Add pages for items
		for (int itemsize = 0; itemsize < modItems.size(); itemsize++)
		{
			ItemStack stack = (ItemStack) modItems.get(itemsize);
			Item item = stack.getItem();
			String name = StatCollector.translateToLocal(item.getUnlocalizedName() + ".name");
			String docs = StatCollector.translateToLocal(item.getUnlocalizedName() + ".documentation");
			this.pages.add(new HandbookPage(name, docs));
		}
		// Auto Add pages for blocks
		for (int itemsize = 0; itemsize < modBlocks.size(); itemsize++)
		{
			ItemStack stack = (ItemStack) modBlocks.get(itemsize);
			Block block = Block.getBlockFromItem(stack.getItem());

			String name = StatCollector.translateToLocal(block.getUnlocalizedName() + ".name");
			String docs = StatCollector.translateToLocal(block.getUnlocalizedName() + ".documentation");
			this.pages.add(new HandbookPage(name, docs));
		}
		this.updateButtons();
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int x, int y, float p_73863_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guitexture);
		int k = (this.width - this.bookImageWidth) / 2;
		byte b0 = 2;
		this.drawTexturedModalRect(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);

		HandbookPage page = (HandbookPage) this.pages.get(this.currPage);
		this.fontRendererObj.drawString("\u00A7l" + page.getTitle(), k + 35, 15, 0x00000000);
		String[] wrappedDesc = StringUtils.wrap(page.getDocs(), 25);
		for (int i = 0; i < wrappedDesc.length; i++)
			this.fontRendererObj.drawString(wrappedDesc[i], k + 35, 30 + (i * 10), 0x00000000);

		super.drawScreen(x, y, p_73863_3_);
	}

	private void updateButtons()
	{
		this.buttonNextPage.visible = this.currPage < (this.pages.size() - 1);
		this.buttonPreviousPage.visible = this.currPage > 0;
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.enabled)
		{
			this.prevPage = this.currPage;

			if (button.id == 1)
			{
				if (this.currPage < (this.pages.size() - 1))
				{
					++this.currPage;
				}
			}
			else if (button.id == 2)
			{
				if (this.currPage > 0)
				{
					--this.currPage;
				}
			}
			else if (button.id == 3)
			{
				if (this.currPage > 0)
				{
					this.currPage = 0;
				}
			}
			else if (button.id == 4)
			{
				this.currPage = this.prevPage;
			}

			this.updateButtons();
		}
	}

	/**
	 * Returns true if this GUI should pause the game when it is displayed in
	 * single-player
	 */
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	public void onGuiClosed()
	{
		ItemHandbook handbook = (ItemHandbook) this.stack.getItem();
		handbook.setCurrentPage(this.stack, this.currPage);
	}

	static class HandbookPage
	{
		String title, docs;

		public HandbookPage(String title, String docs)
		{
			this.title = title;
			this.docs = docs;
		}

		public String getTitle()
		{
			return this.title;
		}

		public String getDocs()
		{
			return this.docs;
		}
	}

	@SideOnly(Side.CLIENT)
	static class NextPageButton extends GuiButton
	{
		private final boolean field_146151_o;

		public NextPageButton(int p_i1079_1_, int p_i1079_2_, int p_i1079_3_, boolean p_i1079_4_)
		{
			super(p_i1079_1_, p_i1079_2_, p_i1079_3_, 23, 13, "");
			this.field_146151_o = p_i1079_4_;
		}

		/**
		 * Draws this button to the screen.
		 */
		@Override
		public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
		{
			if (this.visible)
			{
				boolean flag = (p_146112_2_ >= this.xPosition) && (p_146112_3_ >= this.yPosition) && (p_146112_2_ < (this.xPosition + this.width))
						&& (p_146112_3_ < (this.yPosition + this.height));
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				p_146112_1_.getTextureManager().bindTexture(guitexture);
				int k = 0;
				int l = 192;

				if (flag)
				{
					k += 23;
				}

				if (!this.field_146151_o)
				{
					l += 13;
				}

				this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 23, 13);
			}
		}
	}

	public int getPageIndexFromTitle(String string)
	{
		for (int x = 0; x < this.pages.size(); x++)
		{
			String title = ((HandbookPage) this.pages.get(x)).getTitle();
			if (string.equals(title))
				return x;
		}
		return 0;
	}
}
