
package steamcraft.common.blocks.machines;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.tiles.TileInjector;

/**
 * @author decebaldecebal
 *
 */
public class BlockInjector extends BlockBaseBoiler
{
	public BlockInjector()
	{
		super();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon)
	{

	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
			return true;
		else
		{
			TileInjector tile = (TileInjector) world.getTileEntity(par2, par3, par4);

			if ((tile == null) || player.isSneaking())
				return false;

			player.openGui(Steamcraft.instance, GuiIDs.INJECTOR, world, par2, par3, par4);
			return true;
		}
	}

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

	@Override
	public TileEntity createNewTileEntity(World par1World, int metadata)
	{
		return new TileInjector();
	}
}
