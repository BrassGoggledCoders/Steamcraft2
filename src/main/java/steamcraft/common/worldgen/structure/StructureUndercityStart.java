package steamcraft.common.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureUndercityStart extends StructureStart
{
	private static final String __OBFID = "CL_00000450";

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