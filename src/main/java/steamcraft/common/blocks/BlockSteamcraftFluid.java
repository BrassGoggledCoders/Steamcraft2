
package steamcraft.common.blocks;

import boilerplate.common.utils.ItemStackUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import steamcraft.common.lib.ModInfo;

/**
 * @author Decebaldecebal
 *
 */
public class BlockSteamcraftFluid extends BlockFluidClassic
{
	public IIcon stillIcon;
	public IIcon flowIcon;

	String texture;

	private boolean overwriteIcons = true;

	public BlockSteamcraftFluid(Fluid fluid, Material material, String texture)
	{
		super(fluid, material);
		this.texture = texture;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.stillIcon = iconRegister.registerIcon(ModInfo.PREFIX + this.texture);
		this.flowIcon = iconRegister.registerIcon(ModInfo.PREFIX + this.texture + "_flow");

		if (this.overwriteIcons)
			this.getFluid().setIcons(this.stillIcon, this.flowIcon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if ((side == 0) || (side == 1))
			return this.stillIcon;
		return this.flowIcon;
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

	public void dontOverwriteIcons()
	{
		this.overwriteIcons = false;
	}
}