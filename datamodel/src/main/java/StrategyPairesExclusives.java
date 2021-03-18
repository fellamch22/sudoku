
/* StrategyPairesExclusives.java
 * Createur : Li Fang SU
 * Date de creation : 03/2020
 * Description : Si dans une box se trouve deux Cases dans lesquelles 
 * les deux memes uniques possibilites figurent, alors on peut supprimer 
 * ces deux chiffres des autres cases de la box.*/


import java.util.ArrayList;

public class StrategyPairesExclusives  {// Strategie Paire Exclusive


	public static boolean StrategiePairesExclusives(Grille g , int size) {
		int posL;
		int posC;
		int compteur = 0;
		ArrayList<Integer> ligneAGarder = new ArrayList<Integer>();
		ArrayList<Integer> colAGarder = new ArrayList<Integer>();
		boolean flag = false;
		//DEBUG : g.afficherPossibility();
		//Pour toutes les cases ayant 2 possibilitees
		for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
			for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {
				if (g.t[ligne][col].getPossibility().size() == size) {

					// on comparre avec les cases de la box, sauf la case actuelle
					posL = ligne - ligne % g.TAILLE_SOUSZONE_COL;
					posC = col - col % g.TAILLE_SOUSZONE_LIGNE;

					for (int i = posL; i < posL + g.TAILLE_SOUSZONE_COL; i++) {
						for (int j = posC; j < posC + g.TAILLE_SOUSZONE_LIGNE; j++) {

							if (g.t[ligne][col].getPossibility().equals(g.t[i][j].getPossibility())
									&& g.t[ligne][col].getPossibility().size() == size) {
								compteur += 1;
								ligneAGarder.add(i);
								colAGarder.add(j);

							}
						}
					}
					if (compteur == size) {
						for (int i = 0; i < size; i++) {
							flag = g.removeNumberInBox(g.t[ligne][col].getPossibility().get(i), ligneAGarder, colAGarder, "Paires exclusives " );
							if (flag == true) {
								//DEBUG : g.afficherPossibility();
								return true;
							}
						}
					}
					compteur = 0;
					ligneAGarder.clear();
					colAGarder.clear();
				}
			}
		}
		return flag;
	}
	// Fin Strategie Paires Exclusives
}
