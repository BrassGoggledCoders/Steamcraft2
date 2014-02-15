/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Jan 30, 2014, 6:12:00 PM]
 */
package common.steamcraft.mod.common.block;

import common.steamcraft.mod.client.core.helper.SC2_IconHelper;
import common.steamcraft.mod.common.lib.SC2_CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author MrArcane111
 *
 */
public abstract class BlockSC2Container extends BlockContainer
{
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		blockIcon = SC2_IconHelper.forBlock(icon, this);
	}
	
	protected static boolean keepInventory = false;
	protected final Random random = new Random();
	public static String owner = "[SC2]";
	
	protected BlockSC2Container(int id, Material mat)
	{
		super(id, mat);
		this.setCreativeTab(SC2_CreativeTabs.tabSCBlocks);
		this.setHardness(5.0F);
	}
	
	public String getOwner() 
	{
		return owner;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase living, ItemStack stack)
	{
		super.onBlockPlacedBy(world, i, j, k, living, stack);
	
		if(living instanceof EntityPlayer)
			owner = ((EntityPlayer) living).username;
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int oldID, int oldMeta) 
	{
		super.breakBlock(world, i, j, k, oldID, oldMeta);
	}

	@Override
	public int getLightValue(IBlockAccess world, int i, int j, int k)
	{
		TileEntity tile = world.getBlockTileEntity(i, j, k);
		
		if(tile instanceof IMachine && ((IMachine) tile).isActive())
			return super.getLightValue(world, i, j, k) + 8;
		
		return super.getLightValue(world, i, j, k);
	}
}
