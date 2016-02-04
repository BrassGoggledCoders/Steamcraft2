
package steamcraft.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.blocks.BaseMetadataBlock;
import brassutils.api.IEngravable;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
@Optional.Interface(iface = "brassutils.api.IEngravable", modid = "brassutils")
public class BlockMetal extends BaseMetadataBlock implements IEngravable
{
	public IIcon[] icon = new IIcon[8];

	public BlockMetal()
	{
		super(Material.iron);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeMetal);
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
		this.icon[0] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockAluminum");
		this.icon[1] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockCopper");
		this.icon[2] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockTin");
		this.icon[3] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockZinc");
		this.icon[4] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockBrass");
		this.icon[5] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockBronze");
		this.icon[6] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockSteel");
		this.icon[7] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockCastIron");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(InitBlocks.blockMetal, 1, var4));
	}

	@Override
	public Block getChiseledVariant()
	{
		return InitBlocks.blockEngraved;
	}

	@Override
	public int getChiseledVariantMeta()
	{
		return -1;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
	{
		return true;
	}

}
