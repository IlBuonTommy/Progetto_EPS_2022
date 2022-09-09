package it.unibs.eps.ludogame.game;

import java.io.Serializable;
/**
 * 
 * classe che implementa le caratteristiche della casella
 *
 */
public class Casella implements Serializable{
    private int colore;
    private boolean doppio;

    public Casella() {
        this.colore = -1;
        this.doppio = false;
    }

    public int getColore() {
        return this.colore;
    }

    public void setColore(int colore) {
        this.colore = colore;
    }

    public boolean getDoppio() {
        return this.doppio;
    }

    public void setDoppio(boolean doppio) {
        this.doppio = doppio;
    }

	@Override
	public String toString() {
		return "Casella [colore=" + colore + ", doppio=" + doppio + "]";
	}
    
    
}