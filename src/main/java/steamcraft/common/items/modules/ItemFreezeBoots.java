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
package steamcraft.common.items.modules;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.ForgeDirection;

import steamcraft.api.item.ModuleRegistry;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ItemFreezeBoots extends BaseArmorModule
{
	public ItemFreezeBoots()
	{
		super();
		ModuleRegistry.registerModule(this);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public int getApplicablePiece()
	{
		return 3;
	}

	@Override
	public String getName()
	{
		return "Freeze Boots";
	}

	@Override
	public String getModuleId()
	{
		return "freezeboots";
	}

	@Override
	public void applyModuleEffect(World world, EntityPlayer player, ItemStack stack)
	{
		int pX = (int) Math.round(player.posX);
		int pY = (int) Math.round(player.posY) - 1;
		int pZ = (int) Math.round(player.posZ);
		if (world.getBlock(pX, pY, pZ).getMaterial() == Material.water)
		{
			world.setBlock(pX, pY, pZ, InitBlocks.blockMeltingIce);
		}
		for (int i = 2; i < ForgeDirection.VALID_DIRECTIONS.length; i++)
		{
			ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[i];
			if (world.getBlock(pX + dir.offsetX, pY + dir.offsetY, pZ + dir.offsetZ).getMaterial() == Material.water)
			{
				world.setBlock(pX + dir.offsetX, pY + dir.offsetY, pZ + dir.offsetZ, InitBlocks.blockMeltingIce);
			}
		}
	}

	@Override
	public EnumArmorEffectType getArmorEffectType()
	{
		return EnumArmorEffectType.ONTICK;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}
}
