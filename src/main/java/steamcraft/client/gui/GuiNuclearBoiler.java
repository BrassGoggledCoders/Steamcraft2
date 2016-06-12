
package steamcraft.client.gui;

import org.lwjgl.opengl.GL11;

import boilerplate.client.BaseContainerGui;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileNuclearBoiler;
import steamcraft.common.tiles.container.ContainerBaseBoiler;

/**
 * @author decebaldecebal
 *
 */
public class GuiNuclearBoiler extends BaseContainerGui
{
	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/steamboiler.png");

	private TileNuclearBoiler boiler;

	public GuiNuclearBoiler(InventoryPlayer player, TileNuclearBoiler tile)
	{
		super(new ContainerBaseBoiler(player, tile));

		this.boiler = tile;
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

		this.drawFluid(FluidRegistry.getFluid("water"), this.boiler.getScaledWaterLevel(60), this.guiLeft + 8, this.guiTop + 18, 20, 60);
		this.drawFluid(FluidRegistry.getFluid("steam"), this.boiler.getScaledSteamLevel(60), this.guiLeft + 74, this.guiTop + 18, 32, 60);

		this.mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 24, 176, 14, 20, 49);
		this.drawTexturedModalRect(this.guiLeft + 74, this.guiTop + 24, 176, 14, 20, 49);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		if (((y - this.guiTop) >= 18) && ((y - this.guiTop) <= 78))
			if (((x - this.guiLeft) >= 8) && ((x - this.guiLeft) <= 28))
				this.drawFluidInfo(this.boiler.waterTank, x, y);
			else if (((x - this.guiLeft) >= 74) && ((x - this.guiLeft) <= 106))
				this.drawFluidInfo(this.boiler.steamTank, x, y);
	}
}
