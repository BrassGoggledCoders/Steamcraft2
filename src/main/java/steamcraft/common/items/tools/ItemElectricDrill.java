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
package steamcraft.common.items.tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.items.electric.ItemElectricTool;
import steamcraft.common.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElectricDrill extends ItemElectricTool
{
	protected int maxEnergy = 80;
	protected short maxReceive = 80;
	protected int energyPerBlock = 400;

	public ItemElectricDrill(ToolMaterial mat, int maxEnergy, int maxReceive)
	{
		super(1, mat, ItemDrill.effectiveBlocks, maxEnergy, maxReceive);
		this.setHarvestLevel("pickaxe", mat.getHarvestLevel());
		this.setHarvestLevel("shovel", mat.getHarvestLevel());
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag)
	{
		super.addInformation(stack, entityplayer, list, flag);

		list.add("Efficiency: " + this.toolMaterial.getEfficiencyOnProperMaterial());
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block p_150894_3_, int x, int y, int z, EntityLivingBase living)
	{
		if(living instanceof EntityPlayer)
		{
			this.setEnergy(stack, this.getEnergyStored(stack) - this.energyPerBlock);

			world.playSoundAtEntity(living, ModInfo.PREFIX + "drill.steam", 0.6F, 1.0F);
			world.spawnParticle("smoke", x + 0.5, y + 0.5, z + 0.5, Item.itemRand.nextGaussian(), Item.itemRand.nextGaussian(),
					Item.itemRand.nextGaussian());
			return true;
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		this.itemIcon = icon.registerIcon(ModInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		for(Block element : ItemDrill.effectiveBlocks)
			if(element == block)
				return true;

		return false;
	}
}
