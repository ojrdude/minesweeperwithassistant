package com.ojrdude.minesweeperwithassistant;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Unit tests for Three By Three Cell Grid
 */
public class ThreeByThreeCellGridTest extends TestCase {

    public static final String NO_EXCEPTION_THROWN_CONSTRUCTOR = "ThreeByThreeGrid constructor didn't throw expected exception";
    public static final String ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE = "Thrown IllegalArgumentException didn't have expected message.";
    public static final String UNEXPECTED_EXCEPTION_THROWN_CONSTRUCTOR = "Unexpected exception thrown by ThreeByThreeCellGrid constructor.";
    private Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;
    private Cell[] defaultCells;
    public void setUp(){
        cell1 = new Cell(0, 2, Cell.CellContents.ONE);
        cell2 = new Cell(1, 2, Cell.CellContents.ONE);
        cell3 = new Cell(2, 2, Cell.CellContents.THREE);
        cell4 = new Cell(0, 1, Cell.CellContents.MINE);
        cell5 = new Cell(1, 1, Cell.CellContents.FOUR);
        cell6 = new Cell(2, 1, Cell.CellContents.EIGHT);
        cell7 = new Cell(0, 0, Cell.CellContents.SIX);
        cell8 = new Cell(1, 0, Cell.CellContents.FIVE);
        cell9 = new Cell(2, 0, Cell.CellContents.ONE);
        defaultCells = new Cell[]{cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9};
    }

    public void testArrayConstructor(){
        Cell[] invalidArray1 = {};
        Cell[] invalidArray2 = {cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8};
        Cell[] invalidArray3 = {cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8,
            cell9, new Cell(5, 5, Cell.CellContents.MINE)};
        Cell[] validArray = {cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9};
        ThreeByThreeCellGrid grid;
        try{
            //noinspection UnusedAssignment Testing Constructor throws exception only
            grid = new ThreeByThreeCellGrid(invalidArray1);
            fail(NO_EXCEPTION_THROWN_CONSTRUCTOR);
        }
        catch(IllegalArgumentException e){
            assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                    ThreeByThreeCellGrid.ARRAY_NOT_LENGTH_9, e.getMessage());
        }
        catch(Exception e){
            fail(UNEXPECTED_EXCEPTION_THROWN_CONSTRUCTOR);
        }

        try{
            //noinspection UnusedAssignment Testing Constructor throws exception only
            grid = new ThreeByThreeCellGrid(invalidArray2);
            fail(NO_EXCEPTION_THROWN_CONSTRUCTOR);
        }
        catch(IllegalArgumentException e){
            assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                    ThreeByThreeCellGrid.ARRAY_NOT_LENGTH_9, e.getMessage());
        }
        catch(Exception e){
            fail(UNEXPECTED_EXCEPTION_THROWN_CONSTRUCTOR);
        }

        try{
            //noinspection UnusedAssignment Testing Constructor throws exception only
            grid = new ThreeByThreeCellGrid(invalidArray3);
            fail(NO_EXCEPTION_THROWN_CONSTRUCTOR);
        }
        catch(IllegalArgumentException e){
            assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                    ThreeByThreeCellGrid.ARRAY_NOT_LENGTH_9, e.getMessage());
        }
        catch(Exception e){
            fail(UNEXPECTED_EXCEPTION_THROWN_CONSTRUCTOR);
        }

        grid = new ThreeByThreeCellGrid(validArray);
        assertCellsSet(true, grid);
    }

    public void testConstructorAndGetCell(){
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(cell1, cell2, cell3, cell4, cell5,
                cell6, cell7, cell8, cell9);

        assertGridContents(grid);
    }

    public void testSetAndGetCell(){
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid();

        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT, cell1);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP, cell2);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT, cell3);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.LEFT, cell4);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.CENTRE, cell5);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.RIGHT, cell6);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT, cell7);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM, cell8);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT, cell9);

        assertGridContents(grid);
    }

    public void testIsCellSetAt(){
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid();
        assertCellsSet(false, grid);

        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT, cell1);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP, cell2);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT, cell3);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.LEFT, cell4);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.CENTRE, cell5);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.RIGHT, cell6);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT, cell7);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM, cell8);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT, cell9);

        assertCellsSet(true, grid);
    }

    public void testGridIterator(){
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid();
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT, cell1);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP, cell2);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT, cell3);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.LEFT, cell4);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.CENTRE, cell5);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.RIGHT, cell6);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT, cell7);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM, cell8);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT, cell9);

        Iterator iter = grid.iterator();
        assertTrue(iter.hasNext());
        assertEquals(cell1, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(cell2, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(cell3, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(cell4, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(cell5, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(cell6, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(cell7, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(cell8, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(cell9, iter.next());

        assertFalse(iter.hasNext());
        try{
            iter.next();
            fail("Iterator didn't throw NoSuchElementException when expected.");
        }
        catch (NoSuchElementException e){
            assertEquals("The NoSuchElementException thrown by the iterator did not have the expected message.",
                    ThreeByThreeCellGrid.ITERATOR_AT_END, e.getMessage());
        }
        catch(Exception e){
            fail("Iterator threw an unexpected exception.");
        }
    }

    public void testGetValueOfCell(){
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(defaultCells);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT), Cell.CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP), Cell.CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT), Cell.CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.LEFT), Cell.CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.CENTRE), Cell.CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.RIGHT), Cell.CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT), Cell.CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM), Cell.CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT), Cell.CellContents.UNKNOWN);
        for(Cell cell : grid){
            cell.uncover();
        }
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT), Cell.CellContents.ONE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP), Cell.CellContents.ONE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT), Cell.CellContents.THREE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.LEFT), Cell.CellContents.MINE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.CENTRE), Cell.CellContents.FOUR);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.RIGHT), Cell.CellContents.EIGHT);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT), Cell.CellContents.SIX);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM), Cell.CellContents.FIVE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT), Cell.CellContents.ONE);

    }
    private void assertGridContents(ThreeByThreeCellGrid grid) {
        assertEquals(cell1, grid.getCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT));
        assertEquals(cell2, grid.getCell(ThreeByThreeCellGrid.GridLocation.TOP));
        assertEquals(cell3, grid.getCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT));
        assertEquals(cell4, grid.getCell(ThreeByThreeCellGrid.GridLocation.LEFT));
        assertEquals(cell5, grid.getCell(ThreeByThreeCellGrid.GridLocation.CENTRE));
        assertEquals(cell6, grid.getCell(ThreeByThreeCellGrid.GridLocation.RIGHT));
        assertEquals(cell7, grid.getCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT));
        assertEquals(cell8, grid.getCell(ThreeByThreeCellGrid.GridLocation.BOTTOM));
        assertEquals(cell9, grid.getCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT));
    }

    private void assertCellsSet(boolean set, ThreeByThreeCellGrid grid){
        assertEquals(set, grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_LEFT));
        assertEquals(set, grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP));
        assertEquals(set, grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT));
        assertEquals(set, grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.LEFT));
        assertEquals(set, grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.CENTRE));
        assertEquals(set, grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.RIGHT));
        assertEquals(set, grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT));
        assertEquals(set, grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM));
        assertEquals(set, grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT));
    }
}
