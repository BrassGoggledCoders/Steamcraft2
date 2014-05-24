/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Mar 12, 2014, 5:46:41 PM]
 */
package steamcraft.api.machines;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMachine.
 *
 * @author Surseance (Johnny Eatmon)
 */
public interface IMachine
{

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public abstract boolean isActive();

	/**
	 * Gets the facing direction.
	 *
	 * @return the facing direction
	 */
	public abstract int getFacingDirection();

	/**
	 * Sets the facing direction.
	 *
	 * @param paramInt the new facing direction
	 */
	public abstract void setFacingDirection(int paramInt);

	/**
	 * A.
	 *
	 * @param paramNBTTagCompound the param nbt tag compound
	 */
	public abstract void a(NBTTagCompound paramNBTTagCompound);

	/**
	 * B.
	 *
	 * @param paramNBTTagCompound the param nbt tag compound
	 */
	public abstract void b(NBTTagCompound paramNBTTagCompound);

	/**
	 * Write packet.
	 *
	 * @param paramDataOutputStream the param data output stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void writePacket(DataOutputStream paramDataOutputStream)
			throws IOException;

	/**
	 * Read packet.
	 *
	 * @param paramDataInputStream the param data input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void readPacket(DataInputStream paramDataInputStream)
			throws IOException;

	/**
	 * Checks if is solid on side.
	 *
	 * @param paramForgeDirection the param forge direction
	 * @return true, if is solid on side
	 */
	public abstract boolean isSolidOnSide(ForgeDirection paramForgeDirection);

	/**
	 * On break.
	 */
	public abstract void onBreak();

	/**
	 * On block placed by.
	 *
	 * @param paramWorld the param world
	 * @param paramInt1 the param int1
	 * @param paramInt2 the param int2
	 * @param paramInt3 the param int3
	 * @param paramEntityLiving the param entity living
	 */
	public abstract void onBlockPlacedBy(World paramWorld, int paramInt1,
			int paramInt2, int paramInt3, EntityLiving paramEntityLiving);

	/**
	 * On activated.
	 *
	 * @param paramEntityPlayer the param entity player
	 * @param paramInt the param int
	 * @param paramFloat1 the param float1
	 * @param paramFloat2 the param float2
	 * @param paramFloat3 the param float3
	 * @return true, if successful
	 */
	public abstract boolean onActivated(EntityPlayer paramEntityPlayer,
			int paramInt, float paramFloat1, float paramFloat2,
			float paramFloat3);

	/**
	 * Gets the block dropped.
	 *
	 * @return the block dropped
	 */
	public abstract ArrayList<Block> getBlockDropped();

	/**
	 * Gets the hardness.
	 *
	 * @return the hardness
	 */
	public abstract float getHardness();
}
