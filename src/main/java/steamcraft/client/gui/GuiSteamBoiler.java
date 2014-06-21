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
 * File created @ 23-May-2014
 */
package steamcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileSteamBoiler;
import steamcraft.common.tiles.container.ContainerSteamBoiler;

// TODO: Auto-generated Javadoc
/**
 * The Class GuiSteamBoiler.
 *
 * @author Decebaldecebal
 */
public class GuiSteamBoiler extends GuiContainer {

	/** The Constant guitexture. */
	private static ResourceLocation guitexture = new ResourceLocation(
			LibInfo.PREFIX + "textures/gui/steamboiler.png");

	/** The tile. */
	private TileSteamBoiler tile;

	/**
	 * Instantiates a new gui steam boiler.
	 *
	 * @param player the player
	 * @param tile the tile
	 */
	public GuiSteamBoiler(InventoryPlayer player, TileSteamBoiler tile) {
		super(new ContainerSteamBoiler(player, tile));
		this.tile = tile;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.client.gui.inventory.GuiContainer#drawGuiContainerForegroundLayer(int, int)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		// fontRenderer.drawString("Inventory", 8, ySize - 96 + 2, 4210752);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.client.gui.inventory.GuiContainer#drawGuiContainerBackgroundLayer(float, int, int)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);

		if (this.tile.isBurning()) {
			int burnTime = this.tile.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(var5 + 43, (var6 + 48) - burnTime, 176,
					12 - burnTime, 14, burnTime + 2);
		}

		this.drawFluid(new FluidStack(FluidRegistry.getFluid("water"), 0),
				this.tile.getScaledWaterLevel(60), var5 + 8, var6 + 18, 20, 60);
		this.drawFluid(new FluidStack(FluidRegistry.getFluid("steam"), 0),
				this.tile.getScaledSteamLevel(60), var5 + 74, var6 + 18, 32, 60);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(var5 + 8, var6 + 24, 176, 14, 20, 49);
		this.drawTexturedModalRect(var5 + 74, var6 + 24, 176, 14, 20, 49);
	}

	/**
	 * Draw fluid.
	 *
	 * @param fluid the fluid
	 * @param level the level
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	private void drawFluid(FluidStack fluid, int level, int x, int y,
			int width, int height) {
		if ((fluid == null) || (fluid.getFluid() == null)) {
			return;
		}

		IIcon icon = fluid.getFluid().getBlock().getIcon(0, 0);
		this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		// RenderUtils.setGLColorFromInt(fluid.getFluid().getColor(fluid));
		int fullX = width / 16;
		int fullY = height / 16;
		int lastX = width - (fullX * 16);
		int lastY = height - (fullY * 16);
		int fullLvl = (height - level) / 16;
		int lastLvl = (height - level) - (fullLvl * 16);
		for (int i = 0; i < fullX; i++) {
			for (int j = 0; j < fullY; j++) {
				if (j >= fullLvl) {
					this.drawCutIcon(icon, x + (i * 16), y + (j * 16), 16, 16,
							j == fullLvl ? lastLvl : 0);
				}
			}
		}
		for (int i = 0; i < fullX; i++) {
			this.drawCutIcon(icon, x + (i * 16), y + (fullY * 16), 16, lastY,
					fullLvl == fullY ? lastLvl : 0);
		}
		for (int i = 0; i < fullY; i++) {
			if (i >= fullLvl) {
				this.drawCutIcon(icon, x + (fullX * 16), y + (i * 16), lastX,
						16, i == fullLvl ? lastLvl : 0);
			}
		}
		this.drawCutIcon(icon, x + (fullX * 16), y + (fullY * 16), lastX,
				lastY, fullLvl == fullY ? lastLvl : 0);
	}

	/**
	 * Draw cut icon.
	 *
	 * @param icon the icon
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param cut the cut
	 */
	private void drawCutIcon(IIcon icon, int x, int y, int width, int height,
			int cut) {
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, this.zLevel, icon.getMinU(),
				icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + height, this.zLevel,
				icon.getInterpolatedU(width), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + cut, this.zLevel,
				icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x, y + cut, this.zLevel, icon.getMinU(),
				icon.getInterpolatedV(cut));
		tess.draw();
	}
}
