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

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.InitBlocks;
import steamcraft.common.InitItems;
import steamcraft.common.lib.ModInfo;
import boilerplate.steamapi.block.IHammerable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 * 
 */
public class BlockSteamcraftOre extends BaseBlock implements IHammerable
{
	private final IIcon[] icon = new IIcon[7];

	public BlockSteamcraftOre()
	{
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(6.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		if(metadata < icon.length)
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
		for(int var4 = 0; var4 < 7; ++var4)
			l.add(new ItemStack(InitBlocks.blockCustomOre, 1, var4));
	}

	@Override
	public ItemStack getOutput(int meta)
	{
		if(meta < 4)
			return new ItemStack(InitItems.itemPowder);
		else
			return null;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

		switch(metadata)
		{
			case 4:
				drop.add(new ItemStack(InitItems.itemResource, 1, 4));
				break;
			case 5:
				drop.add(new ItemStack(InitItems.itemResource, 1, 1));
				break;
			case 6:
				drop.add(new ItemStack(InitItems.itemResource, 1, 3));
				break;
			default:
				drop.add(0, new ItemStack(InitBlocks.blockCustomOre, 1, metadata));
				break;
		}

		return drop;
	}
}
