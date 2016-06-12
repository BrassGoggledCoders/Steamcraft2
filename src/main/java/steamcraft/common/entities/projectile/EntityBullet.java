
package steamcraft.common.entities.projectile;

import boilerplate.common.baseclasses.BaseShootableEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import steamcraft.common.lib.DamageSourceHandler;
import steamcraft.common.lib.ModInfo;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class EntityBullet extends BaseShootableEntity
{
	private int damage;

	public EntityBullet(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	public EntityBullet(World world, EntityLivingBase shooter, int damage, int accuracy)
	{
		super(world, shooter, damage, accuracy);
		this.damage = damage;
		this.setSize(0.5F, 0.5F);
	}

	@Override
	public void onHitEntity(Entity entity)
	{
		if (entity.attackEntityFrom(DamageSourceHandler.bullet, this.damage))
		{
			this.worldObj.playSoundAtEntity(this, ModInfo.PREFIX + "hitflesh", 1.0F, 1.2F / ((this.rand.nextFloat() * 0.2F) + 0.9F));
			this.setDead();
		}
		else
		{
			this.motionX *= 0.10000000149011612D;
			this.motionY *= 0.10000000149011612D;
			this.motionZ *= 0.10000000149011612D;
			this.flyTime = 0;
			this.setDead();
		}
	}

	@Override
	public void onHitBlock(Block block, MovingObjectPosition mop)
	{
		if ((block == Blocks.glass) || (block == Blocks.glowstone) || (block == Blocks.glass_pane) || (block == Blocks.ice)
				|| (block == Blocks.stained_glass) || (block == Blocks.stained_glass_pane))
		{
			this.worldObj.playSoundEffect(this.xTile + 0.5D, this.yTile + 0.5D, this.zTile + 0.5D, block.stepSound.getBreakSound(), 1.0F, 1.0F);
			this.worldObj.setBlockToAir(this.xTile, this.yTile, this.zTile);
		}
		else
		{
			this.motionX = (float) (mop.hitVec.xCoord - this.posX);
			this.motionY = (float) (mop.hitVec.yCoord - this.posY);
			this.motionZ = (float) (mop.hitVec.zCoord - this.posZ);
			float magnitude = MathHelper.sqrt_double((this.motionX * this.motionX) + (this.motionY * this.motionY) + (this.motionZ * this.motionZ));
			this.posX -= (this.motionX / magnitude) * 0.05000000074505806D;
			this.posY -= (this.motionY / magnitude) * 0.05000000074505806D;
			this.posZ -= (this.motionZ / magnitude) * 0.05000000074505806D;
			this.worldObj.playSoundAtEntity(this, ModInfo.PREFIX + "hitblock", 1.0F, 1.0F);
			this.setDead();
		}
	}
}
