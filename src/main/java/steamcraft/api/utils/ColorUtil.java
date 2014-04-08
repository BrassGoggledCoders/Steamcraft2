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
 * File created @ [Mar 13, 2014, 5:41:57 PM]
 */
package steamcraft.api.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import steamcraft.api.INBT;
import net.minecraft.nbt.NBTTagCompound;

import com.google.common.collect.Multiset.Entry;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ColorUtil implements INBT
{
	public static Map<String, String> specialColors = new HashMap<String, String>(); static 
	{
		specialColors.put("[black]", "¤0");
		specialColors.put("[navy]", "¤1");
		specialColors.put("[blue]", "¤1");
		specialColors.put("[dblue]", "¤1");
		specialColors.put("[darkblue]", "¤1");
		specialColors.put("[green]", "¤2");
		specialColors.put("[dgreen]", "¤2");
		specialColors.put("[darkgreen]", "¤2");
		specialColors.put("[dcyan]", "¤3");
		specialColors.put("[darkcyan]", "¤3");
		specialColors.put("[daqua]", "¤3");
		specialColors.put("[darkaqua]", "¤3");
		specialColors.put("[darkred]", "¤4");
		specialColors.put("[red]", "¤4");
		specialColors.put("[dred]", "¤4");
		specialColors.put("[purple]", "¤5");
		specialColors.put("[orange]", "¤6");
		specialColors.put("[grey]", "¤7");
		specialColors.put("[gray]", "¤7");
		specialColors.put("[dgrey]", "¤8");
		specialColors.put("[darkgrey]", "¤8");
		specialColors.put("[dgray]", "¤8");
		specialColors.put("[darkgray]", "¤8");
		specialColors.put("[indigo]", "¤9");
		specialColors.put("[lblue]", "¤9");
		specialColors.put("[lightblue]", "¤9");
		specialColors.put("[lime]", "¤a");
		specialColors.put("[limegreen]", "¤a");
		specialColors.put("[aqua]", "¤b");
		specialColors.put("[cyan]", "¤b");
		specialColors.put("[lred]", "¤c");
		specialColors.put("[lightred]", "¤c");
		specialColors.put("[pink]", "¤d");
		specialColors.put("[yellow]", "¤e");
		specialColors.put("[white]", "¤f");
		specialColors.put("[random]", "¤k");
		specialColors.put("[bold]", "¤l");
		specialColors.put("[b]", "¤l");
		specialColors.put("[s]", "¤m");
		specialColors.put("[strike]", "¤m");
		specialColors.put("[u]", "¤n");
		specialColors.put("[underline]", "¤n");
		specialColors.put("[italics]", "¤o");
		specialColors.put("[i]", "¤o");
		specialColors.put("[reset]", "¤r");
		specialColors.put("[r]", "¤r");
		specialColors.put("[#0]", "¤0");
		specialColors.put("[#1]", "¤1");
		specialColors.put("[#2]", "¤2");
		specialColors.put("[#3]", "¤3");
		specialColors.put("[#4]", "¤4");
		specialColors.put("[#5]", "¤5");
		specialColors.put("[#6]", "¤6");
		specialColors.put("[#7]", "¤7");
		specialColors.put("[#8]", "¤8");
		specialColors.put("[#9]", "¤9");
		specialColors.put("[#a]", "¤a");
		specialColors.put("[#b]", "¤b");
		specialColors.put("[#c]", "¤c");
		specialColors.put("[#d]", "¤d");
		specialColors.put("[#e]", "¤e");
		specialColors.put("[#f]", "¤f");
	}

	public static HashMap<String, Integer> namedColors = new HashMap<String, Integer>(); static 
	{
		namedColors.put("white", 0xffffff);
		namedColors.put("silver", 0xc0c0c0);
		namedColors.put("gray", 0x808080);
		namedColors.put("black", 0x000000);
		namedColors.put("red", 0xff0000);
		namedColors.put("maroon", 0x800000);
		namedColors.put("yellow", 0xffff00);
		namedColors.put("olive", 0x808000);
		namedColors.put("lime", 0x00ff00);
		namedColors.put("green", 0x008000);
		namedColors.put("aqua", 0x00ffff);
		namedColors.put("teal", 0x008080);
		namedColors.put("blue", 0x0000ff);
		namedColors.put("navy", 0x000080);
		namedColors.put("fuchsia", 0xff00ff);
		namedColors.put("purple", 0x800080);
		namedColors.put("brick", 0xB22222);
		namedColors.put("darkred", 0x8B0000);
		namedColors.put("salmon", 0xFA8072);
		namedColors.put("pink", 0xff1493);
		namedColors.put("orange", 0xff4500);
		namedColors.put("gold", 0xffd700);
		namedColors.put("magenta", 0xff00ff);
		namedColors.put("violet", 0x9400d3);
		namedColors.put("indigo", 0x483D8B);
		namedColors.put("limegreen", 0x32cd32);
		namedColors.put("darkgreen", 0x006400);
		namedColors.put("cyan", 0x00ffff);
		namedColors.put("steel", 0x4682b4);
		namedColors.put("darkblue", 0x00008b);
		namedColors.put("brown", 0x8b4513);
		namedColors.put("lightgray", 0xd3d3d3);
		namedColors.put("darkgray", 0xa9a9a9);
	}

	/** Gets a color's hex code from a given color name. */
	public static Integer getHexColorForName(Object obj)
	{
		String name = null;

		if (obj instanceof String) 
			name = ((String) obj);
		if (name != null)
		{
			for (String key : namedColors.keySet())
			{
				if (key.equalsIgnoreCase(name)) 
				{
					return namedColors.get(key);
				}
			}
		} else 
		{
			//return Calc.toInteger(obj);
		}

		return null;
	}

	/** Converts color and format tags in input strings to chat color codes. */
	public static String convertMagicColors(String input) 
	{
		input = input.replaceAll("\\[/.*?\\]", "¤r");
		input = input.replaceAll("</.*?>", "¤r");

		for (java.util.Map.Entry<String, String> entry : specialColors.entrySet())
		{
			input = input.replace(entry.getKey(), entry.getValue());
			input = input.replace(entry.getKey().replace('[', '<').replace(']', '>'), entry.getValue());
		}

		return input;
	}

	/** Red, green, blue */
	public double r, g, b;

	private boolean invisible = false;

	public ColorUtil(double r, double g, double b) 
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public ColorUtil(int hex) 
	{
		this.r = red(hex);
		this.g = green(hex);
		this.b = blue(hex);
	}

	public ColorUtil(ColorUtil c)
	{
		this.r = c.r;
		this.g = c.g;
		this.b = c.b;
	}

	/** Creates white by default. */
	public ColorUtil() 
	{
		r = g = b = 1.0D;
	}

	private static Random rand = new Random();

	/** Creates a random (bright) color. */
	public ColorUtil randomizeBright() 
	{
		do {
			r = rand.nextFloat();
			g = rand.nextFloat();
			b = rand.nextFloat();
		} while (r + g + b < 0.8F);

		return this;
	}

	/** Creates a random color. */
	public ColorUtil randomize() 
	{
		r = rand.nextFloat();
		g = rand.nextFloat();
		b = rand.nextFloat();
		return this;
	}

	public static ColorUtil randomBrightColor() 
	{
		return new ColorUtil().randomizeBright();
	}

	public static ColorUtil randomColor()
	{
		return new ColorUtil().randomize();
	}

	/** Returns a color as a hex code (0xRRGGBB). */
	public int getHex() 
	{
		int r255 = (int) Math.round(r * 255) & 0xff;
		int g255 = (int) Math.round(g * 255) & 0xff;
		int b255 = (int) Math.round(b * 255) & 0xff;
		return r255 << 16 | g255 << 8 | b255;
	}

	/** Sets visibility. Used for things like particles. */
	public ColorUtil setVisible(boolean visibility) 
	{
		invisible = !visibility;
		return this;
	}

	/** Is the color visible? */
	public boolean isVisible() 
	{
		return !invisible;
	}

	/** Mixes color with another. */
	public ColorUtil mixWith(ColorUtil c) 
	{
		return new ColorUtil((r + c.r) / 2D, (g + c.g) / 2D, (b + c.b) / 2D);
	}

	/** Mixes a color with another. */
	public ColorUtil mixWith(double cr, double cg, double cb) 
	{
		return new ColorUtil((r + cr) / 2D, (g + cg) / 2D, (b + cb) / 2D);
	}

	/** Converts a hex code into a color. */
	public static ColorUtil valueOf(int hex)
	{
		return new ColorUtil(red(hex), green(hex), blue(hex));
	}

	public ColorUtil copy() 
	{
		return new ColorUtil(r, g, b);
	}

	/** Sets a color from a given hex code. */
	public ColorUtil setTo(int hex) 
	{
		r = red(hex);
		g = green(hex);
		b = blue(hex);
		return this;
	}

	public void setTo(double cr, double cg, double cb) 
	{
		r = cr;
		g = cg;
		b = cb;
	}

	/** Gets red from the hex color (returns a double). */
	public static double red(int hex) 
	{
		return (1D / 255D) * ((hex & 0xff0000) >> 16);
	}

	/** Gets green from the hex color (returns a double). */
	public static double green(int hex) 
	{
		return (1D / 255D) * ((hex & 0x00ff00) >> 8);
	}

	/** Gets blue from the hex color (returns a double). */
	public static double blue(int hex) 
	{
		return (1D / 255D) * ((hex & 0x0000ff));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) 
	{
		tagCompound.setDouble("r", r);
		tagCompound.setDouble("g", g);
		tagCompound.setDouble("b", b);
		tagCompound.setBoolean("invisible", invisible);
		return tagCompound;
	}

	@Override
	public INBT readFromNBT(NBTTagCompound tagCompound) 
	{
		r = tagCompound.getDouble("r");
		g = tagCompound.getDouble("g");
		b = tagCompound.getDouble("b");
		invisible = tagCompound.getBoolean("invisible");
		return this;
	}

	/** Makes a color form the hex code. */
	public static ColorUtil fromHex(int hex) 
	{
		return new ColorUtil().setTo(hex);
	}
}
