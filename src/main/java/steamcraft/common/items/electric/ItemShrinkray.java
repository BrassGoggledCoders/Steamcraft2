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
package steamcraft.common.items.electric;

import java.awt.Color;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.entity.EntityMinedBlock;
import boilerplate.common.utils.PlayerUtils;
import boilerplate.common.utils.Utils;

/**
 * @author Surseance
 * 
 */
public class ItemShrinkray extends ElectricItem
{
	static short energyPerUse = 1000;

	static HashMap<String, Object> ray = new HashMap<String, Object>();
	static HashMap<String, Long> soundDelay = new HashMap<String, Long>();

	public ItemShrinkray(String raySound, int maxEnergy, int maxReceive, int maxSend)
	{
		super(maxEnergy, maxReceive, maxSend);
		this.setMaxStackSize(1);
		this.setFull3D();
		this.setMaxDamage(20);
		this.setHasSubtypes(false);
	}

	@SuppressWarnings("all")
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (this.getEnergyStored(stack) > this.energyPerUse)
		{
			MovingObjectPosition mop = PlayerUtils.getTargetBlock(world, player, true, 10);

			Vec3 vec3 = player.getLookVec();
			double tx = player.posX + (vec3.xCoord * 10.0D);
			double ty = player.posY + (vec3.yCoord * 10.0D);
			double tz = player.posZ + (vec3.zCoord * 10.0D);
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
				soundDelay.put(player.getCommandSenderName(), Long.valueOf(0L));
			if (!world.isRemote && (soundDelay.get(player.getCommandSenderName()).longValue() < System.currentTimeMillis()))
			{
				world.playSoundEffect(tx, ty, tz, LibInfo.PREFIX + "raygun", 0.35F, 1.0F);
				soundDelay.put(player.getCommandSenderName(), Long.valueOf(System.currentTimeMillis() + 1200L));
			}
			else
				soundDelay.put(player.getCommandSenderName(), Long.valueOf(0L));
			if (world.isRemote)
				ray.put(player.getCommandSenderName(),
						Steamcraft.proxy.rayFX(world, player, tx, ty, tz, 2, false, impact > 0 ? 2.0F : 0.0F, ray.get(player), impact, Color.BLUE));
			/*
			 * if(mop != null && mop.typeOfHit ==
			 * MovingObjectPosition.MovingObjectType.ENTITY) {
			 * if(!(mop.entityHit instanceof EntityEnderman)) {
			 * mop.entityHit.setFire(500); extractEnergy(stack, energyPerUse,
			 * false); } }
			 */
			if ((mop != null) && (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK))
			{
				int x = mop.blockX;
				int y = mop.blockY;
				int z = mop.blockZ;
				// this.spawnParticles(world, x, y, z);

				if (!world.isAirBlock(x, y, z) && !Utils.getBlockUnbreakable(world, x, y, z))
				{
					player.worldObj.spawnEntityInWorld(new EntityMinedBlock(player.worldObj, x + 0.5F, y + 0.5F, z + 0.5F, world.getBlock(x, y, z),
							world.getBlockMetadata(x, y, z), false));
					// ArrayList<ItemStack> items = world.getBlock(x, y,
					// z).getDrops(world, x, y, z, 0, 0);
					// for(ItemStack drops : items)
					// world.spawnEntityInWorld(new EntityItem(world, x, y, z,
					// drops));
					world.setBlockToAir(x, y, z);
					this.extractEnergy(stack, this.energyPerUse, false);
				}
			}
		}
		return stack;
	}
}