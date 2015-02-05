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
package steamcraft.common.blocks;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.ModInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 * 
 */
public class BlockEngravedVanilla extends BlockEngravedSolid
{
	private final IIcon[] icon = new IIcon[5];

	public BlockEngravedVanilla()
	{
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int side, final int metadata)
	{
		if(metadata < this.icon.length)
			return this.icon[metadata];
		else
			return this.icon[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon[0] = ir.registerIcon(ModInfo.PREFIX + "blockEngravedDiamond");
		this.icon[1] = ir.registerIcon(ModInfo.PREFIX + "blockEngravedGold");
		this.icon[2] = ir.registerIcon(ModInfo.PREFIX + "blockEngravedIron");
		this.icon[3] = ir.registerIcon(ModInfo.PREFIX + "blockEngravedLapis");
		this.icon[4] = ir.registerIcon(ModInfo.PREFIX + "blockEngravedStone");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List l)
	{
		for(int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(InitBlocks.blockEngravedVanilla, 1, var4));
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}
