import java.util.*;
import java.io.*;
public class Parseur {
	
	
	public static void LoadFromFile(File f) {
	try{ /* try catch pour gérer le cas où "mydoc.txt" ne s’ouvre pas */
		
		int i = 0, i1 = 0 , j = 0  , j1 = 0 ;
		Scanner sc = new Scanner(f);
	
	    i = Integer.parseInt(sc.next()) ;
	    j = Integer.parseInt(sc.next()) ;
	    
	    Grille g = new Grille( i , j);
	
	    while ( sc.hasNext()) {
		
	    Scanner s2 = new Scanner(sc.next());
	    s2.useDelimiter(",");
	    g.insererVal(i1, j1, Integer.parseInt( s2.next() ) );
	    
	    j1 ++ ;
	    if ( j1 == j*i ) {
	    	j1 = 0  ;
	    	i1 ++ ;
	    }
	 }
	    
	 g.afficher();

	} catch(Exception e) {
		
	    e.printStackTrace();
	}
	
	
	}
	
	
	
	
	public static void main(String args[]) {
		
		LoadFromFile(new File("./grille.txt"));
	}

}
