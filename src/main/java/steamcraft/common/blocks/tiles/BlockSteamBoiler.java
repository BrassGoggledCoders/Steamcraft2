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
 * File created @ 23-May-2014
 */
package steamcraft.common.blocks.tiles;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileSteamBoiler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockSteamBoiler.
 */
public class BlockSteamBoiler extends BlockContainerMod
{

	/** The icon top. */
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	/** The icon front. */
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;

	/** The icon front active. */
	@SideOnly(Side.CLIENT)
	private IIcon iconFrontActive;

	/**
	 * Instantiates a new block steam boiler.
	 */
	public BlockSteamBoiler()
	{
		super(Material.iron);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#damageDropped(int)
	 */
	@Override
	public int damageDropped(int metadata)
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == (meta - 7))
			return iconFrontActive;
		if (((meta == 0) && (side == 3)) || (side == meta))
			return iconFront;
		switch (side)
		{
		case 0:
			return iconTop; // bottom

		case 1:
			return iconTop; // top

		default:
			return blockIcon; // sides
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#registerBlockIcons(net.minecraft.client.renderer
	 * .texture.IIconRegister)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		blockIcon = icon.registerIcon(LibInfo.PREFIX + "blockSteamBoilerSide");
		iconFront = icon.registerIcon(LibInfo.PREFIX + "blockSteamBoilerFrontInactive");
		iconFrontActive = icon.registerIcon(LibInfo.PREFIX + "blockSteamBoilerFrontActive");
		iconTop = icon.registerIcon(LibInfo.PREFIX + "blockSteamBoilerTop");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#onBlockActivated(net.minecraft.world.World,
	 * int, int, int, net.minecraft.entity.player.EntityPlayer, int, float,
	 * float, float)
	 */
	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
			return true;
		else
		{
			TileSteamBoiler tile = (TileSteamBoiler) world.getTileEntity(par2, par3, par4);

			if ((tile == null) || player.isSneaking())
				return false;

			System.out.println("works1");
			player.openGui(Steamcraft.instance, GuiIDs.GUI_ID_STEAM_BOILER, world, par2, par3, par4);
			return true;
		}
	}

	/**
	 * Update furnace block state.
	 * 
	 * @param par0
	 *            the par0
	 * @param par1World
	 *            the par1 world
	 * @param par2
	 *            the par2
	 * @param par3
	 *            the par3
	 * @param par4
	 *            the par4
	 */
	public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#randomDisplayTick(net.minecraft.world.World,
	 * int, int, int, java.util.Random)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int par2, int par3, int par4, Random par5Random)
	{
		int l = world.getBlockMetadata(par2, par3, par4);
		if (l >= 7)
		{
			float f = par2 + 0.5F;
			float f1 = par3 + 0.0F + ((par5Random.nextFloat() * 6.0F) / 16.0F);
			float f2 = par4 + 0.5F;
			float f3 = 0.52F;
			float f4 = (par5Random.nextFloat() * 0.6F) - 0.3F;

			if ((l == 4) || (l == 11))
			{
				world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if ((l == 5) || (l == 12))
			{
				world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if ((l == 2) || (l == 9))
			{
				world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			}
			else if ((l == 3) || (l == 10))
			{
				world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft
	 * .world.World, int)
	 */
	@Override
	public TileEntity createNewTileEntity(World par1World, int metadata)
	{
		return new TileSteamBoiler();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * steamcraft.common.blocks.machine.BlockContainerMod#onBlockPlacedBy(net
	 * .minecraft.world.World, int, int, int,
	 * net.minecraft.entity.EntityLivingBase, net.minecraft.item.ItemStack)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * steamcraft.common.blocks.machine.BlockContainerMod#breakBlock(net.minecraft
	 * .world.World, int, int, int, net.minecraft.block.Block, int)
	 */
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block block, int par6)
	{
		if (!keepInventory)
		{
			TileSteamBoiler var7 = (TileSteamBoiler) par1World.getTileEntity(par2, par3, par4);

			if (var7 != null)
				for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
				{
					ItemStack var9 = var7.getStackInSlot(var8);

					if (var9 != null)
					{
						float var10 = (random.nextFloat() * 0.8F) + 0.1F;
						float var11 = (random.nextFloat() * 0.8F) + 0.1F;
						float var12 = (random.nextFloat() * 0.8F) + 0.1F;

						while (var9.stackSize > 0)
						{
							int var13 = random.nextInt(21) + 10;

							if (var13 > var9.stackSize)
								var13 = var9.stackSize;

							var9.stackSize -= var13;
							EntityItem var14 = new EntityItem(par1World, par2 + var10, par3 + var11, par4 + var12, new ItemStack(var9.getItem(),
									var13, var9.getItemDamage()));

							if (var9.hasTagCompound())
								var14.getEntityItem().setTagCompound((NBTTagCompound) var9.getTagCompound().copy());

							float var15 = 0.05F;
							var14.motionX = (float) random.nextGaussian() * var15;
							var14.motionY = ((float) random.nextGaussian() * var15) + 0.2F;
							var14.motionZ = (float) random.nextGaussian() * var15;
							par1World.spawnEntityInWorld(var14);
						}
					}
				}
		}

		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#hasComparatorInputOverride()
	 */
	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#getComparatorInputOverride(net.minecraft.world
	 * .World, int, int, int, int)
	 */
	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
	{
		return Container.calcRedstoneFromInventory((IInventory) par1World.getTileEntity(par2, par3, par4));
	}
}
