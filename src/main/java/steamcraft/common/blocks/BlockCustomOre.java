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
 * File created @ [Mar 14, 2014, 3:22:57 PM]
 */
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class BlockCustomOre extends Block
{
    private IIcon[] icon = new IIcon[8];

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return this.icon[metadata];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir)
    {
        this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreBornite");
        this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreBrimstone");
        this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "orePhosphate");
        this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreUranite");
        this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreAluminum");
        this.icon[5] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreCopper");
        this.icon[6] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreTin");
        this.icon[7] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "oreZinc");
    }

    public BlockCustomOre()
    {
        super(Material.rock);
        this.setHardness(3.0F);
        this.setResistance(6.0F);
        this.setStepSound(Block.soundTypeStone);
        //this.setUnlocalizedName("blockOre");
        this.setTickRandomly(true);
        this.setCreativeTab(Steamcraft.tabSC2);
    }

    @Override
    public int damageDropped(int id)
    {
        return id;
    }
}
