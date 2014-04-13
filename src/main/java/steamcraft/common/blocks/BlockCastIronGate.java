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
 * File created @ [3/15/14, 11:32]
 */
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.util.Icon;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCastIronGate extends BlockFenceGate
{
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
        return ConfigBlocks.blockCastIronFence.getBlockTextureFromSide(side);
    }

    public BlockCastIronGate(int id)
    {
    	super(id);
        this.setHardness(7.0F);
        this.setResistance(20.0F);
        this.setStepSound(Block.soundMetalFootstep);
        this.setCreativeTab(Steamcraft.tabSC2);
        setUnlocalizedName("blockCastIronGate");
    }
}
