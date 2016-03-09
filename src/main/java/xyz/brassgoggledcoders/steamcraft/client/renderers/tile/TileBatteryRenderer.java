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
package xyz.brassgoggledcoders.steamcraft.client.renderers.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.steamcraft.client.renderers.models.ModelBattery;
import xyz.brassgoggledcoders.steamcraft.common.lib.ModInfo;
import boilerplate.client.ClientHelper;
import org.lwjgl.opengl.GL11;

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
