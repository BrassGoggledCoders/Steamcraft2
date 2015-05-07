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
package steamcraft.common.items.pda;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.init.InitBlocks;
import steamcraft.common.init.InitItems;

public abstract class PDATabs
{
	public static PDATabs[] tabArray = new PDATabs[2];
	public static final PDATabs tabBlocks = new PDATabs(0, "blockInfo")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(InitBlocks.blockSteamBoiler);
		}
	};
	public static final PDATabs tabItems = new PDATabs(1, "itemInfo")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return InitItems.itemCanisterSteam;
		}
	};
	private final int tabIndex;
	private final String tabLabel;
	@SideOnly(Side.CLIENT)
	private ItemStack field_151245_t;

	public PDATabs(String lable)
	{
		this(getNextID(), lable);
	}

	public PDATabs(int p_i1853_1_, String p_i1853_2_)
	{
		if(p_i1853_1_ >= tabArray.length)
		{
			PDATabs[] tmp = new PDATabs[p_i1853_1_ + 1];
			System.arraycopy(tabArray, 0, tmp, 0, tabArray.length);
			tabArray = tmp;
		}
		this.tabIndex = p_i1853_1_;
		this.tabLabel = p_i1853_2_;
		tabArray[p_i1853_1_] = this;
	}

	@SideOnly(Side.CLIENT)
	public int getTabIndex()
	{
		return this.tabIndex;
	}

	@SideOnly(Side.CLIENT)
	public String getTabLabel()
	{
		return this.tabLabel;
	}

	/**
	 * Gets the translated Label.
	 */
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "itemGroup." + this.getTabLabel();
	}

	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack()
	{
		if(this.field_151245_t == null)
		{
			this.field_151245_t = new ItemStack(this.getTabIconItem(), 1, this.func_151243_f());
		}

		return this.field_151245_t;
	}

	@SideOnly(Side.CLIENT)
	public abstract Item getTabIconItem();

	@SideOnly(Side.CLIENT)
	public int func_151243_f()
	{
		return 0;
	}

	/**
	 * returns index % 6
	 */
	@SideOnly(Side.CLIENT)
	public int getTabColumn()
	{
		if(this.tabIndex > 11)
		{
			return ((this.tabIndex - 12) % 10) % 5;
		}
		return this.tabIndex % 6;
	}

	/**
	 * returns tabIndex < 6
	 */
	@SideOnly(Side.CLIENT)
	public boolean isTabInFirstRow()
	{
		if(this.tabIndex > 11)
		{
			return ((this.tabIndex - 12) % 10) < 5;
		}
		return this.tabIndex < 6;
	}

	public static int getNextID()
	{
		return tabArray.length;
	}
}