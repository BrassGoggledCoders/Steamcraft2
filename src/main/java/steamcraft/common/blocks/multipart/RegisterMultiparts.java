package steamcraft.common.blocks.multipart;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import steamcraft.common.InitBlocks;
import steamcraft.common.lib.ModInfo;
import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;

public class RegisterMultiparts implements IPartFactory, IPartConverter
{
	@Override
	public TMultiPart createPart(String name, boolean client)
	{
		if(name.equals(ModInfo.PREFIX + "LightningRodPart"))
			return new LightningRodPart();

		return null;
	}

	public void init()
	{
		MultiPartRegistry.registerConverter(this);
		MultiPartRegistry.registerParts(this, new String[] {
				ModInfo.PREFIX + "LightningRodPart",
		});
	}

	@Override
	public Iterable<Block> blockTypes()
	{
		return Arrays.asList(InitBlocks.blockLightningRod);
	}

	@Override
	public TMultiPart convert(World world, BlockCoord pos)
	{
		Block b = world.getBlock(pos.x, pos.y, pos.z);
		int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
		if(b == InitBlocks.blockLightningRod)
			return new LightningRodPart();

		return null;
	}
}
