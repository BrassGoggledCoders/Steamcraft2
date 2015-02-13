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
package steamcraft.common.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureUndercityStart extends StructureStart
{

	public StructureUndercityStart()
	{
	}

	public StructureUndercityStart(World p_i2039_1_, Random p_i2039_2_, int p_i2039_3_, int p_i2039_4_)
	{
		super(p_i2039_3_, p_i2039_4_);
		StructureUndercityPieces.Room room = new StructureUndercityPieces.Room(0, p_i2039_2_, (p_i2039_3_ << 4) + 2, (p_i2039_4_ << 4) + 2);
		this.components.add(room);
		room.buildComponent(room, this.components, p_i2039_2_);
		this.updateBoundingBox();
		this.markAvailableHeight(p_i2039_1_, p_i2039_2_, 10);
	}
}