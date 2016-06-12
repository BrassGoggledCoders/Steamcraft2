
package steamcraft.common.compat;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import steamcraft.common.tiles.recipes.BloomeryRecipes;

public class IMCHandler
{
	@EventHandler
	public void imcCallback(FMLInterModComms.IMCEvent event)
	{
		for (final FMLInterModComms.IMCMessage msg : event.getMessages())
		{
			if (msg.key.equalsIgnoreCase("addBloomeryRecipe") && msg.isNBTMessage())
			{
				NBTTagCompound tag = msg.getNBTValue();
				BloomeryRecipes.getInstance().addBloomeryRecipe(ItemStack.loadItemStackFromNBT(tag.getCompoundTag("input1")),
						ItemStack.loadItemStackFromNBT(tag.getCompoundTag("input2")), ItemStack.loadItemStackFromNBT(tag.getCompoundTag("result")));
			}
		}
	}
}
