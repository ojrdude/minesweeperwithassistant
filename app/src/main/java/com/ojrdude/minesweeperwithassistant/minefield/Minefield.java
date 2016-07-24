package com.ojrdude.minesweeperwithassistant.minefield;

import com.ojrdude.minesweeperwithassistant.cell.Cell;
import com.ojrdude.minesweeperwithassistant.cell.CellContents;
import com.ojrdude.minesweeperwithassistant.cell.ThreeByThreeCellGrid;

import java.util.Locale;

/**
 * Class that represents a minefield. Its size and number of mines are
 * configurable. Its main role is to transform the grid of boolean values that the generator
 * provides into a grid of Cell objects.
 */
public class Minefield {

    public static final String COORDINATE_OUT_OF_RANGE = "Invalid %s coordinate: %d";

    private final IMinefieldGenerator minefieldGenerator;
    private Cell[][] fieldAsCells;
    private boolean[][] fieldAsBooleans;
    private boolean isFirstMove;

    /**
     * Constructor for Minefield. The distribution is controlled by the generator. Similarly,
     * the height, width and numberOfMines will be dictated by the generator.
     *
     * @param minefieldGenerator An implementation of the IMinefieldGenerator that will be responsible
     *                           for the minefield generation.
     */
    public Minefield(IMinefieldGenerator minefieldGenerator) {
        this.minefieldGenerator = minefieldGenerator;
        fieldAsBooleans = minefieldGenerator.generateMinefield();
        fieldAsCells = constructField(fieldAsBooleans);
        isFirstMove = true;
    }

    /**
     * Generate a new minefield distribution.
     */
    public void regenerateMinefield() {
        fieldAsBooleans = minefieldGenerator.generateMinefield();
        fieldAsCells = constructField(fieldAsBooleans);
        isFirstMove = true;
    }

    /**
     * Returns a ThreeByThreeCellGrid of the Minefield for with the cell at the
     * gven coordinates at the centre. It is provided so that analysing classes can obtain
     * a small section of the board for their analysis.
     *
     * @param xCoord The x coordinate of the cell to be at the centre of the returned grid.
     * @param yCoord The y coordinate of the cell rto be at the centre of the returned grid.
     * @return A ThreeByThreeCellGrid with the specified cell at the centre.
     * @throws IllegalArgumentException if one of the coordinates is out of the range of the minefield grid.
     */
    public ThreeByThreeCellGrid getThreeByThreeGrid(int xCoord, int yCoord) {
        checkCoordinatesInRange(xCoord, yCoord);

        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid();
        grid.setCell(ThreeByThreeCellGrid.GridLocation.CENTRE, fieldAsCells[xCoord][yCoord]);

        if (xCoord > 0 && yCoord > 0) {
            grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT, fieldAsCells[xCoord - 1][yCoord - 1]);
        }
        if (yCoord > 0) {
            grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP, fieldAsCells[xCoord][yCoord - 1]);
        }
        if (xCoord < minefieldGenerator.getWidth() - 1 && yCoord > 0) {
            grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT, fieldAsCells[xCoord + 1][yCoord - 1]);
        }
        if (xCoord > 0) {
            grid.setCell(ThreeByThreeCellGrid.GridLocation.LEFT, fieldAsCells[xCoord - 1][yCoord]);
        }
        if (xCoord < minefieldGenerator.getWidth() - 1) {
            grid.setCell(ThreeByThreeCellGrid.GridLocation.RIGHT, fieldAsCells[xCoord + 1][yCoord]);
        }
        if (xCoord > 0 && yCoord < minefieldGenerator.getHeight() - 1) {
            grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT, fieldAsCells[xCoord - 1][yCoord + 1]);
        }
        if (yCoord < minefieldGenerator.getHeight() - 1) {
            grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM, fieldAsCells[xCoord][yCoord + 1]);
        }
        if (xCoord < minefieldGenerator.getWidth() - 1 && yCoord < minefieldGenerator.getHeight() - 1) {
            grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT, fieldAsCells[xCoord + 1][yCoord + 1]);
        }

        return grid;
    }

    /**
     * Uncover the cell at postion x,y.
     *
     * @param xCoord The X coordinate.
     * @param yCoord The Y coordinate.
     * @return The CellContents of this cell.
     */
    public CellContents uncoverCell(int xCoord, int yCoord) {
        checkCoordinatesInRange(xCoord, yCoord);
        final Cell cell = fieldAsCells[xCoord][yCoord];
        cell.uncover();
        if (isFirstMove) {
            isFirstMove = false;

            if (cell.getContents() == CellContents.MINE) {

                fieldAsBooleans[xCoord][yCoord] = false;
                mineMoved:
                for (int j = 0; j < getHeight(); j++) {
                    for (int i = 0; i < getWidth(); i++) {
                        if (!fieldAsBooleans[i][j]) {
                            fieldAsBooleans[i][j] = true;
                            break mineMoved;
                        }
                    }
                }
                fieldAsCells = constructField(fieldAsBooleans);
            }
        }
        return fieldAsCells[xCoord][yCoord].getContents();
    }

    /**
     * Flags the cell at the coordinates. Does not catch exceptions thrown by Cell so exceptions
     * can be expected if the cell is uncovered or already flagged.
     *
     * @param xCoord The X coordinate.
     * @param yCoord The Y coordinate.
     */
    public void flagCell(int xCoord, int yCoord) {
        checkCoordinatesInRange(xCoord, yCoord);
        fieldAsCells[xCoord][yCoord].flag();
    }

    /**
     * Unflags the cell at the coordinates. Does not catch exceptions thrown by Cell so exceptions
     * can be expected if the cell is uncovered or already unflagged.
     *
     * @param xCoord The X coordinate.
     * @param yCoord The Y coordinate.
     */
    public void removeFlag(int xCoord, int yCoord) {
        checkCoordinatesInRange(xCoord, yCoord);
        fieldAsCells[xCoord][yCoord].removeFlag();
    }

    /**
     * Returns whether the cell is flagged or not.
     *
     * @param xCoord The X coordinate.
     * @param yCoord The Y coordinate.
     * @return True if the cell is flagged. False if not.
     */
    public boolean isCellFlagged(int xCoord, int yCoord) {
        checkCoordinatesInRange(xCoord, yCoord);
        return fieldAsCells[xCoord][yCoord].isFlagged();
    }

    public boolean isCellUncovered(int xCoord, int yCoord){
        checkCoordinatesInRange(xCoord,yCoord);
        return fieldAsCells[xCoord][yCoord].isUncovered();
    }

    /**
     * Throws an IllegalArgumentException if the coordinates are out of range.
     *
     * @param xCoord The X coordinate.
     * @param yCoord The Y coordinate.
     */

    private void checkCoordinatesInRange(int xCoord, int yCoord) {
        if (xCoord < 0 || xCoord >= minefieldGenerator.getWidth()) {
            throw new IllegalArgumentException(String.format(Locale.UK, COORDINATE_OUT_OF_RANGE, "x", xCoord));
        }
        if (yCoord < 0 || yCoord >= minefieldGenerator.getHeight()) {
            throw new IllegalArgumentException(String.format(Locale.UK, COORDINATE_OUT_OF_RANGE, "y", yCoord));
        }
    }

    @SuppressWarnings("ConstantConditions")
    // Too complicated for data flow algorithm but thorough testing of class
    // will build confidence in the method.
    private CellContents calculateCellContents(int cellXCoord, int cellYCoord, boolean[][] fieldRepresentation) {
        int numberOfAdjMines = 0;
        int bottomXCoord = cellXCoord > 0 ? cellXCoord - 1 : 0;
        int bottomYCoord = cellYCoord > 0 ? cellYCoord - 1 : 0;
        int topXCoord = cellXCoord < minefieldGenerator.getWidth() - 1 ? cellXCoord + 1 : minefieldGenerator.getWidth() - 1;
        int topYCoord = cellYCoord < minefieldGenerator.getHeight() - 1 ? cellYCoord + 1 : minefieldGenerator.getHeight() - 1;

        for (int i = bottomXCoord; i <= topXCoord; i++) {
            for (int j = bottomYCoord; j <= topYCoord; j++) {
                if (fieldRepresentation[i][j] && !(i == cellXCoord && j == cellYCoord)) {
                    numberOfAdjMines++;
                }
            }
        }
        return CellContents.getCellContentsForValue(numberOfAdjMines);
    }

    private Cell[][] constructField(boolean[][] booleanFieldRepresentation) {
        Cell[][] newField = new Cell[minefieldGenerator.getWidth()][minefieldGenerator.getHeight()];
        for (int i = 0; i < minefieldGenerator.getWidth(); i++) {
            for (int j = 0; j < minefieldGenerator.getHeight(); j++) {
                if (booleanFieldRepresentation[i][j]) {
                    newField[i][j] = new Cell(i, j, CellContents.MINE);
                } else {
                    CellContents cellContents = calculateCellContents(i, j, booleanFieldRepresentation);
                    newField[i][j] = new Cell(i, j, cellContents);
                }
            }
        }
        return newField;
    }

    public int getNumberOfCells() {
        return minefieldGenerator.getHeight() * minefieldGenerator.getWidth();
    }

    public int getWidth() {
        return minefieldGenerator.getWidth();
    }

    public int getHeight() {
        return minefieldGenerator.getHeight();
    }
}
