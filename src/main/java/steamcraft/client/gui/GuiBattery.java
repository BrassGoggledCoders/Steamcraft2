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
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileBattery;
import steamcraft.common.tiles.container.ContainerBattery;

/**
 * @author Decebaldecebal
 * 
 */
public class GuiBattery extends GuiContainer
{
	private static ResourceLocation guitexture = new ResourceLocation(LibInfo.PREFIX + "textures/gui/battery.png");

	private TileBattery tile;

	public GuiBattery(InventoryPlayer inventory, TileBattery tile)
	{
		super(new ContainerBattery(inventory, tile));

		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int var8 = this.tile.getEnergyScaled(16);
		this.drawTexturedModalRect(this.guiLeft + 12, (this.guiTop + 64) - var8, 176, 56 - var8, 16, var8 + 1);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		this.drawString(this.fontRendererObj, "Energy: ", 26, 10, -1);
		this.drawString(
				this.fontRendererObj,
				this.getEnergyUnits(this.tile.getEnergyStored(ForgeDirection.UNKNOWN)) + "/"
						+ this.getEnergyUnits(this.tile.getMaxEnergyStored(ForgeDirection.UNKNOWN)) + " RF", 30, 20, -1);

		this.drawString(this.fontRendererObj, "Transfer: ", 26, 30, -1);
		this.drawString(this.fontRendererObj, this.tile.transferRate + " RF", 30, 40, -1);
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
