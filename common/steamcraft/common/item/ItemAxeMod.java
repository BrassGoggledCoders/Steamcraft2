package common.steamcraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

public class ItemAxeMod extends ItemModTool
{
	public static final Block[] blocksEffectiveAgainst = new Block[] {Block.planks, Block.bookShelf, 
		Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern};
	
    protected ItemAxeMod(int id, EnumToolMaterial toolMat)
    {
        super(id, 3.0F, toolMat, blocksEffectiveAgainst);
    }
    
    @Override
    public float getStrVsBlock(ItemStack stack, Block block)
    {
        return block != null && (block.blockMaterial == Material.wood || block.blockMaterial == Material.plants || block.blockMaterial == Material.vine) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(stack, block);
    }
}
