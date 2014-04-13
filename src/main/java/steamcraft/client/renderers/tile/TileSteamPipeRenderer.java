/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 13, 2014, 2:19:03 PM]
 */
package steamcraft.client.renderers.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import steamcraft.api.machines.ModelCustomBox;
import steamcraft.api.machines.ModelCustomRenderer;
import steamcraft.common.tiles.TileSteamPipe;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class TileSteamPipeRenderer extends TileEntitySpecialRenderer
{
	private ModelCustomRenderer straightTube;
	private ModelCustomRenderer curvedTube;
	private ModelCustomRenderer joint;

	public TileSteamPipeRenderer()
	{
		this.straightTube = new ModelCustomRenderer();
		ModelCustomBox Tube = new ModelCustomBox(0, 0, 256, 256, -4.0F, -4.0F, -8.0F, 8, 8, 16, 0.0F);
		Tube.setQuadTexCoords(0, 176.0D, 32.0D, 184.0D, 48.0D);
		Tube.rotateQuad(0, 1);
		Tube.setQuadTexCoords(1, 176.0D, 32.0D, 184.0D, 48.0D);
		Tube.rotateQuad(1, 1);
		Tube.setQuadTexCoords(2, 176.0D, 32.0D, 184.0D, 48.0D);
		Tube.setQuadTexCoords(3, 176.0D, 32.0D, 184.0D, 48.0D);
		Tube.shouldRenderQuad(4, false);
		Tube.shouldRenderQuad(5, false);
		this.straightTube.addBox(Tube);
		this.curvedTube = new ModelCustomRenderer();
		Tube = new ModelCustomBox(0, 0, 256, 256, -4.0F, -4.0F, -8.0F, 8, 8, 12, 0.0F);
		Tube.setQuadTexCoords(0, 184.0D, 32.0D, 192.0D, 44.0D);
		Tube.rotateQuad(0, 3);
		Tube.setQuadTexCoords(1, 184.0D, 44.0D, 192.0D, 32.0D);
		Tube.rotateQuad(1, 3);
		Tube.setQuadTexCoords(2, 176.0D, 32.0D, 184.0D, 48.0D);
		Tube.rotateQuad(2, 2);
		Tube.setQuadTexCoords(3, 176.0D, 32.0D, 184.0D, 48.0D);
		Tube.shouldRenderQuad(4, false);
		Tube.setQuadTexCoords(5, 176.0D, 36.0D, 184.0D, 48.0D);
		this.curvedTube.addBox(Tube);
		Tube = new ModelCustomBox(0, 0, 256, 256, -4.0F, 4.0F, -4.0F, 8, 4, 8, 0.0F);
		Tube.setQuadTexCoords(0, 192.0D, 32.0D, 196.0D, 40.0D);
		Tube.rotateQuad(0, 3);
		Tube.setQuadTexCoords(1, 192.0D, 32.0D, 196.0D, 40.0D);
		Tube.rotateQuad(1, 1);
		Tube.shouldRenderQuad(2, false);
		Tube.shouldRenderQuad(3, false);
		Tube.setQuadTexCoords(4, 176.0D, 44.0D, 184.0D, 48.0D);
		Tube.rotateQuad(4, 2);
		Tube.setQuadTexCoords(5, 176.0D, 32.0D, 184.0D, 36.0D);
		this.curvedTube.addBox(Tube);
		this.joint = new ModelCustomRenderer();
		ModelCustomBox JointBox = new ModelCustomBox(0, 0, 256, 256, -6.0F, -6.0F, -8.0F, 12, 12, 2, 0.0F);
		JointBox.setQuadTexCoords(0, 196.0D, 43.0D, 208.0D, 45.0D);
		JointBox.rotateQuad(0, 1);
		JointBox.setQuadTexCoords(1, 196.0D, 43.0D, 208.0D, 45.0D);
		JointBox.rotateQuad(1, 1);
		JointBox.setQuadTexCoords(2, 196.0D, 43.0D, 208.0D, 45.0D);
		JointBox.setQuadTexCoords(3, 196.0D, 43.0D, 208.0D, 45.0D);
		JointBox.setQuadTexCoords(4, 196.0D, 32.0D, 208.0D, 44.0D);
		JointBox.setQuadTexCoords(5, 196.0D, 32.0D, 208.0D, 44.0D);
		this.joint.addBox(JointBox);
	}

	public void renderInventory()
	{
		//ForgeHooksClient.bindTexture("/deatrathias/cogs/img/blocks.png", 0);

		render(2, 3, null);
	}

	public void render(int side1, int side2, TileSteamPipe pipe)
	{
		boolean straight = false;

		if (side1 == ForgeDirection.OPPOSITES[side2])
		{
			straight = true;

			if (side1 == 1)
			{
				this.straightTube.rotateAngleX = 90.0F;
			}
			else 
			{
				this.straightTube.rotateAngleX = 0.0F;
			}
			if (side1 == 5)
			{
				this.straightTube.rotateAngleY = 90.0F;
			}
			else
			{
				this.straightTube.rotateAngleY = 0.0F;
			}

			this.straightTube.rotateAngleZ = 0.0F;
			this.straightTube.render(0.0625F);
		}
		else
		{
			this.curvedTube.rotateAngleX = 0.0F;
			this.curvedTube.rotateAngleY = 0.0F;
			this.curvedTube.rotateAngleZ = 0.0F;

			if ((side2 == 0) || (side2 == 1))
			{
				if (side1 == 4)
				{
					this.curvedTube.rotateAngleY = 90.0F;
				}
				else if (side1 == 5)
				{
					this.curvedTube.rotateAngleY = -90.0F;
				}
				else if (side1 == 3) 
				{
					this.curvedTube.rotateAngleY = 180.0F;
				}
				if (side2 == 0)
				{
					this.curvedTube.rotateAngleX = 180.0F;
					this.curvedTube.rotateAngleY -= 180.0F;
				}
			}
			else
			{
				this.curvedTube.rotateAngleZ = 90.0F;

				if (side2 == 3)
				{
					this.curvedTube.rotateAngleX = 90.0F;
				}
				if (side1 == 5) 
				{
					this.curvedTube.rotateAngleZ = -90.0F;
				}
			}

			this.curvedTube.render(0.0625F);
		}

		renderJoint(side1, straight, pipe);
		renderJoint(side2, straight, pipe);
	}

	private void renderJoint(int side, boolean straight, TileSteamPipe pipe)
	{
		if ((straight) && (pipe != null))
		{
			ForgeDirection direction = ForgeDirection.getOrientation(side);
			TileEntity te = pipe.worldObj.getBlockTileEntity(pipe.xCoord + direction.offsetX, pipe.yCoord + direction.offsetY, pipe.zCoord + direction.offsetZ);

			if ((te != null) && ((te instanceof TileSteamPipe)))
			{
				TileSteamPipe otherPipe = (TileSteamPipe)te;

				if ((otherPipe.getConnection1() == ForgeDirection.OPPOSITES[side]) || (otherPipe.getConnection2() == ForgeDirection.OPPOSITES[side]))
				{
					return;
				}
			}
		}

		this.joint.rotateAngleX = 0.0F;
		this.joint.rotateAngleY = 0.0F;

		switch (side) {
		case 0:
			this.joint.rotateAngleX = -90.0F;
			break;
		case 1:
			this.joint.rotateAngleX = 90.0F;
			break;
		case 3:
			this.joint.rotateAngleY = 180.0F;
			break;
		case 4:
			this.joint.rotateAngleY = 90.0F;
			break;
		case 5:
			this.joint.rotateAngleY = -90.0F;
		case 2:
		}

		this.joint.render(0.0625F);
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		TileSteamPipe pipe = (TileSteamPipe)te;

		if (!pipe.loaded) 
		{
			return;
		}
		
		GL11.glPushMatrix();
		GL11.glEnable(32826);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
		render(pipe.getConnection1(), pipe.getConnection2(), pipe);
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}
}
