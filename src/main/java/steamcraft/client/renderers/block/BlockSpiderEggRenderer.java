package steamcraft.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import org.lwjgl.opengl.GL11;
import steamcraft.client.lib.RenderIDs;

public class BlockSpiderEggRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
		int k = 0;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();

		for(int l = 0; l < 8; ++l)
		{
			byte b0 = 0;
			byte b1 = 1;

			if(l == 0)
			{
				b0 = 2;
			}

			if(l == 1)
			{
				b0 = 3;
			}

			if(l == 2)
			{
				b0 = 4;
			}

			if(l == 3)
			{
				b0 = 5;
				b1 = 2;
			}

			if(l == 4)
			{
				b0 = 6;
				b1 = 3;
			}

			if(l == 5)
			{
				b0 = 7;
				b1 = 5;
			}

			if(l == 6)
			{
				b0 = 6;
				b1 = 2;
			}

			if(l == 7)
			{
				b0 = 3;
			}

			float f5 = b0 / 16.0F;
			float f6 = 1.0F - (k / 16.0F);
			float f7 = 1.0F - ((k + b1) / 16.0F);
			k += b1;
			renderer.setRenderBounds(0.5F - f5, f7, 0.5F - f5, 0.5F + f5, f6, 0.5F + f5);
			tessellator.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 0));
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 1));
			tessellator.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 2));
			tessellator.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 3));
			tessellator.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 4));
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 5));
		}

		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID,
			RenderBlocks renderer)
	{
		boolean flag = false;
		int l = 0;

		for(int i1 = 0; i1 < 8; i1++)
		{
			byte b0 = 0;
			byte b1 = 1;

			if(i1 == 0)
			{
				b0 = 2;
			}

			if(i1 == 1)
			{
				b0 = 3;
			}

			if(i1 == 2)
			{
				b0 = 4;
			}

			if(i1 == 3)
			{
				b0 = 5;
				b1 = 2;
			}

			if(i1 == 4)
			{
				b0 = 6;
				b1 = 3;
			}

			if(i1 == 5)
			{
				b0 = 7;
				b1 = 5;
			}

			if(i1 == 6)
			{
				b0 = 6;
				b1 = 2;
			}

			if(i1 == 7)
			{
				b0 = 3;
			}

			float f = b0 / 16.0F;
			float f1 = 1.0F - (l / 16.0F);
			float f2 = 1.0F - ((l + b1) / 16.0F);
			l += b1;
			renderer.setRenderBounds(0.5F - f, f2, 0.5F - f, 0.5F + f, f1, 0.5F + f);
			renderer.renderStandardBlock(block, x, y, z);
		}
		flag = true;
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
		return RenderIDs.blockSpiderEggRI;
	}
}
