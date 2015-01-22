package steamcraft.client.renderers.tile.multipart;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import steamcraft.client.renderers.models.ModelLightningRod;
import steamcraft.common.blocks.multipart.LightningRodPart;
import steamcraft.common.lib.ModInfo;
import codechicken.multipart.TileMultipart;

public class TileLightningRodPartRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation crystal = new ResourceLocation(ModInfo.PREFIX.replace(":", ""), "textures/models/lightningrod.png");
	private final ModelLightningRod model = new ModelLightningRod();

	@Override
	public void renderTileEntityAt(TileEntity tile2, double dx, double dy, double dz, float p_147500_8_)
	{
		TileMultipart tilePart = (TileMultipart) tile2;
		LightningRodPart part = (LightningRodPart) tilePart.jPartList().get(0);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx + 0.5F, (float) dy + 1.5F, (float) dz + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(crystal);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
