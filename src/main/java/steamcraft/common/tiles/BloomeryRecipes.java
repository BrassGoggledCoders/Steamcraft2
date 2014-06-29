package steamcraft.common.tiles;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import steamcraft.common.config.ConfigItems;

public class BloomeryRecipes
{
    private static final BloomeryRecipes smeltingBase = new BloomeryRecipes();
    /** The list of smelting results. */
    private Map<ItemStack[], ItemStack> smeltingList = new HashMap<ItemStack[], ItemStack>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static BloomeryRecipes smelting()
    {
        return smeltingBase;
    }

    private BloomeryRecipes()
    {
        this.addBloomeryRecipe(new ItemStack(Blocks.iron_ore), new ItemStack(Items.coal), new ItemStack(ConfigItems.itemIngot, 1, 6));
        this.addBloomeryRecipe(new ItemStack(Blocks.iron_ore), new ItemStack(Blocks.iron_ore), new ItemStack(ConfigItems.itemIngot, 2, 7));
    }

    public void addBloomeryRecipe(ItemStack input, ItemStack input2, ItemStack result)
    {
        this.smeltingList.put(new ItemStack[]{input, input2}, result);
    }


    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack stack)
    {
        Iterator<?> iterator = this.smeltingList.entrySet().iterator();
        Entry<?, ?> entry;
        ItemStack result = null;
		do
        {
            if (!iterator.hasNext())
            {
                return null;
            }
            entry = (Entry)iterator.next();
            if(entry.getKey() != null)
            result = (ItemStack) entry.getValue();
        }
        while (result == null);

        return (ItemStack) entry.getValue();
    }
    public ItemStack[] getSmeltingInputs(ItemStack output)
    {
    	 Iterator<?> iterator = this.smeltingList.entrySet().iterator();
         Entry<?, ?> entry;
         ItemStack[] inputs = null;
         do
         {
        	 entry = (Entry<?, ?>) iterator.next();
        	 if(entry.getValue() == output)
        	inputs = (ItemStack[]) entry.getKey();
        	 return (ItemStack[]) entry.getKey();
         }
         while(inputs == null);
    }


    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem();
        		/*&& (p_151397_2_.getItemDamage() == 32767
        		|| p_151397_2_.getItemDamage()
        		== p_151397_1_.getItemDamage());*/
    }

    public Map<ItemStack[], ItemStack> getSmeltingList()
    {
        return this.smeltingList;
    }
}