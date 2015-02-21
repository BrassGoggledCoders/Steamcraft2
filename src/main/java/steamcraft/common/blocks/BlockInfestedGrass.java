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

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInfestedGrass extends BlockInfestedDirt
{
	@SideOnly(Side.CLIENT)
	private IIcon field_149991_b;
	@SideOnly(Side.CLIENT)
	private IIcon field_149993_M;
	@SideOnly(Side.CLIENT)
	private IIcon field_149994_N;

	public BlockInfestedGrass(Material mat)
	{
		super(mat);

	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return p_149691_1_ == 1 ? this.field_149991_b : (p_149691_1_ == 0 ? Blocks.dirt.getBlockTextureFromSide(p_149691_1_) : this.blockIcon);
	}

	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
	{
		return true;
	}

	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
	{
		if(p_149673_5_ == 1)
		{
			return this.field_149991_b;
		}
		else if(p_149673_5_ == 0)
		{
			return Blocks.dirt.getBlockTextureFromSide(p_149673_5_);
		}
		else
		{
			Material material = p_149673_1_.getBlock(p_149673_2_, p_149673_3_ + 1, p_149673_4_).getMaterial();
			return material != Material.snow && material != Material.craftedSnow ? this.blockIcon : this.field_149993_M;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
		this.field_149991_b = p_149651_1_.registerIcon(this.getTextureName() + "_top");
		this.field_149993_M = p_149651_1_.registerIcon(this.getTextureName() + "_side_snowed");
		this.field_149994_N = p_149651_1_.registerIcon(this.getTextureName() + "_side_overlay");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		double d0 = 0.5D;
		double d1 = 1.0D;
		return ColorizerGrass.getGrassColor(d0, d1);
	}

	/**
	 * Returns the color this block should be rendered. Used by leaves.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int p_149741_1_)
	{
		return this.getBlockColor();
	}

	/**
	 * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called when first determining what to render.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
	{
		int l = 0;
		int i1 = 0;
		int j1 = 0;

		for(int k1 = -1; k1 <= 1; ++k1)
		{
			for(int l1 = -1; l1 <= 1; ++l1)
			{
				int i2 = p_149720_1_.getBiomeGenForCoords(p_149720_2_ + l1, p_149720_4_ + k1).getBiomeGrassColor(p_149720_2_ + l1, p_149720_3_,
						p_149720_4_ + k1);
				l += (i2 & 16711680) >> 16;
				i1 += (i2 & 65280) >> 8;
				j1 += i2 & 255;
			}
		}

		return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
	}

	@SideOnly(Side.CLIENT)
	public static IIcon getIconSideOverlay()
	{
		return Blocks.grass.getIconSideOverlay();
	}
}
