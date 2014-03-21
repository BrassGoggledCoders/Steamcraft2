package common.steamcraft.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockCryoIce extends BlockMod {

	public BlockCryoIce(int id, Material mat) {
		super(id, mat);
		
	}
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity 
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) 
    {
    	this.applyDeepFreeze((EntityLiving)par5Entity);
    }
	public void applyDeepFreeze(EntityLiving entity)
	{
	entity.extinguish();
	entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 9999, 128, true));
	entity.addPotionEffect(new PotionEffect(Potion.jump.id, 9999, 128, true));
	entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 9999, 128, true));
	entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 9999, 1, true));
	entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 9999, 1, true));
	}
}
