package steamcraft.common.compat.chisel;

import boilerplate.common.baseclasses.items.BaseSteamItem;
import com.cricketcraft.chisel.api.IChiselItem;
import com.cricketcraft.chisel.api.carving.ICarvingVariation;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * Created by Skylar on 9/2/2015.
 */
@Optional.Interface(iface = "com.cricketcraft.chisel.api.IChiselItem", modid = "chisel")
public class ItemSteamChisel extends BaseSteamItem implements IChiselItem
{

	public ItemSteamChisel()
	{
		super(32000);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.setUnlocalizedName("itemChiselSteam");
		this.setFull3D();
		this.setNoRepair();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
	}

	@Override
	public boolean canOpenGui(World world, EntityPlayer player, ItemStack chisel)
	{
		return getFluidLevel(chisel) > 100;
	}

	@Override
	public boolean onChisel(World world, ItemStack chisel, ICarvingVariation target)
	{
		return consumeSteamFromCanister(chisel, 100);
	}

	@Override
	public boolean canChisel(World world, ItemStack chisel, ICarvingVariation target)
	{
		return getFluidLevel(chisel) > 100;
	}

	@Override
	public boolean canChiselBlock(World world, EntityPlayer player, int x, int y, int z, Block block, int metadata)
	{
		ItemStack currentItem = player.getCurrentEquippedItem();
		return getFluidLevel(currentItem) > 100;
	}

	@Override
	public boolean hasModes(ItemStack chisel)
	{
		return false;
	}

	private int getFluidLevel(ItemStack chisel)
	{
		if (chisel != null && chisel.getItem() instanceof IFluidContainerItem)
		{
			FluidStack fluidStack = ((IFluidContainerItem) chisel.getItem()).getFluid(chisel);
			return fluidStack != null ? fluidStack.amount : 0;
		}
		return 0;
	}
}
