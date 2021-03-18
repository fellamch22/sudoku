
/* StrategyChiffreExclusifRegion.java
 * Createur : Fella MECHOUAR
 * Date de creation : 03/2020
 * Description : si, a l interieur d une Region, deux ou trois chiffres identiques 
 * figurent dans une Ligne et qu ils ne figurent pas ailleurs sur la meme Ligne dans 
 * les autres Regions, on peut alors supprimer sans autre ce chiffre dans la Region 
 * ou l on se trouve. Cette strategie s applique egalement pour une Colonne.*/


public class StrategyChiffreExclusifRegion  {
	
	// Strategie Chiffres Exclusifs Region ligne

	public static boolean strategieChiffreExclusifRegionLigne(Grille g) {

		int c = 0, pos = 0, j = 0, k = 0; 
		boolean res = true;
		int cpt = 0;

		for (int l = 0; l < g.TAILLE_COTE_PLATEAU; l++) {

			c = 0;

			while (c < g.TAILLE_SOUSZONE_COL) {

				for (int chif = 1; chif <= g.TAILLE_COTE_PLATEAU; chif++) {
					res = true;
					pos = g.nbPosLigneBox(l, c, chif);

					if (pos >= 2 && g.nbPosNbBox(l, c, chif) > pos) {
						j = c;
						c += g.TAILLE_SOUSZONE_COL;

						while (c < g.TAILLE_COTE_PLATEAU && res) {
							res = res && (g.nbPosLigneBox(l, c, chif) == 0);
							c += g.TAILLE_SOUSZONE_COL;
						}

						c = j - g.TAILLE_SOUSZONE_COL;

						while (c >= 0 && res) {
							res = res && (g.nbPosLigneBox(l, c, chif) == 0);
							c -= g.TAILLE_SOUSZONE_COL;
						}

						c = j;
						if (res) {
							g.supprchifBoxSaufLigne(l, c, chif);
							System.out.println("ChiffreExclusifRegionLigne : We remove " + l + " * " + c + " : " + chif + " because "+ chif + " is found in only one region in the same row.\n");
							cpt ++ ;
						}
						

					}

				}

				c += g.TAILLE_SOUSZONE_COL;
			}

		}


		if (cpt > 0 ) return true ;
		return false ;
	}

	
	// fin Strategie Chiffres Exclusifs Region ligne
	
	// Strategie Chiffres Exclusifs Region colonne

	public static boolean strategieChiffreExclusifRegionCol(Grille g) {
		

		int l = 0, j = 0, pos = 0;
		int cpt = 0 ;
		boolean res = true;

		for (int c = 0; c < g.TAILLE_COTE_PLATEAU; c++) {

			l = 0;

			while (l < g.TAILLE_COTE_PLATEAU) {

				for (int n = 1; n <= g.TAILLE_COTE_PLATEAU; n++) {

					res = true;
					j = l;
					pos = g.nbPosColBox(l, c, n);

					if (pos >= 2 && g.nbPosNbBox(l, c, n) > pos) {

						l -= g.TAILLE_SOUSZONE_LIGNE;

						while (l >= 0 && res) {

							res = res && (g.nbPosColBox(l, c, n) == 0);
							l -= g.TAILLE_SOUSZONE_LIGNE;
						}

						l = j + g.TAILLE_SOUSZONE_LIGNE;

						while (l < g.TAILLE_COTE_PLATEAU) {

							res = res && (g.nbPosColBox(l, c, n) == 0);
							l += g.TAILLE_SOUSZONE_LIGNE;
						}

						l = j;
						if (res) {
							g.supprchifBoxSaufCol(l, c, n);
							System.out.println("ChiffreExclusifRegionCol : We remove " + l + " * " + c + " : " + n + " because "+ n + " is found only in one region in the same column.\n");
							cpt ++ ;


						}

					}

				}

				l += g.TAILLE_SOUSZONE_LIGNE;
			}

		}
		
		if (cpt > 0 ) return true ;
		return false ;
	}

	
}
