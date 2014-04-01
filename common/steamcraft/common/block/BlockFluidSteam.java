package common.steamcraft.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import common.steamcraft.common.lib2.CreativeTabsMod;
import common.steamcraft.common.lib2.LibInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidSteam extends BlockFluidClassic 
{

    @SideOnly(Side.CLIENT)
    protected Icon stillIcon;
    @SideOnly(Side.CLIENT)
    protected Icon flowingIcon;
    
    public BlockFluidSteam(Fluid fluid, Material material) 
    {
            super(1000, fluid, material);
            setUnlocalizedName("steamFluidBlock");
            setCreativeTab(CreativeTabsMod.tabSCBlocks);
    }
    
    @Override
    public Icon getIcon(int side, int meta) 
    {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) 
    {
            stillIcon = register.registerIcon(LibInfo.SC2_PREFIX + "steamStill");
            flowingIcon = register.registerIcon(LibInfo.SC2_PREFIX + "steamFlowing");
    }
    
    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) 
    {
            if (world.getBlockMaterial(x,  y,  z).isLiquid()) 
            	return false;
            return 
            	super.canDisplace(world, x, y, z);
    }
    
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) 
    {
            if (world.getBlockMaterial(x,  y,  z).isLiquid())
            	return false;
            return 
            	super.displaceIfPossible(world, x, y, z);
    }
}
