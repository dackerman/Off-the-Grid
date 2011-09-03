package com.ackermansoftware.offthegrid.generator;

/**
 * Represents a 26-bit bitfield. Allows for setting and unsetting bits, and
 * checking if bits exist.
 * 
 * @author David Ackerman
 */
public class BitField {
	private static final int FULL = 0x3FFFFFF;
	private int bits;

	/**
	 * Create a new {@code BitField} with no bits set.
	 */
	public BitField() {
	}

	/**
	 * Create a new {@code BitField} with the given bits set.
	 */
	public BitField(int bits) {
		this.bits = bits;
	}

	/**
	 * True if the bit at {@code pos} is set.
	 */
	public boolean isSet(int pos) {
		return ((bits >> pos) & 1) == 1;
	}

	/**
	 * Set the bit at {@code pos}
	 */
	public void set(int pos) {
		bits |= (1 << pos);
	}

	/**
	 * Unset the bit at {@code pos}
	 */
	public void unset(int pos) {
		bits &= (1 << pos) ^ FULL;
	}

	/**
	 * Return a bitwise-or on this {@code BitField} with another one.
	 */
	public BitField combine(BitField other) {
		return new BitField(bits | other.bits);
	}

	/**
	 * True if all of the bits in this {@code BitField} are set.
	 */
	public boolean isFull() {
		return bits == FULL;
	}
}