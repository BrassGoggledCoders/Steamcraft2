
package steamcraft.common.entities.living;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import steamcraft.common.init.InitBlocks;

public class EntitySpiderQueen extends EntitySpider implements IBossDisplayData
{
	public EntitySpiderQueen(World p_i1743_1_)
	{
		super(p_i1743_1_);
		this.setSize(10F, 7F);
		this.experienceValue = 50;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!this.worldObj.isRemote)
		{
			int plusX = this.rand.nextInt(5);
			int plusY = this.rand.nextInt(5);
			int plusZ = this.rand.nextInt(5);
			if ((this.rand.nextInt(30) == 0) && !this.worldObj.isAirBlock((int) Math.round(this.posX) + plusX,
					((int) Math.round(this.posY) + plusY) - 1, (int) Math.round(this.posZ) + plusZ))
				this.worldObj.setBlock((int) Math.round(this.posX) + plusX, (int) Math.round(this.posY) + plusY, (int) Math.round(this.posZ) + plusZ,
						InitBlocks.blockSpiderEgg);
		}
	}

	@Override
	public IChatComponent func_145748_c_()
	{
		return new ChatComponentText("Spider Queen");
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
	}
}
