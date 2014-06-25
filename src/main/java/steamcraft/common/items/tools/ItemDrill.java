/*
 *
 */
package steamcraft.common.items.tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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
	protected ToolMaterial toolMaterial;

	public ItemDrill(ToolMaterial mat)
	{
		super(1.0F, mat, blocksEffectiveAgainst);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.toolMaterial = mat;
		//this.setMaxDamage(this.toolMaterial.getMaxUses());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		this.itemIcon = icon.registerIcon(LibInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
		
		blocksEffectiveAgainst = new Block[] { Blocks.cobblestone, Blocks.dirt, Blocks.stone, Blocks.sand, Blocks.clay,
				Blocks.ice, Blocks.snow, Blocks.netherrack, Blocks.grass, Blocks.gravel };
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
		
		list.add("Efficiency: " + this.toolMaterial.getEfficiencyOnProperMaterial());
	}
}
