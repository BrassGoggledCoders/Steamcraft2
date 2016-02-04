
package steamcraft.common.items.armor;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.items.BaseArmor;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitItems;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.lib.ModInfo;

/**
 * @author Decebaldecebal
 *
 */
public class ItemSteamJetpack extends BaseArmor
{
	private final byte steamPerTick;

	public ItemSteamJetpack(ArmorMaterial mat, int renderIndex, int armorType, byte steam)
	{
		super(mat, armorType, "", ModInfo.PREFIX);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.setMaxDamage(0);
		this.steamPerTick = steam;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());

		list.add("Canister Detected: " + String.valueOf(itemStack.getTagCompound().getBoolean("hasCanister")));
		if (ClientHelper.isShiftKeyDown())
			this.getWrappedDesc(list);
		else
			list.add(ClientHelper.shiftForInfo);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		boolean hasCanister = this.hasCanister(player);

		if (!player.capabilities.allowFlying && hasCanister)
		{
			if (!itemStack.hasTagCompound())
				itemStack.setTagCompound(new NBTTagCompound());

			NBTTagCompound tag = itemStack.getTagCompound();

			if (hasCanister != tag.getBoolean("hasCanister"))
			{
				tag.setBoolean("hasCanister", hasCanister);
			}

			if (((player.posY < 200) && Steamcraft.proxy.isKeyPressed(0)))
			{
				this.consumeSteamFromCanister(player, this.steamPerTick);

				if (player.motionY > 0.0D)
					player.motionY += 0.08499999910593033D;
				else
					player.motionY += 0.11699999910593033D;

				world.spawnParticle("smoke", player.posX, player.posY - 0.25D, player.posZ, 0.0D, 0.0D, 0.0D);

			}

			if ((this == InitItems.itemSteamWingpack) && (player.motionY < 0.0D) && player.isSneaking() && !player.onGround)
			{
				this.consumeSteamFromCanister(player, (byte) (this.steamPerTick / 2));
				player.motionY /= 1.4D;

				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
			}

			if (!player.onGround)
			{
				player.motionX *= 1.04D;
				player.motionZ *= 1.04D;
			}

			if ((player.fallDistance > 0.0F) && !player.onGround)
			{
				this.consumeSteamFromCanister(player, (byte) (this.steamPerTick / 4));
				player.fallDistance = 0.0F;
			}
		}
	}

	protected void consumeSteamFromCanister(EntityPlayer player, byte steam)
	{
		ItemStack[] mainInv = player.inventory.mainInventory;

		for (ItemStack element : mainInv)
			if ((element != null) && (element.getItem() instanceof ItemCanister))
			{
				ItemCanister canister = (ItemCanister) element.getItem();

				if (canister.getFluidAmount(element) > steam)
				{
					canister.drain(element, steam, true);

					return;
				}
			}
	}

	protected boolean isCanisterEmpty(ItemStack stack)
	{
		ItemCanister canister = (ItemCanister) stack.getItem();

		return canister.getFluidAmount(stack) <= this.steamPerTick;
	}

	protected boolean hasCanister(EntityPlayer player)
	{
		boolean hasCanister = false;
		ItemStack[] mainInv = player.inventory.mainInventory;
		for (int i = 0; i != mainInv.length; i++)
		{
			if ((mainInv[i] != null) && (mainInv[i].getItem() instanceof ItemCanister))
				hasCanister = hasCanister || !this.isCanisterEmpty(mainInv[i]);
		}
		return hasCanister;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		ModelBiped armorModel = new ModelBiped();

		if (itemStack != null)
		{
			if (this == InitItems.itemSteamJetpack)
				armorModel = Steamcraft.proxy.getJetpackArmorModel(1);
			else
				armorModel = Steamcraft.proxy.getWingpackArmorModel(1);

			if (armorModel != null)
			{
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = (armorSlot == 1) || (armorSlot == 2);
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = (armorSlot == 2) || (armorSlot == 3);
				armorModel.bipedLeftLeg.showModel = (armorSlot == 2) || (armorSlot == 3);
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();

				// armorModel.heldItemRight =
				// entityLiving.getCurrentItemOrArmor(0) != null ? 1 :0;

				if (entityLiving instanceof EntityPlayer)
					armorModel.aimedBow = ((EntityPlayer) entityLiving).getItemInUseDuration() > 2;

				return armorModel;
			}
		}

		return null;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return ModInfo.PREFIX + "textures/models/armor/wings.png";
	}
}
