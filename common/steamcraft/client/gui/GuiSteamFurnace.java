/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Jan 30, 2014, 5:19:11 PM]
 */
package common.steamcraft.client.gui;

import common.steamcraft.common.block.tile.TileEntitySteamFurnace;
import common.steamcraft.common.block.tile.container.ContainerSteamFurnace;
import common.steamcraft.common.lib2.LibInfo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/**
 * 
 * @author MrArcane111 & Proloe
 *
 */
public class GuiSteamFurnace extends GuiContainer
{
	private static final ResourceLocation steamGuiTextures = new ResourceLocation(LibInfo.MOD_ID.toLowerCase(), "textures/gui/steamfurnace.png");
	private TileEntitySteamFurnace furnaceInventory;

	public GuiSteamFurnace(InventoryPlayer inventoryplayer, TileEntitySteamFurnace furnace)
	{
		super(new ContainerSteamFurnace(inventoryplayer, furnace));
		this.furnaceInventory = furnace;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j)
	{
		this.fontRenderer.drawString("Steam Furnace", 60, 6, 0x404040);
		this.fontRenderer.drawString("Inventory", 8, (this.ySize - 96) + 2, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(steamGuiTextures);
		int size1 = (this.width - this.xSize) / 2;
		int size2 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(size1, size2, 0, 0, this.xSize, this.ySize);
		
		if(this.furnaceInventory.isBurning())
		{
			int burnTime = furnaceInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(size1 + 56, (size2 + 36 + 12) - burnTime, 176, 12 - burnTime, 14, burnTime + 2);
		}
		
		int cookProgress = this.furnaceInventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(size1 + 79, size2 + 34, 176, 14, cookProgress + 1, 16);
		int waterLevel = furnaceInventory.getWaterScaled(31);
		this.drawTexturedModalRect(size1 + 32, (size2 + 16 + 31) - waterLevel, 176, (31 + 31 - waterLevel), 8, waterLevel + 2);
	}
}