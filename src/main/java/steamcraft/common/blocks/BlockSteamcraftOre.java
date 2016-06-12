
package steamcraft.common.blocks;

import java.util.ArrayList;
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
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

/**
 * @author Surseance
 *
 */
public class BlockSteamcraftOre extends BaseMetadataBlock
{
	private final IIcon[] icon = new IIcon[7];

	public BlockSteamcraftOre()
	{
		super(Material.rock);
		this.setHarvestLevel("pickaxe", 2, 0);
		this.setHarvestLevel("pickaxe", 1, 1);
		this.setHarvestLevel("pickaxe", 1, 2);
		this.setHarvestLevel("pickaxe", 2, 3);
		this.setHarvestLevel("pickaxe", 2, 4);
		this.setHarvestLevel("pickaxe", 2, 5);
		this.setHarvestLevel("pickaxe", 2, 6);
		this.setHardness(3.0F);
		this.setResistance(6.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setTickRandomly(true);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		if (metadata < this.icon.length)
			return this.icon[metadata];
		else
			return this.icon[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon[0] = ir.registerIcon(ModInfo.PREFIX + "ore/" + "oreAluminum");
		this.icon[1] = ir.registerIcon(ModInfo.PREFIX + "ore/" + "oreCopper");
		this.icon[2] = ir.registerIcon(ModInfo.PREFIX + "ore/" + "oreTin");
		this.icon[3] = ir.registerIcon(ModInfo.PREFIX + "ore/" + "oreZinc");
		this.icon[4] = ir.registerIcon(ModInfo.PREFIX + "ore/" + "oreUranite");
		this.icon[5] = ir.registerIcon(ModInfo.PREFIX + "ore/" + "oreBrimstone");
		this.icon[6] = ir.registerIcon(ModInfo.PREFIX + "ore/" + "orePhosphate");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < 7; ++var4)
			l.add(new ItemStack(InitBlocks.blockCustomOre, 1, var4));
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

		switch (metadata)
		{
		case 4:
			drop.add(new ItemStack(InitItems.itemResource, 1, 3));
			break;
		case 5:
			drop.add(new ItemStack(InitItems.itemResource, 2 + world.rand.nextInt(3), 0));
			break;
		case 6:
			drop.add(new ItemStack(InitItems.itemResource, 2 + world.rand.nextInt(3), 1));
			break;
		default:
			drop.add(0, new ItemStack(InitBlocks.blockCustomOre, 1, metadata));
			break;
		}

		return drop;
	}
}
