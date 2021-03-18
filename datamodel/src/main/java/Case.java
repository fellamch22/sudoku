
/* Case.java
 * Createurs : Amayas HADJ MOHAND, Stephane BERAT, Fella MECHOUAR, Li Fang SU
 * Date de creation : 01/2020
 * Description : Modele de conception unitaire des grilles de sudoku*/

import java.util.ArrayList;

public class Case {
	private boolean[]t;
 	protected int val;
	protected ArrayList<Integer> Possibility;


	Case() {
		Possibility =  new ArrayList<Integer>();
		val = 0;
	}
	
	Case(Case a) {
		this.Possibility = new ArrayList<Integer>(a.Possibility);
		this.val = a.val;
	}	
	
	Case(int n,int k){
		t=new boolean[n*k];
		val=0;
		for(int i=0;i<t.length;i++) {
			t[i]=true;
		}
	}

	public int getValue() {
		return val;
	}

	public void setValue(int value) {
		this.val = value;
	}

	public ArrayList<Integer> getPossibility() {
		return Possibility;
	}

	public void setPossibility(ArrayList<Integer> possibility) {
		Possibility.addAll(possibility);
	}
	
	//boolean
	
	public boolean[] gettab() {
		return t;
	}
	
	public void majpos(int n) {
		if(n<t.length && n>=0) {
			t[n]=false;
		}
	}
	
	boolean estpresent(int n) {
		if(n>0 && n<t.length) {
			return t[n-1];
		}
		return false;
	}
	
	void removeallexpt(int n) {
		for(int i=0;i<t.length;i++) {
			if(i!=n) {
				majpos(i);
			}
		}
	}
	
	boolean estUniquetrue() {
		int a=0;
		for(int i=0;i<t.length;i++) {
			if(t[i]==true) a++;
		}
		if(a==1)return true;
		return false;
	}


}
