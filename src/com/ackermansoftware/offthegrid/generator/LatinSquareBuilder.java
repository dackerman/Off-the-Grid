package com.ackermansoftware.offthegrid.generator;

/**
 * Builds a Latin Square, and outputs the value to a {@link GridDisplay}.
 * 
 * Has the ability to use any type of EntropySource to randomly pick from the
 * set of available letters.
 * 
 * @author David Ackerman
 */
public class LatinSquareBuilder {

	public static final int SIZE = 26;

	private GridDisplay display;
	private final EntropySource rand;

	private final BitField[] columns = new BitField[SIZE];

	public LatinSquareBuilder(EntropySource rand) {
		this.rand = rand;
	}

	/**
	 * Generate a new Latin Square, and output the result to the given
	 * {@link GridDisplay}.
	 */
	public void generate(GridDisplay output) {
		display = output;
		for (int i = 0; i < columns.length; i++) {
			columns[i] = new BitField();
		}
		int currentRow = 0;
		while (currentRow < SIZE) {
			currentRow = calculateRow(currentRow);
		}
	}

	private int calculateRow(int row) {
		BitField currentRow = new BitField();
		int[] outputValues = new int[SIZE];
		for (int i = 0; i < columns.length; i++) {
			BitField used = currentRow.combine(columns[i]);
			if (used.isFull()) {
				return row;
			}
			int candidate = findUnusedCandidate(used);
			currentRow.set(candidate);
			outputValues[i] = candidate;
			display.displayAt(row, i, candidate);
		}
		// Set columns with values chosen.
		for (int i = 0; i < outputValues.length; i++) {
			columns[i].set(outputValues[i]);
		}
		return row + 1;
	}

	private int findUnusedCandidate(BitField used) {
	  int candidate;
	  do {
			candidate = rand.randomNumber(SIZE);
	  } while (used.isSet(candidate));
	  return candidate;
  }
}
