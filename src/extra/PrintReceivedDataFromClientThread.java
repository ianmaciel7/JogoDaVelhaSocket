package extra;

import java.net.Socket;

import br.com.ucsal.ian.server.ServerHandler;
import br.com.ucsal.ian.server.ServerThread;

public class PrintReceivedDataFromClientThread extends Thread {

	private ServerThread serverThread;

	public PrintReceivedDataFromClientThread(ServerThread serverThread) {
		this.serverThread = serverThread;
	}

	@Override
	public void run() {
		while (true) {
			//serverThread.refreshServer(new ServerHandler(serverThread.getSocket()));
			String serverData = serverThread.read();
			System.out.println(serverData);

		}
	}
}
