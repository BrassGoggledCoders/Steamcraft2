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
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.lib.DamageSourceHandler;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;

/**
 * @author warlordjones
 *
 */
public class TileTeslaCoil extends TileEntity implements IEnergyReceiver
{
	private static int RFPerTick = 500;
	private static int RFPerZap = 1000;
	private static byte RangePerDir = 5;
	private static byte Damage = 3;
	
	private final EnergyStorage buffer = new EnergyStorage(5000, RFPerTick);

	@SuppressWarnings("rawtypes")
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{
			if(this.buffer.getEnergyStored() >= RFPerZap)
			{
				Random random = this.worldObj.rand;
				if(this.getWorldObj().isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord))
				{
					AxisAlignedBB axisalignedbb2 = AxisAlignedBB.getBoundingBox((double) this.xCoord - RangePerDir, (double) this.yCoord - RangePerDir,
							(double) this.zCoord - RangePerDir, this.xCoord + RangePerDir, this.yCoord + RangePerDir, this.zCoord + RangePerDir);
					
					List list2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb2);
					Iterator iterator2 = list2.iterator();
					EntityPlayer player;
	
					while(iterator2.hasNext() && this.buffer.getEnergyStored() >= RFPerZap)
					{
						player = (EntityPlayer) iterator2.next();
						player.attackEntityFrom(DamageSourceHandler.electrocution, Damage);
						this.worldObj.spawnParticle("reddust", player.posX + (random.nextFloat() / 2), (player.posY - 0.5F) + (random.nextFloat() / 2),
								player.posZ + (random.nextFloat() / 2), 0, 0, 0);
						
						this.buffer.modifyEnergyStored(-RFPerZap);
					}
				}
				else if((this.getWorldObj().getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == Blocks.emerald_block))
				{
					AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double) this.xCoord - RangePerDir, (double) this.yCoord - RangePerDir,
							(double) this.zCoord - RangePerDir, this.xCoord + RangePerDir, this.yCoord + RangePerDir, this.zCoord + RangePerDir);
					
					List list = this.worldObj.getEntitiesWithinAABB(EntityVillager.class, axisalignedbb);
					Iterator iterator = list.iterator();
					EntityVillager villager;
	
					while(iterator.hasNext() && this.buffer.getEnergyStored() >= RFPerZap)
					{
						villager = (EntityVillager) iterator.next();
						villager.attackEntityFrom(DamageSourceHandler.electrocution, Damage);
						this.worldObj.spawnParticle("reddust", villager.posX + (random.nextFloat() / 3), villager.posY + (random.nextFloat() / 3),
								villager.posZ + (random.nextFloat() / 3), 0, 0, 0);
						
						this.buffer.modifyEnergyStored(-RFPerZap);
					}
				}
				else
				{
					AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double) this.xCoord - RangePerDir, (double) this.yCoord - RangePerDir,
							(double) this.zCoord - RangePerDir, this.xCoord + RangePerDir, this.yCoord + RangePerDir, this.zCoord + RangePerDir);
					
					List list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, axisalignedbb);
					Iterator iterator = list.iterator();
					EntityLiving living;
	
					while(iterator.hasNext() && this.buffer.getEnergyStored() >= RFPerZap)
					{
						living = (EntityLiving) iterator.next();
						living.attackEntityFrom(DamageSourceHandler.electrocution, Damage);
						this.worldObj.spawnParticle("reddust", living.posX + (random.nextFloat() / 2), living.posY + (random.nextFloat() / 2),
								living.posZ + (random.nextFloat() / 2), 0, 0, 0);
						
						this.buffer.modifyEnergyStored(-RFPerZap);
					}
				}
			}
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
