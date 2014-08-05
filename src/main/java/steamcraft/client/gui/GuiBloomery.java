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
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileBloomery;
import steamcraft.common.tiles.container.ContainerBloomery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBloomery extends GuiContainer
{
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(LibInfo.PREFIX + "textures/gui/bloomery.png");
	private TileBloomery tileFurnace;

	public GuiBloomery(InventoryPlayer par1InventoryPlayer, TileBloomery par2TileEntityFurnace)
	{
		super(new ContainerBloomery(par1InventoryPlayer, par2TileEntityFurnace));
		tileFurnace = par2TileEntityFurnace;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		String s = tileFurnace.hasCustomInventoryName() ? tileFurnace.getInventoryName() : I18n.format(tileFurnace.getInventoryName(), new Object[0]);
		fontRendererObj.drawString(s, (xSize / 2) - (fontRendererObj.getStringWidth(s) / 2), 6, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, (ySize - 96) + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		mc.getTextureManager().bindTexture(furnaceGuiTextures);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		int i1;

		if (tileFurnace.isBurning())
		{
			i1 = tileFurnace.getBurnTimeRemainingScaled(12);
			drawTexturedModalRect(guiLeft + 46, (guiTop + 36 + 12) - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = tileFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, i1 + 1, 16);
	}
}
