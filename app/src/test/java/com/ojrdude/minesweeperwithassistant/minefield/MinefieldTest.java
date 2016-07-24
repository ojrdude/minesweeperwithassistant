package com.ojrdude.minesweeperwithassistant.minefield;

import com.ojrdude.minesweeperwithassistant.cell.Cell;
import com.ojrdude.minesweeperwithassistant.cell.CellContents;
import com.ojrdude.minesweeperwithassistant.cell.ThreeByThreeCellGrid;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit tests for Minefield Class.
 */
public class MinefieldTest {

    private static final String NO_EXCEPTION_THROWN = "No exception thrown";
    private static final String UNEXPECTED_EXCEPTION_THROWN_BY_CONSTRUCTOR = "Unexpected exception thrown by constructor.";
    private Minefield minefield;

    @Before
    public void setUp() {
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
    public void testUncoverCell() {

        CellContents cell22 = minefield.uncoverCell(2, 2);
        assertEquals(CellContents.THREE, cell22);

        CellContents cell00 = minefield.uncoverCell(0, 0);
        assertEquals(CellContents.MINE, cell00);

        CellContents cell13 = minefield.uncoverCell(1, 3);

        assertEquals(CellContents.TWO, cell13);

        CellContents cell33 = minefield.uncoverCell(3, 3);
        assertEquals(CellContents.MINE, cell33);
    }


    @Test
    public void testGetThreeByThreeCellGridCentralGrid() {
        ThreeByThreeCellGrid grid = minefield.getThreeByThreeGrid(2, 1);
        for (Cell cell : grid) {
            assertEquals(CellContents.UNKNOWN, cell.getContents());
            assertFalse(cell.isFlagged());
            cell.uncover();
        }
        Cell topLeft = grid.getCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT);
        Cell top = grid.getCell(ThreeByThreeCellGrid.GridLocation.TOP);
        Cell topRight = grid.getCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT);
        Cell left = grid.getCell(ThreeByThreeCellGrid.GridLocation.LEFT);
        Cell centre = grid.getCell(ThreeByThreeCellGrid.GridLocation.CENTRE);
        Cell right = grid.getCell(ThreeByThreeCellGrid.GridLocation.RIGHT);
        Cell bottomLeft = grid.getCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT);
        Cell bottom = grid.getCell(ThreeByThreeCellGrid.GridLocation.BOTTOM);
        Cell bottomRight = grid.getCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT);

        assertCellCoordinates(1, 0, topLeft);
        assertCellCoordinates(2, 0, top);
        assertCellCoordinates(3, 0, topRight);
        assertCellCoordinates(1, 1, left);
        assertCellCoordinates(2, 1, centre);
        assertCellCoordinates(3, 1, right);
        assertCellCoordinates(1, 2, bottomLeft);
        assertCellCoordinates(2, 2, bottom);
        assertCellCoordinates(3, 2, bottomRight);

        assertEquals(CellContents.TWO, topLeft.getContents());
        assertEquals(CellContents.ONE, top.getContents());
        assertEquals(CellContents.ONE, topRight.getContents());
        assertEquals(CellContents.THREE, left.getContents());
        assertEquals(CellContents.MINE, centre.getContents());
        assertEquals(CellContents.TWO, right.getContents());
        assertEquals(CellContents.THREE, bottomLeft.getContents());
        assertEquals(CellContents.THREE, bottom.getContents());
        assertEquals(CellContents.MINE, bottomRight.getContents());

    }

    @Test
    public void testGetThreeByThreeCellGridBoardEdge() {
        ThreeByThreeCellGrid grid = minefield.getThreeByThreeGrid(1, 3);
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_LEFT));
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP));
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT));
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.LEFT));
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.CENTRE));
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.RIGHT));

        assertFalse(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT));
        assertFalse(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM));
        assertFalse(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT));

        Cell centre = grid.getCell(ThreeByThreeCellGrid.GridLocation.CENTRE);
        assertCellCoordinates(1, 3, centre);
        centre.uncover();
        assertEquals(CellContents.TWO, centre.getContents());
    }

    @Test
    public void testGetThreeByThreeCellGridCorner() {
        ThreeByThreeCellGrid grid = minefield.getThreeByThreeGrid(3, 3);
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_LEFT));
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP));
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.LEFT));
        assertTrue(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.CENTRE));

        assertFalse(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT));
        assertFalse(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.RIGHT));
        assertFalse(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT));
        assertFalse(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM));
        assertFalse(grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT));

        Cell left = grid.getCell(ThreeByThreeCellGrid.GridLocation.LEFT);
        assertCellCoordinates(2, 3, left);
        left.uncover();
        assertEquals(CellContents.TWO, left.getContents());
    }

    /**
     * Tests the unlikely and unusual situation where the minefield is only 2x2 in size.
     * Do the constructor and getThreeByThreeCellGrid method still behave?
     */
    @Test
    public void testGetThreeByThreeGridFromTwoByTwoField() {
        IMinefieldGenerator smallGenerator = new IMinefieldGenerator() {
            @Override
            public boolean[][] generateMinefield() {
                return new boolean[][]{
                        {true, false},
                        {false, true}
                };
            }

            @Override
            public int getWidth() {
                return 2;
            }

            @Override
            public int getHeight() {
                return 2;
            }

            @Override
            public int getNumberOfMines() {
                return 2;
            }
        };
        Minefield smallMinefield = new Minefield(smallGenerator);
        ThreeByThreeCellGrid smallGrid = smallMinefield.getThreeByThreeGrid(0, 1);

        assertTrue(smallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.CENTRE));
        assertTrue(smallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP));
        assertTrue(smallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.RIGHT));
        assertTrue(smallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT));

        assertFalse(smallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_LEFT));
        assertFalse(smallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.LEFT));
        assertFalse(smallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT));
        assertFalse(smallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM));
        assertFalse(smallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT));

        assertCellCoordinates(0, 1, smallGrid.getCell(ThreeByThreeCellGrid.GridLocation.CENTRE));
        assertCellCoordinates(0, 0, smallGrid.getCell(ThreeByThreeCellGrid.GridLocation.TOP));
        assertCellCoordinates(1, 1, smallGrid.getCell(ThreeByThreeCellGrid.GridLocation.RIGHT));
        assertCellCoordinates(1, 0, smallGrid.getCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT));
    }

    /**
     * Tests the unlikely and unusual situation where the minefield is only 1x1 in size.
     * Do the constructor and getThreeByThreeCellGrid method still behave?
     */
    @Test
    public void testGetThreeByThreeGridFromOneByOneField() {
        IMinefieldGenerator vSmallGenerator = new IMinefieldGenerator() {
            @Override
            public boolean[][] generateMinefield() {
                return new boolean[][]{
                        {false}
                };
            }

            @Override
            public int getWidth() {
                return 1;
            }

            @Override
            public int getHeight() {
                return 1;
            }

            @Override
            public int getNumberOfMines() {
                return 0;
            }
        };

        Minefield vSmallMinefield = new Minefield(vSmallGenerator);
        ThreeByThreeCellGrid vSmallGrid = vSmallMinefield.getThreeByThreeGrid(0, 0);

        assertTrue(vSmallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.CENTRE));

        assertFalse(vSmallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_LEFT));
        assertFalse(vSmallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP));
        assertFalse(vSmallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT));
        assertFalse(vSmallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.LEFT));
        assertFalse(vSmallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.RIGHT));
        assertFalse(vSmallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT));
        assertFalse(vSmallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM));
        assertFalse(vSmallGrid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT));

        assertCellCoordinates(0, 0, vSmallGrid.getCell(ThreeByThreeCellGrid.GridLocation.CENTRE));
    }


    @Test
    public void testGetThreeByThreeCellGridExceptions() {
        try {
            minefield.getThreeByThreeGrid(4, 3);
            fail(NO_EXCEPTION_THROWN);
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(Locale.UK, Minefield.COORDINATE_OUT_OF_RANGE, "x", 4),
                    e.getMessage());
        } catch (Exception e) {
            fail(UNEXPECTED_EXCEPTION_THROWN_BY_CONSTRUCTOR);
        }

        try {
            minefield.getThreeByThreeGrid(-1, 2);
            fail(NO_EXCEPTION_THROWN);
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(Locale.UK, Minefield.COORDINATE_OUT_OF_RANGE, "x", -1),
                    e.getMessage());
        } catch (Exception e) {
            fail(UNEXPECTED_EXCEPTION_THROWN_BY_CONSTRUCTOR);
        }

        try {
            minefield.getThreeByThreeGrid(1, 4);
            fail(NO_EXCEPTION_THROWN);
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(Locale.UK, Minefield.COORDINATE_OUT_OF_RANGE, "y", 4),
                    e.getMessage());
        } catch (Exception e) {
            fail(UNEXPECTED_EXCEPTION_THROWN_BY_CONSTRUCTOR);
        }

        try {
            minefield.getThreeByThreeGrid(0, -1);
            fail(NO_EXCEPTION_THROWN);
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(Locale.UK, Minefield.COORDINATE_OUT_OF_RANGE, "y", -1),
                    e.getMessage());
        } catch (Exception e) {
            fail(UNEXPECTED_EXCEPTION_THROWN_BY_CONSTRUCTOR);
        }
    }

    /**
     * Test that the first cell uncovered is never a mine. Uncover a cell that we know
     * does contain a mine and check that the mine is not returned.
     */
    @Test
    public void testUncoverCellFirstMoveNoMines() {
        CellContents contents = minefield.uncoverCell(3, 3);
        assertNotEquals(CellContents.MINE, contents);
    }

    @Test
    public void testUncoverCellExceptions() {
        try {
            minefield.uncoverCell(-1, 0);
        }
        catch (IllegalArgumentException e){
            assertEquals(String.format(Locale.UK, Minefield.COORDINATE_OUT_OF_RANGE, "x", -1), e.getMessage());
        }
        try {
            minefield.uncoverCell(4, 0);
        }
        catch (IllegalArgumentException e){
            assertEquals(String.format(Locale.UK, Minefield.COORDINATE_OUT_OF_RANGE, "x", 4), e.getMessage());
        }
        try {
            minefield.uncoverCell(0, -1);
        }
        catch (IllegalArgumentException e){
            assertEquals(String.format(Locale.UK, Minefield.COORDINATE_OUT_OF_RANGE, "y", -1), e.getMessage());
        }
        try {
            minefield.uncoverCell(0, 4);
        }
        catch (IllegalArgumentException e){
            assertEquals(String.format(Locale.UK, Minefield.COORDINATE_OUT_OF_RANGE, "y", 4), e.getMessage());
        }
    }

    private void assertCellCoordinates(int xCoordinate, int yCoordinate, Cell cell) {
        assertEquals(xCoordinate, cell.getXCoordinate());
        assertEquals(yCoordinate, cell.getYCoordinate());
    }

}
