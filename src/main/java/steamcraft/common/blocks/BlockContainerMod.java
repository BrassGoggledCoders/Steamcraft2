package steamcraft.common.blocks;

import java.util.Random;

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
	protected static boolean keepInventory = false;

	/** */
	protected final Random random = new Random();

	/** */
	public static String owner = "[SC2]";

	protected BlockContainerMod(int id, Material mat)
	{
		super(id, mat);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.setHardness(5.0F);
	}

	public String getOwner() 
	{
		return owner;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase living, ItemStack is) 
	{
		super.onBlockPlacedBy(world, i, j, k, living, is);

		if (living instanceof EntityPlayer)
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
		TileEntity te = world.getBlockTileEntity(i, j, k);

		if (te instanceof IMachine && ((IMachine) te).isActive())
			return super.getLightValue(world, i, j, k) + 8;

		return super.getLightValue(world, i, j, k);
	}
}
