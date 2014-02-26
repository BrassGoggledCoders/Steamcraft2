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
 * File created @ [Jan 30, 2014, 5:19:11 PM]
 */
package common.steamcraft.client.fx;

import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
/**
 * @author Based on Azanor's math, but vehemently adjusted 
 * for SC2 by MrArcane111
 *
 */
public class FXRayGun extends EntityFX
{
	public int particle = 16;
	EntityPlayer player = null;
	private double offset = 0.0D;
	private float length = 0.0F;
	private float rotYaw = 0.0F;
	private float rotPitch = 0.0F;
	private float prevYaw = 0.0F;
	private float prevPitch = 0.0F;
	private Entity targetEntity = null;
	private double tX = 0.0D;
	private double tY = 0.0D;
	private double tZ = 0.0D;
	private double ptX = 0.0D;
	private double ptY = 0.0D;
	private double ptZ = 0.0D;
	private int type = 0;
	private float endMod = 1.0F;
	private boolean reverse = false;
	private boolean pulse = true;
	private int rotationspeed = 5;
	private float prevSize = 0.0F;
	public int impact;
	private static final ResourceLocation rayTex = new ResourceLocation(LibInfo.MOD_ID.toLowerCase(), "textures/misc/ray.png");
	public static boolean amITooLazyToLearnTheUntiCircle = true;

	public FXRayGun(World world, EntityPlayer player, double tx, double ty, double tz, float red, float green, float blue, int age)
	{
		super(world, player.posX, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);

		if(player.entityId != Minecraft.getMinecraft().renderViewEntity.entityId) 
			this.offset = (player.height / 2.0F + 0.25D);

		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
		this.player = player;
		this.setSize(0.02F, 0.02F);
		this.noClip = true;
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.tX = tx;
		this.tY = ty;
		this.tZ = tz;
		float xd = (float)(player.posX - this.tX);
		float yd = (float)(player.posY + this.offset - this.tY);
		float zd = (float)(player.posZ - this.tZ);
		this.length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
		double var7 = MathHelper.sqrt_double(xd * xd + zd * zd);
		this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D);
		this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D);
		this.prevYaw = this.rotYaw;
		this.prevPitch = this.rotPitch;
		this.particleMaxAge = age;
		EntityLivingBase renderentity = FMLClientHandler.instance().getClient().renderViewEntity;
		int visibleDistance = 50;

		if(!FMLClientHandler.instance().getClient().gameSettings.fancyGraphics)
			visibleDistance = 25;
		if(renderentity.getDistance(player.posX, player.posY, player.posZ) > visibleDistance)
			this.particleMaxAge = 0;
	}

	public void updateRay(double x, double y, double z)
	{
		this.tX = x;
		this.tY = y;
		this.tZ = z;

		while(this.particleMaxAge - this.particleAge < 4) 
			this.particleMaxAge += 1;
	}

	@Override
	public void onUpdate()
	{
		this.prevPosX = this.player.posX;
		this.prevPosY = (this.player.posY + this.offset);
		this.prevPosZ = this.player.posZ;
		this.ptX = this.tX;
		this.ptY = this.tY;
		this.ptZ = this.tZ;
		this.prevYaw = this.rotYaw;
		this.prevPitch = this.rotPitch;
		float xd = (float)(this.player.posX - this.tX);
		float yd = (float)(this.player.posY + this.offset - this.tY);
		float zd = (float)(this.player.posZ - this.tZ);
		this.length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
		double var7 = MathHelper.sqrt_double(xd * xd + zd * zd);
		this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D);

		for(this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D); 
			this.rotPitch - this.prevPitch < -180.0F; this.prevPitch -= 360.0F);
		while(this.rotPitch - this.prevPitch >= 180.0F)
			this.prevPitch += 360.0F;
		while(this.rotYaw - this.prevYaw < -180.0F)
			this.prevYaw -= 360.0F;
		while(this.rotYaw - this.prevYaw >= 180.0F)
			this.prevYaw += 360.0F;
		if(this.impact > 0)
			this.impact -= 1;
		if(this.particleAge++ >= this.particleMaxAge)
			this.setDead();
	}

	public void setRGB(float r, float g, float b) 
	{
		this.particleRed = r;
		this.particleGreen = g;
		this.particleBlue = b;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public void setEndMod(float endMod)
	{
		this.endMod = endMod;
	}

	public void setReverse(boolean reverse)
	{
		this.reverse = reverse;
	}

	public void setPulse(boolean pulse)
	{
		this.pulse = pulse;
	}

	public void setRotationspeed(int rotationspeed)
	{
		this.rotationspeed = rotationspeed;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
	{
		tessellator.draw();
		GL11.glPushMatrix();
		float var9 = 1.0F;
		float slide = (float)this.worldObj.getWorldTime();
		float rot = (float)(this.worldObj.provider.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
		float size = 1.0F;

		if(this.pulse) 
		{
			size = Math.min(this.particleAge / 4.0F, 1.0F);
			size = (float)(this.prevSize + (size - this.prevSize) * f);
		}

		float op = 0.4F;

		if((this.pulse) && (this.particleMaxAge - this.particleAge <= 4)) 
		{
			op = 0.4F - (4 - (this.particleMaxAge - this.particleAge)) * 0.1F;
		}

		switch(this.type)
		{
		default:
			// In case I make another type of beam, I can change the beam texture here
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

		if(this.reverse) 
			var11 *= -1.0F; // why name your variables "var11"? That is so un-helpful

		float var12 = -var11 * 0.2F - MathHelper.floor_float(-var11 * 0.1F);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 1);
		GL11.glDepthMask(false);
		double prex = this.player.prevPosX;
		double prey = this.player.prevPosY + this.offset;
		double prez = this.player.prevPosZ;
		double px = this.player.posX;
		double py = this.player.posY + this.offset;
		double pz = this.player.posZ;
		prex -= MathHelper.cos(this.player.prevRotationYaw / 180.0F * 3.141593F) * 0.066F;
		prey -= 0.06D;
		prez -= MathHelper.sin(this.player.prevRotationYaw / 180.0F * 3.141593F) * 0.04F;
		Vec3 vec3d = this.player.getLook(1.0F);
		prex += vec3d.xCoord * 0.3D;
		prey += vec3d.yCoord * 0.3D;
		prez += vec3d.zCoord * 0.3D;
		px -= MathHelper.cos(this.player.rotationYaw / 180.0F * 3.141593F) * 0.066F;
		py -= 0.06D;
		pz -= MathHelper.sin(this.player.rotationYaw / 180.0F * 3.141593F) * 0.04F;
		vec3d = this.player.getLook(1.0F);
		px += vec3d.xCoord * 0.3D;
		py += vec3d.yCoord * 0.3D;
		pz += vec3d.zCoord * 0.3D;
		float xx = (float)(prex + (px - prex) * f - EntityFX.interpPosX);
		float yy = (float)(prey + (py - prey) * f - EntityFX.interpPosY);
		float zz = (float)(prez + (pz - prez) * f - EntityFX.interpPosZ);
		GL11.glTranslated(xx, yy, zz);
		float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
		float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
		GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
		GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
		double var44 = -0.15D * size;
		double var17 = 0.15D * size;
		double var44b = -0.15D * size * this.endMod;
		double var17b = 0.15D * size * this.endMod;
		GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);

		for(int t = 0; t < 3; t++)
		{
			double var29 = this.length * size * var9;
			double var31 = 0.0D;
			double var33 = 1.0D;
			double var35 = -1.0F + var12 + t / 3.0F;
			double var37 = this.length * size * var9 + var35;
			GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
			tessellator.startDrawingQuads();
			tessellator.setBrightness(200);
			tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, op);
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
		this.prevSize = size;
	}
}