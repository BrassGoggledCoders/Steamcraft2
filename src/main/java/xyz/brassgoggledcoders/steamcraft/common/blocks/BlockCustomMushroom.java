package xyz.brassgoggledcoders.steamcraft.common.blocks;

import java.util.List;

import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import xyz.brassgoggledcoders.steamcraft.common.init.InitBlocks;
import xyz.brassgoggledcoders.steamcraft.common.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCustomMushroom extends BlockMushroom
{
	public IIcon[] icon = new IIcon[3];

	public BlockCustomMushroom()
	{
		super();
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return this.icon[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		this.icon[0] = ir.registerIcon(ModInfo.PREFIX + "blockMushroom");
		this.icon[1] = ir.registerIcon(ModInfo.PREFIX + "blockMushroom1");
		this.icon[2] = ir.registerIcon(ModInfo.PREFIX + "blockMushroom2");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(InitBlocks.blockMushroom, 1, var4));
	}

}
