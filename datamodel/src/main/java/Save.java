
/* save.java
 * Createur : Li Fang SU
 * Date de creation : 05/2020
 * Description : Sauvegarde les grilles generes sous format fichier */

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Save {
	public static void sauvegarder(Grille g, String relativePath) {
		File file = new File(relativePath);
		try {
			if (!file.exists()) {
				file.createNewFile();

				System.out.println("\n" + " File Created at location :" + relativePath + "\n");
				FileWriter myWriter = new FileWriter(file);
				
				String toWrite = g.TAILLE_SOUSZONE_LIGNE + " " + g.TAILLE_SOUSZONE_COL + "\n";// we write n and k
				
				for (int ligne = 0; ligne < g.TAILLE_COTE_PLATEAU; ligne++) {// we write each number
					for (int col = 0; col < g.TAILLE_COTE_PLATEAU; col++) {
						toWrite = toWrite + g.t[ligne][col].getValue() + ", ";
					}
					toWrite = toWrite + "\n";
				}
				myWriter.write(toWrite);
				myWriter.close();
			} else {
				System.out.println("\n" + "File " + relativePath + " already exists, save cancelled ");
			}
		} catch (IOException e) {
			System.out.println("\n" + "File " + relativePath + " already exists, save cancelled ");
		}
	}

}