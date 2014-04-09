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

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class BlockCustomOre extends Block
{
    private Icon[] icon = new Icon[8];

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
        return this.icon[metadata];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
        this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "blockOreBornite");
        this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "blockOreBrimstone");
        this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "blockOrePhosphate");
        this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "blockOreUranite");
        this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "blockOreAluminum");
        this.icon[5] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "blockOreCopper");
        this.icon[6] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "blockOreTin");
        this.icon[7] = ir.registerIcon(LibInfo.PREFIX + "ore/" + "blockOreZinc");
    }

    public BlockCustomOre(int id)
    {
        super(id, Material.rock);
        this.setHardness(3.0F);
        this.setResistance(6.0F);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("blockOre");
        this.setTickRandomly(true);
        this.setCreativeTab(Steamcraft.tabSC2);
    }

    @Override
    public int damageDropped(int id)
    {
        return id;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tabs, List list)
    {
        list.add(new ItemStack(id, 1, 0));
        list.add(new ItemStack(id, 1, 1));
        list.add(new ItemStack(id, 1, 2));
        list.add(new ItemStack(id, 1, 3));
        list.add(new ItemStack(id, 1, 4));
        list.add(new ItemStack(id, 1, 5));
        list.add(new ItemStack(id, 1, 6));
        list.add(new ItemStack(id, 1, 7));
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList drop = new ArrayList();

        switch (metadata) {
            case 0:
                drop.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 0));
            case 1:
                drop.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 1));
            case 2:
                drop.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 2));
            case 3:
                drop.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 3));
            case 4:
                drop.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 4));
            case 5:
                drop.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 5));
            case 6:
                drop.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 6));
            case 7:
                drop.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 7));
        }

        return drop;
    }
}
