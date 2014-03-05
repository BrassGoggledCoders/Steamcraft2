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
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [2 Mar 2014, 21:46:49]
 */
package common.steamcraft.common.core.handler.recipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import common.steamcraft.common.item.ModItems;

/**
 * @author warlordjones
 *
 * 2 Mar 201421:46:49
 */
public class CrushingHandler
{
    private static final CrushingHandler recipeBase = new CrushingHandler();

    /** The list of machine results. */
    private HashMap<List<Integer>, ItemStack> metaRecipeList = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Integer> metaChargeList = new HashMap<List<Integer>, Integer>();

    /**
     * Used to call methods addRecipe and getRecipeResult.
     */
    public static final CrushingHandler recipe()
    {
        return recipeBase;
    }

    private CrushingHandler()
    {
    	// Vanilla Ores
    	this.addRecipe(new ItemStack(Block.oreIron), new ItemStack(ModItems.canisterSteam), 250);
    	this.addRecipe(new ItemStack(Block.oreGold), new ItemStack(ModItems.canisterSteam), 500);
    }

    /**
     * Adds a recipe.
     */
    public void addRecipe(ItemStack input, ItemStack stack, int chargeLevel)
    {
        this.metaRecipeList.put(Arrays.asList(input.itemID, input.getItemDamage()), stack);
        this.metaChargeList.put(Arrays.asList(input.itemID, input.getItemDamage()), Integer.valueOf(chargeLevel));
    }

    public Map getRecipeList()
    {
        return this.metaRecipeList;
    }

    public ItemStack getResult(ItemStack stack) 
    {
        if(stack == null)
        {
            return null;
        }
       
        return metaRecipeList.get(Arrays.asList(stack.itemID, stack.getItemDamage()));
    }

    /**
     * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
     */
    public int getChargeLevel(ItemStack stack)
    {
        if(stack == null)
        {
            return 0;
        }
        
        return metaChargeList.get(Arrays.asList(stack.itemID, stack.getItemDamage()));
    }
}