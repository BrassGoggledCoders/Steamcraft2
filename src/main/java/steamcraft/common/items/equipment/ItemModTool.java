/*
 * 
 */
package steamcraft.common.items.equipment;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
	public static final int steamForRepair = 10; // how much it costs in steam
													// to repair the tool with
													// the below durabillity
	/** The Constant repairAmount. */
	public static final int repairAmount = 15;

	/** The blocks effective against. */
	protected static Block[] blocksEffectiveAgainst;

	/** The efficiency on proper material. */
	public float efficiencyOnProperMaterial = 4.0F;

	/** The damage vs entity. */
	public float damageVsEntity;

	/** The tool material. */
	protected ToolMaterial toolMaterial;

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
		ItemModTool.blocksEffectiveAgainst = blockArray;
		this.maxStackSize = 1;
		this.setMaxDamage(toolMat.getMaxUses());
		this.efficiencyOnProperMaterial = toolMat.getEfficiencyOnProperMaterial();
		this.damageVsEntity = damage + toolMat.getDamageVsEntity();
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
	 * @see net.minecraft.item.Item#isFull3D()
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
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
				if (this.toolMaterial == MaterialHelper.TOOL_STEAM)
				{
					return (efficiencyOnProperMaterial - (((float) stack.getItemDamage()) * 11 / 320));
				}

				return efficiencyOnProperMaterial;
			}
		}

		return 1.0F;
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
		itemstack.damageItem(2, living2);
		return true;
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
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier",
				this.damageVsEntity, 0));
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
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_,
			EntityLivingBase living)
	{
		/*
		 * if(toolMaterial == MaterialHelper.TOOL_STEAM) {
		 * System.out.println(efficiencyOnProperMaterial - (((float)
		 * stack.getItemDamage()) * 11 / 320)); //int itemDamage =
		 * stack.getItemDamage(); }
		 */

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
			// if(!ClientHelper.isShiftKeyDown())
			// {
			// list.add(ClientHelper.shiftForInfo);
			return;
			// }

			// list.add("\u00A77"+ (this.getMaxDamage() -
			// itemStack.getItemDamage()) + "/" + this.getMaxDamage() +
			// " steam");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.item.Item#onItemRightClick(net.minecraft.item.ItemStack,
	 * net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer)
	 */
	@SuppressWarnings("all")
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			if (toolMaterial == MaterialHelper.TOOL_STEAM)
			{
				int i = 0;

				while (stack.getItemDamage() - repairAmount >= 0 && canConsumeSteamFromCanister(player))
				{
					stack.setItemDamage(stack.getItemDamage() - repairAmount);
					i++;
				}
			}
		}

		return stack;
	}

	/**
	 * Can consume steam from canister.
	 * 
	 * @param player
	 *            the player
	 * @return true, if successful
	 */
	protected boolean canConsumeSteamFromCanister(EntityPlayer player)
	{
		int i = 0;
		while (i < 36)
		{
			ItemStack[] mainInv = player.inventory.mainInventory;
			if ((mainInv[i] != null) && (mainInv[i].getItem() == ConfigItems.itemCanisterSteam))
			{
				ItemCanister canister = (ItemCanister) mainInv[i].getItem();

				if (!(getDamage(new ItemStack(canister)) == 0))
				{
					canister.drain(new ItemStack(canister), steamForRepair, true);

					return true;
				}
				else
				{
					mainInv[i] = new ItemStack(ConfigItems.itemCanisterSteam);

					return false;
				}
			}

			i++;
		}

		return false;
	}
}
