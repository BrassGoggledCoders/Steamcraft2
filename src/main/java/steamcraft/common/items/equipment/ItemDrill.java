/*
 *
 */
package steamcraft.common.items.equipment;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Base class for drills.
 * 
 * @author Decebaldecebal
 * 
 */
public class ItemDrill extends ItemModTool
{
	public static final Block[] blocksEffectiveAgainst = new Block[] { Blocks.cobblestone, Blocks.dirt, Blocks.stone, Blocks.sand, Blocks.clay,
			Blocks.ice, Blocks.snow, Blocks.netherrack, Blocks.grass, Blocks.gravel };

	protected ToolMaterial toolMaterial;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		this.itemIcon = icon.registerIcon(LibInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
	}

	public ItemDrill(ToolMaterial mat)
	{
		super(2.0F, mat, blocksEffectiveAgainst);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.toolMaterial = mat;
		this.setMaxDamage(this.toolMaterial.getMaxUses());
	}

	@SuppressWarnings("all")
	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		for (Block element : ItemDrill.blocksEffectiveAgainst)
		{
			if (element == block)
			{
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		super.addInformation(stack, player, list, flag);
		list.add("Efficiency: " + this.getEfficiency());
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase hitEntity, EntityLivingBase player)
	{
		// if(this.getEnergy(stack) > 0) {
		hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), (this.toolMaterial.getDamageVsEntity()));
		stack.damageItem(1, player);

		return false;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
			NBTTagCompound tag = stack.getTagCompound();
			if(tag.getBoolean("hasCanister"))
			return this.canHarvestBlock(block, stack) ? this.getEfficiency() : 1.0F;
			else return 0.1F;
	}

	public float getEfficiency()
	{
		return this.toolMaterial.getEfficiencyOnProperMaterial();
	}
}
