package common.steamcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import common.steamcraft.common.block.tile.TileEntityCoalGenerator;
import common.steamcraft.common.block.tile.container.ContaineCoalGenerator;
import common.steamcraft.common.lib2.LibInfo;

public class GuiCoalGenerator extends GuiContainer
{
	private static final ResourceLocation guitexture = new ResourceLocation(LibInfo.SC2_PREFIX + "textures/gui/coalgenerator.png");
	private TileEntityCoalGenerator furnaceInventory;

	public GuiCoalGenerator(InventoryPlayer player, TileEntityCoalGenerator tile)
	{
		super(new ContaineCoalGenerator(player, tile));
		furnaceInventory = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString("Inventory", 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		mc.renderEngine.bindTexture(guitexture);
		int var5 = (width - xSize) / 2;
		int var6 = (height - ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);

		if (furnaceInventory.isBurning())
		{
			int burnTime = furnaceInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(var5 + 80, var6 + 50 - burnTime, 176, 12 - burnTime, 14, burnTime + 2);
		}
		if(furnaceInventory.hasEnergy())
		{
			int var8 = furnaceInventory.getEnergyScaled(31);
			this.drawTexturedModalRect(var5 + 32, var6 + 49 - var8, 176, 54 - var8, 16, var8 + 1);
		}
		
	}

}
