package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.net.Socket;

public class Prova {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ClientGameLudo c = new ClientGameLudo("192.168.1.16","ale");
	//	System.out.println(c.checkConnection());
		try {
			Socket client = new Socket("192.168.1.16",50358);
			while(true) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
