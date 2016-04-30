package com.ojrdude.minesweeperwithassistant.cell;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Unit tests for Three By Three Cell Grid
 */
public class ThreeByThreeCellGridTest {

    private static final String NO_EXCEPTION_THROWN_CONSTRUCTOR = "ThreeByThreeGrid constructor didn't throw expected exception";
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE = "Thrown IllegalArgumentException didn't have expected message.";
    private static final String UNEXPECTED_EXCEPTION_THROWN_CONSTRUCTOR = "Unexpected exception thrown by ThreeByThreeCellGrid constructor.";
    private Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;
    private Cell[] defaultCells;

    @Before
    public void setUp(){
        cell1 = new Cell(0, 2, CellContents.ONE);
        cell2 = new Cell(1, 2, CellContents.ONE);
        cell3 = new Cell(2, 2, CellContents.THREE);
        cell4 = new Cell(0, 1, CellContents.MINE);
        cell5 = new Cell(1, 1, CellContents.FOUR);
        cell6 = new Cell(2, 1, CellContents.EIGHT);
        cell7 = new Cell(0, 0, CellContents.SIX);
        cell8 = new Cell(1, 0, CellContents.FIVE);
        cell9 = new Cell(2, 0, CellContents.ONE);
        defaultCells = new Cell[]{cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9};
    }

    @Test
    public void testArrayConstructor(){
        Cell[] invalidArray1 = {};
        Cell[] invalidArray2 = {cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8};
        Cell[] invalidArray3 = {cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8,
            cell9, new Cell(5, 5, CellContents.MINE)};
        Cell[] validArray = {cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9};
        ThreeByThreeCellGrid grid;
        try{
            new ThreeByThreeCellGrid(invalidArray1);
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
            new ThreeByThreeCellGrid(invalidArray2);
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
            new ThreeByThreeCellGrid(invalidArray3);
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

    @Test
    public void testConstructorAndGetCell(){
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(cell1, cell2, cell3, cell4, cell5,
                cell6, cell7, cell8, cell9);

        assertGridContents(grid);
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void testGetValueOfCell(){
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(defaultCells);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT), CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP), CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT), CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.LEFT), CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.CENTRE), CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.RIGHT), CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT), CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM), CellContents.UNKNOWN);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT), CellContents.UNKNOWN);
        for(Cell cell : grid){
            cell.uncover();
        }
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT), CellContents.ONE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP), CellContents.ONE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT), CellContents.THREE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.LEFT), CellContents.MINE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.CENTRE), CellContents.FOUR);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.RIGHT), CellContents.EIGHT);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT), CellContents.SIX);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM), CellContents.FIVE);
        assertEquals(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT), CellContents.ONE);

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
