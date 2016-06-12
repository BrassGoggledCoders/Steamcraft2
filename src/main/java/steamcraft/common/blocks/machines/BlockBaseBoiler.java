
package steamcraft.common.blocks.machines;

import boilerplate.common.baseclasses.blocks.BaseContainerBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author decebaldecebal
 *
 */
public abstract class BlockBaseBoiler extends BaseContainerBlock
{
	@SideOnly(Side.CLIENT)
	protected IIcon iconTop;
	@SideOnly(Side.CLIENT)
	protected IIcon iconFront;
	@SideOnly(Side.CLIENT)
	protected IIcon iconFrontActive;

	public BlockBaseBoiler()
	{
		super(Material.iron);
	}

	@Override
	public int damageDropped(int metadata)
	{
		return 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == (meta - 7))
			return this.iconFrontActive;
		if (((meta == 0) && (side == 3)) || (side == meta))
			return this.iconFront;
		switch (side)
		{
		case 0:
			return this.iconTop; // bottom

		case 1:
			return this.iconTop; // top

		default:
			return this.blockIcon; // sides
		}
	}

	public static void updateBlockState(boolean par0, World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);

		keepInventory = true;

		if (par0)
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var5 + 7, 2);
		else
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var5 - 7, 2);

		keepInventory = false;

		if (tileentity != null)
		{
			tileentity.validate();
			par1World.setTileEntity(par2, par3, par4, tileentity);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		int l = MathHelper.floor_double(((living.rotationYaw * 4.0F) / 360.0F) + 0.5D) & 3;

		if (l == 0)
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);

		if (l == 1)
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);

		if (l == 2)
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);

		if (l == 3)
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);

		super.onBlockPlacedBy(world, x, y, z, living, stack);
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
	{
		return Container.calcRedstoneFromInventory((IInventory) par1World.getTileEntity(par2, par3, par4));
	}
}
