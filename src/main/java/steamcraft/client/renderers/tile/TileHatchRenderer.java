
package steamcraft.client.renderers.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import boilerplate.client.ClientHelper;
import org.lwjgl.opengl.GL11;
import steamcraft.client.renderers.models.ModelHatch;
import steamcraft.common.blocks.BlockHatch;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class TileHatchRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation hatch = new ResourceLocation(ModInfo.PREFIX + "textures/blocks/metal/blockSteel.png");
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
		ClientHelper.textureManager().bindTexture(hatch);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

		int meta = te.blockMetadata;
		if (BlockHatch.isOpen(meta))
		{
			GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0F, -0.4F, 0.5F);
			if ((meta & 3) == 0)
			{
				GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0.0F, -0.8F, 0.0F);
			}
			// 1 is default
			else if ((meta & 3) == 2)
			{
				GL11.glRotatef(90F, 0.0F, 0.0F, -1.0F);
				GL11.glTranslatef(-0.4F, -0.4F, 0.0F);
			}
			else if ((meta & 3) == 3)
			{
				GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0.4F, -0.4F, 0.0F);
			}
		}
		this.model.render(0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	public static class TileHatch extends TileEntity
	{
		public TileHatch()
		{

		}
	}
}
