package steamcraft.common.items.equipment;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.items.BaseItem;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.MaterialHelper;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModTool extends BaseItem
{
	public static final int steamForRepair = 20;
	protected static Block[] blocksEffectiveAgainst;
	public float efficiencyOnProperMaterial = 4.0F;
	public float damageVsEntity;
	protected ToolMaterial toolMaterial;

	protected ItemModTool(float damage, ToolMaterial toolMat, Block[] blockArray)
	{
		super();
		this.setCreativeTab(Steamcraft.tabSC2);
		this.toolMaterial = toolMat;
		blocksEffectiveAgainst = blockArray;
		this.maxStackSize = 1;
		this.efficiencyOnProperMaterial = toolMat.getEfficiencyOnProperMaterial();
		this.damageVsEntity = damage;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		for (Block element : blocksEffectiveAgainst)
		{
			if (element == block)
			{
				return this.efficiencyOnProperMaterial;
			}
		}
		return 0.1F;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase living1, EntityLivingBase living2)
	{
		if (this.isSteampowered() && (living2 instanceof EntityPlayer))
			if (this.hasCanister((EntityPlayer) living2))
				this.consumeSteamFromCanister((EntityPlayer) living2);
		
		if (this instanceof ItemModSword)
			itemstack.damageItem(1, living2);
		else
			itemstack.damageItem(2, living2);
		
		return true;
	}

	protected boolean isSteampowered()
	{
		if (this.toolMaterial == MaterialHelper.TOOL_STEAM)
			return true;
		else if ((this.toolMaterial == MaterialHelper.DRILL_EMERALD) || (this.toolMaterial == MaterialHelper.DRILL_ETHERIUM)
				|| (this.toolMaterial == MaterialHelper.DRILL_GOLD) || (this.toolMaterial == MaterialHelper.DRILL_IRON)
				|| (this.toolMaterial == MaterialHelper.DRILL_OBSIDIAN) || (this.toolMaterial == MaterialHelper.DRILL_STEAM)
				|| (this.toolMaterial == MaterialHelper.DRILL_STEAM) || (this.toolMaterial == MaterialHelper.DRILL_WOOD))
			return true;
		else
			return false;
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
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier",
				damageVsEntity, 0));
		return multimap;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_,
			EntityLivingBase living)
	{
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		if (stack.getTagCompound().getBoolean("hasCanister"))
		{
			if (this.isSteampowered() && (living instanceof EntityPlayer))
			{
				if (this.hasCanister((EntityPlayer) living))
					this.consumeSteamFromCanister((EntityPlayer) living);
			}
		}
		stack.damageItem(1, living);
		return true;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (this.toolMaterial == MaterialHelper.TOOL_STEAM)
		{
			if (!itemStack.hasTagCompound())
				itemStack.setTagCompound(new NBTTagCompound());

			list.add("Canister Detected: " + String.valueOf(itemStack.getTagCompound().getBoolean("hasCanister")));
		}
	}

	protected void consumeSteamFromCanister(EntityPlayer player)
	{
		ItemStack[] mainInv = player.inventory.mainInventory;
		
		for (ItemStack element : mainInv)
		{
			if ((element != null) && (element.getItem() == ConfigItems.itemCanisterSteam))
			{
				ItemCanister canister = (ItemCanister) element.getItem();

				if (canister.getFluidAmount(element) > steamForRepair)
				{
					canister.drain(element, steamForRepair, true);
					
					return ;
				}
			}
		}
	}

	protected boolean isCanisterEmpty(ItemStack stack)
	{
		ItemCanister canister = (ItemCanister) stack.getItem();

		if (canister.getFluidAmount(stack) <= steamForRepair)
			return true;
		else
			return false;
	}

	protected boolean hasCanister(EntityPlayer player)
	{
		for (int i = 0; i != player.inventory.mainInventory.length; i++)
		{
			ItemStack[] mainInv = player.inventory.mainInventory;
			if ((mainInv[i] != null) && (mainInv[i].getItem() == ConfigItems.itemCanisterSteam))
			{
				return !this.isCanisterEmpty(mainInv[i]);
			}
		}
		return false;
	}

	@Override
	public void onUpdate(ItemStack itemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if (!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());
		NBTTagCompound tag = itemStack.getTagCompound();
		if (par3Entity instanceof EntityPlayer)
		{
			if (this.hasCanister((EntityPlayer) par3Entity))
				tag.setBoolean("hasCanister", true);
			else
				tag.setBoolean("hasCanister", false);
			itemStack.setTagCompound(tag);
		}
		
		//getAttributeModifiers(itemStack).put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), 
				//new AttributeModifier(field_111210_e, "Tool modifier", 10, 0));
	}

	/*
	 * Seems to not be used anyway...
	@SuppressWarnings("all")
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack)
	{
		super.getAttributeModifiers(stack).put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), 
			new AttributeModifier(field_111210_e, "Tool modifier", damageVsEntity, 0));
		return super.getAttributeModifiers(stack);
	}
	*/
}
