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
 * File created @ [Feb 17, 2014, 3:30:22 PM]
 */
package common.steamcraft.common.block.machines;

/**
 * @author MrArcane111
 *
 */
public class MachineConstants {
	public static final float STEAM_PER_UNIT = 60.0F;

	public static enum MachineTypes {
		NONE(null, null, false), 
		MACHINE_PENDULUM("machine.pendulum", BlockCompressor.class, true);

		String name;
		public Class instanceClass;
		boolean placable;

		private MachineTypes(String name, Class instanceClass, boolean placable) {
			this.name = name;
			this.instanceClass = instanceClass;
			this.placable = placable;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Class getInstanceClass() {
			return this.instanceClass;
		}

		public void setInstanceClass(Class instanceClass) {
			this.instanceClass = instanceClass;
		}

		public boolean isPlacable() {
			return this.placable;
		}

		public void setPlacable(boolean placable) {
			this.placable = placable;
		}
	}
}
