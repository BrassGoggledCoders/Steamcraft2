package common.steamcraft.common.item;

import com.google.common.collect.Multimap;

import common.steamcraft.client.core.helper.ClientHelper;
import common.steamcraft.common.lib2.MaterialMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemModTool extends ItemMod
{
	protected static Block[] blocksEffectiveAgainst;
	public float efficiencyOnProperMaterial = 4.0F;
	public float damageVsEntity;
	protected EnumToolMaterial toolMaterial;
	
	@SuppressWarnings("all")
	protected ItemModTool(int id, float damage, EnumToolMaterial toolMat, Block[] blockArray)
	{
		super(id);
		this.toolMaterial = toolMat;
		this.blocksEffectiveAgainst = blockArray;
		this.maxStackSize = 1;
		this.setMaxDamage(toolMat.getMaxUses());
		this.efficiencyOnProperMaterial = toolMat.getEfficiencyOnProperMaterial();

		/*
		 * This makes the damage of steam tools negative
		 * 
		if(this.toolMaterial == SC2_Material.STEAM_TOOL)
		{
			damageVsEntity = damage - (int) Math.round(this.getMaxDamage() * 5 / 320);
		}
		 */

		this.damageVsEntity = damage + toolMat.getDamageVsEntity();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block)
	{
		for(int i = 0; i < blocksEffectiveAgainst.length; i++)
		{
			if(blocksEffectiveAgainst[i] == block)
			{
				if(this.toolMaterial == MaterialMod.STEAM_TOOL)
				{
					return (efficiencyOnProperMaterial - (((float) stack.getItemDamage()) * 11 / 320));
				}

				return efficiencyOnProperMaterial;
			}
		}

		return 1.0F;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase living1, EntityLivingBase living2)
	{
		itemstack.damageItem(2, living2);
		return true;
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
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", (double)this.damageVsEntity, 0));
		return multimap;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, int i, int j, int k, int l, EntityLivingBase living)
	{
		if(toolMaterial == MaterialMod.STEAM_TOOL)
		{
			System.out.println(efficiencyOnProperMaterial - (((float) stack.getItemDamage()) * 11 / 320));
			//int itemDamage = stack.getItemDamage();
		}

		stack.damageItem(1, living);
		return true;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) 
	{
		if(toolMaterial==MaterialMod.INSTANCE.STEAM_TOOL)
		{
			if(!ClientHelper.isShiftKeyDown())
			{
				list.add(ClientHelper.shiftForInfo);
				return;
			}

			list.add("\u00A77"+ (this.getMaxDamage() - itemStack.getItemDamage()) + "/" + this.getMaxDamage() + " steam");
		}
	}

	@SuppressWarnings("all")
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			if(toolMaterial==MaterialMod.INSTANCE.STEAM_TOOL)
			{
				if(player.inventory.hasItem(ModItems.canisterSteam.itemID))
				{
					int i = 0;

					while(stack.getItemDamage() != 0 && i < 36)
					{
						if(player.inventory.mainInventory[i]!=null &&
								player.inventory.mainInventory[i].itemID == ModItems.canisterSteam.itemID)
						{
							while(player.inventory.mainInventory[i].getItemDamage() < ModItems.canisterSteam.getMaxDamage() && stack.getItemDamage() > 0)
							{
								player.inventory.mainInventory[i].damageItem(1, player);
								stack.setItemDamage(stack.getItemDamage() - 1);
							}
						}
						
						i++;
					}
				}
			}
		}

		return stack;
	}
}
