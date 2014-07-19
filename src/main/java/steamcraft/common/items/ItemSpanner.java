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
package steamcraft.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.InitBlocks;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileCopperPipe;
import boilerplate.steamapi.UniversalWrench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class ItemSpanner extends UniversalWrench
{
	public ItemSpanner()
	{
		super();
		this.setCreativeTab(Steamcraft.tabSC2);
	}
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		Block block = world.getBlock(x, y, z);

		if(block == InitBlocks.blockCopperPipe)
		{
			TileCopperPipe pipe = (TileCopperPipe) world.getTileEntity(x, y, z);

			pipe.changeExtracting();
		}
		//TODO
		/*if(world.getTileEntity(x, y, z) instanceof BaseTileWithInventory)
		{
			BaseTileWithInventory tile = (BaseTileWithInventory) world.getTileEntity(x, y, z);
			BlockContainer container = (BlockContainer) world.getBlock(x, y, z);
			Item itemblock = new ItemBlockWithExtraData(container, new Object[]{tile.inventory});
			player.inventory.addItemStackToInventory(new ItemStack(itemblock));
			world.setBlockToAir(x, y, z);
			world.setTileEntity(x, y, z, null);
		}*/
		return true;

	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}
}
