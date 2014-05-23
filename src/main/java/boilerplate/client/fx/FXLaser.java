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
 * File created @ [Mar 20, 2014, 11:26:57 AM]
 */
package boilerplate.client.fx;

import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

/**
 * @author Surseance (Johnny Eatmon)
 * 
 */
public class FXLaser extends EntityReddustFX
{
	private int part;

	public FXLaser(final World world, final double dx, final double dy,
			final double dz, final float r, final float g, final float b,
			final int movX, final int movY, final int movZ, final int part)
	{
		super(world, dx, dy, dz, 0.5F, r, g, b);
		motionX = 0.04F;
		motionZ = 0.04F;
		motionY = 0.04F;
		motionX *= movX;
		motionZ *= movZ;
		motionY *= movY;
		this.part = part;
		noClip = true;
	}

	public FXLaser(final World world, final double dx, final double dy,
			final double dz, final float r, final float g, final float b,
			final float scale)
	{
		super(world, dx, dy, dz, r, g, b, scale);
	}

	@Override
	public void renderParticle(final Tessellator tessellator, final float par2,
			final float par3, final float par4, final float par5,
			final float par6, final float par7)
	{
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

		setParticleTextureIndex(7 - (particleAge * 8) / particleMaxAge);

		if (!isDead)
		{
			moveEntity(motionX, motionY, motionZ);
		}

		// motionX *= 0.95999997854232788D;
		// motionZ *= 0.95999997854232788D;

		if (part == 1)
		{
			if (motionX > 0 && posX - Math.floor(posX) > 0.45F)
			{
				setDead();
			}
			if (motionX < 0 && posX - Math.floor(posX) < 0.55F)
			{
				setDead();
			}
			if (motionY > 0 && posY - Math.floor(posY) > 0.45F)
			{
				setDead();
			}
			if (motionY < 0 && posY - Math.floor(posY) < 0.55F)
			{
				setDead();
			}
			if (motionZ > 0 && posZ - Math.floor(posZ) > 0.45F)
			{
				setDead();
			}
			if (motionZ < 0 && posZ - Math.floor(posZ) < 0.55F)
			{
				setDead();
			}
		}
	}

	@Override
	public int getBrightnessForRender(final float f)
	{
		return 0xf000f0;
	}

	@Override
	public float getBrightness(final float f)
	{
		return 0.9F;
	}
}
