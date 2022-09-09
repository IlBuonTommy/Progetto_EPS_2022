package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unibs.eps.ludogame.client.HostWaitingRoom;
import it.unibs.eps.ludogame.client.MainFrame;
import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.client.Sconfitta;
import it.unibs.eps.ludogame.client.Vittoria;
import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.GestionePartita;
import it.unibs.eps.ludogame.game.Giocatore;
/**
 * server di gioco
 * 
 *
 */
public class ProvaServer {

	private Socket client;
	private static final int PORT = 50358;
	private ServerSocket listener;
	private ExecutorService pool;
	private int numMaxGiocatori;
	private HostWaitingRoom frameWaiting;
	private Giocatore[] listaGiocatori;
	private ArrayList<ProvaHandler> clients = new ArrayList<>();
	private String[] listaBot = { "giovanninoBot", "antoniettaBot", "rinaldoBot" };
	private ProvaHandler clientHandler;
	private boolean partitaAvviata = false;
	private String nomeServer;
	private GameModel serverModel;
	private MainFrame framePrincipale;
	private int nGiocatoriConnessi = 0;
	private GestionePartita gestione;
/**
 * costruttore
 * @param numMaxGiocatori
 * @param frameWaiting
 * @param nomeServer
 */
	public ProvaServer(int numMaxGiocatori, HostWaitingRoom frameWaiting, String nomeServer) {
		this.numMaxGiocatori = numMaxGiocatori;
		this.frameWaiting = frameWaiting;
		this.nomeServer = nomeServer;
		this.listaGiocatori = new Giocatore[numMaxGiocatori];
		this.listaGiocatori[0] = new Giocatore(0, nomeServer, false);
		try {
			listener = new ServerSocket(PORT);
			pool = Executors.newFixedThreadPool(numMaxGiocatori);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frameWaiting.setS(this);
	}

	public boolean isPartitaAvviata() {
		return partitaAvviata;
	}

	public void setPartitaAvviata(boolean partitaAvviata) {
		this.partitaAvviata = partitaAvviata;
	}
/**
 * metodo che accetta le connessioni di nuovi client, verificando che ci sia ancora posto nella lobby
 * crea l'oggetto clientHandler e lo aggiunge alla pool di esecuzione
 * @return false quando deve iniziare la partita perchè è stato raggiunto il numero massimo di giocatori
 */
	public synchronized boolean accettaConnessioni() {
		if (nGiocatoriConnessi < numMaxGiocatori && partitaAvviata == false) {
			System.out.println("[SERVER]: waiting for client connection");
			try {
				client = listener.accept();
				System.out.println("accettato");
				clientHandler = new ProvaHandler(client, this);
				
				clients.add(clientHandler);
				
				pool.execute(clientHandler);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nGiocatoriConnessi++;
			return true;
		} else {
			System.out.println("Numero massimo di giocatori raggiunto.Inizia la partita");
			return false;

		}
	}
	
	/**
	 * invio il model iniziale a tutti i client e avvio il metodo di comunicazione
	 */
	public void allClientSendModel() {
		for (ProvaHandler c : clients) {
			c.setThreadModel(serverModel);
			c.comunicazione();

		}
	}
/**
 * verifico chi ha vinto e attivo i metodi che informano i client, per poter mostrare le relative schermate
 * @param vincitore
 */
	public void setWinner(int vincitore) {

		for (ProvaHandler c : clients) {
			c.setVincitore(vincitore);
		}

		framePrincipale.dispose();
		if (vincitore == 0) {
			Vittoria vittoria = new Vittoria();
			vittoria.setVisible(true);
			vittoria.setLocationRelativeTo(null);
		} else {
			String nome = serverModel.getPlayer()[vincitore].getUsername();
			Sconfitta sconfitta = new Sconfitta(nome);
			sconfitta.setVisible(true);
			sconfitta.setLocationRelativeTo(null);
		}

	}
/**
 * chiamo le funzioni dell'handler che inviano i parametri del model aggiornati a tutti i client
 */
	public void sendModelInGame() {
		framePrincipale.resetta(serverModel.getBase(), serverModel.getFinale(), serverModel.getPlancia(),serverModel.getCurrentPlayerIndex());

		for (ProvaHandler c : clients) {
			c.setBase(serverModel.getBase());
			c.setFinale(serverModel.getFinale());
			c.setPlanciaColore(serverModel.getPlancia());
			c.setPlanciaDoppio(serverModel.getPlancia());
			c.setCurrentPlayer(serverModel.getCurrentPlayerIndex());
			c.resettaFrame();
		}
		if (serverModel.getCurrentPlayerIndex() == 0) {
			framePrincipale.enableRoll();
		}else {
			framePrincipale.disableAllButtons();
		}
		
	}
/**
 * chiamo le funzioni dell'handler che inviano il dado ai client
 * @param dado
 */
	public void sendDado(int dado) {
		framePrincipale.setDado(dado);
		for (ProvaHandler c : clients) {
			c.setDado(dado);
		}
	}
/**
 * passo alla seconda fase di gioco
 * @param dado
 */
	public void starTurnoDue(int dado) {
		System.out.println("ricevuto dado sever");
		gestione.gestioneTurnoDue(dado);
	}
/**
 * chiamo le funzioni dell'handler che inviano le posizioni ai client
 * @param dado
 */
	public void sendMovePosition(int dado) {
		Posizione[] listapos = serverModel.getTastiAbilitati(dado);

		if (listapos[0] != null) {
			int currentPlayer = serverModel.getCurrentPlayerIndex();
			if (currentPlayer == 0) {
				framePrincipale.enableBoardButtons(listapos);
			} else {
				clients.get(currentPlayer - 1).sendListaPos(listapos);
			}
		} else {
			gestione.gestioneTurnoQuattro();
		}

	}
/**
 * passo alla terza fase di gioco
 * @param p
 */
	public void startTurnoTre(Posizione p) {
		gestione.gestioneTurnoTre(p);
	}
/**
 * chiamo le funzioni dell'handler che inviano il comando di disabilitare i tasti
 */
	public void disableAllButton() {
		int currentPlayer = serverModel.getCurrentPlayerIndex();
		if (currentPlayer == 0) {
			framePrincipale.disableAllButtons();
		} else {
			clients.get(currentPlayer - 1).disabilitaTasti();
		}
	}
/**
 * avvio la prima fase di gioco
 */
	public void gestioneTurnoIniziale() {
		// creo il model con i dati dei player che mi hanno passato le view
		// il model viene inviato a tutti i client
		serverModel = new GameModel(numMaxGiocatori, listaGiocatori);
		allClientSendModel();
		for (ProvaHandler c : clients) {
			c.disabilitaTasti();
			c.resettaFrame();
		}
		gestione = new GestionePartita(this);
		gestione.gestioneTurnoUno();

	}

	/**
	 * ricevo il dado
	 * @param dado
	 */
	public void receiveDado(int dado) {
		gestione.gestioneTurnoDue(dado);
	}
/**
 * mi metto in attesa del dado dei giocatori
 */
	public void startWaitingDado() {
		System.out.println("ENTRA IN WAITING DADO da Server");
		int currentPlayer = serverModel.getCurrentPlayerIndex();
		if (currentPlayer != 0) {
			clients.get(currentPlayer - 1).receiveDadoFromClient();
		}
	}
/**
 * chiamo le funzioni dell'handler che inviano il turno ai client
 */
	public void settaTurnoClient() {
		int currentPlayer = serverModel.getCurrentPlayerIndex();
		System.out.println("INDEX SERVER:" + currentPlayer);
		for (ProvaHandler c : clients) {
			c.setTurno(currentPlayer);
		}
		// framePrincipale.setTurno(currentPlayer);
	}
/**
 * inizio del gioco
 */
	public void inizioGame() {
		framePrincipale.disableAllButtons();
		framePrincipale.resetta(serverModel.getBase(), serverModel.getFinale(), serverModel.getPlancia(), 0);
		framePrincipale.enableRoll();
	}

	public GameModel getServerModel() {
		return serverModel;
	}
/**
 * avvio il server
 */
	public synchronized void avvia() {
		System.out.println("[SERVER]: avviato correttamente.");
		// createModel();
		int n = 1;
		while (true) {
		
			if (accettaConnessioni()) {
				System.out.println("[SERVER]: new client connected");
				try {
					wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listaGiocatori[n] = new Giocatore(n, clients.get(clients.size() - 1).getNome(), false);
				System.out.println("NOMELISTA:" + listaGiocatori[n].getUsername());
				frameWaiting.addPlayer(listaGiocatori[n].getUsername());

				n++;
			} else {
				if (n == numMaxGiocatori) {
					// gestioneTurnoIniziale();
					break;
				}
			}
		} // fine ciclo
		

	}
/**
 * creo il frame principale del server
 */
	public void creaMainFrame() {
		// TODO Auto-generated method stub
		String[] listaGiocatori = new String[serverModel.getPlayer().length];

		for (int i = 0; i < listaGiocatori.length; i++) {
			listaGiocatori[i] = serverModel.getPlayer()[i].getUsername();
		}
		framePrincipale = new MainFrame(listaGiocatori, null, this);
		framePrincipale.setVisible(true);
		framePrincipale.setLocationRelativeTo(null);

	}
/**
 * genero il bot in base ai giocatori mancanti
 */
	public void creaBot() {
		// TODO Auto-generated method stub
		if (nGiocatoriConnessi < numMaxGiocatori) {
			for (int i = nGiocatoriConnessi + 1; i < (numMaxGiocatori); i++) {
				listaGiocatori[i] = new Giocatore(i, listaBot[numMaxGiocatori - i - 1], true);

			}
		}
	}
/**
 * ricevo la posizione
 * @param pos
 */
	public void receivePosition(Posizione pos) {
		// TODO Auto-generated method stub
		gestione.gestioneTurnoTre(pos);
	}
/**
 * mi metto in ascolto delle posizioni del client
 */
	public void startWaitingPosition() {
		int currentPlayer = serverModel.getCurrentPlayerIndex();
		if (currentPlayer != 0) {
			clients.get(currentPlayer - 1).receivePosizioneFromClient();
		}
	}

}
