
package steamcraft.client.renderers.entity;

import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.lib.ModInfo;

public class RenderAbandonedGolem extends RenderIronGolem
{
	private static final ResourceLocation ENT_TEXTURE = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/abandoned_golem.png");

	public RenderAbandonedGolem()
	{
		super();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return RenderAbandonedGolem.ENT_TEXTURE;
	}
}
