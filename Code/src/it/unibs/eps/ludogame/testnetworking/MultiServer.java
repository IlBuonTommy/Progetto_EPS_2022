package it.unibs.eps.ludogame.testnetworking;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//TODO:
/*
* Update broadcast array 
* Gestione turno
* Generazione random dado
* Verifica vincita a fine turno e termina connessione --> FATTO
* */
public class MultiServer{
	ServerSocket server;
	ArrayList<Socket> listaSocket = new ArrayList<>();
	
	
  public void start(int porta){ 
    try{
    	server = new ServerSocket(porta);
    	ExecutorService  pool = Executors.newFixedThreadPool(4);
      for (;;) 
      { 
        System.out.println("1 Server in attesa ... "); 
        Socket socket = server.accept();
        listaSocket.add(socket);
        System.out.println("3 Server socket  " + socket);
        pool.execute(new ServerThread(socket));
  
      } 
    }
    catch (Exception e){
      System.out.println(e.getMessage());
      System.out.println("Errore durante l'istanza del server !");
      System.exit(1);
    }
  } 

  public static void main (String[] args){ 
     MultiServer tcpServer = new MultiServer(); 
     tcpServer.start(6789); 
   } 
}
