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
 * File created @ 23-May-2014
 */
package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.tiles.TileSteamBoiler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ContainerSteamBoiler.
 */
public class ContainerSteamBoiler extends Container
{
	
	/** The Tile_ e. */
	protected TileSteamBoiler Tile_E;
	
	/** The last burn time. */
	private int lastBurnTime = 0;
	
	/** The last item burn time. */
	private int lastItemBurnTime = 0;
	
	/** The last steam level. */
	private int lastSteamLevel = 0;
	
	/** The last water level. */
	private int lastWaterLevel = 0;

	/**
	 * Instantiates a new container steam boiler.
	 *
	 * @param player the player
	 * @param tile the tile
	 */
	public ContainerSteamBoiler(final InventoryPlayer player,
			final TileSteamBoiler tile)
	{
		Tile_E = tile;
		addSlotToContainer(new Slot(tile, 0, 42, 52));
		addSlotToContainer(new Slot(tile, 1, 132, 57)
		{
			@Override
			public boolean isItemValid(final ItemStack stack)
			{
				if (FluidContainerRegistry.isContainer(stack))
				{
					return true;
				}
				return false;
			}
		});
		addSlotToContainer(new Slot(tile, 2, 132, 21)
		{
			@Override
			public boolean isItemValid(final ItemStack stack)
			{
				if (stack.getItem() == ConfigItems.itemCanisterGas)
				{
					return true;
				}
				return false;
			}
		});

		int var3;

		for (var3 = 0; var3 < 3; ++var3)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				addSlotToContainer(new Slot(player, var4 + var3 * 9 + 9,
						8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3)
		{
			addSlotToContainer(new Slot(player, var3, 8 + var3 * 18, 142));
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.inventory.Container#addCraftingToCrafters(net.minecraft.inventory.ICrafting)
	 */
	@Override
	public void addCraftingToCrafters(final ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, Tile_E.furnaceBurnTime);
		par1ICrafting
				.sendProgressBarUpdate(this, 1, Tile_E.currentItemBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 2,
				Tile_E.steamTank.getFluidAmount());
		par1ICrafting.sendProgressBarUpdate(this, 3,
				Tile_E.waterTank.getFluidAmount());
	}

	/* (non-Javadoc)
	 * @see net.minecraft.inventory.Container#detectAndSendChanges()
	 */
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int var1 = 0; var1 < crafters.size(); ++var1)
		{
			final ICrafting var2 = (ICrafting) crafters.get(var1);

			if (lastBurnTime != Tile_E.furnaceBurnTime)
			{
				var2.sendProgressBarUpdate(this, 0, Tile_E.furnaceBurnTime);
			}

			if (lastItemBurnTime != Tile_E.currentItemBurnTime)
			{
				var2.sendProgressBarUpdate(this, 1, Tile_E.currentItemBurnTime);
			}

			if (lastSteamLevel != Tile_E.steamTank.getFluidAmount())
			{
				var2.sendProgressBarUpdate(this, 2,
						Tile_E.steamTank.getFluidAmount());
			}

			if (lastWaterLevel != Tile_E.waterTank.getFluidAmount())
			{
				var2.sendProgressBarUpdate(this, 3,
						Tile_E.waterTank.getFluidAmount());
			}
		}

		lastBurnTime = Tile_E.furnaceBurnTime;
		lastItemBurnTime = Tile_E.currentItemBurnTime;
		lastSteamLevel = Tile_E.steamTank.getFluidAmount();
		lastWaterLevel = Tile_E.waterTank.getFluidAmount();
	}

	/* (non-Javadoc)
	 * @see net.minecraft.inventory.Container#updateProgressBar(int, int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(final int par1, final int par2)
	{
		if (par1 == 0)
		{
			Tile_E.furnaceBurnTime = par2;
		}
		else if (par1 == 1)
		{
			Tile_E.currentItemBurnTime = par2;
		}
		else if (par1 == 2)
		{
			Tile_E.steamTank.setFluid(new FluidStack(FluidRegistry
					.getFluid("steam"), par2));
		}
		else if (par1 == 3)
		{
			Tile_E.waterTank.setFluid(new FluidStack(FluidRegistry
					.getFluid("water"), par2));
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.inventory.Container#canInteractWith(net.minecraft.entity.player.EntityPlayer)
	 */
	@Override
	public boolean canInteractWith(final EntityPlayer par1EntityPlayer)
	{
		return Tile_E.isUseableByPlayer(par1EntityPlayer);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.inventory.Container#transferStackInSlot(net.minecraft.entity.player.EntityPlayer, int)
	 */
	@Override
	public ItemStack transferStackInSlot(final EntityPlayer par1EntityPlayer,
			final int par2)
	{
		ItemStack var3 = null;
		final Slot var4 = (Slot) inventorySlots.get(par2);

		if (var4 != null && var4.getHasStack())
		{
			final ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if (par2 > 2)
			{
				final FluidStack liquid = FluidContainerRegistry
						.getFluidForFilledItem(var5);

				if (liquid != null && liquid.getFluid() == FluidRegistry.WATER)
				{
					if (!mergeItemStack(var5, 1, 2, false))
					{
						return null;
					}
				}
				else if (TileEntityFurnace.getItemBurnTime(var5) > 0)
				{
					if (!mergeItemStack(var5, 0, 1, false))
					{
						if (par2 >= 3 && par2 < 30)
						{
							if (!mergeItemStack(var5, 30, 39, false))
							{
								return null;
							}
						}
						else if (par2 >= 30 && par2 < 39
								&& !mergeItemStack(var5, 3, 30, false))
						{
							return null;
						}
					}
				}
				else if (var5.getItem() == ConfigItems.itemCanisterGas)
				{
					if (!mergeItemStack(var5, 2, 3, false))
					{
						if (par2 >= 3 && par2 < 30)
						{
							if (!mergeItemStack(var5, 30, 39, false))
							{
								return null;
							}
						}
						else if (par2 >= 30 && par2 < 39
								&& !mergeItemStack(var5, 3, 30, false))
						{
							return null;
						}
					}
				}
				else if (par2 >= 3 && par2 < 30
						&& !mergeItemStack(var5, 30, 39, false))
				{
					return null;
				}
				else if (par2 >= 30 && par2 < 39
						&& !mergeItemStack(var5, 3, 30, false))
				{
					return null;
				}
			}
			else if (!mergeItemStack(var5, 3, 39, false))
			{
				return null;
			}

			if (var5.stackSize == 0)
			{
				var4.putStack((ItemStack) null);
			}
			else
			{
				var4.onSlotChanged();
			}

			if (var5.stackSize == var3.stackSize)
			{
				return null;
			}

			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}

		return var3;
	}
}
