package br.com.ucsal.ian.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.ian.app.jogo_da_velha.JogoDaVelhaServer;
import br.com.ucsal.ian.app.jogo_da_velha.Turn;
import br.com.ucsal.ian.app.jogo_da_velha.Turns;
import br.com.ucsal.ian.util.SocketConstants;

public class ServerMain {

	public static List<ServerThread> threads;
	public static List<String> data;
	private static ServerSocket serverSocket;
	
	public static void main(String[] args) throws IOException {
		
		Turn.whoseTurnIsIt = Turns.FIRST_PLAYERS_TURN;
		
		threads = new ArrayList<ServerThread>();
		data = new ArrayList<String>();
		serverSocket = new ServerSocket(SocketConstants.PORT);
		
		new JogoDaVelhaServer().start();
		
		createServerThread();
		
		

	}

	private static void createServerThread() throws IOException {
		while (true) {	
			Socket clientConnected= serverSocket.accept();
			ServerThread serverThread = new ServerThread(clientConnected);
			threads.add(serverThread);
			serverThread.start();
		}
	}

}
