package common.steamcraft.mod.common.lib;

import common.steamcraft.mod.common.block.ModBlocks;
import common.steamcraft.mod.common.item.ModTools;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

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