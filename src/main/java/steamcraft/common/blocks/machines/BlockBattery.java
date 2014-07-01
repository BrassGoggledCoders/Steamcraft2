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
 * File created @ [Jul 1, 2014, 1:55:12 PM]
 */
package steamcraft.common.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileBattery;
import steamcraft.common.tiles.TileSteamBoiler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author decebaldecebal
 *
 */
public class BlockBattery extends BlockContainerMod
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	public BlockBattery()
	{
		super(Material.iron);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
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
	public void registerBlockIcons(IIconRegister icon)
	{
		blockIcon = icon.registerIcon(LibInfo.PREFIX + "blockBatterySide");
		iconTop = icon.registerIcon(LibInfo.PREFIX + "blockSteamBoilerTop");
	}

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

			player.openGui(Steamcraft.instance, GuiIDs.GUI_ID_STEAM_BOILER, world, par2, par3, par4);
			return true;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileBattery();
	}
	
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
