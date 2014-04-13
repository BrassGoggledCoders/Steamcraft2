package steamcraft.client.gui;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileEntitySteamBoiler;
import steamcraft.common.tiles.container.ContainerSteamBoiler;

public class GuiSteamBoiler extends GuiContainer{
	private static final ResourceLocation guitexture = new ResourceLocation(LibInfo.PREFIX + "textures/gui/steamboiler.png");
	private TileEntitySteamBoiler tile;

	public GuiSteamBoiler(InventoryPlayer player, TileEntitySteamBoiler tile)
	{
		super(new ContainerSteamBoiler(player, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		//fontRenderer.drawString("Inventory", 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		mc.renderEngine.bindTexture(guitexture);
		int var5 = (width - xSize) / 2;
		int var6 = (height - ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
		
		if (tile.isBurning())
		{
			int burnTime = tile.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(var5 + 43, var6 + 48 - burnTime, 176, 12 - burnTime, 14, burnTime + 2);
		}
				
		drawFluid(new FluidStack(FluidRegistry.getFluid("water"), 0), tile.getScaledWaterLevel(60), var5 + 8, var6 + 18, 20, 60);
		drawFluid(new FluidStack(FluidRegistry.getFluid("steam"), 0), tile.getScaledSteamLevel(60), var5 + 74, var6 + 18, 32, 60);
		
		mc.renderEngine.bindTexture(guitexture);
		this.drawTexturedModalRect(var5 + 8, var6 + 26, 176, 14, 20, 49);
		this.drawTexturedModalRect(var5 + 74, var6 + 24, 176, 14, 20, 49);
	}
	
	/*
	 * 
	 * Code from BC
	 * IDK what any of it does :)
	 * 
	 */
	private void drawFluid(FluidStack fluid, int level, int x, int y, int width, int height)
	{
		if(fluid == null || fluid.getFluid() == null) {
			return;
		}
		Icon icon = Block.blocksList[fluid.getFluid().getBlockID()].getIcon(0, 0); //Had to do this another way because our steam fluid's icon doesn't appear to register
		mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		setGLColorFromInt(fluid.getFluid().getColor(fluid));
		int fullX = width / 16;
		int fullY = height / 16;
		int lastX = width - fullX * 16;
		int lastY = height - fullY * 16;
		int fullLvl = (height - level) / 16;
		int lastLvl = (height - level) - fullLvl * 16;
		for(int i = 0; i < fullX; i++) {
			for(int j = 0; j < fullY; j++) {
				if(j >= fullLvl) {
					drawCutIcon(icon, x + i * 16, y + j * 16, 16, 16, j == fullLvl ? lastLvl : 0);
				}
			}
		}
		for(int i = 0; i < fullX; i++) {
			drawCutIcon(icon, x + i * 16, y + fullY * 16, 16, lastY, fullLvl == fullY ? lastLvl : 0);
		}
		for(int i = 0; i < fullY; i++) {
			if(i >= fullLvl) {
				drawCutIcon(icon, x + fullX * 16, y + i * 16, lastX, 16, i == fullLvl ? lastLvl : 0);
			}
		}
		drawCutIcon(icon, x + fullX * 16, y + fullY * 16, lastX, lastY, fullLvl == fullY ? lastLvl : 0);
	}

	//For some weird reason, our version of steam has NULL icons, so the GUISteamBoiler function drawCutIcon will crash
	private void drawCutIcon(Icon icon, int x, int y, int width, int height, int cut)
	{
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, zLevel, icon.getMinU(), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + height, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + cut, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x, y + cut, zLevel, icon.getMinU(), icon.getInterpolatedV(cut));
		tess.draw();
	}
	
	public static void setGLColorFromInt(int color) 
	{
		float red = (float) (color >> 16 & 255) / 255.0F;
		float green = (float) (color >> 8 & 255) / 255.0F;
		float blue = (float) (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}
}
