package com.ackermansoftware.offthegrid.cmd;

import java.util.Random;

import com.ackermansoftware.offthegrid.generator.EntropySource;

/**
 * Provides entropy through the standard java {@code Random} API.
 * 
 * @author David Ackerman
 */
public class RandomGenerator implements EntropySource {

	private final Random r;

	public RandomGenerator() {
		r = new Random(System.currentTimeMillis());
	}

	public RandomGenerator(long seed) {
		r = new Random(seed);
	}

	@Override
	public int randomNumber(int scale) {
		return r.nextInt(scale);
	}
}