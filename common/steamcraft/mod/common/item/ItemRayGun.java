package common.steamcraft.mod.common.item;

import common.steamcraft.mod.common.SC2;
import common.steamcraft.mod.common.lib.CreativeTabsMod;
import common.steamcraft.mod.common.lib.LibInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Random;

public class ItemRayGun extends ItemMod
{
	String raySound;
	private Random random = new Random();
	static HashMap<String, Object> ray = new HashMap();
	static HashMap<String, Long> soundDelay = new HashMap();

	public ItemRayGun(int id, String raySound)
	{
		super(id);
		this.raySound = raySound;
		this.setCreativeTab(CreativeTabsMod.tabSCItems);
		this.setMaxDamage(300);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("raygun");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{	
		MovingObjectPosition mop = this.getTargetBlock(world, player, false); // Grabs vector
		Vec3 vec3 = player.getLookVec();
		double tx = player.posX + vec3.xCoord * 10.0D;
		double ty = player.posY + vec3.yCoord * 10.0D;
		double tz = player.posZ + vec3.zCoord * 10.0D;
		int impact = 0;

		if(mop != null) // Sets vector
		{
			tx = mop.hitVec.xCoord;
			ty = mop.hitVec.yCoord;
			tz = mop.hitVec.zCoord;
			impact = 5;
		}

		// For RAY GUN sounds
		if(soundDelay.get(player) == null) 
		{
			soundDelay.put(player.username, Long.valueOf(0L));
		}
		if((!world.isRemote) && (((Long)soundDelay.get(player.username)).longValue() < System.currentTimeMillis()))
		{
			world.playSoundEffect(tx, ty, tz, LibInfo.SC2_PREFIX + "raygun", 0.35F, 1.0F);
			soundDelay.put(player.username, Long.valueOf(System.currentTimeMillis() + 1200L));
		} else 
		{
			soundDelay.put(player.username, Long.valueOf(0L));
		}
		if(world.isRemote) 
		{
			ray.put(player.username, SC2.proxy.rayBeam(world, player, tx, ty, tz, 2, false, impact > 0 ? 2.0F : 0.0F, ray.get(player), impact));
		}

		// Couldn't get this shit to work...maybe I was just having a bad day
		/*
		if(mop != null && mop.typeOfHit == EnumMovingObjectType.ENTITY)
		{
			if(!(mop.entityHit instanceof EntityEnderman))
			{
				//mop.entityHit.setFire(500);
				System.out.println(mop.entityHit);
				//DamageSource damage = DamageSource.causePlayerDamage(player);
				stack.damageItem(1, player);
			}
		}
		*/
		if(mop != null && mop.typeOfHit == EnumMovingObjectType.TILE)
		{
			int x = mop.blockX;
			int y = mop.blockY;
			int z = mop.blockZ;
			//this.spawnParticles(world, x, y, z);
			int bid = world.getBlockId(x, y, z);

			if(bid > 0) // Causes unceremonious destruction & havoc all over the place!
			{
				//int randomInt = random.nextInt(5);

				for(int i = x - random.nextInt(3); i < x + random.nextInt(3); i++)
				{
					for(int j = y - random.nextInt(3); j < y + random.nextInt(3); j++)
					{
						for(int k = z - random.nextInt(3); k < z + random.nextInt(3); k++)
						{
							if(world.getBlockId(i, j, k) == 0)
							{
								world.setBlock(i, j, k, Block.fire.blockID);
								stack.damageItem(1, player);
							}
							if(world.getBlockId(i, j, k) == Block.sand.blockID)
							{
								world.setBlock(i, j, k, Block.glass.blockID);
								stack.damageItem(1, player);
							}
							if(world.getBlockId(i, j, k) == Block.netherrack.blockID)
							{
								world.setBlock(i, j, k, Block.lavaMoving.blockID, 3, 2);
								stack.damageItem(1, player);
							}
							if(world.getBlockId(i, j, k) == Block.blockClay.blockID)
							{
								world.setBlock(i, j, k, Block.hardenedClay.blockID);
								stack.damageItem(1, player);
							}
							if(world.getBlockId(i, j, k) == Block.blockSnow.blockID)
							{
								world.setBlock(i, j, k, Block.waterMoving.blockID, 3, 2);
								stack.damageItem(1, player);
							}
							if(world.getBlockId(i, j, k) == Block.snow.blockID)
							{
								world.setBlock(i, j, k, Block.waterMoving.blockID, 3, 2);
								stack.damageItem(1, player);
							}
							if(world.getBlockId(i, j, k) == Block.waterMoving.blockID)
							{
								world.setBlock(i, j, k, 0);
								stack.damageItem(1, player);
							}
							if(world.getBlockId(i, j, k) == Block.waterStill.blockID)
							{
								world.setBlock(i, j, k, 0);
								stack.damageItem(1, player);
							}
							if(world.getBlockId(i, j, k) == Block.ice.blockID)
							{
								world.setBlock(i, j, k, Block.waterStill.blockID);
								stack.damageItem(1, player);
							}
						}
					}
				}
			}
		}

		return stack;
	}

	// Grabs a vector from the player
	public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean flag)
	{
		float var4 = 1.0F;
		float var5 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * var4;
		float var6 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * var4;
		double var7 = entity.prevPosX + (entity.posX - entity.prevPosX) * var4;
		double var9 = entity.prevPosY + (entity.posY - entity.prevPosY) * var4 + 1.62D - entity.yOffset;
		double var11 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * var4;
		Vec3 var13 = world.getWorldVec3Pool().getVecFromPool(var7, var9, var11);
		float var14 = MathHelper.cos(-var6 * 0.01745329F - 3.141593F);
		float var15 = MathHelper.sin(-var6 * 0.01745329F - 3.141593F);
		float var16 = -MathHelper.cos(-var5 * 0.01745329F);
		float var17 = MathHelper.sin(-var5 * 0.01745329F);
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		double var21 = 10.0D;
		Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
		return world.rayTraceBlocks_do_do(var13, var23, flag, !flag);
	}

	private void spawnParticles(World world, int x, int y, int z) // From RedstoneOre
	{
		for(int l = 0; l < 4; ++l)
		{
			double d1 = (double)((float)x + random.nextFloat());
			double d2 = (double)((float)y + random.nextFloat());
			double d3 = (double)((float)z + random.nextFloat());
			world.spawnParticle("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
		}
	}
}