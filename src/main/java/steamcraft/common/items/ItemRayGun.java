/*
 * 
 */
package steamcraft.common.items;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.utils.PlayerUtils;
import boilerplate.common.utils.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemRayGun.
 */
public class ItemRayGun extends BaseItem
{

	/** The ray sound. */
	String raySound;

	/** The random. */
	private Random random = new Random();

	/** The ray. */
	@SuppressWarnings("all")
	static HashMap<String, Object> ray = new HashMap();

	/** The sound delay. */
	@SuppressWarnings("all")
	static HashMap<String, Long> soundDelay = new HashMap();

	/**
	 * Instantiates a new item ray gun.
	 * 
	 * @param raySound
	 *            the ray sound
	 */
	public ItemRayGun(String raySound)
	{
		super();
		this.raySound = raySound;
		setMaxDamage(300);
		setMaxStackSize(1);
		setUnlocalizedName("itemRaygun");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.item.Item#onItemRightClick(net.minecraft.item.ItemStack,
	 * net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer)
	 */
	@SuppressWarnings("all")
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		MovingObjectPosition mop = PlayerUtils.getTargetBlock(world, player, false); // Grabs
																						// vector
		Vec3 vec3 = player.getLookVec();
		double tx = player.posX + vec3.xCoord * 10.0D;
		double ty = player.posY + vec3.yCoord * 10.0D;
		double tz = player.posZ + vec3.zCoord * 10.0D;
		int impact = 0;

		if (mop != null) // Sets vector
		{
			tx = mop.hitVec.xCoord;
			ty = mop.hitVec.yCoord;
			tz = mop.hitVec.zCoord;
			impact = 5;
		}

		// For RAY GUN sounds
		if (soundDelay.get(player) == null)
		{
			soundDelay.put(player.getCommandSenderName(), Long.valueOf(0L));
		}
		if ((!world.isRemote) && (soundDelay.get(player.getCommandSenderName()).longValue() < System.currentTimeMillis()))
		{
			world.playSoundEffect(tx, ty, tz, LibInfo.PREFIX + "raygun", 0.35F, 1.0F);
			soundDelay.put(player.getCommandSenderName(), Long.valueOf(System.currentTimeMillis() + 1200L));
		}
		else
		{
			soundDelay.put(player.getCommandSenderName(), Long.valueOf(0L));
		}
		if (world.isRemote)
		{
			ray.put(player.getCommandSenderName(),
					Steamcraft.proxy.rayFX(world, player, tx, ty, tz, 2, false, impact > 0 ? 2.0F : 0.0F, ray.get(player), impact, Color.GREEN));
		}

		// Couldn't get this shit to work...maybe I was just having a bad day
		/*
		 * if(mop != null && mop.typeOfHit == EnumMovingObjectType.ENTITY) {
		 * if(!(mop.entityHit instanceof EntityEnderman)) {
		 * //mop.entityHit.setFire(500); System.out.println(mop.entityHit);
		 * //DamageSource damage = DamageSource.causePlayerDamage(player);
		 * stack.damageItem(1, player); } }
		 */
		if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			int x = mop.blockX;
			int y = mop.blockY;
			int z = mop.blockZ;
			// this.spawnParticles(world, x, y, z);

			if (!world.isAirBlock(x, y, z) /* This bit is pretty important! */
					&& !Utils.getBlockUnbreakable(world, x, y, z)) // Causes
																	// unceremonious
																	// destruction
																	// & havoc
																	// all over
																	// the
																	// place!
			{
				// int randomInt = random.nextInt(5);

				for (int i = x - random.nextInt(3); i < x + random.nextInt(3); i++)
				{
					for (int j = y - random.nextInt(3); j < y + random.nextInt(3); j++)
					{
						for (int k = z - random.nextInt(3); k < z + random.nextInt(3); k++)
						{
							if (world.isAirBlock(i, j, k))
							{
								world.setBlock(i, j, k, Blocks.fire);
								stack.damageItem(1, player);
							}
							else if (world.getBlock(i, j, k) == Blocks.snow)
							{
								world.setBlock(i, j, k, Blocks.flowing_water);
								stack.damageItem(1, player);
							}
							if (world.getBlock(i, j, k) == Blocks.snow_layer)
							{
								world.setBlock(i, j, k, Blocks.flowing_water);
								stack.damageItem(1, player);
							}
							if (world.getBlock(i, j, k) == Blocks.sand)
							{
								world.setBlock(i, j, k, Blocks.glass);
								stack.damageItem(1, player);
							}
							if (world.getBlock(i, j, k) == Blocks.netherrack)
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
}