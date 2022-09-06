package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unibs.eps.ludogame.client.HostWaitingRoom;
import it.unibs.eps.ludogame.game.Giocatore;

public class ProvaServer {

	
		private Socket client;
		private static final int PORT = 50358;
		private ServerSocket listener;
		private  ExecutorService pool;
		private int numMaxGiocatori;
		private HostWaitingRoom frameWaiting;
		private  Giocatore[] listaGiocatori;
		private  ArrayList<ProvaHandler> clients = new ArrayList<>();
		private String[] listaBot = {"giovanninoBot","antoniettaBot","rinaldoBot"};
		private ProvaHandler clientHandler;
		private boolean partitaAvviata;
		private String nomeServer;
		public ProvaServer(int numMaxGiocatori, HostWaitingRoom frameWaiting, String nomeServer) {
			this.numMaxGiocatori = numMaxGiocatori;
			this.frameWaiting = frameWaiting;
			this.nomeServer = nomeServer;
			this.listaGiocatori = new Giocatore[numMaxGiocatori];
			this.listaGiocatori[0] = new Giocatore(0,nomeServer,false);
			try {
				listener = new ServerSocket(PORT);
				pool = Executors.newFixedThreadPool(numMaxGiocatori);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public synchronized boolean accettaConnessioni() {
			int nGiocatoriConnessi=0;

			if (nGiocatoriConnessi < numMaxGiocatori && partitaAvviata == false) {
				System.out.println("[SERVER]: waiting for client connection");
				try {
					client = listener.accept();
					System.out.println("accettato");
					clientHandler = new ProvaHandler(client);
					System.out.println("handler");
					clients.add(clientHandler);
					System.out.println("add");
					pool.execute(clientHandler);
					System.out.println("arrivato qui");
					//DEBUG ONLY
					partitaAvviata = true;
					//connectedClients.add(client);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			//	clientThread = new ClientHandler(client,clients,modelBase,modelBase.getPlayer()[playerNumber].getUsername());
			//	clients.add(clientThread);
				
				nGiocatoriConnessi++;
				return true;
			}else {
				System.out.println("Numero massimo di giocatori raggiunto.Inizia la partita");
				return false;
				
			}
		}
			/*	if(nGiocatoriConnessi<numMaxGiocatori-1) {
					try {
						Socket client = listener.accept();
						
						System.out.println("connesso");
						ProvaHandler p = new ProvaHandler(client);
						pool.execute(p);
						wait(1000);
						//Thread t = new Thread(p);
						//t.run();
						System.out.println("NOME:"+ p.getNome());
						frameWaiting.addPlayer(p.getNome());
						return true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					System.out.println("Numero massimo di giocatori raggiunto.Inizia la partita");
					return false;
					
				}
			*/

		
		public synchronized void avvia() {
			int playerNumber = 0;

				System.out.println("[SERVER]: avviato correttamente.");
				//createModel();
				int n=1;
				while(true) {
					System.out.println("ci sono");
					if(accettaConnessioni()) {
						System.out.println("[SERVER]: new client connected");
						try {
							wait(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						listaGiocatori[n] = new Giocatore(n,clients.get(clients.size()-1).getNome(),false);
						System.out.println("NOMELISTA:"+ listaGiocatori[n].getUsername());
						frameWaiting.addPlayer(listaGiocatori[n].getUsername());
						
						n++;
					}else {
						if(n == numMaxGiocatori) {
							break;
						}else {
							for(int i=n;i<(numMaxGiocatori);i++) {
								listaGiocatori[i] = new Giocatore(i,listaBot[numMaxGiocatori-i],true);
								
							}
							System.out.println(Arrays.toString(listaGiocatori));
							break;
						}
						
					}
				}
			//	System.out.println(listaGiocatori);
				/*for(ClientHandler c : clients) {
					pool.execute(c);
				}*/
				//funzioni che prendono il nome

			
		}


}
