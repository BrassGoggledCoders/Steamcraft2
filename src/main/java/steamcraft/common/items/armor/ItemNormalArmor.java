
package steamcraft.common.items.armor;

import boilerplate.common.baseclasses.items.BaseArmor;
import steamcraft.common.Steamcraft;

/**
 * @author Decebaldecebal
 *
 */
public class ItemNormalArmor extends BaseArmor
{
	public ItemNormalArmor(ArmorMaterial mat, int type, String textureName)
	{
		super(mat, type, textureName);
		this.setCreativeTab(Steamcraft.tabSC2);
	}
}
