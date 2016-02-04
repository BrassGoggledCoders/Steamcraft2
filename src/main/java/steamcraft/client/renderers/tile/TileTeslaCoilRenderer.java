
package steamcraft.client.renderers.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import boilerplate.client.ClientHelper;
import org.lwjgl.opengl.GL11;
import steamcraft.client.renderers.models.ModelTeslaCoil;
import steamcraft.common.lib.ModInfo;

public class TileTeslaCoilRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation crystal = new ResourceLocation(ModInfo.PREFIX.replace(":", ""), "textures/models/teslacoil.png");
	private final ModelTeslaCoil model;

	public TileTeslaCoilRenderer()
	{
		this.model = new ModelTeslaCoil();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double dx, double dy, double dz, float scale)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx + 0.5F, (float) dy + 1.5F, (float) dz + 0.5F);
		ClientHelper.textureManager().bindTexture(crystal);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
