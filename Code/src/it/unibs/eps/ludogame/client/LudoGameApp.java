package it.unibs.eps.ludogame.client;

import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;
import it.unibs.eps.ludogame.testnetworking.ClientGameLudo;
import it.unibs.eps.ludogame.testnetworking.ServerGameLudo;

public class LudoGameApp {

	public static void main(String[] args) {
		//DEBUG ONLY

		ServerGameLudo s = new ServerGameLudo();
		ClientGameLudo c = new ClientGameLudo();
		
		s.inizializzazione();
	//	c.connetti();
		
		
		
	}

}