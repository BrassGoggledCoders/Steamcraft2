
package steamcraft.common.blocks;

import java.util.List;

import boilerplate.common.baseclasses.blocks.BaseMetadataBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockRustyMetal extends BaseMetadataBlock
{
	public IIcon[] icon = new IIcon[8];

	public BlockRustyMetal()
	{
		super(Material.iron);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeMetal);
		this.setTickRandomly(true);
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
		this.icon[0] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockAluminumRusty");
		this.icon[1] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockCopperRusty");
		this.icon[2] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockTinRusty");
		this.icon[3] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockZincRusty");
		this.icon[4] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockBrassRusty");
		this.icon[5] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockBronzeRusty");
		this.icon[6] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockSteelRusty");
		this.icon[7] = ir.registerIcon(ModInfo.PREFIX + "metal/" + "blockCastIronRusty");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(InitBlocks.blockRustyMetal, 1, var4));
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
	{
		return true;
	}

}
