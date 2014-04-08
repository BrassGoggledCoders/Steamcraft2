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
 * File created @ [Apr 8, 2014, 1:08:24 PM]
 */
package steamcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.container.ContainerVanity;
import steamcraft.common.container.InventoryVanity;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
@SideOnly(Side.CLIENT)
public class GuiVanity extends GuiContainer
{
	/** x size of the inventory window in pixels. Defined as float, passed as int */
	private float xSize_lo;

	/** y size of the inventory window in pixels. Defined as float, passed as int. */
	private float ySize_lo;

	private static final ResourceLocation iconLocation = new ResourceLocation(LibInfo.PREFIX + "textures/gui/vanity.png");

	private final InventoryVanity inventory;

	public GuiVanity(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryVanity inventoryCustom)
	{
		super(new ContainerVanity(player, inventoryPlayer, inventoryCustom));
		this.inventory = inventoryCustom;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int x, int y, float scale)
	{
		super.drawScreen(x, y, scale);
		this.xSize_lo = x;
		this.ySize_lo = y;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = this.inventory.isInvNameLocalized() ? this.inventory.getInvName() : I18n.getString(this.inventory.getInvName());
		this.fontRenderer.drawString(s, this.xSize - this.fontRenderer.getStringWidth(s) - 8, 5, 4210752);
		this.fontRenderer.drawString(I18n.getString("container.inventory"), 120, this.ySize - 92, 4210752);
		this.fontRenderer.drawString("Thanks, coolAlias!", this.xSize + 35, this.ySize + 34, 2000);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(iconLocation);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}
}
