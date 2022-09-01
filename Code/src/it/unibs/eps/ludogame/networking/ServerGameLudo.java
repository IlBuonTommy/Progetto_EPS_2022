package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;

public class ServerGameLudo {
	private ServerSocket serverSocket;
	private static Integer port = 50358;
	private int numMaxGiocatori = 2;
	private String nomeGiocatore = "alessio";
	private boolean partitaAvviata;
	private GameModel model;

	private ArrayList<ServerThread> listaClient = new ArrayList<>();

	public void inizializzazione() {
		// fare sparire finestra cosa avviare
		// fare apparire finestra server

		// chiudere avvio server
		// aprire finestra dell'ip
		start(port);

	}

	public void generateModel() {
		Giocatore[] listaGiocatori = new Giocatore[numMaxGiocatori];

		listaGiocatori[0] = new Giocatore(0, "Alessio", false);
		listaGiocatori[1] = new Giocatore(1, "Paolo", false);
		// listaGiocatori[2] = new Giocatore(2,"Tommy",false);

		/*
		 * for(int i=0;i<numMaxGiocatori-1;i++) { listaGiocatori[i] = new
		 * Giocatore(i,listaClient.get(i).getNomeGiocatore(),false);
		 * System.out.println(listaClient.get(i).getNomeGiocatore()); }
		 */
		model = new GameModel(numMaxGiocatori, listaGiocatori);
	}

	private void start(Integer port) {
		int num = 0;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server avviato correttamente...");
			ExecutorService esecutore = Executors.newCachedThreadPool();
			// Controllo giocatori
			while (true) {
				if (num < numMaxGiocatori - 1 && partitaAvviata == false) {
					ServerThread s = new ServerThread(serverSocket.accept());
					listaClient.add(s);
					//generateModel();
					//s.serverModel = model;
					num++;
				} else {
					System.out.println("max giocatori raggiunto");
					break;

				}
			}
			generateModel();
			for (ServerThread s : listaClient) {
				s.serverModel = model;
				esecutore.execute(s);
			}
			
			// Aggiornamento dei model di tutti in broadcast
			while (true) {
				for (ServerThread s : listaClient) {
					if (s.isNeedUpdate()) {
						model = s.serverModel;

					}
				}
				for (ServerThread s : listaClient) {
					s.serverModel = model;
				}
			}

		} catch (IOException ex) {
			System.out.println("eccezione del server--> " + ex.getMessage());
		}

	}

}
