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
package steamcraft.common.tiles.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import steamcraft.common.InitItems;

/**
 * @author warlordjones & decebaldecebal
 * 
 */
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
		addBloomeryRecipe(new ItemStack(Blocks.iron_ore), new ItemStack(Items.coal, 4), new ItemStack(InitItems.itemIngot, 1, 6));
	}

	public void addBloomeryRecipe(ItemStack input, ItemStack input2, ItemStack result)
	{
		recipeList.put(new ItemStack[] { input, input2 }, result);
	}

	public ItemStack getResult(ItemStack stack1, ItemStack stack2)
	{
		ItemStack input[] = new ItemStack[2];
		input[0] = stack1;
		input[1] = stack2;

		Iterator<Entry<ItemStack[], ItemStack>> iterator = recipeList.entrySet().iterator();
		Entry<?, ?> entry;

		do
		{
			if (!iterator.hasNext())
				return null;

			entry = iterator.next();
		} while (!checkItemsAgainstRecipes(input, (ItemStack[]) entry.getKey()));

		return (ItemStack) entry.getValue();
	}

	@SuppressWarnings("unchecked")
	public ItemStack[] getSmeltingInputs(ItemStack output)
	{
		Iterator<?> iterator = recipeList.entrySet().iterator();
		Entry<ItemStack[], ItemStack> entry;
		ItemStack[] inputs = null;
		do
		{
			entry = (Entry<ItemStack[], ItemStack>) iterator.next();

			if ((entry.getValue().getItem() == output.getItem()) && (entry.getValue().getItemDamage() == output.getItemDamage()))
				return entry.getKey();
		} while (inputs == null);

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
		ItemStack[] inputs = getSmeltingInputs(output);

		if (input1 != null)
		{
			if (input2 != null)
				if (checkItemsAgainstRecipes(new ItemStack[] { input1, input2 }, inputs))
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
		return recipeList;
	}
}