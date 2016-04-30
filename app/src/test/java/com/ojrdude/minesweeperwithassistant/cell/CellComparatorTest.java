package com.ojrdude.minesweeperwithassistant.cell;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Unit tests for CellComparator class
 */
public class CellComparatorTest {

    private CellComparator cc;

    @Before
    public void setUp() throws Exception {
        cc = new CellComparator();
    }

    @Test
    public void testCellOneHigherYCoordReturnsMinusOne(){
        Cell cell1 = new Cell(0,0, CellContents.THREE);
        Cell cell2 = new Cell(0,9, CellContents.ONE);
        Cell cell3 = new Cell(23, 23, CellContents.THREE);
        Cell cell4 = new Cell(4, 24, CellContents.FIVE);

        assertEquals(-1, cc.compare(cell2, cell1));
        assertEquals(-1, cc.compare(cell3, cell2));
        assertEquals(-1, cc.compare(cell4, cell3));
    }

    @Test
    public void testCellOneLowerYCoordReturnsPlusOne(){
        Cell cell1 = new Cell(1, 3, CellContents.TWO);
        Cell cell2 = new Cell(1, 4, CellContents.ONE);
        Cell cell3 = new Cell(55, 55, CellContents.SIX);
        Cell cell4 = new Cell(20, 56, CellContents.FOUR);

        assertEquals(1, cc.compare(cell1, cell2));
        assertEquals(1, cc.compare(cell2, cell3));
        assertEquals(1, cc.compare(cell3, cell4));
    }

    @Test
    public void testCellOneLowerXCoordReturnsMinusOneSameY(){
        Cell cell1 = new Cell(0, 20, CellContents.TWO);
        Cell cell2 = new Cell(3, 20, CellContents.FIVE);
        Cell cell3 = new Cell(4, 333, CellContents.SEVEN);
        Cell cell4 = new Cell(211, 333, CellContents.MINE);
        Cell cell5 = new Cell(1, 1, CellContents.ONE);
        Cell cell6 = new Cell(2, 1, CellContents.EIGHT);

        assertEquals(-1, cc.compare(cell1, cell2));
        assertEquals(-1, cc.compare(cell3, cell4));
        assertEquals(-1, cc.compare(cell5, cell6));
    }

    @Test
    public void testCellOneHigherXCoordReturnsPlusOneSameY(){
        Cell cell1 = new Cell(1, 0, CellContents.SIX);
        Cell cell2 = new Cell(0, 0, CellContents.MINE);
        Cell cell3 = new Cell(101, 999, CellContents.FIVE);
        Cell cell4 = new Cell(20, 999, CellContents.EIGHT);
        Cell cell5 = new Cell(3, 11011, CellContents.FOUR);
        Cell cell6 = new Cell(2, 11011, CellContents.ONE);

        assertEquals(1, cc.compare(cell1, cell2));
        assertEquals(1, cc.compare(cell3, cell4));
        assertEquals(1, cc.compare(cell5, cell6));
    }

    @Test
    public void testSameCoordinatesReturnsZero(){
        Cell cell1 = new Cell(0, 0, CellContents.MINE);
        Cell cell2 = new Cell(0,0, CellContents.ZERO);
        Cell cell3 = new Cell(33, 35, CellContents.FIVE);
        Cell cell4 = new Cell(33, 35, CellContents.FIVE);
        Cell cell5 = new Cell(201, 222, CellContents.THREE);
        Cell cell6 = new Cell(201, 222, CellContents.EIGHT);

        assertEquals(0, cc.compare(cell1, cell2));
        assertEquals(0, cc.compare(cell3, cell4));
        assertEquals(0, cc.compare(cell5, cell6));
    }
}
