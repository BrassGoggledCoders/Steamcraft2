
package steamcraft.client.renderers.entity;

import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import steamcraft.common.lib.ModInfo;

public class RenderShroomZombie extends RenderZombie
{
	private static final ResourceLocation TEX = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/shroomzombie.png");

	public RenderShroomZombie()
	{
		super();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return RenderShroomZombie.TEX;
	}
}
