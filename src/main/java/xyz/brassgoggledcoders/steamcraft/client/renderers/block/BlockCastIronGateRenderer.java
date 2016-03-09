package xyz.brassgoggledcoders.steamcraft.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import xyz.brassgoggledcoders.steamcraft.client.lib.RenderIDs;
import xyz.brassgoggledcoders.steamcraft.common.blocks.BlockCastIronGate;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import org.lwjgl.opengl.GL11;

public class BlockCastIronGateRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;

		float i;

		renderer.setRenderBoundsFromBlock(block);
		int k;

		for (k = 0; k < 3; ++k)
		{
			i = 0.0625F;

			if (k == 0)
			{
				renderer.setRenderBounds(0.5F - i, 0.30000001192092896D, 0.0D, 0.5F + i, 1.0D, i * 2.0F);
			}

			if (k == 1)
			{
				renderer.setRenderBounds(0.5F - i, 0.30000001192092896D, 1.0F - (i * 2.0F), 0.5F + i, 1.0D, 1.0D);
			}

			i = 0.0625F;

			if (k == 2)
			{
				renderer.setRenderBounds(0.5F - i, 0.5D, 0.0D, 0.5F + i, 1.0F - i, 1.0D);
			}

			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 0));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 1));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 2));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 3));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 4));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 5));
			tessellator.draw();
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		boolean flag = true;
		int l = renderer.blockAccess.getBlockMetadata(x, y, z);
		boolean flag1 = BlockCastIronGate.isFenceGateOpen(l);
		int i1 = BlockDirectional.getDirection(l);
		float f = 0.375F;
		float f1 = 0.5625F;
		float f2 = 0.75F;
		float f3 = 0.9375F;
		float f4 = 0.3125F;
		float f5 = 1.0F;

		if ((((i1 == 2) || (i1 == 0)) && (renderer.blockAccess.getBlock(x - 1, y, z) == Blocks.cobblestone_wall)
				&& (renderer.blockAccess.getBlock(x - 1, y, z) == Blocks.cobblestone_wall))
				|| (((i1 == 3) || (i1 == 1)) && (renderer.blockAccess.getBlock(x, y, z - 1) == Blocks.cobblestone_wall)
						&& (renderer.blockAccess.getBlock(x, y, z + 1) == Blocks.cobblestone_wall)))
		{
			f -= 0.1875F;
			f1 -= 0.1875F;
			f2 -= 0.1875F;
			f3 -= 0.1875F;
			f4 -= 0.1875F;
			f5 -= 0.1875F;
		}

		renderer.renderAllFaces = true;
		float f6;
		float f7;
		float f8;
		float f9;

		if ((i1 != 3) && (i1 != 1))
		{
			f6 = 0.0F;
			f7 = 0.125F;
			f8 = 0.4375F;
			f9 = 0.5625F;
			renderer.setRenderBounds(f6, f4, f8, f7, f5, f9);
			renderer.renderStandardBlock(block, x, y, z);
			f6 = 0.875F;
			f7 = 1.0F;
			renderer.setRenderBounds(f6, f4, f8, f7, f5, f9);
			renderer.renderStandardBlock(block, x, y, z);
		}
		else
		{
			renderer.uvRotateTop = 1;
			f6 = 0.4375F;
			f7 = 0.5625F;
			f8 = 0.0F;
			f9 = 0.125F;
			renderer.setRenderBounds(f6, f4, f8, f7, f5, f9);
			renderer.renderStandardBlock(block, x, y, z);
			f8 = 0.875F;
			f9 = 1.0F;
			renderer.setRenderBounds(f6, f4, f8, f7, f5, f9);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.uvRotateTop = 0;
		}

		if (flag1)
		{
			if ((i1 == 2) || (i1 == 0))
			{
				renderer.uvRotateTop = 1;
			}

			if (i1 == 3)
			{
				f6 = 0.0F;
				f7 = 0.125F;
				f8 = 0.875F;
				f9 = 1.0F;
				renderer.setRenderBounds(0.8125D, f, 0.0D, 0.9375D, f3, 0.125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.8125D, f, 0.875D, 0.9375D, f3, 1.0D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.5625D, f, 0.0D, 0.8125D, f1, 0.125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.5625D, f, 0.875D, 0.8125D, f1, 1.0D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.5625D, f2, 0.0D, 0.8125D, f3, 0.125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.5625D, f2, 0.875D, 0.8125D, f3, 1.0D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (i1 == 1)
			{
				f6 = 0.0F;
				f7 = 0.125F;
				f8 = 0.875F;
				f9 = 1.0F;
				renderer.setRenderBounds(0.0625D, f, 0.0D, 0.1875D, f3, 0.125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.0625D, f, 0.875D, 0.1875D, f3, 1.0D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.1875D, f, 0.0D, 0.4375D, f1, 0.125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.1875D, f, 0.875D, 0.4375D, f1, 1.0D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.1875D, f2, 0.0D, 0.4375D, f3, 0.125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.1875D, f2, 0.875D, 0.4375D, f3, 1.0D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (i1 == 0)
			{
				f6 = 0.0F;
				f7 = 0.125F;
				f8 = 0.875F;
				f9 = 1.0F;
				renderer.setRenderBounds(0.0D, f, 0.8125D, 0.125D, f3, 0.9375D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.875D, f, 0.8125D, 1.0D, f3, 0.9375D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.0D, f, 0.5625D, 0.125D, f1, 0.8125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.875D, f, 0.5625D, 1.0D, f1, 0.8125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.0D, f2, 0.5625D, 0.125D, f3, 0.8125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.875D, f2, 0.5625D, 1.0D, f3, 0.8125D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (i1 == 2)
			{
				f6 = 0.0F;
				f7 = 0.125F;
				f8 = 0.875F;
				f9 = 1.0F;
				renderer.setRenderBounds(0.0D, f, 0.0625D, 0.125D, f3, 0.1875D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.875D, f, 0.0625D, 1.0D, f3, 0.1875D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.0D, f, 0.1875D, 0.125D, f1, 0.4375D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.875D, f, 0.1875D, 1.0D, f1, 0.4375D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.0D, f2, 0.1875D, 0.125D, f3, 0.4375D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.875D, f2, 0.1875D, 1.0D, f3, 0.4375D);
				renderer.renderStandardBlock(block, x, y, z);
			}
		}
		else if ((i1 != 3) && (i1 != 1))
		{
			f6 = 0.375F;
			f7 = 0.5F;
			f8 = 0.4375F;
			f9 = 0.5625F;
			renderer.setRenderBounds(f6, f, f8, f7, f3, f9);
			renderer.renderStandardBlock(block, x, y, z);
			f6 = 0.5F;
			f7 = 0.625F;
			renderer.setRenderBounds(f6, f, f8, f7, f3, f9);
			renderer.renderStandardBlock(block, x, y, z);
			f6 = 0.625F;
			f7 = 0.875F;
			renderer.setRenderBounds(f6, f, f8, f7, f1, f9);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setRenderBounds(f6, f2, f8, f7, f3, f9);
			renderer.renderStandardBlock(block, x, y, z);
			f6 = 0.125F;
			f7 = 0.375F;
			renderer.setRenderBounds(f6, f, f8, f7, f1, f9);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setRenderBounds(f6, f2, f8, f7, f3, f9);
			renderer.renderStandardBlock(block, x, y, z);
		}
		else
		{
			renderer.uvRotateTop = 1;
			f6 = 0.4375F;
			f7 = 0.5625F;
			f8 = 0.375F;
			f9 = 0.5F;
			renderer.setRenderBounds(f6, f, f8, f7, f3, f9);
			renderer.renderStandardBlock(block, x, y, z);
			f8 = 0.5F;
			f9 = 0.625F;
			renderer.setRenderBounds(f6, f, f8, f7, f3, f9);
			renderer.renderStandardBlock(block, x, y, z);
			f8 = 0.625F;
			f9 = 0.875F;
			renderer.setRenderBounds(f6, f, f8, f7, f1, f9);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setRenderBounds(f6, f2, f8, f7, f3, f9);
			renderer.renderStandardBlock(block, x, y, z);
			f8 = 0.125F;
			f9 = 0.375F;
			renderer.setRenderBounds(f6, f, f8, f7, f1, f9);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setRenderBounds(f6, f2, f8, f7, f3, f9);
			renderer.renderStandardBlock(block, x, y, z);
		}

		renderer.renderAllFaces = false;
		renderer.uvRotateTop = 0;
		renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
		return flag;
	}

	@Override
	public boolean shouldRender3DInInventory(int i)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return RenderIDs.blockCastIronGateRI;
	}
}
