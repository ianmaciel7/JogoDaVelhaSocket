package br.com.ucsal.ian.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.ucsal.ian.server.ServerMain;
import br.com.ucsal.ian.util.SocketConstants;

public class ClientMain {

	public static void main(String[] args) throws UnknownHostException, IOException {


		Socket socket = new Socket(SocketConstants.HOST, SocketConstants.PORT);
		Client client = new Client(socket);
				
			while (true) {
				String data = readData(client);
				sendData(client, SocketConstants.ENABLE_KEYBOARD.trim().equalsIgnoreCase(data.trim()));								
			}	
	}
	
	
	
	private static void sendData(Client client,boolean canRead) {
		if(canRead) {			
			client.sendDataToServerByKeyBoard();
		}
	}
	
	private static String readData(Client client) {
		if(client != null) {
			String serverData = client.readDataFromServer();
			System.out.println(serverData);
			return serverData;
		}
		return null;
	}

	
}
