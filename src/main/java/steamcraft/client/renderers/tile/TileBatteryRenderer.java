
package steamcraft.client.renderers.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import boilerplate.client.ClientHelper;
import org.lwjgl.opengl.GL11;
import steamcraft.client.renderers.models.ModelBattery;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class TileBatteryRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation battery = new ResourceLocation(ModInfo.PREFIX + "textures/models/batterybank.png");
	private final ModelBattery model;

	public TileBatteryRenderer()
	{
		this.model = new ModelBattery();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double dx, double dy, double dz, float scale)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx + 0.5F, (float) dy + 1.5F, (float) dz + 0.5F);
		ClientHelper.textureManager().bindTexture(battery);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
		GL11.glPopMatrix();
	}
}
