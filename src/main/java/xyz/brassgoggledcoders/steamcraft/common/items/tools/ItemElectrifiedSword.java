/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package xyz.brassgoggledcoders.steamcraft.common.items.tools;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.steamcraft.common.items.electric.ItemElectricTool;

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
