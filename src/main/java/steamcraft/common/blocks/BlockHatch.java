/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [12 Apr 2014, 21:57:03]
 */
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.client.ClientProxy;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.tiles.TileCrystal;
import steamcraft.common.tiles.TileHatch;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class BlockHatch extends BlockContainer{

	public static ResourceLocation hatchTextures = new ResourceLocation("assets/steamcraft/textures/blocks/");
	/**
	 * @param par1
	 * @param par2Material
	 */
	public BlockHatch(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(Steamcraft.tabSC2);
		setUnlocalizedName("blockHatch");
		setBlockBounds(0, 0, 0, 1, 0.55F, 1);
	}
	 @Override
	    public TileEntity createNewTileEntity(World world)
	    {
	        return new TileHatch();
	    }

	    @Override
	    public int getRenderType()
	    {
	        return ConfigBlocks.blockHatchRI;
	    }

	    @Override
	    public boolean isOpaqueCube()
	    {
	        return false;
	    }

	    @Override
	    public boolean renderAsNormalBlock()
	    {
	        return false;
	    }
}
