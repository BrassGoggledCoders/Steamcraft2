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
 * File created @ [Feb 4, 2014, 5:19:04 PM]
 */
package common.steamcraft.common.block.machines;

import java.util.Random;

import common.steamcraft.common.block.BlockMod;
import common.steamcraft.common.lib2.LibInfo;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author MrArcane111 & Proloe
 *
 */
public class BlockBattery extends BlockMod
{
	@SideOnly(Side.CLIENT)
	private Icon batteryIconTop;
	
	private Random random = new Random();
	
	protected BlockBattery(int id)
	{
		super(id, Material.circuits);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		this.setLightOpacity(0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{	
		/** If top or bottom */
		if(side == 0 || side == 1)
			return this.batteryIconTop;
		else
			return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		this.blockIcon = icon.registerIcon(LibInfo.SC2_PREFIX + "batteryside");
		this.batteryIconTop = icon.registerIcon(LibInfo.SC2_PREFIX + "batterytop");
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return this.getSelectedBoundingBoxFromPool(world, i, j, k);
	}

	@Override
	public int tickRate(World world) {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		world.setBlockMetadataWithNotify(i, j, k, 1, 2);
		world.notifyBlocksOfNeighborChange(i, j, k, blockID);
		world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
		world.markBlockRangeForRenderUpdate(i, j, k, i, j, k);
		world.scheduleBlockUpdate(i, j, k, blockID, this.tickRate(world));
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int oldID, int oldMeta) {
		world.notifyBlocksOfNeighborChange(i, j, k, this.blockID);
		world.notifyBlocksOfNeighborChange(i + 1, j, k, this.blockID);
		world.notifyBlocksOfNeighborChange(i - 1, j, k, this.blockID);
		world.notifyBlocksOfNeighborChange(i, j, k + 1, this.blockID);
		world.notifyBlocksOfNeighborChange(i, j, k - 1, this.blockID);
		world.notifyBlocksOfNeighborChange(i, j + 1, k, this.blockID);
		world.notifyBlocksOfNeighborChange(i, j - 1, k, this.blockID);
		super.breakBlock(world, i, j, k, oldID, oldMeta);
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess iBlockAccess, int i, int j, int k, int side) {
		if(!this.canProvidePower()) {
            return 0;
        } else {
            return MathHelper.clamp_int(random.nextInt(10), 0, 20);
        }
    }
	
	@Override
	public int isProvidingStrongPower(IBlockAccess iBlockAccess, int i, int j, int k, int side) {
		// Fancy return statements, punk! Whatcha gonna do 'bout it?
        return side != 0 || side != 1 ? this.isProvidingWeakPower(iBlockAccess, i, j, k, side) : 0;
    }
	
	@Override
	public boolean canProvidePower() {
		return true;
	}

	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		double d = (double)((float)i + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
		double d1 = (double)((float)j + 0.2F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
		double d2 = (double)((float)k + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
		double d4 = 0.5D;
		world.spawnParticle("reddust", d - d4, d1, d2, -1.0D, 0.7D, 1.0D);
		world.spawnParticle("reddust", d + d4, d1, d2, -1.0D, 0.7D, 1.0D);
		world.spawnParticle("reddust", d, d1, d2 - d4, -1.0D, 0.7D, 1.0D);
		world.spawnParticle("reddust", d, d1, d2 + d4, -1.0D, 0.7D, 1.0D);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public int idDropped(int i, Random random, int j) {
		return this.blockID;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int i, int j, int k, int side) {
		if(side == 1) {
			return true;
		}
		if(!super.shouldSideBeRendered(iBlockAccess, i, j, k, side)) {
			return false;
		}
		if(side == 0) {
			return true;
		} else {
			return iBlockAccess.getBlockId(i, j, k) != blockID;
		}
	}
}
