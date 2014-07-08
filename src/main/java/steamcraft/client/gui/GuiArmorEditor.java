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

		mc.renderEngine.bindTexture(guitexture);
		int var5 = (width - xSize) / 2;
		int var6 = (height - ySize) / 2;
		drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
	}
}