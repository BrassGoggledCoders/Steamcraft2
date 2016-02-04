
package steamcraft.common.items.electric;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import boilerplate.common.entity.EntityMinedBlock;
import boilerplate.common.utils.PlayerUtils;
import boilerplate.common.utils.Utils;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author Surseance
 *
 */
public class ItemShrinkray extends ElectricItem
{
	public static short energyPerUse = 1000;

	static HashMap<String, Object> ray = new HashMap<String, Object>();
	static HashMap<String, Long> soundDelay = new HashMap<String, Long>();

	public ItemShrinkray(String raySound, int maxEnergy, int maxReceive)
	{
		super(maxEnergy, maxReceive, 0);
		this.setMaxStackSize(1);
		this.setFull3D();
		this.setHasSubtypes(false);
	}

	@SuppressWarnings("all")
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (player.isSneaking())
		{
			NBTTagCompound tag = stack.getTagCompound();

			if (tag.getBoolean("active"))
				tag.setBoolean("active", false);
			else
				tag.setBoolean("active", true);

			stack.setTagCompound(tag);
		}
		if (stack.getTagCompound().getBoolean("active"))
		{
			if (this.getEnergyStored(stack) >= ItemShrinkray.energyPerUse)
			{
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
						world.playSoundEffect(tx, ty, tz, ModInfo.PREFIX + "raygun", 0.35F, 1.0F);
						soundDelay.put(player.getCommandSenderName(), Long.valueOf(System.currentTimeMillis() + 1200L));
					}
					else
						soundDelay.put(player.getCommandSenderName(), Long.valueOf(0L));
					if (world.isRemote)
						ray.put(player.getCommandSenderName(), Steamcraft.proxy.rayFX(world, player, tx, ty, tz, 2, false, impact > 0 ? 2.0F : 0.0F,
								ray.get(player), impact, Color.BLUE));
					if (mop != null)
					{
						if ((mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK))
						{
							int x = mop.blockX;
							int y = mop.blockY;
							int z = mop.blockZ;

							if (!world.isAirBlock(x, y, z) && !Utils.getBlockUnbreakable(world, x, y, z) && (world.getTileEntity(x, y, z) == null))
							{
								player.worldObj.spawnEntityInWorld(new EntityMinedBlock(player.worldObj, x + 0.5F, y + 0.5F, z + 0.5F,
										world.getBlock(x, y, z), world.getBlockMetadata(x, y, z), false));
								if (!world.isRemote)
								{
									world.setBlockToAir(x, y, z);
									this.setEnergy(stack, this.getEnergyStored(stack) - energyPerUse);

									if (this.getEnergyStored(stack) < energyPerUse)
										return stack;
								}
							}
						}
					}
				}
			}
			/*
			 * Entity pointedEntity = PlayerUtils.getPointedEntity(world,
			 * player, 32.0D); double px = player.posX; double py = player.posY;
			 * double pz = player.posZ; py = player.boundingBox.minY +
			 * player.height / 2.0F + 0.25D; px -=
			 * MathHelper.cos(player.rotationYaw / 180.0F * 3.141593F) * 0.16F;
			 * py -= 0.05000000014901161D; pz -=
			 * MathHelper.cos(player.rotationYaw / 180.0F * 3.141593F) * 0.16F;
			 * Vec3 vec3d = player.getLook(1.0F); px += vec3d.xCoord * 0.5D; py
			 * += vec3d.yCoord * 0.5D; pz += vec3d.zCoord * 0.5D;
			 * if((pointedEntity != null) && pointedEntity instanceof
			 * EntityCreature) { EntityCreature creature = (EntityCreature)
			 * pointedEntity; if(!world.isRemote) {
			 * creature.attackEntityFrom(DamageSource.outOfWorld,
			 * creature.getMaxHealth() * 10);
			 * if(player.inventory.hasItem(InitItems.itemEmptyMobBottle)) {
			 * player.inventory.consumeInventoryItem(InitItems.
			 * itemEmptyMobBottle); NBTTagCompound tag = new NBTTagCompound();
			 * tag.setTag("storedCreature", creature.getEntityData()); ItemStack
			 * stack1 = new ItemStack(InitItems.itemMobBottle);
			 * stack.setTagCompound(tag);
			 * player.inventory.addItemStackToInventory(stack1); } } }
			 */
		}
		return stack;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		super.addInformation(itemStack, entityPlayer, list, bool);

		if (itemStack.getTagCompound().getBoolean("active"))
		{
			list.add("\u00A74" + "Safety Catch Off");
		}
		else
		{
			list.add("\u00A72" + "Safety Catch On");
		}
	}
}