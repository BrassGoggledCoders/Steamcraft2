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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockMossyMetalLatticeThin extends BlockMetalLattice
{
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		this.icon[0] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockMossyAluminumLatticeThin");
		this.icon[1] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockMossyCopperLatticeThin");
		this.icon[2] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockMossyTinLatticeThin");
		this.icon[3] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockMossyZincLatticeThin");
		this.icon[4] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockMossyBrassLatticeThin");
		this.icon[5] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockMossyBronzeLatticeThin");
		this.icon[6] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockMossySteelLatticeThin");
		this.icon[7] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockMossyCastIronLatticeThin");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(InitBlocks.blockMossyMetalLatticeThin, 1, var4));
	}
}
