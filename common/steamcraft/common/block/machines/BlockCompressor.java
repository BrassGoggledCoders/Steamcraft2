package common.steamcraft.common.block.machines;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import common.steamcraft.client.lib2.GuiIDs;
import common.steamcraft.common.SC2;
import common.steamcraft.common.block.tile.TileEntityCompressor;
import common.steamcraft.common.lib2.LibInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressor extends BlockContainerMod
{
	@SideOnly(Side.CLIENT)
	private Icon iconBottom;
	@SideOnly(Side.CLIENT)
	private Icon iconTop;
	@SideOnly(Side.CLIENT)
	private Icon iconFront;
	@SideOnly(Side.CLIENT)
	private Icon iconFrontA;

	public BlockCompressor(int par1)
	{
		super(par1, Material.iron);
	}

	@Override
	public int damageDropped (int metadata)
	{
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta)
	{
		if(side == meta-7) //active icon in world
			return iconFrontA;
		if (meta == 0 && side == 3 || side == meta) //icon in the world and in the inventory
			return iconFront;
		switch (side)
		{
			case 0:
				return iconBottom; // bottom

			case 1:
				return iconTop; // top

			default:
				return blockIcon; // sides
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		blockIcon = par1IconRegister.registerIcon(LibInfo.SC2_PREFIX + "compressor_sides");
		iconFront = par1IconRegister.registerIcon(LibInfo.SC2_PREFIX + "compressor_front");
		iconFrontA = par1IconRegister.registerIcon(LibInfo.SC2_PREFIX + "compressor_front");
		iconTop = par1IconRegister.registerIcon(LibInfo.SC2_PREFIX + "compressor_top");
		iconBottom = par1IconRegister.registerIcon(LibInfo.SC2_PREFIX + "compressor_bottom");
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8,
			float par9)
	{
		if (par1World.isRemote)
			return true;
		else
		{
			TileEntityCompressor tile_entity = (TileEntityCompressor) par1World.getBlockTileEntity(par2, par3, par4);
			if (tile_entity == null || par5EntityPlayer.isSneaking())
				return false;
			par5EntityPlayer.openGui(SC2.instance, GuiIDs.GUI_ID_COMPRESSOR, par1World, par2, par3, par4);
			return true;
		}
	}

	public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
		keepInventory = true;

		if (par0)
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var5+7, 2);
		else
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var5-7, 2);

		keepInventory = false;

		if (tileentity != null)
		{
			tileentity.validate();
			par1World.setBlockTileEntity(par2, par3, par4, tileentity);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);
		if (l>=7)
		{
			float f = par2 + 0.5F;
			float f1 = par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
			float f2 = par4 + 0.5F;
			float f3 = 0.52F;
			float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

			if (l == 4 || l==11)
			{
				par1World.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 5 || l==12)
			{
				par1World.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 2 || l==9)
			{
				par1World.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 3 || l==10)
			{
				par1World.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityCompressor();
	}

	 /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {    	
        int l = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        
    	super.onBlockPlacedBy(world, x, y, z, living, stack);
    }

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		if (!keepInventory)
		{
			TileEntityCompressor var7 = (TileEntityCompressor) par1World.getBlockTileEntity(par2, par3, par4);

			if (var7 != null)
				for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
				{
					ItemStack var9 = var7.getStackInSlot(var8);

					if (var9 != null)
					{
						float var10 = random.nextFloat() * 0.8F + 0.1F;
						float var11 = random.nextFloat() * 0.8F + 0.1F;
						float var12 = random.nextFloat() * 0.8F + 0.1F;

						while (var9.stackSize > 0)
						{
							int var13 = random.nextInt(21) + 10;

							if (var13 > var9.stackSize)
								var13 = var9.stackSize;

							var9.stackSize -= var13;
							EntityItem var14 = new EntityItem(par1World, par2 + var10, par3 + var11, par4 + var12, new ItemStack(var9.itemID, var13,
									var9.getItemDamage()));

							if (var9.hasTagCompound())
								var14.getEntityItem().setTagCompound((NBTTagCompound) var9.getTagCompound().copy());

							float var15 = 0.05F;
							var14.motionX = (float) random.nextGaussian() * var15;
							var14.motionY = (float) random.nextGaussian() * var15 + 0.2F;
							var14.motionZ = (float) random.nextGaussian() * var15;
							par1World.spawnEntityInWorld(var14);
						}
					}
				}
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
	{
		return Container.calcRedstoneFromInventory((IInventory) par1World.getBlockTileEntity(par2, par3, par4));
	}

}
