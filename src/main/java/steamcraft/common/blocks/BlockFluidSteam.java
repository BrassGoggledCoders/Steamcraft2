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
public class BlockFluidSteam extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
	private static IIcon iconFlowing;

	public BlockFluidSteam(Fluid fluid, Material material)
	{
		super(fluid, material);
		// this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side != 0) && (side != 1) ? iconFlowing : this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(ModInfo.PREFIX + "steamStill");
		iconFlowing = iconRegister.registerIcon(ModInfo.PREFIX + "steamFlowing");
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