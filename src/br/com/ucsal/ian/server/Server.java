package br.com.ucsal.ian.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Server {

	private Socket socket;
	private BufferedReader readerData;
	private PrintWriter senderData;
	private Scanner scanner;

	public Server(Socket socket) {
		try {
			this.socket = socket;
			readerData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			senderData = new PrintWriter(socket.getOutputStream(), true);
			scanner = new Scanner(System.in);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public String readDataFromClient() {

		try {
			String response = readerData.readLine();
			System.out.println(response);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void sendDataToClient(String data) {
		System.out.println("Enviando para cliente: "+data);
		
		senderData.println(data);
		senderData.flush();	
	}

	public void refresh() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						readerData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						senderData = new PrintWriter(socket.getOutputStream(), true);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}
		});

	}

}
