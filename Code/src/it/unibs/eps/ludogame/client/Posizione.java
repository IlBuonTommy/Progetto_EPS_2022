package src.it.unibs.eps.ludogame.client;
import java.util.HashMap;

//import Posizione.NomePosizione;

public class Posizione {
	
	public static enum NomePosizione{
		Base,Fine,Board
	}
	
	//RED(0),BLUE(1),GREEN(2),YELLOW(3),EMPTY(-1)
    
	
	private NomePosizione nomeposizione;
	private int colore;
	private int arrayposizione;
	private static int[] listaBoard=new int[] {44,45,46,47,48,37,26,15,4,5,6,17,28,39,50,51,52,53,54,65,76,75,74,73,72,83,94,105,116,115,114,103,92,81,70,69,68,67,66,55};
	private static int[] listabasefine=new int[] {0,1,11,12,9,10,20,21,108,109,119,120,99,100,110,111,56,57,58,59,16,27,38,49,64,63,62,61,104,93,82,71};
	static HashMap <Integer,Integer> mappaposizioni=new HashMap<Integer,Integer>();
	
	/**
	 * inizializziamo la mappa
	 */
	static {
		for (int i=0;i<listaBoard.length;i++) {
			mappaposizioni.put(listaBoard[i],i);
		}
		
		for(int i=0;i<listabasefine.length;i++) {
			mappaposizioni.put(listabasefine[i],i%4);
		}
	}
	
	
	public Posizione(int n) {
		setNomePosizione(n);
		this.arrayposizione=this.mappaPosizione(n);
	}
	
	public Posizione(Posizione.NomePosizione nomepos,int col, int arraypos) {
		this.arrayposizione=arraypos;
		this.nomeposizione=nomepos;
		this.colore=col;
	}
	
	/**
	 * prende in input la posizione del bottone e setta se è Base Board o Fine e setta il colore
	 * @param n
	 */
	public void setNomePosizione(int n) {
		
		//baserosso
		if(n<2|| (n<13&n>10)) {
			this.colore=0;
			this.nomeposizione= NomePosizione.Base;
			return;
		}
			
		//baseblu
		if((n<11 & n>8)|| (n<22 & n>19))
		{
			this.colore=1;
			this.nomeposizione= NomePosizione.Base;
			return;
		}
		
		//baseverde
				if(n>=11*9+9 && n<11*10|| (n>=11*10+9))
				{
					this.colore=2;
					this.nomeposizione= NomePosizione.Base;
					return;
				}
		
		//basegiallo
		if(n>=11*9 && n<11*9+2|| (n>=11*10 && n<11*10+2))
		{
			this.colore=3;
			this.nomeposizione= NomePosizione.Base;
			return;
		}
		
		//fine rosso
				if(n>11*5 & n<11*5+5) {
					this.colore=0;
					this.nomeposizione= NomePosizione.Fine;
					return;
				}
		
		//fineblu
		if(n%11==5 & n<11*5 & n>11){
			this.colore=1;
			this.nomeposizione= NomePosizione.Fine;
			return;
			
		}
		
		//fine verde
		if(n>11*5+5 & n<11*6-1){
			this.colore=2;
			this.nomeposizione= NomePosizione.Fine;
			return;
		}
			
		//fine giallo
		if(n%11==5 & n>11*6 & n<11*10){
			this.colore=3;
			this.nomeposizione= NomePosizione.Fine;
			return;
		}
			
		//board
			this.colore=-1;
			this.nomeposizione= NomePosizione.Board;
		
			
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public int mappaPosizione(int n) {
		
			if( mappaposizioni.get(n) != null) {
				return mappaposizioni.get(n);
			}
		
		
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public NomePosizione getNomeposizione() {
		return nomeposizione;
	}

	
	/**
	 * 
	 * @return
	 */
	public int getArrayposizione() {
		return arrayposizione;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getColor() {
		return this.colore;
	}
	
	
}
