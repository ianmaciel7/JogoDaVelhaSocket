package br.com.ucsal.ian.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.ucsal.ian.util.SocketConstants;

public class ClientMain {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket(SocketConstants.HOST, SocketConstants.PORT);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		Scanner scanner = new Scanner(System.in);

		new Thread(new ClientRunnable(socket)).start();

		while (true) {
			String msg = scanner.nextLine();
			out.println(msg);
		}

	}
}
