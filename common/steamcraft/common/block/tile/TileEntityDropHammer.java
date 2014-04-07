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
 * File created @ [2 Mar 2014, 21:42:15]
 */
package common.steamcraft.common.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import common.steamcraft.common.block.machines.BlockCompressor;
import common.steamcraft.common.core.handler.recipe.CompressorHandler;
import common.steamcraft.common.core.handler.recipe.CrushingHandler;
import common.steamcraft.common.item.ModItems;
import common.steamcraft.common.util.EnergyUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 * 2 Mar 201421:42:15
 */
public class TileEntityDropHammer extends TileEntityElectricMachine
{	
	private int energyPerTick = 20;
	public int furnaceBurnTime = 0;
	
	public TileEntityDropHammer()
	{
		super();
		this.energy = new EnergyUtils(10000, 128);
		inventory = new ItemStack[3];
	}
	
	@Override
	public String getInvName()
	{
		return "Drop Hammer";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);

		furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		
		par1NBTTagCompound.setShort("BurnTime", (short) furnaceBurnTime);
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1)
	{
		return furnaceBurnTime / par1;
	}
	
	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		boolean var1 = furnaceBurnTime > 0;
		boolean var2 = false;

		if (!worldObj.isRemote)
		{
			if(inventory[2]!=null && energy.getEmptySpace() > 0)
				this.discharge(2, this);
			
			if (this.canSmelt())
			{		
				if(this.getEnergy()==0)
					furnaceBurnTime = 0;
				
				if(this.energy.extractEnergy(this.energyPerTick, true)==this.energyPerTick)
					++furnaceBurnTime;
				
				if (furnaceBurnTime == 250)
				{
					furnaceBurnTime = 0;
					this.smeltItem();
					var2 = true;
				}
			}
			else
				furnaceBurnTime = 0;

			if (var1 != furnaceBurnTime > 0)
			{
				var2 = true;
				BlockCompressor.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (var2)
			this.onInventoryChanged();
	}
		
	private boolean canSmelt()
	{
		if (inventory[0] == null)
			return false;
		if(inventory[1] == null)
			return false;
		if(CrushingHandler.recipe().getResult(inventory[1])==null)
			return false;
		
		if(CrushingHandler.recipe().getResult(inventory[1]).itemID==inventory[0].itemID)
		{
			int chargeLevel = CompressorHandler.recipe().getChargeLevel(inventory[1]);
			
			if(inventory[0].getItemDamage()-chargeLevel>=0)
					return true;
		}
		
		return false;
	}

	private void smeltItem()
	{
		if (this.canSmelt())
		{
			int chargeLevel = CrushingHandler.recipe().getChargeLevel(inventory[1]);
			
			inventory[0].setItemDamage(inventory[0].getItemDamage()-chargeLevel);
			
			if(inventory[1].stackSize==1)
				inventory[1] = inventory[1].getItem().getContainerItemStack(inventory[1]);
			else
				--inventory[1].stackSize;
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return new int[]{0,1};
	}
	
	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3)
	{
		if(itemstack.getItem() == ModItems.canisterSteam && par1==0)
			return true;
		else if(par1==1)
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		if(par1==0)
			return true;
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if(i==0 || i==1)
			return true;
		return false;
	}
}
