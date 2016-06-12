
package steamcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.lib.ModInfo;

public class RenderGrub extends RenderLiving
{
	private static final ResourceLocation TEX = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/grub.png");

	public RenderGrub(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return RenderGrub.TEX;
	}
}
