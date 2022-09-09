package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

import it.unibs.eps.ludogame.client.ClientWaitingRoom;
import it.unibs.eps.ludogame.client.MainFrame;
import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.client.Sconfitta;
import it.unibs.eps.ludogame.client.Vittoria;
import it.unibs.eps.ludogame.game.Casella;
import it.unibs.eps.ludogame.game.GameModel;

/**
 * 
 * il client di gioco
 *
 */
public class ProvaClient {
	private String SERVER_IP;
	private static final int SERVER_PORT = 50358;
	private Socket clientSocket;
	private String playerName;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private GameModel modelClient = null;
	private Casella[] plancia;
	private Casella[][] base;
	private Casella[][] finale;
	private int playerIndex;
	private Posizione posizioneUtente;
	private int valoreDado;
	private ClientWaitingRoom clientFrame;
	private MainFrame framePrincipale;
	private int playerNumeroClient;
	private int i = 0;
	private int j = 0;
	private int iPlancia = 0;

	/**
	 * costruttore
	 * 
	 * @param serverIp
	 * @param playerName
	 * @param clientframe
	 */
	public ProvaClient(String serverIp, String playerName, ClientWaitingRoom clientframe) {
		this.SERVER_IP = serverIp;
		this.playerName = playerName;
		this.clientFrame = clientframe;
		this.plancia = new Casella[40];
		this.base = new Casella[4][4];
		this.finale = new Casella[4][4];
	}

	/**
	 * metodo che si connette
	 * 
	 * @return al controller della view se la connessione è andata a buon fine
	 */
	public boolean checkConnection() {
		System.out.println("[CLIENT]: in esecuzione.");

		try {
			clientSocket = new Socket(SERVER_IP, SERVER_PORT);

			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * richiedo al server il model iniziale
	 */
	public void richiestaModelIniziale() {
		Pacchetto p = new Pacchetto("model", null);
		try {
			out.writeObject(p);
			out.flush();
			modelClient = (GameModel) in.readObject();
			System.out.println("Model client ricevuto: " + modelClient.toString());
			base = modelClient.getBase();
			finale = modelClient.getFinale();
			plancia = modelClient.getPlancia();
			for (int i = 0; i < modelClient.getPlayer().length; i++) {
				if (this.playerName.equals(modelClient.getPlayer()[i].getUsername())) {
					this.playerNumeroClient = i;
				}
			}
			// Avvia view
			this.avviaMainFrame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Comunica al server il nome del giocatore, in modo che possa creare il model
	 */
	public synchronized void comunica() {

		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			out.flush();
			out.writeObject(playerName);
			out.flush();
			System.out.println("mandato");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * classe che si occupa di gestire tutta la comunicazione di dati da serve a
	 * client, implmenta un ciclo che legge il pacchetto in arrivo e in base della
	 * sua tipologia entra nell'if che lo gestisce
	 */
	public void comunicazioneInGameDaServer() {
		try {
			while (true) {
				Pacchetto p = (Pacchetto) in.readObject();
				String tipoRicevuto = p.getType();
				/**
				 * disabilita il tasto lancia
				 */
				if (tipoRicevuto.equals("disable")) {
					System.out.println("Client: disabilito il tasto lancia");
					framePrincipale.disableAllButtons();

				}
				/**
				 * esegue il repaint
				 */
				if (tipoRicevuto.equals("repaint")) {
					modelClient.setBase(base);
					modelClient.setPlancia(plancia);
					modelClient.setFinale(finale);
					modelClient.setCurrentPlayerIndex(playerIndex);

					framePrincipale.resetta(base, finale, plancia, playerIndex);

				}
				/**
				 * setta il turno
				 */
				if (tipoRicevuto.equals("setTurno")) {
					System.out.println("index: " + this.playerNumeroClient);
					framePrincipale.setTurno((int) p.getMessage());
					if ((int) p.getMessage() == this.playerNumeroClient) {
						framePrincipale.enableRoll();
					}
				}
				/**
				 * setto il valore del dado
				 */
				if (tipoRicevuto.equals("setDado")) {
					framePrincipale.setDado((int) p.getMessage());
				}
				/**
				 * setto il model
				 */
				if (tipoRicevuto.equals("setModel")) {
					modelClient = (GameModel) p.getMessage();
				}
				/**
				 * abilito i bottoni in base alla posizione ricevuta
				 */
				if (tipoRicevuto.equals("setPosizioni")) {
					framePrincipale.enableBoardButtons((Posizione[]) p.getMessage());

				}
				/**
				 * visualizza la schermata di vittoria o sconfitta
				 */
				if (tipoRicevuto.equals("setVincitore")) {
					int vincitore = (int) p.getMessage();
					framePrincipale.dispose();
					if (vincitore == this.playerIndex) {
						Vittoria vittoria = new Vittoria();
						vittoria.setVisible(true);
						vittoria.setLocationRelativeTo(null);
					} else {
						String nome = modelClient.getPlayer()[vincitore].getUsername();
						Sconfitta sconfitta = new Sconfitta(nome);
						sconfitta.setVisible(true);
						sconfitta.setLocationRelativeTo(null);
					}
				}
				/**
				 * ricevo l'array "base" del model
				 */
				if (tipoRicevuto.equals("setBase")) {
					base[i][j].setColore((int) p.getMessage());
					
					if (i == 3 && j == 3) {
						i = 0;
						j = 0;
					} else if (j == 3) {
						i++;
						j = 0;
					} else {
						j++;
					}
				}
				/**
				 * ricevo l'array finale del model
				 */
				if (tipoRicevuto.equals("setFinale")) {
					finale[i][j].setColore((int) p.getMessage());
					if (i == 3 && j == 3) {
						i = 0;
						j = 0;
					} else if (j == 3) {
						i++;
						j = 0;
					} else {
						j++;
					}
				}
				/**
				 * ricevo l'array plancia del model, nella sua componente colore
				 */
				if (tipoRicevuto.equals("setPlanciaColore")) {
					plancia[iPlancia].setColore((int) p.getMessage());
					if (iPlancia == 39) {
						iPlancia = 0;
					} else {
						iPlancia++;
					}
				}
				/**
				 * ricevo l'array plancia del model nella sua componente doppio
				 */
				if (tipoRicevuto.equals("setPlanciaDoppio")) {
					
					plancia[iPlancia].setDoppio((boolean) p.getMessage());
					if (iPlancia == 39) {
						iPlancia = 0;
					} else {
						iPlancia++;
					}
					

				}
				/**
				 * ricevo l'indice del giocatore corrente
				 */
				if (tipoRicevuto.equals("currentPlayer")) {

					
					playerIndex = (int) p.getMessage();
					

				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * metodo che manda il  valore del dado al server
	 * @param n
	 */
	public synchronized void sendDadoToServer(int n) {

		try {
			out.flush();
			out.writeObject(n);
			out.flush();
			System.out.println("mandato Dado");
			// richiestaModelIniziale();
			// Avviare View

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * metodo che avvia il frame principale del client
 */
	public void avviaMainFrame() {
		String[] listaGiocatori = new String[modelClient.getPlayer().length];

		for (int i = 0; i < listaGiocatori.length; i++) {
			listaGiocatori[i] = modelClient.getPlayer()[i].getUsername();
		}

		framePrincipale = new MainFrame(listaGiocatori, this, null);
		framePrincipale.setVisible(true);
		framePrincipale.setLocationRelativeTo(null);
		comunicazioneInGameDaServer();
		clientFrame.closeFrame();

	}
/**
 * metodo che manda la posizione al server
 * @param p
 */
	public void sendPositionToServer(Posizione p) {
		// TODO Auto-generated method stub

		try {
			out.flush();
			out.writeObject(p);
			out.flush();
			System.out.println("mandato posizione");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
