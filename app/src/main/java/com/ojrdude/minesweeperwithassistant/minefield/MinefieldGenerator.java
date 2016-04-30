package com.ojrdude.minesweeperwithassistant.minefield;

import java.util.Locale;
import java.util.Random;

/**
 * Class responsible for generating a random minefield.
 */
public class MinefieldGenerator implements IMinefieldGenerator{
    public static final String INVALID_WIDTH = "Invalid width: %d. Valid values are: 0 < width <= 100.";
    public static final String INVALID_HEIGHT = "Invalid height: %d. Valid values are: 0 < height <= 100.";
    public static final String INVALID_NUMBER_OF_MINES = "Invalid numberOfMines. Must be greater than 0. Must be less than (width*height).";

    private int width, height, numberOfMines;

    /**
     * Constructor for minefieldGenerator.
     *
     * @param width The width of the grids that will be generated. Allowed values: 0 < width <= 100.
     * @param height The height of the grids that will be generated. Allowed values: 0 < height <= 100.
     * @param numberOfMines The number of mines to place in grids that are generated.
     *                      Allowed values: 0 < numberOfMines < (width*height)
     */
    public MinefieldGenerator(int width, int height, int numberOfMines){
        if (width <= 0 || width > 100) {
            throw new IllegalArgumentException(String.format(Locale.UK, INVALID_WIDTH, width));
        }
        if (height <= 0 || height > 100) {
            throw new IllegalArgumentException(String.format(Locale.UK, INVALID_HEIGHT, height));
        }
        if (numberOfMines <= 0 || numberOfMines >= (width * height)) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_MINES);
        }
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;

    }

    /**
     * Generates a random distribution of "mines" (actually true/false boolean values) in grid.
     * @return A random distribution of boolean true/false values.
     */
    public boolean[][] generateMinefield() {
        boolean[][] field = new boolean[width][height];
        Random rand = new Random();
        for (int i = 0; i < numberOfMines; i++) {
            int randomWidth = rand.nextInt(width);
            int randomHeight = rand.nextInt(height);
            boolean minePlaced = false;
            while (!minePlaced) {
                if (!field[randomWidth][randomHeight]) {
                    field[randomWidth][randomHeight] = true;
                    minePlaced = true;
                } else { // Move on to next cell.
                    if (randomWidth < width - 1) {
                        randomWidth++;
                    } else if (randomHeight < height -1) {
                        randomWidth = 0;
                        randomHeight++;
                    } else {
                        randomWidth = 0;
                        randomHeight = 0;
                    }
                }
            }
        }
        return field;
    }

    /**
     * Get the width of the grids that this generator will produce.
     * @return the width of the grids produced.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the grids that this generator will produce.
     * @return the height of the grids produced.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the number of mines that will be in a distribution that this generator produces.
     * @return the number of mines that this generator will distribute.
     */
    public int getNumberOfMines() {
        return numberOfMines;
    }
}
