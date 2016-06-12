
package steamcraft.client.gui;

import org.lwjgl.opengl.GL11;

import boilerplate.client.BaseContainerGui;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileLiquidBoiler;
import steamcraft.common.tiles.container.ContainerLiquidBoiler;

/**
 * @author decebaldecebal
 *
 */
public class GuiLiquidBoiler extends BaseContainerGui
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/liquidboiler.png");

	private TileLiquidBoiler boiler;

	public GuiLiquidBoiler(InventoryPlayer player, TileLiquidBoiler tileLiquidBoiler)
	{
		super(new ContainerLiquidBoiler(player, tileLiquidBoiler));

		this.boiler = tileLiquidBoiler;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		if (this.boiler.isBurning())
		{
			int burnTime = this.boiler.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(this.guiLeft + 43, (this.guiTop + 48) - burnTime, 176, 12 - burnTime, 14, burnTime + 2);
		}

		this.drawFluid(FluidRegistry.getFluid("water"), this.boiler.getScaledWaterLevel(60), this.guiLeft + 8, this.guiTop + 9, 20, 60);
		this.drawFluid(FluidRegistry.getFluid("whaleoil"), this.boiler.getScaledFuelLevel(60), this.guiLeft + 40, this.guiTop + 9, 20, 60);
		this.drawFluid(FluidRegistry.getFluid("steam"), this.boiler.getScaledSteamLevel(60), this.guiLeft + 74, this.guiTop + 9, 32, 60);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 15, 176, 14, 20, 49);
		this.drawTexturedModalRect(this.guiLeft + 40, this.guiTop + 15, 176, 14, 20, 49);
		this.drawTexturedModalRect(this.guiLeft + 74, this.guiTop + 15, 176, 14, 20, 49);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		if (((y - this.guiTop) >= 18) && ((y - this.guiTop) <= 78))
			if (((x - this.guiLeft) >= 8) && ((x - this.guiLeft) <= 28))
				this.drawFluidInfo(this.boiler.waterTank, x, y);
			else if (((x - this.guiLeft) >= 74) && ((x - this.guiLeft) <= 106))
				this.drawFluidInfo(this.boiler.steamTank, x, y);
		// else if (((x - this.guiLeft) >= 40) && ((x - this.guiLeft) <= 60))
		// this.drawFluidInfo(this.boiler.fuelTank, x, y);
	}
}
