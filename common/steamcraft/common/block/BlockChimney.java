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
 * File created @ [Jan 30, 2014, 6:11:43 PM]
 */
package common.steamcraft.common.block;

import common.steamcraft.client.lib2.RenderIDs;
import common.steamcraft.common.block.machines.BlockContainerMod;
import common.steamcraft.common.block.tile.TileEntityChimney;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

/**
 * @author MrArcane111
 *
 */
public class BlockChimney extends BlockContainerMod implements ISimpleBlockRenderingHandler
{
	public static int[] sides = new int[3];
	
	public BlockChimney(int id)
	{
		super(id, Material.rock);
	}

	public int textureIndex(int meta)
	{
		meta &= 7;
		if(meta >  2)
			return this.sides[2];
		else
			return this.sides[meta & 7];
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

	@Override
	public int getRenderType()
	{
		return 0; // Check RenderBlocks for changes if needed
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityChimney();
	}

	@Override
	public int damageDropped(int i)
	{
		return i & 7; // checks the block's metadata
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{          
		if(player.isSneaking())
			this.infaSmoke(world, i, j, k, player);

		return false;
	}

	public void infaSmoke(World world, int i, int j, int k, EntityPlayer player)
	{
		int meta = world.getBlockMetadata(i, j, k);

		if((meta & 8) != 0)
		{
			meta &= 7;
			player.addChatMessage("Smoke OFF");
		} else
		{
			meta |= 8;
			player.addChatMessage("Smoke ON");
		}

		world.setBlockMetadataWithNotify(i, j, k, meta, 2);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) 
	{
		Tessellator tessellator = Tessellator.instance;

		if(this.getRenderType() == 0)
		{
			float f = 0.2F;
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f); 
			this.renderInvBox(renderer, block, modelID);
			block.setBlockBounds(1.0F - f, 0.0F, f, 1.0F, 1.0F, 1.0F - f); 
			this.renderInvBox(renderer, block, modelID);
			block.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F); 
			this.renderInvBox(renderer, block, modelID);
			block.setBlockBounds(0.0F, 0.0F, f, f, 1.0F, 1.0F - f); 
			this.renderInvBox(renderer, block, modelID);       
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F); 

			return;
		}

		return;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) 
	{
		if(this.getRenderType() == 0)
		{
			float f = 0.2F;
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f); 
			renderer.renderStandardBlock(block, i, j, k);
			block.setBlockBounds(1.0F - f, 0.0F, f, 1.0F, 1.0F, 1.0F - f); 
			renderer.renderStandardBlock(block, i, j, k);
			block.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F); 
			renderer.renderStandardBlock(block, i, j, k);
			block.setBlockBounds(0.0F, 0.0F, f, f, 1.0F, 1.0F - f); 
			renderer.renderStandardBlock(block, i, j, k);            
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F); 

			return true;
		}

		return false;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId() 
	{
		return RenderIDs.chimneyID;
	}

	private void renderInvBox(RenderBlocks renderer, Block block, int modelID)
	{
		Tessellator tessellator = Tessellator.instance;    
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderBlockAllFaces(block, (int)0.0D, (int)0.0D, (int)0.0D);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderBlockAllFaces(block, (int)0.0D, (int)0.0D, (int)0.0D);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderBlockAllFaces(block, (int)0.0D, (int)0.0D, (int)0.0D);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderBlockAllFaces(block, (int)0.0D, (int)0.0D, (int)0.0D);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderBlockAllFaces(block, (int)0.0D, (int)0.0D, (int)0.0D);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderBlockAllFaces(block, (int)0.0D, (int)0.0D, (int)0.0D);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}