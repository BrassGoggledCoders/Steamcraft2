package common.steamcraft.common.block.tile.container.slot;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;

public class SlotNukeFurnace extends Slot
{	
	private EntityPlayer thePlayer;
	private int field_75228_b;
	
    public SlotNukeFurnace(EntityPlayer player, IInventory inventory, int i, int j, int k)
    {
        super(inventory, i, j, k);
        thePlayer = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }
    
    @Override
    public ItemStack decrStackSize(int i)
    {
        if(this.getHasStack())
        {
            this.field_75228_b += Math.min(i, this.getStack().stackSize);
        }

        return super.decrStackSize(i);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
    {
        this.onCrafting(stack);
        super.onPickupFromSlot(player, stack);
		// ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_Steamcraft.ach_Fallout);
    }
    
    @Override
    protected void onCrafting(ItemStack par1ItemStack, int par2)
    {
        this.field_75228_b += par2;
        this.onCrafting(par1ItemStack);
    }
    
    @Override
    protected void onCrafting(ItemStack stack)
    {
        stack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75228_b);

        if(!this.thePlayer.worldObj.isRemote)
        {
            int i = this.field_75228_b;
            float exp = FurnaceRecipes.smelting().getExperience(stack);
            int j;

            if(exp == 0.0F)
            {
                i = 0;
            } else if(exp < 1.0F)
            {
                j = MathHelper.floor_float((float)i * exp);

                if(j < MathHelper.ceiling_float_int((float)i * exp) && (float)Math.random() < (float)i * exp - (float)j)
                {
                    ++j;
                }

                i = j;
            } while(i > 0)
            {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5D, this.thePlayer.posZ + 0.5D, j));
            }
        }

        this.field_75228_b = 0;
        GameRegistry.onItemSmelted(thePlayer, stack);

        if(stack.itemID == Item.ingotIron.itemID)
        {
            this.thePlayer.addStat(AchievementList.acquireIron, 1);
        }
        if(stack.itemID == Item.fishCooked.itemID)
        {
            this.thePlayer.addStat(AchievementList.cookFish, 1);
        }
    }
}