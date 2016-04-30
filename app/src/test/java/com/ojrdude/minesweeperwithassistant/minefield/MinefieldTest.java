package com.ojrdude.minesweeperwithassistant.minefield;

import com.ojrdude.minesweeperwithassistant.cell.Cell;
import com.ojrdude.minesweeperwithassistant.cell.CellContents;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Unit tests for Minefield Class.
 */
public class MinefieldTest {

    private Minefield minefield;

    @Before
    public void setUp(){
        IMinefieldGenerator generator = new IMinefieldGenerator() {
            @Override
            /**
             * Represents x o o o
             *            o o x o
             *            x o o x
             *            x o o x
             * Where x is mines.
             */
            public boolean[][] generateMinefield() {
                return new boolean[][]{
                        {true, false, true, true},
                        {false, false, false, false},
                        {false, true, false, false},
                        {false, false, true, true}
                };
            }

            @Override
            public int getWidth() {
                return 4;
            }

            @Override
            public int getHeight() {
                return 4;
            }

            @Override
            public int getNumberOfMines() {
                return 6;
            }
        };

        minefield = new Minefield(generator);
    }

    @Test
    public void testGetCellAt(){
        Cell cell00 = minefield.getCellAtCoordinates(0, 0);
        assertEquals(0, cell00.getXCoordinate());
        assertEquals(0, cell00.getYCoordinate());
        assertFalse(cell00.isFlagged());
        cell00.uncover();
        assertEquals(CellContents.MINE, cell00.getContents());

        Cell cell22 = minefield.getCellAtCoordinates(2, 2);
        assertEquals(2, cell22.getXCoordinate());
        assertEquals(2, cell22.getYCoordinate());
        assertFalse(cell22.isFlagged());
        cell22.uncover();
        assertEquals(CellContents.THREE, cell22.getContents());

        Cell cell13 = minefield.getCellAtCoordinates(1, 3);
        assertEquals(1, cell13.getXCoordinate());
        assertEquals(3, cell13.getYCoordinate());
        assertFalse(cell13.isFlagged());
        cell13.uncover();
        assertEquals(CellContents.TWO, cell13.getContents());

        Cell cell33 = minefield.getCellAtCoordinates(3, 3);
        assertEquals(3, cell33.getXCoordinate());
        assertEquals(3, cell33.getYCoordinate());
        assertFalse(cell33.isFlagged());
        cell33.uncover();
        assertEquals(CellContents.MINE, cell33.getContents());
    }


}
