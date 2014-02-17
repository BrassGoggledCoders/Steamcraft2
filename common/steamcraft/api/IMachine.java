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
 * File created @ [Feb 1, 2014, 8:51:16 PM]
 */
package common.steamcraft.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author MrArcane111
 *
 */
public abstract interface IMachine {
	/** */
	public abstract boolean isActive();
	
	/** */
	public abstract int getFacingDirection();

	/** */
	public abstract void setFacingDirection(int paramInt);

	/** */
	public abstract void a(NBTTagCompound paramNBTTagCompound);

	/** */
	public abstract void b(NBTTagCompound paramNBTTagCompound);

	/** */
	public abstract void writePacket(DataOutputStream paramDataOutputStream) throws IOException;

	/** */
	public abstract void readPacket(DataInputStream paramDataInputStream) throws IOException;

	/** */
	public abstract boolean isSolidOnSide(ForgeDirection paramForgeDirection);

	/** */
	public abstract void onBreak();

	/** */
	public abstract void onBlockPlacedBy(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving);

	/** */
	public abstract boolean onActivated(EntityPlayer paramEntityPlayer, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3);

	/** */
	public abstract ArrayList getBlockDropped();

	/** */
	public abstract float getHardness();
}
