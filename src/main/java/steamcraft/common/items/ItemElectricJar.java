package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElectricJar extends BaseItem implements IEnergyContainerItem
{
	private static IIcon emptyIcon;
    private static IIcon fullIcon;
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;

	public ItemElectricJar()
	{
		setMaxDamage(capacity/100);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		emptyIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "itemElectricJarEmpty");
		itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "itemElectricJarHalf");
		fullIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "itemElectricJarFull");
	}
	 @SideOnly(Side.CLIENT)
	 public IIcon getIconFromDamage(int damage)
	 {
		 if(damage == 0)
			 return fullIcon;
		 else if(damage == this.getMaxDamage())
			 return emptyIcon;
		 return this.itemIcon;
	 }
		@SuppressWarnings("all")
		@Override
		@SideOnly(Side.CLIENT)
		public void getSubItems(Item item, CreativeTabs tab, List l)
		{
			l.add(new ItemStack(ConfigItems.itemElectricJar, 1, this.getMaxDamage()));
			l.add(getFilledCanister());
		}

		public ItemStack getFilledCanister()
		{
			ItemStack filled = new ItemStack(ConfigItems.itemElectricJar, 1, 0);

			receiveEnergy(filled, capacity, true);

			return filled;
		}


	public ItemElectricJar(int capacity) {

		this(capacity, capacity, capacity);
	}

	public ItemElectricJar(int capacity, int maxTransfer) {

		this(capacity, maxTransfer, maxTransfer);
	}

	public ItemElectricJar(int capacity, int maxReceive, int maxExtract) {

		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}

	public ItemElectricJar setCapacity(int capacity) {

		this.capacity = capacity;
		return this;
	}

	public void setMaxTransfer(int maxTransfer) {

		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive) {

		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract) {

		this.maxExtract = maxExtract;
	}

	/* IEnergyContainerItem */
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

		if (container.stackTagCompound == null) {
			container.stackTagCompound = new NBTTagCompound();
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate) {
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		updateJarDamage(container);
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		updateJarDamage(container);
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
		}
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {

		return capacity;
	}
	private void updateJarDamage(ItemStack jar)
	{
		jar.setItemDamage(jar.getMaxDamage() - this.getEnergyStored(jar)/100);
	}
}
