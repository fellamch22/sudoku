
/* Couple.java
 * Createur :  Amayas HADJ MOHAND. 
 * Date de creation : 02/2020
 * Description : Couple d'inversion utilise par le generateur de grilles.*/

public class Couple {
	int x;
	int y;

	Couple(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
}
