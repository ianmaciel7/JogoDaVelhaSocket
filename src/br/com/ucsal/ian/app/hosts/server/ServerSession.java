package br.com.ucsal.ian.app.hosts.server;

import static br.com.ucsal.ian.app.jogo_da_velha.domain.common.Properties.*;

import java.util.Arrays;
import java.util.List;

public class ServerSession {

	private List<List<Integer>> data;
	
		
	public List<List<Integer>> get() {
		return data;
	}
	
	
	public boolean set(String positions,int value) {
		List<String> d = Arrays.asList(positions.split(","));
		
		if(d.size() != 2) {
			System.out.println("[ER]");
			return false;
		}
		
		List<Integer> numbers = d.stream().map((e) -> Integer.valueOf(e)).toList();
		int row = numbers.get(0);
		int column = numbers.get(1);
		
		set(row,column,value);
		
		return true;
	}
	
	public boolean set(int row,int column,int value) {
		
		data.get(row).set(column, Integer.valueOf(value));
		return true;
	}
	

	public ServerSession fill() {
		this.data = Arrays.asList(
							Arrays.asList(NOT_CHOSEN,NOT_CHOSEN,NOT_CHOSEN),
							Arrays.asList(NOT_CHOSEN,NOT_CHOSEN,NOT_CHOSEN),
							Arrays.asList(NOT_CHOSEN,NOT_CHOSEN,NOT_CHOSEN)
						);
		return this;
	}
	
}
