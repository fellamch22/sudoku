
/* StrategyChiffresExclusifs.java
 * Createur : Li Fang SU
 * Date de creation : 02/2020
 * Description : Si, a l intérieur d une Region, deux ou trois chiffres identiques 
 * figurent dans une Ligne et qu ils ne figurent pas ailleurs dans la Region, 
 * on peut alors supprimer sans autre ce chiffre se trouvant dans les autres Regions 
 * sur la meme Ligne. Cette strategie s applique egalement pour une Colonne.*/


public class StrategyChiffresExclusifs {

	//Strategie chiffre exclusif Ligne
	public static boolean StrategieChiffresExclusifsLignes(Grille g) {
		boolean bool = false;
		// pour toutes les box
		for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne = ligne + g.TAILLE_SOUSZONE_COL) {
			for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col = col + g.TAILLE_SOUSZONE_LIGNE) {
				// pour tous les chiffres
				for (int number = 1; number <= g.TAILLE_COTE_PLATEAU; number++) {
					
					// si le chiffre est unique dans la ligne ( et il n'est pas place ) on l'enleve
					// sur les possibilitees de la ligne
					if (g.UniqueInLigne(ligne, col, number) != -1) {
						for (int c = 0; c < g.TAILLE_COTE_PLATEAU; c++) {
							if ((c < col || c >= (col + g.TAILLE_SOUSZONE_LIGNE))
									&& g.t[g.UniqueInLigne(ligne, col, number)][c].getPossibility().contains(number)) {
								String str = "ChiffresExclusifs : We remove " + g.UniqueInLigne(ligne, col, number)
										+ " * " + c + " : " + number + " because " + number + " must be in the box "
										+ ligne + " * " + col + " \n";
								System.out.println(str);
								g.t[g.UniqueInLigne(ligne, col, number)][c].getPossibility().remove((Integer) number);
								bool = true;
							}
						}
					}
				}
			}
		}
		return bool;
	}
//Fin Strategie Chiffre Exclusif Ligne

	//Strategie Chiffre Exclusif Colonne 
	public static boolean StrategieChiffresExclusifsCol(Grille g) {
		boolean bool = false;
		// pour toutes les box
		for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne = ligne + g.TAILLE_SOUSZONE_COL) {
			for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col = col + g.TAILLE_SOUSZONE_LIGNE) {
				// pour tous les chiffres
				for (int number = 1; number <= g.TAILLE_COTE_PLATEAU; number++) {
			
					// si le chiffre est unique dans la col ( et il n'est pas placÃ© ) on l'enleve
					// sur les possibilitees de la ligne
					if (g.UniqueInCol(ligne, col, number) != -1) {
						for (int l = 0; l < g.TAILLE_COTE_PLATEAU; l++) {
							
							if ((l < ligne || l >= (ligne + g.TAILLE_SOUSZONE_COL))
									&& g.t[l][g.UniqueInCol(ligne, col, number)].getPossibility().contains(number)) {
								String str = "ChiffresExclusifs : We remove " + l + " * "
										+ g.UniqueInCol(ligne, col, number) + " : " + number + " because " + number
										+ " must be in the box " + ligne + " * " + col + " \n";
								System.out.println(str);
								g.t[l][g.UniqueInCol(ligne, col, number)].getPossibility().remove((Integer) number);
								//Casting the number as an Integer to ensure not to remove the index value

								bool = true;
							}
						}
					}
				}
			}
		}
		return bool;
	}
//Fin Strategie Chiffre Exclusif Col
}