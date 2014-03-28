/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 8, 2014, 3:43:45 PM]
 */
package common.steamcraft.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import common.steamcraft.client.core.helper.IconHelper;
import common.steamcraft.common.lib2.CreativeTabsMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Base class for electric drills.
 * 
 * @author Decebaldecebal
 *
 */
public class ItemElectricDrill extends ItemElectricMod 
{
	int energyPerBlock = 200; //same as IC2 drill(50 EU per block)
	int toolTier;
	
	/** An array of blocks the drill can mine. */
	public static final Block[] blocksEffectiveAgainst = new Block[] {
		Block.cobblestone, Block.dirt, Block.stone, Block.sand, Block.blockClay, Block.ice,
		Block.snow, Block.netherrack, Block.grass, Block.gravel
	}; 

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		itemIcon = IconHelper.forItem(icon, this);
	}

	public ItemElectricDrill(int id, int maxEnergy, int toolTier, int energyTier) 
	{
		super(id, maxEnergy, (byte)energyTier);
		this.setCreativeTab(CreativeTabsMod.tabSCItems);
		this.toolTier = toolTier;
	}

	@SuppressWarnings("all")
	@Override
	public boolean canHarvestBlock(Block block) {
		for (int i = 0; i < this.blocksEffectiveAgainst.length; ++i) {
            if (this.blocksEffectiveAgainst[i] == block) {
                return true;
            }
        } 
		
		return false;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		super.addInformation(stack, player, list, flag);
		list.add("Efficiency: " + getEfficiency());
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase hitEntity, EntityLivingBase player) {
		if(this.getEnergy(stack) > 0) {
			hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), (this.toolTier + 1));
			this.setEnergy(stack, getEnergy(stack) - this.energyPerBlock * 2);
		} else {
			hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), 1);
		}

		return false;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block) {
		return this.getEnergy(stack) != 0 && this.canHarvestBlock(block) ? this.getEfficiency() : 1.0F;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, int id, int i, int j, int k, EntityLivingBase living)
	{
		if(Block.blocksList[id].getBlockHardness(world, i, j, k) != 0.0D) 
			this.setEnergy(stack, getEnergy(stack) - this.energyPerBlock);
		else
			this.setEnergy(stack, getEnergy(stack) - this.energyPerBlock/2);

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public float getEfficiency() {
		return ((this.toolTier + 1.5F) / 1.5F) *  7.5F;
	}
}
