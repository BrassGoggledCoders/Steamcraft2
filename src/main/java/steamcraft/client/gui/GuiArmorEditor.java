/*
 *
 */
package steamcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.container.ContainerArmorEditor;

// TODO: Auto-generated Javadoc
/**
 * The Class GuiArmorEditor.
 */
public class GuiArmorEditor extends GuiContainer
{

	/** The Constant guitexture. */
	private static ResourceLocation guitexture = new ResourceLocation(LibInfo.PREFIX + "textures/gui/armoreditor.png");

	/** The tile. */
	private TileArmorEditor tile;

	/**
	 * Instantiates a new gui armor editor.
	 *
	 * @param player
	 *            the player
	 * @param tile
	 *            the tile
	 */
	public GuiArmorEditor(InventoryPlayer player, TileArmorEditor tile)
	{
		super(new ContainerArmorEditor(player, tile));
		this.tile = tile;
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		this.drawTexturedModalRect(var5 + 8, var6 + 24, 176, 14, 20, 49);
		this.drawTexturedModalRect(var5 + 74, var6 + 24, 176, 14, 20, 49);
	}
}