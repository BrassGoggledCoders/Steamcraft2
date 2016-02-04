
package steamcraft.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.common.util.ForgeDirection;

import boilerplate.client.BaseContainerGui;
import org.lwjgl.opengl.GL11;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.container.ContainerBattery;
import steamcraft.common.tiles.energy.TileBattery;

/**
 * @author decebaldecebal
 *
 */
public class GuiBattery extends BaseContainerGui
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/battery.png");

	private TileBattery battery;

	public GuiBattery(InventoryPlayer inventory, TileBattery tile)
	{
		super(new ContainerBattery(inventory, tile));

		this.tile = tile;
		this.battery = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int var8 = this.battery.getEnergyScaled(17);
		this.drawTexturedModalRect(this.guiLeft + 12, (this.guiTop + 64) - var8, 176, 56 - var8, 16, var8 + 1);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		this.drawString(this.fontRendererObj, "Energy: ", 26, 10, -1);
		this.drawString(this.fontRendererObj, this.getEnergyUnits(this.battery.getEnergyStored(ForgeDirection.UNKNOWN)) + "/"
				+ this.getEnergyUnits(this.battery.getMaxEnergyStored(ForgeDirection.UNKNOWN)) + " RF", 30, 20, -1);

		this.drawString(this.fontRendererObj, "Transfer: ", 26, 30, -1);
		this.drawString(this.fontRendererObj, this.battery.transferRate + " RF", 30, 40, -1);
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
