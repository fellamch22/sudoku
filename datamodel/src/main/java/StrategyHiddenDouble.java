
/* StrategyHiddenDouble.java
 * Createur: Li Fang SU
 * Date de creation : 04/2020
 * Description : Hidden Pair/Triplet/Quad
 * In a Sudoku , if two digits are candidates for the same two cells in the same row 
 * and not for any other cells of that row, then such two digits must be in either one 
 * of these two cells and other candidates in those two cells can be eliminated. 
 * This technique has been adapted to work with N digits*/

import java.util.ArrayList;

public class StrategyHiddenDouble  {
//sur une ligne si on a 2 chiffres sur 2 cases identiques uniquement
	// on efface les autres probas
	public static boolean StrategyHiddenDoubleLigne(Grille g,int nb) {
		//System.out.println("\nStrategie Hidden Double Ligne "  );
		ArrayList<ArrayList <Integer>> ColAGarder = new ArrayList<ArrayList <Integer>>();
		ArrayList<Integer> currentCol = new ArrayList<Integer>();
		ArrayList<Integer> doubletTest = new ArrayList<Integer>();
		ArrayList<Integer>  temp= new ArrayList<Integer>();
		int cpt;
		for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
			for (int number = 1; number <= g.TAILLE_COTE_PLATEAU; number++) {
				for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {
					if(g.t[ligne][col].getPossibility().contains(number)) {
						//System.out.println("le chiffre "+number + " est en " + ligne +"*"+ col);
						currentCol.add(col);
					}
				}
				ColAGarder.add(currentCol);
				currentCol=new ArrayList<Integer>();
			}
			//ColAGarder contain for each number the column possibility
			cpt=0;
			for(int i=0;i<ColAGarder.size();i++) {
				if ( !doubletTest.isEmpty()  && doubletTest.equals(ColAGarder.get(i)) ) {cpt++;temp.add(i+1);}
			    if ( !doubletTest.isEmpty()  && doubletTest.equals(ColAGarder.get(i)) && cpt==nb ) {
					//System.out.println(doubletTest + " xxx " +temp);
					//si le doublet est deja defini et que la nouvelle valeur de colAGarder est egale, on efface les autres chiffres
					for(int number=1;number<=g.TAILLE_COTE_PLATEAU;number++) {
						for(int elt=0;elt<doubletTest.size();elt++) {
						if(number != i+1 && !temp.contains(number) && g.t[ligne][doubletTest.get(elt)].getPossibility().contains(number)) { 
							System.out.print("HiddenDouble : Numbers " + temp + " present only in the row cells " + 
						ligne + " * " + doubletTest.get(elt) + g.t[ligne][doubletTest.get(elt)].getPossibility() );
							for(int e=1;e<doubletTest.size();e++)
							{
								System.out.print(" and "+ ligne + " * " + doubletTest.get(e) + g.t[ligne][doubletTest.get(e)].getPossibility() );
							}
							System.out.println(" so we remove the number " + number + " which present in the cell " + ligne + " * " + doubletTest.get(elt)+ "\n");
							g.t[ligne][doubletTest.get(elt)].getPossibility().remove((Integer) number);			
							return true;
						}

						}
					}
				}
				if(ColAGarder.get(i).size() == nb  ) {
					temp.clear();
					temp.add(i+1); // = the number
					doubletTest = ColAGarder.get(i) ; // the column where the number is possible
					cpt++;
				}

			}
			temp.clear();
			doubletTest.clear();
			ColAGarder.clear();
		}
		return false;
	}

	public static boolean StrategyHiddenDoubleCol(Grille g,int nb) {
		//System.out.println("\nStrategie Hidden Double Col "  );
		ArrayList<ArrayList <Integer>> LigneAGarder = new ArrayList<ArrayList <Integer>>();
		ArrayList<Integer> CurrentLigne = new ArrayList<Integer>();
		ArrayList<Integer> doubletTest = new ArrayList<Integer>();
		ArrayList<Integer>  temp= new ArrayList<Integer>();
		int cpt;
		for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {
			for (int number = 1; number <= g.TAILLE_COTE_PLATEAU; number++) {
				for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {
					if(g.t[ligne][col].getPossibility().contains(number)) {
						CurrentLigne.add(ligne);
					}
				}
				LigneAGarder.add(CurrentLigne);
				CurrentLigne=new ArrayList<Integer>();
			}
			cpt=0;
			//System.out.println("col "+col+"LigneAGarder "+LigneAGarder);
			for(int i=0;i<LigneAGarder.size();i++) {
				if ( !doubletTest.isEmpty()  && doubletTest.equals(LigneAGarder.get(i)) ) {cpt++;temp.add(i+1);}
				if ( !doubletTest.isEmpty()  && doubletTest.equals(LigneAGarder.get(i)) && cpt==nb ) {
					//si le doublet est deja defini et que la nouvelle valeur de colAGarder est egale, on efface les autres chiffres
					for(int number=1;number<=g.TAILLE_COTE_PLATEAU;number++) {
						for(int elt=0;elt<doubletTest.size();elt++) {
						if(number != i+1 && !temp.contains(number) && g.t[doubletTest.get(elt)][col].getPossibility().contains(number)) { 
							System.out.print("HiddenDouble : Numbers " + temp + " present only in the column cells " + 
									doubletTest.get(0) + " * " + col  + g.t[doubletTest.get(0)][col].getPossibility() );
							for(int e=1;e<doubletTest.size();e++)
							{
								System.out.print(" and "+ doubletTest.get(e) + " * " + col + g.t[doubletTest.get(e)][col].getPossibility() );
							}
							System.out.println(" so we remove the number " + number + " which present in the cell " + doubletTest.get(elt) + " * " + col+ "\n");
							g.t[doubletTest.get(elt)][col].getPossibility().remove((Integer) number);
							return true;
						}
					}
					}
				}
				if(LigneAGarder.get(i).size() == nb  ) {
					temp.clear();
					temp.add(i+1);
					//System.out.println("Doublet test "+LigneAGarder.get(i));
					doubletTest = LigneAGarder.get(i) ;
					cpt=1;
				}

			}
			temp.clear();
			doubletTest.clear();
			LigneAGarder.clear();
		}
		
		return false;
	}	
	
	
}
