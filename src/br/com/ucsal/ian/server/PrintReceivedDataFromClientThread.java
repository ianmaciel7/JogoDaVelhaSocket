package br.com.ucsal.ian.server;

import java.net.Socket;

public class PrintReceivedDataFromClientThread extends Thread {

	private ServerThread serverThread;

	public PrintReceivedDataFromClientThread(ServerThread serverThread) {
		this.serverThread = serverThread;
	}

	@Override
	public void run() {
		while (true) {
			serverThread.refreshServer(new Server(serverThread.getSocket()));
			String serverData = serverThread.readDataFromClient();
			System.out.println(serverData);

		}
	}
}
