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
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 *
 */
public class BaseBlock extends Block
{
	public IIcon overlayIcon;
	private int pass;

	public BaseBlock(Material mat)
	{
		super(mat);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
		this.overlayIcon = ir.registerIcon(LibInfo.PREFIX + "/overlay/pumpkin");
	}
	@Override
	public boolean canRenderInPass(int pass)
    {
		pass = this.pass;
		return true;
    }
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
		if(pass == 0)
		return blockIcon;
		else return overlayIcon;
    }
	@Override
	public int getRenderBlockPass()
    {
		return 1;
    }
}
