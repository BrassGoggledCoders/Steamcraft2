/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 23, 2014, 10:26:03 PM]
 */
package steamcraft.common.items.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import steamcraft.common.config.ConfigItems;
import steamcraft.common.items.ItemCanister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Decebaldecebal
 *
 */
public class ItemSteamJetpack extends ItemCustomArmor
{
	private static final int steamPerTick = 10; //how much steam is uses per tick
	
	public ItemSteamJetpack(int id, ArmorMaterial mat, int renderIndex, int armorType)
	{
		super(mat, renderIndex, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{		
        int i = 0;
        while(i < 36)
        {
            ItemStack[] mainInv = player.inventory.mainInventory;
            if(mainInv[i] != null && mainInv[i].getItem() == ConfigItems.itemCanisterSteam)
            {
                if(!player.capabilities.allowFlying)
                {
                    if(Minecraft.getMinecraft().currentScreen == null && Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()))
                    {
                        if(player.motionY > 0.0D)
                        {
                            player.motionY += 0.08499999910593033D;
                        } 
                        else
                        {
                            player.motionY += 0.11699999910593033D;
                        }

                        if(!ItemCanister.isEmptySteam(mainInv[i]))
                        	ItemCanister.addSteam(mainInv[i], -steamPerTick);
                        else
                            mainInv[i] = new ItemStack(ConfigItems.itemCanisterSteam);
                        
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
                    
            		if(player.fallDistance > 0)
            		{
            			ItemCanister.addSteam(mainInv[i], -(int) (player.fallDistance / Math.PI));
        	        	player.fallDistance = 0;
            		}
                }
            }
            i++;
        }
	}

	/*
	Not sure if this is the right place for it
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) 
	{
		ModelBiped armorModel = new ModelBiped();

		if(itemStack != null)
		{
			if(itemStack.getItem() instanceof ItemSteamJetpack)
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
	*/
}
