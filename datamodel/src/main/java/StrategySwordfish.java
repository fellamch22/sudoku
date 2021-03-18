
/* StrategySwordfish.java
 * Createur : Fella MECHOUAR
 * Date de creation : 04/2020
 * Description : Cette strategie vient comme une generalisation pour la strategie X-Wing, 
 * elle consiste a voir si : Dans trois lignes différentes pour un nombre donne il existe 
 * deux ou trois possibilites au plus qui se trouvent sur les memes colonnes.
 * OU Dans trois colonnes differentes pour un nombre donne il existe deux ou trois possibilites 
 * au plus qui se trouvent sur les memes lignes. Alors, on peut supprimer les possibilites 
 * d avoir ce nombre dans les colonnes (Resp les lignes) selectionnées sauf dans les lignes 
 * (Resp les colonnes) qui ont ete selectionnées pour etablir le schema d un Swordfish*/

import java.util.ArrayList;

public class StrategySwordfish {

	
static boolean  StrategieSwordfishCOL(Grille g ) {
		

		
		 ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();
		 ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		 ArrayList<Integer> stock = new ArrayList<Integer>();
		 ArrayList<Integer> elmts = new ArrayList<Integer>();
		 ArrayList<Integer> digit = new ArrayList<Integer>();

		int col = 0 ;
		int i = 0 ;
		int k = 0 ;
		int sauv = 0 ;
		boolean found = false ;
		int cpt = 0 ;
		int check = 0 ;
		
		 for (int number = 1 ; number <= g.TAILLE_COTE_PLATEAU ; number ++ ) {
			 
			 // collecte des donnees 
			 while( col < g.TAILLE_COTE_PLATEAU ) {
				 
				 stock  = g.WhereIsXinRow(col, number) ;
				 if ( stock.size() == 3 ||  stock.size() == 4 ) {
					 
					 data.add(stock);
				 }
				 
				 col ++ ;
			 }
			 
			 while ( data.size() >= 3 && !found ) {
				 
				 	while ( i < data.size() &&  result.size() < 3 ) {
				 		
				 		if ( result.size() == 0) {
				 			
				 			// premier element ajoute directement , principe premier arrive premier servi
				 			result.add(data.get(i));
				 			
				 		}else if ( result.size() == 1) {
				 			// 2 eme element trouve 
				 			
				 			sauv = result.get(0).remove(result.get(0).size()-1 );
				 			
				 			if (result.get(0).size() == 4 ) {
				 				
				 				for ( int j = 0 ; j <  data.get(i).size() - 1; j ++ ) {
				 					
				 					if (result.get(0).contains(data.get(i).get(j)) ) check ++ ;
				 				}
				 				
				 			 result.get(0).add(sauv);
				 			 
				 			 if ( check ==  data.get(i).size() - 1 ) result.add(data.get(i)) ;
				 			 
				 			 check = 0 ;
				 			 
				 			}else {//result (0) size == 3
				 				
					 			
					 		if(data.get(i).size() == 3 )	{
					 			
					 			if ( ( result.get(0).contains(data.get(i).get(0)) && 
					 					  !result.get(0).contains(data.get(i).get(1)) )
					 					|| ( !result.get(0).contains(data.get(i).get(0)) && 
							 					  result.get(0).contains(data.get(i).get(1)) )
					 					) {

					 					result.add(data.get(i));
					 			}
					 			
					 		}else { // data.get(i).size == 4
					 			
					 			
					 			for ( int j = 0 ; j < data.get(i).size() -1 ; j ++ ) {
					 				
					 				if( result.get(0).contains(data.get(i).get(j)) ) check ++ ;
					 			}
					 			
					 			if (check == data.get(i).size() -1 )  result.add(data.get(i)) ;
					 			check = 0 ;
					 			
					 		}
					 	
					 		
					 		result.get(0).add(sauv);
				 		}	
				 			
				 	}else { // size == 2
				 		
				 			// 3 eme element trouve 
				 		
				 			// si les deux premiers ont size 4 ,
				 		    if ( result.get(0).size() == 4 && result.get(1).size() == 4 ) {
				 		    	
				 		    	sauv = result.get(1).remove(result.get(1).size() -1 );
				 		    	
				 		    // si size 3 : il doit avoir au moins deux en commun ,
				 		    	if (data.get(i).size() == 3) {
				 		    		
				 		    	     for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
				 		    	    	 if (result.get(1).contains(data.get(i).get(j)) ) check ++;
				 		    	     }
				 		    	     
				 		    	     if ( check == 2 ) result.add(data.get(i));
				 		    	     check = 0 ;
				 		    	}else {
				 		    // size 4 : tout en commun

				 		    	     for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
				 		    	    	 if (result.get(1).contains(data.get(i).get(j)) ) check ++;
				 		    	     }
				 		    	     
				 		    	     if ( check == 3 ) result.add(data.get(i));
				 		    	     check = 0 ;
				 		    	}
				 		    	
			 		    	     result.get(1).add(sauv);

				 		    	
				 		    }else if (result.get(0).size() == 3 && result.get(1).size() == 3 ) {
				 			// si les deux size 3 ,
				 		    	sauv = result.get(1).remove(result.get(1).size() -1 );

				 		    	if (data.get(i).size() == 3) {
						 		    // si size 3 : il doit contenir les deux elements qui ne sont 
				 		    		// pas dans l'intersection des deux ,

				 		    		// remplir elements par les elements qui ne sont pas dans l'intersection
				 		    		if ( !result.get(1).contains(result.get(0).get(0))){
				 		    			
				 		    			   elmts.add((result.get(0).get(0)));
				 		    			   
				 		    				if (result.get(1).get(0) != result.get(0).get(1)) {
				 		    					
				 		    					elmts.add((result.get(1).get(0)));
				 		    				}else {
				 		    					
				 		    					elmts.add((result.get(1).get(1)));
				 		    				}
				 		    	   }else {// result.get(1).contains(result.get(0).get(0))
				 		    			   elmts.add((result.get(0).get(1)));
				 		    			   
				 		    			   if (result.get(1).get(0) != result.get(0).get(0)) {
				 		    				   
				 		    				  elmts.add((result.get(1).get(0)));
				 		    			   }else {
				 		    				   
					 		    		       elmts.add((result.get(1).get(1)));

				 		    			   }
				 		    		}
				 		    		
				 		    		// verification
				 		    		for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
				 		    	    	 if (elmts.contains(data.get(i).get(j)) ) check ++;
				 		    	     }
				 		    		
				 		    		 if ( check == 2) result.add(data.get(i));
				 		    	     check = 0 ;
				 		    	     elmts.clear();
				 		    	}else {
						 		    // si size 4 : il doit contenir le tout en commun avec les deux , 4 elmts
					 		    	//  commun avec tout les deux 
				 		    		 for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
				 		    	    	 if (result.get(1).contains(data.get(i).get(j)) ) check ++;
				 		    	    	 if (data.get(i).get(j) == result.get(1).get(0) 
				 		    	    			 ||data.get(i).get(j) == result.get(1).get(1) )
				 		    	    		 check ++;
				 		    	     }
				 		    	     
				 		    	     if ( check == 4) result.add(data.get(i));
				 		    	     check = 0 ;

				 		    	}
				 		    	
			 		    	     result.get(1).add(sauv);

				 																	
				 		    }  else {
				 		     // si l'un size 3 et l'autre size 4 , 
				 		    
				 		    	if (data.get(i).size() == 3) {
				 		    		//si size 3 : il doit contenir  l'element qui n'est pas dans l'intersection et 
							 		   // l'un des deux autres en commun entre les deux elem
				 		    		sauv = data.get(i).remove(data.get(i).size()-1);
				 		    		if( result.get(0).size() == 4 ) {
				 		    			k = -1 ;
				 		    			//on cherche l'element qui n'est pas dans l'intersection dans celui de size 4
				 		    			for(int j =0 ; j < result.get(0).size()-1 && k ==-1 ; j++ ) {
				 		    				if (result.get(0).get(j) != result.get(1).get(0) 
				 		    						&& result.get(0).get(j) != result.get(1).get(1) ) 
				 		    					k = j ;
				 		    				
				 		    			}
				 		    			 
				 		    			if  ( ( (data.get(i).contains(result.get(1).get(0))
				 		    					&& !data.get(i).contains(result.get(1).get(1))) || 
				 		    					(!data.get(i).contains(result.get(1).get(0))
						 		    					&& data.get(i).contains(result.get(1).get(1))) ) 
				 		    					&& data.get(i).contains(result.get(0).get(k)) ) {
				 		    				data.get(i).add(sauv);
				 		    				result.add(data.get(i));
				 		    				
				 		    			}else {
				 		    				data.get(i).add(sauv);
				 		    			}
				 		    				  
				 		    			k = 0 ; 

				 		    		}else {// result.get(0).size() == 3
				 		    			
				 		    			k = -1 ;
				 		    			//on cherche l'element qui n'est pas dans l'intersection dans celui de size 4
				 		    			for(int j =0 ; j < result.get(1).size()-1 && k ==-1 ; j++ ) {
				 		    				if (result.get(1).get(j) != result.get(0).get(0) 
				 		    						&& result.get(1).get(j) != result.get(0).get(1) ) 
				 		    					k = j ;
				 		    				
				 		    			}
				 		    			 
				 		    			if  ( ( (data.get(i).contains(result.get(0).get(0))
				 		    					&& !data.get(i).contains(result.get(0).get(1))) || 
				 		    					(!data.get(i).contains(result.get(0).get(0))
						 		    					&& data.get(i).contains(result.get(0).get(1))) ) 
				 		    					&& data.get(i).contains(result.get(1).get(k)) ) {
				 		    				data.get(i).add(sauv);
				 		    				result.add(data.get(i));
				 		    				
				 		    			}else {
				 		    				data.get(i).add(sauv);
				 		    			}
				 		    				  
				 		    			k = 0 ; 
				 		    		}
				 		    		
				 		    	}else {
							 		   //si size 4 : il doit avoir tout en commun  avec celui de size 4
				 		    		if( result.get(0).size() == 4 ) {
				 		    			 k = 0;
				 		    			}else {// result.get(1).size() == 4 
				 		    			
				 		    			k =1 ;
				 		    		   }
				 		    		
				 		    		   sauv = result.get(k).remove(result.get(k).size() -1 );
				 		    		   

					 		    		 for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
					 		    	    	 if (result.get(k).contains(data.get(i).get(j)) ) check ++;
					 		    	     }
					 		    	     
					 		    	     if ( check == 3 ) result.add(data.get(i));
					 		    	     check = 0 ;
					 		    	     
					 		    	    result.get(k).add(sauv) ;
					 		    	    
			
				 		    	}
				 		    	
				 			
				 		    }
				 		    
				 		}
				 		
				 		i ++ ;
				 	}
				 	
				 	// if result.size ==3 , et on doit verifier qu'il existe au moins un elmt a supprimer
				 	// pour eviter de retomber sur le meme cas une autre fois et rentrer dans une boucle infinie
				 	// --> found = true
				 	stock.clear();
				 	if (result.size() == 3 ) stock.addAll(elmtsAsupprimerLigne(g, number , result ));
				 	if (stock.size() > 0 ) {
				 		found = true ;
				 	}else {
					 	// else - Remove la premiere ligne de data - on remet i = 0 --> la boucle sera relancee
				 		data.remove(0);
				 		i = 0 ;
				 	}
			 }
			 
			 
			 //stock.clear();
			 data.clear();
			 elmts.clear();
			 k = 0 ;
			 i = 0 ;
			 // if found on lance la suppression sur les colonnes sauf les lignes qu'on va garder
		      if (found) {
		    	  // on recolte les lignes a garder et les colonnes a supprimer
		    	  // elmts contiendra les lignes a garder , et dans stock ona deja mis les colonnes a supprimer 
		    	  elmts.add(result.get(0).remove(result.get(0).size()-1));
		    	  elmts.add(result.get(1).remove(result.get(1).size()-1));
		    	  elmts.add(result.get(2).remove(result.get(2).size()-1));
		    	

		    	  // suppression
		    	  digit.add(number);
		    	   g.removeNumberInCol(digit , stock.get(0), elmts, "Strategy Swordfish Ligne ");
		    	   g.removeNumberInCol(digit , stock.get(1), elmts, "Strategy Swordfish Ligne ");
		    	   g.removeNumberInCol(digit , stock.get(2), elmts, "Strategy Swordfish Ligne ");

		    	   cpt ++ ;
		    	   elmts.clear();
		    	   stock.clear();
		    	   digit.clear();
		    	   k = 0 ;
		      }
		      
		      result.clear();
		      found = false ;
		 }
		 
		if (cpt > 0 ) return true ;
		return false;
		
		
	}
	
static ArrayList<Integer> elmtsAsupprimerLigne(Grille g , int number , ArrayList<ArrayList<Integer>> result ) {
	ArrayList<Integer> cols = new ArrayList<Integer>();
	ArrayList<Integer> occs = new ArrayList<Integer>();
    int i = 0 ;
    int k = 0 ;
    int cpt = 0;
	// collecter les lignes
	while(cols.size() < 3 ) {
		while (i < result.size() ) {
			
			for(int j = 0 ; j < result.get(i).size() - 1 ; j ++) {
				
				if (!cols.contains(result.get(i).get(j))) cols.add(result.get(i).get(j));
			}
			
			i ++ ;
		}
	}
	
	i = 0;
	// compter le nombre d'appartion dans result
	while ( i < 3 ) {
		
		while (k < 3 ) {
			
			for (int j= 0 ; j < result.get(k).size() -1 ; j++ ) {
				
				if (result.get(k).get(j) == cols.get(i)) cpt ++;
			}
			k ++ ;
		}
		
		occs.add(cpt);
		cpt = 0;
		i ++;
	}
	
	i = 0 ;
	// compter la difference pour chaque colonne 
	while ( i  < cols.size()) {
		if ( g.nbPosLigne(cols.get(i), number) > occs.get(i)) return cols ;
		i ++ ;
	}
	
	return new ArrayList<Integer>();
}

	static boolean  StrategieSwordfishLIGNE(Grille g ) {
		
		 ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();
		 ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		 ArrayList<Integer> stock = new ArrayList<Integer>();
		 ArrayList<Integer> elmts = new ArrayList<Integer>();
		 ArrayList<Integer> digit = new ArrayList<Integer>();

		int ligne = 0 ;
		int i = 0 ;
		int k = 0 ;
		int sauv = 0 ;
		boolean found = false ;
		int cpt = 0 ;
		int check = 0 ;
		
		 for (int number = 1 ; number <= g.TAILLE_COTE_PLATEAU ; number ++ ) {
			 
			 // collecte des donnees 
			 while( ligne < g.TAILLE_COTE_PLATEAU ) {
				 
				 stock  = g.WhereIsXinCol(ligne, number) ;
				 if ( stock.size() == 3 ||  stock.size() == 4 ) {
					 
					 data.add(stock);
				 }
				 
				 ligne ++ ;
			 }
			 
			 while ( data.size() >= 3 && !found ) {
				 
				 	while ( i < data.size() &&  result.size() < 3 ) {
				 		
				 		if ( result.size() == 0) {
				 			
				 			// premier element ajoute directement , principe premier arrive premier servi
				 			result.add(data.get(i));
				 			
				 		}else if ( result.size() == 1) {
				 			// 2 eme element trouve 
				 			
				 			sauv = result.get(0).remove(result.get(0).size()-1 );
				 			
				 			if (result.get(0).size() == 4 ) {
				 				
				 				for ( int j = 0 ; j <  data.get(i).size() - 1; j ++ ) {
				 					
				 					if (result.get(0).contains(data.get(i).get(j)) ) check ++ ;
				 				}
				 				
				 			 result.get(0).add(sauv);
				 			 
				 			 if ( check ==  data.get(i).size() - 1 ) result.add(data.get(i)) ;
				 			 
				 			 check = 0 ;
				 			 
				 			}else {//result (0) size == 3
				 				
					 			//sauv = result.get(0).remove(result.get(0).size() -1 );
					 			
					 		if(data.get(i).size() == 3 )	{
					 			
					 			if ( ( result.get(0).contains(data.get(i).get(0)) && 
					 					  !result.get(0).contains(data.get(i).get(1)) )
					 					|| ( !result.get(0).contains(data.get(i).get(0)) && 
							 					  result.get(0).contains(data.get(i).get(1)) )
					 					) {

					 					result.add(data.get(i));
					 			}
					 			
					 		}else { // data.get(i).size == 4
					 			
					 			
					 			for ( int j = 0 ; j < data.get(i).size() -1 ; j ++ ) {
					 				
					 				if( result.get(0).contains(data.get(i).get(j)) ) check ++ ;
					 			}
					 			
					 			if (check == data.get(i).size() -1 )  result.add(data.get(i)) ;
					 			check = 0 ;
					 			
					 		}
					 	
					 		
					 		result.get(0).add(sauv);
				 		}	
				 			
				 	}else { // size == 2
				 		
				 			// 3 eme element trouve 
				 		
				 			// si les deux premiers ont size 4 ,
				 		    if ( result.get(0).size() == 4 && result.get(1).size() == 4 ) {
				 		    	
				 		    	sauv = result.get(1).remove(result.get(1).size() -1 );
				 		    	
				 		    // si size 3 : il doit avoir au moins deux en commun ,
				 		    	if (data.get(i).size() == 3) {
				 		    		
				 		    	     for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
				 		    	    	 if (result.get(1).contains(data.get(i).get(j)) ) check ++;
				 		    	     }
				 		    	     
				 		    	     if ( check == 2 ) result.add(data.get(i));
				 		    	     check = 0 ;
				 		    	}else {
				 		    // size 4 : tout en commun

				 		    	     for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
				 		    	    	 if (result.get(1).contains(data.get(i).get(j)) ) check ++;
				 		    	     }
				 		    	     
				 		    	     if ( check == 3 ) result.add(data.get(i));
				 		    	     check = 0 ;
				 		    	}
				 		    	
			 		    	     result.get(1).add(sauv);

				 		    	
				 		    }else if (result.get(0).size() == 3 && result.get(1).size() == 3 ) {
				 			// si les deux size 3 ,
				 		    	sauv = result.get(1).remove(result.get(1).size() -1 );

				 		    	if (data.get(i).size() == 3) {
						 		    // si size 3 : il doit contenir les deux elements qui ne sont 
				 		    		// pas dans l'intersection des deux ,

				 		    		// remplir elements par les elements qui ne sont pas dans l'intersection
				 		    		if ( !result.get(1).contains(result.get(0).get(0))){
				 		    			
				 		    			   elmts.add((result.get(0).get(0)));
				 		    			   
				 		    				if (result.get(1).get(0) != result.get(0).get(1)) {
				 		    					
				 		    					elmts.add((result.get(1).get(0)));
				 		    				}else {
				 		    					
				 		    					elmts.add((result.get(1).get(1)));
				 		    				}
				 		    	   }else {// result.get(1).contains(result.get(0).get(0))
				 		    			   elmts.add((result.get(0).get(1)));
				 		    			   
				 		    			   if (result.get(1).get(0) != result.get(0).get(0)) {
				 		    				   
				 		    				  elmts.add((result.get(1).get(0)));
				 		    			   }else {
				 		    				   
					 		    		       elmts.add((result.get(1).get(1)));

				 		    			   }
				 		    		}
				 		    		
				 		    		// verification
				 		    		for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
				 		    	    	 if (elmts.contains(data.get(i).get(j)) ) check ++;
				 		    	     }
				 		    		
				 		    		 if ( check == 2) result.add(data.get(i));
				 		    	     check = 0 ;
				 		    	     elmts.clear();
				 		    	}else {
						 		    // si size 4 : il doit contenir le tout en commun avec les deux , 4 elmts
					 		    	// FIXED : commun avec tout les deux 
				 		    		 for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
				 		    	    	 if (result.get(1).contains(data.get(i).get(j)) ) check ++;
				 		    	    	 if (data.get(i).get(j) == result.get(1).get(0) 
				 		    	    			 ||data.get(i).get(j) == result.get(1).get(1) )
				 		    	    		 check ++;
				 		    	     }
				 		    	     
				 		    	     if ( check == 4) result.add(data.get(i));
				 		    	     check = 0 ;

				 		    	}
				 		    	
			 		    	     result.get(1).add(sauv);

				 																	
				 		    }  else {
				 		     // si l'un size 3 et l'autre size 4 , 
				 		    
				 		    	if (data.get(i).size() == 3) {
				 		    		//si size 3 : il doit contenir  l'element qui n'est pas dans l'intersection et 
							 		   // l'un des deux autres en commun entre les deux elem
				 		    		sauv = data.get(i).remove(data.get(i).size()-1);
				 		    		if( result.get(0).size() == 4 ) {
				 		    			k = -1 ;
				 		    			//on cherche l'element qui n'est pas dans l'intersection dans celui de size 4
				 		    			for(int j =0 ; j < result.get(0).size()-1 && k ==-1 ; j++ ) {
				 		    				if (result.get(0).get(j) != result.get(1).get(0) 
				 		    						&& result.get(0).get(j) != result.get(1).get(1) ) 
				 		    					k = j ;
				 		    				
				 		    			}
				 		    			 
				 		    			if  ( ( (data.get(i).contains(result.get(1).get(0))
				 		    					&& !data.get(i).contains(result.get(1).get(1))) || 
				 		    					(!data.get(i).contains(result.get(1).get(0))
						 		    					&& data.get(i).contains(result.get(1).get(1))) ) 
				 		    					&& data.get(i).contains(result.get(0).get(k)) ) {
				 		    				data.get(i).add(sauv);
				 		    				result.add(data.get(i));
				 		    				
				 		    			}else {
				 		    				data.get(i).add(sauv);
				 		    			}
				 		    				  
				 		    			k = 0 ; 

				 		    		}else {// result.get(0).size() == 3
				 		    			
				 		    			k = -1 ;
				 		    			//on cherche l'element qui n'est pas dans l'intersection dans celui de size 4
				 		    			for(int j =0 ; j < result.get(1).size()-1 && k ==-1 ; j++ ) {
				 		    				if (result.get(1).get(j) != result.get(0).get(0) 
				 		    						&& result.get(1).get(j) != result.get(0).get(1) ) 
				 		    					k = j ;
				 		    				
				 		    			}
				 		    			 
				 		    			if  ( ( (data.get(i).contains(result.get(0).get(0))
				 		    					&& !data.get(i).contains(result.get(0).get(1))) || 
				 		    					(!data.get(i).contains(result.get(0).get(0))
						 		    					&& data.get(i).contains(result.get(0).get(1))) ) 
				 		    					&& data.get(i).contains(result.get(1).get(k)) ) {
				 		    				data.get(i).add(sauv);
				 		    				result.add(data.get(i));
				 		    				
				 		    			}else {
				 		    				data.get(i).add(sauv);
				 		    			}
				 		    				  
				 		    			k = 0 ; 
				 		    		}
				 		    		
				 		    	}else {
							 		   //si size 4 : il doit avoir tout en commun  avec celui de size 4
				 		    		if( result.get(0).size() == 4 ) {
				 		    			 k = 0;
				 		    			}else {// result.get(1).size() == 4 
				 		    			
				 		    			k =1 ;
				 		    		   }
				 		    		
				 		    		   sauv = result.get(k).remove(result.get(k).size() -1 );
				 		    		   

					 		    		 for (int j = 0 ; j < data.get(i).size()-1 ;j++ ) {
					 		    	    	 if (result.get(k).contains(data.get(i).get(j)) ) check ++;
					 		    	     }
					 		    	     
					 		    	     if ( check == 3 ) result.add(data.get(i));
					 		    	     check = 0 ;
					 		    	     
					 		    	    result.get(k).add(sauv) ;
					 		    	    
			
				 		    	}
				 		    	
				 			
				 		    }
				 		    
				 		}
				 		
				 		i ++ ;
				 	}
				 	
				 	// if result.size ==3 , et on doit verifier qu'il existe au moins un elmt a supprimer
				 	// pour eviter de retomber sur le meme cas une autre fois et rentrer dans une boucle infinie
				 	// --> found = true
				 	stock.clear();
				 	if (result.size() == 3 ) stock.addAll(elmtsAsupprimerCol(g, number , result ));
				 	if (stock.size() > 0 ) {
				 		found = true ;
				 	}else {
					 	// else - Remove la premiere ligne de data - on remet i = 0 --> la boucle sera relancee
				 		data.remove(0);
				 		i = 0 ;
				 	}
			 }
			 
			 
			 //stock.clear();
			 data.clear();
			 elmts.clear();
			 k = 0 ;
			 i = 0 ;
			 // if found on lance la suppression sur les colonnes sauf les lignes qu'on va garder
		      if (found) {
		    	  // on recolte les lignes a garder et les colonnes a supprimer
		    	  // elmts contiendra les lignes a garder , et dans stock ona deja mis les colonnes a supprimer 
		    	  elmts.add(result.get(0).remove(result.get(0).size()-1));
		    	  elmts.add(result.get(1).remove(result.get(1).size()-1));
		    	  elmts.add(result.get(2).remove(result.get(2).size()-1));
		    	
 
		    	  // suppression
		    	  digit.add(number);
		    	   g.removeNumberInCol(digit , stock.get(0), elmts, "Strategy Swordfish Ligne ");
		    	   g.removeNumberInCol(digit , stock.get(1), elmts, "Strategy Swordfish Ligne ");
		    	   g.removeNumberInCol(digit , stock.get(2), elmts, "Strategy Swordfish Ligne ");

		    	   cpt ++ ;
		    	   elmts.clear();
		    	   stock.clear();
		    	   digit.clear();
		    	   k = 0 ;
		      }
		      
		      result.clear();
		      found = false ;
		 }
		 
		if (cpt > 0 ) return true ;
		return false;
		
	}
	
	static ArrayList<Integer> elmtsAsupprimerCol(Grille g , int number , ArrayList<ArrayList<Integer>> result ) {
		ArrayList<Integer> cols = new ArrayList<Integer>();
		ArrayList<Integer> occs = new ArrayList<Integer>();
        int i = 0 ;
        int k = 0 ;
        int cpt = 0;
		// collecter les colonnes
		while(cols.size() < 3 ) {
			while (i < result.size() ) {
				
				for(int j = 0 ; j < result.get(i).size() - 1 ; j ++) {
					
					if (!cols.contains(result.get(i).get(j))) cols.add(result.get(i).get(j));
				}
				
				i ++ ;
			}
		}
		
		i = 0;
		// compter le nombre d'appartion dans result
		while ( i < 3 ) {
			
			while (k < 3 ) {
				
				for (int j= 0 ; j < result.get(k).size() -1 ; j++ ) {
					
					if (result.get(k).get(j) == cols.get(i)) cpt ++;
				}
				k ++ ;
			}
			
			occs.add(cpt);
			cpt = 0;
			i ++;
		}
		
		i = 0 ;
		// compter la difference pour chaque colonne 
		while ( i  < cols.size()) {
			if ( g.nbPosCol(cols.get(i), number) > occs.get(i)) return cols ;
			i ++ ;
		}
		
		return new ArrayList<Integer>();
	}
	

}
