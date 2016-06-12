
package steamcraft.common.items.tools;

import java.util.List;

import boilerplate.common.baseclasses.items.electric.ItemElectricTool;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.lib.ModInfo;

public class ItemElectricDrill extends ItemElectricTool
{
	protected int energyPerBlock = 400;

	public ItemElectricDrill(ToolMaterial mat, int maxEnergy, int maxReceive)
	{
		super(1, mat, maxEnergy, maxReceive);
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
		if (living instanceof EntityPlayer)
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
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if (this.getEnergyStored(stack) <= 0)
			return 1.0F;

		return super.getDigSpeed(stack, block, metadata);
	}
}
