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
 * File created @ [3/15/14, 10:44]
 */
package steamcraft.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

/**
 * Created by Surseance (Johnny Eatmon)
 */
public class FXRaygun extends EntityFX
{
	public int particle = 16;
	EntityPlayer player = null;
	private double offset = 0.0D;
	private float length = 0.0F;
	private float rotYaw = 0.0F;
	private float rotPitch = 0.0F;
	private float prevYaw = 0.0F;
	private float prevPitch = 0.0F;
	private double tX = 0.0D;
	private double tY = 0.0D;
	private double tZ = 0.0D;
	@SuppressWarnings("unused")
	private double ptX = 0.0D;
	@SuppressWarnings("unused")
	private double ptY = 0.0D;
	@SuppressWarnings("unused")
	private double ptZ = 0.0D;
	private int type = 0;
	private float endMod = 1.0F;
	private boolean reverse = false;
	private boolean pulse = true;
	private int rotationspeed = 5;
	private float prevSize = 0.0F;
	public int impact;
	private static final ResourceLocation rayTex = new ResourceLocation(
			LibInfo.PREFIX, "textures/misc/ray.png");
	public static boolean amITooLazyToLearnTheUntiCircle = true;

	public FXRaygun(final World world, final EntityPlayer player,
			final double tx, final double ty, final double tz, final float red,
			final float green, final float blue, final int age)
	{
		super(world, player.posX, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);

		if (player.getEntityData() != Minecraft.getMinecraft().renderViewEntity
				.getEntityData())
		{
			offset = (player.height / 2.0F + 0.25D);
		}

		particleRed = red;
		particleGreen = green;
		particleBlue = blue;
		this.player = player;
		setSize(0.02F, 0.02F);
		noClip = true;
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		tX = tx;
		tY = ty;
		tZ = tz;
		final float xd = (float) (player.posX - tX);
		final float yd = (float) (player.posY + offset - tY);
		final float zd = (float) (player.posZ - tZ);
		length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
		final double var7 = MathHelper.sqrt_double(xd * xd + zd * zd);
		rotYaw = (float) (Math.atan2(xd, zd) * 180.0D / 3.141592653589793D);
		rotPitch = (float) (Math.atan2(yd, var7) * 180.0D / 3.141592653589793D);
		prevYaw = rotYaw;
		prevPitch = rotPitch;
		particleMaxAge = age;
		final EntityLivingBase renderentity = FMLClientHandler.instance()
				.getClient().renderViewEntity;
		int visibleDistance = 50;

		if (!FMLClientHandler.instance().getClient().gameSettings.fancyGraphics)
		{
			visibleDistance = 25;
		}
		if (renderentity.getDistance(player.posX, player.posY, player.posZ) > visibleDistance)
		{
			particleMaxAge = 0;
		}
	}

	public void updateRay(final double x, final double y, final double z)
	{
		tX = x;
		tY = y;
		tZ = z;

		while (particleMaxAge - particleAge < 4)
		{
			particleMaxAge += 1;
		}
	}

	@Override
	public void onUpdate()
	{
		prevPosX = player.posX;
		prevPosY = (player.posY + offset);
		prevPosZ = player.posZ;
		ptX = tX;
		ptY = tY;
		ptZ = tZ;
		prevYaw = rotYaw;
		prevPitch = rotPitch;
		final float xd = (float) (player.posX - tX);
		final float yd = (float) (player.posY + offset - tY);
		final float zd = (float) (player.posZ - tZ);
		length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
		final double var7 = MathHelper.sqrt_double(xd * xd + zd * zd);
		rotYaw = (float) (Math.atan2(xd, zd) * 180.0D / 3.141592653589793D);

		for (rotPitch = (float) (Math.atan2(yd, var7) * 180.0D / 3.141592653589793D); rotPitch
				- prevPitch < -180.0F; prevPitch -= 360.0F)
		{
			;
		}
		while (rotPitch - prevPitch >= 180.0F)
		{
			prevPitch += 360.0F;
		}
		while (rotYaw - prevYaw < -180.0F)
		{
			prevYaw -= 360.0F;
		}
		while (rotYaw - prevYaw >= 180.0F)
		{
			prevYaw += 360.0F;
		}
		if (impact > 0)
		{
			impact -= 1;
		}
		if (particleAge++ >= particleMaxAge)
		{
			setDead();
		}
	}

	public void setRGB(final float r, final float g, final float b)
	{
		particleRed = r;
		particleGreen = g;
		particleBlue = b;
	}

	public void setType(final int type)
	{
		this.type = type;
	}

	public void setEndMod(final float endMod)
	{
		this.endMod = endMod;
	}

	public void setReverse(final boolean reverse)
	{
		this.reverse = reverse;
	}

	public void setPulse(final boolean pulse)
	{
		this.pulse = pulse;
	}

	public void setRotationspeed(final int rotationspeed)
	{
		this.rotationspeed = rotationspeed;
	}

	@Override
	public void renderParticle(final Tessellator tessellator, final float f,
			final float f1, final float f2, final float f3, final float f4,
			final float f5)
	{
		tessellator.draw();
		GL11.glPushMatrix();
		final float var9 = 1.0F;
		final float slide = worldObj.getWorldTime();
		final float rot = worldObj.provider.getWorldTime()
				% (360 / rotationspeed) * rotationspeed + rotationspeed * f;
		float size = 1.0F;

		if (pulse)
		{
			size = Math.min(particleAge / 4.0F, 1.0F);
			size = prevSize + (size - prevSize) * f;
		}

		float op = 0.4F;

		if ((pulse) && (particleMaxAge - particleAge <= 4))
		{
			op = 0.4F - (4 - (particleMaxAge - particleAge)) * 0.1F;
		}

		switch (type)
		{
		default:
			// In case I make another type of beam, I can change the beam
			// texture here
			break;
		case 1:
			// and here...
			break;
		case 2:
			Minecraft.getMinecraft().renderEngine.bindTexture(rayTex);
		}

		GL11.glTexParameterf(3553, 10242, 10497.0F);
		GL11.glTexParameterf(3553, 10243, 10497.0F);
		GL11.glDisable(2884);
		float var11 = slide + f;

		if (reverse)
		{
			var11 *= -1.0F; // why name your variables "var11"? That is so
							// un-helpful
		}

		final float var12 = -var11 * 0.2F
				- MathHelper.floor_float(-var11 * 0.1F);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 1);
		GL11.glDepthMask(false);
		double prex = player.prevPosX;
		double prey = player.prevPosY + offset;
		double prez = player.prevPosZ;
		double px = player.posX;
		double py = player.posY + offset;
		double pz = player.posZ;
		prex -= MathHelper.cos(player.prevRotationYaw / 180.0F * 3.141593F) * 0.066F;
		prey -= 0.06D;
		prez -= MathHelper.sin(player.prevRotationYaw / 180.0F * 3.141593F) * 0.04F;
		Vec3 vec3d = player.getLook(1.0F);
		prex += vec3d.xCoord * 0.3D;
		prey += vec3d.yCoord * 0.3D;
		prez += vec3d.zCoord * 0.3D;
		px -= MathHelper.cos(player.rotationYaw / 180.0F * 3.141593F) * 0.066F;
		py -= 0.06D;
		pz -= MathHelper.sin(player.rotationYaw / 180.0F * 3.141593F) * 0.04F;
		vec3d = player.getLook(1.0F);
		px += vec3d.xCoord * 0.3D;
		py += vec3d.yCoord * 0.3D;
		pz += vec3d.zCoord * 0.3D;
		final float xx = (float) (prex + (px - prex) * f - EntityFX.interpPosX);
		final float yy = (float) (prey + (py - prey) * f - EntityFX.interpPosY);
		final float zz = (float) (prez + (pz - prez) * f - EntityFX.interpPosZ);
		GL11.glTranslated(xx, yy, zz);
		final float ry = prevYaw + (rotYaw - prevYaw) * f;
		final float rp = prevPitch + (rotPitch - prevPitch) * f;
		GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
		GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
		final double var44 = -0.15D * size;
		final double var17 = 0.15D * size;
		final double var44b = -0.15D * size * endMod;
		final double var17b = 0.15D * size * endMod;
		GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);

		for (int t = 0; t < 3; t++)
		{
			final double var29 = length * size * var9;
			final double var31 = 0.0D;
			final double var33 = 1.0D;
			final double var35 = -1.0F + var12 + t / 3.0F;
			final double var37 = length * size * var9 + var35;
			GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
			tessellator.startDrawingQuads();
			tessellator.setBrightness(200);
			tessellator.setColorRGBA_F(particleRed, particleGreen,
					particleBlue, op);
			tessellator.addVertexWithUV(var44b, var29, 0.0D, var33, var37);
			tessellator.addVertexWithUV(var44, 0.0D, 0.0D, var33, var35);
			tessellator.addVertexWithUV(var17, 0.0D, 0.0D, var31, var35);
			tessellator.addVertexWithUV(var17b, var29, 0.0D, var31, var37);
			tessellator.draw();
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glEnable(2884);
		GL11.glPopMatrix();
		tessellator.startDrawingQuads();
		prevSize = size;
	}
}
