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
 * File created @ [Feb 17, 2014, 4:01:46 PM]
 */
package common.steamcraft.common.block.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;

import common.steamcraft.api.CoupleUtil;
import common.steamcraft.api.ISteamConsumer;
import common.steamcraft.api.ISteamProvider;
import common.steamcraft.api.ISteamStorage;
import common.steamcraft.common.SC2;
import common.steamcraft.common.item.ItemCanister;
import common.steamcraft.common.item.ModItems;
import common.steamcraft.common.util.EffectUtil;


/**
 * Someone needs to fix this shit.
 * 
 * @author MrArcane111
 *
 */
/*
decebaldecebal:
I did some work but I do not know what this tile should actually do...

public class TileEntityBasicSteamBoiler extends TileEntityMachine implements IFluidTank, ISteamProvider 
{
	public static final int MAX_CAPACITY = 10000;
	public static final float STEAM_PRODUCED = 60.0F;
	private FluidTank[] liquidTank;
	private ItemStack[] inventoryItems;
	private int burnTime;
	private int maxBurnTime;
	private Random random = new Random();
	private boolean steaming;
	private boolean burning;
	private int particleDelay;

	public TileEntityBasicSteamBoiler() {
		this.inventoryItems = new ItemStack[3];
		this.liquidTank = new FluidTank[] { new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 10000), new FluidTank(new FluidStack(FluidRegistry.getFluid("steam"), 0), 10000) };
	}

	public boolean isSteaming() {
		return this.steaming;
	}

	public void setSteaming(boolean steaming) {
		if (this.steaming != steaming)
			setMarkedForResend(true);
		this.steaming = steaming;
	}

	public boolean isBurning()
	{
		return this.burning;
	}

	public void setBurning(boolean burning)
	{
		if (this.burning != burning)
			setMarkedForResend(true);
		this.burning = burning;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving)
	{
		int side = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;

		switch (side)
		{
		case 0:
			side = 2;
			break;
		case 1:
			side = 5;
			break;
		case 2:
			side = 3;
			break;
		case 3:
			side = 4;
		}

		setFacingDirection(side);
	}

	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound);

		NBTTagCompound liquidCompound = (NBTTagCompound)nbtTagCompound.getTag("Liquid");
		if (liquidCompound != null)
		{
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(liquidCompound);
			if(fluid!=null)
				this.liquidTank[0].setFluid(fluid);
		}
		else {
			this.liquidTank[0].setFluid(null);
		}
		this.burnTime = nbtTagCompound.getShort("BurnTime");
		this.maxBurnTime = nbtTagCompound.getShort("MaxBurnTime");
	}

	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		if (this.liquidTank[0].getFluid() != null)
		{
			NBTTagCompound liquidCompound = new NBTTagCompound();
			this.liquidTank[0].getFluid().writeToNBT(liquidCompound);
			nbtTagCompound.setTag("Liquid", liquidCompound);
		}

		nbtTagCompound.setShort("BurnTime", (short)this.burnTime);
		nbtTagCompound.setShort("MaxBurnTime", (short)this.maxBurnTime);
	}

	public void writePacket(DataOutputStream dataStream)
			throws IOException
			{
		super.writePacket(dataStream);

		dataStream.writeBoolean(this.steaming);
		dataStream.writeBoolean(this.burning);
			}

	public void readPartialPacket(DataInputStream dataStream)
			throws IOException
			{
		this.steaming = dataStream.readBoolean();
		this.burning = dataStream.readBoolean();

		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}

	public void updateEntity()
	{
		manageDeletion();

		if (!this.worldObj.isRemote)
		{
			boolean currentSteaming = false;
			boolean currentBurning = false;

			if ((this.liquidTank[0].getFluid() != null) && (this.liquidTank[0].getFluid().amount >= 1))
			{
				if ((this.burnTime > 0) || ((this.inventoryItems[0] != null) && (TileEntityFurnace.isItemFuel(this.inventoryItems[0]))))
				{
					currentBurning = true;
					if (this.burnTime == 0) {
						refuel();
					}
					this.liquidTank[0].getFluid().amount -= 1;

					if (this.liquidTank[0].getFluid().amount == 0) {
						this.liquidTank[0].setFluid(null);
					}
					float produced = 60.0F;
					if ((this.inventoryItems[1] != null) && (this.inventoryItems[1].itemID == ModItems.canisterSteam.itemID) && (!ItemCanister.isFull(this.inventoryItems[1])))
					{
						float filled = Math.min(150000.0F - ItemCanister.getSteam(this.inventoryItems[1]), produced);
						ItemCanister.setSteam(this.inventoryItems[1], ItemCanister.getSteam(this.inventoryItems[1]) + filled);
						produced -= filled;
					}

					if (produced > 0.0F)
					{
						CoupleUtil connected = TileEntitySteamPipe.findConnectedEntity(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord, ForgeDirection.UP.ordinal());

						if (connected != null)
						{
							if ((connected.getFirstObject() instanceof ISteamConsumer))
							{
								((ISteamConsumer)connected.getFirstObject()).addSteam(produced);
							}
							else if ((connected.getFirstObject() instanceof ISteamStorage))
							{
								((ISteamStorage)connected.getFirstObject()).supplySteam(produced, ((Integer)connected.getSecondObject()).intValue(), new ArrayList());
							}
						}
						else if (this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord) == 0) {
							currentSteaming = true;
						}
					}
				}
			}
			if (this.burnTime > 0)
			{
				this.burnTime -= 1;
				currentBurning = true;
			}

			if (this.inventoryItems[2] != null)
			{
				FluidStack addLiquid = FluidContainerRegistry.getFluidForFilledItem(this.inventoryItems[2]);

				int filled = fill(0, addLiquid, false);
				if ((filled > 0) && (addLiquid != null) && (filled == addLiquid.amount))
				{
					fill(0, addLiquid, true);
					if (this.inventoryItems[2].stackSize == 1)
						this.inventoryItems[2] = this.inventoryItems[2].getItem().getContainerItemStack(this.inventoryItems[2]);
					else {
						this.inventoryItems[2].stackSize -= 1;
					}
				}
			}
			setSteaming(currentSteaming);
			setBurning(currentBurning);
		}
		else {
			if (this.steaming) {
				EffectUtil.displayFX("smoke", this.worldObj, this.xCoord + 0.5D, this.yCoord + 1, this.zCoord + 0.5D, this.random.nextDouble() / 50.0D, 0.03D, this.random.nextDouble() / 50.0D, 2.5F);
			}

			if ((this.burning) && (this.particleDelay == 0))
			{
				float var7 = this.xCoord + 0.5F;
				float var8 = this.yCoord + 0.0F + this.random.nextFloat() * 6.0F / 16.0F;
				float var9 = this.zCoord + 0.5F;
				float var10 = 0.52F;
				float var11 = this.random.nextFloat() * 0.6F - 0.3F;

				if (this.facingDirection == 4)
				{
					this.worldObj.spawnParticle("smoke", var7 - var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
					this.worldObj.spawnParticle("flame", var7 - var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
				}
				else if (this.facingDirection == 5)
				{
					this.worldObj.spawnParticle("smoke", var7 + var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
					this.worldObj.spawnParticle("flame", var7 + var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
				}
				else if (this.facingDirection == 2)
				{
					this.worldObj.spawnParticle("smoke", var7 + var11, var8, var9 - var10, 0.0D, 0.0D, 0.0D);
					this.worldObj.spawnParticle("flame", var7 + var11, var8, var9 - var10, 0.0D, 0.0D, 0.0D);
				}
				else if (this.facingDirection == 3)
				{
					this.worldObj.spawnParticle("smoke", var7 + var11, var8, var9 + var10, 0.0D, 0.0D, 0.0D);
					this.worldObj.spawnParticle("flame", var7 + var11, var8, var9 + var10, 0.0D, 0.0D, 0.0D);
				}

				this.particleDelay = 5;
			}

			if (this.particleDelay > 0) {
				this.particleDelay -= 1;
			}
		}
		super.updateEntity();
	}

	public int getBlockTextureFromSide(int side)
	{
		if ((side == this.facingDirection) && (!this.burning))
			return 10;
		if ((side == this.facingDirection) && (this.burning))
			return 13;
		if (side == 1)
			return 12;
		return 11;
	}

	private void refuel()
	{
		if ((this.inventoryItems[0] != null) && (TileEntityFurnace.isItemFuel(this.inventoryItems[0])))
		{
			this.burnTime = TileEntityFurnace.getItemBurnTime(this.inventoryItems[0]);
			this.maxBurnTime = this.burnTime;

			this.inventoryItems[0].stackSize -= 1;

			if (this.inventoryItems[0].stackSize == 0)
			{
				this.inventoryItems[0] = this.inventoryItems[0].getItem().getContainerItemStack(this.inventoryItems[0]);
			}
		}
	}

	public void onBreak()
	{
		for (int i = 0; i < this.inventoryItems.length; i++)
		{
			ItemStack stack = this.inventoryItems[i];

			if (stack == null)
				continue;
			float randx = this.random.nextFloat() * 0.8F + 0.1F;
			float randy = this.random.nextFloat() * 0.8F + 0.1F;
			float randz = this.random.nextFloat() * 0.8F + 0.1F;

			EntityItem entItem = new EntityItem(this.worldObj, this.xCoord + randx, this.yCoord + randy, this.zCoord + randz, new ItemStack(stack.itemID, stack.stackSize, stack.getItemDamage()));

			entItem.motionX = ((float)this.random.nextGaussian() * 0.05F);
			entItem.motionY = ((float)this.random.nextGaussian() * 0.05F + 0.2F);
			entItem.motionZ = ((float)this.random.nextGaussian() * 0.05F);

			if (stack.hasTagCompound())
			{
				entItem.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
			}
			this.worldObj.spawnEntityInWorld(entItem);
		}
	}

	public int getBurnTime()
	{
		return this.burnTime;
	}

	public void setBurnTime(int burnTime)
	{
		this.burnTime = burnTime;
	}

	public int getMaxBurnTime()
	{
		return this.maxBurnTime;
	}

	public void setMaxBurnTime(int maxBurnTime)
	{
		this.maxBurnTime = maxBurnTime;
	}

	public int getBurnTimeScaled(int maxSize)
	{
		if (this.maxBurnTime == 0)
		{
			this.maxBurnTime = 200;
		}

		return this.burnTime * maxSize / this.maxBurnTime;
	}

	public boolean onActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!player.isSneaking())
		{
			if (this.worldObj.isRemote)
				return true;
			player.openGui(SC2.instance, 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			return true;
		}
		return false;
	}

	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return fill(0, resource, doFill);
	}

	public int fill(int tankIndex, FluidStack resource, boolean doFill)
	{
		if ((resource == null) || (resource.fluidID != Block.waterStill.blockID)) { // XXX No metadata?
			return 0;
		}
		return this.liquidTank[0].fill(resource, doFill);
	}

	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return null;
	}

	public FluidStack drain(int tankIndex, int maxDrain, boolean doDrain)
	{
		return null;
	}

	public IFluidTank[] getTanks(ForgeDirection direction)
	{
		return this.liquidTank;
	}

	public IFluidTank getTank(ForgeDirection direction, FluidStack type)
	{
		return this.liquidTank[0];
	}

	public int getSizeInventory()
	{
		return this.inventoryItems.length;
	}

	public ItemStack getStackInSlot(int var1)
	{
		return this.inventoryItems[var1];
	}

	public ItemStack decrStackSize(int slot, int amount)
	{
		if (this.inventoryItems[slot] != null)
		{
			if (this.inventoryItems[slot].stackSize <= amount)
			{
				ItemStack stack = this.inventoryItems[slot];
				this.inventoryItems[slot] = null;
				onInventoryChanged();
				return stack;
			}

			ItemStack stack = this.inventoryItems[slot].splitStack(amount);

			if (this.inventoryItems[slot].stackSize == 0)
			{
				this.inventoryItems[slot] = null;
			}

			onInventoryChanged();
			return stack;
		}

		return null;
	}

	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.inventoryItems[slot] != null)
		{
			ItemStack stack = this.inventoryItems[slot];
			this.inventoryItems[slot] = null;
			return stack;
		}

		return null;
	}

	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		this.inventoryItems[slot] = stack;

		if ((stack != null) && (stack.stackSize > getInventoryStackLimit()))
		{
			stack.stackSize = getInventoryStackLimit();
		}

		onInventoryChanged();
	}

	public String getInvName()
	{
		return "container.steamboiler";
	}

	public int getInventoryStackLimit()
	{
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this;
	}

	public void openChest()
	{
	}

	public void closeChest()
	{
	}

	public int getStartInventorySide(ForgeDirection side)
	{
		if (side == ForgeDirection.DOWN)
			return 0;
		if ((side == ForgeDirection.UP) || (side.ordinal() == this.facingDirection) || (side.getOpposite().ordinal() == this.facingDirection)) {
			return 1;
		}
		return 2;
	}

	public int getSizeInventorySide(ForgeDirection side)
	{
		return 1;
	}
}
*/