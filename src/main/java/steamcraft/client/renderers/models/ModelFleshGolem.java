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
package steamcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//TODO
@SideOnly(Side.CLIENT)
public class ModelFleshGolem extends ModelBase
{
	// fields
	ModelRenderer hump;
	ModelRenderer tooth3;
	ModelRenderer tooth;
	ModelRenderer tooth2;
	ModelRenderer hand1;
	ModelRenderer tooth1;
	ModelRenderer finger17;
	ModelRenderer hand;
	ModelRenderer leftarm1;
	ModelRenderer leftarm;
	ModelRenderer finger14;
	ModelRenderer finger15;
	ModelRenderer finger9;
	ModelRenderer finger5;
	ModelRenderer finger3;
	ModelRenderer finger1;
	ModelRenderer finger2;
	ModelRenderer finger4;
	ModelRenderer finger6;
	ModelRenderer finger7;
	ModelRenderer finger8;
	ModelRenderer finger12;
	ModelRenderer finger13;
	ModelRenderer finger10;
	ModelRenderer finger11;
	ModelRenderer body1;
	ModelRenderer body;
	ModelRenderer rightarm1;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer head;
	ModelRenderer jaw;
	ModelRenderer rightarm;

	public ModelFleshGolem()
	{
		textureWidth = 64;
		textureHeight = 64;

		hump = new ModelRenderer(this, 0, 0);
		hump.addBox(0F, 0F, 0F, 10, 9, 2);
		hump.setRotationPoint(-5F, -7F, -5F);
		hump.setTextureSize(64, 64);
		hump.mirror = true;
		setRotation(hump, 0.4461433F, 0F, 0F);
		tooth3 = new ModelRenderer(this, 20, 48);
		tooth3.addBox(0F, 0F, 0F, 1, 1, 1);
		tooth3.setRotationPoint(2.7F, -9F, -15.3F);
		tooth3.setTextureSize(64, 64);
		tooth3.mirror = true;
		setRotation(tooth3, 0.1487144F, 0.0743572F, 0.1858931F);
		tooth = new ModelRenderer(this, 20, 48);
		tooth.addBox(0F, 0F, 0F, 1, 1, 1);
		tooth.setRotationPoint(-1.5F, -10F, -15.3F);
		tooth.setTextureSize(64, 64);
		tooth.mirror = true;
		setRotation(tooth, 0F, -0.2974289F, 0.1312612F);
		tooth2 = new ModelRenderer(this, 20, 48);
		tooth2.addBox(0F, 0F, 0F, 1, 1, 1);
		tooth2.setRotationPoint(1F, -9F, -15.3F);
		tooth2.setTextureSize(64, 64);
		tooth2.mirror = true;
		setRotation(tooth2, -0.0743572F, -0.2974289F, 0F);
		hand1 = new ModelRenderer(this, 40, 43);
		hand1.addBox(0F, 0F, 0F, 3, 5, 5);
		hand1.setRotationPoint(-9F, 5.5F, -4.5F);
		hand1.setTextureSize(64, 64);
		hand1.mirror = true;
		setRotation(hand1, 0F, 3.141593F, 0.4461433F);
		tooth1 = new ModelRenderer(this, 20, 48);
		tooth1.addBox(0F, 0F, 0F, 1, 1, 1);
		tooth1.setRotationPoint(-0.5F, -9F, -15F);
		tooth1.setTextureSize(64, 64);
		tooth1.mirror = true;
		setRotation(tooth1, -0.0743572F, 0.8922867F, 0.4089647F);
		finger17 = new ModelRenderer(this, 24, 27);
		finger17.addBox(0F, 0F, 0F, 1, 2, 1);
		finger17.setRotationPoint(0F, 0F, 0F);
		finger17.setTextureSize(64, 64);
		finger17.mirror = true;
		setRotation(finger17, 0F, -1.449966F, 2.849844F);
		hand = new ModelRenderer(this, 40, 43);
		hand.addBox(0F, 0F, 0F, 3, 5, 5);
		hand.setRotationPoint(10F, 6.5F, -8F);
		hand.setTextureSize(64, 64);
		hand.mirror = true;
		setRotation(hand, 0F, 0F, 0.4461433F);
		leftarm1 = new ModelRenderer(this, 28, 34);
		leftarm1.addBox(-1F, -2F, -2F, 5, 6, 5);
		leftarm1.setRotationPoint(10F, 4F, -6.2F);
		leftarm1.setTextureSize(64, 64);
		leftarm1.mirror = true;
		setRotation(leftarm1, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 28, 19);
		leftarm.addBox(-1F, -2F, -2F, 6, 11, 6);
		leftarm.setRotationPoint(9.6F, -7F, -7F);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		finger14 = new ModelRenderer(this, 24, 27);
		finger14.addBox(0F, 0F, 0F, 1, 1, 1);
		finger14.setRotationPoint(-8.5F, 9F, -9F);
		finger14.setTextureSize(64, 64);
		finger14.mirror = true;
		setRotation(finger14, 0F, -2.342252F, 1.697307F);
		finger15 = new ModelRenderer(this, 24, 27);
		finger15.addBox(0F, 0F, 0F, 1, 2, 1);
		finger15.setRotationPoint(-8F, 9.5F, -9.3F);
		finger15.setTextureSize(64, 64);
		finger15.mirror = true;
		setRotation(finger15, 0F, -2.342252F, 2.849844F);
		finger9 = new ModelRenderer(this, 24, 23);
		finger9.addBox(0F, 0F, 0F, 1, 2, 1);
		finger9.setRotationPoint(-6F, 10.5F, -4.7F);
		finger9.setTextureSize(64, 64);
		finger9.mirror = true;
		setRotation(finger9, 0F, 3.141593F, 2.180629F);
		finger5 = new ModelRenderer(this, 24, 23);
		finger5.addBox(0F, 0F, 0F, 1, 3, 1);
		finger5.setRotationPoint(6.5F, 11F, -8.5F);
		finger5.setTextureSize(64, 64);
		finger5.mirror = true;
		setRotation(finger5, 0F, 0F, 3.092641F);
		finger3 = new ModelRenderer(this, 24, 23);
		finger3.addBox(0F, 0F, 0F, 1, 3, 1);
		finger3.setRotationPoint(6.5F, 10.5F, -6.7F);
		finger3.setTextureSize(64, 64);
		finger3.mirror = true;
		setRotation(finger3, 0F, 0F, 2.849844F);
		finger17 = new ModelRenderer(this, 24, 23);
		finger17.addBox(0F, 0F, 0F, 1, 3, 1);
		finger17.setRotationPoint(6.5F, 10.5F, -4.8F);
		finger17.setTextureSize(64, 64);
		finger17.mirror = true;
		setRotation(finger17, 0F, 0F, 2.738308F);
		finger14 = new ModelRenderer(this, 24, 23);
		finger14.addBox(0F, 0F, 0F, 1, 3, 1);
		finger14.setRotationPoint(9F, 10F, -4.8F);
		finger14.setTextureSize(64, 64);
		finger14.mirror = true;
		setRotation(finger14, 0F, 0F, 1.697307F);
		finger2 = new ModelRenderer(this, 24, 23);
		finger2.addBox(0F, 0F, 0F, 1, 3, 1);
		finger2.setRotationPoint(9F, 10F, -6.7F);
		finger2.setTextureSize(64, 64);
		finger2.mirror = true;
		setRotation(finger2, 0F, 0F, 1.697307F);
		finger4 = new ModelRenderer(this, 24, 23);
		finger4.addBox(0F, 0F, 0F, 1, 3, 1);
		finger4.setRotationPoint(6F, 10F, -7.7F);
		finger4.setTextureSize(64, 64);
		finger4.mirror = true;
		setRotation(finger4, 0F, 3.141593F, 1.697307F);
		finger6 = new ModelRenderer(this, 24, 27);
		finger6.addBox(0F, 0F, 0F, 1, 2, 1);
		finger6.setRotationPoint(10.5F, 11F, -7.7F);
		finger6.setTextureSize(64, 64);
		finger6.mirror = true;
		setRotation(finger6, 0F, -1.449966F, 1.697307F);
		finger7 = new ModelRenderer(this, 24, 27);
		finger7.addBox(0F, 0F, 0F, 1, 2, 1);
		finger7.setRotationPoint(10.33333F, 11F, -9F);
		finger7.setTextureSize(64, 64);
		finger7.mirror = true;
		setRotation(finger7, 0F, -1.449966F, 2.849844F);
		finger8 = new ModelRenderer(this, 24, 23);
		finger8.addBox(0F, 0F, 0F, 1, 3, 1);
		finger8.setRotationPoint(-8F, 9.5F, -4.7F);
		finger8.setTextureSize(64, 64);
		finger8.mirror = true;
		setRotation(finger8, 0F, 3.141593F, 1.065271F);
		finger12 = new ModelRenderer(this, 24, 23);
		finger12.addBox(0F, 0F, 0F, 1, 3, 1);
		finger12.setRotationPoint(-8F, 9.5F, -8.7F);
		finger12.setTextureSize(64, 64);
		finger12.mirror = true;
		setRotation(finger12, 0F, 3.141593F, 1.697307F);
		finger13 = new ModelRenderer(this, 24, 23);
		finger13.addBox(0F, 0F, 0F, 1, 2, 1);
		finger13.setRotationPoint(-6F, 9.5F, -8.7F);
		finger13.setTextureSize(64, 64);
		finger13.mirror = true;
		setRotation(finger13, 0F, 3.141593F, 2.812665F);
		finger10 = new ModelRenderer(this, 24, 23);
		finger10.addBox(0F, 0F, 0F, 1, 3, 1);
		finger10.setRotationPoint(-8F, 9.5F, -6.7F);
		finger10.setTextureSize(64, 64);
		finger10.mirror = true;
		setRotation(finger10, 0F, 3.141593F, 2.026234F);
		finger11 = new ModelRenderer(this, 24, 23);
		finger11.addBox(0F, 0F, 0F, 1, 3, 1);
		finger11.setRotationPoint(-6F, 9.5F, -6.7F);
		finger11.setTextureSize(64, 64);
		finger11.mirror = true;
		setRotation(finger11, 0F, 3.141593F, 3.141593F);
		body1 = new ModelRenderer(this, 0, 0);
		body1.addBox(-4F, -8F, -4F, 16, 13, 6);
		body1.setRotationPoint(-3.5F, -1F, -4F);
		body1.setTextureSize(64, 64);
		body1.mirror = true;
		setRotation(body1, 0.4461433F, 0F, 0F);
		body = new ModelRenderer(this, 0, 19);
		body.addBox(-4F, 0F, -2F, 10, 7, 4);
		body.setRotationPoint(-0.5F, 3F, -3F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.3346075F, 0F, 0F);
		rightarm1 = new ModelRenderer(this, 28, 34);
		rightarm1.addBox(-1F, -2F, -2F, 5, 6, 5);
		rightarm1.setRotationPoint(-8.8F, 3F, -6.7F);
		rightarm1.setTextureSize(64, 64);
		rightarm1.mirror = true;
		setRotation(rightarm1, 0F, 3.141593F, 0F);
		rightleg = new ModelRenderer(this, 0, 30);
		rightleg.addBox(-2F, 0F, -2F, 5, 15, 5);
		rightleg.setRotationPoint(-3.5F, 9F, 0F);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 30);
		leftleg.addBox(-2F, 0F, -2F, 5, 15, 5);
		leftleg.setRotationPoint(3.5F, 9F, 0F);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		head = new ModelRenderer(this, 0, 46);
		head.addBox(0F, 0F, 0F, 6, 6, 6);
		head.setRotationPoint(-2F, -13F, -14F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		jaw = new ModelRenderer(this, 20, 50);
		jaw.addBox(0F, 0F, 0F, 6, 2, 6);
		jaw.setRotationPoint(-2F, -8F, -15.5F);
		jaw.setTextureSize(64, 64);
		jaw.mirror = true;
		setRotation(jaw, 0.1487144F, 0F, 0F);
		rightarm = new ModelRenderer(this, 28, 19);
		rightarm.addBox(-1F, -2F, -2F, 6, 11, 6);
		rightarm.setRotationPoint(-8.5F, -7.5F, -6F);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 3.141593F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GL11.glScalef(1.4F, 1.4F, 1.4F);
		GL11.glTranslatef(0, -0.4F, 0);
		hump.render(f5);
		tooth3.render(f5);
		tooth.render(f5);
		tooth2.render(f5);
		hand1.render(f5);
		tooth1.render(f5);
		finger17.render(f5);
		hand.render(f5);
		leftarm1.render(f5);
		leftarm.render(f5);
		finger14.render(f5);
		finger15.render(f5);
		finger9.render(f5);
		finger5.render(f5);
		finger3.render(f5);
		finger17.render(f5);
		finger2.render(f5);
		finger4.render(f5);
		finger6.render(f5);
		finger7.render(f5);
		finger8.render(f5);
		finger12.render(f5);
		finger13.render(f5);
		finger10.render(f5);
		finger11.render(f5);
		body1.render(f5);
		body.render(f5);
		rightarm1.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		head.render(f5);
		jaw.render(f5);
		rightarm.render(f5);
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
