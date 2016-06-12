
package steamcraft.common.items.tools;

import java.util.List;

import boilerplate.common.baseclasses.items.electric.ItemElectricTool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Surseance
 *
 */
public class ItemElectrifiedSword extends ItemElectricTool
{
	protected int energyPerHit = 400;

	public ItemElectrifiedSword(ToolMaterial toolMat, int maxEnergy, int maxRecieve)
	{
		super(7F, toolMat, maxEnergy, maxRecieve);
		this.setHarvestLevel("sword", toolMat.getHarvestLevel());
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag)
	{
		super.addInformation(stack, entityplayer, list, flag);

		list.add("Efficiency: " + this.toolMaterial.getEfficiencyOnProperMaterial());
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase living1, EntityLivingBase living2)
	{
		this.extractEnergy(stack, this.energyPerHit, false);
		// stack.damageItem(1, living2);

		// super.hitEntity(stack, living1, living2);

		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if (((ItemElectricTool) stack.getItem()).getEnergyStored(stack) > 0)
			this.changeToolDamage(stack, 7);
		else
			this.changeToolDamage(stack, 1.0D);
	}
}
