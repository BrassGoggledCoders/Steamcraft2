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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.blocks.BaseMetadataBlock;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

public class BlockSiren extends BaseMetadataBlock
{
	private boolean isOn;
	String sound;

	public BlockSiren(Material p_i45394_1_, boolean isOn, String sound)
	{
		super(p_i45394_1_);
		this.isOn = isOn;
		this.sound = sound;
		if(!isOn)
			this.setCreativeTab(Steamcraft.tabSC2);
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if(!world.isRemote)
		{
			if(this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
			}
			else if(!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, ModInfo.PREFIX + this.sound, world.getBlockPowerInput(x, y, z) / 2, 1F);
			}
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, neighbor
	 * Block
	 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_)
	{
		if(!world.isRemote)
		{
			if(this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
			}
			else if(!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, ModInfo.PREFIX + this.sound, 5F, 1F);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return this.blockIcon;
	}
}
