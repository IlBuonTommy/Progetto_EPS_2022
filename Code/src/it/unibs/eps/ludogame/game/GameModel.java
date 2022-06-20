package it.unibs.eps.ludogame.game;

public class GameModel {
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
}