/*
 *
 */
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
import net.minecraft.item.Item.ToolMaterial;
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

// TODO: Auto-generated Javadoc
/**
 * The Class ItemModTool.
 */
public class ItemModTool extends BaseItem
{

	/** The Constant steamForRepair. */
	public static final int steamForRepair = 20;

	/** The blocks effective against. */
	protected static Block[] blocksEffectiveAgainst;

	/** The efficiency on proper material. */
	public float efficiencyOnProperMaterial = 4.0F;

	/** The damage vs entity. */
	public static float damageVsEntity;

	/** The tool material. */
	protected ToolMaterial toolMaterial;

	//private boolean canister = false;

	/**
	 * Instantiates a new item mod tool.
	 *
	 * @param damage
	 *            the damage
	 * @param toolMat
	 *            the tool mat
	 * @param blockArray
	 *            the block array
	 */
	@SuppressWarnings("all")
	protected ItemModTool(float damage, ToolMaterial toolMat, Block[] blockArray)
	{
		super();
		setCreativeTab(Steamcraft.tabSC2);
		this.toolMaterial = toolMat;
		blocksEffectiveAgainst = blockArray;
		this.maxStackSize = 1;
		this.setMaxDamage(toolMat.getMaxUses());
		this.efficiencyOnProperMaterial = toolMat.getEfficiencyOnProperMaterial();
		if(this instanceof ItemModSword)
		this.damageVsEntity = toolMat.getDamageVsEntity();
		else
		this.damageVsEntity = 2;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.BaseItem#registerIcons(net.minecraft.client.renderer
	 * .texture.IIconRegister)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
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
			for (int i = 0; i < blocksEffectiveAgainst.length; i++)
			{
				if (blocksEffectiveAgainst[i] == block)
				{
					return efficiencyOnProperMaterial;
				}
			}
		return 0.1F;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.item.Item#hitEntity(net.minecraft.item.ItemStack,
	 * net.minecraft.entity.EntityLivingBase,
	 * net.minecraft.entity.EntityLivingBase)
	 */
	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase living1, EntityLivingBase living2)
	{
		 if(isSteampowered() && living2 instanceof EntityPlayer)
		 {
			 if(hasCanister((EntityPlayer) living2))
			 consumeSteamFromCanister((EntityPlayer) living2);
		 }
		if(this instanceof ItemModSword)
		{
			itemstack.damageItem(1, living2);
		}
		else
		itemstack.damageItem(2, living2);
		return true;
	}

	protected boolean isSteampowered()
	{
		if(toolMaterial == MaterialHelper.TOOL_STEAM)
		{
			return true;
		}
		else if(toolMaterial == MaterialHelper.DRILL_EMERALD || toolMaterial == MaterialHelper.DRILL_ETHERIUM || toolMaterial == MaterialHelper.DRILL_GOLD || toolMaterial == MaterialHelper.DRILL_IRON || toolMaterial == MaterialHelper.DRILL_OBSIDIAN || toolMaterial == MaterialHelper.DRILL_STEAM || toolMaterial == MaterialHelper.DRILL_STEAM || toolMaterial == MaterialHelper.DRILL_WOOD)
			return true;
		else
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.item.Item#getItemEnchantability()
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
	 * net.minecraft.item.Item#getIsRepairable(net.minecraft.item.ItemStack,
	 * net.minecraft.item.ItemStack)
	 */
	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2)
	{
		Item item = stack2.getItem();
		return this.toolMaterial.func_150995_f() == item ? true : super.getIsRepairable(stack1, stack2);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.item.Item#getItemAttributeModifiers()
	 */
	@SuppressWarnings("all")
	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", this.damageVsEntity, 0));
		return multimap;
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
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase living)
	{
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		if (stack.getTagCompound().getBoolean("hasCanister"))
		{
			if(isSteampowered() && living instanceof EntityPlayer)
			{
				if(hasCanister((EntityPlayer) living))
				consumeSteamFromCanister((EntityPlayer) living);
		 	}
		}
		stack.damageItem(1, living);
		return true;
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
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (toolMaterial == MaterialHelper.TOOL_STEAM)
		{
			if (!itemStack.hasTagCompound())
				itemStack.setTagCompound(new NBTTagCompound());

			if(itemStack.getTagCompound().hasKey("hasCanister"))
			list.add("Canister Detected: " + String.valueOf(itemStack.getTagCompound().getBoolean("hasCanister")));

		}
	}

	/**
	 * Can consume steam from canister.
	 *
	 * @param player the player
	 */
	protected void consumeSteamFromCanister(EntityPlayer player)
	{
		ItemStack[] mainInv = player.inventory.mainInventory;
		for(int i=0; i<mainInv.length;i++)
		{
			if (mainInv[i] != null && mainInv[i].getItem() == ConfigItems.itemCanisterSteam)
			{
				ItemCanister canister = (ItemCanister) mainInv[i].getItem();

				if(canister.getFluidAmount(mainInv[i]) > steamForRepair)
				{
					canister.drain(mainInv[i], steamForRepair, true);
				}
			}
		}
	}
	/**
	 * Can consume steam from canister.
	 *
	 * @param player the player
	 */
	protected boolean isCanisterEmpty(ItemStack stack)
	{
		ItemCanister canister = (ItemCanister) stack.getItem();

		if(canister.getFluidAmount(stack) <= steamForRepair)
		{
			return true;
		}
		else return false;
	}
	
	protected boolean hasCanister(EntityPlayer player)
	{
		for(int i=0; i!=player.inventory.mainInventory.length; i++)
		{
			ItemStack[] mainInv = player.inventory.mainInventory;
			if ((mainInv[i] != null) && (mainInv[i].getItem() == ConfigItems.itemCanisterSteam))
			{
				return !isCanisterEmpty(mainInv[i]);
			}
		}
		return false;
	}
	
	public void onUpdate(ItemStack itemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if (!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());
		NBTTagCompound tag = itemStack.getTagCompound();
		if(par3Entity instanceof EntityPlayer)
		{
		if (hasCanister((EntityPlayer) par3Entity))
			tag.setBoolean("hasCanister", true);
		else
			tag.setBoolean("hasCanister", false);
		itemStack.setTagCompound(tag);
		}
		//getItemAttributeModifiers().notify();
		//getItemAttributeModifiers().put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", getDamage(toolMaterial, itemStack), 0));
		/*if(tag.getBoolean("hasCanister"))
		{
			efficiencyOnProperMaterial = 4.0F;
		}
		else efficiencyOnProperMaterial = 0F;*/
	}
	
	@Override
    public Multimap<String,AttributeModifier> getAttributeModifiers(ItemStack stack)
    {
    	super.getAttributeModifiers(stack).put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier",0, 0));
        return super.getAttributeModifiers(stack);
    }
}
