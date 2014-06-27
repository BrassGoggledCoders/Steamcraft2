/*
 *
 */
package steamcraft.common.items.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
		super(toolMat.getDamageVsEntity() + 4.0F, toolMat, blocksEffectiveAgainst);
		this.toolMaterial = toolMat;
		this.maxStackSize = 1;
		this.setMaxDamage(toolMat.getMaxUses());
	}

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
			return (material != Material.plants) && (material != Material.vine) && (material != Material.coral) && (material != Material.leaves) ? 1.0F
					: 1.5F;
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		if (block.getBlockHardness(world, x, y, z) != 0.0D)
		{
			stack.damageItem(2, living);
		}

		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemstack)
	{
		return block == Blocks.web;
	}

	@Override
	public int getItemEnchantability()
	{
		return this.toolMaterial.getEnchantability();
	}

	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2)
	{
		Item item = stack2.getItem();
		return this.toolMaterial.func_150995_f() == item ? true : super.getIsRepairable(stack1, stack2);
	}
	
	/*
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (this.isSteampowered())
		{
			NBTTagCompound tag = stack.getTagCompound();
			if (tag.getBoolean("hasCanister"))
			{
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), this.toolMaterial.getDamageVsEntity());
				return true;
			}
			else
				return false;
		}
		else
		{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), this.toolMaterial.getDamageVsEntity());
			return true;
		}
	}
	*/
}