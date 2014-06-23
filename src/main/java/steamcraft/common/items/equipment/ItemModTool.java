package steamcraft.common.items.equipment;

import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		if(isSteampowered())
		{
			ItemStack stack = new ItemStack(this, 1, 0);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("hasCanister", true); //To make sure damage is reset when the tool is spawned from creative
			
			stack.setTagCompound(tag);
			
			l.add(stack);
		}
	}
	
	@Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("hasCanister", true); //To make sure damage is reset when the tool is crafted
		
		stack.setTagCompound(tag);
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
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_,
			EntityLivingBase living)
	{
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		if (this.isSteampowered())
		{
			if (stack.getTagCompound().getBoolean("hasCanister") && (living instanceof EntityPlayer))
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
			boolean hasCanister = false;
			
			if (this.hasCanister((EntityPlayer) par3Entity))
				hasCanister = true;
			
			if(hasCanister!=tag.getBoolean("hasCanister")) //For performance reason.Not sure if it's a good idea to change NBT data each tick...
			{				
				tag.setBoolean("hasCanister", hasCanister);
				itemStack.setTagCompound(tag);
				
				if(hasCanister)
					changeToolDamage(itemStack, damageVsEntity);
				else
					changeToolDamage(itemStack, 1.0F);
			}
		}
	}

	private void changeToolDamage(ItemStack itemStack, float damage)
	{
		addModifier(itemStack, SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), damage, 0);
	}
	
	//Got this from the forums.Maybe move it to a more appropriate class or in BoilerPlate?
	public static void addModifier(ItemStack itemStack, String attribute, double amount, int mode)
	{

		  NBTTagList list = new NBTTagList();
	  	  NBTTagCompound attributes = new NBTTagCompound();
	  	  attributes.setString("Name", "Attribute");
	  	  attributes.setString("AttributeName", attribute);
	  	  attributes.setDouble("Amount", amount);
	  	  attributes.setLong("UUIDMost", UUID.randomUUID().getMostSignificantBits());
	  	  attributes.setLong("UUIDLeast", UUID.randomUUID().getLeastSignificantBits());
	  	  attributes.setInteger("Operation", mode);
	  	  
	  	  list.appendTag(attributes);
	  	  
	  	  NBTTagCompound attributeModifierTag = itemStack.getTagCompound();
	  	  attributeModifierTag.setTag("AttributeModifiers", list);
	  	  
	  	  itemStack.setTagCompound(attributeModifierTag);
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
}
