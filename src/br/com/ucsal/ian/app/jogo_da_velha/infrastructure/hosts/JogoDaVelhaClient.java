package br.com.ucsal.ian.app.jogo_da_velha.infrastructure.hosts;

import java.io.IOException;
import java.net.UnknownHostException;

import br.com.ucsal.ian.app.hosts.client.Client;
import br.com.ucsal.ian.app.jogo_da_velha.domain.Action;
import br.com.ucsal.ian.app.jogo_da_velha.domain.common.Properties;

public class JogoDaVelhaClient extends Client {

	private boolean isKeyboardEnabled;

	public JogoDaVelhaClient() throws UnknownHostException, IOException {	
		super(Properties.HOST, Properties.PORT);
		this.isKeyboardEnabled = false;
	}

	@Override
	public void run() {
		super.run();
		
		new Thread(() -> {
			while (true) {
				this.read();
			}
		}).start();
		
		new Thread(() -> {
			while (true) {		
				this.send();
			}
		}).start();
	}
	
	
	@Override
	public String read() {
		String data= super.read();
		
		if(Action.ENABLE_KEYBOARD.name().trim().equalsIgnoreCase(data.trim())) this.isKeyboardEnabled = true;
		if(Action.DISABLE_KEYBOARD.name().trim().equalsIgnoreCase(data.trim())) this.isKeyboardEnabled = false;
		
		return data;
	}

	@Override
	public void send() {
		if (this.isKeyboardEnabled) {	
			super.send();
		} else {
			super.flushSystemIn();
		}
	}

}