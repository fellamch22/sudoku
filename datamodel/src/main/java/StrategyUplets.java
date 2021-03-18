
/* StrategyUplets.java
 * Createur : Li Fang SU
 * Date de creation : 03/2020
 * Description : 
 * In a Sudoku , if two cells in the same row have exactly the same two digit as possibilities, 
 * then such two digits must be in either one of these two cells and cannot be candidates 
 * in any other cells of that row.  The same reasoning can be used for any columns and 
 * any 3x3 boxes. The technique is called naked pair.

This technique has been adapted to work with N digits */

import java.util.ArrayList;

public class StrategyUplets {
	// Strategy Triplet
	
	static ArrayList<Integer> uplettest = new ArrayList<Integer>();
	static ArrayList<Integer> complementaire= new ArrayList<Integer>();
	// Fonctions generation uplet de test
	
	public static boolean incrementeArrayList(Grille g) {
		// on incremente le dernier chiffre a la pos i
		int u = uplettest.size();
		uplettest.set(u - 1, uplettest.get(u - 1) + 1);
		// pour tous les chiffres en commencant par le dernier
		for (int i = u - 1; i >= 0; i--) {
			
			//si le chiffre est egal a la taille max du plateau + 1, on le reset et incremente le precedent
			if (uplettest.get(i) == g.TAILLE_COTE_PLATEAU + 1) {
				//DEBUG : System.out.println("on reset la pos :" + i);
				if (i-1  < 0) return false;
				uplettest.set(i - 1, uplettest.get(i - 1) + 1);
				for (int c = 		1; c < g.TAILLE_COTE_PLATEAU; c++) {
					if (!uplettest.contains(c)) {
						uplettest.set(i, (int) c);
						break;
					}
				}
			}
		}
		return true;
	}
	
	public static boolean checkElemArralList(Grille g, ArrayList<Integer>  t ) {
		//dit si tous les elem de l'arraylist sont differents
		
		for (int k=0; k<t.size()-1;k++) { //check decroissant
			if (t.get(k) >= t.get(k+1)) {
				return false;
			}
		}
		
		for(int i=0;i<t.size();i++) {
			for(int j=0;j<t.size();j++) {
				if( t.get(i).equals(t.get(j)) && i!=j  ){
				return false;
				}
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> tripletTest(Grille g, int u) {
		// Cette fonction renvoi un uplet de test de taille u , en fonction des chiffres max de la grille g
		
		if (complementaire.isEmpty()) {
			for (int chiffre = g.TAILLE_COTE_PLATEAU - u + 1; chiffre <= g.TAILLE_COTE_PLATEAU; chiffre++) {
				complementaire.add(chiffre);
			}
			//DEBUG : System.out.println("complementaire = " + complementaire);
		}
		
		// si l'arraylist est vide, on l'initialise avec les chiffres les plus bas.
		if (uplettest.isEmpty()) {
			for (int chiffre = 1; chiffre <= g.TAILLE_COTE_PLATEAU; chiffre++) {
				uplettest.add(chiffre);
				if (chiffre == u)
					break;
			}
		} else {
			if (uplettest.equals(complementaire)) { //le cas sorti, uplettes = -1
				//DEBUG : System.out.println("sorti!");
				uplettest.clear();
				complementaire.clear();
				uplettest.add(-1);				
				return uplettest;
			}
			//DEBUG : System.out.println("check " + (int) ( uplet.get(u - 1) + 1) );
			// si elle est pas vide, on incremente le dernier chiffre.
			incrementeArrayList(g);
			while (!checkElemArralList(g,uplettest)  ) {incrementeArrayList(g);} 
		}
		//DEBUG : System.out.println("uplist = " + uplettest);
		return uplettest;
	}
	
	
	// Fin Fonctions generation uplet de test

	public static boolean StrategieUplets(Grille g, int uplet) {
		ArrayList<Integer> lignesAGarder = new ArrayList<Integer>();
		ArrayList<Integer> colAGarder = new ArrayList<Integer>();
		int compteur = 0;
		boolean flag = false;	
		String stra = "";
		uplettest.clear();
		
		while(tripletTest(g, uplet).get(0) != -1 ) {
			//DEBUG : System.out.println(uplettest);
			for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
				for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {

					// on regarde alors toute les lignes de chaque col
					// pour chaque doublet ou triplet inclus dans le triplet de test => on
					// incremente le compteur et note la ligne dans arraylist LigneaGarder
					for (int lintest = 0; lintest < g.TAILLE_COTE_PLATEAU; lintest++) {
						if (uplettest.containsAll(g.t[lintest][col].getPossibility())
								&& !g.t[lintest][col].getPossibility().isEmpty()) {
							compteur += 1;
							lignesAGarder.add(lintest);
						}
					}
			

					// si apres cela le compteur == 3 => on a 3 cases qui contiennent le triplet de
					// test sur la col
					// on supprime les 3 valeurs des possibilitees des autres cases de la colone
					// sauf des 3 cases precedentes
					if (compteur == uplet) {
						stra += "Uplets : Numbers " + uplettest + " present in these cells : ";
						for (int s = 0; s < lignesAGarder.size(); s++) {
							stra += lignesAGarder.get(s) + " * " + col + " "
									+ g.t[lignesAGarder.get(s)][col].getPossibility() + "   ";
						}
						stra += "\nWe can remove " + uplettest + " in the column " + col
								+ ", except for these rows : " + lignesAGarder + " so ";

						flag = g.removeNumberInCol(uplettest, col, lignesAGarder, stra);

					}
					
					
					lignesAGarder.clear();// on reinitialise arraylist de sauvegarde et compteur
					compteur = 0;
					stra = "";
					
					// puis on fait la meme chose pour toutes les colonnes de chaque ligne
					// pour chaque doublet ou triplet inclus dans le triplet de test => on
					// incremente le compteur et note la col dans arraylist colaGarder
					for (int coltest = 0; coltest < g.TAILLE_COTE_PLATEAU; coltest++) {
						if (uplettest.containsAll(g.t[ligne][coltest].getPossibility())
								&& !g.t[ligne][coltest].getPossibility().isEmpty()) {
							compteur += 1;
							colAGarder.add(coltest);
						}
					}
					// si apres cela le compteur == 3 => on a 3 cases qui contiennent le triplet de
					// test sur la ligne
					// on supprime les 3 valeurs des possibilitees des autres cases de la ligne sauf
					// des 3 cases precedentes

					if (compteur == uplet) {
						stra += "Uplets : Numbers " + uplettest + " present in these cells : ";
						for (int s = 0; s < colAGarder.size(); s++) {
							stra += ligne + " * " + colAGarder.get(s) + g.t[ligne][colAGarder.get(s)].getPossibility()
									+ "   ";
						}
						stra += "\nWe can remove " + uplettest + " in the row " + ligne
								+ ", except for these columns : " + colAGarder + " so ";

						flag = g.removeNumberInLigne(uplettest, ligne, colAGarder, stra);

					}

					// on reinitialise arraylist de sauvegarde et compteur
					colAGarder.clear();
					compteur = 0;
					
				}
			}
		}

		return flag;
	}

//Fin Strategy 
}
