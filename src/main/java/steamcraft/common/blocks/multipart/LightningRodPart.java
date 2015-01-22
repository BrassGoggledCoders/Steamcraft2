package steamcraft.common.blocks.multipart;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.nbt.NBTTagCompound;
import steamcraft.client.renderers.tile.multipart.TileLightningRodPartRenderer;
import steamcraft.common.InitBlocks;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.energy.TileLightningRod;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.minecraft.McBlockPart;

public class LightningRodPart extends McBlockPart
{
	TileLightningRod rod;
	TileLightningRodPartRenderer rend;

	@Override
	public Cuboid6 getBounds()
	{
		return new Cuboid6(0.3F, 0, 0.3F, 0.7F, 2.0F, 0.7F);
	}

	@Override
	public Block getBlock()
	{
		return InitBlocks.blockLightningRod;
	}

	@Override
	public String getType()
	{
		return ModInfo.PREFIX + "LightningRodPart";
	}

	@Override
	public void invalidateConvertedTile()
	{
		rod = (TileLightningRod) this.world().getTileEntity(x(), y(), z());
	}

	@Override
	public void load(NBTTagCompound tag)
	{
		if(rod != null)
			rod.buffer.readFromNBT(tag);
	}

	@Override
	public void save(NBTTagCompound tag)
	{
		if(rod != null)
			rod.buffer.writeToNBT(tag);
	}

	@Override
	public void onWorldJoin()
	{
		rend = new TileLightningRodPartRenderer();
		rend.func_147497_a(TileEntityRendererDispatcher.instance);
	}

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass)
	{
		rend.renderTileEntityAt(tile(), pos.x, pos.y, pos.z, 0);
	}
}
