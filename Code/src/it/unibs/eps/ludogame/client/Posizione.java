package src.it.unibs.eps.ludogame.client;
import java.util.HashMap;

public class Posizione {
	
	public static enum NomePosizione{
		BaseRosso,BaseBlu,BaseVerde,BaseGiallo,FineRosso,FineBlu,FineVerde,FineGiallo,Board
	}
	
	private NomePosizione nomeposizione;
	private int arrayposizione;
	private static int[] listaBoard=new int[] {44,45,46,47,48,37,26,15,4,5,6,17,28,39,50,51,52,53,54,65,76,75,74,73,72,83,94,105,116,115,114,103,92,81,70,69,68,67,66,55};
	private static int[] listabasefine=new int[] {0,1,11,12,9,10,20,21,108,109,119,120,99,100,110,111,56,57,58,59,16,27,38,49,64,63,62,61,104,93,82,71};
	static HashMap <Integer,Integer> mappaposizioni=new HashMap<Integer,Integer>();
	
	//inizializzo la mappa
	static {
		for (int i=0;i<listaBoard.length;i++) {
			mappaposizioni.put(listaBoard[i],i);
		}
		
		for(int i=0;i<listabasefine.length;i++) {
			mappaposizioni.put(listabasefine[i],i%4);
		}
	}
	
	
	public Posizione(int n) {
		this.nomeposizione=getNomePosizione(n);
		this.arrayposizione=this.getPosizione(n);
	}
	
	public static NomePosizione getNomePosizione(int n) {
		
		if(n<2|| (n<13&n>10))
			return NomePosizione.BaseRosso;
		
		if((n<11 & n>8)|| (n<22 & n>19))
			return NomePosizione.BaseBlu;
		
		if(n>=11*9 && n<11*9+2|| (n>=11*10 && n<11*10+2))
			return NomePosizione.BaseGiallo;
		
		if(n>=11*9+9 && n<11*10|| (n>=11*10+9))
			return NomePosizione.BaseVerde;
		
		if(n>11*5+5 & n<11*6-1)
			return NomePosizione.FineVerde;
		
		if(n%11==5 & n>11*6 & n<11*10)
			return NomePosizione.FineGiallo;
		
		if(n>11*5 & n<11*5+5)
			return NomePosizione.FineRosso;
		
		if(n%11==5 & n<11*5 & n>11)
			return NomePosizione.FineBlu;
		
		return NomePosizione.Board;
	}
	
	public int getPosizione(int n) {
		
			if( mappaposizioni.get(n) != null) {
				return mappaposizioni.get(n);
			}
		
		
		return 0;
	}

	public NomePosizione getNomeposizione() {
		return nomeposizione;
	}

	

	public int getArrayposizione() {
		return arrayposizione;
	}

	
	
}
