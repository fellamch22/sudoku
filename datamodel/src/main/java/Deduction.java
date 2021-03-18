 
/* Deduction.java
 * Createur : Li Fang SU
 * Date de creation : 01/2020
 * Description : Les déductions consistent à écrire un chiffre sur la grille de Sudoku, 
 * dont on est sûr à 100% de sa présence. Cela correspond à 4 cas précis correspondant aux règles du sudoku :
	⦁ Un chiffre donné n'a qu'une seule position possible sur une ligne
	⦁ Un chiffre donné n'a qu'une seule position possible sur une colonne
	⦁ Un chiffre donné n'a qu'une seule position possible sur une box
	⦁ Une case n'a qu'une unique possibilité*/

public class Deduction {
	
	// Pour chaque box, si un unique chiffre non place est possible a un endroit, on
	// le met sur la grille sudoku
	public static boolean deduction(Grille g) {
		boolean result = false;
		int number;
		String str;
		for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {// pour toutes les CASES
			for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {// pour tous les chiffres
				if (g.t[ligne][col].getValue() == g.VALEUR_VIDE) { // verifier unique le case vide
					if (g.t[ligne][col].getPossibility().size() == 1) { // une seule possiblite	
						str="(" + (g.nbChiffres() + 1 ) + "/" + g.nbCases
								+ ")  Deduction : UNIQUE POSSIBLITY => " + ligne + " * " + col + " : "
								+ g.t[ligne][col].getPossibility().get(0) + " write on the Sudoku ";
						g.writeNumber(str,ligne, col, g.t[ligne][col].getPossibility().get(0));
						
						result = true;
					} else {
						for (int n = 0; n < g.t[ligne][col].getPossibility().size(); n++) {
							number=g.t[ligne][col].getPossibility().get(n); //tester que les possibilites de la case
							if (g.t[ligne][col].getPossibility().contains(number) && g.nbPosNbBox(ligne, col, number) == 1) {// pour la box
								
								str="(" + (g.nbChiffres() + 1 ) + "/" + g.nbCases
										+ ")  Deduction : UNIQUE NUMBER IN BOX => " + ligne + " * " + col + " : " + number
										+ " write on the Sudoku ";
								g.writeNumber(str,ligne, col, number);
								
								result = true;
							}
							if (g.t[ligne][col].getPossibility().contains(number) && g.nbPosLigne(ligne,  number) == 1) {// pour la ligne
								
								str="(" + (g.nbChiffres() + 1 ) + "/" + g.nbCases
										+ ")  Deduction : UNIQUE NUMBER IN LIGNE => " + ligne + " * " + col + " : " + number
										+ " write on the Sudoku ";
								g.writeNumber(str,ligne, col, number);
								result = true;
							}
							if (g.t[ligne][col].getPossibility().contains(number) && g.nbPosCol( col, number) == 1 ) {// pour la col																						

								str="(" + (g.nbChiffres() + 1 ) + "/" + g.nbCases
										+ ")  Deduction : UNIQUE NUMBER IN COL => " + ligne + " * " + col + " : " + number
										+ " write on the Sudoku ";
								g.writeNumber(str,ligne, col, number);
								result = true;
							}
						}
					} // Strategie chiffre unique dans une case
				}
			}
		}
		return result;
	}
}