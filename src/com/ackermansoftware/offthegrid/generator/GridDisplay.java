package com.ackermansoftware.offthegrid.generator;

/**
 * Describes the ability to display values in an two dimensional grid.
 * 
 * @author David Ackerman
 */
public interface GridDisplay {

	/**
	 * Display the given value at coordinates (x, y).
	 * 
	 * Note: this method may be called multiple times for each (x, y) pair, in the
	 * case that the generator needs to restart a row.
	 * 
	 * Also, do not depend on this being called in sequential x or y order, just
	 * that each (x, y) pair will all be called at least once. If you want to
	 * display the final value statically, it is recommended that you store the
	 * data in a two dimensional array.
	 */
	void displayAt(int x, int y, int value);
}