
/* StrategyXYWing.java
 * Createurs : Amayas HADJ MOHAND
 * Date de creation : 04/2020 */



import java.util.ArrayList;

public class StrategyXYWing {

	public static ArrayList<XYZindex> isXY(Grille g) {
		ArrayList<XYZindex> list = new ArrayList<XYZindex>();
		int x = 0, y = 0;

		for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
			for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {
				if (g.t[ligne][col].Possibility.size() == 2) {
					Couple c = new Couple(ligne, col);
					list.add(new XYZindex(g.t[ligne][col].getPossibility().get(0),
							g.t[ligne][col].getPossibility().get(1), 0, c));
					list.add(new XYZindex(g.t[ligne][col].getPossibility().get(1),
							g.t[ligne][col].getPossibility().get(0), 0, c));
				}
			}
		}
		return list;
	}

	public static XYZindex isYZInLine(Grille g, XYZindex n) {

		// System.out.println("Y = " + n.y + ", Z = " + n.z);
		for (int i = 0; i < g.TAILLE_COTE_PLATEAU; i++) {
			boolean b1 = (i != n.couple.x && i % g.TAILLE_SOUSZONE_LIGNE != n.couple.x % g.TAILLE_SOUSZONE_LIGNE);

			if (b1 && g.t[n.couple.x][i].Possibility.size() == 2 && g.t[n.couple.x][i].Possibility.contains(n.y)) {

				int z = 0;

				if (g.t[n.couple.x][i].Possibility.get(0) == n.y)

					z = g.t[n.couple.x][i].Possibility.get(1);

				else

					z = g.t[n.couple.x][i].Possibility.get(0);

				return new XYZindex(n.x, n.y, z, new Couple(n.couple.x, i));
			}
		}

		return null;
	}

	public static XYZindex isXZinColonne(Grille g, XYZindex n) {

		for (int i = 0; i < g.TAILLE_COTE_PLATEAU; i++) {
			boolean b1 = (i != n.couple.x && i % g.TAILLE_SOUSZONE_COL != n.couple.x % g.TAILLE_SOUSZONE_COL);
			if (b1 && g.t[i][n.couple.y].Possibility.size() == 2 && g.t[i][n.couple.y].Possibility.contains(n.x)) {

				int z = 0;

				if (g.t[i][n.couple.y].Possibility.get(0) == n.x)

					z = g.t[i][n.couple.y].Possibility.get(1);

				else

					z = g.t[i][n.couple.y].Possibility.get(0);

				return new XYZindex(n.x, n.y, z, new Couple(i, n.couple.y));

			}
		}
		return null;
	}

	public static XYZindex isXZinBox(Grille g, XYZindex n) {

		for (int i = (n.couple.x / g.TAILLE_SOUSZONE_LIGNE)
				* g.TAILLE_SOUSZONE_LIGNE; i < (n.couple.x / g.TAILLE_SOUSZONE_LIGNE) * g.TAILLE_SOUSZONE_LIGNE
						+ 3; i++) {
			for (int j = (n.couple.y / g.TAILLE_SOUSZONE_COL)
					* g.TAILLE_SOUSZONE_COL; j < (n.couple.y / g.TAILLE_SOUSZONE_COL) * g.TAILLE_SOUSZONE_COL
							+ 3; j++) {
				boolean b1 = i == n.couple.x && j == n.couple.y;

				if (!b1 && g.t[i][j].Possibility.size() == 2 && g.t[i][j].Possibility.contains(n.x)) {

					int z = 0;

					if (g.t[i][j].Possibility.get(0) == n.x)

						z = g.t[i][j].Possibility.get(1);

					else

						z = g.t[i][j].Possibility.get(0);

					return new XYZindex(n.x, n.y, z, new Couple(i, j));

				}
			}
		}
		return null;

	}

	public static XYZindex isYZinBox(Grille g, XYZindex n) {

		for (int i = (n.couple.x / g.TAILLE_SOUSZONE_LIGNE)
				* g.TAILLE_SOUSZONE_LIGNE; i < (n.couple.x / g.TAILLE_SOUSZONE_LIGNE) * g.TAILLE_SOUSZONE_LIGNE
						+ 3; i++) {
			for (int j = (n.couple.y / g.TAILLE_SOUSZONE_COL)
					* g.TAILLE_SOUSZONE_COL; j < (n.couple.y / g.TAILLE_SOUSZONE_COL) * g.TAILLE_SOUSZONE_COL
							+ 3; j++) {
				boolean b1 = i == n.couple.x && j == n.couple.y;

				if (!b1 && g.t[i][j].Possibility.size() == 2 && g.t[i][j].Possibility.contains(n.y)) {

					int z = 0;

					if (g.t[i][j].Possibility.get(0) == n.y)

						z = g.t[i][j].Possibility.get(1);

					else

						z = g.t[i][j].Possibility.get(0);

					return new XYZindex(n.x, n.y, z, new Couple(i, j));

				}
			}
		}
		return null;

	}

	public static boolean isTheSameZ(Grille g, XYZindex n) {
		if (isYZInLine(g, n) == null || isXZinColonne(g, n) == null)
			return false;
		else {
			XYZindex a = isYZInLine(g, n);
			XYZindex b = isXZinColonne(g, n);
			if (a != null && b != null && a.z == b.z)
				return true;

		}
		return false;
	}

	public static int indexAppliable(Grille g) {
		int s = -1;
		ArrayList<XYZindex> list = isXY(g);
		if (list == null)
			return -1;
		for (int i = 0; i < list.size(); i++) {
			if (isTheSameZ(g, list.get(i)))
				s = i;
		}
		return s;
	}

	public static boolean isTheSameZ2(Grille g, XYZindex n) {
		if (isYZInLine(g, n) == null || isXZinBox(g, n) == null)
			return false;
		else {
			XYZindex a = isYZInLine(g, n);
			XYZindex b = isXZinBox(g, n);
			if (a != null && b != null && a.z == b.z)
				return true;
		}
		return false;
	}

	public static int indexAppliable2(Grille g) {
		int s = -1;
		ArrayList<XYZindex> list = isXY(g);
		if (list == null)
			return -1;
		for (int i = 0; i < list.size(); i++) {
			if (isTheSameZ2(g, list.get(i)))
				s = i;
		}
		return s;
	}

	public static boolean isTheSameZ3(Grille g, XYZindex n) {
		if (isYZinBox(g, n) == null || isXZinColonne(g, n) == null)
			return false;
		else {
			XYZindex a = isYZinBox(g, n);
			XYZindex b = isXZinColonne(g, n);
			if (a != null && b != null && a.z == b.z)
				return true;
		}
		return false;
	}

	public static int indexAppliable3(Grille g) {
		int s = -1;
		ArrayList<XYZindex> list = isXY(g);
		if (list == null)
			return -1;
		for (int i = 0; i < list.size(); i++) {
			if (isTheSameZ3(g, list.get(i)))
				s = i;
		}
		return s;
	}

	public static boolean applyStrategy1(Grille g) {
		ArrayList<XYZindex> list = isXY(g);
		if (list == null)
			return false;

		int a = indexAppliable(g);
		if (a == -1) {
			System.out.println("can't apply the strategy");
			return false;
		}
		XYZindex b = list.get(a);
		XYZindex c = isXZinColonne(g, b);
		XYZindex d = isYZInLine(g, b);
		if (g.t[c.couple.x][d.couple.y].getPossibility().contains(c.z)) {
			boolean bool = g.t[c.couple.x][d.couple.y].getPossibility().remove((Integer) c.z);
			if (bool)
				System.out.println("JUST REMOVED " + c.z);
			return bool;
		}
		return false;
	}

	public static boolean applyStrategy2(Grille g) {
		ArrayList<XYZindex> list = isXY(g);
		if (list == null)
			return false;

		int a = indexAppliable2(g);
		if (a == -1) {
			System.out.println("can't apply the strategy");
			return false;
		}
		XYZindex b = list.get(a);
		XYZindex c = isYZInLine(g, b);
		XYZindex d = isXZinBox(g, b);

		boolean bool = true;
		boolean bool1 = false;
		boolean bool2 = false;

		for (int i = (b.couple.y / g.TAILLE_SOUSZONE_LIGNE)
				* g.TAILLE_SOUSZONE_LIGNE; i < (b.couple.y / g.TAILLE_SOUSZONE_LIGNE) * g.TAILLE_SOUSZONE_LIGNE
						+ 3; i++) {

			if (g.t[b.x][i].Possibility.contains(c.z)) {
				bool1 = bool && g.t[b.x][i].Possibility.remove((Integer) c.z);
				if (bool1)
					System.out.println("JUST REMOVED " + c.z);

			}
		}
		for (int i = (c.couple.y / g.TAILLE_SOUSZONE_LIGNE)
				* g.TAILLE_SOUSZONE_LIGNE; i < (c.couple.y / g.TAILLE_SOUSZONE_LIGNE) * g.TAILLE_SOUSZONE_LIGNE
						+ 3; i++) {

			if (g.t[d.x][i].Possibility.contains(c.z)) {
				bool2 = bool && g.t[d.x][i].Possibility.remove((Integer) c.z);
				if (bool2)
					System.out.println("JUST REMOVED +" + c.z);

			}
		}
		return bool1 || bool2;
	}

	public static boolean applyStrategy3(Grille g) {
		ArrayList<XYZindex> list = isXY(g);
		if (list == null)
			return false;

		int a = indexAppliable3(g);
		if (a == -1) {
			System.out.println("can't apply the strategy");
			return false;
		}
		XYZindex b = list.get(a);
		XYZindex c = isYZinBox(g, b);
		XYZindex d = isXZinColonne(g, b);

		boolean bool = true;
		boolean bool1 = false;
		boolean bool2 = false;

		for (int i = (b.couple.x / g.TAILLE_SOUSZONE_COL)
				* g.TAILLE_SOUSZONE_COL; i < (b.couple.x / g.TAILLE_SOUSZONE_COL) * g.TAILLE_SOUSZONE_COL + 3; i++) {

			if (g.t[i][b.couple.y].Possibility.contains(c.z)) {
				bool1 = bool && g.t[i][b.couple.y].Possibility.remove((Integer) c.z);
				if (bool1)
					System.out.println("JUST REMOVED +" + c.z);
			}
		}

		for (int i = (d.couple.x / g.TAILLE_SOUSZONE_COL)
				* g.TAILLE_SOUSZONE_COL; i < (d.couple.x / g.TAILLE_SOUSZONE_COL) * g.TAILLE_SOUSZONE_COL + 3; i++) {
			if (g.t[i][c.couple.y].Possibility.contains(c.z)) {

				bool2 = bool && g.t[i][c.couple.y].Possibility.remove((Integer) c.z);
				if (bool2)
					System.out.println("JUST REMOVED +" + c.z);
			}
		}
		return bool1 || bool2;
	}

	public static boolean applyStrategy(Grille g) {
		return applyStrategy1(g) || applyStrategy2(g) || applyStrategy3(g);
	}

}
