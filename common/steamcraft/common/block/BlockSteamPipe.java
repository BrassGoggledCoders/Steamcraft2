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
 * File created @ [Feb 17, 2014, 2:44:47 PM]
 */
package common.steamcraft.common.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import common.steamcraft.common.block.machines.BlockContainerMod;
import common.steamcraft.common.block.tile.TileEntitySteamPipe;
import common.steamcraft.common.lib2.CreativeTabsMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author MrArcane111
 *
 */
public class BlockSteamPipe extends BlockContainerMod {
	public BlockSteamPipe(int id, Material mat) {
		super(id, mat.iron);
		this.setCreativeTab(CreativeTabsMod.tabSCBlocks);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntitySteamPipe te = (TileEntitySteamPipe)world.getBlockTileEntity(x, y, z);

		if(te != null) {
			te.cycleConnections(-1); // Maybe add an item to do this?
			return true;
		}

		return false;
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
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySteamPipe();
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		return side + 1;
	}

	private double[] getBounds(World world, int x, int y, int z) {
		double[] result = new double[6];
		double d1 = (result[2] = 0.25D); result[1] = d1; result[0] = d1;
		double d2 = (result[5] = 0.75D); result[4] = d1; result[3] = d2;

		TileEntity te = world.getBlockTileEntity(x, y, z);

		if ((te != null) && (te instanceof TileEntitySteamPipe)) {
			TileEntitySteamPipe tePipe = (TileEntitySteamPipe)te;
			ForgeDirection side1 = ForgeDirection.getOrientation(tePipe.getConnection1());
			ForgeDirection side2 = ForgeDirection.getOrientation(tePipe.getConnection2());

			if ((side1.offsetX < 0) || (side2.offsetX < 0)) {
				result[0] = 0.0D;
			}
			if ((side1.offsetX > 0) || (side2.offsetX > 0)) {
				result[3] = 1.0D;
			}
			if ((side1.offsetY < 0) || (side2.offsetY < 0)) {
				result[1] = 0.0D;
			}
			if ((side1.offsetY > 0) || (side2.offsetY > 0)) {
				result[4] = 1.0D;
			}
			if ((side1.offsetZ < 0) || (side2.offsetZ < 0)) {
				result[2] = 0.0D;
			}
			if ((side1.offsetZ > 0) || (side2.offsetZ > 0)) {
				result[5] = 1.0D;
			}
		}

		return result;
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List boxList, Entity entity) {
		float[] bounds = new float[6];
		float f1 = (bounds[2] = 0.25F); bounds[1] = f1; bounds[0] = f1;
		float f2 = (bounds[5] = 0.75F); bounds[4] = f2; bounds[3] = f2;

		TileEntity te = world.getBlockTileEntity(x, y, z);

		if ((te != null) && ((te instanceof TileEntitySteamPipe))) {
			TileEntitySteamPipe pipe = (TileEntitySteamPipe)te;
			ForgeDirection side = ForgeDirection.getOrientation(pipe.getConnection1());

			if (side.offsetX < 0) {
				bounds[0] = 0.0F;
			}
			if (side.offsetX > 0) {
				bounds[3] = 1.0F;
			}
			if (side.offsetY < 0) {
				bounds[1] = 0.0F;
			}
			if (side.offsetY > 0) {
				bounds[4] = 1.0F;
			}
			if (side.offsetZ < 0) {
				bounds[2] = 0.0F;
			}
			if (side.offsetZ > 0) {
				bounds[5] = 1.0F;
			}
			
			this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
			super.addCollisionBoxesToList(world, x, y, z, box, boxList, entity);
			float f3 = (bounds[2] = 0.25F); bounds[1] = f3; bounds[0] = f3;
			float f4 = (bounds[5] = 0.75F); bounds[4] = f4; bounds[3] = f4;

			side = ForgeDirection.getOrientation(pipe.getConnection2());

			if (side.offsetX < 0) {
				bounds[0] = 0.0F;
			}
			if (side.offsetX > 0) {
				bounds[3] = 1.0F;
			}
			if (side.offsetY < 0) {
				bounds[1] = 0.0F;
			}
			if (side.offsetY > 0) {
				bounds[4] = 1.0F;
			}
			if (side.offsetZ < 0) {
				bounds[2] = 0.0F;
			}
			if (side.offsetZ > 0) {
				bounds[5] = 1.0F;
			}
			
			this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
			super.addCollisionBoxesToList(world, x, y, z, box, boxList, entity);
		}

		this.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		double[] bounds = getBounds(world, x, y, z);
		return AxisAlignedBB.getAABBPool().getAABB(bounds[0] + x, bounds[1] + y, bounds[2] + z, bounds[3] + x, bounds[4] + y, bounds[5] + z);
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec) {
		double[] bounds = getBounds(world, x, y, z);
		this.setBlockBounds((float)bounds[0], (float)bounds[1], (float)bounds[2], (float)bounds[3], (float)bounds[4], (float)bounds[5]);
		MovingObjectPosition result = super.collisionRayTrace(world, x, y, z, startVec, endVec);
		this.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
		return result;
	}
}
