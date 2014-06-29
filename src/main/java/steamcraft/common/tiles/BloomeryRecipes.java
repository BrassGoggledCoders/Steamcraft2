package steamcraft.common.tiles;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import steamcraft.common.config.ConfigItems;

public class BloomeryRecipes
{
    private static final BloomeryRecipes instance = new BloomeryRecipes();
    private Map<ItemStack[], ItemStack> recipeList = new HashMap<ItemStack[], ItemStack>();

    public static BloomeryRecipes getInstance()
    {
        return instance;
    }

    private BloomeryRecipes()
    {
        this.addBloomeryRecipe(new ItemStack(Blocks.iron_ore), new ItemStack(Items.coal), new ItemStack(ConfigItems.itemIngot, 1, 6));
        this.addBloomeryRecipe(new ItemStack(Blocks.iron_ore), new ItemStack(Blocks.iron_ore), new ItemStack(ConfigItems.itemIngot, 2, 7));
    }

    public void addBloomeryRecipe(ItemStack input, ItemStack input2, ItemStack result)
    {
        this.recipeList.put(new ItemStack[]{input, input2}, result);
    }

    public ItemStack getResult(ItemStack stack1, ItemStack stack2)
    {		
		ItemStack input[] = new ItemStack[2];
		input[0] = stack1;
		input[1] = stack2;
		
        Iterator<Entry<ItemStack[], ItemStack>> iterator = this.recipeList.entrySet().iterator();
        Entry<?, ?> entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry<?, ?>)iterator.next();
        }
        while (!this.checkItemsAgainstRecipes(input, (ItemStack[])entry.getKey()));

        return (ItemStack)entry.getValue();
    }
    
    public ItemStack[] getSmeltingInputs(ItemStack output)
    {
    	 Iterator<?> iterator = this.recipeList.entrySet().iterator();
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


    private boolean checkItemsAgainstRecipes(ItemStack[] input1, ItemStack[] input2)
    {
    	 return input2[0].getItem() == input1[0].getItem() && (input2[0].getItemDamage() == 32767 || input2[0].getItemDamage() == input1[0].getItemDamage()) && 
         		input2[1].getItem() == input1[1].getItem() && (input2[1].getItemDamage() == 32767 || input2[1].getItemDamage() == input1[1].getItemDamage());
    }

    public Map<ItemStack[], ItemStack> getSmeltingList()
    {
        return this.recipeList;
    }
}