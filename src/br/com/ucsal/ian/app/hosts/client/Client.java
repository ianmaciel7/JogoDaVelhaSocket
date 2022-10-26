package br.com.ucsal.ian.app.hosts.client;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public abstract class Client implements Runnable {

	private Socket socket;
	private BufferedReader readerData;
	private PrintWriter senderData;
	private Scanner scanner;
	protected String lastData;
	private String host;
	private int port;

	public Client(String host,int port) throws UnknownHostException, IOException {
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			this.socket = new Socket(host, port);
			this.readerData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.senderData = new PrintWriter(socket.getOutputStream(), true);
			this.scanner = new Scanner(System.in);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void send() {
		sendDataToServerByKeyBoard();
	}
	
	public void flushSystemIn() {
		try {
			System.in.read(new byte[System.in.available()]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String read() {
		try {

			String serverData = readDataFromServer();
			this.lastData = serverData;
			System.out.println(serverData);
			return serverData;
		} catch (Exception e) {
		}

		return null;
	}

	private String readDataFromServer() {
		try {
			String response = readerData.readLine();
			return response;

		} catch (Exception e) {

		}
		return null;

	}

	private String sendDataToServerByKeyBoard() {

		try {

			System.out.println("Teclado liberado");
			String msg = scanner.nextLine();
			senderData.println(msg);
			senderData.flush();

			return msg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
}
