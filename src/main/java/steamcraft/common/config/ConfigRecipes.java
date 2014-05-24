package steamcraft.common.config;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ConfigRecipes
{
	public void initCrafting()
	{
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemPoppySeed), new Object[] {new ItemStack(Blocks.double_plant, 1, 2), new ItemStack(Blocks.double_plant, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemPipe, 1, 1), new Object[] {new ItemStack(ConfigItems.itemPipe), new ItemStack(ConfigItems.itemPoppySeed)});
	}
}
