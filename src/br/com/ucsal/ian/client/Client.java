package br.com.ucsal.ian.client;

import java.io.BufferedReader;
import java.io.Console;
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
			senderData = new PrintWriter(socket.getOutputStream(), true);
			scanner = new Scanner(System.in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(boolean canRead) {

		try {
			if (canRead) {
				sendDataToServerByKeyBoard();
			} else {
				System.in.read(new byte[System.in.available()]);
			}
		} catch (Exception e) {

		}

	}

	public String read() {

		try {

			String serverData = readDataFromServer();
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
