/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.init.InitItems;
import steamcraft.common.lib.DamageSourceHandler;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 * 
 */
public class ItemResource extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[7];

	public ItemResource()
	{
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon[0] = ir.registerIcon(ModInfo.PREFIX + "itemCrystal");
		this.itemIcon[1] = ir.registerIcon(ModInfo.PREFIX + "itemChemSalt");
		this.itemIcon[2] = ir.registerIcon(ModInfo.PREFIX + "itemSlate");
		this.itemIcon[3] = ir.registerIcon(ModInfo.PREFIX + "itemPhosphorus");
		this.itemIcon[4] = ir.registerIcon(ModInfo.PREFIX + "itemUranium");
		this.itemIcon[5] = ir.registerIcon(ModInfo.PREFIX + "itemPellet");
		this.itemIcon[6] = ir.registerIcon(ModInfo.PREFIX + "itemCrystalShard");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		for(int var4 = 0; var4 < this.itemIcon.length; ++var4)
			l.add(new ItemStack(InitItems.itemResource, 1, var4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return this.itemIcon[itemDamage];
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity holder, int p_77663_4_, boolean p_77663_5_)
	{
		if(!world.isRemote && (stack.getItemDamage() == 5))
		{
			holder.attackEntityFrom(DamageSourceHandler.radioative, 1);
		}
	}

}
