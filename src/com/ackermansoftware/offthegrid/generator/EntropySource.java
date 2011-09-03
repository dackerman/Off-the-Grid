package com.ackermansoftware.offthegrid.generator;

/**
 * Provides entropy to an application via random numbers.
 * 
 * @author David Ackerman
 */
public interface EntropySource {

	/**
	 * Return a random number between 0 (inclusive) and scale (exclusive).
	 */
	int randomNumber(int scale);
}