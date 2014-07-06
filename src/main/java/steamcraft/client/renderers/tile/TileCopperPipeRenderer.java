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
package steamcraft.client.renderers.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.common.config.ConfigBlocks;

/**
 * @author warlordjones
 * 
 */
public class TileCopperPipeRenderer extends TileEntitySpecialRenderer
{
	private final ModelCopperPipe model;

	public TileCopperPipeRenderer()
	{
		this.model = new ModelCopperPipe();
	}

	@Override
	public void renderTileEntityAt(final TileEntity te, final double dx, final double dy, final double dz, final float scale)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx + 0.5F, (float) dy + 1.5F, (float) dz + 0.5F);
		final ResourceLocation crystal = new ResourceLocation("steamcraft:textures/blocks/metal/blockCopper.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(crystal);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		if (te.hasWorldObj())
		{
			if (te.getWorldObj().getBlock(te.xCoord, te.yCoord + 1, te.zCoord) == ConfigBlocks.blockCopperPipe)
			{
				this.model.renderModel(0.0625F, 0);
			}
			else if (te.getWorldObj().getBlock(te.xCoord, te.yCoord - 1, te.zCoord) == ConfigBlocks.blockCopperPipe)
			{
				this.model.renderModel(0.0625F, 1);
			}
			else if (te.getWorldObj().getBlock(te.xCoord + 1, te.yCoord, te.zCoord) == ConfigBlocks.blockCopperPipe)
			{
				this.model.renderModel(0.0625F, 2);
			}
			else if (te.getWorldObj().getBlock(te.xCoord - 1, te.yCoord, te.zCoord) == ConfigBlocks.blockCopperPipe)
			{
				this.model.renderModel(0.0625F, 3);
			}
			else if (te.getWorldObj().getBlock(te.xCoord, te.yCoord, te.zCoord + 1) == ConfigBlocks.blockCopperPipe)
			{
				this.model.renderModel(0.0625F, 4);
			}
			else if (te.getWorldObj().getBlock(te.xCoord, te.yCoord, te.zCoord - 1) == ConfigBlocks.blockCopperPipe)
			{
				this.model.renderModel(0.0625F, 5);
			}
			else
				this.model.renderModel(1, 3);;
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
