package br.com.ucsal.ian.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.ian.app.jogo_da_velha.JogoDaVelhaServer;
import br.com.ucsal.ian.util.SocketConstants;

public class ServerMain {

	public static List<ServerThread> connections;
	public static List<String> data;
	private static ServerSocket serverSocket;
	
	public static void main(String[] args) throws IOException {
		
		connections = new ArrayList<ServerThread>();
		data = new ArrayList<String>();
		serverSocket = new ServerSocket(SocketConstants.PORT);

		
		new JogoDaVelhaServer().start();

		while (true) {	
			Socket clientConnected= serverSocket.accept();
			ServerThread serverThread =new ServerThread(clientConnected);
			serverThread.start();
		}
		
		

	}

}
