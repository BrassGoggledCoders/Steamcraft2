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
package steamcraft.common.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author Surseance
 * 
 */
public class ItemModHoe extends ItemModTool
{
	public ItemModHoe(ToolMaterial toolMat)
	{
		super(1F, toolMat, blocksEffectiveAgainst);
		maxStackSize = 1;
		setMaxDamage(toolMat.getMaxUses());
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int l, float f1, float f2, float f3)
	{
		if (player.canPlayerEdit(x, y, z, l, stack))
			if (isSteampowered())
			{
				NBTTagCompound tag = stack.getTagCompound();
				if (tag.getBoolean("hasCanister"))
				{
					executeHoeAction(stack, player, world, x, y, z, l, f1, f2, f3);
					return true;
				}
			}
			else
			{
				executeHoeAction(stack, player, world, x, y, z, l, f1, f2, f3);
				return true;
			}
		return false;
	}

	private void executeHoeAction(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int l, float f1, float f2, float f3)
	{
		Block i1 = world.getBlock(i, j, k);

		if (((l != 0) && world.isAirBlock(i, j, k) && (i1 == Blocks.grass)) || (i1 == Blocks.dirt))
		{
			Block block = Blocks.farmland;
			world.playSoundEffect(i + 0.5F, j + 0.5F, k + 0.5F, block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F,
					block.stepSound.getPitch() * 0.8F);

			if (!world.isRemote)
			{
				world.setBlock(i, j, k, block);

				stack.damageItem(1, player);
			}
		}
	}
}