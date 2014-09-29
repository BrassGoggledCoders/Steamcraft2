package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TilePlankStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlankStack extends BlockContainer
{
	private int numStoredPlanks = 6;
	private int plankMeta = 0;

	public BlockPlankStack(Material mat)
	{
		super(mat);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random p_149745_1_)
	{
		return this.getNumStoredPlanks();
	}

	@Override
	public int damageDropped(int p_149692_1_)
	{
		return this.getPlankMeta();
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(Blocks.planks);
	}

	public int getNumStoredPlanks()
	{
		return this.numStoredPlanks;
	}

	public Block setNumStoredPlanks(int numStoredPlanks)
	{
		this.numStoredPlanks = numStoredPlanks;
		return this;
	}

	public int getPlankMeta()
	{
		return this.plankMeta;
	}

	public Block setPlankMeta(int plankMeta)
	{
		this.plankMeta = plankMeta;
		return this;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TilePlankStack();
	}

	@Override
	public void onBlockPlacedBy(World world, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
	{
		this.setNumStoredPlanks(6);
		this.setPlankMeta(0);
	}

}
