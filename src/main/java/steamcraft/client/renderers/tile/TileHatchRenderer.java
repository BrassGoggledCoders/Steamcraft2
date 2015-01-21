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

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.client.renderers.models.ModelHatch;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 * 
 */
public class TileHatchRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation hatch = new ResourceLocation(ModInfo.PREFIX + "textures/models/modelHatch.png");
	private final ModelHatch model;

	public TileHatchRenderer()
	{
		this.model = new ModelHatch();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double dx, double dy, double dz, float scale)
	{

		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx + 0.5F, (float) dy, (float) dz + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(hatch);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		// TODO
		if(BlockTrapDoor.func_150118_d(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord)))
		{
			GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0F, 0F, 1F);
		}
		this.model.render(0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	public static class TileHatch extends TileEntity
	{
		public TileHatch()
		{

		}
	}
}
