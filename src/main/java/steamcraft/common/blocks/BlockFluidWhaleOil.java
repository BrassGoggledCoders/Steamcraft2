
package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import boilerplate.common.utils.ItemStackUtils;
import steamcraft.common.lib.ModInfo;

/**
 * @author Decebaldecebal
 *
 */
public class BlockFluidWhaleOil extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
	private IIcon iconFlowing;

	public BlockFluidWhaleOil(Fluid fluid, Material material)
	{
		super(fluid, material);
		// this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side != 0) && (side != 1) ? this.iconFlowing : this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(ModInfo.PREFIX + "whaleOilStill");
		this.iconFlowing = iconRegister.registerIcon(ModInfo.PREFIX + "whaleOilFlowing");
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		return !ItemStackUtils.getBlockMaterial(world, x, y, z).isLiquid() && super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		return !ItemStackUtils.getBlockMaterial(world, x, y, z).isLiquid() && super.displaceIfPossible(world, x, y, z);
	}
}