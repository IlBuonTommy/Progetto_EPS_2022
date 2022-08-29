package it.unibs.eps.ludogame.server;

import java.io.Serializable;
import java.util.Arrays;

import it.unibs.eps.ludogame.game.Casella;
import it.unibs.eps.ludogame.game.Giocatore;

public class Controllore implements Serializable{
	
    private Casella plancia[];
    private Casella base[][];
    private Casella finale[][];
    private Giocatore player[];
    int numGiocatori;
	
	public void mandaBenvenuto() {
		// TODO Auto-generated method stub
		System.out.println("CIAO");
	/*	for(int i=0;i<player.length;i++) {
			System.out.println("CIAO " + player[i].getUsername());
		}*/
	}

	public Casella[] getPlancia() {
		return plancia;
	}

	public void setPlancia(Casella[] plancia) {
		this.plancia = plancia;
	}

	public Casella[][] getBase() {
		return base;
	}

	public void setBase(Casella[][] base) {
		this.base = base;
	}

	public Casella[][] getFinale() {
		return finale;
	}

	public void setFinale(Casella[][] finale) {
		this.finale = finale;
	}

	public Giocatore[] getPlayer() {
		return player;
	}

	public void setPlayer(Giocatore[] player) {
		this.player = player;
	}

	public int getNumGiocatori() {
		return numGiocatori;
	}

	public void setNumGiocatori(int numGiocatori) {
		this.numGiocatori = numGiocatori;
	}

	@Override
	public String toString() {
		return "Controllore [plancia=" + Arrays.toString(plancia) + ", base=" + Arrays.toString(base) + ", finale="
				+ Arrays.toString(finale) + ", player=" + Arrays.toString(player) + ", numGiocatori=" + numGiocatori
				+ "]";
	}

	
	
	
	

}
