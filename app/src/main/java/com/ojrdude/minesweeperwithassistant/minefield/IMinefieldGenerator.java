package com.ojrdude.minesweeperwithassistant.minefield;

/**
 * Interface for classes that can generate minefields.
 */
interface IMinefieldGenerator {
    boolean[][] generateMinefield();
    int getWidth();
    int getHeight();
    int getNumberOfMines();
}
