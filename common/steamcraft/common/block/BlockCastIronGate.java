package common.steamcraft.common.block;

import common.steamcraft.common.lib2.CreativeTabsMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockCastIronGate extends BlockFenceGate
{
    public BlockCastIronGate(int id, Material mat)
    {
        super(id);
        this.setHardness(7F);
        this.setResistance(20F);
        this.setStepSound(Block.soundMetalFootstep);
        this.setUnlocalizedName("castirongate");
        this.setCreativeTab(CreativeTabsMod.tabSCBlocks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int i, int j)
    {
        return ModBlocks.blockCastIron.getBlockTextureFromSide(i);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {}
}