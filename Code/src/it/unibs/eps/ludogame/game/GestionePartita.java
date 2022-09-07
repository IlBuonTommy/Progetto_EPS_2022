package it.unibs.eps.ludogame.game;

import java.io.IOException;

import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.networking.ProvaServer;

public class GestionePartita {
	private ProvaServer server;
	private int valoreDadoS;
	
	public GestionePartita(ProvaServer server) {
		this.server = server;
	}
	
	public void gestioneTurnoUno(){
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
			
		}
	}
	//questa funzione è richiamata dal tasto lancia dado 
	public void gestioneTurnoDue(int valoreDado){
		valoreDadoS = valoreDado;
		//mando a tutti i client il valore del dado
		server.sendDado(valoreDado);
		//mando alla view i bottoni che può abilitare QUESTO PASSAGGIO PUò ESSERE FATTO IN AUTONOMIA DAL PROGRAMMA DEL GIOCATORE
		server.sendMovePosition(valoreDadoS);
	}
	//questa funzione viene richiamata dalla pressione del bottone da parte di un giocatore
	public void gestioneTurnoTre(Posizione tastoPremuto){
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
	public void gestioneTurnoQuattro(){
		if(server.getServerModel().checkWin()!=-1){
			//il gioco finisce chiudiamo le connessioni e tutti a baita
			//System.out.println("Partita terminata, Complimenti ha vinto il giocatore: "+ model.getPlayer()[model.checkWin()].getUsername());
			System.out.println("GIOCO FINITO GAEE");
			/*try {
				//client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			return;
		}
		if(valoreDadoS==6){
			gestioneTurnoUno();
			return;
		}
		server.getServerModel().nextTurn();
		gestioneTurnoUno();
	}
	
}
