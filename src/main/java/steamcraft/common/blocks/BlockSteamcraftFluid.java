/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
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

import steamcraft.common.lib.ModInfo;
import boilerplate.common.utils.ItemStackUtils;

/**
 * @author Decebaldecebal
 *
 */
public class BlockSteamcraftFluid extends BlockFluidClassic
{
	private static IIcon stillIcon;
	private static IIcon flowIcon;

	private Fluid fluid;

	private String texture;

	private boolean overwriteIcons = true;

	public BlockSteamcraftFluid(Fluid fluid, Material material, String texture)
	{
		super(fluid, material);
		this.fluid = fluid;
		this.texture = texture;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		stillIcon = iconRegister.registerIcon(ModInfo.PREFIX + texture);
		flowIcon = iconRegister.registerIcon(ModInfo.PREFIX + texture + "_flow");

		if(overwriteIcons)
			this.getFluid().setIcons(stillIcon, flowIcon);

		if(this.getFluid().getBlock() != this && fluid != null)
			fluid.setIcons(stillIcon, flowIcon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if(side == 0 || side == 1)
			return stillIcon;
		return flowIcon;
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