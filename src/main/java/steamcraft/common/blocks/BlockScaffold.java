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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.blocks.BaseMetadataBlock;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockScaffold extends BaseMetadataBlock
{
	public IIcon[] icon = new IIcon[6];
	String[] textureSuffixs;

	public BlockScaffold(String[] suffixs)
	{
		super(Material.wood);
		this.textureSuffixs = suffixs;
		this.setHardness(1.0F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundTypeWood);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int side, final int metadata)
	{
		if (metadata < this.icon.length)
			return this.icon[metadata];
		else
			return this.icon[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		for (int i = 0; i < this.icon.length; i++)
			this.icon[i] = ir.registerIcon(ModInfo.PREFIX + "frame/" + "blockScaffold" + textureSuffixs[i]);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(this, 1, var4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 0;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockTransWithInsideRI;
	}

	@Override
	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
	{
		return true;
	}
}
