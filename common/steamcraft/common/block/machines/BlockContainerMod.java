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
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 * 
 * File created @ [Jan 30, 2014, 6:12:00 PM]
 */
package common.steamcraft.common.block.machines;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import common.steamcraft.api.IMachine;
import common.steamcraft.common.lib2.CreativeTabsMod;

/**
 * @author MrArcane111
 *
 */
public abstract class BlockContainerMod extends BlockContainer {
	/** */
	protected static boolean keepInventory = false;
	
	/** */
	protected final Random random = new Random();
	
	/** */
	public static String owner = "[SC2]";
	
	protected BlockContainerMod(int id, Material mat) {
		super(id, mat);
		this.setCreativeTab(CreativeTabsMod.tabSCBlocks);
		this.setHardness(5.0F);
	}
	
	public String getOwner() {
		return owner;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase living, ItemStack stack) {
		super.onBlockPlacedBy(world, i, j, k, living, stack);
	
		if (living instanceof EntityPlayer)
			owner = ((EntityPlayer) living).username;
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int oldID, int oldMeta)  {
		super.breakBlock(world, i, j, k, oldID, oldMeta);
	}

	@Override
	public int getLightValue(IBlockAccess world, int i, int j, int k) {
		TileEntity te = world.getBlockTileEntity(i, j, k);
		
		if (te instanceof IMachine && ((IMachine) te).isActive())
			return super.getLightValue(world, i, j, k) + 8;
		
		return super.getLightValue(world, i, j, k);
	}
}
