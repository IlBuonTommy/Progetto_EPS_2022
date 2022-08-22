package it.unibs.eps.ludogame.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.unibs.eps.ludogame.game.Casella;
import it.unibs.eps.ludogame.server.Controllore;

public class TestClient {

	 public static void main(String[] arg) {
		 Casella cas = new Casella();
		 Casella c [] = {cas};
	      try {
	    	  Controllore control = new Controllore();
	    	  Socket socketConnection = new Socket("127.0.0.1", 1234);
	    	  
	    	  ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
	    	  clientOutputStream.writeObject(control);
	    	 
	      } catch (Exception e) {System.out.println(e); }
	 }
}
