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
 * File created @ [Jan 30, 2014, 6:11:43 PM]
 */
package common.steamcraft.common.block;

import common.steamcraft.client.core.helper.IconHelper;
import common.steamcraft.common.item.ModItems;
import common.steamcraft.common.lib2.CreativeTabsMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author MrArcane111
 *
 */
public class BlockOreMod extends BlockMod
{
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		blockIcon = IconHelper.forBlock(icon, this);
	}
	
	public BlockOreMod(int id, String type) {
		super(id, Material.rock);
		this.setCreativeTab(CreativeTabsMod.tabSCBlocks);
		this.setStepSound(Block.soundStoneFootstep);
		
		if (type.equals("bornite") || type.equals("brimstone")) {
			this.setHardness(3.0F);
			this.setResistance(5.0F);
		} else if (type.equals("phosphate")) {
			this.setHardness(2.5F);
			this.setResistance(5.0F);
			this.setLightValue(0.75F);
		} else if (type.equals("volucite")) {
			this.setHardness(50.0F);
			this.setResistance(6000000.0F);
		}
	}

	@Override
	public int idDropped(int i, Random random, int j) {
		if (this.blockID == ModOres.oreVolucite.blockID) {
			return ModItems.etherium.itemID;
		} else {
			return this.blockID;
		}
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f, int m) {
		super.dropBlockAsItemWithChance(world, i, j, k, l, f, m);
	}
}