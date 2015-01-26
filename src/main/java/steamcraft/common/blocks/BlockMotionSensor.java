package steamcraft.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.TileMotionSensor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMotionSensor extends BlockContainer
{
	public boolean isOn;

	public BlockMotionSensor(Material p_i45386_1_, boolean isOn)
	{
		super(p_i45386_1_);
		this.isOn = isOn;
		if(!isOn)
			this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileMotionSensor();
	}

	public boolean isIndirectlyPoweringTo(World par1World, int par2, int par3, int par4, int par5)
	{
		return this.isOn;
	}

	@Override
	public boolean canProvidePower()
	{
		return this.isOn;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
	{
		if(this.isOn)
			return 15;
		else
			return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

}
