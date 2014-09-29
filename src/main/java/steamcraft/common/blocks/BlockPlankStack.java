package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockPlankStack extends BaseBlock
{
	private int numStoredPlanks = 1;

	public BlockPlankStack(Material mat)
	{
		super(mat);

	}
	  /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return getNumStoredPlanks();
    }

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
		this.numStoredPlanks = numStoredPlanks;
	}

}
