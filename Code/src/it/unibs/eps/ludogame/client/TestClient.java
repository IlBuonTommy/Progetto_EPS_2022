package it.unibs.eps.ludogame.client;

import java.io.ObjectOutputStream;
import java.net.Socket;

import it.unibs.eps.ludogame.game.Casella;
import it.unibs.eps.ludogame.server.Controllore;

public class TestClient {

	 public static void main(String[] arg) {
		 Casella cas = new Casella();
		 Casella c [][]= new Casella[5][5];
		 c[0][0] = cas;
		 Controllore control = new Controllore();
		 control.setBase(c);
	      try {
	    	  Socket socketConnection = new Socket("127.0.0.1", 50358);
	    	 
	    	  ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
	    	  clientOutputStream.writeObject(control.getBase());
	    	
	    	 
	      } catch (Exception e) {System.out.println(e); }
	 }
}
