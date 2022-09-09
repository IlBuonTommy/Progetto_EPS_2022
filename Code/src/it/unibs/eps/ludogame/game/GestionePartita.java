package it.unibs.eps.ludogame.game;

import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.networking.ProvaServer;
/**
 * 
 * classe che si occupa di gestire la partita
 *
 */
public class GestionePartita {
	private ProvaServer server;
	private int valoreDadoS;
	
	public GestionePartita(ProvaServer server) {
		this.server = server;
		
		
	}
	/**
	 * prima parte, gestione movimento del bot, oppure abilito il bottone di lancio del giocatore corrente
	 */
	public synchronized void gestioneTurnoUno(){
		System.out.println("turno 1, indice: " + server.getServerModel().getCurrentPlayerIndex());
		
		if(server.getServerModel().currentIsBot()){
			//genero numero random da 1 a 6 e lo salvo su valoreDadoS
			valoreDadoS = (int)Math.floor(Math.random()*(6)+1);
			
			server.sendDado(valoreDadoS);
			
			//faccio fare il movimento al bot
			server.getServerModel().movimentoBOT(server.getServerModel().getCurrentPlayerIndex(), valoreDadoS);
			//aggiorno il model dei vari client e visualizzo quello nuovo
			server.sendModelInGame();
			gestioneTurnoQuattro();
		}else{
			//abilito il bottone di lancio dado del giocatore corrente
			server.settaTurnoClient();
			server.startWaitingDado();
		}
	}
	/**
	 * mando a tutti i client il valore del dado e mando alla view i bottoni che si possono abilitare sulla base del dado,attendo la mossa
	 * @param valoreDado
	 */
	public void gestioneTurnoDue(int valoreDado){
		System.out.println("turno 2");
		valoreDadoS = valoreDado;
		//mando a tutti i client il valore del dado
		server.sendDado(valoreDado);
		//mando alla view i bottoni che può abilitare QUESTO PASSAGGIO PUò ESSERE FATTO IN AUTONOMIA DAL PROGRAMMA DEL GIOCATORE
		server.sendMovePosition(valoreDadoS);
		server.startWaitingPosition();
	}
	/**
	 * il giocatore preme, richiamando uesta funzione che modifica il model sulla base del tasto premuto
	 * @param tastoPremuto
	 */
	public void gestioneTurnoTre(Posizione tastoPremuto){
		System.out.println("turno 3");
		
		//modifico il model in base al tasto premuto del giocatore
		if(!server.getServerModel().tastoPremuto(tastoPremuto.getNomeposizione(),tastoPremuto.getArrayposizione(), tastoPremuto.getColor(), valoreDadoS)){
			gestioneTurnoDue(valoreDadoS);
			return;
		}
		//disabilito tutti i tasti al giocatore corrente
		server.disableAllButton();
		//invio a tutti i giocatori il nuovo model e lo visualizzo
		
		server.sendModelInGame();
		gestioneTurnoQuattro();
	}
	/**
	 * controllo se un giocatore ha vinto, nel caso il gioco termina con la visualizzazione dei frame di vittoria/sconfitta, altrimenti ricomincia il ciclo del turno
	 */
	public  void gestioneTurnoQuattro(){
		System.out.println("turno 4");
		int vincitore=server.getServerModel().checkWin();
		if(vincitore!=-1){
			//il gioco finisce chiudiamo le connessioni e tutti a baita
			server.setWinner(vincitore);
			System.out.println("Gioco finito.");

			return;
		}
		if(valoreDadoS==6){
			gestioneTurnoUno();
			return;
		}
		server.getServerModel().nextTurn();
		server.sendModelInGame();			
		
		gestioneTurnoUno();
		
		
			
			
	}
	
}
