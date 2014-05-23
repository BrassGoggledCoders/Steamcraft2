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

	public FXLaser(World world, double dx, double dy, double dz, float r, float g, float b, int movX, int movY, int movZ, int part) 
	{
		super(world, dx, dy, dz, 0.5F, r, g, b);
		this.motionX = 0.04F;
		this.motionZ = 0.04F;
		this.motionY = 0.04F;
		this.motionX *= movX;
		this.motionZ *= movZ;
		this.motionY *= movY;
		this.part = part;
		this.noClip = true;
	}

	public FXLaser(World world, double dx, double dy, double dz, float r, float g, float b, float scale) 
	{
		super(world, dx, dy, dz, r, g, b, scale);
	}

	@Override
	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.renderParticle(tessellator, par2, par3, par4, par5, par6, par7);
	}

	@Override
	public void onUpdate() 
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) 
		{
			setDead();
		}

		this.setParticleTextureIndex(7 - (this.particleAge * 8) / this.particleMaxAge);

		if (!this.isDead) 
			moveEntity(this.motionX, this.motionY, this.motionZ);

		// motionX *= 0.95999997854232788D;
		// motionZ *= 0.95999997854232788D;

		if (this.part == 1) 
		{
			if (this.motionX > 0 && this.posX - Math.floor(this.posX) > 0.45F) 
			{
				this.setDead();
			}
			if (this.motionX < 0 && this.posX - Math.floor(this.posX) < 0.55F) 
			{
				this.setDead();
			}
			if (this.motionY > 0 && this.posY - Math.floor(this.posY) > 0.45F) 
			{
				this.setDead();
			}
			if (this.motionY < 0 && this.posY - Math.floor(this.posY) < 0.55F)
			{
				this.setDead();
			}
			if (this.motionZ > 0 && this.posZ - Math.floor(this.posZ) > 0.45F)
			{
				this.setDead();
			}
			if (this.motionZ < 0 && this.posZ - Math.floor(this.posZ) < 0.55F) 
			{
				this.setDead();
			}
		}
	}

	@Override
	public int getBrightnessForRender(float f) 
	{
		return 0xf000f0;
	}

	@Override
	public float getBrightness(float f) 
	{
		return 0.9F;
	}
}
