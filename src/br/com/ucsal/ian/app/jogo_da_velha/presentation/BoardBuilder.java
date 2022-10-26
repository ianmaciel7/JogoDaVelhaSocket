package br.com.ucsal.ian.app.jogo_da_velha.presentation;

import java.util.List;

import br.com.ucsal.ian.app.jogo_da_velha.util.Properties;

public class BoardBuilder {
	private StringBuilder builder;
	private List<List<Integer>> values;

	public BoardBuilder(List<List<Integer>> values) {
		super();
		this.builder = new StringBuilder();
		this.values = values;
	}

	@Override
	public String toString() {

		System.out.println(this.values);
		
		List<Integer> l1 = values.get(0);
		List<Integer> l2 = values.get(1);
		List<Integer> l3 = values.get(2);
		
		String line1 = line(l1.get(0),l1.get(1),l1.get(2));
		String line2 = line(l2.get(0),l2.get(1),l2.get(2));
		String line3 = line(l3.get(0),l3.get(1),l3.get(2));
		
		builder.append("\n\n\n\n\n\n-- JOGADAS -- \n" );
		builder.append("  0   1   2 \n");
		builder.append("0"+line1+"\n");
		builder.append("1"+line2+"\n");
		builder.append("2"+line3+"\n");

		return builder.toString();
	}
	
	private String line(Integer v1,Integer v2,Integer v3) {
		
		return  String.format(" %s | %s | %s ", 
				getValue(v1),
				getValue(v2),
				getValue(v3)
		);
	}
	
	private String getValue(int v) {
		switch (v) {
		case Properties.PLAYER_1: {
			return "X";
		}
		case Properties.PLAYER_2: {
			return "O";
		}
		default:
			return " ";
		}
	}
	
	
	
	
	
	
}
