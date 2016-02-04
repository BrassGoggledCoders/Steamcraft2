
package steamcraft.common.entities.living;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityFleshGolem extends EntityGolem
{
	private int attackTimer;

	public EntityFleshGolem(World p_i1694_1_)
	{
		super(p_i1694_1_);
		this.setSize(1.4F, 2.9F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}

	@Override
	protected int decreaseAirSupply(int p_70682_1_)
	{
		return p_70682_1_;
	}

	@Override
	protected void collideWithEntity(Entity p_82167_1_)
	{
		if ((p_82167_1_ instanceof IMob) && (this.getRNG().nextInt(20) == 0))
		{
			this.setAttackTarget((EntityLivingBase) p_82167_1_);
		}

		super.collideWithEntity(p_82167_1_);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.attackTimer > 0)
		{
			--this.attackTimer;
		}
		if ((((this.motionX * this.motionX) + (this.motionZ * this.motionZ)) > 2.500000277905201E-7D) && (this.rand.nextInt(5) == 0))
		{
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - this.yOffset);
			int k = MathHelper.floor_double(this.posZ);
			Block block = this.worldObj.getBlock(i, j, k);

			if (block.getMaterial() != Material.air)
			{
				this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(block) + "_" + this.worldObj.getBlockMetadata(i, j, k),
						this.posX + ((this.rand.nextFloat() - 0.5D) * this.width), this.boundingBox.minY + 0.1D,
						this.posZ + ((this.rand.nextFloat() - 0.5D) * this.width), 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D,
						(this.rand.nextFloat() - 0.5D) * 4.0D);
			}
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound p_70014_1_)
	{
		super.writeEntityToNBT(p_70014_1_);

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound p_70037_1_)
	{
		super.readEntityFromNBT(p_70037_1_);

	}

	@Override
	public boolean attackEntityAsMob(Entity p_70652_1_)
	{
		this.attackTimer = 10;
		this.worldObj.setEntityState(this, (byte) 4);
		boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 7 + this.rand.nextInt(15));

		if (flag)
		{
			p_70652_1_.motionY += 0.4000000059604645D;
		}

		this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
		return flag;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte p_70103_1_)
	{
		if (p_70103_1_ == 4)
		{
			this.attackTimer = 10;
			this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
		}
		else
		{
			super.handleHealthUpdate(p_70103_1_);
		}
	}

	@SideOnly(Side.CLIENT)
	public int getAttackTimer()
	{
		return this.attackTimer;
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.zombie.hit";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.zombie.death";
	}

	@Override
	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
	{
		this.playSound("mob.zombie.walk", 1.0F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
	{
		int j = this.rand.nextInt(3);
		int k;

		for (k = 0; k < j; ++k)
		{
			this.func_145778_a(Items.iron_ingot, 1, 0.0F);
		}

		k = 3 + this.rand.nextInt(3);

		for (int l = 0; l < k; ++l)
		{
			this.dropItem(Items.rotten_flesh, 1);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canAttackClass(Class p_70686_1_)
	{
		return EntityPlayer.class != p_70686_1_;
	}
}
