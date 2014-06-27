package steamcraft.common.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import boilerplate.client.ClientHelper;
import boilerplate.common.utils.StringUtils;

public class ItemBlockWithDesc extends ItemBlock
{
	Block block;
	boolean descNeedsShift = true;

	public ItemBlockWithDesc(Block block)
	{
		super(block);
		this.block = block;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (!StatCollector.translateToLocal(block.getUnlocalizedName() + ".desc").contains("tile."))
			if (descNeedsShift)
			{
				if (ClientHelper.isShiftKeyDown())
					getWrappedDesc(list);
				else
					list.add(ClientHelper.shiftForInfo);
			}
			else
				getWrappedDesc(list);
	}

	public void getWrappedDesc(List<String> list)
	{
		String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(getUnlocalizedName() + ".desc"), 30);
		for (String element : wrappedDesc)
			list.add(element.trim());
	}
}
