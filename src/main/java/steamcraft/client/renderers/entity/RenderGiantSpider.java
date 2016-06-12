
package steamcraft.client.renderers.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.lib.ModInfo;

public class RenderGiantSpider extends RenderLiving
{
	private static final ResourceLocation spiderTextures = new ResourceLocation(ModInfo.PREFIX + "textures/models/mobs/ghostspider.png");
	private static final ResourceLocation spiderEyesTextures = new ResourceLocation("textures/entity/spider_eyes.png");

	public RenderGiantSpider()
	{
		super(new ModelSpider(), 2.0F);
		this.setRenderPassModel(new ModelSpider());
	}

	protected float getDeathMaxRotation(EntitySpider p_77037_1_)
	{
		return 180.0F;
	}

	protected int shouldRenderPass(EntitySpider p_77032_1_, int p_77032_2_, float p_77032_3_)
	{
		if (p_77032_2_ != 0)
		{
			return -1;
		}
		else
		{
			this.bindTexture(spiderEyesTextures);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

			if (p_77032_1_.isInvisible())
			{
				GL11.glDepthMask(false);
			}
			else
			{
				GL11.glDepthMask(true);
			}

			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			return 1;
		}
	}

	protected ResourceLocation getEntityTexture(EntitySpider p_110775_1_)
	{
		return spiderTextures;
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase p_77037_1_)
	{
		return this.getDeathMaxRotation((EntitySpider) p_77037_1_);
	}

	/**
	 * Queries whether should render the specified pass or not.
	 */
	@Override
	protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
	{
		return this.shouldRenderPass((EntitySpider) p_77032_1_, p_77032_2_, p_77032_3_);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return this.getEntityTexture((EntitySpider) p_110775_1_);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		GL11.glScalef(2.0F, 2.0F, 2.0F);
	}
}
