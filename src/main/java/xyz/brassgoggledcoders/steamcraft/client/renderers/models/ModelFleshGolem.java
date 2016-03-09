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
package xyz.brassgoggledcoders.steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

//TODO
@SideOnly(Side.CLIENT)
public class ModelFleshGolem extends ModelBase
{
	public final ModelRenderer hump;
	public final ModelRenderer tooth3;
	public final ModelRenderer tooth;
	public final ModelRenderer tooth2;
	public final ModelRenderer hand1;
	public final ModelRenderer tooth1;
	public ModelRenderer finger17;
	public final ModelRenderer hand;
	public final ModelRenderer leftarm1;
	public final ModelRenderer leftarm;
	public ModelRenderer finger14;
	public final ModelRenderer finger15;
	public final ModelRenderer finger9;
	public final ModelRenderer finger5;
	public final ModelRenderer finger3;
	public ModelRenderer finger1;
	public final ModelRenderer finger2;
	public final ModelRenderer finger4;
	public final ModelRenderer finger6;
	public final ModelRenderer finger7;
	public final ModelRenderer finger8;
	public final ModelRenderer finger12;
	public final ModelRenderer finger13;
	public final ModelRenderer finger10;
	public final ModelRenderer finger11;
	public final ModelRenderer body1;
	public final ModelRenderer body;
	public final ModelRenderer rightarm1;
	public final ModelRenderer rightleg;
	public final ModelRenderer leftleg;
	public final ModelRenderer head;
	public final ModelRenderer jaw;
	public final ModelRenderer rightarm;

	public ModelFleshGolem()
	{
		this.textureHeight = 64;

		this.hump = new ModelRenderer(this);
		this.hump.addBox(0F, 0F, 0F, 10, 9, 2);
		this.hump.setRotationPoint(-5F, -7F, -5F);
		this.hump.mirror = true;
		this.setRotation(this.hump, 0.4461433F, 0F, 0F);
		this.tooth3 = new ModelRenderer(this, 20, 48);
		this.tooth3.addBox(0F, 0F, 0F, 1, 1, 1);
		this.tooth3.setRotationPoint(2.7F, -9F, -15.3F);
		this.tooth3.mirror = true;
		this.setRotation(this.tooth3, 0.1487144F, 0.0743572F, 0.1858931F);
		this.tooth = new ModelRenderer(this, 20, 48);
		this.tooth.addBox(0F, 0F, 0F, 1, 1, 1);
		this.tooth.setRotationPoint(-1.5F, -10F, -15.3F);
		this.tooth.mirror = true;
		this.setRotation(this.tooth, 0F, -0.2974289F, 0.1312612F);
		this.tooth2 = new ModelRenderer(this, 20, 48);
		this.tooth2.addBox(0F, 0F, 0F, 1, 1, 1);
		this.tooth2.setRotationPoint(1F, -9F, -15.3F);
		this.tooth2.mirror = true;
		this.setRotation(this.tooth2, -0.0743572F, -0.2974289F, 0F);
		this.hand1 = new ModelRenderer(this, 40, 43);
		this.hand1.addBox(0F, 0F, 0F, 3, 5, 5);
		this.hand1.setRotationPoint(-9F, 5.5F, -4.5F);
		this.hand1.mirror = true;
		this.setRotation(this.hand1, 0F, 3.141593F, 0.4461433F);
		this.tooth1 = new ModelRenderer(this, 20, 48);
		this.tooth1.addBox(0F, 0F, 0F, 1, 1, 1);
		this.tooth1.setRotationPoint(-0.5F, -9F, -15F);
		this.tooth1.mirror = true;
		this.setRotation(this.tooth1, -0.0743572F, 0.8922867F, 0.4089647F);
		this.finger17 = new ModelRenderer(this, 24, 27);
		this.finger17.addBox(0F, 0F, 0F, 1, 2, 1);
		this.finger17.mirror = true;
		this.setRotation(this.finger17, 0F, -1.449966F, 2.849844F);
		this.hand = new ModelRenderer(this, 40, 43);
		this.hand.addBox(0F, 0F, 0F, 3, 5, 5);
		this.hand.setRotationPoint(10F, 6.5F, -8F);
		this.hand.mirror = true;
		this.setRotation(this.hand, 0F, 0F, 0.4461433F);
		this.leftarm1 = new ModelRenderer(this, 28, 34);
		this.leftarm1.addBox(-1F, -2F, -2F, 5, 6, 5);
		this.leftarm1.setRotationPoint(10F, 4F, -6.2F);
		this.leftarm1.mirror = true;
		this.leftarm = new ModelRenderer(this, 28, 19);
		this.leftarm.addBox(-1F, -2F, -2F, 6, 11, 6);
		this.leftarm.setRotationPoint(9.6F, -7F, -7F);
		this.leftarm.mirror = true;
		this.finger14 = new ModelRenderer(this, 24, 27);
		this.finger14.addBox(0F, 0F, 0F, 1, 1, 1);
		this.finger14.setRotationPoint(-8.5F, 9F, -9F);
		this.finger14.mirror = true;
		this.setRotation(this.finger14, 0F, -2.342252F, 1.697307F);
		this.finger15 = new ModelRenderer(this, 24, 27);
		this.finger15.addBox(0F, 0F, 0F, 1, 2, 1);
		this.finger15.setRotationPoint(-8F, 9.5F, -9.3F);
		this.finger15.mirror = true;
		this.setRotation(this.finger15, 0F, -2.342252F, 2.849844F);
		this.finger9 = new ModelRenderer(this, 24, 23);
		this.finger9.addBox(0F, 0F, 0F, 1, 2, 1);
		this.finger9.setRotationPoint(-6F, 10.5F, -4.7F);
		this.finger9.mirror = true;
		this.setRotation(this.finger9, 0F, 3.141593F, 2.180629F);
		this.finger5 = new ModelRenderer(this, 24, 23);
		this.finger5.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger5.setRotationPoint(6.5F, 11F, -8.5F);
		this.finger5.mirror = true;
		this.setRotation(this.finger5, 0F, 0F, 3.092641F);
		this.finger3 = new ModelRenderer(this, 24, 23);
		this.finger3.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger3.setRotationPoint(6.5F, 10.5F, -6.7F);
		this.finger3.mirror = true;
		this.setRotation(this.finger3, 0F, 0F, 2.849844F);
		this.finger17 = new ModelRenderer(this, 24, 23);
		this.finger17.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger17.setRotationPoint(6.5F, 10.5F, -4.8F);
		this.finger17.mirror = true;
		this.setRotation(this.finger17, 0F, 0F, 2.738308F);
		this.finger14 = new ModelRenderer(this, 24, 23);
		this.finger14.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger14.setRotationPoint(9F, 10F, -4.8F);
		this.finger14.mirror = true;
		this.setRotation(this.finger14, 0F, 0F, 1.697307F);
		this.finger2 = new ModelRenderer(this, 24, 23);
		this.finger2.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger2.setRotationPoint(9F, 10F, -6.7F);
		this.finger2.mirror = true;
		this.setRotation(this.finger2, 0F, 0F, 1.697307F);
		this.finger4 = new ModelRenderer(this, 24, 23);
		this.finger4.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger4.setRotationPoint(6F, 10F, -7.7F);
		this.finger4.mirror = true;
		this.setRotation(this.finger4, 0F, 3.141593F, 1.697307F);
		this.finger6 = new ModelRenderer(this, 24, 27);
		this.finger6.addBox(0F, 0F, 0F, 1, 2, 1);
		this.finger6.setRotationPoint(10.5F, 11F, -7.7F);
		this.finger6.mirror = true;
		this.setRotation(this.finger6, 0F, -1.449966F, 1.697307F);
		this.finger7 = new ModelRenderer(this, 24, 27);
		this.finger7.addBox(0F, 0F, 0F, 1, 2, 1);
		this.finger7.setRotationPoint(10.33333F, 11F, -9F);
		this.finger7.mirror = true;
		this.setRotation(this.finger7, 0F, -1.449966F, 2.849844F);
		this.finger8 = new ModelRenderer(this, 24, 23);
		this.finger8.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger8.setRotationPoint(-8F, 9.5F, -4.7F);
		this.finger8.mirror = true;
		this.setRotation(this.finger8, 0F, 3.141593F, 1.065271F);
		this.finger12 = new ModelRenderer(this, 24, 23);
		this.finger12.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger12.setRotationPoint(-8F, 9.5F, -8.7F);
		this.finger12.mirror = true;
		this.setRotation(this.finger12, 0F, 3.141593F, 1.697307F);
		this.finger13 = new ModelRenderer(this, 24, 23);
		this.finger13.addBox(0F, 0F, 0F, 1, 2, 1);
		this.finger13.setRotationPoint(-6F, 9.5F, -8.7F);
		this.finger13.mirror = true;
		this.setRotation(this.finger13, 0F, 3.141593F, 2.812665F);
		this.finger10 = new ModelRenderer(this, 24, 23);
		this.finger10.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger10.setRotationPoint(-8F, 9.5F, -6.7F);
		this.finger10.mirror = true;
		this.setRotation(this.finger10, 0F, 3.141593F, 2.026234F);
		this.finger11 = new ModelRenderer(this, 24, 23);
		this.finger11.addBox(0F, 0F, 0F, 1, 3, 1);
		this.finger11.setRotationPoint(-6F, 9.5F, -6.7F);
		this.finger11.mirror = true;
		this.setRotation(this.finger11, 0F, 3.141593F, 3.141593F);
		this.body1 = new ModelRenderer(this);
		this.body1.addBox(-4F, -8F, -4F, 16, 13, 6);
		this.body1.setRotationPoint(-3.5F, -1F, -4F);
		this.body1.mirror = true;
		this.setRotation(this.body1, 0.4461433F, 0F, 0F);
		this.body = new ModelRenderer(this, 0, 19);
		this.body.addBox(-4F, 0F, -2F, 10, 7, 4);
		this.body.setRotationPoint(-0.5F, 3F, -3F);
		this.body.mirror = true;
		this.setRotation(this.body, 0.3346075F, 0F, 0F);
		this.rightarm1 = new ModelRenderer(this, 28, 34);
		this.rightarm1.addBox(-1F, -2F, -2F, 5, 6, 5);
		this.rightarm1.setRotationPoint(-8.8F, 3F, -6.7F);
		this.rightarm1.mirror = true;
		this.setRotation(this.rightarm1, 0F, 3.141593F, 0F);
		this.rightleg = new ModelRenderer(this, 0, 30);
		this.rightleg.addBox(-2F, 0F, -2F, 5, 15, 5);
		this.rightleg.setRotationPoint(-3.5F, 9F, 0F);
		this.rightleg.mirror = true;
		this.leftleg = new ModelRenderer(this, 0, 30);
		this.leftleg.addBox(-2F, 0F, -2F, 5, 15, 5);
		this.leftleg.setRotationPoint(3.5F, 9F, 0F);
		this.leftleg.mirror = true;
		this.head = new ModelRenderer(this, 0, 46);
		this.head.addBox(0F, 0F, 0F, 6, 6, 6);
		this.head.setRotationPoint(-2F, -13F, -14F);
		this.head.mirror = true;
		this.jaw = new ModelRenderer(this, 20, 50);
		this.jaw.addBox(0F, 0F, 0F, 6, 2, 6);
		this.jaw.setRotationPoint(-2F, -8F, -15.5F);
		this.jaw.mirror = true;
		this.setRotation(this.jaw, 0.1487144F, 0F, 0F);
		this.rightarm = new ModelRenderer(this, 28, 19);
		this.rightarm.addBox(-1F, -2F, -2F, 6, 11, 6);
		this.rightarm.setRotationPoint(-8.5F, -7.5F, -6F);
		this.rightarm.mirror = true;
		this.setRotation(this.rightarm, 0F, 3.141593F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GL11.glScalef(1.4F, 1.4F, 1.4F);
		GL11.glTranslatef(0, -0.4F, 0);
		this.hump.render(f5);
		this.tooth3.render(f5);
		this.tooth.render(f5);
		this.tooth2.render(f5);
		this.hand1.render(f5);
		this.tooth1.render(f5);
		this.finger17.render(f5);
		this.hand.render(f5);
		this.leftarm1.render(f5);
		this.leftarm.render(f5);
		this.finger14.render(f5);
		this.finger15.render(f5);
		this.finger9.render(f5);
		this.finger5.render(f5);
		this.finger3.render(f5);
		this.finger17.render(f5);
		this.finger2.render(f5);
		this.finger4.render(f5);
		this.finger6.render(f5);
		this.finger7.render(f5);
		this.finger8.render(f5);
		this.finger12.render(f5);
		this.finger13.render(f5);
		this.finger10.render(f5);
		this.finger11.render(f5);
		this.body1.render(f5);
		this.body.render(f5);
		this.rightarm1.render(f5);
		this.rightleg.render(f5);
		this.leftleg.render(f5);
		this.head.render(f5);
		this.jaw.render(f5);
		this.rightarm.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
