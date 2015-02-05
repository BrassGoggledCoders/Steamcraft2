package steamcraft.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import steamcraft.client.lib.RenderIDs;

public class BlockSpiderEggRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{

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
			float f1 = 1.0F - l / 16.0F;
			float f2 = 1.0F - (l + b1) / 16.0F;
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
