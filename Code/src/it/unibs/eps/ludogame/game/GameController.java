package it.unibs.eps.ludogame.game;

import it.unibs.eps.ludogame.game.*;

//TODO: gestione gioco
public interface GameController {
	
	//Genera un nuovo giocatore, gli assegna un colore e controlla se ci sono gi� 4 giocatori
	public PlayerColor register(PlayerColor requestedColor, String name, String clientName, Float clientVersion);
	
	//Rimuove giocatore
	public void remove(PlayerColor color);
	
	//Salva stato client
	public Boolean ready(PlayerColor color, Boolean isReady);
	
	//Ritorna stato del gioco
	public GameState getState();
	
	//Ritorna il giocatore corrente
	public PlayerColor currentPlayer();
	
	//ritorna true se il giocatore corrente � un bot
	public Boolean currentPlayerIsBot();

	//esegue il turno del bot nel tempo indicato
	public void botTurn(long millis);
	
}
