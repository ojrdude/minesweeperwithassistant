package com.ojrdude.minesweeperwithassistant.analyser;

import com.ojrdude.minesweeperwithassistant.analyser.exceptions.TooManyFlagsException;
import com.ojrdude.minesweeperwithassistant.cell.Cell;
import com.ojrdude.minesweeperwithassistant.cell.CellContents;
import com.ojrdude.minesweeperwithassistant.cell.ThreeByThreeCellGrid;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit Test for SingleCellAnalyser
 */
public class SingleCellAnalyserTest{
    private static final String FIND_SAFE_CELLS_NO_ILLEGAL_ARG_EXCEPTION = "findSafeCells did not throw expected IllegalArgumentException";
    private static final String FIND_SAFE_CELLS_UNEXPECTED_EXCEPTION_THROWN = "Unexpected Exception thrown by findSafeCells.";
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE = "IllegalArgumentException had different message to that expected";
    private SingleCellAnalyser sca;

    @Before
    public void setUp(){
        sca = new SingleCellAnalyser();
    }

    @Test
    public void testNullGridThrowsException(){
        try{
            sca.findSafeCells(null);
            fail(FIND_SAFE_CELLS_NO_ILLEGAL_ARG_EXCEPTION);
        }
        catch (IllegalArgumentException e){
            assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                    SingleCellAnalyser.NULL_PARAMETER, e.getMessage());
        }
        catch(Exception e){
            fail(FIND_SAFE_CELLS_UNEXPECTED_EXCEPTION_THROWN);
        }
    }

    @Test
    public void testTooManyNullCellsThrowsException(){
        Cell[] cells = {
                new Cell(8, 8, CellContents.MINE),
                new Cell(9, 8, CellContents.SIX),
                new Cell(10, 8, CellContents.SEVEN),
                new Cell(9, 7, CellContents.EIGHT),
        };
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(null,null,null,
                null,cells[3],null,
                null,null,null);

        assertFindSafeCellsIllegalArgExceptionThrown(grid, SingleCellAnalyser.TOO_MANY_NULL_CELLS);

        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT, cells[0]);
        assertFindSafeCellsIllegalArgExceptionThrown(grid, SingleCellAnalyser.TOO_MANY_NULL_CELLS);

        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP, cells[1]);
        assertFindSafeCellsIllegalArgExceptionThrown(grid, SingleCellAnalyser.TOO_MANY_NULL_CELLS);

        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT, cells[2]);
    }

    @Test
    public void testCentralCellNullThrowsException(){
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid();
        assertFindSafeCellsIllegalArgExceptionThrown(grid, SingleCellAnalyser.NULL_CENTRAL_CELL);

        Cell[] cells = {
                new Cell(9, 21, CellContents.MINE),
                new Cell(10, 21, CellContents.ONE),
                new Cell(11, 21, CellContents.FIVE),
                new Cell(9, 20, CellContents.ONE),
                new Cell(11, 20, CellContents.ZERO),
                new Cell(9, 19, CellContents.ZERO),
                new Cell(10, 19, CellContents.TWO),
                new Cell(11, 19, CellContents.FOUR)
        };
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_LEFT, cells[0]);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP, cells[1]);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.TOP_RIGHT, cells[2]);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.LEFT, cells[3]);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.RIGHT, cells[4]);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_LEFT, cells[5]);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM, cells[6]);
        grid.setCell(ThreeByThreeCellGrid.GridLocation.BOTTOM_RIGHT, cells[7]);

        assertFindSafeCellsIllegalArgExceptionThrown(grid, SingleCellAnalyser.NULL_CENTRAL_CELL);
    }

    @Test
    public void testCoveredCentralCellReturnsEmptySet(){
        Cell[] cells = {
                new Cell(37,99, CellContents.ONE),
                new Cell(38, 99, CellContents.MINE),
                new Cell(39, 99, CellContents.ONE),
                new Cell(37, 98, CellContents.ONE),
                new Cell(38, 98, CellContents.ONE),
                new Cell(39, 98, CellContents.ONE),
                new Cell(37, 97, CellContents.ZERO),
                new Cell(38, 97, CellContents.ZERO),
                new Cell(39, 97, CellContents.ZERO)
        };
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(
                cells[0], cells[1], cells[2],
                cells[3], cells[4], cells[5],
                cells[6], cells[7], cells[8]);

        assertEquals(new HashSet<Cell>(), sca.findSafeCells(grid));
    }

    /**
     * Test the situation where the central cell contains a 1, there is a flagged mine,
     * and all other cells are covered. The analyser should return all the covered cells
     * in a set as safe.
     */
    @Test
    public void testOneMineOneCellAllOthersSafe(){
        Cell[] cells = {
                new Cell(0, 2, CellContents.ONE),
                new Cell(1, 2, CellContents.ONE),
                new Cell(2, 2, CellContents.TWO),
                new Cell(0, 1, CellContents.MINE),
                new Cell(1, 1, CellContents.ONE),
                new Cell(2, 1, CellContents.ZERO),
                new Cell(0, 0, CellContents.ONE),
                new Cell(1, 0, CellContents.ONE),
                new Cell(2, 0, CellContents.ZERO)
        };
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(cells);

        grid.getCell(ThreeByThreeCellGrid.GridLocation.CENTRE).uncover();
        grid.getCell(ThreeByThreeCellGrid.GridLocation.LEFT).flag();

        Set <Cell> expectedSafeCells = new HashSet<>();
        expectedSafeCells.add(cells[0]);
        expectedSafeCells.add(cells[1]);
        expectedSafeCells.add(cells[2]);
        expectedSafeCells.add(cells[5]);
        expectedSafeCells.add(cells[6]);
        expectedSafeCells.add(cells[7]);
        expectedSafeCells.add(cells[8]);
        assertEquals(expectedSafeCells, sca.findSafeCells(grid));
    }

    /**
     * Test the situation where there are 7 mines, all flagged, and the central
     * cell contains a 7. This cell only should be returned in the safe cell set by the analyser.
     */
    @Test
    public void testSevenMinesOneSafe(){
        Cell[] cells = {
                new Cell(29, 1001, CellContents.MINE),
                new Cell(30, 1001, CellContents.MINE),
                new Cell(31, 1001, CellContents.MINE),
                new Cell(29, 1000, CellContents.MINE),
                new Cell(30, 1000, CellContents.SEVEN),
                new Cell(31, 1000, CellContents.TWO),
                new Cell(29, 999, CellContents.MINE),
                new Cell(30, 999, CellContents.MINE),
                new Cell(31, 999, CellContents.MINE),
        };
        cells[0].flag();
        cells[1].flag();
        cells[2].flag();
        cells[3].flag();
        cells[4].uncover();
        cells[6].flag();
        cells[7].flag();
        cells[8].flag();
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(cells);
        Set<Cell> expectedResult = new HashSet<>();
        expectedResult.add(cells[5]);

        assertEquals(expectedResult, sca.findSafeCells(grid));
    }

    /**
     * Test that when an eight is in the central cell, the safe cell set is empty regardless
     * of the number of flags.
     */
    @Test
    public void testEightMinesNoneSafe(){
        Cell[] cells = {
                new Cell(55, 10, CellContents.MINE),
                new Cell(56, 10, CellContents.MINE),
                new Cell(57, 10, CellContents.MINE),
                new Cell(55, 9, CellContents.MINE),
                new Cell(56, 9, CellContents.EIGHT),
                new Cell(57, 9, CellContents.MINE),
                new Cell(55, 8, CellContents.MINE),
                new Cell(56, 8, CellContents.MINE),
                new Cell(57, 8, CellContents.MINE)
        };
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(cells);

        Set<Cell> expectedResult = new HashSet<>();
        assertEquals(expectedResult, sca.findSafeCells(grid));
        for(int i=0; i<cells.length; i++){
            if(i==4) continue; // Don't flag centre cell!
            cells[i].flag();
            assertEquals(expectedResult, sca.findSafeCells(grid));
        }
    }

    /**
     * Test the situation where there is a number 4 in the centre cell. For 0, 1, 2 or 3 flags
     * the safe cell set should be empty. When there are 4 flags, the other 4 cells should be returned
     * as safe.
     */
    @Test
    public void testFourMinesZeroToFourFlags(){
        Cell[] cells = {
                new Cell(200, 200, CellContents.MINE),
                new Cell(201, 200, CellContents.MINE),
                new Cell(202, 200, CellContents.FIVE),
                new Cell(200, 199, CellContents.MINE),
                new Cell(201, 199, CellContents.FOUR),
                new Cell(202, 199, CellContents.THREE),
                new Cell(200, 198, CellContents.TWO),
                new Cell(201, 198, CellContents.TWO),
                new Cell(202, 198, CellContents.MINE)
        };
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(cells);
        Set<Cell> emptySet = new HashSet<>();
        Set<Cell> safeCells = new HashSet<>();

        cells[4].uncover();

        safeCells.add(cells[2]);
        safeCells.add(cells[5]);
        safeCells.add(cells[6]);
        safeCells.add(cells[7]);

        assertEquals(emptySet, sca.findSafeCells(grid));
        cells[0].flag();
        assertEquals(emptySet, sca.findSafeCells(grid));
        cells[1].flag();
        assertEquals(emptySet, sca.findSafeCells(grid));
        cells[3].flag();
        assertEquals(emptySet, sca.findSafeCells(grid));
        cells[8].flag();
        assertEquals(safeCells, sca.findSafeCells(grid));
    }

    @Test
    public void testTooManyFlagsRaisesException(){
        Cell[] cells = {
                new Cell(4, 24, CellContents.ONE),
                new Cell(5, 24, CellContents.TWO),
                new Cell(6, 24, CellContents.MINE),
                new Cell(4, 23, CellContents.TWO),
                new Cell(5, 23, CellContents.TWO),
                new Cell(6, 23, CellContents.MINE),
                new Cell(4, 22, CellContents.ONE),
                new Cell(5, 22, CellContents.THREE),
                new Cell(6, 22, CellContents.TWO)
        };
        cells[4].uncover();
        cells[0].flag();
        cells[2].flag();
        cells[5].flag();
        ThreeByThreeCellGrid grid = new ThreeByThreeCellGrid(cells);

        try{
            sca.findSafeCells(grid);
            fail("findSafeCells did not throw expected TooManyFlagsException");
        }
        catch(TooManyFlagsException e){
            assertEquals("TooManyFlagsException did not have the expected message.",
                    String.format(Locale.UK, SingleCellAnalyser.TOO_MANY_FLAGS, 2, 3), e.getMessage());
            assertEquals(cells[4], e.getCellAtFault());
        }
        catch(Exception e){
            fail(FIND_SAFE_CELLS_UNEXPECTED_EXCEPTION_THROWN);
        }
    }

    private void assertFindSafeCellsIllegalArgExceptionThrown(ThreeByThreeCellGrid grid, String expectedMessage) {
        try{
            sca.findSafeCells(grid);
            fail(FIND_SAFE_CELLS_NO_ILLEGAL_ARG_EXCEPTION);
        }
        catch (IllegalArgumentException e){
            assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                    expectedMessage, e.getMessage());
        }
        catch(Exception e){
            fail(FIND_SAFE_CELLS_UNEXPECTED_EXCEPTION_THROWN);
        }
    }
}
