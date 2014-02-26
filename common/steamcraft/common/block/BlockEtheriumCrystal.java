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
 * File created @ [Feb 17, 2014, 10:07:13 AM]
 */
package common.steamcraft.common.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import common.steamcraft.common.block.machines.BlockContainerMod;
import common.steamcraft.common.block.tile.TileEntityEtheriumCrystal;
import common.steamcraft.common.item.ModItems;
import common.steamcraft.common.lib2.CreativeTabsMod;

/**
 * @author MrArcane111
 *
 */
public class BlockEtheriumCrystal extends BlockContainerMod {
	public BlockEtheriumCrystal(int id) {
		super(id, Material.glass);
		this.setHardness(50.0F);
		this.setResistance(6000000.0F);
		this.setCreativeTab(CreativeTabsMod.tabSCBlocks);
	}

	@Override
	public int tickRate(World world) {
		return 10;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityEtheriumCrystal();
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return null;
    }
	
	@Override
	public int idDropped(int i, Random random, int j) {
		return ModItems.etherium.itemID;
		
	}

	@Override
	public int getExpDrop(World world, int i, int j) {
		if (this.idDropped(i, world.rand, j) != this.blockID) {
			int randInt = MathHelper.getRandomIntegerInRange(world.rand, 4, 10);
			return randInt;
		}

		return 0;
	}

	@Override
	public int quantityDroppedWithBonus(int quantity, Random random) {
		if (quantity > 0 && (this.blockID != this.idDropped(0, random, quantity))) {
			int randInt = random.nextInt(quantity + 2) - 1;

			if (randInt < 0) {
				randInt = 0;
			}

			return this.quantityDropped(random) * (randInt + 1);
		} else {
			return this.quantityDropped(random);
		}
	}
}	
