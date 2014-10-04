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
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import steamcraft.common.lib.DamageSourceHandler;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.baseclasses.BaseProjectileEntity;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class EntityBullet extends BaseProjectileEntity
{
	private int damage;

	public EntityBullet(World world)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
	}

	public EntityBullet(World world, EntityLivingBase shooter, int damage, int accuracy)
	{
		super(world, shooter, damage, accuracy);
		this.damage = damage;
	}
	@Override
	public void onHitEntity(Entity entity)
	{
		if(entity.attackEntityFrom(DamageSourceHandler.bullet, this.damage))
		{
			this.worldObj.playSoundAtEntity(this, LibInfo.PREFIX + "hitflesh", 1.0F, 1.2F / ((this.rand.nextFloat() * 0.2F) + 0.9F));
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
		if((block == Blocks.glass) || (block == Blocks.glowstone) || (block == Blocks.glass_pane) || (block == Blocks.ice)
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
			float magnitude = MathHelper.sqrt_double((this.motionX * this.motionX) + (this.motionY * this.motionY)
					+ (this.motionZ * this.motionZ));
			this.posX -= (this.motionX / magnitude) * 0.05000000074505806D;
			this.posY -= (this.motionY / magnitude) * 0.05000000074505806D;
			this.posZ -= (this.motionZ / magnitude) * 0.05000000074505806D;
			this.worldObj.playSoundAtEntity(this, LibInfo.PREFIX + "hitblock", 1.0F, 1.0F);
			this.setDead();
		}
	}
	@Override
	protected void entityInit()
	{
		//See BaseProjectileEntity
	}
}
