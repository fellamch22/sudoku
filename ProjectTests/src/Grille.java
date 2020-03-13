import java.util.Scanner;

public class Grille{
 int k;
 int n;
 Case t[][];
 
 
 public Grille (int n , int k ){
	 
	 this.n = n ;
	 this.k = k ;
	 
	 this.t = new Case [n*k][n*k];
	 
	 for(int i=0;i<t.length;i++){
		    for(int j=0;j<t[i].length;j++){
		     
		    	t[i][j]=new Case(n,k);

		    }
	 }
 }
 
 
 
 public Grille(){

  Scanner sc = new Scanner(System.in);
  System.out.println("veuillez saisir la largeur de la petite grille");
  n=sc.nextInt();

  Scanner sc1 = new Scanner(System.in);
  System.out.println("veuillez saisir la longuer de la petite grille");
  k=sc.nextInt();

  t=new Case [n*k][n*k];
  for(int i=0;i<t.length;i++){
    for(int j=0;j<t[i].length;j++){
      t[i][j]=new Case(n,k);

    }
  }

}
 
void initialiser(){
  for(int i=0;i<t.length;i++){
    for(int j=0;j<t[i].length;j++){
      t[i][j].val=i+1;

}
}
}

void insererVal(int i,int j,int v){
   if(!(i< 0 || i>= t.length || j<0 || j >= t.length || v<0 || v > n*k)) {
            t[i][j].val = v ;
   }
}



void echangeLigne(int a , int b){
  if(a<0 || a>=t.length || b<0 || b>t.length) return;

  int tab[]=new int [t[0].length];

 for(int i=0;i<t[0].length;i++){
   tab[i]=t[a][i].val;
 }

 for(int i=0;i<t[0].length;i++){
   t[a][i].val=t[b][i].val;
}

for(int i=0;i<t[0].length;i++){
t[b][i].val=tab[i];
}
}

void afficher(){
  for(int i=0;i<t.length;i++){
    if(i%k==0){
      System.out.println("");
      for(int z=0;z<n*k;z++)
      System.out.print("~~");
              }

    if(i%k==0)
     System.out.print("~~");

    System.out.println("");

    for(int j=0;j<t[i].length;j++){
      if(j%n==0)
      System.out.print("|");
      System.out.print(t[i][j].val+" ");


    }
    System.out.print("|");
  }
  System.out.println("");
  for(int z=0;z<2*n*k+n;z++)
    System.out.print("~");

  System.out.println("");

}
}
