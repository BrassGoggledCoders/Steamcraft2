package common.steamcraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCoreDrill extends ItemMod
{
	protected ItemCoreDrill(int id)
	{
		super(id);
		this.maxStackSize = 1;
		this.setMaxDamage(256);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int l, float f1, float f2, float f3)
	{
		world.createExplosion(player, i, j, k, 2F, true);
		double d = (double)((float)i + 0.5F) + (double)(0.5F) * 2.0000000000000001D;
		double d1 = (double)((float)j + 0.7F) + (double)(0.5F) * 2.0000000000000001D;
		double d2 = (double)((float)k + 0.5F) + (double)(0.5F) * 2.0000000000000001D;
		world.spawnParticle("smoke", d, d1, d2, 0.0D, 10.0D, 0.0D);
		stack.damageItem(1, player);
		return true;
	}
	
	/*public int getRandomInt(Random random, int bound){
	return random.nextInt(bound);
	}

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
	 blocksToPlace = (new Object[] {
	 Block.dirt, Block.stone, Block.wood, Block.cobblestone, Block.bloodStone, Block.lightStone,
	 Block.cactus, Block.cake, Block.blockClay, Block.cloth, Block.sand, Block.glass, Block.gravel,
	 Block.sandStone, Block.oreGold, Block.oreIron, Block.oreCoal, Block.oreLapis, Block.cobblestoneMossy,
	 Block.obsidian, Block.mobSpawner, Block.oreDiamond, Block.oreRedstone, Block.blockSnow, Block.ice,
	 Block.pumpkin, Block.slowSand, Block.brimstone, Block.orePhosphate
	 });
        if(l == 0)
        {
            j--;
        }
        if(l == 1)
        {
            j++;
        }
        if(l == 2)
        {
            k--;
        }
        if(l == 3)
        {
            k++;
        }
        if(l == 4)
        {
            i--;
        }
        if(l == 5)
        {
            i++;
        }
        if(!world.isAirBlock(i, j, k))
        {
            return false;
        }
		int randomInt = getRandomInt(new Random(), blocksToPlace.length);
        if(Block.waterMoving.canPlaceBlockAt(world, i, j, k))
        {
			Block blockPlacing = (Block)blocksToPlace[randomInt];
            world.setBlockWithNotify(i, j, k, blockPlacing.blockID);
			setMaxDamage(getMaxDamage()-1);
        }
        return true;
    }
	  private Object blocksToPlace[];*/
}