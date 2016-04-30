package com.ojrdude.minesweeperwithassistant.cell;

import java.util.Comparator;

/**
 * The Cell Comparator allows Cells to be compared according to their positions on the
 * game board i.e. their coordinates. This Class assumes that the grid coordinates are measured
 * from an origin at the bottom left of the game board. Because the normal way to read a grid of
 * cells is from top-left to bottom-right, going across, the following comparisons are applied:
 *  - A cell with a HIGHER y coordinate is LESS THAN a cell with a lower one.
 *  - Where two cells have the same y coordinate, the cell with the LOWER x coordinate is LESS THAN
 *  the one with a higher x coordinate.
 *  - This comparator is consistent with equals, i.e. where two cells have the same coordinates,\
 *  they are equal.
 *
 */
class CellComparator implements Comparator<Cell> {
    @Override
    public int compare(Cell left, Cell right) {
        if (left.getYCoordinate() > right.getYCoordinate()){
            return -1;
        }
        else if(left.getYCoordinate() < right.getYCoordinate()){
            return 1;
        }
        else if(left.getXCoordinate() < right.getXCoordinate()){
            return -1;
        }
        else if(left.getXCoordinate() > right.getXCoordinate()){
            return 1;
        }
        return 0;
    }
}
