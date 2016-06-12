
package steamcraft.client.gui;

import org.lwjgl.opengl.GL11;

import boilerplate.client.BaseContainerGui;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.container.ContainerCharger;
import steamcraft.common.tiles.energy.TileCharger;

/**
 * @author decebaldecebal
 *
 */
public class GuiCharger extends BaseContainerGui
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/charger.png");

	private TileCharger charger;

	public GuiCharger(InventoryPlayer inventory, TileCharger tile)
	{
		super(new ContainerCharger(inventory, tile));

		this.tile = tile;
		this.charger = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int var8 = this.charger.getEnergyScaled(17);
		this.drawTexturedModalRect(this.guiLeft + 12, (this.guiTop + 64) - var8, 176, 56 - var8, 16, var8 + 1);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		this.drawString(this.fontRendererObj, "Energy: ", 26, 10, -1);
		this.drawString(this.fontRendererObj, this.getEnergyUnits(this.charger.getEnergyStored(ForgeDirection.UNKNOWN)) + "/"
				+ this.getEnergyUnits(this.charger.getMaxEnergyStored(ForgeDirection.UNKNOWN)) + " RF", 30, 20, -1);

		this.drawString(this.fontRendererObj, "Transfer: ", 26, 30, -1);
		this.drawString(this.fontRendererObj, TileCharger.transferRate + " RF", 30, 40, -1);
	}

	private String getEnergyUnits(int number)
	{
		number /= 1000;

		if (number >= 1000)
		{
			number /= 1000;
			return number + "M";
		}

		return number + "K";
	}
}
