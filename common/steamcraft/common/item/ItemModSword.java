package common.steamcraft.common.item;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemModSword extends ItemModTool
{
	protected EnumToolMaterial toolMaterial;
	private float weaponDamage;

	public ItemModSword(int id, EnumToolMaterial toolMat)
	{
		//Fixed the damage on swords
		super(id, 5.0F, toolMat, blocksEffectiveAgainst);
		this.toolMaterial = toolMat;
		this.maxStackSize = 1;
		this.setMaxDamage(toolMat.getMaxUses());
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block)
	{
		if(block.blockID == Block.web.blockID)
		{
			return 15.0F;
		} else
		{
			Material material = block.blockMaterial;
			return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.pumpkin ? 1.0F : 1.5F;
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase living1, EntityLivingBase living2)
	{	
		int itemDamage = stack.getItemDamage();
		System.out.println(weaponDamage - (int) Math.round(itemDamage * 10 / 320));
		stack.damageItem(1, living2);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, int i, int j, int k, int l, EntityLivingBase living)
	{
		if((double)Block.blocksList[l].getBlockHardness(world, i, j, k) != 0.0D)
		{
			stack.damageItem(2, living);
		}

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
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
	public boolean canHarvestBlock(Block block)
	{
		return block.blockID == Block.web.blockID;
	}

	@Override
	public int getItemEnchantability()
	{
		return this.toolMaterial.getEnchantability();
	}

	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2)
	{
		return this.toolMaterial.getToolCraftingMaterial() == stack2.itemID ? true : super.getIsRepairable(stack1, stack2);
	}

	@SuppressWarnings("all")
	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.weaponDamage, 0));
		return multimap;
	}
}