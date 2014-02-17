/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 9, 2014, 2:19:45 PM]
 */
package common.steamcraft.common.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * @author MrArcane111
 *
 */
public class ConveyorUtil {
	/** Max horizontal speed of an item on a belt; items that are going slower are accelerated. */
	public static final double MAX_HORIZONTAL_SPEED = 0.5F; //0.4D;

	/** Velocity increment added to an item on a belt; if it is going slower than MAX_HORIZONTAL_SPEED. */
	public static final double HORIZONTAL_BOOST = 0.14D; //0.11D;

	/** Items this far from belt sides get BORDER_BOOST to stay in the center. */
	public static final double BORDERS = 0.35D;

	/** Velocity increment added to item when it gets close to a border, in order to keep it on the belt. */
	public static final double BORDER_BOOST = 0.063D;

	/** The belts' height in units. 0.0625F = one pixel in vanilla textures. */
	public static final float HEIGHT = 0.0625F;

	/** Collision box height - must be smaller than HEIGHT to let the items collide -> get moved. */
	public static final float HEIGHT_COLLISION = HEIGHT;// - 0.0125F;

	/** Selection box height. */
	public static final float HEIGHT_SELECTED = HEIGHT;

	/** How far from the start of a belt the item must be to be stored. */
	public static final float STORAGE_BORDER = 0.5F;

	/** Long storage border. */
	public static final float STORAGE_BORDER_LONG = 0.8F;

	/** Vertical storage border - how high above belt the item must be to get stored. */
	public static final float STORAGE_BORDER_V = 0.6F;

	public static void playSFXChest(World world, int i, int j, int k) {
		world.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "random.pop", (world.rand.nextFloat() + 0.7F) / 5.0F, 0.5F + world.rand.nextFloat() * 0.3F);
	}

	public static void playSFXBelt(World world, int i, int j, int k) {
		world.playSoundEffect(i + 0.5D, j + 0.625D, k + 0.5D, "random.wood click", (world.rand.nextFloat() + 0.2F) / 10.0F, 1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.6F);
	}

	/** Checks if the path of a block is obstructed. */
	public static boolean isObstructed(World world, int i, int j, int k) {
		boolean isObstructed = !world.isAirBlock(i, j, k);

		if (isObstructed) {
			Block block = Block.blocksList[world.getBlockId(i, j, k)];

			if (block != null) {
				if (!block.blockMaterial.blocksMovement()) {
					isObstructed = false;
				}
			}
		}

		return isObstructed;
	}

	/** Tries to store an item entity into an inventory (if there is one) at the specified position. */
	public static boolean storeEntityItemAt(World world, int i, int j, int k, EntityItem entity) {
		IInventory inventory = InventoryUtil.getCompositeInventoryAt(world, i, j, k);

		if (inventory != null && entity != null && entity.isEntityAlive()) {
			ItemStack stackToStore = entity.getEntityItem();

			if (stackToStore != null && InventoryUtil.storeItemInInventory(inventory, stackToStore)) {
				playSFXChest(world, i, j, k);

				if (stackToStore.stackSize <= 0) {
					entity.setDead();
					stackToStore.stackSize = 0;
					return true;
				}
			}
		}

		return false;
	}

	/** Packs items at a specified position into stacks of the same type; reduces lag. */
	//@Deprecated I think the game does this automagically now, but whatever ;)
	public static void packItems(World world, int i, int j, int k) {
		List<EntityItem> itemList = world.getEntitiesWithinAABB(net.minecraft.entity.item.EntityItem.class, AxisAlignedBB.getBoundingBox(i, j, k, i + 1, j + 1, k + 1));

		if (itemList.size() < 5) {
			return;
		}

		nextItem:
			for (EntityItem entityItem1 : itemList) {
				if (entityItem1 == null || entityItem1.isDead || entityItem1.getEntityItem() == null) {
					continue nextItem;
				}
				if (entityItem1.getEntityItem().stackSize < 1) {
					entityItem1.setDead();
					continue nextItem;
				}
				if (entityItem1.getEntityItem().isItemStackDamageable()) {
					continue nextItem;
				} 
				if (entityItem1.getEntityItem().isItemEnchanted()) {
					continue nextItem;
				}
				if (!entityItem1.getEntityItem().isStackable()) {
					continue nextItem;
				} 

				ItemStack stackTarget = entityItem1.getEntityItem();

				if (stackTarget.stackSize == stackTarget.getMaxStackSize()) {
					continue nextItem;
				}
				for (EntityItem entityItem2 : itemList) {
					if (entityItem2.isDead) {
						continue nextItem;
					}

					ItemStack stackAdded = entityItem2.getEntityItem();

					if (entityItem2 == entityItem1) {
						continue;
					}
					if (stackTarget.isItemEqual(stackAdded)) {
						if (stackTarget.stackSize < stackTarget.getMaxStackSize()) {
							int sizeRemain = stackTarget.getMaxStackSize() - stackTarget.stackSize;

							if (sizeRemain >= stackAdded.stackSize) {
								stackTarget.stackSize += stackAdded.stackSize;
								entityItem2.setDead();
							} else {
								stackTarget.stackSize = stackTarget.getMaxStackSize();
								stackAdded.stackSize -= sizeRemain;
								continue nextItem;
							}
						}
					}
				} 
			}
	}


	/** Stores items into a Minecart within a 1 block radius. */
	public static boolean storeItemIntoMinecart(World world, int i, int j, int k, EntityItem entityItem) {
		List<EntityMinecart> hitList = world.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(i, j, k, i + 1, j + 1, k + 1).expand(1.0D, 1.0D, 1.0D));

		if (hitList.size() > 0) {
			for (EntityMinecart cart : hitList) {
				if (cart == null || cart.getMinecartType() != 1) {
					continue;
				}

				IInventory inventory = (IInventory)cart; //TODO

				if (entityItem != null && entityItem.isEntityAlive()) {
					ItemStack stackToStore = entityItem.getEntityItem();

					if (stackToStore != null && InventoryUtil.storeItemInInventory(inventory, stackToStore)) {
						playSFXChest(world, i, j, k);

						if (stackToStore.stackSize <= 0) {
							entityItem.setDead();
							stackToStore.stackSize = 0;
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public static void doSpecialItemAction(World world, int i, int j, int k, EntityItem entityItem) {
		if (entityItem == null || entityItem.getEntityItem() == null) {
			return;
		}

		boolean flag = false;
		flag |= entityItem.getEntityItem().itemID == Item.bucketWater.itemID;
		flag |= entityItem.getEntityItem().itemID == Item.bucketEmpty.itemID;
		flag |= entityItem.getEntityItem().itemID == Item.glassBottle.itemID;

		if (!flag) {
			return;
		}
		do {
			if (ConveyorUtil.performSpecialItemActions(world, i, j, k + 1, entityItem)) {
				break;
			}
			if (ConveyorUtil.performSpecialItemActions(world, i, j, k - 1, entityItem)) {
				break;
			}
			if (ConveyorUtil.performSpecialItemActions(world, i + 1, j, k, entityItem)) {
				break;
			}
			if (ConveyorUtil.performSpecialItemActions(world, i - 1, j, k, entityItem)) {
				break;
			}
			if (ConveyorUtil.performSpecialItemActions(world, i, j - 1, k + 1, entityItem)) {
				break;
			}
			if (ConveyorUtil.performSpecialItemActions(world, i, j - 1, k - 1, entityItem)) {
				break;
			}
			if (ConveyorUtil.performSpecialItemActions(world, i + 1, j - 1, k, entityItem)) {
				break;
			}
			if (ConveyorUtil.performSpecialItemActions(world, i - 1, j - 1, k, entityItem)) {
				break;
			}
			if (ConveyorUtil.performSpecialItemActions(world, i, j + 1, k, entityItem)) {
				break;
			}
			if (ConveyorUtil.performSpecialItemActions(world, i, j - 1, k, entityItem)) {
				break;
			}
		} while (false);
	}

	private static boolean performSpecialItemActions(World world, int i, int j, int k, EntityItem entityItem) {
		if (entityItem.getEntityItem().itemID == Item.bucketWater.itemID) {
			if (world.getBlockId(i, j, k) == Block.cauldron.blockID && world.getBlockMetadata(i, j, k) < 3) {
				world.setBlockMetadataWithNotify(i, j, k, 3, 2);
				entityItem.getEntityItem().itemID = Item.bucketEmpty.itemID;
				return true;
			}
		}
		if (entityItem.getEntityItem().itemID == Item.bucketEmpty.itemID) {
			if (world.getBlockId(i, j, k) == Block.waterStill.blockID || world.getBlockId(i, j, k) == Block.waterMoving.blockID && world.getBlockMetadata(i, j, k) == 0) {
				world.setBlockToAir(i, j, k);
				entityItem.getEntityItem().itemID = Item.bucketWater.itemID;
				return true;
			}
		}
		if (entityItem.getEntityItem().itemID == Item.glassBottle.itemID) {
			if (world.getBlockId(i, j, k) == Block.cauldron.blockID && world.getBlockId(i, j, k) > 0) {
				// Decreases water amount
				int meta = world.getBlockMetadata(i, j, k);
				world.setBlockMetadataWithNotify(i, j, k, meta - 1, 2);
				EntityItem entityItemPotion = new EntityItem(world, entityItem.posX, entityItem.posY, entityItem.posZ, new ItemStack(Item.potion.itemID, 1, 0));
				entityItemPotion.motionX = entityItem.motionX;
				entityItemPotion.motionY = entityItem.motionY;
				entityItemPotion.motionZ = entityItem.motionZ;
				entityItemPotion.delayBeforeCanPickup = 7;
				world.spawnEntityInWorld(entityItemPotion);
				entityItem.getEntityItem().stackSize--;

				if (entityItem.getEntityItem().stackSize <= 0) {
					entityItem.getEntityItem().stackSize = 0;
					entityItem.setDead();
				}

				return true;
			}
		}

		return false;
	}

	/** Prevents entities from despawning while on the conveyor. */
	public static void entityPreventDespawning(World world, int i, int j, int k, boolean preventPickup, Entity entity) {
		if (entity instanceof EntityItem) {
			if (preventPickup) {
				((EntityItem) entity).delayBeforeCanPickup = 7;
			}
			if (((EntityItem) entity).age >= 5000) {
				if (world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(i, j, k, i + 1, j + 1, k + 1)).size() < 40) {
					((EntityItem) entity).age = 4000;
				}
			}
		}
		if (entity instanceof EntityXPOrb) {
			if (((EntityXPOrb) entity).xpOrbAge >= 5000) {
				if (world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(i, j, k, i + 1, j + 1, k + 1)).size() < 40) {
					((EntityXPOrb) entity).xpOrbAge = 4000;
				}
			}
		}
	}

	/** Moves the entity on the conveyor belt. */
	public static void moveEntityOnBelt(World world, int i, int j, int k, Entity entity, boolean bordersEnabled, boolean motionEnabled, int moveDirection, double max_horizontal_speed, double horizontal_boost) {
		if (motionEnabled && world.rand.nextInt(35) == 0) {
			@SuppressWarnings("rawtypes")
			List list = world.getEntitiesWithinAABBExcludingEntity(entity, AxisAlignedBB.getBoundingBox(i, j, k, i + 1, j + 1, k + 1));

			if (world.rand.nextInt(list.size() + 1) == 0) {
				ConveyorUtil.playSFXBelt(world, i, j, k);
			}
		}
		if (entity instanceof EntityPlayer) {
			if (((EntityPlayer) entity).inventory.armorItemInSlot(0) != null) {
				//if (((EntityPlayer) entity).inventory.armorItemInSlot(0).itemID == mod_PCtransport.slimeboots.shiftedIndex) {
				return;
				//}
			}
		}
		if (entity instanceof EntityItem) {
			if (entity.motionY > 0.2F) {
				entity.motionY /= 3F;
			}
		}
		if (entity instanceof EntityItem || entity instanceof EntityXPOrb) {
			if (entity.motionY > 0.2) {
				entity.motionY -= 0.1;
			}
		}
		if (entity.stepHeight <= 0.15F) {
			entity.stepHeight = 0.25F;
		}

		entity.motionZ = MathHelper.clamp_float((float) entity.motionZ, (float) -max_horizontal_speed, (float) max_horizontal_speed);
		entity.motionX = MathHelper.clamp_float((float) entity.motionX, (float) -max_horizontal_speed, (float) max_horizontal_speed);

		switch (moveDirection) {
		case 0: // Z--
			if (entity.motionZ >= -max_horizontal_speed && motionEnabled) {
				entity.motionZ -= horizontal_boost;
			}
			if (bordersEnabled) {
				if (entity.posX > i + (1D - BORDERS)) {
					entity.motionX -= BORDER_BOOST;
				}
				if (entity.posX < j + BORDERS) {
					entity.motionX += BORDER_BOOST;
				}
			}

			break;
		case 1: // X++
			if (entity.motionX <= max_horizontal_speed && motionEnabled) {
				entity.motionX += horizontal_boost;
			}
			if (bordersEnabled) {
				if (entity.posZ > k + BORDERS) {
					entity.motionZ -= BORDER_BOOST;
				}
				if (entity.posZ < k + (1D - BORDERS)) {
					entity.motionZ += BORDER_BOOST;
				}
			}

			break;
		case 2: // Z++
			if (entity.motionZ <= max_horizontal_speed && motionEnabled) {
				entity.motionZ += horizontal_boost;
			}
			if (bordersEnabled) {
				if (entity.posX > i + (1D - BORDERS)) {
					entity.motionX -= BORDER_BOOST;
				}
				if (entity.posX < i + BORDERS) {
					entity.motionX += BORDER_BOOST;
				}
			}

			break;
		case 3: // X--
			if (entity.motionX >= -max_horizontal_speed && motionEnabled) {
				entity.motionX -= horizontal_boost; /* entity.motionY+=0.1; */
			}
			if (bordersEnabled) {
				if (entity.posZ > k + BORDERS) {
					entity.motionZ -= BORDER_BOOST;
				}
				if (entity.posZ < k + (1D - BORDERS)) {
					entity.motionZ += BORDER_BOOST;
				}
			}

			break;
		}
	}

	/** Determines which entities are to be ignored by the conveyor belt. */
	public static boolean isEntityIgnored(Entity entity) {
		if (entity == null) {
			return true;
		}
		if (!entity.isEntityAlive()) {
			return true;
		}
		if (entity instanceof EntityPlayer && ((EntityPlayer) entity).isSneaking()) {
			return true;
		}
		if (entity instanceof EntityDiggingFX) {
			return false;
		}
		if (entity instanceof EntityFX) {
			return true;
		}

		return false;
	}


	/** Checks each side to determine if it can store the item(s). */
	public static boolean determineSidesToStore(World world, int i, int j, int k, EntityItem entityItem) {
		if (storeItemIntoMinecart(world, i, j, k, entityItem)) {
			return true;
		}
		if (storeEntityItemAt(world, i - 1, j, k, entityItem)) {
			return true;
		}
		if (storeEntityItemAt(world, i + 1, j, k, entityItem)) {
			return true;
		}
		if (storeEntityItemAt(world, i, j - 1, k, entityItem)) {
			return true;
		}
		if (storeEntityItemAt(world, i, j + 1, k, entityItem)) {
			return true;
		}
		if (storeEntityItemAt(world, i, j, k - 1, entityItem)) {
			return true;
		}
		if (storeEntityItemAt(world, i, j, k + 1, entityItem)) {
			return true;
		}

		return false;
	}


	/** Determines if an item is beyond the storage border. */
	public static boolean isItemBeyondStorageBorder(World world, int rotation, int i, int j, int k, Entity entity, float border) {
		switch (rotation) {
		case 0: // Z--
			if (entity.posZ > k + 1 - border) {
				return false;
			}

			break;
		case 1: // X++
			if (entity.posX < i + border) {
				return false;
			}

			break;
		case 2: // Z++
			if (entity.posZ < k + border) {
				return false;
			}

			break;
		case 3: // X--
			if (entity.posX > i + 1 - border) {
				return false;
			}

			break;
		}

		return true;
	}


	/** The item entity's movement on a conveyor belt. */
	public static void createEntityItemOnBelt(World world, int invX, int invY, int invZ, int beltX, int beltY, int beltZ, ItemStack stack) {
		EntityItem entityItem = new EntityItem(world, beltX + 0.5D, beltY + 0.3D, beltZ + 0.5D, stack);
		entityItem.motionX = 0.0D;
		entityItem.motionY = 0.0D;
		entityItem.motionZ = 0.0D;
		Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(beltX, beltY, beltZ); // TODO
		entityItem.posX += 0.43D * vec3.xCoord;
		entityItem.posZ += 0.43D * vec3.zCoord;
		entityItem.delayBeforeCanPickup = 7;
		world.spawnEntityInWorld(entityItem);
	}


	/** Stores an item into a nearby inventory. */
	public static boolean storeNearby(World world, int i, int j, int k, EntityItem entityItem, boolean ignoreStorageBorder) {
		if (storeItemIntoMinecart(world, i, j, k, entityItem)) {
			return true;
		}
		if (!ignoreStorageBorder && entityItem.posY > j + 1 - STORAGE_BORDER_V) {
			return false;
		}

		int rot = getRotation(world.getBlockMetadata(i, j, k));

		if (isItemBeyondStorageBorder(world, rot, i, j, k, entityItem, STORAGE_BORDER) || ignoreStorageBorder) {
			if (rot == 0 && storeEntityItemAt(world, i - 1, j, k, entityItem)) {
				return true;
			}
			if (rot == 1 && storeEntityItemAt(world, i + 1, j, k, entityItem)) {
				return true;
			}
			if (rot == 2 && storeEntityItemAt(world, i, j, k - 1, entityItem)) {
				return true;
			}
			if (rot == 3 && storeEntityItemAt(world, i - 1, j, k, entityItem)) {
				return true;
			}
			if (rot != 0 && rot != 2 && storeEntityItemAt(world, i, j, k - 1, entityItem)) {
				return true;
			}
			if (rot != 1 && rot != 3 && storeEntityItemAt(world, i + 1, j, k, entityItem)) {
				return true;
			}
			if (rot != 2 && rot != 0 && storeEntityItemAt(world, i, j, k + 1, entityItem)) {
				return true;
			}
			if (rot != 3 && rot != 1 && storeEntityItemAt(world, i - 1, j, k, entityItem)) {
				return true;
			}
			if (storeEntityItemAt(world, i, j + 1, k, entityItem)) {
				return true;
			}
		}

		return false;
	}

	public static int getRotation(int meta) {
		switch (meta) {
		case 0:
		case 6:
			return 0;
		case 1:
		case 7:
			return 1;
		case 8:
		case 14:
			return 2;
		case 9:
		case 15:
			return 3;
		}
		
		return 0;
	}
}
