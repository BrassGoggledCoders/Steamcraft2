
package steamcraft.common.worldgen.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureUndercityStart extends StructureStart
{

	public StructureUndercityStart()
	{
	}

	public StructureUndercityStart(World world, Random rand, int chunkX, int chunkZ)
	{
		super(chunkX, chunkZ);
		StructureUndercityPieces.Room room = new StructureUndercityPieces.Room(0, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
		this.components.add(room);
		room.buildComponent(room, this.components, rand);
		this.updateBoundingBox();
		this.markAvailableHeight(world, rand, 10);
	}
}