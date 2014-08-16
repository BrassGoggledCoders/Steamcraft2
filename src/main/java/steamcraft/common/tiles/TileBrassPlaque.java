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
package steamcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.tileentity.TileEntitySign;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileBrassPlaque extends TileEntitySign
{
	/** An array of four strings storing the lines of text on the sign. */
	public String[] signText = new String[] { "", "", "", "" };
	/**
	 * The index of the line currently being edited. Only used on client side,
	 * but defined on both. Note this is only really used when the > < are going
	 * to be visible.
	 */
	public int lineBeingEdited = -1;
	private boolean field_145916_j = true;
	private EntityPlayer field_145917_k;

	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_)
	{
		super.writeToNBT(p_145841_1_);
		p_145841_1_.setString("Text1", this.signText[0]);
		p_145841_1_.setString("Text2", this.signText[1]);
		p_145841_1_.setString("Text3", this.signText[2]);
		p_145841_1_.setString("Text4", this.signText[3]);
	}

	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_)
	{
		this.field_145916_j = false;
		super.readFromNBT(p_145839_1_);

		for (int i = 0; i < 4; ++i)
		{
			this.signText[i] = p_145839_1_.getString("Text" + (i + 1));

			if (this.signText[i].length() > 15)
				this.signText[i] = this.signText[i].substring(0, 15);
		}
	}

	/**
	 * Overriden in a sign to provide the text.
	 */
	@Override
	public Packet getDescriptionPacket()
	{
		String[] astring = new String[4];
		System.arraycopy(this.signText, 0, astring, 0, 4);
		return new S33PacketUpdateSign(this.xCoord, this.yCoord, this.zCoord, astring);
	}

	@Override
	public boolean func_145914_a()
	{
		return this.field_145916_j;
	}

	/**
	 * Sets the sign's isEditable flag to the specified parameter.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void setEditable(boolean p_145913_1_)
	{
		this.field_145916_j = p_145913_1_;

		if (!p_145913_1_)
			this.field_145917_k = null;
	}

	@Override
	public void func_145912_a(EntityPlayer p_145912_1_)
	{
		this.field_145917_k = p_145912_1_;
	}

	@Override
	public EntityPlayer func_145911_b()
	{
		return this.field_145917_k;
	}
}
