package common.steamcraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;

public class ItemModSpade extends ItemModTool
{
	public static final Block[] blocksEffectiveAgainst = new Block[] {Block.grass, Block.dirt, 
		Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, 
		Block.slowSand, Block.mycelium};

	public ItemModSpade(int id, EnumToolMaterial toolMat)
	{
		super(id, 1.0F, toolMat, blocksEffectiveAgainst);
	}

	@Override
	public boolean canHarvestBlock(Block block)
	{
		return block == Block.snow ? true : block == Block.blockSnow;
	}
}