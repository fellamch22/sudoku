
/* StrategyLockedCandidates.java
 * Createur : Li Fang SU
 * Date de creation : 03/2020
 * Description : When examining a sudoku box, one might find that 
 * one digit is locked in a specific row (or column) of that box 
 * although the exact position cannot be determined yet.  
 * This information leads us to confirm that such digit cannot appear
 * in other positions of the same row (or column). */

import java.util.ArrayList;

public class StrategyLockedCandidates {

	public static boolean StrategyLockedCandidatesLignes(Grille g) {
		//DEBUG : g.afficherPossibility();
		int box = -1;
		boolean flag = false;
		ArrayList<Integer> ligneAGarder = new ArrayList<Integer>();
		ArrayList<Integer> colAGarder = new ArrayList<Integer>();
		for (int number = 1; number <= g.TAILLE_COTE_PLATEAU; number++) {
			for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
				for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {
					if (g.t[ligne][col].getPossibility().contains(number) && box != -1
							&& box != (col - col % g.TAILLE_SOUSZONE_LIGNE)) {
						box = -2; // inactive until new row
					}
					if (g.t[ligne][col].getPossibility().contains(number) && 
							( box == -1 || box == (col - col % g.TAILLE_SOUSZONE_LIGNE) )
							) {
						box = col - col % g.TAILLE_SOUSZONE_LIGNE;
						ligneAGarder.add(ligne);
						colAGarder.add(col);
					}
				}
				if (box > -1) {
					//DEBUG : System.out.println("Appel removeNumberInBox("+number+","+ligneAGarder+"," + colAGarder + ")");
					flag = g.removeNumberInBox(number,ligneAGarder,colAGarder,"Locked Candidates in row : ");
					if (flag == true) {return true;}
				}
				box = -1;
				ligneAGarder.clear();
				colAGarder.clear();
			}
		}
		return flag;
	}
	
	public static boolean StrategyLockedCandidatesCol(Grille g) {
		//DEBUG : g.afficherPossibility();
		int box = -1;
		boolean flag = false;
		ArrayList<Integer> ligneAGarder = new ArrayList<Integer>();
		ArrayList<Integer> colAGarder = new ArrayList<Integer>();
		for (int number = 1; number <= g.TAILLE_COTE_PLATEAU; number++) {
				for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {
					for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
					if (g.t[ligne][col].getPossibility().contains(number) && box != -1
							&& box != (ligne - ligne % g.TAILLE_SOUSZONE_LIGNE)) {
						box = -2; // inactive until new row
					}
					if (g.t[ligne][col].getPossibility().contains(number) && 
							( box == -1 || box == (ligne - ligne % g.TAILLE_SOUSZONE_LIGNE) )
							) {
						box = ligne - ligne % g.TAILLE_SOUSZONE_LIGNE;
						ligneAGarder.add(ligne);
						colAGarder.add(col);
					}
				}
				if (box > -1) {
					//DEBUG : System.out.println("Appel removeNumberInBox("+number+","+ligneAGarder+"," + colAGarder + ")");
					flag = g.removeNumberInBox(number,ligneAGarder,colAGarder, "Locked Candidates in columns : " );
					if (flag == true) {return true;}
				}
				box = -1;
				ligneAGarder.clear();
				colAGarder.clear();
			}
		}
		return flag;
	}
	
}
