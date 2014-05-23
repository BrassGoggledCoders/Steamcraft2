/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/15/14, 10:49]
 */
package boilerplate.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by Surseance (Johnny Eatmon)
 */
public class FXSmoke extends EntityReddustFX
{
	float smokeParticleScale;
	private static final ResourceLocation smoke = new ResourceLocation("sc2",
			"textures/misc/smoke.png");

	public FXSmoke(final World world, final double dx, final double dy,
			final double dz, final float r, final float g, final float b)
	{
		this(world, dx, dy, dz, r, g, b, 1.0F);
	}

	public FXSmoke(final World world, final double dx, final double dy,
			final double dz, final float r, final float g, final float b,
			final float scale)
	{
		super(world, dx, dy, dz, 0.0F, 0.0F, 0.0F, scale);
		motionX *= 0.10000000149011612D;
		motionY *= 0.10000000149011612D;
		motionZ *= 0.10000000149011612D;
		motionX += r;
		motionY += g;
		motionZ += b;
		particleRed = particleGreen = particleBlue = (float) (Math.random() * 0.30000001192092896D);
		particleScale *= 0.75F;
		particleScale *= scale;
		smokeParticleScale = particleScale;
		particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
		particleMaxAge = (int) (particleMaxAge * scale);
		noClip = false;
	}

	@Override
	public void renderParticle(final Tessellator tessellator, final float par2,
			final float par3, final float par4, final float par5,
			final float par6, final float par7)
	{
		float f6 = (particleAge + par2) / particleMaxAge * 32.0F;

		if (f6 < 0.0F)
		{
			f6 = 0.0F;
		}
		if (f6 > 1.0F)
		{
			f6 = 1.0F;
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(smoke);

		particleScale = smokeParticleScale * f6;
		super.renderParticle(tessellator, par2, par3, par4, par5, par6, par7);
	}

	@Override
	public void onUpdate()
	{
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge)
		{
			setDead();
		}

		setParticleTextureIndex(7 - particleAge * 8 / particleMaxAge);
		motionY += 0.004D;
		moveEntity(motionX, motionY, motionZ);

		if (posY == prevPosY)
		{
			motionX *= 1.1D;
			motionZ *= 1.1D;
		}

		motionX *= 0.9599999785423279D;
		motionY *= 0.9599999785423279D;
		motionZ *= 0.9599999785423279D;

		if (onGround)
		{
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
		}
	}
}
