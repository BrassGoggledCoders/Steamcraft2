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
package common.steamcraft.mod.common.block;

import common.steamcraft.mod.client.core.helper.IconHelper;
import common.steamcraft.mod.common.item.ModItems;
import common.steamcraft.mod.common.lib.CreativeTabsMod;
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
		if (this.blockID == ModOres.oreBornite.blockID) {
			return ModOres.oreBornite.blockID;
		} else if (this.blockID == ModOres.oreBrimstone.blockID) {
			return ModOres.oreBrimstone.blockID;
		} else if (this.blockID == ModOres.orePhosphate.blockID) {
			return ModOres.orePhosphate.blockID;
		} else if (this.blockID == ModOres.oreVolucite.blockID) {
			return ModItems.etherium.itemID;
		} else {
			return 0;
		}
	}

	@Override
	public int quantityDroppedWithBonus(int quantity, Random random) {
		if (this.blockID != ModOres.oreVolucite.blockID) {
			if (quantity > 0 && this.blockID != this.idDropped(0, random, quantity)) {
				int randInt = random.nextInt(quantity + 2) - 1;

				if (randInt < 0) {
					randInt = 0;
				}

				return this.quantityDropped(random) * (randInt + 1);
			} else {
				return this.quantityDropped(random);
			}
		} else {
			return 1;
		}
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f, int m) {
		super.dropBlockAsItemWithChance(world, i, j, k, l, f, m);
	}

	@Override
	public int getExpDrop(World world, int i, int j) {
		if (this.idDropped(i, world.rand, j) != this.blockID) {
			int randInt = 0;

			if (this.blockID == ModOres.oreVolucite.blockID) {
				randInt = MathHelper.getRandomIntegerInRange(world.rand, 4, 10);
			} 

			return randInt;
		}

		return 0;
	}
}