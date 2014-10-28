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
	ModelRenderer body1;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer head;
	ModelRenderer jaw;
	ModelRenderer leftarm1;
	ModelRenderer hump;
	ModelRenderer tooth2;
	ModelRenderer tooth;
	ModelRenderer tooth1;
	ModelRenderer hand;
	ModelRenderer finger;
	ModelRenderer finger1;
	ModelRenderer finger2;
	ModelRenderer finger3;
	ModelRenderer finger4;
	ModelRenderer finger5;
	ModelRenderer finger6;
	ModelRenderer finger7;
	ModelRenderer finger8;
	ModelRenderer hand1;
	ModelRenderer finger9;
	ModelRenderer hand2;
	ModelRenderer finger10;
	ModelRenderer finger11;

	public ModelFleshGolem()
	{
		textureWidth = 64;
		textureHeight = 64;

		body1 = new ModelRenderer(this, 0, 0);
		body1.addBox(-4F, -8F, -4F, 14, 13, 6);
		body1.setRotationPoint(-3F, 2F, -4F);
		body1.setTextureSize(64, 64);
		body1.mirror = true;
		setRotation(body1, 0.4461433F, 0F, 0F);
		body = new ModelRenderer(this, 0, 19);
		body.addBox(-4F, 0F, -2F, 8, 7, 4);
		body.setRotationPoint(0F, 6F, -3F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.3346075F, 0F, 0F);
		rightarm = new ModelRenderer(this, 16, 30);
		rightarm.addBox(-3F, -2F, -2F, 3, 10, 3);
		rightarm.setRotationPoint(-7F, -4F, -8F);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 28, 34);
		leftarm.addBox(-1F, -2F, -2F, 4, 5, 4);
		leftarm.setRotationPoint(8.4F, 6F, -8.2F);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 30);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-3F, 12F, 0F);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 30);
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(3F, 12F, 0F);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		head = new ModelRenderer(this, 0, 46);
		head.addBox(0F, 0F, 0F, 5, 6, 5);
		head.setRotationPoint(-2F, -9F, -13F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		jaw = new ModelRenderer(this, 20, 50);
		jaw.addBox(0F, 0F, 0F, 5, 2, 5);
		jaw.setRotationPoint(-2F, -4F, -14.5F);
		jaw.setTextureSize(64, 64);
		jaw.mirror = true;
		setRotation(jaw, 0.1487144F, 0F, 0F);
		leftarm = new ModelRenderer(this, 28, 19);
		leftarm.addBox(-1F, -2F, -2F, 5, 10, 5);
		leftarm.setRotationPoint(8F, -4F, -9F);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		hump = new ModelRenderer(this, 0, 0);
		hump.addBox(0F, 0F, 0F, 10, 9, 2);
		hump.setRotationPoint(-5F, -4F, -5F);
		hump.setTextureSize(64, 64);
		hump.mirror = true;
		setRotation(hump, 0.4461433F, 0F, 0F);
		tooth2 = new ModelRenderer(this, 20, 48);
		tooth2.addBox(0F, 0F, 0F, 1, 1, 1);
		tooth2.setRotationPoint(1.7F, -5F, -14.3F);
		tooth2.setTextureSize(64, 64);
		tooth2.mirror = true;
		setRotation(tooth2, 0.1487144F, 0.0743572F, 0.1858931F);
		tooth = new ModelRenderer(this, 20, 48);
		tooth.addBox(0F, 0F, 0F, 1, 1, 1);
		tooth.setRotationPoint(-1.5F, -5F, -14.3F);
		tooth.setTextureSize(64, 64);
		tooth.mirror = true;
		setRotation(tooth, 0F, -0.2974289F, 0.1312612F);
		tooth1 = new ModelRenderer(this, 20, 48);
		tooth1.addBox(0F, 0F, 0F, 1, 1, 1);
		tooth1.setRotationPoint(0F, -5F, -14.3F);
		tooth1.setTextureSize(64, 64);
		tooth1.mirror = true;
		setRotation(tooth1, -0.0743572F, -0.2974289F, 0F);
		hand = new ModelRenderer(this, 40, 43);
		hand.addBox(0F, 0F, 0F, 2, 5, 4);
		hand.setRotationPoint(10F, 8F, -10.5F);
		hand.setTextureSize(64, 64);
		hand.mirror = true;
		setRotation(hand, 0F, 0F, 0.4461433F);
		finger = new ModelRenderer(this, 24, 27);
		finger.addBox(0F, 0F, 0F, 1, 2, 1);
		finger.setRotationPoint(10F, 12F, -9.7F);
		finger.setTextureSize(64, 64);
		finger.mirror = true;
		setRotation(finger, 0F, -1.449966F, 1.697307F);
		finger1 = new ModelRenderer(this, 24, 27);
		finger1.addBox(0F, 0F, 0F, 1, 2, 1);
		finger1.setRotationPoint(10F, 12F, -11F);
		finger1.setTextureSize(64, 64);
		finger1.mirror = true;
		setRotation(finger1, 0F, -1.449966F, 2.849844F);
		finger2 = new ModelRenderer(this, 24, 23);
		finger2.addBox(0F, 0F, 0F, 1, 3, 1);
		finger2.setRotationPoint(9F, 12F, -10.5F);
		finger2.setTextureSize(64, 64);
		finger2.mirror = true;
		setRotation(finger2, 0F, 0F, 1.362699F);
		finger3 = new ModelRenderer(this, 24, 23);
		finger3.addBox(0F, 0F, 0F, 1, 3, 1);
		finger3.setRotationPoint(7F, 13F, -10.5F);
		finger3.setTextureSize(64, 64);
		finger3.mirror = true;
		setRotation(finger3, 0F, 0F, 3.092641F);
		finger4 = new ModelRenderer(this, 24, 23);
		finger4.addBox(0F, 0F, 0F, 1, 3, 1);
		finger4.setRotationPoint(9F, 12F, -8.7F);
		finger4.setTextureSize(64, 64);
		finger4.mirror = true;
		setRotation(finger4, 0F, 0F, 1.697307F);
		finger5 = new ModelRenderer(this, 24, 23);
		finger5.addBox(0F, 0F, 0F, 1, 3, 1);
		finger5.setRotationPoint(7F, 12.5F, -8.7F);
		finger5.setTextureSize(64, 64);
		finger5.mirror = true;
		setRotation(finger5, 0F, 0F, 2.849844F);
		finger6 = new ModelRenderer(this, 24, 23);
		finger6.addBox(0F, 0F, 0F, 1, 3, 1);
		finger6.setRotationPoint(9F, 12F, -6.8F);
		finger6.setTextureSize(64, 64);
		finger6.mirror = true;
		setRotation(finger6, 0F, 0F, 1.697307F);
		finger1 = new ModelRenderer(this, 24, 23);
		finger1.addBox(0F, 0F, 0F, 1, 3, 1);
		finger1.setRotationPoint(7F, 12.5F, -6.8F);
		finger1.setTextureSize(64, 64);
		finger1.mirror = true;
		setRotation(finger1, 0F, 0F, 2.738308F);
		hand1 = new ModelRenderer(this, 20, 43);
		hand1.addBox(0F, 0F, 0F, 1, 2, 2);
		hand1.setRotationPoint(-10F, 4F, -9.5F);
		hand1.setTextureSize(64, 64);
		hand1.mirror = true;
		setRotation(hand1, 0F, 0F, -0.4461433F);
		finger7 = new ModelRenderer(this, 16, 43);
		finger7.addBox(0F, 0F, 0F, 1, 2, 1);
		finger7.setRotationPoint(-6.5F, 4F, -8F);
		finger7.setTextureSize(64, 64);
		finger7.mirror = true;
		setRotation(finger7, 0F, 0F, 0.4461433F);
		hand1 = new ModelRenderer(this, 26, 44);
		hand1.addBox(0F, 0F, 0F, 1, 3, 3);
		hand1.setRotationPoint(-9F, 4F, -10F);
		hand1.setTextureSize(64, 64);
		hand1.mirror = true;
		setRotation(hand1, 0F, 0F, -0.4461433F);
		finger8 = new ModelRenderer(this, 16, 43);
		finger8.addBox(0F, 0F, 0F, 1, 2, 1);
		finger8.setRotationPoint(-6.5F, 4F, -10F);
		finger8.setTextureSize(64, 64);
		finger8.mirror = true;
		setRotation(finger8, 0F, 0F, 0.7063936F);
		finger9 = new ModelRenderer(this, 16, 43);
		finger9.addBox(0F, 0F, 0F, 1, 2, 1);
		finger9.setRotationPoint(-7.5F, 4F, -11F);
		finger9.setTextureSize(64, 64);
		finger9.mirror = true;
		setRotation(finger9, 0F, 0.9666439F, 0.7807508F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		GL11.glScalef(1.5F, 1.5F, 2F);
		GL11.glTranslatef(0, 0, 5F);
		body1.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		head.render(f5);
		jaw.render(f5);
		leftarm1.render(f5);
		hump.render(f5);
		tooth2.render(f5);
		tooth.render(f5);
		tooth1.render(f5);
		hand.render(f5);
		finger1.render(f5);
		finger2.render(f5);
		finger3.render(f5);
		finger4.render(f5);
		finger5.render(f5);
		finger6.render(f5);
		finger7.render(f5);
		finger8.render(f5);
		hand.render(f5);
		finger9.render(f5);
		hand1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
}
