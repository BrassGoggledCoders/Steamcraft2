/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Jan 30, 2014, 6:11:43 PM]
 */
package common.steamcraft.common.item;

import common.steamcraft.common.SC2;
import common.steamcraft.common.lib2.CreativeTabsMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

/**
 * 
 * @author MrArcane111 & general3214
 *
 */
public class ItemSteamWings extends ItemArmorMod
{
    public ItemStack canister;
	public ItemSteamWings(int id, EnumArmorMaterial mat, int renderIndex, int armorType, String texture)
	{
		super(id, mat, renderIndex, armorType, texture);
		mat = material;
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabsMod.tabSCItems);
		this.setUnlocalizedName("steamWings");
	}

	@Override
	@SideOnly(Side.CLIENT) // Thank-you Forge for this method. Otherwise, I would have to use TickHandlers...
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack stack)
	{
        int i = 0;
        while(i < 36)
        {
            ItemStack[] mainInv = player.inventory.mainInventory;
            if(mainInv[i] != null && mainInv[i].itemID == ModItems.steamCanister.itemID)
            {
                canister = mainInv[i];
                if(!player.capabilities.allowFlying)
                {
                    if(Minecraft.getMinecraft().currentScreen == null && Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.keyCode))
                    {
                        if(player.motionY > 0.0D)
                        {
                            player.motionY += 0.08499999910593033D;
                        } else
                        {
                            player.motionY += 0.11699999910593033D;
                        }

                        if(canister.getItemDamage() < 499) canister.damageItem(1, player);
                        else if(canister.getItemDamage() >= 499)
                        {
                            player.inventory.consumeInventoryItem(canister.itemID);
                            mainInv[i] = new ItemStack(ModItems.emptyCanister);
                        }
                        world.spawnParticle("smoke", player.posX, player.posY - 0.25D, player.posZ, 0.0D, 0.0D, 0.0D);
                        world.spawnParticle("smoke", player.posX, player.posY - 0.25D, player.posZ, 0.0D, 0.0D, 0.0D);
                        world.spawnParticle("smoke", player.posX, player.posY - 0.25D, player.posZ, 0.0D, 0.0D, 0.0D);
                        world.spawnParticle("smoke", player.posX, player.posY - 0.25D, player.posZ, 0.0D, 0.0D, 0.0D);
                        break;
                    }
                    if(player.motionY < 0.0D && !player.isSneaking())
                    {
                        player.motionY /= 1.149999976158142D;
                    }
                    if(!player.onGround)
                    {
                        player.motionX *= 1.0399999618530273D;
                        player.motionZ *= 1.0399999618530273D;
                    }
                }
            }
            i++;
        }
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) 
	{
		ModelBiped armorModel = new ModelBiped();

		if(itemStack != null)
		{
			if(itemStack.getItem() instanceof ItemSteamWings)
			{
				int type = ((ItemArmor)itemStack.getItem()).armorType;

				if(type == 1 || type == 3)
				{
					armorModel = SC2.proxy.getWingsArmorModel(0);
				} else
				{
					armorModel = SC2.proxy.getWingsArmorModel(1);
				}
			}
			if(armorModel != null)
			{
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = entityLiving.getCurrentItemOrArmor(0) != null ? 1 :0;

				if(entityLiving instanceof EntityPlayer)
				{
					armorModel.aimedBow = ((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
				}

				return armorModel;
			}
		}

		return null;
	}
}