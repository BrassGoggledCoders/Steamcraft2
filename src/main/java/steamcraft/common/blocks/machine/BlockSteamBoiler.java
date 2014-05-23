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
package steamcraft.common.blocks.machine;

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
import steamcraft.client.gui.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileSteamBoiler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSteamBoiler extends BlockContainerMod
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	@SideOnly(Side.CLIENT)
	private IIcon iconFrontA;

	public BlockSteamBoiler() // TODO: This class needs cleanup; consolidate the
								// icons to an array of icons
	{
		super(Material.iron);
		// this.setUnlocalizedName("steamBoiler");
	}

	@Override
	public int damageDropped(final int metadata)
	{
		return 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(final int side, final int meta)
	{
		if (side == meta - 7)
		{
			return iconFrontA;
		}
		if (meta == 0 && side == 3 || side == meta)
		{
			return iconFront;
		}
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

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(final IIconRegister par1IconRegister)
	{
		blockIcon = par1IconRegister.registerIcon(LibInfo.PREFIX
				+ "generatorside");
		iconFront = par1IconRegister.registerIcon(LibInfo.PREFIX
				+ "generatorfrontinactive");
		iconFrontA = par1IconRegister.registerIcon(LibInfo.PREFIX
				+ "generatorfrontinactive");
		iconTop = par1IconRegister
				.registerIcon(LibInfo.PREFIX + "generatortop");
	}

	@Override
	public boolean onBlockActivated(final World par1World, final int par2,
			final int par3, final int par4,
			final EntityPlayer par5EntityPlayer, final int par6,
			final float par7, final float par8, final float par9)
	{
		if (par1World.isRemote)
		{
			return true;
		}
		else
		{
			final TileSteamBoiler tile_entity = (TileSteamBoiler) par1World
					.getTileEntity(par2, par3, par4);
			if (tile_entity == null || par5EntityPlayer.isSneaking())
			{
				return false;
			}
			par5EntityPlayer.openGui(Steamcraft.instance,
					GuiIDs.GUI_ID_COAL_GENERATOR, par1World, par2, par3, par4);
			return true;
		}
	}

	public static void updateFurnaceBlockState(final boolean par0,
			final World par1World, final int par2, final int par3,
			final int par4)
	{
		final int var5 = par1World.getBlockMetadata(par2, par3, par4);
		final TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		keepInventory = true;

		if (par0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var5 + 7, 2);
		}
		else
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var5 - 7, 2);
		}

		keepInventory = false;

		if (tileentity != null)
		{
			tileentity.validate();
			par1World.setTileEntity(par2, par3, par4, tileentity);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(final World par1World, final int par2,
			final int par3, final int par4, final Random par5Random)
	{
		final int l = par1World.getBlockMetadata(par2, par3, par4);
		if (l >= 7)
		{
			final float f = par2 + 0.5F;
			final float f1 = par3 + 0.0F + par5Random.nextFloat() * 6.0F
					/ 16.0F;
			final float f2 = par4 + 0.5F;
			final float f3 = 0.52F;
			final float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

			if (l == 4 || l == 11)
			{
				par1World.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D,
						0.0D, 0.0D);
				par1World.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D,
						0.0D, 0.0D);
			}
			else if (l == 5 || l == 12)
			{
				par1World.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D,
						0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D,
						0.0D, 0.0D);
			}
			else if (l == 2 || l == 9)
			{
				par1World.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D,
						0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D,
						0.0D, 0.0D);
			}
			else if (l == 3 || l == 10)
			{
				par1World.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D,
						0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D,
						0.0D, 0.0D);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(final World par1World,
			final int metadata)
	{
		return new TileSteamBoiler();
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(final World world, final int x, final int y,
			final int z, final EntityLivingBase living, final ItemStack stack)
	{
		final int l = MathHelper
				.floor_double(living.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

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
	public void breakBlock(final World par1World, final int par2,
			final int par3, final int par4, final Block block, final int par6)
	{
		if (!keepInventory)
		{
			final TileSteamBoiler var7 = (TileSteamBoiler) par1World
					.getTileEntity(par2, par3, par4);

			if (var7 != null)
			{
				for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
				{
					final ItemStack var9 = var7.getStackInSlot(var8);

					if (var9 != null)
					{
						final float var10 = random.nextFloat() * 0.8F + 0.1F;
						final float var11 = random.nextFloat() * 0.8F + 0.1F;
						final float var12 = random.nextFloat() * 0.8F + 0.1F;

						while (var9.stackSize > 0)
						{
							int var13 = random.nextInt(21) + 10;

							if (var13 > var9.stackSize)
							{
								var13 = var9.stackSize;
							}

							var9.stackSize -= var13;
							final EntityItem var14 = new EntityItem(par1World,
									par2 + var10, par3 + var11, par4 + var12,
									new ItemStack(var9.getItem(), var13,
											var9.getItemDamage()));

							if (var9.hasTagCompound())
							{
								var14.getEntityItem().setTagCompound(
										(NBTTagCompound) var9.getTagCompound()
												.copy());
							}

							final float var15 = 0.05F;
							var14.motionX = (float) random.nextGaussian()
									* var15;
							var14.motionY = (float) random.nextGaussian()
									* var15 + 0.2F;
							var14.motionZ = (float) random.nextGaussian()
									* var15;
							par1World.spawnEntityInWorld(var14);
						}
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(final World par1World,
			final int par2, final int par3, final int par4, final int par5)
	{
		return Container.calcRedstoneFromInventory((IInventory) par1World
				.getTileEntity(par2, par3, par4));
	}
}
