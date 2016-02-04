
package steamcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import steamcraft.common.lib.ModInfo;

public class RenderLostMiner extends RenderLiving
{
	private static final ResourceLocation LOSTMINER_TEXTURE = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/lost_miner.png");

	public RenderLostMiner(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return RenderLostMiner.LOSTMINER_TEXTURE;
	}
}
