
package steamcraft.common.entities.projectile;

import boilerplate.common.baseclasses.BaseShootableEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import steamcraft.common.lib.DamageSourceHandler;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class EntityRocket extends BaseShootableEntity
{
	private int type;
	EntityLivingBase shooter;

	public EntityRocket(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	public EntityRocket(World world, EntityLivingBase shooter, int type, int accuracy)
	{
		super(world, shooter, 0, accuracy);
		this.type = type;
		shooter = this.shooter;
	}

	@Override
	public void onHitEntity(Entity entity)
	{
		switch (this.type)
		{
		case 1:
			entity.setFire(30);
			break;
		case 2:
			entity.attackEntityFrom(DamageSourceHandler.rocket, 7F);
			break;
		default:
			// TODO
			// this.worldObj.newExplosion(this, entity.posX, entity.posY,
			// entity.posZ, 5, false, true);
			break;
		}
		this.worldObj.playSoundAtEntity(this, ModInfo.PREFIX + "hitflesh", 1.0F, 1.2F / ((this.rand.nextFloat() * 0.2F) + 0.9F));
		this.setDead();
	}

	@Override
	public void onHitBlock(Block block, MovingObjectPosition mop)
	{
		this.motionX = (float) (mop.hitVec.xCoord - this.posX);
		this.motionY = (float) (mop.hitVec.yCoord - this.posY);
		this.motionZ = (float) (mop.hitVec.zCoord - this.posZ);
		float magnitude = MathHelper.sqrt_double((this.motionX * this.motionX) + (this.motionY * this.motionY) + (this.motionZ * this.motionZ));
		this.posX -= (this.motionX / magnitude) * 0.05000000074505806D;
		this.posY -= (this.motionY / magnitude) * 0.05000000074505806D;
		this.posZ -= (this.motionZ / magnitude) * 0.05000000074505806D;
		switch (this.type)
		{
		case 1:
			this.worldObj.newExplosion(this, this.xTile, this.yTile, this.zTile, 5, true, false);
			break;
		case 2:
			this.worldObj.newExplosion(this, this.xTile, this.yTile, this.zTile, 0, false, false);
			break;
		default:
			this.worldObj.newExplosion(this, this.xTile, this.yTile, this.zTile, 5, false, true);
			break;
		}
		this.worldObj.playSoundAtEntity(this, ModInfo.PREFIX + "hitblock", 1.0F, 1.0F);
		this.setDead();
	}
}
