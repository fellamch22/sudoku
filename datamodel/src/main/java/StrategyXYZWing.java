
/* StrategyXYZWing.java
 * Createurs : Amayas HADJ MOHAND
 * Date de creation : 04/2020 */

import java.util.ArrayList;

public class StrategyXYZWing {

	public static ArrayList<XYZindex> isXYZ(Grille g) {
		ArrayList<XYZindex> list = new ArrayList<XYZindex>();
		int x = 0, y = 0, z = 0;
		for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
			for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {

				if (g.t[ligne][col].Possibility.size() == 3) {
					Couple c = new Couple(ligne, col);
					list.add(new XYZindex(g.t[ligne][col].getPossibility().get(0),
							g.t[ligne][col].getPossibility().get(1), g.t[ligne][col].getPossibility().get(2), c));
					list.add(new XYZindex(g.t[ligne][col].getPossibility().get(0),
							g.t[ligne][col].getPossibility().get(2), g.t[ligne][col].getPossibility().get(1), c));
					list.add(new XYZindex(g.t[ligne][col].getPossibility().get(1),
							g.t[ligne][col].getPossibility().get(0), g.t[ligne][col].getPossibility().get(2), c));
					list.add(new XYZindex(g.t[ligne][col].getPossibility().get(1),
							g.t[ligne][col].getPossibility().get(2), g.t[ligne][col].getPossibility().get(0), c));
					list.add(new XYZindex(g.t[ligne][col].getPossibility().get(2),
							g.t[ligne][col].getPossibility().get(0), g.t[ligne][col].getPossibility().get(1), c));
					list.add(new XYZindex(g.t[ligne][col].getPossibility().get(2),
							g.t[ligne][col].getPossibility().get(1), g.t[ligne][col].getPossibility().get(0), c));

				}
			}
		}
		return list;
	}

	public static boolean isXZinBox(Grille g, XYZindex n) {

		boolean b = false;
		for (int i = (n.couple.x / g.TAILLE_SOUSZONE_LIGNE)
				* g.TAILLE_SOUSZONE_LIGNE; i < (n.couple.x / g.TAILLE_SOUSZONE_LIGNE) * g.TAILLE_SOUSZONE_LIGNE
						+ 3; i++) {
			for (int j = (n.couple.y / g.TAILLE_SOUSZONE_COL)
					* g.TAILLE_SOUSZONE_COL; j < (n.couple.y / g.TAILLE_SOUSZONE_COL) * g.TAILLE_SOUSZONE_COL
							+ 3; j++) {
				boolean b1 = i == n.couple.x && j == n.couple.y;

				if (!b1 && g.t[i][j].Possibility.size() == 2 && g.t[i][j].Possibility.contains(n.x)
						&& g.t[i][j].Possibility.contains(n.z)) {

					b = true;
					System.out.println("l'indice de la case est: i = " + i + " , j = " + j);
					System.out.println(g.t[i][j].Possibility.toString());
				}
			}
		}
		return b;

	}

	public static boolean isYZinColonne(Grille g, XYZindex n) {
		boolean b = false;
		System.out.println("Y = " + n.y + ", Z = " + n.z);
		for (int i = 0; i < g.TAILLE_COTE_PLATEAU; i++) {
			// boolean b1= i/g.TAILLE_SOUSZONE_LIGNE!=n.couple.x/g.TAILLE_SOUSZONE_LIGNE;
			boolean b1 = (i != n.couple.x);
			if (b1 && g.t[i][n.couple.y].Possibility.size() == 2 && g.t[i][n.couple.y].Possibility.contains(n.y)
					&& g.t[i][n.couple.y].Possibility.contains(n.z)) {

				b = true;

			}
		}
		return b;
	}

	public static boolean isYZInLine(Grille g, XYZindex n) {
		boolean b = false;
		System.out.println("Y = " + n.y + ", Z = " + n.z);
		for (int i = 0; i < g.TAILLE_COTE_PLATEAU; i++) {
			// boolean b1= i/g.TAILLE_SOUSZONE_LIGNE!=n.couple.x/g.TAILLE_SOUSZONE_LIGNE;
			boolean b1 = (i != n.couple.y);
			if (b1 && g.t[n.couple.x][i].Possibility.size() == 2 && g.t[n.couple.x][i].Possibility.contains(n.y)
					&& g.t[n.couple.x][i].Possibility.contains(n.z)) {

				// if(i/g.TAILLE_SOUSZONE_LIGNE!=n.couple.x/g.TAILLE_SOUSZONE_LIGNE &&
				// g.t[i][n.couple.y].Possibility.contains(n.y) &&
				// g.t[i][n.couple.y].Possibility.contains(n.z))

				b = true;

			}
		}
		return b;
	}

	/*
	 * public static boolean isYZInLine(Grille g,XYZindex n){ boolean b=false; for
	 * (int j = 0; j < g.TAILLE_COTE_PLATEAU;j++) {
	 * if(j/g.TAILLE_SOUSZONE_COL!=n.couple.y/g.TAILLE_SOUSZONE_COL &&
	 * g.t[n.couple.x][j].Possibility.contains(n.y) &&
	 * g.t[n.couple.x][j].Possibility.contains(n.z)) //
	 * if(j/g.TAILLE_SOUSZONE_COL!=n.couple.y/g.TAILLE_SOUSZONE_COL &&
	 * g.t[n.couple.y][j].Possibility.size()==2 &&
	 * g.t[n.couple.y][j].Possibility.contains(n.y) &&
	 * g.t[n.couple.y][j].Possibility.contains(n.z))
	 *
	 * b=true; } return b; }
	 */

	public static boolean isYZinRow(Grille g, XYZindex n) {
		return isYZInLine(g, n) || isYZinColonne(g, n);
	}

	public static boolean isAppliable(Grille g) {
		boolean b = false;
		ArrayList<XYZindex> list = isXYZ(g);
		if (list == null)
			return false;
		for (int i = 0; i < list.size(); i++) {
			if (isXZinBox(g, list.get(i)) && isYZinRow(g, list.get(i)))
				b = true;
		}
		return b;
	}

	public static int indexAppliable(Grille g) {
		int s = -1;
		ArrayList<XYZindex> list = isXYZ(g);
		if (list == null)
			return -1;
		for (int i = 0; i < list.size(); i++) {
			if (isXZinBox(g, list.get(i)) && isYZinRow(g, list.get(i)))
				s = i;
		}
		return s;
	}

  public static boolean applyStrategy(Grille g){
    boolean bool=false,bool1=false,bool2=false;

    ArrayList<XYZindex>list=isXYZ(g);

    if(list==null) return false;

    int a=indexAppliable(g);
    if(a==-1){
      System.out.println("can't apply the strategy");
      return false;
    }
		XYZindex b=list.get(a);
    System.out.println("X= :"+list.get(a).x+ " ,Y = :"+list.get(a).y + " ,Z = :"+list.get(a).z);

    if(isYZInLine(g,b)) {
      System.out.println("         IT IS IN LIGNE          ");
      for( int i=(b.couple.y / g.TAILLE_SOUSZONE_COL) * g.TAILLE_SOUSZONE_COL;i< (b.couple.y / g.TAILLE_SOUSZONE_COL)*g.TAILLE_SOUSZONE_COL+3;i++){
        boolean b1=(i==b.couple.y);
        if(!b1 && g.t[b.couple.x][i].Possibility.contains(b.z)) {
          bool1= bool1 || g.t[b.couple.x][i].Possibility.remove((Integer)b.z);
          if(bool1) System.out.println("YZ IS IN LINE -> JUST DELETED THE POSSIBILITY :"+b.z);

        }
    }

  }
  if(isYZinColonne(g,b)){
    System.out.println("         IT IS IN COLONE          ");
    for( int i=(b.couple.x / g.TAILLE_SOUSZONE_LIGNE) * g.TAILLE_SOUSZONE_LIGNE;i< (b.couple.x / g.TAILLE_SOUSZONE_LIGNE)*g.TAILLE_SOUSZONE_LIGNE+3;i++){
      boolean b1=(i==b.couple.x);
      if(!b1 && g.t[i][b.couple.y].Possibility.contains(b.z)){
        bool2=bool2 || g.t[i][b.couple.y].Possibility.remove((Integer)b.z);
        if(bool2) System.out.println(" YZ IS IN COLONNE -> JUST DELETED THE POSSIBILITY :"+b.z);
        break;
      }

  }
  }
  return bool1 || bool2;
  }

}
