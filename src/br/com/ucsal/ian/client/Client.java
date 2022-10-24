package br.com.ucsal.ian.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Socket socket;
	private BufferedReader readerData;
	private PrintWriter senderData;
	private Scanner scanner;


	public Client(Socket socket) {
		try {
			this.socket = socket;
			readerData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			senderData =  new PrintWriter(socket.getOutputStream(), true);
			scanner =  new Scanner(System.in);			
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}
	
	public String readDataFromServer() {
		try {
			String response = readerData.readLine();
	        return response;
	        
	        
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}

	public String sendDataToServerByKeyBoard() {

		scanner = new Scanner(System.in);

		
		String msg = scanner.nextLine();	
		senderData.println(msg);
	
		return msg;			
	}
	
}
