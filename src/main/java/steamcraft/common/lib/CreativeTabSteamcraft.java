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
 * File created @ [Mar 12, 2014, 4:23:41 PM]
 */
package steamcraft.common.lib;

import java.util.Collections;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import steamcraft.common.config.ConfigItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class CreativeTabSteamcraft.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class CreativeTabSteamcraft extends CreativeTabs
{

	/**
	 * Instantiates a new creative tab steamcraft.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 */
	public CreativeTabSteamcraft(int id, String name)
	{
		super(id, name);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.creativetab.CreativeTabs#getTabIconItem()
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return ConfigItems.brassGoggles;
	}
}
