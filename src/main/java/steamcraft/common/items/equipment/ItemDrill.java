/*
 * 
 */
package steamcraft.common.items.equipment;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * Base class for drills.
 * 
 * @author Decebaldecebal
 * 
 */
public class ItemDrill extends ItemCoreDrill
{

	/** An array of blocks the drill can mine. */
	public static final Block[] blocksEffectiveAgainst = new Block[] { Blocks.cobblestone, Blocks.dirt, Blocks.stone, Blocks.sand, Blocks.clay,
			Blocks.ice, Blocks.snow, Blocks.netherrack, Blocks.grass, Blocks.gravel };

	/** The tool material. */
	protected ToolMaterial toolMaterial;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * steamcraft.common.items.equipment.ItemCoreDrill#registerIcons(net.minecraft
	 * .client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		this.itemIcon = icon.registerIcon(LibInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
	}

	/**
	 * Instantiates a new item drill.
	 * 
	 * @param mat
	 *            the mat
	 */
	public ItemDrill(ToolMaterial mat)
	{
		super();
		this.setCreativeTab(Steamcraft.tabSC2);
		this.toolMaterial = mat;
		this.setMaxDamage(toolMaterial.getMaxUses());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#canHarvestBlock(net.minecraft.block.Block,
	 * net.minecraft.item.ItemStack)
	 */
	@SuppressWarnings("all")
	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		for (Block element : ItemDrill.blocksEffectiveAgainst)
		{
			if (element == block)
			{
				return true;
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * boilerplate.common.RootItem#addInformation(net.minecraft.item.ItemStack,
	 * net.minecraft.entity.player.EntityPlayer, java.util.List, boolean)
	 */
	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		super.addInformation(stack, player, list, flag);
		list.add("Efficiency: " + this.getEfficiency());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#hitEntity(net.minecraft.item.ItemStack,
	 * net.minecraft.entity.EntityLivingBase,
	 * net.minecraft.entity.EntityLivingBase)
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase hitEntity, EntityLivingBase player)
	{
		// if(this.getEnergy(stack) > 0) {
		hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), (this.toolMaterial.getDamageVsEntity()));
		stack.damageItem(1, player);
		// this.setEnergy(stack, getEnergy(stack) - this.energyPerBlock * 2);
		// } else {
		// hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player),
		// 1);
		// }

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#getDigSpeed(net.minecraft.item.ItemStack,
	 * net.minecraft.block.Block, int)
	 */
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		return /* this.getEnergy(stack) != 0 && */this.canHarvestBlock(block, stack) ? this.getEfficiency() : 1.0F;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.item.Item#onBlockDestroyed(net.minecraft.item.ItemStack,
	 * net.minecraft.world.World, net.minecraft.block.Block, int, int, int,
	 * net.minecraft.entity.EntityLivingBase)
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		// if(block.getBlockHardness(world, x, y, z) != 0.0D)
		stack.damageItem(1, living);
		// this.setEnergy(stack, getEnergy(stack) - this.energyPerBlock);
		// else
		// stack.damageItem(1, living);
		// this.setEnergy(stack, getEnergy(stack) - this.energyPerBlock/2);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#isFull3D()
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	/**
	 * Gets the efficiency.
	 * 
	 * @return the efficiency
	 */
	public float getEfficiency()
	{
		return this.toolMaterial.getEfficiencyOnProperMaterial();
	}
}
