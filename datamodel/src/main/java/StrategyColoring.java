
/* Coloring.java
 * Createur : Li Fang SU
 * Date de creation : 04/2020
 * Description : Permettant de sauvegarder la grille, tester un chiffre sur une case
 * a faible nombre de possibilite, et restaurer la grille en cas d'erreur */

public class StrategyColoring {

	// BACKTRACKING
	static Grille sauvegarde;
	static Case[][] tsav;
	static int level = 1;

	public static boolean coloring(Grille g) {
		
		if (level < 0) {
			return false;
		}
		// Si la sauvegarde existe, on la restore
		if (sauvegarde != null) {
			System.out.println("Coloring Failure. Rollback to previous backup. Try another number.");
			// DEBUG : sauvegarde.afficher();
			// DEBUG : sauvegarde.afficherPossibility();
			g.CopyGrille(sauvegarde);

		}
		// sinon on en cree une

		// DEBUG : System.out.println("Coloring - Sauvegarde Grille : " + level);
		sauvegarde = new Grille(g);
		// DEBUG : sauvegarde.afficher();
		// DEBUG : sauvegarde.afficherPossibility();

		for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
			for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {

				if (g.t[ligne][col].getValue() == g.VALEUR_VIDE && g.t[ligne][col].getPossibility().size() == 2) {
					String str = "(" + (g.nbChiffres() + 1) + "/" + g.nbCases
							+ "?)  Coloring Check Point : We test one of the two numbers => " + ligne + " * " + col
							+ " : " + g.t[ligne][col].getPossibility() + " : "
							+ g.t[ligne][col].getPossibility().get(level) + " write on the Sudoku ";
					g.writeNumber(str, ligne, col, g.t[ligne][col].getPossibility().get(level));
					level--;
					return true;
				}
			}
		}

		return false; // completed
	}

}

// FIN COLORING
