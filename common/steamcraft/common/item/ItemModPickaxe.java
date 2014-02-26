package common.steamcraft.common.item;

import common.steamcraft.common.lib2.MaterialMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

public class ItemModPickaxe extends ItemModTool
{
	public static final Block[] blocksEffectiveAgainst = new Block[] {Block.cobblestone, 
		Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone,
		Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, 
		Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, 
		Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone,
		Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered, 
		Block.railActivator};

	protected ItemModPickaxe(int id, EnumToolMaterial toolMat)
	{
		super(id, 2.0F, toolMat, blocksEffectiveAgainst);
	}

	@Override
	public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (par1Block != Block.blockDiamond && par1Block != Block.oreDiamond ? (par1Block != Block.oreEmerald && par1Block != Block.blockEmerald ? (par1Block != Block.blockGold && par1Block != Block.oreGold ? (par1Block != Block.blockIron && par1Block != Block.oreIron ? (par1Block != Block.blockLapis && par1Block != Block.oreLapis ? (par1Block != Block.oreRedstone && par1Block != Block.oreRedstoneGlowing ? (par1Block.blockMaterial == Material.rock ? true : (par1Block.blockMaterial == Material.iron ? true : par1Block.blockMaterial == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }
	
	@Override
	public float getStrVsBlock(ItemStack stack, Block block)
    {
		if(this.toolMaterial == MaterialMod.STEAM_TOOL)
		{
			return (4.0F - (((float) stack.getItemDamage()) * 11 / 320));
		}
		if(block != null && (block.blockMaterial == Material.iron || block.blockMaterial == Material.anvil || block.blockMaterial == Material.rock))
		{
			super.getStrVsBlock(stack, block);
		}
		
		return this.efficiencyOnProperMaterial;
    }
}