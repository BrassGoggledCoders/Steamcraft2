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
 * File created @ [9 Apr 2014, 09:43:13]
 */
package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class BlockMetal extends Block
{
	private IIcon[] icon = new IIcon[9];

	private boolean powered;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
        this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockBrass");
        this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockBronze");
        this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockSteel");
        this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockUranium");
        this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockAluminum");
        this.icon[5] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockCopper");
        this.icon[6] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockTin");
        this.icon[7] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockZinc");
        this.icon[8] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockEtherium");
	}
	public BlockMetal()
	{
		super(Material.iron);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeMetal);
		//this.setUnlocalizedName("blockMetal");
		this.setTickRandomly(true);
		this.setCreativeTab(Steamcraft.tabSC2);

		if (this.powered)
		{
			this.setLightLevel(0.98F);
		}
	}

	@Override
	public int damageDropped(int id)
	{
		return id;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if ((world.getBlock(x, y, z) == this) && (world.getBlockMetadata(x, y, z) == 4))
			entity.attackEntityFrom(DamageSource.magic, 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if ((world.getBlock(x, y, z) == this) && (world.getBlockMetadata(x, y, z) == 4))
			Utils.sparkle(world, x, y, z, "reddust");
	}
}
