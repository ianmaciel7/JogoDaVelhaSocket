package br.com.ucsal.ian.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ServerThread extends Thread {

	private Socket socket;
	private PrintWriter out;
	private List<String> msgs;
	
	
	
	public ServerThread(Socket socket,List<String> msgs) {
		super();
		this.socket = socket;
		this.msgs = msgs;
	}



	@Override
	public void run() {
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			while(true) {
				String msg = in.readLine();
				msgs.add(msg);
				out.println(Arrays.toString(msgs.toArray()));	
				System.out.println("cliente msg: "+msg);
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.run();
	}

}
