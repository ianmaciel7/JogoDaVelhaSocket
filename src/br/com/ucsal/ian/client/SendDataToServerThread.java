package br.com.ucsal.ian.client;

import java.net.Socket;

import br.com.ucsal.ian.util.SocketConstants;

public class SendDataToServerThread extends Thread {

	
	private Client client;
	private Socket socket;
	
	public SendDataToServerThread(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		while (true) {
			client = new Client(socket);

			String serverData = client.readDataFromServer();
			boolean isKeyboardDisabled = SocketConstants.DISABLE_KEYBOARD.trim().equalsIgnoreCase(serverData.trim());			
			
			
			String data = null;
			if(!isKeyboardDisabled) {	
				System.out.println("ffffffffff");
				client.sendDataToServerByKeyBoard();
			}
				
		}
	}

	
}
