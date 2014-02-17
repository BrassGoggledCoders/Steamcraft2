/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 17, 2014, 12:08:13 PM]
 */
package common.steamcraft.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
/**
 * @author MrArcane111
 *
 */
public class ModelCustomRenderer {
	public float rotationPointX;
	public float rotationPointY;
	public float rotationPointZ;
	public float rotateAngleX;
	public float rotateAngleY;
	public float rotateAngleZ;
	private boolean compiled;
	private int displayList;
	public boolean mirror;
	public boolean showModel;
	public boolean isHidden;
	public List cubeList;
	public List childModels;
	public final String boxName;

	public ModelCustomRenderer(String name) {
		this.compiled = false;
		this.displayList = 0;
		this.mirror = false;
		this.showModel = true;
		this.isHidden = false;
		this.cubeList = new ArrayList();
		this.boxName = name;
	}

	public ModelCustomRenderer() {
		this((String)null);
	}

	public void addChild(ModelCustomRenderer modelRenderer) {
		if (this.childModels == null) {
			this.childModels = new ArrayList();
		}

		this.childModels.add(modelRenderer);
	}

	public ModelCustomRenderer addBox(ModelCustomBox box) {
		this.cubeList.add(box);
		return this;
	}

	public void setRotationPoint(float par1, float par2, float par3) {
		this.rotationPointX = par1;
		this.rotationPointY = par2;
		this.rotationPointZ = par3;
	}

	Iterator iterator = null;
	
	public void render(float f) {
		if (!this.isHidden) {
			if (this.showModel) {
				if (!this.compiled) {
					compileDisplayList(f);
				}
				if ((this.rotateAngleX == 0.0F) && (this.rotateAngleY == 0.0F) && (this.rotateAngleZ == 0.0F)) {
					if ((this.rotationPointX == 0.0F) && (this.rotationPointY == 0.0F) && (this.rotationPointZ == 0.0F)) {
						GL11.glCallList(this.displayList);

						if (this.childModels == null) {
							return;
						}

						iterator = this.childModels.iterator();
					}
				}	

				while (iterator.hasNext()) {
					ModelCustomRenderer modelRenderer = (ModelCustomRenderer)iterator.next();
					modelRenderer.render(f); 
					GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
					GL11.glCallList(this.displayList);
					GL11.glTranslatef(-this.rotationPointX * f, -this.rotationPointY * f, -this.rotationPointZ * f); 
					GL11.glPushMatrix();
					GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);

					if (this.rotateAngleZ != 0.0F) {
						GL11.glRotatef(this.rotateAngleZ, 0.0F, 0.0F, 1.0F);
					}
					if (this.rotateAngleY != 0.0F) {
						GL11.glRotatef(this.rotateAngleY, 0.0F, 1.0F, 0.0F);
					}
					if (this.rotateAngleX != 0.0F) {
						GL11.glRotatef(this.rotateAngleX, 1.0F, 0.0F, 0.0F);
					}

					GL11.glCallList(this.displayList);
					GL11.glPopMatrix();
				}
			}			
		}
	}

	public void renderWithRotation(float par1) {
		if (!this.isHidden)	{
			if (this.showModel) {
				if (!this.compiled) {
					compileDisplayList(par1);
				}

				GL11.glPushMatrix();
				GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);

				if (this.rotateAngleY != 0.0F) {
					GL11.glRotatef(this.rotateAngleY, 0.0F, 1.0F, 0.0F);
				}
				if (this.rotateAngleX != 0.0F) {
					GL11.glRotatef(this.rotateAngleX, 1.0F, 0.0F, 0.0F);
				}
				if (this.rotateAngleZ != 0.0F) {
					GL11.glRotatef(this.rotateAngleZ, 0.0F, 0.0F, 1.0F);
				}

				GL11.glCallList(this.displayList);
				GL11.glPopMatrix();
			}
		}
	}

	public void postRender(float par1) {
		if (!this.isHidden) {
			if (this.showModel) {
				if (!this.compiled) {
					compileDisplayList(par1);
				}
				if ((this.rotateAngleX == 0.0F) && (this.rotateAngleY == 0.0F) && (this.rotateAngleZ == 0.0F)) {
					if ((this.rotationPointX != 0.0F) || (this.rotationPointY != 0.0F) || (this.rotationPointZ != 0.0F)) {
						GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
					}
				} else {
					GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);

					if (this.rotateAngleZ != 0.0F) {
						GL11.glRotatef(this.rotateAngleZ, 0.0F, 0.0F, 1.0F);
					}
					if (this.rotateAngleY != 0.0F) {
						GL11.glRotatef(this.rotateAngleY, 0.0F, 1.0F, 0.0F);
					}
					if (this.rotateAngleX != 0.0F) {
						GL11.glRotatef(this.rotateAngleX, 1.0F, 0.0F, 0.0F);
					}
				}
			}
		}
	}

	private void compileDisplayList(float par1) {
		this.displayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(this.displayList, 4864);
		Tessellator var2 = Tessellator.instance;
		Iterator var3 = this.cubeList.iterator();

		while (var3.hasNext()) {
			ModelCustomBox var4 = (ModelCustomBox)var3.next();
			var4.render(var2, par1);
		}

		GL11.glEndList();
		this.compiled = true;
	}
}
