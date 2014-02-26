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
 * File created @ [Feb 17, 2014, 3:17:01 PM]
 */
package common.steamcraft.api;

/**
 * @author MrArcane111
 *
 */
public class CoupleUtil {
	private Object firstObject;
	private Object secondObject;

	public Object getFirstObject() {
		return this.firstObject;
	}

	public void setFirstObject(Object firstObject) {
		this.firstObject = firstObject;
	}

	public Object getSecondObject() {
		return this.secondObject;
	}

	public void setSecondObject(Object secondObject) {
		this.secondObject = secondObject;
	}

	public CoupleUtil(Object firstObject, Object secondObject) {
		this.firstObject = firstObject;
		this.secondObject = secondObject;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (this.firstObject == null ? 0 : this.firstObject.hashCode());
		result = 31 * result + (this.secondObject == null ? 0 : this.secondObject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		CoupleUtil other = (CoupleUtil)obj;
		
		if (this.firstObject == null) {
			if (other.firstObject != null) {
				return false;
			}
		} else if (!this.firstObject.equals(other.firstObject)) {
			return false;
		}
		if (this.secondObject == null) {
			if (other.secondObject != null) {
				return false;
			}
		} else if (!this.secondObject.equals(other.secondObject)) {
			return false;
		}
		
		return true;
	}
}
