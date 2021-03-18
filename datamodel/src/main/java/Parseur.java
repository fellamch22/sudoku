
/* Parseur.java
 * Createurs : Fella MECHOUAR
 * Date de creation : 02/2020
 * Description : Charge une grille a partir d'un fichier*/

import java.util.*;
import java.io.*;

public class Parseur {
	
	public static Grille LoadFromFile(File f) throws Exception{
		Grille g ;

			int i = 0, i1 = 0, j = 0, j1 = 0;
			Scanner sc = new Scanner(f); 

			i = Integer.parseInt(sc.next());
			j = Integer.parseInt(sc.next());

			g = new Grille(i, j);

			while (sc.hasNext()) {

				Scanner s2 = new Scanner(sc.next());
				s2.useDelimiter(",");
				g.insererVal(i1, j1, Integer.parseInt(s2.next()));

				j1++;
				
				if (j1 == j * i) {
					j1 = 0;
					i1++;
				}
			}
			int chiffresOri=g.nbChiffres();
			System.out.println("Grille chargee depuis : "+ f + " \n" + chiffresOri +"/"+ g.TAILLE_COTE_PLATEAU*g.TAILLE_COTE_PLATEAU+ " Chiffres initiaux !");
		
		return g ;

	}

}
