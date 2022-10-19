package br.com.ucsal.ian.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.ian.util.SocketConstants;

public class ServerMain {

	public static void main(String[] args) throws IOException {
		List<String> msgs = new ArrayList<String>();
		ServerSocket serverSocket = new ServerSocket(SocketConstants.PORT);

		while (true) {
			new ServerThread(serverSocket.accept(),msgs).start();
		}

	}

}
