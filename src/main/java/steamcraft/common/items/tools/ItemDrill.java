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

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

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
		super(1.0F, mat);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.setHarvestLevel("pickaxe", mat.getHarvestLevel());
		this.setHarvestLevel("shovel", mat.getHarvestLevel());
		this.toolMaterial = mat;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		this.itemIcon = icon.registerIcon(ModInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		super.addInformation(stack, player, list, flag);

		list.add("Efficiency: " + this.toolMaterial.getEfficiencyOnProperMaterial());
	}
}
