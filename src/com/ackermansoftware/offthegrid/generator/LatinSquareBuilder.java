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

	// Values to determine success or failure of the current row.
	private static final boolean NEXT_ROW = true;
	private static final boolean RETRY_ROW = false;

	public static final int SIZE = 26;

	private GridDisplay display;
	private final EntropySource rand;

	private final BitField[] colMasks = new BitField[SIZE];

	public LatinSquareBuilder(EntropySource rand) {
		this.rand = rand;
	}

	/**
	 * Generate a new Latin Square, and output the result to the given
	 * {@link GridDisplay}.
	 */
	public void generate(GridDisplay output) {
		display = output;
		initializeColumnsToZero();
		int currentRow = 0;
		for (int row = 0; row < SIZE; row++) {
			// Retries calculating the row until it succeeds.
			while (!calculateRow(currentRow)) {}
		}
	}

	/**
	 * Calculate the current row's values and set them into the output. If it
	 * succeeds, returns true. If it can't complete, it returns false.
	 */
	private boolean calculateRow(int row) {
		int[] outputValues = new int[SIZE];

		BitField rowMask = new BitField();
		// Add all columns
		for (int col = 0; col < colMasks.length; col++) {
			BitField rowAndColMask = rowMask.combine(colMasks[col]);
			if (cannotCompleteCurrentRow(rowAndColMask)) {
				return RETRY_ROW;
			} else {
				int character = findUnusedCharacter(rowAndColMask);
				rowMask.set(character);
				outputValues[col] = character;
				display.displayAt(row, col, character);
			}
		}

		addToColumnMasks(outputValues);
		return NEXT_ROW;
	}

	private boolean cannotCompleteCurrentRow(BitField rowAndColMask) {
	  return rowAndColMask.isFull();
  }

	private void initializeColumnsToZero() {
		for (int i = 0; i < colMasks.length; i++) {
			colMasks[i] = new BitField();
		}
	}

	private void addToColumnMasks(int[] outputValues) {
	  for (int i = 0; i < outputValues.length; i++) {
			colMasks[i].set(outputValues[i]);
		}
  }

	/**
	 * Find the next character for this row that doesn't conflict with the
	 * existing row and column bitfields.
	 */
	private int findUnusedCharacter(BitField rowAndColMask) {
		while (true) {
			int candidateCharacter = rand.randomNumber(SIZE);
			if (!rowAndColMask.isSet(candidateCharacter)) {
				return candidateCharacter;
			}
		}
	}
}
