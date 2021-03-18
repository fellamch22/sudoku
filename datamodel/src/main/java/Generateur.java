
/* Generateur.java
 * Createurs : Amayas HADJ MOHAND
 * Date de creation : 03/2020
 * Description : Generer une grille de sudoku aleatoire*/


import java.util.ArrayList;

public class Generateur {

// generateur de la grille

	public static ArrayList<Couple> ligneAlCouple(Grille g) {
		ArrayList<Couple> list = new ArrayList<Couple>();
		int i = 0;
		while (i < g.TAILLE_SOUSZONE_LIGNE) {
			int r1 = (int) (Math.random() * (g.TAILLE_SOUSZONE_LIGNE));
			int r2 = (int) (Math.random() * (g.TAILLE_SOUSZONE_LIGNE));

			list.add(new Couple(r1 + (i * g.TAILLE_SOUSZONE_LIGNE), r2 + (i * g.TAILLE_SOUSZONE_LIGNE)));
			i++;
		}
		return list;
	}

	public static ArrayList<Couple> colonneAlCouple(Grille g) {
		ArrayList<Couple> list = new ArrayList<Couple>();
		int i = 0;
		while (i < g.TAILLE_SOUSZONE_COL) {
			int r1 = (int) (Math.random() * (g.TAILLE_SOUSZONE_COL));
			int r2 = (int) (Math.random() * (g.TAILLE_SOUSZONE_COL));

			list.add(new Couple(r1 + (i * g.TAILLE_SOUSZONE_COL), r2 + (i * g.TAILLE_SOUSZONE_COL)));
			i++;
		}
		return list;

	}

	public static void unePermutation(Grille g) {

		for (Couple c : ligneAlCouple(g)) {
			g.echangeColonne(c.x, c.y);
		}

		for (Couple c : colonneAlCouple(g)) {
			g.echangeLigne(c.x, c.y);
		}

	}

	public static void GenerateurNbAleatoires(Grille g) {
		int m = 0;
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < g.TAILLE_COTE_PLATEAU; i++) {
			l.add(i + 1);
		}

		for (int i = 0; i < g.TAILLE_COTE_PLATEAU; i++) {
			int nn = (int) l.remove((int) (Math.random() * ((g.TAILLE_COTE_PLATEAU - m))));
			m++;
			g.t[0][i].val = nn;
		}
		for (int z = 1; z <= g.TAILLE_COTE_PLATEAU; z++) {
			int i = 1;
			while (i < g.TAILLE_COTE_PLATEAU) {
				g.t[i][(((i % g.TAILLE_SOUSZONE_COL) * g.TAILLE_SOUSZONE_LIGNE + i / g.TAILLE_SOUSZONE_COL) + z - 1)
						% (g.TAILLE_COTE_PLATEAU)].val = g.t[0][z - 1].val;

				i++;
			}
		}
		unePermutation(g);
	}

	public static void viderAlea(Grille g) {
		int Max = g.TAILLE_COTE_PLATEAU;
		int Min = 1;
		int pctCasesGardees = 100;
		int pctCasesSupprimees = 0;
		int nbRollback = 0;
		while (pctCasesSupprimees < g.pctTrousASupprimer
				&& nbRollback < g.TAILLE_COTE_PLATEAU * g.TAILLE_COTE_PLATEAU * g.TAILLE_COTE_PLATEAU) {
			pctCasesGardees = g.nbChiffres() * 100 / (g.TAILLE_COTE_PLATEAU * g.TAILLE_COTE_PLATEAU);
			pctCasesSupprimees = (100 - pctCasesGardees);
			System.out.println("% de chiffres Supprimes : " + pctCasesSupprimees + "%");
			Backtracking.compteur = 0;
			int r1 = Min + (int) (Math.random() * ((Max - Min)));
			int r2 = Min + (int) (Math.random() * ((Max - Min)));
			System.out.println("On vide la valeur :  " + r1 + "*" + r2);
			if (g.t[r1][r2].val != 0) {
				int tmp = g.t[r1][r2].val;
				g.t[r1][r2].val = 0;
				g.GeneratePossibility();
				Backtracking.backtracking(g);
				if (Backtracking.compteur > 1) {
					//Si le fait d avoir enleve ce chiffre fait qu on a desormais plus d une unique solution , on rollback
					System.out.println("Rollback !");
					g.t[r1][r2].val = tmp;
					nbRollback++;
				} else {
					// on reinitialise le nombre de rollback successifs
					nbRollback = 0;
				}
			}
		}
		if (nbRollback >= g.TAILLE_COTE_PLATEAU * g.TAILLE_COTE_PLATEAU * g.TAILLE_COTE_PLATEAU) {
			System.out.println("Nombre d'essais maximums atteints. \nTaux de vidage actuel de la grille : "
					+ pctCasesSupprimees + "% au lieu des " + g.pctTrousASupprimer + "% demandes");
		}
		Backtracking.compteur = 0;
		Backtracking.display = false;
	}

	public static Grille GenerateurGrille() {
		Backtracking.display = false;
		Grille g = new Grille();
		GenerateurNbAleatoires(g);
		viderAlea(g);
		Backtracking.display = true;
		g.GeneratePossibility();
		g.afficher();
		return g;
	}

// fin Generateur
}