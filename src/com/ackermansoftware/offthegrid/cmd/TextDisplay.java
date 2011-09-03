package com.ackermansoftware.offthegrid.cmd;

import com.ackermansoftware.offthegrid.generator.GridDisplay;

public class TextDisplay implements GridDisplay {

	static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private final int[][] cells = new int[26][26];

	@Override
	public void displayAt(int x, int y, int value) {
		cells[x][y] = value;
	}

	public String getRow(int row) {
		StringBuilder sbRow = new StringBuilder(26);
		for (int value : cells[row]) {
			sbRow.append(convert(value));
		}
		return sbRow.toString();
	}

	private char convert(int i) {
		return ALPHA.charAt(i);
	}
}