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
package xyz.brassgoggledcoders.steamcraft.common.items.electric;

import java.awt.Color;
import java.util.HashMap;

import boilerplate.common.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import xyz.brassgoggledcoders.steamcraft.common.lib.ModInfo;

/**
 * @author Surseance
 *
 */

public class ItemRayGun extends ElectricItem {
	public static short energyPerUse = 100;

	static HashMap<String, Object> ray = new HashMap<String, Object>();
	static HashMap<String, Long> soundDelay = new HashMap<String, Long>();
	static final HashMap<Block, Block> meltables = new HashMap<Block, Block>();

	public ItemRayGun(String raySound, int maxEnergy, int maxReceive) {
		super(maxEnergy, maxReceive, 0);
		this.setMaxStackSize(1);
		this.setFull3D();
		meltables.put(Blocks.snow, Blocks.flowing_water);
		meltables.put(Blocks.snow_layer, Blocks.flowing_water);
		meltables.put(Blocks.sand, Blocks.glass);
		meltables.put(Blocks.netherrack, Blocks.flowing_lava);
		meltables.put(Blocks.clay, Blocks.hardened_clay);
		meltables.put(Blocks.ice, Blocks.water);
	}

	@SuppressWarnings("all")
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (this.getEnergyStored(stack) >= ItemRayGun.energyPerUse || player.capabilities.isCreativeMode) {
			MovingObjectPosition mop = PlayerUtils.getTargetBlock(world, player, true, 50);

			Vec3 vec3 = player.getLookVec();
			double tx = player.posX + (vec3.xCoord * 5.0D);
			double ty = player.posY + (vec3.yCoord * 5.0D);
			double tz = player.posZ + (vec3.zCoord * 5.0D);
			int impact = 0;

			if (mop != null) // Sets vector
			{
				tx = mop.hitVec.xCoord;
				ty = mop.hitVec.yCoord;
				tz = mop.hitVec.zCoord;
				impact = 5;
			}

			if (soundDelay.get(player) == null)
				soundDelay.put(player.getCommandSenderName(), Long.valueOf(0L));

			if (!world.isRemote && (soundDelay.get(player.getCommandSenderName()).longValue() < System.currentTimeMillis())) {
				world.playSoundEffect(tx, ty, tz, ModInfo.PREFIX + "raygun", 0.35F, 1.0F);
				soundDelay.put(player.getCommandSenderName(), Long.valueOf(System.currentTimeMillis() + 1200L));
			} else
				soundDelay.put(player.getCommandSenderName(), Long.valueOf(0L));
			if (world.isRemote)
				ray.put(player.getCommandSenderName(), Steamcraft.proxy.rayFX(world, player, tx, ty, tz, 2, false, impact > 0 ? 2.0F : 0.0F, ray.get(player), impact, Color.GREEN));

			if ((mop != null) && (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)) {
				int x = mop.blockX;
				int y = mop.blockY;
				int z = mop.blockZ;

				if (!world.isRemote && !world.isAirBlock(x, y, z))
					for (int i = x - Item.itemRand.nextInt(4); i < (x + Item.itemRand.nextInt(4)); i++)
						for (int j = y - Item.itemRand.nextInt(4); j < (y + Item.itemRand.nextInt(4)); j++)
							for (int k = z - Item.itemRand.nextInt(4); k < (z + Item.itemRand.nextInt(4)); k++) {
								if (world.isAirBlock(i, j, k))
									world.setBlock(i, j, k, Blocks.fire);
								else if (meltables.containsKey(world.getBlock(i, j, k)))
									world.setBlock(i, j, k, meltables.get(world.getBlock(i, j, k)));

								if (!player.capabilities.isCreativeMode) {
									this.setEnergy(stack, this.getEnergyStored(stack) - energyPerUse);
								}

								if (this.getEnergyStored(stack) < energyPerUse)
									return stack;
							}
			}
			Entity pointedEntity = PlayerUtils.getPointedEntity(world, player, 32.0D);

			double px = player.posX;
			double py = player.posY;
			double pz = player.posZ;
			py = player.boundingBox.minY + (player.height / 2.0F) + 0.25D;
			px -= MathHelper.cos((player.rotationYaw / 180.0F) * 3.141593F) * 0.16F;
			py -= 0.05000000014901161D;
			pz -= MathHelper.cos((player.rotationYaw / 180.0F) * 3.141593F) * 0.16F;
			Vec3 vec3d = player.getLook(1.0F);
			px += vec3d.xCoord * 0.5D;
			py += vec3d.yCoord * 0.5D;
			pz += vec3d.zCoord * 0.5D;

			if ((pointedEntity != null) && ((pointedEntity instanceof EntityLivingBase))) {
				if (!world.isRemote)
					pointedEntity.setFire(100);
			}
		}
		return stack;
	}
}