package br.com.ucsal.ian.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.ucsal.ian.server.ServerHandler;
import br.com.ucsal.ian.server.ServerMain;
import br.com.ucsal.ian.util.SocketConstants;

public class ClientMain {

	private Client client;
	
	public static void main(String[] args) throws UnknownHostException, IOException {


		Socket socket = new Socket(SocketConstants.HOST, SocketConstants.PORT);
		
		Client client = new Client(socket);
				
			while (true) {
				String data = client.read();
				client.send(SocketConstants.ENABLE_KEYBOARD.trim().equalsIgnoreCase(data.trim()));								
			}	
	}
	
	
	
	

	
}
