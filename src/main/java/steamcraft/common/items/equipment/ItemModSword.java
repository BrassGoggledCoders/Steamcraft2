package steamcraft.common.items.equipment;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

public class ItemModSword extends ItemModTool
{
	protected ToolMaterial toolMaterial;
	private float weaponDamage;

	public ItemModSword(ToolMaterial toolMat)
	{
		//Fixed the damage on swords
		super(5.0F, toolMat, blocksEffectiveAgainst);
		this.toolMaterial = toolMat;
		this.maxStackSize = 1;
		this.setMaxDamage(toolMat.getMaxUses());
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if(block == Blocks.web)
		{
			return 15.0F;
		} else
		{
			Material material = block.getMaterial();
			return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves ? 1.0F : 1.5F;
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase living1, EntityLivingBase living2)
	{
		int itemDamage = stack.getItemDamage();
		System.out.println(weaponDamage - Math.round(itemDamage * 10 / 320));
		stack.damageItem(1, living2);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		if(block.getBlockHardness(world, x, y, z) != 0.0D)
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
	public boolean canHarvestBlock(Block block,ItemStack itemstack)
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

	@SuppressWarnings("all")
	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.weaponDamage, 0));
		return multimap;
	}
}