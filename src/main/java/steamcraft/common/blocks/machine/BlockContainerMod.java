package steamcraft.common.blocks.machine;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.api.machines.IMachine;
import steamcraft.common.Steamcraft;

public abstract class BlockContainerMod extends BlockContainer
{
	/** */
	protected static boolean keepInventory = true;
	
	/** */
	protected final Random random = new Random();
	
	/** */
	public static String owner = "[SC2]";
	
	protected BlockContainerMod(Material mat)
	{
		super(mat);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.setHardness(5.0F);
	}
	
	public String getOwner()
	{
		return owner;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack is) 
	{
		super.onBlockPlacedBy(world, x, y, z, entityLiving, is);
	
		if (entityLiving instanceof EntityPlayer)
			owner = owner + ((EntityPlayer)entityLiving).getCommandSenderName();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) 
	{
		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		
		if (te instanceof IMachine && ((IMachine) te).isActive())
			return super.getLightValue(world, x, y, z) + 8;
		
		return super.getLightValue(world, x, y, z);
	}
}
