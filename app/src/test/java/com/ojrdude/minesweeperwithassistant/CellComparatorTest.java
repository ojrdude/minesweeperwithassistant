package com.ojrdude.minesweeperwithassistant;

import junit.framework.TestCase;

/**
 * Unit tests for CellComparator class
 */
public class CellComparatorTest extends TestCase {

    CellComparator cc;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        cc = new CellComparator();
    }

    public void testCellOneHigherYCoordReturnsMinusOne(){
        Cell cell1 = new Cell(0,0, Cell.CellContents.THREE);
        Cell cell2 = new Cell(0,9, Cell.CellContents.ONE);
        Cell cell3 = new Cell(23, 23, Cell.CellContents.THREE);
        Cell cell4 = new Cell(4, 24, Cell.CellContents.FIVE);

        assertEquals(-1, cc.compare(cell2, cell1));
        assertEquals(-1, cc.compare(cell3, cell2));
        assertEquals(-1, cc.compare(cell4, cell3));
    }

    public void testCellOneLowerYCoordReturnsPlusOne(){
        Cell cell1 = new Cell(1, 3, Cell.CellContents.TWO);
        Cell cell2 = new Cell(1, 4, Cell.CellContents.ONE);
        Cell cell3 = new Cell(55, 55, Cell.CellContents.SIX);
        Cell cell4 = new Cell(20, 56, Cell.CellContents.FOUR);

        assertEquals(1, cc.compare(cell1, cell2));
        assertEquals(1, cc.compare(cell2, cell3));
        assertEquals(1, cc.compare(cell3, cell4));
    }

    public void testCellOneLowerXCoordReturnsMinusOneSameY(){
        Cell cell1 = new Cell(0, 20, Cell.CellContents.TWO);
        Cell cell2 = new Cell(3, 20, Cell.CellContents.FIVE);
        Cell cell3 = new Cell(4, 333, Cell.CellContents.SEVEN);
        Cell cell4 = new Cell(211, 333, Cell.CellContents.MINE);
        Cell cell5 = new Cell(1, 1, Cell.CellContents.ONE);
        Cell cell6 = new Cell(2, 1, Cell.CellContents.EIGHT);

        assertEquals(-1, cc.compare(cell1, cell2));
        assertEquals(-1, cc.compare(cell3, cell4));
        assertEquals(-1, cc.compare(cell5, cell6));
    }

    public void testCellOneHigherXCoordReturnsPlusOneSameY(){
        Cell cell1 = new Cell(1, 0, Cell.CellContents.SIX);
        Cell cell2 = new Cell(0, 0, Cell.CellContents.MINE);
        Cell cell3 = new Cell(101, 999, Cell.CellContents.FIVE);
        Cell cell4 = new Cell(20, 999, Cell.CellContents.EIGHT);
        Cell cell5 = new Cell(3, 11011, Cell.CellContents.FOUR);
        Cell cell6 = new Cell(2, 11011, Cell.CellContents.ONE);

        assertEquals(1, cc.compare(cell1, cell2));
        assertEquals(1, cc.compare(cell3, cell4));
        assertEquals(1, cc.compare(cell5, cell6));
    }

    public void testSameCoordinatesReturnsZero(){
        Cell cell1 = new Cell(0, 0, Cell.CellContents.MINE);
        Cell cell2 = new Cell(0,0, Cell.CellContents.ZERO);
        Cell cell3 = new Cell(33, 35, Cell.CellContents.FIVE);
        Cell cell4 = new Cell(33, 35, Cell.CellContents.FIVE);
        Cell cell5 = new Cell(201, 222, Cell.CellContents.THREE);
        Cell cell6 = new Cell(201, 222, Cell.CellContents.EIGHT);

        assertEquals(0, cc.compare(cell1, cell2));
        assertEquals(0, cc.compare(cell3, cell4));
        assertEquals(0, cc.compare(cell5, cell6));
    }
}
