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
 * File created @ [3/18/14, 12:17]
 */
package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;

/**
 * @author Surseance (Johnny Eatmon)
 */
public class ItemResource extends Item
{
    public IIcon[] icon = new IIcon[12];

    @Override
    public IIcon getIconFromDamage(int itemDamage)
    {
        return this.icon[itemDamage];
    }

    @Override
    public void registerIcons(IIconRegister ir)
    {
        this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "itemCrystal");
        this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "itemChemSalt");
        this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "itemBornite");
        this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "itemSlate");
        this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "itemBrass");
        this.icon[5] = ir.registerIcon(LibInfo.PREFIX + "itemCastIron");
        this.icon[6] = ir.registerIcon(LibInfo.PREFIX + "itemBulb");
        this.icon[7] = ir.registerIcon(LibInfo.PREFIX + "itemPhosphorus");
        this.icon[8] = ir.registerIcon(LibInfo.PREFIX + "itemUranium");
        this.icon[9] = ir.registerIcon(LibInfo.PREFIX + "itemPellet");
        this.icon[10] = ir.registerIcon(LibInfo.PREFIX + "itemReactorCore");
        this.icon[11] = ir.registerIcon(LibInfo.PREFIX + "itemTeaLeaves");
    }

    public ItemResource()
    {
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(Steamcraft.tabSC2);
    }
}
