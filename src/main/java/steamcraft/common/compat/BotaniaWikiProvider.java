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
package steamcraft.common.compat;

import net.minecraft.block.Block;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.common.Optional;

import org.apache.commons.lang3.text.WordUtils;
import vazkii.botania.api.wiki.IWikiProvider;

@Optional.Interface(iface = "vazkii.botania.api.wiki.IWikiProvider", modid = "Botania")
public class BotaniaWikiProvider implements IWikiProvider
{
	public BotaniaWikiProvider()
	{

	}

	@Override
	@Optional.Method(modid = "Botania")
	public String getBlockName(World world, MovingObjectPosition pos)
	{
		int x = pos.blockX;
		int y = pos.blockY;
		int z = pos.blockZ;

		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if(block == null)
			return null;

		if(!StatCollector.translateToLocal(block.getUnlocalizedName() + "." + meta + ".name").contains(".name"))
			return StatCollector.translateToLocal(block.getUnlocalizedName() + "." + meta + ".name");
		else
			return StatCollector.translateToLocal(block.getUnlocalizedName() + ".name");
	}

	@Override
	@Optional.Method(modid = "Botania")
	public String getWikiURL(World world, MovingObjectPosition pos)
	{
		String name = this.getBlockName(world, pos);
		if(name == null)
			return null;
		return "http://sc2.wikia.com/" + WordUtils.capitalizeFully(name).replaceAll(" ", "%20");
	}

	@Override
	@Optional.Method(modid = "Botania")
	public String getWikiName(World world, MovingObjectPosition pos)
	{
		return "SteamCraft2 Wiki";
	}
}
