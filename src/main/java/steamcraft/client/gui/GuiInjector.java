
package steamcraft.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import boilerplate.client.BaseContainerGui;
import org.lwjgl.opengl.GL11;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileInjector;
import steamcraft.common.tiles.container.ContainerInjector;

/**
 * @author decebaldecebal
 *
 */
public class GuiInjector extends BaseContainerGui
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/injector.png");

	private TileInjector tile;

	public GuiInjector(InventoryPlayer player, TileInjector tile)
	{
		super(new ContainerInjector(player, tile));

		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 15, 176, 14, 20, 49);
		this.drawTexturedModalRect(this.guiLeft + 40, this.guiTop + 15, 176, 14, 20, 49);
		this.drawTexturedModalRect(this.guiLeft + 74, this.guiTop + 15, 176, 14, 20, 49);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{

	}
}
