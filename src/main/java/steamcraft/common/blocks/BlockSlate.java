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
 * File created @ [13 Apr 2014, 09:36:19]
 */
package steamcraft.common.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class BlockSlate extends Block
{
	private IIcon[] icon = new IIcon[9];

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return this.icon[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		//Raw
		this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockSlateRawBlue");
		this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockSlateRawBlack");
		this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "blockSlateRawRed");
		//Cobble
		this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "blockSlateCobbleBlue");
		this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "blockSlateCobbleBlack");
		this.icon[5] = ir.registerIcon(LibInfo.PREFIX + "blockSlateCobbleRed");
		//Brick
		this.icon[6] = ir.registerIcon(LibInfo.PREFIX + "blockSlateBrickBlue");
		this.icon[7] = ir.registerIcon(LibInfo.PREFIX + "blockSlateBrickBlack");
		this.icon[8] = ir.registerIcon(LibInfo.PREFIX + "blockSlateBrickRed");
		//Polished
	}

	public BlockSlate()
	{
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeStone);
		//this.setUnlocalizedName("blockSlate");
		this.setTickRandomly(true);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public int damageDropped(int id)
	{
		return id;
	}
}