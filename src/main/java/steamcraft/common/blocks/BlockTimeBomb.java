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

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.blocks.machines.BaseContainerBlock;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileTimeBomb;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class BlockTimeBomb extends BaseContainerBlock
{
	@SideOnly(Side.CLIENT)
	private IIcon iconEnd;

	public BlockTimeBomb(Material p_i45394_1_)
	{
		super(p_i45394_1_);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileTimeBomb();
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if(world.isRemote)
			return true;
		else
		{
			TileTimeBomb tile = (TileTimeBomb) world.getTileEntity(par2, par3, par4);

			if(tile == null || player.isSneaking())
				return false;

			player.openGui(Steamcraft.instance, GuiIDs.TIMEBOMB, world, par2, par3, par4);
			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		switch(side)
		{
			case 1:
				return this.iconEnd; // top
			default:
				return this.blockIcon; // sides
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		this.blockIcon = icon.registerIcon(LibInfo.PREFIX + "blockTimebomb");
		this.iconEnd = icon.registerIcon(LibInfo.PREFIX + "blockTimebombTop");
	}
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack stack)
	{
		if(ent != null && ent instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)ent;
			FMLLog.info("Time Bomb placed at X: " + Integer.toString(x) + " Y: " + Integer.toString(y) + " Z: " + Integer.toString(z) + " by player: " + player.getCommandSenderName(), "");
		}
	}


}
