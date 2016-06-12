
package steamcraft.client.renderers.tile;

import org.lwjgl.opengl.GL11;

import boilerplate.client.ClientHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import steamcraft.client.renderers.models.ModelCastIronLampSide;
import steamcraft.client.renderers.models.ModelCastIronLampTop;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.ModInfo;

/**
 * @author Surseance
 *
 */
public class TileCastIronLampRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation lampOn = new ResourceLocation(ModInfo.PREFIX + "textures/models/lampon.png");
	private static final ResourceLocation lampOff = new ResourceLocation(ModInfo.PREFIX + "textures/models/lampoff.png");
	private final ModelCastIronLampTop lampModelTop;
	private final ModelCastIronLampSide lampModelSide;

	public TileCastIronLampRenderer()
	{
		this.lampModelTop = new ModelCastIronLampTop();
		this.lampModelSide = new ModelCastIronLampSide();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double dx, double dy, double dz, float scale)
	{
		Block block = te.getBlockType();
		int metadata = te.getBlockMetadata();
		GL11.glPushMatrix();
		float f1 = 0.6666667F;
		float f2 = 0.0F;

		if ((metadata == 1) || (metadata == 4))
		{
			f2 = 180F;
		}

		if (metadata == 2)
		{
			f2 = 90F;
		}

		if (metadata == 3)
		{
			f2 = -90F;
		}

		GL11.glTranslatef((float) dx + 0.5F, (float) dy + (0.75F * f1), (float) dz + 0.5F);
		GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);

		GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);

		if ((block == InitBlocks.blockCastIronLampOn) || (block == InitBlocks.blockInvertedCastIronLamp))
		{
			ClientHelper.textureManager().bindTexture(lampOn);
		}
		else if ((block == InitBlocks.blockCastIronLamp) || (block == InitBlocks.blockInvertedCastIronLampOff))
		{
			ClientHelper.textureManager().bindTexture(lampOff);
		}

		GL11.glPushMatrix();
		GL11.glScalef(f1, -f1, -f1);

		if ((metadata == 0) || (metadata == 1))
		{
			this.lampModelTop.renderLamp();
		}
		else
		{
			this.lampModelSide.renderLamp();
			this.lampModelSide.bracket.showModel = true;
			this.lampModelSide.bracket2.showModel = true;
		}

		GL11.glPopMatrix();
		float f4 = 0.01666667F * f1;
		GL11.glTranslatef(0.0F, 0.5F * f1, 0.07F * f1);
		GL11.glScalef(f4, -f4, f4);
		GL11.glNormal3f(0.0F, 0.0F, -1F * f4);
		GL11.glDepthMask(false);
		GL11.glDepthMask(true);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}
}
