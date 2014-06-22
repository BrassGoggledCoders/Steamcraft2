/*
 *
 */
package steamcraft.common.items.equipment;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemModSword.
 */
public class ItemModSword extends ItemModTool
{

	/** The tool material. */
	protected ToolMaterial toolMaterial;

	/**
	 * Instantiates a new item mod sword.
	 *
	 * @param toolMat
	 *            the tool mat
	 */
	public ItemModSword(ToolMaterial toolMat)
	{
		super(0, toolMat, blocksEffectiveAgainst);
		this.toolMaterial = toolMat;
		this.maxStackSize = 1;
		this.setMaxDamage(toolMat.getMaxUses());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemModTool#getDigSpeed(net.minecraft
	 * .item.ItemStack, net.minecraft.block.Block, int)
	 */
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if (block == Blocks.web)
		{
			return 15.0F;
		}
		else
		{
			Material material = block.getMaterial();
			return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves ? 1.0F
					: 1.5F;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemModTool#onBlockDestroyed(net.minecraft
	 * .item.ItemStack, net.minecraft.world.World, net.minecraft.block.Block,
	 * int, int, int, net.minecraft.entity.EntityLivingBase)
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		if (block.getBlockHardness(world, x, y, z) != 0.0D)
		{
			stack.damageItem(2, living);
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.item.Item#getItemUseAction(net.minecraft.item.ItemStack)
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.block;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.item.Item#getMaxItemUseDuration(net.minecraft.item.ItemStack
	 * )
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemModTool#onItemRightClick(net.minecraft
	 * .item.ItemStack, net.minecraft.world.World,
	 * net.minecraft.entity.player.EntityPlayer)
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.item.Item#canHarvestBlock(net.minecraft.block.Block,
	 * net.minecraft.item.ItemStack)
	 */
	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemstack)
	{
		return block == Blocks.web;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemModTool#getItemEnchantability()
	 */
	@Override
	public int getItemEnchantability()
	{
		return this.toolMaterial.getEnchantability();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.equipment.ItemModTool#getIsRepairable(net.minecraft
	 * .item.ItemStack, net.minecraft.item.ItemStack)
	 */
	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2)
	{
		Item item = stack2.getItem();
		return this.toolMaterial.func_150995_f() == item ? true : super.getIsRepairable(stack1, stack2);
	}
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (this.isSteampowered())
		{
			NBTTagCompound tag = stack.getTagCompound();
			if(tag.getBoolean("hasCanister"))
			{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), toolMaterial.getDamageVsEntity());
			return true;
			}
			else return false;
		}
		else
		{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), toolMaterial.getDamageVsEntity());
			return true;
		}
	}
}