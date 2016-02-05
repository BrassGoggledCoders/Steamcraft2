
package steamcraft.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.container.ContainerArmorEditor;

/**
 * @author warlordjones
 *
 */
public class GuiArmorEditor extends BaseEntityRenderGUI
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/armoreditor.png");
	private float xSizeFloat;
	private float ySizeFloat;

	public GuiArmorEditor(InventoryPlayer player, TileArmorEditor tile)
	{
		super(new ContainerArmorEditor(player, tile));
		this.tile = tile;
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		this.xSizeFloat = p_73863_1_;
		this.ySizeFloat = p_73863_2_;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
		renderEntity(x + 33, y + 75, 30, (x + 33) - this.xSizeFloat, (y + 75) - 50 - this.ySizeFloat, this.mc.thePlayer);
	}
}