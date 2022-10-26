package br.com.ucsal.ian.app;

import java.io.IOException;
import java.net.UnknownHostException;

import br.com.ucsal.ian.app.jogo_da_velha.infrastructure.hosts.JogoDaVelhaClient;

public class ClientApp {

	public static void main(String[] args) throws UnknownHostException, IOException {
		new JogoDaVelhaClient().run();		
	}

}
