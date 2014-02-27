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
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 5, 2014, 8:29:30 PM]
 */
package common.steamcraft.common.block;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import common.steamcraft.common.lib2.CreativeTabsMod;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * The SC2 equivalent to torches. Very steamy and smexy.
 * 
 * @author MrArcane111
 *
 */
public class BlockPhosphorusTorch extends BlockTorch {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		this.blockIcon = icon.registerIcon(LibInfo.SC2_PREFIX + (getUnlocalizedName().substring(5))); // Cannot do a 'this' reflection!
	}
	
	public BlockPhosphorusTorch(int id) {
		super(id);
		this.setCreativeTab(CreativeTabsMod.tabSCBlocks);
		this.setTickRandomly(true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		int meta = world.getBlockMetadata(i, j, k);
        double d = (double)((float)i + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
        double d1 = (double)((float)j + 0.7F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
        double d2 = (double)((float)k + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;
       
        if (meta == 1) {
            world.spawnParticle("reddust", d - d4, d1 + d3, d2, -1.0D, 1.0D, 0.0D);
        } else if (meta == 2) {
            world.spawnParticle("reddust", d + d4, d1 + d3, d2, -1.0D, 1.0D, 0.0D);
        } else if (meta == 3) {
            world.spawnParticle("reddust", d, d1 + d3, d2 - d4, -1.0D, 1.0D, 0.0D);
        } else if (meta == 4) {
            world.spawnParticle("reddust", d, d1 + d3, d2 + d4, -1.0D, 1.0D, 0.0D);
        } else {
            world.spawnParticle("reddust", d, d1, d2, -1.0D, 1.0D, 0.0D);
        }
	}
}
