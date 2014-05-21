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
 * File created @ [Mar 20, 2014, 10:31:50 AM]
 */
package steamcraft.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.client.fx.FXSmoke;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemChisel extends Item
{
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemChisel");
	}
	
	public ItemChisel(int id)
	{
		super(id);
		this.setMaxStackSize(1);
		this.setMaxDamage(21);
		this.bFull3D = true;
		this.setCreativeTab(Steamcraft.tabSC2);
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		int bid = world.getBlockId(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);

		if (bid == Block.blockIron.blockID)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved.blockID);
		}
		if (bid == Block.blockGold.blockID)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved.blockID);
		}
		if (bid == Block.blockDiamond.blockID)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved.blockID);
		}
		if ((bid == ConfigBlocks.blockCosmetic.blockID) && (metadata == 0)) // Brass block
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved.blockID, 0, 2);
		}
		if ((bid == ConfigBlocks.blockCosmetic.blockID) && (metadata == 1)) // Cast iron block
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved.blockID, 1, 2);
		}
		if ((bid == ConfigBlocks.blockCosmetic.blockID) && (metadata == 13)) // Volucite
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved.blockID, 8, 2);
		}
		if (bid == Block.blockLapis.blockID)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved.blockID);
		}
		if (bid == Block.stone.blockID)
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved.blockID);
		}
		if ((bid == ConfigBlocks.blockCosmetic.blockID) && (metadata == 6)) // Uranium
		{
			world.setBlock(x, y, z, ConfigBlocks.blockEngraved.blockID, 7, 2);
		}
	
		Steamcraft.proxy.smokeFX(world, x + Utils.random.nextFloat(), y + Utils.random.nextFloat(), z + Utils.random.nextFloat(), FXSmoke.class);
		is.damageItem(1, player);
		
		return true;
	}
}
