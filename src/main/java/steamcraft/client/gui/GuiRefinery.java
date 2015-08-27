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

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import boilerplate.client.BaseContainerGui;
import boilerplate.client.utils.GuiColors;
import org.lwjgl.opengl.GL11;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileRefinery;
import steamcraft.common.tiles.container.ContainerRefinery;

/**
 * @author decebaldecebal
 *
 */
public class GuiRefinery extends BaseContainerGui
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/refinery.png");

	private TileRefinery refinery;

	public GuiRefinery(InventoryPlayer player, TileRefinery tile)
	{
		super(new ContainerRefinery(player, tile));

		this.refinery = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		if (this.refinery.isBurning())
		{
			int burnTime = this.refinery.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(this.guiLeft + 17, (this.guiTop + 53) - burnTime, 176, 12 - burnTime, 14, burnTime + 2);
		}
		if (this.refinery.isCooking())
		{
			int cookTime = this.refinery.getCookTimeRemainingScaled(12);
			this.drawTexturedModalRect(this.guiLeft + 43, (this.guiTop + 48), 176, 50, cookTime + 1, 16);
		}

		this.drawFluid(new FluidStack(FluidRegistry.getFluid("whaleoil"), 0), this.refinery.getScaledWhaleOilLevel(60), this.guiLeft + 74,
				this.guiTop + 18, 32, 60);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft + 74, this.guiTop + 24, 176, 14, 20, 49);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		if (((y - this.guiTop) >= 18) && ((y - this.guiTop) <= 78))
			if (((x - this.guiLeft) >= 74) && ((x - this.guiLeft) <= 106))
				this.drawFluidInfo(this.refinery.oilTank, x, y);
	}

	@Override
	protected void drawFluidInfo(FluidTank tank, int x, int y)
	{
		ArrayList<String> lines = new ArrayList<String>();

		if (tank.getFluid().getFluid() == FluidRegistry.WATER)
			lines.add(GuiColors.LIGHTBLUE + "Water");
		else
			lines.add(GuiColors.LIGHTGRAY + "Whale Oil");

		lines.add(tank.getFluidAmount() + "/" + tank.getCapacity());

		this.drawHoveringText(lines, x - this.guiLeft, y - this.guiTop, this.fontRendererObj);
	}
}
