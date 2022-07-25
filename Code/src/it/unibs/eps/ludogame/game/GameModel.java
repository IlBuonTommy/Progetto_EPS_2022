package it.unibs.eps.ludogame.game;

import it.unibs.eps.ludogame.server.Controllore;

public class GameModel{
    //RED(0),BLUE(1),GREEN(2),YELLOW(3),EMPTY(-1)
    //3 array di caselle: plancia[40] base[colore][4] finale[colore][4]
    private Casella plancia[];
    private Casella base[][];
    private Casella finale[][];
    private Giocatore player[];
    int numGiocatori;

    public GameModel (int numGiocatori, Giocatore player[]){
        this.numGiocatori=numGiocatori;
        this.plancia = new Casella[40];
        this.base = new Casella[numGiocatori][4];
        this.finale = new Casella[numGiocatori][4];
        this.player = new Giocatore[numGiocatori];
        this.player = player;
        
        for(int i=0; i<numGiocatori; i++){
            for(int j=0; j<4; j++){
                this.base[i][j].setColore(i);
            }
        }
    }

    /**
     * Questo metodo serve per resettare il tabellone alle condizioni iniziali
     * NON I GIOCATORI O IL LORO NUMERO
     */
    public void reset(){
        this.plancia = new Casella[40];
        this.base = new Casella[numGiocatori][4];
        this.finale = new Casella[numGiocatori][4];
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
        int test=-1, cont=0;
        for(int i=0; i<numGiocatori; i++){
            for(int j=0; j<4; j++){
                if(finale[i][j].getColore()!=-1)
                    cont++;
            }
            if(cont == 4)
                return i;
            cont=0;
        }
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
                return true;
            }
        }
        return false;
    }

    
    /** Questo metodo valuta e/o esegue un movimento di una pedina che inizialmente si trova sulla plancia
     * @param posizione indica la posizione della pedina da muovere sulla plancia
     * @param valoreDado indica il valore del dado da considerare
     * @param daEseguire se messo a true il metodo effettua il movimento dopo aver valutato se fattibile
     * @return boolean se true, il movimento è fattibile, se false il movimento non si può fare
     */
    public boolean movimentoDaPlancia(int posizione, int valoreDado, boolean daEseguire){
        int nuovaPosizione = posizione + valoreDado;
        if(nuovaPosizione>39)
            nuovaPosizione = nuovaPosizione - 40;
        //controllo se ha pedina doppia davanti
        for(int i=1; i<=valoreDado; i++){
            if(plancia[posizione].getColore()==0){
                if(posizione <= 39 && posizione+i >= (10*plancia[posizione].getColore())){
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
                if(posizione <= (10*plancia[posizione].getColore())-1 && posizione+i >= (10*plancia[posizione].getColore())){
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
            if(posizione <= 39 && nuovaPosizione >= (10*plancia[posizione].getColore())){
                finaleT = true;
            }
        }else{
            if(posizione <= (10*plancia[posizione].getColore())-1 && nuovaPosizione >= (10*plancia[posizione].getColore())){
                finaleT = true;
            }
        }
        if(finaleT){
            int nuovaPosizioneT=nuovaPosizione;
            if(nuovaPosizione>3)
                nuovaPosizioneT=3;
            
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
            if(daEseguire){
                plancia[nuovaPosizione].setDoppio(true);
                if(plancia[posizione].getDoppio()){
                    plancia[posizione].setDoppio(false);
                }else{
                    plancia[posizione].setColore(-1);
                }
                return true;
            }
        }

        //effettua la mossa
        plancia[nuovaPosizione].setColore(-1);;
        if(plancia[posizione].getDoppio()){
            plancia[posizione].setDoppio(false);
        }else{
            plancia[posizione].setColore(-1);
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
    
    
    public void updateServer(Controllore c) {
    	c.setBase(base);
    	c.setFinale(finale);
    	c.setNumGiocatori(numGiocatori);
    	c.setPlancia(plancia);
    	c.setPlayer(player);
    }

}