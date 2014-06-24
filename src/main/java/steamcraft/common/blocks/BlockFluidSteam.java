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
package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.utils.ItemStackUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockFluidSteam.
 */
public class BlockFluidSteam extends BlockFluidClassic
{

	/** The still icon. */
	@SideOnly(Side.CLIENT)
	public static IIcon[] icon;
	/**
	 * Instantiates a new block fluid steam.
	 *
	 * @param fluid
	 *            the fluid
	 * @param material
	 *            the material
	 */
	public BlockFluidSteam(Fluid fluid, Material material)
	{
		super(fluid, material);
		this.setBlockName("steamFluidBlock");
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return side != 0 && side != 1 ? this.icon[1] : this.icon[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.icon = new IIcon[] {iconRegister.registerIcon(LibInfo.PREFIX + "steamStill"),
				iconRegister.registerIcon(LibInfo.PREFIX + "steamFlowing")};
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraftforge.fluids.BlockFluidBase#canDisplace(net.minecraft.world
	 * .IBlockAccess, int, int, int)
	 */
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z)
	{
		if (ItemStackUtils.getBlockMaterial(world, x, y, z).isLiquid())
		{
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraftforge.fluids.BlockFluidBase#displaceIfPossible(net.minecraft
	 * .world.World, int, int, int)
	 */
	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z)
	{
		if (ItemStackUtils.getBlockMaterial(world, x, y, z).isLiquid())
		{
			return false;
		}
		return super.displaceIfPossible(world, x, y, z);
	}
}