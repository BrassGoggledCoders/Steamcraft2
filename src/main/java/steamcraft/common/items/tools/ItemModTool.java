/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.items.tools;

import java.util.List;

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
import net.minecraft.world.World;
import steamcraft.common.InitItems;
import steamcraft.common.Steamcraft;
import steamcraft.common.items.BaseItem;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.MaterialHelper;
import boilerplate.common.utils.ItemStackUtils;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 * 
 */
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
		setCreativeTab(Steamcraft.tabSC2);
		toolMaterial = toolMat;
		blocksEffectiveAgainst = blockArray;
		maxStackSize = 1;
		efficiencyOnProperMaterial = toolMat.getEfficiencyOnProperMaterial();
		damageVsEntity = damage;
		setFull3D();
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		if (isSteampowered())
		{
			ItemStack stack = new ItemStack(this, 1, 0);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("hasCanister", true);
			stack.setTagCompound(tag);

			l.add(stack);
		}
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		if (isSteampowered())
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("hasCanister", true);

			stack.setTagCompound(tag);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if (isSteampowered() && !stack.getTagCompound().getBoolean("hasCanister"))
			return 0.1F;

		for (Block element : blocksEffectiveAgainst)
			if (element == block)
				return efficiencyOnProperMaterial;

		return 1.0F;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase living1, EntityLivingBase living2)
	{
		if (isSteampowered() && (living2 instanceof EntityPlayer))
		{
			if (hasCanister((EntityPlayer) living2))
			{
				consumeSteamFromCanister((EntityPlayer) living2);

				if (itemstack.getItem() != InitItems.swordSteam)
					consumeSteamFromCanister((EntityPlayer) living2);
			}
		}
		else if (this instanceof ItemModSword)
			itemstack.damageItem(1, living2);
		else
			itemstack.damageItem(2, living2);

		return true;
	}

	protected boolean isSteampowered()
	{
		if ((toolMaterial == MaterialHelper.TOOL_STEAM) || (toolMaterial == MaterialHelper.DRILL_STEAM))
			return true;
		else
			return false;
	}

	@Override
	public int getItemEnchantability()
	{
		return toolMaterial.getEnchantability();
	}

	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2)
	{
		Item item = stack2.getItem();
		return toolMaterial.func_150995_f() == item ? true : super.getIsRepairable(stack1, stack2);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_,
			EntityLivingBase living)
	{
		if (isSteampowered())
			if (stack.getTagCompound().getBoolean("hasCanister") && (living instanceof EntityPlayer))
				if (hasCanister((EntityPlayer) living))
					consumeSteamFromCanister((EntityPlayer) living);
		stack.damageItem(1, living);
		return true;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (isSteampowered())
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
			if ((element != null) && (element.getItem() == InitItems.itemCanisterSteam))
			{
				ItemCanister canister = (ItemCanister) element.getItem();

				if (canister.getFluidAmount(element) > steamForRepair)
				{
					canister.drain(element, steamForRepair, true);

					return;
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
		boolean hasCanister = false;
		for (int i = 0; i != player.inventory.mainInventory.length; i++)
		{
			ItemStack[] mainInv = player.inventory.mainInventory;
			if ((mainInv[i] != null) && (mainInv[i].getItem() == InitItems.itemCanisterSteam))
				hasCanister = hasCanister || !isCanisterEmpty(mainInv[i]);
		}
		return hasCanister;
	}

	@Override
	public void onUpdate(ItemStack itemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if (isSteampowered())
		{
			NBTTagCompound tag = itemStack.getTagCompound();

			if (par3Entity instanceof EntityPlayer)
			{
				boolean hasCanister = false;

				if (hasCanister((EntityPlayer) par3Entity))
					hasCanister = true;

				if (hasCanister != tag.getBoolean("hasCanister"))
				{
					tag.setBoolean("hasCanister", hasCanister);
					itemStack.setTagCompound(tag);

					if (hasCanister)
						changeToolDamage(itemStack, damageVsEntity);
					else
						changeToolDamage(itemStack, 1.0D);
				}
			}
		}
	}

	private void changeToolDamage(ItemStack itemStack, double damage)
	{
		ItemStackUtils.addModifier(itemStack, SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), damage, 0);
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
