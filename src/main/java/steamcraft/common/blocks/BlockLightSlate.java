
package steamcraft.common.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockLightSlate extends BlockSlate
{
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		// Raw
		this.icon[0] = ir.registerIcon(ModInfo.PREFIX + "blockSlateRawLightBlue");
		this.icon[1] = ir.registerIcon(ModInfo.PREFIX + "blockSlateRawGrey");
		this.icon[2] = ir.registerIcon(ModInfo.PREFIX + "blockSlateRawLightRed");
		// Cobble
		this.icon[3] = ir.registerIcon(ModInfo.PREFIX + "blockSlateCobbleLightBlue");
		this.icon[4] = ir.registerIcon(ModInfo.PREFIX + "blockSlateCobbleGrey");
		this.icon[5] = ir.registerIcon(ModInfo.PREFIX + "blockSlateCobbleLightRed");
		// Brick
		this.icon[6] = ir.registerIcon(ModInfo.PREFIX + "blockSlateBrickLightBlue");
		this.icon[7] = ir.registerIcon(ModInfo.PREFIX + "blockSlateBrickGrey");
		this.icon[8] = ir.registerIcon(ModInfo.PREFIX + "blockSlateBrickLightRed");
		// Polished
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(InitBlocks.blockLightSlate, 1, var4));
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

		switch (metadata)
		{
		case 0:
			drop.add(new ItemStack(InitBlocks.blockLightSlate, 1, 3));
			break;
		case 1:
			drop.add(new ItemStack(InitBlocks.blockLightSlate, 1, 4));
			break;
		case 2:
			drop.add(new ItemStack(InitBlocks.blockLightSlate, 1, 5));
			break;
		default:
			drop.add(0, new ItemStack(InitBlocks.blockLightSlate, 1, metadata));
			break;
		}

		return drop;
	}
}