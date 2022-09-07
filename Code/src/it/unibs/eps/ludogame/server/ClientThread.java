package it.unibs.eps.ludogame.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import it.unibs.eps.ludogame.game.*;

//singolo giocatore...
public class ClientThread implements Runnable {
	private Socket socket;
	public Controllore controller;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	ClientThread(Socket socket, Controllore controller) {
		this.socket = socket;
		this.controller = controller;
	}

	@Override
	public synchronized void run() {
		// Qua vanno inseriti i metodi che il client deve fare
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			Casella[] base = (Casella[]) in.readObject();

			System.out.println(base.toString());

		} catch (Exception e) {
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
			}

		}
	}

	protected void out(String data) {

		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
