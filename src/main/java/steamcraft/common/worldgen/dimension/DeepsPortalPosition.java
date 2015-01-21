package steamcraft.common.worldgen.dimension;

import net.minecraft.util.ChunkCoordinates;

public class DeepsPortalPosition extends ChunkCoordinates
{
	public long field_85087_d;
	final TeleporterDeeps field_85088_e;

	public DeepsPortalPosition(TeleporterDeeps teleporterDeeps, int par2, int par3, int par4, long par5)
	{
		super(par2, par3, par4);
		this.field_85088_e = teleporterDeeps;
		this.field_85087_d = par5;
	}
}
