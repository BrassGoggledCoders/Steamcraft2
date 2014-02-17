package common.steamcraft.mod.common.block.tile;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.api.energy.EnergyStorageHandler;
import common.steamcraft.mod.common.block.machines.BlockCompressor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGenerator extends TileEntityElectricMachine implements ISidedInventory
{

	public int furnaceBurnTime = 0;
	public int currentItemBurnTime = 0;
	
	public TileEntityGenerator()
	{
		super();
		this.energy = new EnergyStorageHandler();
		this.energy.setCapacity(10000);
		this.energy.setMaxTransfer(10);
		inventory = new ItemStack[3];
	}
	
	@Override
	public EnumSet<ForgeDirection> getOutputDirections()
	{
		return EnumSet.allOf(ForgeDirection.class);
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return inventory[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (inventory[par1] != null)
		{
			ItemStack var3;

			if (inventory[par1].stackSize <= par2)
			{
				var3 = inventory[par1];
				inventory[par1] = null;
				return var3;
			}
			else
			{
				var3 = inventory[par1].splitStack(par2);

				if (inventory[par1].stackSize == 0)
					inventory[par1] = null;

				return var3;
			}
		}
		else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (inventory[par1] != null)
		{
			ItemStack var2 = inventory[par1];
			inventory[par1] = null;
			return var2;
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		inventory[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
			par2ItemStack.stackSize = this.getInventoryStackLimit();
	}
	
	@Override
	public String getInvName()
	{
		return "CompressorMachine";
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		inventory = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < inventory.length)
				inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}

		furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
		currentItemBurnTime = par1NBTTagCompound.getShort("ItemTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short) furnaceBurnTime);
		par1NBTTagCompound.setShort("ItemTime", (short) currentItemBurnTime);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inventory.length; ++i)
			if (inventory[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		par1NBTTagCompound.setTag("Items", nbttaglist);
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }
	
	@SideOnly(Side.CLIENT)
	public int getEnergyScaled(int par1)
	{
		return (int) (this.energy.getEnergy() / par1);
	}
	
	public boolean hasEnergy()
	{
		return this.energy.getEnergy() > 0;
	}
	
	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}
	
	@Override
	public void updateEntity()
	{
		boolean var1 = furnaceBurnTime > 0;
		boolean var2 = false;

		if (!worldObj.isRemote)
		{
			if(inventory[2]!=null && energy.getEmptySpace() > 0)
				this.discharge(inventory[2]);
			
			if(this.getEnergy(null)!=0)
			{
				if(inventory[1]!=null)
					this.recharge(inventory[1]);
				this.energy.modifyEnergyStored(-this.produce(this.energy.getMaxExtract()));
			}
			
			if (this.getItemBurnTime()>0 && furnaceBurnTime==0)
			{	
				currentItemBurnTime = furnaceBurnTime = this.getItemBurnTime()/4;
				
				if(this.inventory[0].stackSize==1)
					inventory[0]=inventory[0].getItem().getContainerItemStack(inventory[0]);
				else
					--inventory[0].stackSize;
			}
			
			if(furnaceBurnTime > 0)
			{
				this.energy.modifyEnergyStored(this.currentItemBurnTime/40);
				furnaceBurnTime--;
			}

			if (var1 != furnaceBurnTime > 0)
			{
				var2 = true;
				BlockCompressor.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (var2)
			this.onInventoryChanged();
	}
	
	private int getItemBurnTime()
	{
		if (inventory[0] == null)
			return 0;
		
		return TileEntityFurnace.getItemBurnTime(inventory[0]);
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false : par1EntityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D,
				zCoord + 0.5D) <= 64.0D;
	}
	
	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return new int[]{0};
	}
	
	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3)
	{
		if(par1 == 0)
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return false;
	}

	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if(i == 0)
			return true;
		return false;
	}
}
