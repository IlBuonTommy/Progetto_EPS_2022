package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unibs.eps.ludogame.client.HostWaitingRoom;

public class ProvaServer {

	
		private Socket client;
		private ServerSocket ss;
		private  ExecutorService pool;
		private int numMaxGiocatori;
		HostWaitingRoom frameWaiting;
		public ProvaServer(int numMaxGiocatori, HostWaitingRoom frameWaiting) {
			this.numMaxGiocatori = numMaxGiocatori;
			this.frameWaiting = frameWaiting;
			try {
				ss = new ServerSocket(50358);
				pool = Executors.newFixedThreadPool(numMaxGiocatori);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public synchronized void accettaConnessioni() {
			int nGiocatoriConnessi=0;

				if(nGiocatoriConnessi<numMaxGiocatori-1) {
					try {
						Socket client = ss.accept();
						
						System.out.println("connesso");
						ProvaHandler p = new ProvaHandler(client);
						pool.execute(p);
						wait(1000);
						//Thread t = new Thread(p);
						//t.run();
						System.out.println("NOME:"+ p.getNome());
						frameWaiting.addPlayer(p.getNome());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		}


}
