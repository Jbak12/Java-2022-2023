public interface PawnPosition extends Position {
	/**
	 * Unikalny numer identyfikacyjny pionka
	 *
	 * @return numer identyfikacyjny
	 */
	public int pawnId();

	/**
	 * Set the position of the pawn
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
}