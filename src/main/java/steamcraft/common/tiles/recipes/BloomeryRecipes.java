
package steamcraft.common.tiles.recipes;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import steamcraft.api.RecipeAPI;
import steamcraft.common.init.InitItems;

/**
 * @author warlordjones & decebaldecebal
 *
 */
public class BloomeryRecipes
{
	private static final BloomeryRecipes instance = new BloomeryRecipes();
	private final Map<ItemStack[], ItemStack> recipeList = RecipeAPI.addedRecipeList;

	public static BloomeryRecipes getInstance()
	{
		return instance;
	}

	private BloomeryRecipes()
	{
		this.addBloomeryRecipe(new ItemStack(Items.iron_ingot), new ItemStack(Items.coal, 4), new ItemStack(InitItems.itemIngot, 1, 6));
	}

	public void addBloomeryRecipe(ItemStack input, ItemStack input2, ItemStack result)
	{
		this.recipeList.put(new ItemStack[] { input, input2 }, result);
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
				return null;

			entry = iterator.next();
		} while (!this.checkItemsAgainstRecipes(input, (ItemStack[]) entry.getKey()));

		return (ItemStack) entry.getValue();
	}

	@SuppressWarnings("unchecked")
	public ItemStack[] getSmeltingInputs(ItemStack output)
	{
		Entry<ItemStack[], ItemStack> entry;
		for (Object obj : this.recipeList.entrySet())
		{
			entry = (Entry<ItemStack[], ItemStack>) obj;

			if ((entry.getValue().getItem() == output.getItem()) && (entry.getValue().getItemDamage() == output.getItemDamage()))
				return entry.getKey();
		}

		return null;
	}

	private boolean checkItemsAgainstRecipes(ItemStack[] input1, ItemStack[] input2)
	{
		return (input2[0].getItem() == input1[0].getItem())
				&& ((input2[0].getItemDamage() == 32767) || (input2[0].getItemDamage() == input1[0].getItemDamage()))
				&& (input2[1].getItem() == input1[1].getItem())
				&& ((input2[1].getItemDamage() == 32767) || (input2[1].getItemDamage() == input1[1].getItemDamage()))
				&& (input2[0].stackSize <= input1[0].stackSize) && (input2[1].stackSize <= input1[1].stackSize);
	}

	/**
	 * Not so good function but it does the job. Don't tinker with this.Don't
	 * use it!
	 */
	public byte[] getStackSizeForInputs(ItemStack input1, ItemStack input2, ItemStack output)
	{
		ItemStack[] inputs = this.getSmeltingInputs(output);

		if (input1 != null)
		{
			if (input2 != null)
				if (this.checkItemsAgainstRecipes(new ItemStack[] { input1, input2 }, inputs))
					return new byte[] { (byte) inputs[0].stackSize, (byte) inputs[1].stackSize };
				else
					return new byte[] { (byte) inputs[1].stackSize, (byte) inputs[0].stackSize };
			else
				return new byte[] { (byte) inputs[0].stackSize, (byte) 0 };
		}
		else
			return new byte[] { (byte) 0, (byte) inputs[1].stackSize };
	}

	public Map<ItemStack[], ItemStack> getSmeltingList()
	{
		return this.recipeList;
	}
}