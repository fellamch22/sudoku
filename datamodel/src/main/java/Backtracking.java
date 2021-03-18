
/* Bakctracking.java
 * Createur : Li Fang SU
 * Date de creation : 01/2020
 * Description :l’algorithme de Backtracking va résoudre une grille de sudoku par bruteforce, 
 * en essayant toutes les possibilités possible et en comptant le nombre de solutions.*/

public class Backtracking {

	// BACKTRACKING

	public static int compteur = 0; // compteur global
	public static boolean display;

	public static boolean backtracking(Grille g) {
		for (int NbPossibiliteDesCases = 1; NbPossibiliteDesCases <= g.TAILLE_COTE_PLATEAU; NbPossibiliteDesCases++) {
			for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
				for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {
					
					// on regarde poour toutes les cases celles qui ont un petit nombre de possibilite pour commencer : 2 ,3...9 , cela accelere le bt
					
					if (g.t[ligne][col].getValue() == g.VALEUR_VIDE
							&& g.t[ligne][col].getPossibility().size() == NbPossibiliteDesCases) { 
						
						// on cherche une case minimum de NbPossibiliteDesCases possible ( 2 pour commencer ... )
						// on test toutes les possibilites pour cette case , si le chiffre respecte les
						// regles, on passe a la case suivante via un appel recursif
						
						for (int i = 0; i <= g.t[ligne][col].getPossibility().size() - 1; i++) {
							g.t[ligne][col].setValue(g.t[ligne][col].getPossibility().get(i));
							// DEBUG : t[ligne][col].getPossibility().get(i));
							if (g.estOk( ligne, col, g.t[ligne][col].getPossibility().get(i)) && backtracking(g)) {
								return true;
							}
							g.t[ligne][col].setValue(g.VALEUR_VIDE);
							// DEBUG : t[ligne][col].getPossibility().get(i));
						}
						return false; // Return false, le backtracking va else et supprimer la valeur
					}
				}
			}
		}
		compteur += 1;
		if (display == true) {
			System.out.println("Solution " + compteur);
			g.afficher();
		}
		return false; // completed
	}
	
	
	// FIN BACKTRACKING
}
