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
 * File created @ [3/15/14, 13:40]
 */
package steamcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.tiles.TileCrystal;

/**
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCrystal extends BlockContainer implements ITileEntityProvider
{
    public BlockCrystal(int id)
    {
        super(id, Material.glass);
        this.setHardness(8.5F);
        this.setResistance(6000000.0F);
        this.setStepSound(Block.soundGlassFootstep);
        this.setCreativeTab(Steamcraft.tabSC2);
        setUnlocalizedName("blockCrystal");
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileCrystal();
    }

    @Override
    public int getRenderType()
    {
        return ConfigBlocks.blockCrystalRI;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int metadata, EffectRenderer effectRenderer)
    {
        return super.addBlockDestroyEffects(world, x, y, z, metadata, effectRenderer);
    }

    /*
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
    {
        return null;
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
    }*/

    @Override
    public int tickRate(World world)
    {
        return 10;
    }
}
