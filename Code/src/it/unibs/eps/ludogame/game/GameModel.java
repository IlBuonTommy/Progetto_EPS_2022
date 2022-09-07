package it.unibs.eps.ludogame.game;

import java.io.Serializable;
import java.util.Arrays;

import it.unibs.eps.ludogame.client.Posizione;

public class GameModel implements Serializable{
    //RED(0),BLUE(1),GREEN(2),YELLOW(3),EMPTY(-1)
    //3 array di caselle: plancia[40] base[colore][4] finale[colore][4]
    private Casella[] plancia;
    private Casella[][] base;
    private Casella[][] finale;
    private Giocatore[] player;
    private int currentPlayerIndex;
    public int numGiocatori;
    
    public GameModel (int numGiocatori, Giocatore player[]){
        //DEBUG ONLY
        System.out.println("GameModel: inizializzazione tabellone con "+numGiocatori+" giocatori");

        this.numGiocatori=numGiocatori;
        this.plancia = new Casella[40];
        this.base = new Casella[4][4];
        this.finale = new Casella[4][4];
        this.player = new Giocatore[numGiocatori];
        this.player = player;
        this.currentPlayerIndex = 0;
        reset();
    }

	/** 
     * @return int ritorna il numero del giocatore che sta effettuando il turno
     */
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    public Casella[] getPlancia() {
		return plancia;
	}

	public Casella[][] getBase() {
		return base;
	}

	public Casella[][] getFinale() {
		return finale;
	}

	/** 
     * @return bool se il giocatore attuale è un bot ritorna true, false viceversa.
     */
    public boolean currentIsBot(){
        if(player[currentPlayerIndex].getBot())
            return true;
        return false;
    }

    /** 
     * Incrementa l'index del turno e fa passare il turno al prossimo giocatore.
     */
    public void nextTurn(){
        currentPlayerIndex++;
        if(currentPlayerIndex>=numGiocatori)
            currentPlayerIndex=0;
    }

    /**
     * Questo metodo serve per resettare il tabellone alle condizioni iniziali
     * NON I GIOCATORI O IL LORO NUMERO
     */
    public void reset(){
        //DEBUG ONLY
        System.out.println("GameModel: reset tabellone");

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                this.base[i][j] = new Casella();
                this.finale[i][j] = new Casella();
            }
        }
        for(int i=0; i<40; i++){
            this.plancia[i]=new Casella();
        }
        
        for(int i=0; i<numGiocatori; i++){
            for(int j=0; j<4; j++){
                this.base[i][j].setColore(i);
            }
        }
    }
    
    /** Questo metodo controlla se è rimasta qualche pedina nella base di un giocatore
     * @param colore il colore sul quale si vuole effettuare il controllo
     * @return boolean torna vero se c'è almeno una pedina, falso altrimenti
     */
    public boolean someoneInBase(int colore){
        boolean test=false;
        for(int i=0; i<4; i++){
            if(base[colore][i].getColore()>=0)
                test=true;
        }
        return test;
    }

    
    /** Questo metodo controlla se qualcuno ha vinto
     * @return int il colore del vincitore, -1 se non ha trovato vincitori.
     */
    public int checkWin(){
        int cont=0;
        for(int i=0; i<numGiocatori; i++){
            for(int j=0; j<4; j++){
                if(finale[i][j].getColore()!=-1)
                    cont++;
            }
            if(cont == 4){
                //DEBUG ONLY
                System.out.println("GameModel: Richiesta controllo vincita con esito: "+i);

                return i;
            }
            cont=0;
        }
        //DEBUG ONLY
        System.out.println("GameModel: Richiesta controllo vincita con esito negativo");

        return -1;
    }

    //DEBUG ONLY: ALESSIO
    public int ControlloVincitaTempDebug() {
    	return -1;
    }

    /** Questo metodo esegue una mangiata sulla plancia
     * @param posizione indica la posizione della pedina da mangiare sulla plancia
     * @return boolean se true, la mangiata è stata eseguita correttamente
     */
    private boolean mangiata(int posizione){
        if(plancia[posizione].getDoppio())
            return false;
        for(int i=0; i<4; i++){
            if(base[plancia[posizione].getColore()][i].getColore()==-1){
                base[plancia[posizione].getColore()][i].setColore(plancia[posizione].getColore());
                plancia[posizione].setColore(-1);
                
                //DEBUG ONLY
                System.out.println("GameModel: Mangiata eseguita su posizione "+posizione);

                return true;
            }
        }
        
        //DEBUG ONLY
        System.out.println("GameModel: errore su mangiata");

        return false;
    }

    
    /** Questo metodo valuta e/o esegue un movimento di una pedina che inizialmente si trova sulla plancia
     * @param posizione indica la posizione della pedina da muovere sulla plancia
     * @param valoreDado indica il valore del dado da considerare
     * @param daEseguire se messo a true il metodo effettua il movimento dopo aver valutato se fattibile
     * @return boolean se true, il movimento è fattibile, se false il movimento non si può fare
     */
    public boolean movimentoDaPlancia(int posizione, int valoreDado, boolean daEseguire){
        //DEBUG ONLY
        System.out.println("GameModel: Richiesto movimento da plancia dalla posizione "+posizione+" e con valore dado "+valoreDado);

        int nuovaPosizione = posizione + valoreDado;
        if(nuovaPosizione>39)
            nuovaPosizione = nuovaPosizione - 40;
        //controllo se ha pedina doppia davanti
        for(int i=1; i<=valoreDado; i++){
            if(plancia[posizione].getColore()==0){
                if(posizione <= 39 && nuovaPosizione >= (10*plancia[posizione].getColore()) && posizione > 33){
                    i=valoreDado+1;
                }else{
                    if(posizione+i>39){
                        if(plancia[posizione+i-40].getDoppio())
                            return false;
                    }else{
                        if(plancia[posizione+i].getDoppio())
                            return false;
                    }
                }
            }else{
                if(posizione <= (10*plancia[posizione].getColore())-1 && nuovaPosizione >= (10*plancia[posizione].getColore()) && posizione > (10*plancia[posizione].getColore())-7){
                    i=valoreDado+1;
                }else{
                    if(posizione+i>39){
                        if(plancia[posizione+i-40].getDoppio())
                            return false;
                    }else{
                        if(plancia[posizione+i].getDoppio())
                            return false;
                    }
                }
            }
        }

        //controlla se deve andare in finale
        boolean finaleT = false;
        if(plancia[posizione].getColore()==0){
            if(posizione <= 39 && nuovaPosizione >= (10*plancia[posizione].getColore()) && posizione > 33){
                finaleT = true;
            }
        }else{
            if(posizione <= (10*plancia[posizione].getColore())-1 && nuovaPosizione >= (10*plancia[posizione].getColore()) && posizione > (10*plancia[posizione].getColore())-7){
                finaleT = true;
            }
        }
        if(finaleT){
            int nuovaPosizioneT=nuovaPosizione;
            if(plancia[posizione].getColore() != 0){
                nuovaPosizioneT=nuovaPosizioneT-(10*plancia[posizione].getColore());
            }
            if(nuovaPosizioneT<=3){
                if(finale[plancia[posizione].getColore()][nuovaPosizioneT].getColore()!=-1)
                    return false;
                
                if(daEseguire){
                    finale[plancia[posizione].getColore()][nuovaPosizioneT].setColore(plancia[posizione].getColore());
                    if(plancia[posizione].getDoppio()){
                        plancia[posizione].setDoppio(false);
                    }else{
                        plancia[posizione].setColore(-1);
                    }
                }
                return true;
            }
            return false;
        }
        
        //controlla se deve mangiare
        if(plancia[nuovaPosizione].getColore()!=-1 && plancia[nuovaPosizione].getColore()!=plancia[posizione].getColore()){
            if(plancia[nuovaPosizione].getDoppio())
                return false;
            if(daEseguire){
                mangiata(nuovaPosizione);
                plancia[nuovaPosizione].setColore(plancia[posizione].getColore());
                if(plancia[posizione].getDoppio()){
                    plancia[posizione].setDoppio(false);
                }else{
                    plancia[posizione].setColore(-1);
                }
                return true;
            }
        }

        //controlla se crea una doppia
        if(plancia[nuovaPosizione].getColore()==plancia[posizione].getColore() && !plancia[nuovaPosizione].getDoppio()){
            if(nuovaPosizione != 0 && nuovaPosizione != 10 && nuovaPosizione != 20 && nuovaPosizione != 30){
                if(daEseguire){
                    plancia[nuovaPosizione].setDoppio(true);
                    if(plancia[posizione].getDoppio()){
                        plancia[posizione].setDoppio(false);
                    }else{
                        plancia[posizione].setColore(-1);
                    }
                return true;
                }
            }else{
                return false;
            }
        }

        //effettua la mossa
        if(daEseguire){
            plancia[nuovaPosizione].setColore(plancia[posizione].getColore());
            if(plancia[posizione].getDoppio()){
                plancia[posizione].setDoppio(false);
            }else{
                plancia[posizione].setColore(-1);
            }
        }
            
        return true;
    }

    
    /** Questo metodo valuta e/o esegue un movimento di una pedina che inizialmente si trova nella base di un giocatore
     * @param colore indica il colore della pedina da muovere sulla plancia dalla base
     * @param valoreDado indica il valore del dado da considerare
     * @param daEseguire se messo a true il metodo effettua il movimento dopo aver valutato se fattibile
     * @return boolean se true, il movimento è fattibile, se false il movimento non si può fare
     */
    public boolean movimentoDaBase(int colore, int valoreDado, boolean daEseguire){
        //DEBUG ONLY
        System.out.println("GameModel: richiesto moviemnto da base di colore "+ PlayerColor.valueOf(colore));

        if(valoreDado != 6)                         
            return false;
        if(!someoneInBase(colore))
            return false;
        //controllo casella uscita occupata
        if(plancia[colore*10].getDoppio())
            return false;
        if(plancia[colore*10].getColore()==colore){
            return false;
        }
        //esecuzione mossa
        if(!daEseguire)
            return true;

        if(plancia[colore*10].getColore()!=-1){ //casella occupata, bisogna eseguire una mangiata
            mangiata(colore*10);
        }
        //movimento pedina
        for(int i=0; i<4; i++){
            if(base[colore][i].getColore()!=-1){
                plancia[colore*10].setColore(colore);
                plancia[colore*10].setDoppio(false);
                base[colore][i].setColore(-1);
                return true;
            }
        }
        return true;
    }

    
    /** Questo metodo valuta e/o esegue un movimento di una pedina che si trova già nel tratto finale
     * @param posizione indica la posizione della pedina
     * @param colore indica il colore della pedina da muovere
     * @param valoreDado indica il valore del dado da considerare
     * @param daEseguire se messo a true il metodo effettua il movimento dopo aver valutato se fattibile
     * @return boolean se true, il movimento è fattibile, se false il movimento non si può fare
     */
    public boolean movimentoDaFinale(int posizione, int colore, int valoreDado, boolean daEseguire){
        //DEBUG ONLY
        System.out.println("GameModel: richiesto moviemnto da finale del colore "+PlayerColor.valueOf(colore)+" dalla posizione: "+posizione);

        if(posizione+valoreDado>=4)
            return false;
        if(finale[colore][posizione+valoreDado].getColore()!=-1)
            return false;
        if(!daEseguire)
            return true;
        finale[colore][posizione].setColore(-1);
        finale[colore][posizione+valoreDado].setColore(colore);
        return true;
    }


    /** Questo metodo esegue un movimento di una pedina da parte di un BOT deterministico
     * Il bot da la priorità a: mettere in base la pedina, uscire con una nuova pedina, creare una doppia più distante, mangiare la pedina più vicina alla vittoria, liberare l'uscita delle pedine, spostare la pedina più distante, annullare il turno
     * @param colore indica il colore della pedina da muovere
     * @param valoreDado indica il valore del dado da considerare
     * @return boolean se true, il movimento è stato fatto senza errori
     */
    public boolean movimentoBOT(int colore, int valoreDado){
        //DEBUG ONLY
        System.out.println("GameModel: richiesto moviemnto BOT del colore "+PlayerColor.valueOf(colore));
        int[] posizione = {-1,-1,-1,-1};
        int indPos = 0, posInizioP=colore*10, posFineP=colore*10-1;
        if(posFineP<0)
            posFineP=39;
        for(int i=posFineP; i>=0; i--){
            if(plancia[i].getColore()==colore){
                posizione[indPos]=i;
                indPos++;
            }
            if(i==posInizioP)
                i=-2;
            else if(i==0){
                i=40;
            }  
        }
        for(int i=0; i<4; i++){
            if(finale[colore][i].getColore()==colore){
                posizione[indPos]=i+40;
                indPos++;
            }  
        }

        //Entrare in base finale
        for(int i=0; i<4; i++){
            if(posizione[i]!=-1 && posizione[i]<40){
                int nuovPos= posizione[i]+valoreDado;
                if(nuovPos>39)
                    nuovPos=nuovPos-40;
                if(nuovPos>=posInizioP && posizione[i]<posInizioP){
                    if(movimentoDaPlancia(posizione[i], valoreDado, false)){
                        movimentoDaPlancia(posizione[i], valoreDado, true);
                        return true;
                    }
                }
            }
        }
        
        //Uscita con una nuova pedina
        if(valoreDado==6 && someoneInBase(colore) && plancia[10*colore].getColore()!=colore && !plancia[10*colore].getDoppio()){
            movimentoDaBase(colore, valoreDado, true);
            return true;
        }

        //creare doppia più distante
        for(int i=0; i<4; i++){
            if(posizione[i]!=-1 && posizione[i]<40){
                int nuovPos= posizione[i]+valoreDado;
                if(nuovPos>39)
                    nuovPos=nuovPos-40;
                if(nuovPos>=posInizioP && posizione[i]<posInizioP){}
                else{
                    if(plancia[nuovPos].getColore()==colore){
                        if(movimentoDaPlancia(posizione[i], valoreDado, false)){
                            movimentoDaPlancia(posizione[i], valoreDado, true);
                            return true;
                        }
                    }
                }
            }
        }

        //mangiare la più distante
        for(int i=0; i<4; i++){
            if(posizione[i]!=-1 && posizione[i]<40){
                int nuovPos= posizione[i]+valoreDado;
                if(nuovPos>39)
                    nuovPos=nuovPos-40;
                if(plancia[nuovPos].getColore()!=-1 && plancia[nuovPos].getColore()!=colore){
                    if(movimentoDaPlancia(posizione[i], valoreDado, false)){
                        movimentoDaPlancia(posizione[i], valoreDado, true);
                        return true;
                    }
                }
            }
        }

        //Liberare uscita pedine
        for(int i=0; i<4; i++){
            if(posizione[i]==posInizioP){
                if(movimentoDaPlancia(posizione[i], valoreDado, false)){
                    movimentoDaPlancia(posizione[i], valoreDado, true);
                    return true;
                }
            }
        }

        //Spostare la più distante
        for(int i=0; i<4; i++){
            if(posizione[i]!=-1 && posizione[i]<40){
                if(movimentoDaPlancia(posizione[i], valoreDado, false)){
                    movimentoDaPlancia(posizione[i], valoreDado, true);
                    return true;
                }
            }
        }

        //spostare quelle in base
        for(int i=0; i<4; i++){
            if(posizione[i]!=-1 && posizione[i]>=40){
                if(movimentoDaFinale(posizione[i]-40, colore, valoreDado, false)){
                    movimentoDaFinale(posizione[i]-40, colore, valoreDado, true);
                    return true;
                }
            }
        }

        //non puoi fare nulla annulla il turno

        return false;
    }
    

    /** Questa funzione è di collegamento tra il model e il controller, quando l'utente preme un tasto sulla plancia
     * viene passato la posizione del tasto premuto a questa funzione che controlla se il movimento desiderato dall'utente è 
     * realizzabile o meno, in caso si possa effettuare lo esegue.
     * @param pos indica in che punto si trova la pedina sulla plancia
     * @param x il primo parametro della matrice o l'unico parametro del vettore plancia
     * @param y il secondo parametro della matrice
     * @param valoreDado indica il valore del dado del giocatore corrente
     * @return boolean se true, il movimento è stato fatto senza errori, false il moviemnto non è stato fatto.
     */
    public boolean tastoPremuto(Posizione.NomePosizione pos, int posizione, int colore, int valoreDado){
        if(pos == Posizione.NomePosizione.Base){
            if(movimentoDaBase(colore, valoreDado, false)){
                //DEBUG ONLY
                System.out.println("GameModel: MOSSA ESEGUITA: Tasto premuto in Base dal colore "+PlayerColor.valueOf(colore));
                
                movimentoDaBase(colore, valoreDado, true);
                return true;
            }
        }
        if(pos == Posizione.NomePosizione.Fine){
            if(movimentoDaFinale(posizione, colore, valoreDado, false)){
                //DEBUG ONLY
                System.out.println("GameModel: MOSSA ESEGUITA: Tasto premuto in Finale dal colore "+PlayerColor.valueOf(colore)+ " nella posizione "+posizione);

                movimentoDaFinale(posizione, colore, valoreDado, true);
                return true;
            }
        }
        if(pos == Posizione.NomePosizione.Board){
            if(movimentoDaPlancia(posizione, valoreDado, false)){
                //DEBUG ONLY
                System.out.println("GameModel: MOSSA ESEGUITA: Tasto premuto in plancia in posizione "+posizione);

                movimentoDaPlancia(posizione, valoreDado, true);
                return true;
            }
        }
        //DEBUG ONLY
        System.out.println("GameModel: TASTO PREMUTO CON RETURN FALSE");
        return false;
    }

    /** Questa funzione effettua un return dei tasti che può premere il giocatore del turno corrente
     * @param valoreDado indica il valore del dado del giocatore corrente
     * @return Posizione[] un array dei tasti da abilitare sulla plancia
     */
    public Posizione[] getTastiAbilitati(int valoreDado){
        Posizione[] abilitati;
        abilitati = new Posizione[4];
        int lungArr=0;
        for(int i=0; i<4; i++){
            if(base[currentPlayerIndex][i].getColore()==currentPlayerIndex){
                if(movimentoDaBase(currentPlayerIndex, valoreDado, false)){
                    abilitati[lungArr] = new Posizione(Posizione.NomePosizione.Base, currentPlayerIndex, i);
                    lungArr++;
                }
            }
            if(finale[currentPlayerIndex][i].getColore()==currentPlayerIndex){
                if(movimentoDaFinale(i, currentPlayerIndex, valoreDado, false)){
                    abilitati[lungArr] = new Posizione(Posizione.NomePosizione.Fine, currentPlayerIndex, i);
                    lungArr++;
                }
            }
        }
        for(int i=0; i<40; i++){
            if(plancia[i].getColore()==currentPlayerIndex){
                if(movimentoDaPlancia(i, valoreDado, false)){
                    abilitati[lungArr] = new Posizione(Posizione.NomePosizione.Board, -1, i);
                    lungArr++;
                }
            }
        }
        return abilitati;
    }


	public Giocatore[] getPlayer() {
		return player;
	}

	@Override
	public String toString() {
		return "GameModel [plancia=" + Arrays.toString(plancia) + ", base=" + Arrays.toString(base) + ", finale="
				+ Arrays.toString(finale) + ", player=" + Arrays.toString(player) + ", currentPlayerIndex="
				+ currentPlayerIndex + ", numGiocatori=" + numGiocatori + "]";
	}
    
  /*  public void updateServer(Controllore c) {
        //DEBUG ONLY
        System.out.println("GameModel: è stato effettuato l'update sul server, classe Controllore");

    	c.setBase(base);
    	c.setFinale(finale);
    	c.setNumGiocatori(numGiocatori);
    	c.setPlancia(plancia);
    	c.setPlayer(player);
    }*/

}