
/* Launcher.java
 * Createurs : Amayas HADJ MOHAND, Stephane BERAT, Fella MECHOUAR, Li Fang SU
 * Date de creation : 04/2020
 * Description : Lance le menu du projet*/

import java.io.File;
import java.util.Scanner;

public class Launcher {
	static Scanner sc = new Scanner(System.in);

	public static boolean Strategy(Grille g) {
		// si une strategie est true, on relance toutes les strategies

		if (
				StrategyChiffresExclusifs.StrategieChiffresExclusifsCol(g)
				|| StrategyChiffresExclusifs.StrategieChiffresExclusifsLignes(g)
				|| StrategyChiffreExclusifRegion.strategieChiffreExclusifRegionLigne(g)
				|| StrategyChiffreExclusifRegion.strategieChiffreExclusifRegionCol(g)
				|| StrategyPairesExclusives.StrategiePairesExclusives(g,2) //2 cases identiques dans une box
				|| StrategyPairesExclusives.StrategiePairesExclusives(g,3) //3 cases similaires
				|| StrategyPairesExclusives.StrategiePairesExclusives(g,4) //4 cases similaires
				|| StrategyLockedCandidates.StrategyLockedCandidatesLignes(g)
				|| StrategyLockedCandidates.StrategyLockedCandidatesCol(g)
				|| StrategyHiddenDouble.StrategyHiddenDoubleCol(g,2)
				|| StrategyHiddenDouble.StrategyHiddenDoubleLigne(g,2)
				|| StrategyHiddenDouble.StrategyHiddenDoubleCol(g,3)
				|| StrategyHiddenDouble.StrategyHiddenDoubleLigne(g,3)
				|| StrategyHiddenDouble.StrategyHiddenDoubleCol(g,4)
				|| StrategyHiddenDouble.StrategyHiddenDoubleLigne(g,4)
				|| StrategyXWing.StrategieXWingCOL(g)
				|| StrategyXWing.StrategieXWingLIGNE(g)
            	|| StrategySwordfish.StrategieSwordfishLIGNE(g)
            	|| StrategySwordfish.StrategieSwordfishCOL(g)
            	|| StrategyUplets.StrategieUplets(g,2) //doublets         	           	
            	|| StrategyUplets.StrategieUplets(g,3) //triplets         	           	
            	|| StrategyUplets.StrategieUplets(g,4) //quadriplets         	           	
            	|| StrategyUplets.StrategieUplets(g,5) //cincuplets
            	|| StrategyXYZWing.applyStrategy(g)
            	|| StrategyXYWing.applyStrategy2(g)
            	|| StrategyColoring.coloring(g)//toujours intervenir en dernier sur les stratégies
            	
				) {
			return true;
		}
	return false;	
	}
	
	
	public static void menu() {
		
		Grille g;

		System.out.println("---------------------------------------------------------------------------------------------\n"
				+ "Bienvenue dans le sudoku solver ! \n"
				+ "---------------------------------------------------------------------------------------------\n" 
				+ "1 - Charger une nouvelle grille d'un fichier\n"
				+ "2 - Generer une nouvelle grille\n"
				+ "3 - Sortir du programme");
		switch (sc.nextInt()) {
		case 1:
			System.out.println("Veuillez introduire le chemin du fichier contenant la grille : ");
			sc.nextLine();
			try { /* try catch pour gérer le cas où "mydoc.txt" ne s’ouvre pas */

			g = Parseur.LoadFromFile(new File(sc.nextLine()));
			
			g.afficher();
			g.GeneratePossibility();// Generate Possibility
			menuSolver(g);
			} catch (Exception e) {
				
				System.out.println("Imposssible de charger le fichier depuis le chemin introduit \n Reessayez A nouveau");
				menu();
				
			}
		
		break;
		case 2:
			System.out.println("Generateur Sudoku");		
			g = Generateur.GenerateurGrille();	
			g.GeneratePossibility();
			//g.afficher();
			Backtracking.compteur=0;
			menuSolver(g);
			break;
		case 3 :
			System.out.println("---------------------------------------------------------------------------------------------"
					+ " \n                                 À très bientôt  !! \n"
					         + "---------------------------------------------------------------------------------------------\n"
					           + "Sudoku - UE PROJET S4            Année Universitaire :  2019 - 2020     "
					           + "Université de Paris ©\n");
			System.exit(0);
		default :
			System.out.println("Choix invalide");
			menu();
			break;
		}
	}

	public static void menuSolver( Grille g ) {
		
		
	 System.out.println(" Voulez vous : \n"	
			     +"1 - Resoudre la grille par Deduction\n"
				+ "2 - Resoudre la grille par Backtracking avec application prealable des strategies\n"
				+ "3 - Resoudre la grille directement par Backtracking\n"
				+ "4 - Sauvegarder la grille actuelle\n"
				+ "5 - Charger une autre grille\n"
				+ "6 - Sortir du programme");

	 switch(sc.nextInt()) {
	 
	 case 1:
			menudeduction(g);
			menu();
			break;
	case 2 :
			g.afficherPossibility();
			while(Strategy(g)) {}
			menubacktracking(g);
			menu();
			break;
		case 3 : 
			menubacktracking(g);
			menu();
			break;
		case 4 : 	
			System.out.println("Sur quel fichier souhaitez vous sauvegarder ?");
			sc.nextLine();
			String savLocation = sc.nextLine();
			Save.sauvegarder(g,savLocation);
			menuSolver(g);
			break;
		case 5 :
			menu();
			break;
		case 6 :
			System.out.println("---------------------------------------------------------------------------------------------"
					+ " \n                                 À très bientôt  !! \n"
					         + "------------------------------------------------------------------ --------------------------\n"
					           + "Sudoku - UE PROJET S4            Année Universitaire :  2019 - 2020     "
					           + "Université de Paris ©\n");
			System.exit(0);
		default :
			System.out.println("Choix invalide");
			menuSolver(g);
			break;
	 }
	 
	}
	
	public static void menubacktracking(Grille g) {
	
		Backtracking.compteur=0;
		g.afficherPossibility();
		if ( g.checkSolvability() == false ) {  System.exit(0); } // on verifie que les cases vides ont toutes au moins une possiblite
		System.out.println("\nAppui sur \"ENTER\" pour lancer le Backtracking avec " + g.estimate() + " tests\n"
				+ g.nbChiffres() + "/"  + g.TAILLE_COTE_PLATEAU * g.TAILLE_COTE_PLATEAU + " Chiffres sur la grille");	
		sc.nextLine();
		sc.nextLine();
		System.out.println("Backtracking en cours...\n");
		
		g.afficherPossibility();
		// BACKTRACKING JOKER
		long debut = System.currentTimeMillis();

		Backtracking.backtracking(g);

		if (Backtracking.compteur != 0) {
			System.out.println("=> Resolu par Backtracking :" + Backtracking.compteur + " Solution(s) trouvees en "
					+ (double) (System.currentTimeMillis() - debut) / 1000 + " secondes");
		} else {
			System.out.println("=> Impossible de resoudre la grille apres "
					+ (double) (System.currentTimeMillis() - debut) / 1000 + " secondes");
		}
		System.out.println("Grille Originale :");
		g.afficher();
	}

	public static void menudeduction(Grille g) {
		int nbcases = g.TAILLE_COTE_PLATEAU * g.TAILLE_COTE_PLATEAU;
		int chiffresOri = g.nbChiffres();
		g.afficherPossibility();

		long debut = System.currentTimeMillis();
		

		while (true) {
			//if (StrategyColoring.sauvegarde != null) StrategyColoring.sauvegarde.afficher();  
			if (Deduction.deduction(g) == false) {
				if (!Strategy(g)) {
					System.out.println("Aucune autre deduction possible - " + chiffresOri + " => " + g.nbChiffres() + " /"
							+ g.TAILLE_COTE_PLATEAU * g.TAILLE_COTE_PLATEAU + " Chiffres trouves en "
							+ (double) (System.currentTimeMillis() - debut) / 1000 + " secondes");
					//System.out.println(g.listesolution);
					g.afficher();
					if ( g.checkSolvability() == false ) { 					
						g.afficher();
						g.afficherPossibility();
					}
					menubacktracking(g);
					break;
				}
			}
			if (g.nbChiffres() == nbcases) {
				g.afficher();
				System.out.println(
						"Resolu par Deduction en " + (double) (System.currentTimeMillis() - debut) / 1000 + " secondes");
				break;
			}
		}
	}

	public static void main(String args[]) {
		menu(); // Demarrage du programme

	}

}
