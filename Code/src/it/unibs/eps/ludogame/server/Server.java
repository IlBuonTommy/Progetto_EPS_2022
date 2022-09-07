package it.unibs.eps.ludogame.server;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private ServerSocket serverSocket;
	private static Integer port = 50358;
	Controllore controller = new Controllore();

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.start(port);
	}

	private void start(Integer port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(">Server avviato..." + serverSocket);
			// LogController.log(Log.INFO, "Running: " + serverSocket);
			// ServerController controller = new ServerController();
			Controllore controller = new Controllore();

			ExecutorService pool = Executors.newCachedThreadPool();
			int num = 0;
			while (true) {
				// Come passare roba dal server al thread del client?? forse cosi?
				ClientThread gioc1 = new ClientThread(serverSocket.accept(), controller);
				pool.execute(gioc1);
				gioc1.controller.numGiocatori = 10;
				System.out.println(gioc1.controller.numGiocatori);

				num++;
				System.out.println("Nuovo Client collegato: #" + num);
				controller.mandaBenvenuto();

			}

		} catch (Exception e) {
			/* LogController.log(Log.ERROR, e.toString()); */ }
	}

}
