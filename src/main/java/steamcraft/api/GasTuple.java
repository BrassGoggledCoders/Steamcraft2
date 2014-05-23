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
 * File created @ [May 7, 2014, 1:32:26 PM]
 */
package steamcraft.api;

/**
 * Does stuff. For gases.
 * 
 * @author Surseance (Johnny Eatmon)
 * 
 */
public class GasTuple
{
	int[] values;

	public GasTuple(final int... newValues)
	{
		values = newValues;
	}

	public int x()
	{
		int result = 0;

		if (values.length >= 1)
		{
			result = values[0];
		}

		return result;
	}

	public int y()
	{
		int result = 0;

		if (values.length >= 2)
		{
			result = values[1];
		}

		return result;
	}

	public int z()
	{
		int result = 0;

		if (values.length >= 3)
		{
			result = values[2];
		}

		return result;
	}

	public int getValue(final int index)
	{
		int result = 0;

		if (values.length >= index + 1)
		{
			result = values[index];
		}

		return result;
	}
}
