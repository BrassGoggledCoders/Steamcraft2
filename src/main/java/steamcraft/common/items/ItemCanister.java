
package steamcraft.common.items;

import java.util.List;

import boilerplate.common.baseclasses.items.BaseItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

/**
 * @author Decebaldecebal & warlordjones
 *
 */
public class ItemCanister extends BaseItem implements IFluidContainerItem
{
	private IIcon emptyIcon;
	private IIcon fullIcon;
	public int maxSteam;
	public int maxSteamRate;

	public ItemCanister(int maxSteam, int maxSteamRate)
	{
		super();
		this.maxSteam = maxSteam;
		this.maxSteamRate = maxSteamRate;
		this.setMaxStackSize(1);
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(this));
		l.add(this.getFilledCanister());
	}

	public ItemStack getFilledCanister()
	{
		ItemStack filled = new ItemStack(this);

		this.fill(filled, new FluidStack(FluidRegistry.getFluid("steam"), this.maxSteam), true);

		return filled;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		if (this == InitItems.itemCanisterSteam)
		{
			this.emptyIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemCanisterEmpty");
			this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemCanisterHalf");
			this.fullIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemCanisterFull");
		}
		else
		{
			this.emptyIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemReinforcedCanisterEmpty");
			this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemReinforcedCanisterHalf");
			this.fullIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "itemReinforcedCanisterFull");
		}
	}

	/**
	 * Returns the icon index of the stack given as argument.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack stack)
	{
		if (this.getFluidAmount(stack) == 0)
			return this.emptyIcon;
		else if (this.getFluidAmount(stack) == this.maxSteam)
			return this.fullIcon;
		else
			return this.itemIcon;
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		if (this.getFluidAmount(stack) > 0)
		{
			String str = this.getFluid(stack).getFluid().getName();
			int amount = this.getFluidAmount(stack);

			list.add("Holding " + amount + "mB of " + str);
			list.add("(That's about " + (amount / 1000) + " buckets)");
		}
		else
			list.add("Empty");
	}

	@Override
	public FluidStack getFluid(ItemStack container)
	{
		if ((container.stackTagCompound == null) || !container.stackTagCompound.hasKey("Fluid"))
			return null;
		return FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
	}

	@Override
	public int getCapacity(ItemStack container)
	{
		return this.maxSteam;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill)
	{
		if (resource == null)
			return 0;

		if (!doFill)
		{
			if ((container.stackTagCompound == null) || !container.stackTagCompound.hasKey("Fluid"))
				return Math.min(this.maxSteam, resource.amount);

			FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));

			if (stack == null)
				return Math.min(this.maxSteam, resource.amount);

			if (!stack.isFluidEqual(resource))
				return 0;

			return Math.min(this.maxSteam - stack.amount, resource.amount);
		}

		if (container.stackTagCompound == null)
			container.stackTagCompound = new NBTTagCompound();

		if (!container.stackTagCompound.hasKey("Fluid"))
		{
			NBTTagCompound fluidTag = resource.writeToNBT(new NBTTagCompound());

			if (this.maxSteam < resource.amount)
			{
				fluidTag.setInteger("Amount", this.maxSteam);
				container.stackTagCompound.setTag("Fluid", fluidTag);
				return this.maxSteam;
			}

			container.stackTagCompound.setTag("Fluid", fluidTag);
			return resource.amount;
		}

		NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
		FluidStack stack = FluidStack.loadFluidStackFromNBT(fluidTag);

		if (!stack.isFluidEqual(resource))
			return 0;

		int filled = this.maxSteam - stack.amount;
		if (resource.amount < filled)
		{
			stack.amount += resource.amount;
			filled = resource.amount;
		}
		else
			stack.amount = this.maxSteam;

		container.stackTagCompound.setTag("Fluid", stack.writeToNBT(fluidTag));

		return filled;
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain)
	{
		if ((container.stackTagCompound == null) || !container.stackTagCompound.hasKey("Fluid"))
			return null;

		FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));

		if (stack == null)
			return null;

		stack.amount = Math.min(stack.amount, maxDrain);

		if (doDrain)
		{
			if (maxDrain >= this.maxSteam)
			{
				container.stackTagCompound.removeTag("Fluid");

				if (container.stackTagCompound.hasNoTags())
					container.stackTagCompound = null;

				return stack;
			}

			NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
			fluidTag.setInteger("Amount", fluidTag.getInteger("Amount") - stack.amount);
			container.stackTagCompound.setTag("Fluid", fluidTag);
		}

		return stack;
	}

	public int getFluidAmount(ItemStack stack)
	{
		FluidStack fluid = this.getFluid(stack);

		if (fluid == null)
			return 0;

		return fluid.amount;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return 1.0D - ((double) this.getFluidAmount(stack) / this.getCapacity(stack));
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return true;
	}
}
