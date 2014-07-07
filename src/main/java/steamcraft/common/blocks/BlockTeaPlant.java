/**
 * This class was created by <Surseance> or his SC1 development team.
 * This class is available as part of the Steamcraft 1 Mod for Minecraft.
 *
 * Steamcraft 1 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 1 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 1011
 * (http://www.minecraftforum.net/topic/151531-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/18/14, 11:43]
 */
package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 */
 public class BlockTeaPlant extends BlockCrops implements IPlantable
 {
	 IIcon[] iconArray = new IIcon[2];

	 @Override
	 @SideOnly(Side.CLIENT)
	 public IIcon getIcon(int side, int meta)
	 {
		   if (meta < 0 || meta > 1)
	        {
	            meta = 1;
	        }
		 return iconArray[meta];
	 }

	 @Override
	 @SideOnly(Side.CLIENT)
	 public void registerBlockIcons(IIconRegister ir)
	 {
		 this.iconArray[0] = ir.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5) + "_0");
		 this.iconArray[1] = ir.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5) + "_1");
	 }

	 public BlockTeaPlant()
	 {
		 super();
		 this.setHardness(0.0F);
		 this.setResistance(0.0F);
		 this.setStepSound(Block.soundTypeGrass);
		 this.setTickRandomly(true);
		 this.disableStats();
		 this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.15F, 1.0F);
	 }

	 @Override
	 public void updateTick(World world, int x, int y, int z, Random random)
	 {
		 super.updateTick(world, x, y, z, random);

		 if (world.getBlockLightValue(x, y + 1, z) >= 9)
		 {
		 int metadata = world.getBlockMetadata(x, y, z);

		 if (metadata < 1)
		 {
		 float growthRate = this.getGrowthRate(world, x, y, z);

		 if (random.nextInt((int)(15.0F / growthRate) + 1) == 0)
		 {
		 ++metadata;
		 world.setBlockMetadataWithNotify(x, y, z, metadata, 1);
		 }
		 }
		 }
	 }

	 public void fertilize(World world, int x, int y, int z)
	 {
		 world.setBlockMetadataWithNotify(x, y, z, 1, 2);
	 }

	 private float getGrowthRate(World world, int x, int y, int z)
	 {
		 float f = 1.0F;

		 for(int l1 = x - 1; l1 <= x + 1; ++l1)
		 {
		 for(int i3 = z - 1; i3 <= z + 1; ++i3)
		 {
		 Block block = world.getBlock(l1, y - 1, i3);
		 float growthRate = 0.0F;

		 if(block.canSustainPlant(world, l1, y - 1, i3, ForgeDirection.UP, this))
		 {
		 growthRate = 1.0F;

		 if(block.isFertile(world, l1, y - 1, i3))
		 {
		 growthRate = 3.0F;
		 }
		 }
		 if(l1 != x || i3 != z)
		 {
		 growthRate /= 4.0F;
		 }

		 f += growthRate;
		 }
	 }

	 return f;
	 }

	 @Override
	 public int getRenderType()
	 {
		 return 6;
	 }
	 @Override
	 protected Item func_149866_i()
	 {
		 return ConfigItems.itemTeaSeed;
	 }
	 @Override
	 protected Item func_149865_P()
	 {
		 return ConfigItems.itemTeaLeaf;
	 }
	 @Override
	 public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_)
	 {
		 return meta != 0 ? this.func_149865_P() : this.func_149866_i();
	 }
}