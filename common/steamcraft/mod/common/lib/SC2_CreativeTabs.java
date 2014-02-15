package common.steamcraft.mod.common.lib;

import common.steamcraft.mod.common.block.ModBlocks;
import common.steamcraft.mod.common.item.ModTools;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SC2_CreativeTabs extends CreativeTabs 
{
	public SC2_CreativeTabs(int position, String name) 
	{
		super(position, name);
	}

	public static CreativeTabs tabSCBlocks = new SC2_CreativeTabs(CreativeTabs.getNextID(), "SC2Blocks") 
	{
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() 
		{
			return new ItemStack(ModBlocks.decorBrass); 
		}
		
		@Override
		public String getTranslatedTabLabel()
		{
			return "Steamcraft 2 Blocks";
		}
	};

	public static CreativeTabs tabSCItems = new SC2_CreativeTabs(CreativeTabs.getNextID(), "SC2Items")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() 
		{
			return new ItemStack(ModTools.drillCore);
		}
		
		@Override
		public String getTranslatedTabLabel()
		{
			return "Steamcraft 2 Items";
		}
	};
}