package br.com.ucsal.ian.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ucsal.ian.app.jogo_da_velha.BoardBuilder;
import br.com.ucsal.ian.app.jogo_da_velha.JogoDaVelhaServer;
import br.com.ucsal.ian.app.jogo_da_velha.Turn;
import br.com.ucsal.ian.app.jogo_da_velha.Turns;
import br.com.ucsal.ian.util.SocketConstants;

public class ServerMain {

	public static List<ServerThread> threads;	
	private static ServerRepository serverRepository;
	private static ServerSocket serverSocket;
	

	public static void main(String[] args) throws IOException {
				
		Turn.whoseTurnIsIt = Turns.FIRST_PLAYERS_TURN;
		
		serverRepository = new ServerRepository().fill();
		threads = new ArrayList<ServerThread>();
		
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
	
	public static List<List<Integer>> getData() {
		return serverRepository.get();
	}
	
	public static void setData(String positions,int value) {
		serverRepository.set(positions,value);
	}
	
	

}
