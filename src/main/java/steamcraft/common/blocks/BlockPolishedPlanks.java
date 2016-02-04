
package steamcraft.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.blocks.BaseMetadataBlock;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockPolishedPlanks extends BaseMetadataBlock
{
	public IIcon[] icon = new IIcon[3];

	public BlockPolishedPlanks()
	{
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setCreativeTab(Steamcraft.tabSC2);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		this.icon[0] = ir.registerIcon(ModInfo.PREFIX + "blockPolishedPlanks");
		this.icon[1] = ir.registerIcon(ModInfo.PREFIX + "blockVerticalPolishedPlanks");
		this.icon[2] = ir.registerIcon(ModInfo.PREFIX + "blockPolishedPanel");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int side, final int metadata)
	{
		return this.icon[metadata];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(InitBlocks.blockPolishedPlanks, 1, var4));
	}
}
