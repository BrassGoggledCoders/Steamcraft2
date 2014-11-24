package steamcraft.common.compat;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import org.apache.commons.lang3.text.WordUtils;

import vazkii.botania.api.wiki.IWikiProvider;

public class BotaniaWikiProvider implements IWikiProvider
{
	final String urlBase, replacement;

	public BotaniaWikiProvider(String urlBase, String replacement)
	{
		this.urlBase = urlBase;
		this.replacement = replacement;
	}

	@Override
	public String getBlockName(World world, MovingObjectPosition pos)
	{
		int x = pos.blockX;
		int y = pos.blockY;
		int z = pos.blockZ;

		Block block = world.getBlock(x, y, z);
		if(block == null)
			return null;

		ItemStack stack = block.getPickBlock(pos, world, x, y, z);

		if(stack == null || stack.getItem() == null)
			stack = new ItemStack(block, 1, world.getBlockMetadata(x, y, z));

		if(stack.getItem() == null)
			return null;

		String name = stack.getDisplayName();
		if(name == null || name.isEmpty())
			return null;

		return name;
	}

	@Override
	public String getWikiURL(World world, MovingObjectPosition pos)
	{
		String name = getBlockName(world, pos);
		if(name == null)
			return null;
		return String.format(urlBase, WordUtils.capitalizeFully(name).replaceAll(" ", replacement));
	}

	@Override
	public String getWikiName(World world, MovingObjectPosition pos)
	{
		return "SteamCraft2 Wiki";
	}
}
