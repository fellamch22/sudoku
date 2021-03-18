
/* XYZindex.java
 * Createurs : Amayas HADJ MOHAND
 * Date de creation : 04/2020 */


public class XYZindex {
	protected int x;
	protected int y;
	protected int z;
	protected Couple couple;

	XYZindex(int x, int y, int z, Couple c) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.couple = c;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getLigne() {
		return couple.x;
	}

	public int getColonne() {
		return couple.y;
	}

	public String toString() {
		return " x= " + this.x + " ,y= " + this.y + " ,z=" + this.z + " , i= " + couple.x + ", y=" + couple.y;
	}

}
