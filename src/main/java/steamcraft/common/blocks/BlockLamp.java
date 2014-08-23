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

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.InitBlocks;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 * 
 */
public class BlockLamp extends BlockRedstoneLight
{
	private IIcon[] icon = new IIcon[2];

	private static boolean powered;

	public BlockLamp()
	{
		super(powered);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setCreativeTab(Steamcraft.tabSC2);

		if(powered)
			this.setLightLevel(0.98F);
	}
	
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
		this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockLampOff");
		this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockLampOn");
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if(!world.isRemote)
			if(BlockLamp.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, InitBlocks.blockLamp, 1, 12);
			}
			else if(!BlockLamp.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, InitBlocks.blockLamp, 1, 11);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_)
	{
		if(!world.isRemote)
			if(BlockLamp.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, InitBlocks.blockLamp, 1, 12);
			}
			else if(!BlockLamp.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, InitBlocks.blockLamp, 0, 11);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if(!world.isRemote && BlockLamp.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			world.setBlock(x, y, z, InitBlocks.blockLamp, 0, 12);
	}
}
