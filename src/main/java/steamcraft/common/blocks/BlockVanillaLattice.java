
package steamcraft.common.blocks;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockVanillaLattice extends BlockMetalLattice
{
	public IIcon[] blockicon = new IIcon[4];

	public BlockVanillaLattice()
	{
		super("");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		if (metadata < this.blockicon.length)
			return this.blockicon[metadata];
		else
			return this.blockicon[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		this.blockicon[0] = ir.registerIcon(ModInfo.PREFIX + "frame/" + "blockLatticeIron");
		this.blockicon[1] = ir.registerIcon(ModInfo.PREFIX + "frame/" + "blockLatticeGold");
		this.blockicon[2] = ir.registerIcon(ModInfo.PREFIX + "frame/" + "blockLatticeIronThin");
		this.blockicon[3] = ir.registerIcon(ModInfo.PREFIX + "frame/" + "blockLatticeGoldThin");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < this.blockicon.length; ++var4)
			l.add(new ItemStack(this, 1, var4));
	}
}
