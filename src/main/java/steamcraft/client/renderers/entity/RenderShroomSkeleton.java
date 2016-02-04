
package steamcraft.client.renderers.entity;

import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import steamcraft.common.lib.ModInfo;

public class RenderShroomSkeleton extends RenderSkeleton
{
	private static final ResourceLocation TEX = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/shroomskeleton.png");

	public RenderShroomSkeleton()
	{
		super();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return RenderShroomSkeleton.TEX;
	}
}
