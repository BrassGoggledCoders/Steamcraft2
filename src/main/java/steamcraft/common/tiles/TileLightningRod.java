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
package steamcraft.common.tiles;

import java.util.Random;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.blocks.machines.BlockLightningRod;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

/**
 * @author warlordjones
 * 
 */
public class TileLightningRod extends TileEntity implements IEnergyHandler
{
	private EnergyStorage buffer = new EnergyStorage(30000, 10000);

	BlockLightningRod block;

	@Override
	public void updateEntity()
	{
		if (this.getWorldObj() != null)
			this.block = (BlockLightningRod) this.getWorldObj().getBlock(this.xCoord, this.yCoord, this.zCoord);

		if (this.worldObj.getWorldInfo().isThundering() && this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord, this.zCoord))
		{
			Random random = new Random();
			int chance = random.nextInt(1000);
			if (chance == 0)
			{
				this.worldObj.addWeatherEffect((new EntityLightningBolt(this.worldObj, this.xCoord, this.yCoord, this.zCoord)));
				this.buffer.receiveEnergy(10000, false);
				System.out.print(this.buffer.getEnergyStored());
				// Todo - fix
				/*
				 * if(!MinecraftServer.getServer().isDedicatedServer()) {
				 * EntityPlayer player = worldObj.getClosestPlayer(xCoord,
				 * yCoord, zCoord, -1); } else {
				 * if(block.getOwner().substring(5) != null) { EntityPlayer
				 * player =
				 * worldObj.getPlayerEntityByName(block.getOwner().substring
				 * (5)); if(player != null)
				 * player.triggerAchievement(InitAchievements.zapAchieve); } }
				 */
			}
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return false;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return this.buffer.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return this.buffer.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return this.buffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return this.buffer.getMaxEnergyStored();
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.buffer.readFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		this.buffer.writeToNBT(tag);
	}

}
