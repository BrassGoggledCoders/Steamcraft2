/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.entities.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import steamcraft.common.lib.DamageSourceHandler;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.baseclasses.BaseProjectileEntity;

/**
 * @author warlordjones
 *
 */
public class EntityRocket extends BaseProjectileEntity
{
	int type;

	public EntityRocket(World world, EntityLivingBase shooter, int type, int accuracy)
	{
		super(world, shooter, 0, accuracy);
		this.type = type;
	}
	@Override
	public void onHitEntity(Entity entity)
	{
		switch(type)
		{
			case 1: this.worldObj.newExplosion(this, this.xTile, this.yTile, this.zTile, 5, true, false);
			break;
			case 2: entity.attackEntityFrom(DamageSourceHandler.rocket, 5F);
			break;
			default: this.worldObj.newExplosion(this, this.xTile, this.yTile, this.zTile, 5, false, true);
		}
		this.worldObj.playSoundAtEntity(this, LibInfo.PREFIX + "hitflesh", 1.0F, 1.2F / ((this.rand.nextFloat() * 0.2F) + 0.9F));
		this.setDead();
	}

	@Override
	public void onHitBlock(Block block, MovingObjectPosition mop)
	{
		this.motionX = (float) (mop.hitVec.xCoord - this.posX);
		this.motionY = (float) (mop.hitVec.yCoord - this.posY);
		this.motionZ = (float) (mop.hitVec.zCoord - this.posZ);
		float magnitude = MathHelper.sqrt_double((this.motionX * this.motionX) + (this.motionY * this.motionY)
				+ (this.motionZ * this.motionZ));
		this.posX -= (this.motionX / magnitude) * 0.05000000074505806D;
		this.posY -= (this.motionY / magnitude) * 0.05000000074505806D;
		this.posZ -= (this.motionZ / magnitude) * 0.05000000074505806D;

		this.worldObj.playSoundAtEntity(this, LibInfo.PREFIX + "hitblock", 1.0F, 1.0F);
		this.setDead();
	}
	@Override
	protected void entityInit()
	{
		//See BaseProjectileEntity
	}
}
