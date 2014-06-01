package steamcraft.common.items;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;

public class ItemRayGun extends Item
{
	String raySound;
	private Random random = new Random();

	@SuppressWarnings("all")
	static HashMap<String, Object> ray = new HashMap();
	@SuppressWarnings("all")
	static HashMap<String, Long> soundDelay = new HashMap();

	public ItemRayGun(String raySound)
	{
		super();
		this.raySound = raySound;
		setCreativeTab(Steamcraft.tabSC2);
		setMaxDamage(300);
		setMaxStackSize(1);
		setUnlocalizedName("itemRaygun");
	}

	@SuppressWarnings("all")
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		MovingObjectPosition mop = ItemRayGun.getTargetBlock(world, player, false); // Grabs vector
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
			soundDelay.put(player.getCommandSenderName(), Long.valueOf(0L));
		}
		if((!world.isRemote) && (soundDelay.get(player.getCommandSenderName()).longValue() < System.currentTimeMillis()))
		{
			world.playSoundEffect(tx, ty, tz, LibInfo.PREFIX + "raygun", 0.35F, 1.0F);
			soundDelay.put(player.getCommandSenderName(), Long.valueOf(System.currentTimeMillis() + 1200L));
		} else
		{
			soundDelay.put(player.getCommandSenderName(), Long.valueOf(0L));
		}
		if(world.isRemote)
		{
			ray.put(player.getCommandSenderName(), Steamcraft.proxy.rayFX(world, player, tx, ty, tz, 2, false, impact > 0 ? 2.0F : 0.0F, ray.get(player), impact));
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
		if(mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			int x = mop.blockX;
			int y = mop.blockY;
			int z = mop.blockZ;
			//this.spawnParticles(world, x, y, z);

			if(!world.isAirBlock(x, y, z)) // Causes unceremonious destruction & havoc all over the place!
			{
				//int randomInt = random.nextInt(5);

				for(int i = x - random.nextInt(3); i < x + random.nextInt(3); i++)
				{
					for(int j = y - random.nextInt(3); j < y + random.nextInt(3); j++)
					{
						for(int k = z - random.nextInt(3); k < z + random.nextInt(3); k++)
						{
							if(world.isAirBlock(i, j, k))
							{
								world.setBlock(i, j, k, Blocks.fire);
								stack.damageItem(1, player);
							}
							else if(world.getBlock(i, j, k) == Blocks.snow)
							{
								world.setBlock(i, j, k, Blocks.flowing_water);
								stack.damageItem(1, player);
							}
							if(world.getBlock(i, j, k) == Blocks.snow_layer)
							{
								world.setBlock(i, j, k, Blocks.flowing_water);
								stack.damageItem(1, player);
							}
							if(world.getBlock(i, j, k) == Blocks.sand)
							{
								world.setBlock(i, j, k, Blocks.glass);
								stack.damageItem(1, player);
							}
							if(world.getBlock(i, j, k) == Blocks.netherrack)
							{
								world.setBlock(i, j, k, Blocks.flowing_lava);
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
		return world.rayTraceBlocks(var13, var23, flag);
	}
}