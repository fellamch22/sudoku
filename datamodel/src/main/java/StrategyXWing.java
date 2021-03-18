
/* StrategyXWing.java
 * Createur : Fella MECHOUAR
 * Date de creation : 04/2020 */

import java.util.ArrayList;

public class StrategyXWing {
	
	
	public static boolean StrategieXWingCOL(Grille g) {
		

		ArrayList<Integer> data = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> stock = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> colsAgarder = new ArrayList<Integer>();
		ArrayList<Integer> digit = new ArrayList<Integer>();
		String stra ;

        boolean found = false ;
        int col = 0 ;
        int i = 0;
        int cpt = 0 ;
        
		for ( int number = 1 ; number <= g.TAILLE_COTE_PLATEAU ; number ++ ) {
		
			while ( col < g.TAILLE_COTE_PLATEAU && !found ) {
				
				data = g.WhereIsXinRow(col, number);
			
				if ( data.size() == 3 ) {
					i = 0 ;
					while ( i <  stock.size() && !found ) {
						
						if (data.get(0) == stock.get(i).get(0) &&  data.get(1) == stock.get(i).get(1) 
								&&  (g.nbPosLigne(data.get(0), number) > 2 || g.nbPosLigne(data.get(1), number) > 2 ) ) {
							res.add(stock.get(i));
							res.add(data);
							found = true ;
							
						}
						
						i++ ;
						
					}
					
					if ( !found ) stock.add(data);

				}
				
				col ++;
			}
			
			
			if ( found ) {
				

				digit.add(number);
				colsAgarder.add(res.get(0).get(2) );
				colsAgarder.add(res.get(1).get(2) );
				stra = "Strategie X-Wing colonne : We found on : lignes  "+res.get(0).get(0)+" , "+res.get(0).get(1)+" cols : "+res.get(0).get(2)+" , "+res.get(1).get(2) + " ";

				g.removeNumberInLigne(digit, res.get(0).get(0), colsAgarder, stra);
				g.removeNumberInLigne(digit, res.get(0).get(1), colsAgarder, stra);
				cpt ++ ;
				digit.remove(0);
				colsAgarder.clear();
			}
			
			
			col = 0 ;
			data.clear();
			stock.clear();
			res.clear();
			found = false;
			
			
			
		}
		


		if ( cpt > 0) return true;
		
		return false;
	}

	public static boolean StrategieXWingLIGNE(Grille g) {
		
		ArrayList<Integer> data = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> stock = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> lignesAgarder = new ArrayList<Integer>();
		ArrayList<Integer> digit = new ArrayList<Integer>();
		String stra ;

        boolean found = false ;
        int ligne = 0 ;
        int i = 0;
        int cpt = 0 ;
		for ( int number = 1 ; number <= g.TAILLE_COTE_PLATEAU ; number ++ ) {
		
			while ( ligne < g.TAILLE_COTE_PLATEAU && !found ) {
				
				data =  g.WhereIsXinCol(ligne, number);
			 if ( data.size() == 3 ) {
				i = 0 ;
		       while( i < stock.size() && !found ) {
		    	   
		    	    if (data.get(0) == stock.get(i).get(0) && data.get(1) == stock.get(i).get(1) &&
                    (g.nbPosCol(data.get(0), number) > 2 || g.nbPosCol(data.get(1), number) > 2 )  ) {
		    	    	res.add(stock.get(i));
		    	    	res.add(data);
		    	    	found = true ;
		    	    }
		    	    
		    	    i ++ ;
		    	    	 
		       }
		       
		       if ( !found ) stock.add(data);
		       
			 }
			 
		       ligne ++ ;
			}
			
			if (found) { // la  suppression
				lignesAgarder.add(res.get(0).get(2));
				lignesAgarder.add(res.get(1).get(2));
				digit.add(number);
				stra = "Strategie X-Wing Ligne : We found on : lignes  "+res.get(0).get(0)+" , "+res.get(0).get(1)+" cols : "+res.get(0).get(2)+" , "+res.get(1).get(2) + " ";

				g.removeNumberInCol(digit, res.get(0).get(0), lignesAgarder, stra) ;
				g.removeNumberInCol(digit, res.get(0).get(1), lignesAgarder, stra) ;
				cpt ++ ;
				lignesAgarder.clear();
				digit.remove(0);
				
			}
			
			ligne = 0 ;
			data.clear();
			stock.clear();
			res.clear();
			found = false;
			
			
			
		}
	

		if ( cpt > 0)	return true;

		
		return false;
		
	}
	
	
}
