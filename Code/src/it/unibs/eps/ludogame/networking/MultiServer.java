package it.unibs.eps.ludogame.networking;

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
public class MultiServer {
  ServerSocket server;
  ArrayList<Socket> listaSocket = new ArrayList<>();
  ArrayList<ServerThread> listaClient = new ArrayList<>();

  public int generaNumeroDado() {
    int dado = (int) (Math.random() * 6);
    return dado;
  }

  public void start(int porta) {
    try {
      server = new ServerSocket(porta);
      ExecutorService pool = Executors.newFixedThreadPool(4);
      for (;;) {
        System.out.println("1 Server in attesa ... ");
        Socket socket = server.accept();
        listaSocket.add(socket);
        System.out.println("3 Server socket  " + socket);
        // ServerThread nuovoClient = new ServerThread(socket);

        // nuovoClient.comunica();
        // pool.execute(new ServerThread(socket));
        for (Socket i : listaSocket) {
          listaClient.add(new ServerThread(i));
        }
        for (ServerThread s : listaClient) {
          s.comunica();
        }

      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Errore durante l'istanza del server !");
      System.exit(1);
    }
  }

  public static void main(String[] args) {
    MultiServer tcpServer = new MultiServer();
    tcpServer.start(6789);

  }
}
