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

import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileNuclearBoiler;
import steamcraft.common.tiles.container.ContainerNuclearBoiler;
import boilerplate.client.utils.GuiColors;

/**
 * @author decebaldecebal
 * 
 */
public class GuiNuclearBoiler extends GuiContainer
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/steamboiler.png");

	private TileNuclearBoiler tile;

	public GuiNuclearBoiler(InventoryPlayer player, TileNuclearBoiler tile)
	{
		super(new ContainerNuclearBoiler(player, tile));

		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		if(this.tile.isBurning())
		{
			int burnTime = this.tile.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(this.guiLeft + 43, (this.guiTop + 48) - burnTime, 176, 12 - burnTime, 14, burnTime + 2);
		}

		this.drawFluid(new FluidStack(FluidRegistry.getFluid("water"), 0), this.tile.getScaledWaterLevel(60), this.guiLeft + 8, this.guiTop + 18, 20, 60);
		this.drawFluid(new FluidStack(FluidRegistry.getFluid("steam"), 0), this.tile.getScaledSteamLevel(60), this.guiLeft + 74, this.guiTop + 18, 32, 60);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 24, 176, 14, 20, 49);
		this.drawTexturedModalRect(this.guiLeft + 74, this.guiTop + 24, 176, 14, 20, 49);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		if(((y - this.guiTop) >= 18) && ((y - this.guiTop) <= 78))
			if(((x - this.guiLeft) >= 8) && ((x - this.guiLeft) <= 28))
				this.drawFluidInfo(this.tile.waterTank, x, y);
			else if(((x - this.guiLeft) >= 74) && ((x - this.guiLeft) <= 106))
				this.drawFluidInfo(this.tile.steamTank, x, y);
	}

	private void drawFluidInfo(FluidTank tank, int x, int y)
	{
		ArrayList<String> lines = new ArrayList<String>();

		if(tank.getFluid().getFluid() == FluidRegistry.WATER)
			lines.add(GuiColors.LIGHTBLUE + "Water");
		else
			lines.add(GuiColors.GRAY + "Steam");

		lines.add(tank.getFluidAmount() + "/" + tank.getCapacity());

		this.drawHoveringText(lines, x - this.guiLeft, y - this.guiTop, this.fontRendererObj);
	}

	private void drawFluid(FluidStack fluid, int level, int x, int y, int width, int height)
	{
		if((fluid == null) || (fluid.getFluid() == null))
			return;

		IIcon icon = fluid.getFluid().getIcon();
		this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		int fullX = width / 16;
		int fullY = height / 16;
		int lastX = width - (fullX * 16);
		int lastY = height - (fullY * 16);
		int fullLvl = (height - level) / 16;
		int lastLvl = height - level - (fullLvl * 16);
		for(int i = 0; i < fullX; i++)
			for(int j = 0; j < fullY; j++)
				if(j >= fullLvl)
					this.drawCutIcon(icon, x + (i * 16), y + (j * 16), 16, 16, j == fullLvl ? lastLvl : 0);
		for(int i = 0; i < fullX; i++)
			this.drawCutIcon(icon, x + (i * 16), y + (fullY * 16), 16, lastY, fullLvl == fullY ? lastLvl : 0);
		for(int i = 0; i < fullY; i++)
			if(i >= fullLvl)
				this.drawCutIcon(icon, x + (fullX * 16), y + (i * 16), lastX, 16, i == fullLvl ? lastLvl : 0);
		this.drawCutIcon(icon, x + (fullX * 16), y + (fullY * 16), lastX, lastY, fullLvl == fullY ? lastLvl : 0);
	}

	private void drawCutIcon(IIcon icon, int x, int y, int width, int height, int cut)
	{
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, this.zLevel, icon.getMinU(), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + height, this.zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + cut, this.zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x, y + cut, this.zLevel, icon.getMinU(), icon.getInterpolatedV(cut));
		tess.draw();
	}
}
