package steamcraft.common.compat;

import net.minecraft.block.Block;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.apache.commons.lang3.text.WordUtils;

import vazkii.botania.api.wiki.IWikiProvider;

public class BotaniaWikiProvider implements IWikiProvider
{
	public BotaniaWikiProvider()
	{

	}

	@Override
	public String getBlockName(World world, MovingObjectPosition pos)
	{
		int x = pos.blockX;
		int y = pos.blockY;
		int z = pos.blockZ;

		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if(block == null)
			return null;

		if(meta != 0)
			return StatCollector.translateToLocal(block.getUnlocalizedName() + meta + ".name");
		else
			return StatCollector.translateToLocal(block.getUnlocalizedName() + ".name");
	}

	@Override
	public String getWikiURL(World world, MovingObjectPosition pos)
	{
		String name = getBlockName(world, pos);
		if(name == null)
			return null;
		return "http://sc2.wikia.com/" + WordUtils.capitalizeFully(name).replaceAll(" ", "%20");
	}

	@Override
	public String getWikiName(World world, MovingObjectPosition pos)
	{
		return "SteamCraft2 Wiki";
	}
}
