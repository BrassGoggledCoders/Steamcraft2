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
package steamcraft.common.tiles.energy;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;

import net.minecraftforge.common.util.ForgeDirection;

import steamcraft.common.lib.DamageSourceHandler;

/**
 * @author warlordjones
 * 
 */
public class TileTeslaCoil extends TileEntity implements IEnergyReceiver
{
	private static int RFPerTick = 500;
	private static int RFPerZap = 1000;
	private final EnergyStorage buffer = new EnergyStorage(5000, RFPerTick);

	@SuppressWarnings("rawtypes")
	@Override
	public void updateEntity()
	{
		short inputEnergy = (short) this.receiveEnergy(ForgeDirection.UNKNOWN, RFPerTick, true);

		if(inputEnergy > 0)
			for(ForgeDirection direction : EnumSet.allOf(ForgeDirection.class))
				if(inputEnergy > 0)
				{
					TileEntity tileEntity = this.worldObj.getTileEntity(this.xCoord - direction.offsetX, this.yCoord - direction.offsetY, this.zCoord
							- direction.offsetZ);

					if(tileEntity instanceof IEnergyHandler)
						inputEnergy -= this.receiveEnergy(ForgeDirection.UNKNOWN,
								((IEnergyHandler) tileEntity).extractEnergy(direction.getOpposite(), inputEnergy, false), false);
				}
				else
					break;
		if(this.buffer.getEnergyStored() > RFPerZap)
		{
			Random random = this.worldObj.rand;
			if(this.getWorldObj().isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)
					&& (this.buffer.getEnergyStored() > RFPerZap))
			{
				AxisAlignedBB axisalignedbb2 = AxisAlignedBB.getBoundingBox((double) this.xCoord - 5, (double) this.yCoord - 5,
						(double) this.zCoord - 5, this.xCoord + 5, this.yCoord + 5, this.zCoord + 5);
				List list2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb2);
				Iterator iterator2 = list2.iterator();
				EntityPlayer player;

				while(iterator2.hasNext())
				{
					player = (EntityPlayer) iterator2.next();
					player.attackEntityFrom(DamageSourceHandler.electrocution, 3);
					this.worldObj.spawnParticle("reddust", player.posX + (random.nextFloat() / 2), (player.posY - 0.5F) + (random.nextFloat() / 2),
							player.posZ + (random.nextFloat() / 2), 0, 0, 0);
					this.buffer.extractEnergy(RFPerZap, false);
				}
			}
			else if((this.getWorldObj().getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == Blocks.emerald_block)
					&& (this.buffer.getEnergyStored() > RFPerZap))
			{
				AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double) this.xCoord - 5, (double) this.yCoord - 5,
						(double) this.zCoord - 5, this.xCoord + 5, this.yCoord + 5, this.zCoord + 5);
				List list = this.worldObj.getEntitiesWithinAABB(EntityVillager.class, axisalignedbb);
				Iterator iterator = list.iterator();
				EntityVillager villager;

				while(iterator.hasNext())
				{
					villager = (EntityVillager) iterator.next();
					villager.attackEntityFrom(DamageSourceHandler.electrocution, 3);
					this.worldObj.spawnParticle("reddust", villager.posX + (random.nextFloat() / 3), villager.posY + (random.nextFloat() / 3),
							villager.posZ + (random.nextFloat() / 3), 0, 0, 0);
					this.buffer.extractEnergy(RFPerZap, false);
				}
			}
			else if(this.buffer.getEnergyStored() > RFPerZap)
			{
				AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double) this.xCoord - 5, (double) this.yCoord - 5,
						(double) this.zCoord - 5, this.xCoord + 5, this.yCoord + 5, this.zCoord + 5);
				List list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, axisalignedbb);
				Iterator iterator = list.iterator();
				EntityLiving living;

				while(iterator.hasNext())
				{
					living = (EntityLiving) iterator.next();
					living.attackEntityFrom(DamageSourceHandler.electrocution, 3);
					this.worldObj.spawnParticle("reddust", living.posX + (random.nextFloat() / 2), living.posY + (random.nextFloat() / 2),
							living.posZ + (random.nextFloat() / 2), 0, 0, 0);
					this.buffer.extractEnergy(RFPerZap, false);
				}
			}
			this.buffer.extractEnergy(RFPerTick, false);
		}
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

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return this.buffer.receiveEnergy(maxReceive, simulate);
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
}
