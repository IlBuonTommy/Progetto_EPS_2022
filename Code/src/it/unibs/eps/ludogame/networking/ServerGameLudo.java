package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unibs.eps.ludogame.game.GameModel;
import it.unibs.eps.ludogame.game.Giocatore;
import it.unibs.eps.ludogame.game.PlayerColor;

public class ServerGameLudo {
	private static final int PORT = 50358;
	private  ArrayList<ClientHandler> clients = new ArrayList<>();
	private  ArrayList<Socket> connectedClients = new ArrayList<>();
	private String[] listaBot = {"giovanninoBot","antoniettaBot","rinaldoBot"};
	private  int numMaxGiocatori;
	private  ExecutorService pool;
	private  boolean partitaAvviata;
	private  ClientHandler clientThread;
	private  Giocatore[] listaGiocatori;
	private  GameModel modelBase;
	private  ServerSocket listener;

	public ServerGameLudo(int numGiocatori) {
		this.numMaxGiocatori = numGiocatori;
		this.listaGiocatori = new Giocatore[numMaxGiocatori];
		System.out.println("sono nel server");
	}
	
	private  void createModel() {
		for(int i=0;i<numMaxGiocatori;i++) {
			listaGiocatori[i] = new Giocatore(i,PlayerColor.valueOf(i).name(),false);
		}
		modelBase = new GameModel(numMaxGiocatori,listaGiocatori);
		
	}
	public boolean acceptConnection() {
		int playerNumber=0;
		pool = Executors.newFixedThreadPool(numMaxGiocatori);
		return false;
	/*	if (playerNumber < numMaxGiocatori && partitaAvviata == false) {
			System.out.println("[SERVER]: waiting for client connection");
			Socket client;
			try {
				client = listener.accept();
				clientThread = new ClientHandler(client,clients);
				clients.add(clientThread);
				pool.execute(clientThread);
				//connectedClients.add(client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//	clientThread = new ClientHandler(client,clients,modelBase,modelBase.getPlayer()[playerNumber].getUsername());
		//	clients.add(clientThread);
			
			playerNumber++;
			return false;
		}else {
			System.out.println("Numero massimo di giocatori raggiunto.Inizia la partita");
			return true;
			
		}*/
	}
	
	public void start() {
		int playerNumber = 0;
		try {
			listener = new ServerSocket(PORT);
			System.out.println("[SERVER]: avviato correttamente.");
			//createModel();
			int n=0;
			while(true) {
				System.out.println("hinj");
				if(acceptConnection()) {
					System.out.println("[SERVER]: new client connected");
					listaGiocatori[n] = new Giocatore(n,clients.get(clients.size()-1).getNomeGiocatore(),false);
					System.out.println(listaGiocatori[n].getUsername());
					n++;
				}else {
					if(n == numMaxGiocatori) {
						break;
					}else {
						for(int i=n;i<(numMaxGiocatori);i++) {
							listaGiocatori[i] = new Giocatore(i,listaBot[numMaxGiocatori-i],true);
							
						}
					}
					
				}
			}
		//	System.out.println(listaGiocatori);
			/*for(ClientHandler c : clients) {
				pool.execute(c);
			}*/
			//funzioni che prendono il nome

		} catch(IOException e) {
			System.err.println("Exception from the server");
			System.err.println(e.getMessage());
		}
	}

}
