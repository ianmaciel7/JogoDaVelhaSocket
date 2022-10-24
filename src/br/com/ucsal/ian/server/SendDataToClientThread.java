package br.com.ucsal.ian.server;

import br.com.ucsal.ian.client.Client;
import br.com.ucsal.ian.util.SocketConstants;

public class SendDataToClientThread extends Thread{

	private ServerThread serverThread;
	
	public SendDataToClientThread(ServerThread serverThread) {
		this.serverThread = serverThread;
	}
	
	@Override
	public void run() {
		while (true) {
			//String serverData = serverThread.sendDataToClient();				
		}
	}

}
