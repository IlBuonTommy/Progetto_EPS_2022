package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProvaServer {

	
		private Socket client;
		private ServerSocket ss;
		//private  ExecutorService pool;
		private int numMaxGiocatori;
		public ProvaServer(int numMaxGiocatori) {
			this.numMaxGiocatori = numMaxGiocatori;
			try {
				ss = new ServerSocket(50358);
				//pool = Executors.newFixedThreadPool(numMaxGiocatori);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void accettaConnessioni() {
			int nGiocatoriConnessi=0;
			while(true) {
				if(nGiocatoriConnessi<numMaxGiocatori) {
					try {
						Socket client = ss.accept();
						System.out.println("connesso");
						ProvaHandler p = new ProvaHandler(client);
						Thread t = new Thread(p);
						t.run();
						System.out.println("NOME:"+ p.getNome());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}


}
