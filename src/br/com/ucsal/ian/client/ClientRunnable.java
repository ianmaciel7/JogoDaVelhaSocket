package br.com.ucsal.ian.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientRunnable  implements Runnable{

	private Socket socket;
	//private PrintWriter out;
	private BufferedReader in;
	
	public ClientRunnable(Socket socket) throws IOException {
		super();
		this.socket = socket;		
		this.in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
		//this.scanner = new Scanner(System.in);
	}




	@Override
    public void run() {
        
            try {
                while(true) {
                    String response = in.readLine();
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }

	
	
	
}
