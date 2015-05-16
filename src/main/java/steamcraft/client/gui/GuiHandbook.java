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
package steamcraft.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

public class GuiHandbook extends GuiScreen
{
	private static ResourceLocation guitexture = new ResourceLocation("textures/gui/book.png");
	private int bookImageWidth = 192;
	private int bookImageHeight = 192;

	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;
	private int bookTotalPages = 1;
	private int currPage;

	public static List modItems = new ArrayList();
	public static List modBlocks = new ArrayList();

	public GuiHandbook()
	{
		super();
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui()
	{
		this.buttonList.clear();
		byte b0 = 2;
		int i = (this.width - this.bookImageWidth) / 2;
		this.buttonList.add(this.buttonNextPage = new NextPageButton(1, i + 120, b0 + 154, true));
		this.buttonList.add(this.buttonPreviousPage = new NextPageButton(2, i + 38, b0 + 154, false));
		updateButtons();
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guitexture);
		int k = (this.width - this.bookImageWidth) / 2;
		byte b0 = 2;
		this.drawTexturedModalRect(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);

		int i = (this.width - this.bookImageWidth) / 2;
		for(int itemsize = 0; itemsize < modItems.size(); itemsize++)
		{
			String s = StatCollector.translateToLocal(((Item) modItems.get(itemsize)).getUnlocalizedName() + ".documentation");
			int l = this.fontRendererObj.getStringWidth(s);
			// this.fontRendererObj.drawString(s, k - l + this.bookImageWidth - 44, b0 + 16, 0);
			this.fontRendererObj.drawSplitString(s, k + 36, b0 + 16 + 16, 116, 0);
		}

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	@SideOnly(Side.CLIENT)
	static class NextPageButton extends GuiButton
	{
		private final boolean field_146151_o;
		private static final String __OBFID = "CL_00000745";

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
			if(this.visible)
			{
				boolean flag = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width
						&& p_146112_3_ < this.yPosition + this.height;
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				p_146112_1_.getTextureManager().bindTexture(guitexture);
				int k = 0;
				int l = 192;

				if(flag)
				{
					k += 23;
				}

				if(!this.field_146151_o)
				{
					l += 13;
				}

				this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 23, 13);
			}
		}
	}

	private void updateButtons()
	{
		this.buttonNextPage.visible = (this.currPage < this.bookTotalPages - 1);
		this.buttonPreviousPage.visible = this.currPage > 0;
	}

}
