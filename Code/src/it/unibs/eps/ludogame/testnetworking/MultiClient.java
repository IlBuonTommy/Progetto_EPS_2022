package it.unibs.eps.ludogame.testnetworking;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.HashMap;

public class MultiClient {
  String nomeServer ="localhost";                  // indirizzo server locale  
  int portaServer   = 6789;                        // porta x servizio data e ora
  Socket client;                                
  BufferedReader tastiera;                         // buffer per l'input da tastiera
  String stringaUtente;                            // stringa inserita da utente
  String stringaRicevutaDalServer;                 // stringa ricevuta dal server
  PrintWriter outVersoServer;                 // stream di output
  BufferedReader inDalServer;                      // stream di input 
  boolean isRunning = false;
  int[] provaLista = {1,2,3,4};
  
  public MultiClient() {
	  
  }
  
  public synchronized void sendMsg(MultiClient sender, String msg) {
	  if(outVersoServer != null) {
			System.out.printf("invio messaggio: %s\n", msg);
			outVersoServer.println(msg);
			outVersoServer.flush();
	  }
  }
  
  
  public void comunica() {
   for (;;)                                     // ciclo infinito: termina con FINE
    try{
      System.out.println("4 ... utente, inserisci la stringa da trasmettere al server:");
      //stringaUtente = Arrays.toString(provaLista);
      stringaUtente = tastiera.readLine();
      //la spedisco al server 
      System.out.println("5 ... invio la stringa al server e attendo ...");
      outVersoServer.write( stringaUtente+'\n');
      //leggo la risposta dal server 
      stringaRicevutaDalServer=inDalServer.readLine();
      System.out.println("7 ... risposta dal server "+'\n'+stringaRicevutaDalServer );
      if  (stringaRicevutaDalServer.equals("termina")) { 
        System.out.println("-->Termine sessione di gioco..." );
        client.close();                             // chiudo la connessione
        break; 
      }
    } 
    catch (Exception e) 
    {
      System.out.println(e.getMessage());
      System.out.println("Errore durante la comunicazione col server!");
      System.exit(1);
    }
  }
  
 /* public Socket connetti(){
    System.out.println("2 CLIENT partito in esecuzione ...");
    try{
      // input da tastiera
      tastiera = new BufferedReader(new InputStreamReader(System.in));
      //  miosocket = new Socket(InetAddress.getLocalHost(), 6789);
      client = new Socket(nomeServer,portaServer);
      // associo due oggetti al socket per effettuare la scrittura e la lettura 
      outVersoServer = new DataOutputStream(client.getOutputStream());
      inDalServer    = new BufferedReader(new InputStreamReader (client.getInputStream()));
    } 
    catch (UnknownHostException e){
      System.err.println("Host sconosciuto"); } 
    catch (Exception e){
      System.out.println(e.getMessage());
      System.out.println("Errore durante la connessione!");
      System.exit(1);
    }
    return client;
  }*/
  
  public synchronized void close() {
	  try {
			isRunning = false;
			outVersoServer.close();
			inDalServer.close();
			client.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			outVersoServer = null;
			inDalServer = null;
			client = null;
		}
  }

  /*public static void main(String args[]) {
    MultiClient cliente = new MultiClient();
    cliente.connetti();
    cliente.comunica();
  }   */
}


