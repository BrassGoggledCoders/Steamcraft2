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
 * File created @ [Mar 20, 2014, 10:41:55 AM]
 */
package steamcraft.common.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import steamcraft.common.config.ConfigItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author Decebaldecebal
 */
public class ItemCanister extends BaseItem implements IFluidContainerItem
{
	public static final int MAX_STEAM = 10000;
	public static final int MAX_STEAM_RATE = 20; //Maximum amount of steam that can be inserted into this canister per tick

	public ItemCanister()
	{
		super();
		this.maxStackSize = 1;
		this.setNoRepair();
		this.setMaxDamage(MAX_STEAM);
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(ConfigItems.itemCanisterSteam, 1, 0));
		l.add(new ItemStack(ConfigItems.itemCanisterSteam, 1, MAX_STEAM));
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag)
	{
			//list.add(String.format("%d/%d", new Object[] {get(itemStack), -MAX_STEAM}));
	}

	@Override
	public FluidStack getFluid(ItemStack container) {
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid"))
        {
            return null;
        }
        return FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
	}

	@Override
	public int getCapacity(ItemStack container) {
		return MAX_STEAM;
	}
	@Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill)
    {
	 if (resource == null)
     {
         return 0;
     }

     if (!doFill)
     {
         if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid"))
         {
             return Math.min(MAX_STEAM, resource.amount);
         }

         FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));

         if (stack == null)
         {
             return Math.min(MAX_STEAM, resource.amount);
         }

         if (!stack.isFluidEqual(resource))
         {
             return 0;
         }

         return Math.min(MAX_STEAM - stack.amount, resource.amount);
     }

     if (container.stackTagCompound == null)
     {
         container.stackTagCompound = new NBTTagCompound();
     }

     if (!container.stackTagCompound.hasKey("Fluid"))
     {
         NBTTagCompound fluidTag = resource.writeToNBT(new NBTTagCompound());

         if (MAX_STEAM < resource.amount)
         {
             fluidTag.setInteger("Amount", MAX_STEAM);
             container.stackTagCompound.setTag("Fluid", fluidTag);
             return MAX_STEAM;
         }

         container.stackTagCompound.setTag("Fluid", fluidTag);
         return resource.amount;
     }

     NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
     FluidStack stack = FluidStack.loadFluidStackFromNBT(fluidTag);

     if (!stack.isFluidEqual(resource))
     {
         return 0;
     }

     int filled = MAX_STEAM - stack.amount;
     if (resource.amount < filled)
     {
         stack.amount += resource.amount;
         filled = resource.amount;
     }
     else
     {
         stack.amount = MAX_STEAM;
     }

     container.stackTagCompound.setTag("Fluid", stack.writeToNBT(fluidTag));
     return filled;
 }

 @Override
 public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain)
 {
     if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid"))
     {
         return null;
     }

     FluidStack stack = FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
     if (stack == null)
     {
         return null;
     }

     stack.amount = Math.min(stack.amount, maxDrain);
     if (doDrain)
     {
         if (maxDrain >= MAX_STEAM)
         {
             container.stackTagCompound.removeTag("Fluid");

             if (container.stackTagCompound.hasNoTags())
             {
                 container.stackTagCompound = null;
             }
             return stack;
         }

         NBTTagCompound fluidTag = container.stackTagCompound.getCompoundTag("Fluid");
         fluidTag.setInteger("Amount", fluidTag.getInteger("Amount") - maxDrain);
         container.stackTagCompound.setTag("Fluid", fluidTag);
     }
     return stack;
 }
}
