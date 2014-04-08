package common.steamcraft.common.lib2;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import common.steamcraft.common.block.ModBlocks;
import common.steamcraft.common.item.ModTools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabsMod extends CreativeTabs 
{
	public CreativeTabsMod(int position, String name) 
	{
		super(position, name);
	}

	public static CreativeTabs tabSCBlocks = new CreativeTabsMod(CreativeTabs.getNextID(), "SC2Blocks") 
	{
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() 
		{
			return new ItemStack(ModBlocks.decorBrass); 
		}
	};

	public static CreativeTabs tabSCItems = new CreativeTabsMod(CreativeTabs.getNextID(), "SC2Items")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() 
		{
			return new ItemStack(ModTools.drillCore);
		}
	};
}