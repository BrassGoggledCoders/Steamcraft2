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

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileBloomery;
import steamcraft.common.tiles.container.ContainerBloomery;

/**
 * @author warlordjones
 * 
 */
public class GuiBloomery extends BaseContainerGui
{
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(ModInfo.PREFIX + "textures/gui/bloomery.png");
	private TileBloomery tileFurnace;

	public GuiBloomery(InventoryPlayer par1InventoryPlayer, TileBloomery par2TileEntityFurnace)
	{
		super(new ContainerBloomery(par1InventoryPlayer, par2TileEntityFurnace));
		this.tileFurnace = par2TileEntityFurnace;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		String s = this.tileFurnace.hasCustomInventoryName() ? this.tileFurnace.getInventoryName() : I18n.format(this.tileFurnace.getInventoryName());
		this.fontRendererObj.drawString(s, (this.xSize / 2) - (this.fontRendererObj.getStringWidth(s) / 2), 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, (this.ySize - 96) + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int i1;

		if(this.tileFurnace.isBurning())
		{
			i1 = this.tileFurnace.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(this.guiLeft + 46, (this.guiTop + 36 + 12) - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.tileFurnace.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 34, 176, 14, i1 + 1, 16);
	}
}
