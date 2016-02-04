
package steamcraft.client.renderers.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import steamcraft.client.renderers.models.ModelWhale;
import steamcraft.common.lib.ModInfo;

public class RenderWhale extends RenderLiving
{
	private static final ResourceLocation ENT_TEXTURE = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/whale.png");

	public RenderWhale()
	{
		super(new ModelWhale(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return RenderWhale.ENT_TEXTURE;
	}
}
