package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unibs.eps.ludogame.game.Giocatore;

public class ServerGameLudo {
	private static final int PORT = 50358;
	private static ArrayList<ClientHandler> clients = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(3);
	private static int numMaxGiocatori = 2;
	private static boolean partitaAvviata;
	private static ClientHandler clientThread;
	private static void start() {
		int playerNumber = 0;
		try {
			ServerSocket listener = new ServerSocket(PORT);
			System.out.println("[SERVER]: avviato correttamente.");
			
			while(true) {
				
				if (playerNumber < numMaxGiocatori - 1 && partitaAvviata == false) {
					System.out.println("[SERVER]: waiting for client connection");
					Socket client = listener.accept();
					System.out.println("[SERVER]: new client connected");
					clientThread = new ClientHandler(client,clients,numMaxGiocatori);
					clients.add(clientThread);
					pool.execute(clientThread);
					playerNumber++;
				}else {
					System.out.println("Numero massimo di giocatori raggiunto.Inizia la partita");
					break;
					
				}
			}

		} catch(IOException e) {
			System.err.println("Exception from the server");
			System.err.println(e.getMessage());
		}
	}
	
public static void main(String[] args) {
	start();
}
}
