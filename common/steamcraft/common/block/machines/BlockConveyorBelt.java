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
 * File created @ [Feb 10, 2014, 2:17:44 PM]
 */
package common.steamcraft.common.block.machines;

import java.util.Random;

import common.steamcraft.common.block.BlockMod;
import common.steamcraft.common.util.ConveyorUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author MrArcane111
 *
 */
public class BlockConveyorBelt extends BlockMod {
	/** */
	public boolean hasSpeedBoost = false;

	public BlockConveyorBelt(int id) {
		super(id, Material.iron);
		this.setTickRandomly(false);
		this.setUnlocalizedName("conveyor");
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, ConveyorUtil.HEIGHT, 1.0F);
		//this.timePeriod = 0;
		this.setStepSound(this.soundClothFootstep);
	}

	@Override
	public int tickRate(World world) {
		return 1;
	}

	@Override
	public boolean canProvidePower() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int l, float hitX, float hitY, float hitZ) {
		return false;// ConveyorHelper.blockActivated(world, i, j, k, player);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		return world.isBlockNormalCube(i, j - 1, k);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase living, ItemStack stack) {
		int l = ((MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
        world.setBlockMetadataWithNotify(i, j, k, l, 3);
        //return true;
		//int metadata = this.getPlacedMeta(living);
		//world.setBlockMetadataWithNotify(i, j, k, metadata, 2);
	}

	public int getPlacedMeta(EntityLivingBase living) {
		int orientation = MathHelper.floor_double(((living.rotationYaw * 4F) / 360F) + 2.5D) & 3;

		if (living.isSneaking()) {
			orientation = this.reverseSide(orientation);
		}
		if (orientation == 2) {
			orientation = 8;
		}
		if (orientation == 3) {
			orientation = 9;
		}

		return orientation;
	}

	public int reverseSide(int side) {
		if (side == 0) {
			side = 2;
		} else if (side == 2) {
			side = 0;
		} else if (side == 1) {
			side = 3;
		} else if (side == 3) {
			side = 1;
		}

		return side;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess iBlockAccess, int i, int j, int k, int side) {
		return 0;
	}

	public int isProvidingStrongPower(IBlockAccess iBlockAccess, int i, int j, int k, int side) {
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		if (ConveyorUtil.isEntityIgnored(entity)) {
			return;
		}
		if (entity instanceof EntityItem) {
			ConveyorUtil.packItems(world, i, j, k);
		}
		if (entity instanceof EntityItem && !this.hasSpeedBoost) {
			ConveyorUtil.doSpecialItemAction(world, i, j, k, (EntityItem) entity);

			if (ConveyorUtil.storeNearby(world, i, j, k, (EntityItem) entity, false)) {
				return;
			}
		}

		int direction = ConveyorUtil.getRotation(world.getBlockMetadata(i, j, k));

		switch (direction) {
		case 0: // Z--
			k--;
			break;
		case 1: // X++
			i++;
			break;
		case 2: // Z++
			k++;
			break;
		case 3: // X--
			i--;
			break;
		}

		boolean leadsToNowhere = ConveyorUtil.isObstructed(world, i, j, k);
		leadsToNowhere = leadsToNowhere && ConveyorUtil.isItemBeyondStorageBorder(world, direction, i, j, k, entity, ConveyorUtil.STORAGE_BORDER_LONG);

		if (!leadsToNowhere) {
			ConveyorUtil.entityPreventDespawning(world, i, j, k, true, entity);
		}

		double speed_max = ConveyorUtil.MAX_HORIZONTAL_SPEED;

		if (this.hasSpeedBoost) {
			speed_max *= 2.0D;
		}

		double boost = ConveyorUtil.HORIZONTAL_BOOST;

		if (this.hasSpeedBoost) {
			boost *= 2.0D;
		}

		ConveyorUtil.moveEntityOnBelt(world, i, j, k, entity, true, !leadsToNowhere, direction, speed_max, boost);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return AxisAlignedBB.getBoundingBox(i, 0.0F + j, k, (i + 1), (j + ConveyorUtil.HEIGHT_COLLISION + 0.0F), (k + 1));
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
		float f = 0;
		f = 0.0F + ConveyorUtil.HEIGHT_SELECTED;
		return AxisAlignedBB.getBoundingBox(i, 0.0F + j, k, (i + 1), j + f, (float) k + 1);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0F + ConveyorUtil.HEIGHT, 1.0F);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 0.6F, 1.0F);
	}

	//@Override
	//public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int i, int j, int k, int side) {
	//	return side != 1;
	//}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	//@Override
	//public int getRenderType() {
	//	return 0;
	//}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public int getMobilityFlag() {
		return 0;
	}
}
