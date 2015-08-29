package steamcraft.common.entities;

import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import steamcraft.common.blocks.BlockBoulder;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.DamageSourceHandler;

public class EntityFallingBoulder extends EntityFallingBlock
{
	private boolean field_145808_f;
	private int maxDamage;
	private float damageMultiplier;

	public EntityFallingBoulder(World world)
	{
		super(world);
		this.field_145813_c = true;
		this.maxDamage = 60;
		this.damageMultiplier = 3.0F;
	}

	public EntityFallingBoulder(World world, double p_i45319_2_, double p_i45319_4_, double p_i45319_6_)
	{
		super(world);
		this.field_145813_c = true;
		this.maxDamage = 60;
		this.damageMultiplier = 3.0F;
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
		this.yOffset = this.height / 2.0F;
		this.setPosition(p_i45319_2_, p_i45319_4_, p_i45319_6_);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = p_i45319_2_;
		this.prevPosY = p_i45319_4_;
		this.prevPosZ = p_i45319_6_;
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected void fall(float p_70069_1_)
	{
		int i = MathHelper.ceiling_float_int(p_70069_1_ - 1.0F);

		if (i > 0)
		{
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox);
			DamageSource damagesource = DamageSourceHandler.boulder;

			for (Object obj : list)
			{
				Entity entity = (Entity) obj;
				entity.attackEntityFrom(damagesource, Math.min(MathHelper.floor_float(i * this.damageMultiplier) + 20, this.maxDamage));
			}

			if (this.rand.nextFloat() < (0.05000000074505806D + (i * 0.05D)))
			{
				int j = this.field_145814_a >> 2;
				int k = this.field_145814_a & 3;
				++j;

				if (j > 2)
				{
					this.field_145808_f = true;
				}
				else
				{
					this.field_145814_a = k | (j << 2);
				}
			}
		}
	}

	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		++this.field_145812_b;
		this.motionY -= 0.03999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (!this.worldObj.isRemote)
		{
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY);
			int k = MathHelper.floor_double(this.posZ);

			if (this.field_145812_b == 1)
			{
				if (this.worldObj.getBlock(i, j, k) != InitBlocks.blockBoulder)
				{
					this.setDead();
					return;
				}

				this.worldObj.setBlockToAir(i, j, k);
			}

			if (this.onGround)
			{
				this.motionX *= 0.699999988079071D;
				this.motionZ *= 0.699999988079071D;
				this.motionY *= -0.5D;

				if (this.worldObj.getBlock(i, j, k) != Blocks.piston_extension)
				{
					this.setDead();

					if (!this.field_145808_f && this.worldObj.canPlaceEntityOnSide(InitBlocks.blockBoulder, i, j, k, true, 1, null, null)
							&& !BlockBoulder.func_149831_e(this.worldObj, i, j - 1, k)
							&& this.worldObj.setBlock(i, j, k, InitBlocks.blockBoulder, this.field_145814_a, 3))
					{
						if (InitBlocks.blockBoulder instanceof BlockBoulder)
						{
							((BlockBoulder) InitBlocks.blockBoulder).func_149828_a(this.worldObj, i, j, k, this.field_145814_a);
						}

						if ((this.field_145810_d != null) && (InitBlocks.blockBoulder instanceof ITileEntityProvider))
						{
							TileEntity tileentity = this.worldObj.getTileEntity(i, j, k);

							if (tileentity != null)
							{
								NBTTagCompound nbttagcompound = new NBTTagCompound();
								tileentity.writeToNBT(nbttagcompound);

								for (Object obj : this.field_145810_d.func_150296_c())
								{
									String s = (String) obj;
									NBTBase nbtbase = this.field_145810_d.getTag(s);

									if (!s.equals("x") && !s.equals("y") && !s.equals("z"))
									{
										nbttagcompound.setTag(s, nbtbase.copy());
									}
								}

								tileentity.readFromNBT(nbttagcompound);
								tileentity.markDirty();
							}
						}
					}
					else if (this.field_145813_c && !this.field_145808_f)
					{
						this.entityDropItem(new ItemStack(InitBlocks.blockBoulder, 1), 0.0F);
					}
				}
			}
			else if (((this.field_145812_b > 100) && !this.worldObj.isRemote && ((j < 1) || (j > 256))) || (this.field_145812_b > 600))
			{
				if (this.field_145813_c)
				{
					this.entityDropItem(new ItemStack(InitBlocks.blockBoulder, 1), 0.0F);
				}

				this.setDead();
			}
		}
	}

}
