/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/18/14, 11:43]
 */
package steamcraft.common.blocks;

/**
 * @author Surseance (Johnny Eatmon)
 *
 public class BlockTeaPlant extends BlockBush implements IPlantable
 {
 @Override
 @SideOnly(Side.CLIENT)
 public IIcon getIcon(int i, int j)
 {
 if(j < 0 || j > 1)
 {
 j = 1;
 }

 return this.iconArray[j];
 }

 @Override
 @SideOnly(Side.CLIENT)
 public void registerIcons(IIconRegister icon)
 {
 this.iconArray = new IIcon[2];

 for(int i = 0; i < this.iconArray.length; ++i)
 {
 this.iconArray[i] = icon.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5) + "_stage_" + i);
 }
 }

 public BlockTeaPlant(int id)
 {
 super(Material.plants);
 this.setHardness(0.0F);
 this.setResistance(0.0F);
 this.setStepSound(Block.soundTypeGrass);
 this.setTickRandomly(true);
 this.setCreativeTab((CreativeTabs)null);
 this.disableStats();
 this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
 }

 @Override
 protected boolean canPlaceBlockOn(Block block)
 {
 return super.canPlaceBlockOn(block); // Might add a mechanical farming device later down the road
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

 if (random.nextInt((int)(25.0F / growthRate) + 1) == 0)
 {
 ++metadata;
 world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
 }
 }
 }
 }

 public void fertilize(World world, int x, int y, int z)
 {
 int metaRNG = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

 if (metaRNG > 1)
 {
 metaRNG = 1;
 }

 world.setBlockMetadataWithNotify(x, y, z, metaRNG, 2);
 }

 private float getGrowthRate(World world, int x, int y, int z)
 {
 float f = 1.0F;
 Block block1 = world.getBlock(x, y, z - 1);
 Block block2 = world.getBlock(x, y, z + 1);
 Block block3 = world.getBlock(x - 1, y, z);
 Block block4 = world.getBlock(x + 1, y, z);
 Block block5 = world.getBlock(x - 1, y, z - 1);
 Block block6 = world.getBlock(x + 1, y, z - 1);
 Block block7 = world.getBlock(x + 1, y, z + 1);
 Block block8 = world.getBlock(x - 1, y, z + 1);
 boolean flag = j1 == this.blockID || k1 == this.blockID;
 boolean flag1 = l == this.blockID || i1 == this.blockID;
 boolean flag2 = l1 == this.blockID || i2 == this.blockID || j2 == this.blockID || k2 == this.blockID;

 for(int l2 = x - 1; l2 <= x + 1; ++l2)
 {
 for(int i3 = z - 1; i3 <= z + 1; ++i3)
 {
 int j3 = world.getBlockId(l2, y - 1, i3);
 float f1 = 0.0F;

 if(blocksList[j3] != null && blocksList[j3].canSustainPlant(world, l2, y - 1, i3, ForgeDirection.UP, this))
 {
 f1 = 1.0F;

 if(blocksList[j3].isFertile(world, l2, y - 1, i3))
 {
 f1 = 3.0F;
 }
 }
 if(l2 != x || i3 != z)
 {
 f1 /= 4.0F;
 }

 f += f1;
 }
 }
 if(flag2 || flag && flag1)
 {
 f /= 2.0F;
 }

 return f;
 }

 @Override
 public int getRenderType()
 {
 return 6;
 }

 protected Item getSeedItem()
 {
 return Items.apple; // TODO:
 }

 protected Item getCropItem()
 {
 return Items.item_frame;
 }

 @Override
 public void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f, int m)
 {
 super.dropBlockAsItemWithChance(world, i, j, k, l, f, 0);
 }

 @Override
 public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
 {
 ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

 if (metadata >= 1)
 {
 for (int n = 0; n < 3 + fortune; n++)
 {
 if (world.rand.nextInt(3) <= metadata)
 {
 ret.add(new ItemStack(this.getSeedItem(), 1, 0));
 }
 }
 }

 return ret;
 }

 /*
 @Override
 public int idDropped(int i, Random random, int j)
 {
 return i == 1 ? this.getCropItem() : this.getSeedItem();
 }


 @Override
 public int quantityDropped(Random par1Random)
 {
 return 1;
 }
 }*/
