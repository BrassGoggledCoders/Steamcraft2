package common.steamcraft.common.core.compat;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import common.steamcraft.common.core.helper.CompatHelper;
import common.steamcraft.common.item.ModItems;

public class ModCompatLayer {
public static void loadModCompat()
{
	
}

public static void registerOreDictionary() {
	//Ingots
	OreDictionary.registerOre("ingotAluminum", new ItemStack(ModItems.ingotsMetal, 0, 0));
	OreDictionary.registerOre("ingotAluminium", new ItemStack(ModItems.ingotsMetal, 0, 0));
	OreDictionary.registerOre("ingotCopper", new ItemStack(ModItems.ingotsMetal, 0, 1));
	OreDictionary.registerOre("ingotTin", new ItemStack(ModItems.ingotsMetal, 0, 2));
	OreDictionary.registerOre("ingotZinc", new ItemStack(ModItems.ingotsMetal, 0, 3));
}
}
