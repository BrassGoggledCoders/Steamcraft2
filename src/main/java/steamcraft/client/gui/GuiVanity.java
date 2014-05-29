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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.container.ContainerVanity;
import steamcraft.common.container.InventoryVanity;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class GuiVanity.
 *
 * @author warlordjones
 */
@SideOnly(Side.CLIENT)
public class GuiVanity extends GuiContainer
{
	/**
	 * x size of the inventory window in pixels. Defined as float, passed as int
	 */
	@SuppressWarnings("unused")
	private float xSize_lo;

	/**
	 * y size of the inventory window in pixels. Defined as float, passed as
	 * int.
	 */
	@SuppressWarnings("unused")
	private float ySize_lo;

	/** The Constant iconLocation. */
	private static final ResourceLocation iconLocation = new ResourceLocation(
			LibInfo.PREFIX + "textures/gui/vanity.png");

	/** The inventory. */
	private final InventoryVanity inventory;

	/**
	 * Instantiates a new gui vanity.
	 *
	 * @param player the player
	 * @param inventoryPlayer the inventory player
	 * @param inventoryCustom the inventory custom
	 */
	public GuiVanity(final EntityPlayer player,
			final InventoryPlayer inventoryPlayer,
			final InventoryVanity inventoryCustom)
	{
		super(new ContainerVanity(player, inventoryPlayer, inventoryCustom));
		inventory = inventoryCustom;
	}

	/**
	 * Draws the screen and all the components in it.
	 *
	 * @param x the x
	 * @param y the y
	 * @param scale the scale
	 */
	@Override
	public void drawScreen(final int x, final int y, final float scale)
	{
		super.drawScreen(x, y, scale);
		xSize_lo = x;
		ySize_lo = y;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items).
	 *
	 * @param x the x
	 * @param y the y
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(final int x, final int y)
	{
		final String s = inventory.getInventoryName();
		fontRendererObj.drawString(s, xSize - fontRendererObj.getStringWidth(s)
				- 8, 5, 4210752);
		fontRendererObj.drawString("container.inventory", 120, ySize - 92,
				4210752); // TODO: Something happened here
		fontRendererObj.drawString("Thanks, coolAlias!", xSize + 35,
				ySize + 34, 2000);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items).
	 *
	 * @param scale the scale
	 * @param par2 the par2
	 * @param par3 the par3
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(final float scale,
			final int par2, final int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(iconLocation);
		final int x = (width - xSize) / 2;
		final int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
