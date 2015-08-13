package steamcraft.common.items.tools.steam;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.tools.BaseTool;
import steamcraft.common.init.InitItems;
import steamcraft.common.init.InitMaterials;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.lib.ModInfo;

public class ItemSteamTool extends BaseTool
{
	public ItemSteamTool(float damage)
	{
		super(damage, InitMaterials.TOOL_STEAM, ModInfo.PREFIX);
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		ItemStack stack = new ItemStack(this, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("hasCanister", true);
		stack.setTagCompound(tag);

		l.add(stack);
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("hasCanister", false);

		stack.setTagCompound(tag);
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		if (!stack.getTagCompound().getBoolean("hasCanister"))
			return 1.0F;
		else
			return super.getDigSpeed(stack, block, meta);
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase living1, EntityLivingBase living2)
	{
		if (living2 instanceof EntityPlayer)
		{
			if (this.hasCanister((EntityPlayer) living2))
			{
				this.consumeSteamFromCanister((EntityPlayer) living2);

				if (itemstack.getItem() != InitItems.swordSteam)
					this.consumeSteamFromCanister((EntityPlayer) living2);
			}
		}

		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_,
			EntityLivingBase living)
	{
		if (stack.getTagCompound().getBoolean("hasCanister") && (living instanceof EntityPlayer))
			if (this.hasCanister((EntityPlayer) living))
				this.consumeSteamFromCanister((EntityPlayer) living);
		return true;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());

		list.add("Canister Detected: " + String.valueOf(itemStack.getTagCompound().getBoolean("hasCanister")));
		super.addInformation(itemStack, entityPlayer, list, bool);
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

		return canister.getFluidAmount(stack) <= steamForRepair;
	}

	protected boolean hasCanister(EntityPlayer player)
	{
		boolean hasCanister = false;
		for (int i = 0; i != player.inventory.mainInventory.length; i++)
		{
			ItemStack[] mainInv = player.inventory.mainInventory;
			if ((mainInv[i] != null) && (mainInv[i].getItem() == InitItems.itemCanisterSteam))
				hasCanister = hasCanister || !this.isCanisterEmpty(mainInv[i]);
		}
		return hasCanister;
	}

	@Override
	public void onUpdate(ItemStack itemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		NBTTagCompound tag = itemStack.getTagCompound();

		if (par3Entity instanceof EntityPlayer)
		{
			boolean hasCanister = false;

			if (this.hasCanister((EntityPlayer) par3Entity))
				hasCanister = true;

			if (hasCanister != tag.getBoolean("hasCanister"))
			{
				tag.setBoolean("hasCanister", hasCanister);
				itemStack.setTagCompound(tag);

				if (hasCanister)
					this.changeToolDamage(itemStack, this.damageVsEntity);
				else
					this.changeToolDamage(itemStack, 1.0D);
			}
		}
	}
}
