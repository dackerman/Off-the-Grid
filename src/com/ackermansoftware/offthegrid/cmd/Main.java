package com.ackermansoftware.offthegrid.cmd;

import com.ackermansoftware.offthegrid.generator.EntropySource;
import com.ackermansoftware.offthegrid.generator.LatinSquareBuilder;

/**
 * Sample application executing the Latin Square generator and displaying the
 * output on the command line.
 * 
 * @author David Ackerman
 */
public class Main {

	/**
	 * Configure the {@link LatinSquareBuilder} with a {@link TextDisplay} and
	 * {@link RandomGenerator}.
	 */
	public static void main(String[] args) {
		EntropySource entropySource = new RandomGenerator(System.currentTimeMillis());
		TextDisplay textDisplay = new TextDisplay();
		LatinSquareBuilder latinSquareBuilder = new LatinSquareBuilder(entropySource);

		new Main(textDisplay, latinSquareBuilder).run();
	}

	private final TextDisplay display;
	private final LatinSquareBuilder builder;

	public Main(TextDisplay display, LatinSquareBuilder builder) {
		this.display = display;
		this.builder = builder;
	}

	public void run() {
		long duration = timeLatinSquareGeneration(builder);

		for (int row = 0; row < LatinSquareBuilder.SIZE; row++) {
			System.out.println(display.getRow(row));
		}
		System.out.println(String.format("Took %d milliseconds to calculate.", duration));
	}

	private long timeLatinSquareGeneration(LatinSquareBuilder builder) {
	  long start = System.currentTimeMillis();
		builder.generate(display);
		long duration = System.currentTimeMillis() - start;
	  return duration;
  }
}
