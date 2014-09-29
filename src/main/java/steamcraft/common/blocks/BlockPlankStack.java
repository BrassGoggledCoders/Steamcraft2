package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockPlankStack extends BaseBlock
{
	private static int numStoredPlanks = 6;
	private static int plankMeta = 0;

	public BlockPlankStack(Material mat)
	{
		super(mat);

	}
	  /**
     * Returns the quantity of items to drop on block destruction.
     */
	@Override
    public int quantityDropped(Random p_149745_1_)
    {
        return getNumStoredPlanks();
    }
    @Override
    public int damageDropped(int p_149692_1_)
    {
    	return getPlankMeta();
    }
    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(Blocks.planks);
    }
	public int getNumStoredPlanks()
	{
		return numStoredPlanks;
	}
	public void setNumStoredPlanks(int numStoredPlanks)
	{
		BlockPlankStack.numStoredPlanks = numStoredPlanks;
	}
	public static int getPlankMeta()
	{
		return plankMeta;
	}
	public void setPlankMeta(int plankMeta)
	{
		BlockPlankStack.plankMeta = plankMeta;
	}

}
