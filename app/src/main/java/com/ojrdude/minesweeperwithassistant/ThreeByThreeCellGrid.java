package com.ojrdude.minesweeperwithassistant;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents 9 Cells in a 3x3 grid of the Minesweeper game board. It is provided
 * as a utility to make analysis of the game board simpler.
 */
public class ThreeByThreeCellGrid implements Iterable<Cell> {

    public static final String ITERATOR_AT_END = "ThreeByThreeCellGrid Iterator already at end!";
    public static final String REMOVE_NOT_SUPPORTED_BY_ITERATOR = "ThreeByThreeCellGrid Iterator does not support remove";
    public static final String ARRAY_NOT_LENGTH_9 = "A ThreeByThreeCellGrid can only be built from an array" +
            " of length 9. If the grid is at the edge of the board, then the 'non-existent' cells" +
            " should be null";

    /**
     * Defines the grid locations of a three by three grid.
     */
    public enum GridLocation {
        TOP_LEFT(0),
        TOP(1),
        TOP_RIGHT(2),
        LEFT(3),
        CENTRE(4),
        RIGHT(5),
        BOTTOM_LEFT(6),
        BOTTOM(7),
        BOTTOM_RIGHT(8);
        public int value;

        GridLocation(int order){
            this.value = order;
        }
    }
    Cell[] cells;

    /**
     *  Constructor. Instantiates the grid with all cells empty.
     */
    public ThreeByThreeCellGrid(){
        cells = new Cell[9];
    }

    /**
     * Constructor for ThreeByThreeCellGrid, with the cells set at instantiation.
     * If there is no cell in a particular posistion (e.g. if the cell in the centre is
     * near the corner, then a cell can be set to null.
     *
     * @param topLeft Cell at the top left.
     * @param top Cell at the top.
     * @param topRight Cell at the top right.
     * @param left Cell at left.
     * @param centre Cell at the centre.
     * @param right Cell at the right.
     * @param bottomLeft Cell at the bottom left.
     * @param bottom Cell at the bottom.
     * @param bottomRight Cell at the bottom right.
     */
    public ThreeByThreeCellGrid(Cell topLeft, Cell top, Cell topRight, Cell left, Cell centre, Cell right,
                                Cell bottomLeft, Cell bottom, Cell bottomRight){

        cells = new Cell[9];
        cells[GridLocation.TOP_LEFT.value] = topLeft;
        cells[GridLocation.TOP.value] = top;
        cells[GridLocation.TOP_RIGHT.value] = topRight;
        cells[GridLocation.LEFT.value] = left;
        cells[GridLocation.CENTRE.value] = centre;
        cells[GridLocation.RIGHT.value] = right;
        cells[GridLocation.BOTTOM_LEFT.value] = bottomLeft;
        cells[GridLocation.BOTTOM.value] = bottom;
        cells[GridLocation.BOTTOM_RIGHT.value] = bottomRight;

    }

    /**
     * Constructor for ThreeByThreeGrid which takes an array of cells as a parameter.
     * The cells are declared in the array from topleft to bottom right:
     *  0 1 2
     *  3 4 5
     *  6 7 8
     *  If the grid is near the edge of the board and not all cells are present, they should
     *  be null values in the array.
     *
     * @param cellsArray An array of Cells, must be length 9.
     */
    public ThreeByThreeCellGrid(Cell[] cellsArray){
        if (cellsArray.length != 9){
            throw new IllegalArgumentException(ARRAY_NOT_LENGTH_9);
        }
        cells = cellsArray;
    }

    /**
     * Retrieve a cell from the grid.
     *
     * @param gridLocation The location of the cell to retrieve.
     * @return The cell at this location.
     */
    public Cell getCell(GridLocation gridLocation){
        return cells[gridLocation.value];
    }

    /**
     * Set a cell in the grid.
     *
     * @param position The location of the cell to set.
     * @param cell The new cell.
     */
    public void setCell(GridLocation position, Cell cell){
        cells[position.value] = cell;
    }

    /**
     * Returns whether a cell has been defined at a grid position (i.e. not null)
     *
     * @param position The grid position to query.
     * @return True if there is a Cell at this position, or False if there is not.
     */
    public boolean isCellSetAt(GridLocation position){
        return cells[position.value] != null;
    }

    public Cell.CellContents contentsOfCell(GridLocation location){
        switch(location){
            case TOP_LEFT:
                return cells[0].getContents();
            case TOP:
                return cells[1].getContents();
            case TOP_RIGHT:
                return cells[2].getContents();
            case LEFT:
                return cells[3].getContents();
            case CENTRE:
                return cells[4].getContents();
            case RIGHT:
                return cells[5].getContents();
            case BOTTOM_LEFT:
                return cells[6].getContents();
            case BOTTOM:
                return cells[7].getContents();
            case BOTTOM_RIGHT:
                return cells[8].getContents();
            default:
                throw new IllegalStateException("An unknown GridLocation was passed to contentsOfCell().");
        }
    }
    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {
            private GridLocation currentPos = null;
            @Override
            public boolean hasNext() {
                return currentPos != GridLocation.BOTTOM_RIGHT;
            }

            @Override
            public Cell next() {
                if(currentPos == null){
                    currentPos = GridLocation.TOP_LEFT;
                    return getCell(GridLocation.TOP_LEFT);
                }
                switch (currentPos){
                    case TOP_LEFT:
                        currentPos = GridLocation.TOP;
                        return getCell(GridLocation.TOP);
                    case TOP:
                        currentPos = GridLocation.TOP_RIGHT;
                        return getCell(GridLocation.TOP_RIGHT);
                    case TOP_RIGHT:
                        currentPos = GridLocation.LEFT;
                        return getCell(GridLocation.LEFT);
                    case LEFT:
                        currentPos = GridLocation.CENTRE;
                        return getCell(GridLocation.CENTRE);
                    case CENTRE:
                        currentPos = GridLocation.RIGHT;
                        return getCell(GridLocation.RIGHT);
                    case RIGHT:
                        currentPos = GridLocation.BOTTOM_LEFT;
                        return getCell(GridLocation.BOTTOM_LEFT);
                    case BOTTOM_LEFT:
                        currentPos = GridLocation.BOTTOM;
                        return getCell(GridLocation.BOTTOM);
                    case BOTTOM:
                        currentPos = GridLocation.BOTTOM_RIGHT;
                        return getCell(GridLocation.BOTTOM_RIGHT);
                    default:
                        throw new NoSuchElementException(ITERATOR_AT_END);
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException(REMOVE_NOT_SUPPORTED_BY_ITERATOR);
            }
        };
    }
}
