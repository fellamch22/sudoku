
/* Grille.java
 * Createurs : Amayas HADJ MOHAND, Stephane BERAT, Fella MECHOUAR, Li Fang SU
 * Date de creation : 01/2020
 * Description : Modele de conception des grilles de sudoku*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Grille {

//VARIABLES

	protected Case[][] t;
	protected int pctTrousASupprimer;
	protected int VALEUR_VIDE = 0; // case initialement vide Ã la crÃ©ation de la grille *
	protected int TAILLE_COTE_PLATEAU; // Create tableau 6*6 / 9*9 / 16*16
	protected int TAILLE_SOUSZONE_LIGNE; // Each tab has a len of 2/3/4
	protected int TAILLE_SOUSZONE_COL; // Each tab has a len of 3/3/4
	protected int nbCases;

//CONSTRUCTEUR

//Nouvelle grille (Pour Parseur)
	public Grille(int n, int k) {
		TAILLE_COTE_PLATEAU = n * k; // calculer une fois pour tout n*k
		TAILLE_SOUSZONE_LIGNE = n;
		TAILLE_SOUSZONE_COL = k;
		nbCases = TAILLE_COTE_PLATEAU * TAILLE_COTE_PLATEAU;
		this.t = new Case[n * k][n * k];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				t[i][j] = new Case();
			}
		}
	}

//Cree une Nouvelle grille a partir d une grille (Pour strategie Coloring) 
	public Grille(Grille f) {
		this.pctTrousASupprimer = f.pctTrousASupprimer;
		this.nbCases = f.nbCases;
		this.VALEUR_VIDE = f.VALEUR_VIDE;
		this.TAILLE_COTE_PLATEAU = f.TAILLE_COTE_PLATEAU;
		this.TAILLE_SOUSZONE_LIGNE = f.TAILLE_SOUSZONE_LIGNE;
		this.TAILLE_SOUSZONE_COL = f.TAILLE_SOUSZONE_COL;
		this.t = new Case[TAILLE_SOUSZONE_LIGNE * TAILLE_SOUSZONE_COL][TAILLE_SOUSZONE_LIGNE * TAILLE_SOUSZONE_COL];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				t[i][j] = new Case(f.t[i][j]);
			}
		}
	}

//Recopie les cases d'une grille (pour strategie Coloring)
	public void CopyGrille(Grille f) {
		this.pctTrousASupprimer = f.pctTrousASupprimer;
		this.nbCases = f.nbCases;
		this.VALEUR_VIDE = f.VALEUR_VIDE;
		this.TAILLE_COTE_PLATEAU = f.TAILLE_COTE_PLATEAU;
		this.TAILLE_SOUSZONE_LIGNE = f.TAILLE_SOUSZONE_LIGNE;
		this.TAILLE_SOUSZONE_COL = f.TAILLE_SOUSZONE_COL;
		this.t = new Case[TAILLE_SOUSZONE_LIGNE * TAILLE_SOUSZONE_COL][TAILLE_SOUSZONE_LIGNE * TAILLE_SOUSZONE_COL];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				t[i][j] = new Case(f.t[i][j]);
			}
		}
	}

//Nouvelle grille avec scanner
	public Grille() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir la largeur de la petite grille :");
		TAILLE_SOUSZONE_LIGNE = sc.nextInt();
		System.out.println("Veuillez saisir la longueur de la petite grille : ");
		TAILLE_SOUSZONE_COL = sc.nextInt();
		TAILLE_COTE_PLATEAU = TAILLE_SOUSZONE_LIGNE * TAILLE_SOUSZONE_COL; // calculer une fois pour tout n*k
		nbCases = TAILLE_COTE_PLATEAU * TAILLE_COTE_PLATEAU;
		System.out.println("Veuillez saisir le pourcentage de cases a supprimer : \nAttention a ne pas mettre un valeur excessive par rapport qu nombre de cases de la Grille");
		pctTrousASupprimer = sc.nextInt();
		t = new Case[TAILLE_COTE_PLATEAU][TAILLE_COTE_PLATEAU];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				t[i][j] = new Case();
			}
		}

	}

//Fin CONSTRUCTEUR

//Generation de ArrayList possibility
	public boolean GeneratePossibility() { // On remplis la grille de PROSSIBILITY
		for (int ligne = 0; ligne < TAILLE_COTE_PLATEAU; ligne = ligne + 1) {
			for (int col = 0; col < TAILLE_COTE_PLATEAU; col = col + 1) {
				t[ligne][col].getPossibility().clear();
				if (t[ligne][col].getValue() == VALEUR_VIDE) {// pour chaque case vide
					// on test tous les chiffres. si le chiffre respecte les regles suoku, on ajoute a ArrayList POSSIBILITY
					for (int number = 1; number <= TAILLE_COTE_PLATEAU; number++) {
						if (estOk(ligne, col, number)) {
							t[ligne][col].getPossibility().add(number);
						}
					}
				}
			}
		}
		return false;
	}

//Fin de generation de possibilitees

//Code commun

//Les regles du sudoku
	private boolean estDansLigne(int ligne, int col, int number) {// verification number dans la ligne
		for (int i = 0; i < TAILLE_COTE_PLATEAU; i++)
			if (t[ligne][i].getValue() == number && i != col)
				return true;

		return false;
	}

	private boolean estDansCol(int ligne, int col, int number) {// verification number dans la colonne
		for (int i = 0; i < TAILLE_COTE_PLATEAU; i++)
			if (t[i][col].getValue() == number && i != ligne)
				return true;

		return false;
	}

	private boolean estDansBox(int ligne, int col, int number) {// verification number dans la box
		int posl = ligne - ligne % TAILLE_SOUSZONE_COL;
		int posc = col - col % TAILLE_SOUSZONE_LIGNE;
		for (int i = posl; i < posl + TAILLE_SOUSZONE_COL; i++) {
			for (int j = posc; j < posc + TAILLE_SOUSZONE_LIGNE; j++) {
				if (t[i][j].getValue() == number && i != ligne && j != col) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean estOk(int ligne, int col, int number) {
		boolean test = !estDansLigne(ligne, col, number) && !estDansCol(ligne, col, number)
				&& !estDansBox(ligne, col, number);
		return test;
	}

	public boolean checkSolvability() {// Verification : si le case a vide et sans possibilte, le programme stop

		// pour toutes les CASES
		for (int ligne = 0; ligne < TAILLE_COTE_PLATEAU; ligne++) {
			for (int col = 0; col < TAILLE_COTE_PLATEAU; col++) {
				// si une case est insolvable return false
				if (t[ligne][col].getValue() == VALEUR_VIDE && t[ligne][col].getPossibility().size() == 0) {
					System.out.println("Unresolvable : " + ligne + " * " + col);
					return false;
				}
			}
		}
		return true;
	}
//Fin des regles du sudoku

//Function de suppression des chiffres
	public boolean removeNumberInCol(ArrayList<Integer> num, int col, ArrayList<Integer> ligneAGarder, String strat) {
		boolean flag = false;
		int number;
		for (int i = 0; i < num.size(); i++) {
			number = num.get(i);
			for (int ligne = 0; ligne < TAILLE_COTE_PLATEAU; ligne++) {
				if (!ligneAGarder.contains(ligne) && t[ligne][col].getPossibility().contains(number)) {
					System.out.println(strat + "we remove " + ligne + " * " + col + " " + ": " + number + "\n");
					t[ligne][col].getPossibility().remove((Integer) number);
					flag = true;
				}
			}
		}
		return flag;
	}

	public boolean removeNumberInLigne(ArrayList<Integer> num, int ligne, ArrayList<Integer> colAGarder, String strat) {
		boolean flag = false;
		int number;

		// pour toutes les valeurs de arraylist = triplet
		for (int i = 0; i < num.size(); i++) {
			number = num.get(i);
			for (int col = 0; col < TAILLE_COTE_PLATEAU; col++) {
				if (!colAGarder.contains(col) && t[ligne][col].getPossibility().contains(number)) {
					System.out.println(strat + "we remove " + ligne + " * " + col + " " + ": " + number + "\n");
					t[ligne][col].getPossibility().remove((Integer) number);
					flag = true;
				}
			}
		}
		return flag;
	}

	public boolean removeNumberInBox(int number, ArrayList<Integer> ligneAGarder, ArrayList<Integer> colAGarder,
			String strat) {
		String str = "";
		for (int i = 0; i < ligneAGarder.size(); i++) {
			str += ligneAGarder.get(i) + " * " + colAGarder.get(i) + " "
					+ t[ligneAGarder.get(i)][colAGarder.get(i)].getPossibility() + "   ";
		}
		// on enleve le chiffre de l'arraylist de toutes les cases de la box , sauf sur les 3 coordonnÃ©es
		int posL = ligneAGarder.get(0) - ligneAGarder.get(0) % TAILLE_SOUSZONE_COL;
		int posC = colAGarder.get(0) - colAGarder.get(0) % TAILLE_SOUSZONE_LIGNE;
		boolean flag = false;
		boolean todo = true;

		// pour toutes les cases de la box
		for (int i = posL; i < posL + TAILLE_SOUSZONE_COL; i++) {
			for (int j = posC; j < posC + TAILLE_SOUSZONE_LIGNE; j++) {
				todo = true;
				for (int coord = 0; coord < ligneAGarder.size(); coord++) {
					if ((ligneAGarder.get(coord) == i && colAGarder.get(coord) == j)) {
						todo = false;
					}
				}
				if (t[i][j].getPossibility().contains(number) && todo == true) {
					t[i][j].getPossibility().remove((Integer) number);
					System.out.println(strat + "we remove " + i + " * " + j + " " + ": " + number
							+ " because it present in cell  " + str + "\n");
					flag = true;
					todo = true;
				}

			}
		}
		return flag;
	}
//Fin Function de suppression des chiffres

//Fonction d ecrtiture des chiffres
	//Cette fonction met a jour les 2 grilles SUDOKU et POSSIBILITY lors d update
	public void writeNumber(String strategy, int ligne, int col, int number) {

		t[ligne][col].setValue(number);// UPDATE GRILLE SUDOKU
		for (int l = 0; l < TAILLE_COTE_PLATEAU; l++) {// UPDATE GRILLE POSSIBLITE: on enleve le chiffre en question des
														// possibiliteesde toutes les cases de la ligne
			t[l][col].getPossibility().remove((Integer) number);
		}
		for (int c = 0; c < TAILLE_COTE_PLATEAU; c++) { // on enleve le chiffre en question des possibilitees de toutes
														// les cases de la col
			t[ligne][c].getPossibility().remove((Integer) number);
		}
		int posl = ligne - ligne % TAILLE_SOUSZONE_COL;
		int posc = col - col % TAILLE_SOUSZONE_LIGNE;

		for (int i = posl; i < posl + TAILLE_SOUSZONE_COL; i++) {
			for (int j = posc; j < posc + TAILLE_SOUSZONE_LIGNE; j++) {
				t[i][j].getPossibility().remove((Integer) number); // on enleve le chiffre en question des possibilitees
																	// de toutes les cases de la box
			}
		}
		System.out.println(strategy);
		t[ligne][col].getPossibility().clear();// on vide l'arraylist de possibilite quand on ecrit un chiffre
	}

	public int nbPosNbBox(int ligne, int col, int number) { // calculate the number of possibility of a specific number
															// into a box
		int compteur = 0;
		int posl = ligne - ligne % TAILLE_SOUSZONE_COL;
		int posc = col - col % TAILLE_SOUSZONE_LIGNE;
		for (int i = posl; i < posl + TAILLE_SOUSZONE_COL; i++) {
			for (int j = posc; j < posc + TAILLE_SOUSZONE_LIGNE; j++) {
				if (t[i][j].getPossibility().contains(number)) {
					compteur++;
				}
			}
		}
		return compteur;
	}

	public int nbPosLigne(int ligne, int number) {// calculate the number of possibility of a specific number into a row
		int compteur = 0;
		for (int k = 0; k < TAILLE_COTE_PLATEAU; k++) {
			if (t[ligne][k].getPossibility().contains(number)) {
				compteur++;
			}
		}
		return compteur;
	}

	public int nbPosCol(int col, int number) {// calculate the number of possibility of a specific number into a row
		int compteur = 0;
		for (int k = 0; k < TAILLE_COTE_PLATEAU; k++) {
			if (t[k][col].getPossibility().contains(number)) {
				compteur++;
			}
		}
		return compteur;
	}
//Fin de Fonction d ecrtiture des chiffres

//Function lie aux grilles
	public int nbChiffres() { // pour affichier combien de chiffres trouve par deduction
		int sum = 0;
		for (int ligne = 0; ligne < TAILLE_COTE_PLATEAU; ligne++) {
			for (int col = 0; col < TAILLE_COTE_PLATEAU; col++) {
				if (t[ligne][col].getValue() != VALEUR_VIDE) { // si une case est insolvable return false
					sum++;
				}
			}
		}
		return sum;
	}

	public BigInteger estimate() { // pour affichier combien de chiffres trouve par deduction
		BigInteger sum = new BigInteger("1");
		for (int ligne = 0; ligne < TAILLE_COTE_PLATEAU; ligne++) {
			for (int col = 0; col < TAILLE_COTE_PLATEAU; col++) {
				if (t[ligne][col].getValue() == VALEUR_VIDE) { // si une case est insolvable return false
					sum = sum.multiply(BigInteger.valueOf(t[ligne][col].getPossibility().size()));
				}
			}
		}
		return sum;
	}
//Fin de Function lie aux grilles

// supprime la possibilite d'un chiffre donnee en parametre de toutes les lignes de la box sauf la ligne passee en parametre
	public void supprchifBoxSaufLigne(int ligne, int col, int number) {

		int k = ligne;
		int j = col;
		k--;

		while (k % TAILLE_SOUSZONE_LIGNE != TAILLE_SOUSZONE_LIGNE - 1 && k >= 0) {

			t[k][col].getPossibility().remove((Integer) number);
			col--;

			while (col >= 0 && col % TAILLE_SOUSZONE_COL != TAILLE_SOUSZONE_COL - 1) {

				t[k][col].getPossibility().remove((Integer) number);
				col--;

			}

			col = j + 1;

			while (col % TAILLE_SOUSZONE_COL != 0) {

				t[k][col].getPossibility().remove((Integer) number);
				col++;
			}

			col = j;
			k--;

		}

		k = ligne + 1;

		while (k % TAILLE_SOUSZONE_LIGNE != 0) {

			t[k][col].getPossibility().remove((Integer) number);
			col--;

			while (col >= 0 && col % TAILLE_SOUSZONE_COL != TAILLE_SOUSZONE_COL - 1) {

				t[k][col].getPossibility().remove((Integer) number);
				col--;

			}

			col = j + 1;

			while (col % TAILLE_SOUSZONE_COL != 0) {

				t[k][col].getPossibility().remove((Integer) number);
				col++;
			}

			col = j;
			k++;

		}

	}
// fin de la fonction supprchifBoxSaufLigne

	// supprime la possibilite d'un chiffre donnee en parametre de toutes les colonnes de la box sauf la colonne passee en parametre
	public void supprchifBoxSaufCol(int ligne, int col, int number) {

		int l = ligne;
		int c = col;

		c -= 1;

		while (c >= 0 && (c % TAILLE_SOUSZONE_COL) != TAILLE_SOUSZONE_COL - 1) {

			t[l][c].getPossibility().remove((Integer) number);

			l -= 1;
			while (l >= 0 && (l % TAILLE_SOUSZONE_LIGNE) != TAILLE_SOUSZONE_LIGNE - 1) {

				t[l][c].getPossibility().remove((Integer) number);
				l--;
			}

			l = ligne + 1;

			while (l % TAILLE_SOUSZONE_LIGNE != 0) {

				t[l][c].getPossibility().remove((Integer) number);
				l++;
			}

			l = ligne;
			c--;
		}

		l = ligne;
		c = col + 1;

		while (c % TAILLE_SOUSZONE_COL != 0) {

			t[l][c].getPossibility().remove((Integer) number);

			l -= 1;
			while (l >= 0 && (l % TAILLE_SOUSZONE_LIGNE) != TAILLE_SOUSZONE_LIGNE - 1) {

				t[l][c].getPossibility().remove((Integer) number);
				l--;
			}

			l = ligne + 1;

			while (l % TAILLE_SOUSZONE_LIGNE != 0) {

				t[l][c].getPossibility().remove((Integer) number);
				l++;
			}

			l = ligne;
			c++;
		}

	}

// Calculate the number of possibilities of a number in a ligne of a box
	public int nbPosLigneBox(int ligne, int col, int number) {

		int i = col;
		int poss = 0;

		if ((i) % TAILLE_SOUSZONE_COL == TAILLE_SOUSZONE_COL - 1) {

			if (t[ligne][i].getPossibility().contains(number))
				poss++;
			i -= 1;

		}

		while ((i) % TAILLE_SOUSZONE_COL != TAILLE_SOUSZONE_COL - 1 && i >= 0) {

			if (t[ligne][i].getPossibility().contains(number))
				poss++;
			i -= 1;
		}

		i = col + 1;

		while ((i) % TAILLE_SOUSZONE_COL != 0) {

			if (t[ligne][i].getPossibility().contains(number))
				poss++;
			i += 1;
		}

		return poss;
	}
// end nbPosLigneBox

	// Calculate the number of possibilities of a number in a column of a box
	public int nbPosColBox(int ligne, int col, int number) {

		int i = ligne;
		int poss = 0;

		if (i % TAILLE_SOUSZONE_LIGNE == TAILLE_SOUSZONE_LIGNE - 1) {
			if (t[i][col].getPossibility().contains(number))
				poss++;
			i -= 1;

		}

		while (i % TAILLE_SOUSZONE_LIGNE != TAILLE_SOUSZONE_LIGNE - 1 && i >= 0) {

			if (t[i][col].getPossibility().contains(number))
				poss++;
			i -= 1;
		}

		i = ligne + 1;

		while (i % TAILLE_SOUSZONE_LIGNE != 0) {

			if (t[i][col].getPossibility().contains(number))
				poss++;
			i += 1;
		}

		return poss;
	}

// End nbPosColBox

	public int UniqueInCol(int ligne, int col, int number) {
		// Pour toutes les cases de la box
		int colCompteur = -1;
		int posL = ligne - ligne % TAILLE_SOUSZONE_COL;
		int posC = col - col % TAILLE_SOUSZONE_LIGNE;
		for (int i = posL; i < posL + TAILLE_SOUSZONE_COL; i++) {
			for (int j = posC; j < posC + TAILLE_SOUSZONE_LIGNE; j++) {

				// si la case de la box contient le nombre et la colCompteur est deja set >=0 return false
				if (t[i][j].getPossibility().contains(number) && colCompteur >= 0 && colCompteur != j) {
					return -1;
				}
				// si la case contient la valeur dans ses possibilitees on set la ligne compteur
				if (t[i][j].getPossibility().contains(number) && (colCompteur == -1 || colCompteur == j)) {
					colCompteur = j;
				}
			}
		}
		return colCompteur;
	}

	public int UniqueInLigne(int ligne, int col, int number) {
		// Pour toutes les cases de la box
		int ligneCompteur = -1;
		int posL = ligne - ligne % TAILLE_SOUSZONE_COL;
		int posC = col - col % TAILLE_SOUSZONE_LIGNE;

		for (int i = posL; i < posL + TAILLE_SOUSZONE_COL; i++) {
			for (int j = posC; j < posC + TAILLE_SOUSZONE_LIGNE; j++) {

				// si la case de la box contient le nombre et la lignecompteur est deja set >=0,
				// return false
				if (t[i][j].getPossibility().contains(number) && ligneCompteur >= 0 && ligneCompteur != i) {
					return -1;
				}

				// si la case contient la valeur dans ses possibilitees on set la ligne compteur
				if (t[i][j].getPossibility().contains(number) && (ligneCompteur == -1 || ligneCompteur == i)) {
					ligneCompteur = i;
				}
			}
		}

		return ligneCompteur;
	}

	public ArrayList<Integer> WhereIsXinRow(int col, int number) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int ligne = 0; ligne < TAILLE_COTE_PLATEAU; ligne++) {
			if (t[ligne][col].getPossibility().contains(number)) {
				result.add(ligne);
			}
		}
		result.add(col);
		return result;
	}

	public ArrayList<Integer> WhereIsXinCol(int ligne, int number) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int col = 0; col < TAILLE_COTE_PLATEAU; col++) {
			if (t[ligne][col].getPossibility().contains(number)) {
				result.add(col);
			}
		}
		result.add(ligne);
		return result;
	}
//Fin de code commun

//FONCTIONS POUR GENERATEUR

	public void insererVal(int i, int j, int v) {
		if (!(i < 0 || i >= t.length || j < 0 || j >= t.length || v < 0 || v > TAILLE_COTE_PLATEAU)) {
			t[i][j].setValue(v);
		}
	}

	public void echangeLigne(int a, int b) {
		if (a < 0 || a >= t.length || b < 0 || b > t.length)
			return;
		int tab[] = new int[t[0].length];
		for (int i = 0; i < t[0].length; i++) {
			tab[i] = t[a][i].getValue();
		}
		for (int i = 0; i < t[0].length; i++) {
			t[a][i].setValue(t[b][i].getValue());
		}
		for (int i = 0; i < t[0].length; i++) {
			t[b][i].setValue(tab[i]);
		}
	}

	public void echangeColonne(int a, int b) {

		if (a < 0 || a >= t.length || b < 0 || b > t.length)
			return;
		int tab[] = new int[t[0].length];

		for (int i = 0; i < t.length; i++) {
			tab[i] = t[i][a].val;
		}

		for (int i = 0; i < t.length; i++) {
			t[i][a].val = t[i][b].val;
		}

		for (int i = 0; i < t.length; i++) {
			t[i][b].val = tab[i];
		}

	}

//FIN FONCTIONS POUR GENERATEUR

//AFFICHAGE

	public void displayligne() {
		for (int ligne = 0; ligne < 28 * TAILLE_COTE_PLATEAU + 4; ligne++) { 
			// valeur de test Ã corriger //28 est pour belle affichage
			System.out.print("~");
		}
		System.out.println();
	}

	public void printLine() {
		System.out.print("     ");
		for (int z = 0; z < TAILLE_COTE_PLATEAU; z++)
			System.out.print("~~~~");
		for (int k = 0; k < TAILLE_SOUSZONE_COL; k++)
			System.out.print("~");
		System.out.print("~");
	}

	public void afficher() {
		System.out.print("\n      ");
		for (int j = 0; j < TAILLE_COTE_PLATEAU; j++) {
			if (j % TAILLE_SOUSZONE_LIGNE == 0 && j != 0)
				System.out.print(" ");
			System.out.printf("C%-2s ", j);

		}
		for (int i = 0; i < t.length; i++) {
			if (i % TAILLE_SOUSZONE_COL == 0) {
				System.out.println("");
				printLine();
			}
			System.out.println("");
			System.out.printf(" L%-2s ", i);
			for (int j = 0; j < t[i].length; j++) {
				if (j % TAILLE_SOUSZONE_LIGNE == 0)
					System.out.print("|");
				if (t[i][j].val == 0) {
					System.out.print("__  ");
				} else {
					System.out.printf("%-2s  ", t[i][j].val);
				}
			}
			System.out.print("|");
		}
		System.out.println("");
		printLine();
		System.out.println("");
	}

	public void afficherPossibility() {// Ã supprimer plus tard uniquement pour les test
		System.out.println("\nPossibility :");// Display la grille Possibility
		for (int ligne = 0; ligne < TAILLE_COTE_PLATEAU; ligne++) {
			if (ligne % TAILLE_SOUSZONE_COL == 0) {
				displayligne();
			}
			for (int colonne = 0; colonne < TAILLE_COTE_PLATEAU; colonne++) {
				if (colonne % TAILLE_SOUSZONE_LIGNE == 0) {
					System.out.print("|");
				}
				System.out.printf(" %26s ", t[ligne][colonne].getPossibility());
			}
			System.out.println("|");
		}
		displayligne();// Ligne finale
	}

//FIN AFFICHAGE

}