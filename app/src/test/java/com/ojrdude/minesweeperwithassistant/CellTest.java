package com.ojrdude.minesweeperwithassistant;

import junit.framework.TestCase;

import java.util.Random;

/**
 * Unit Tests for Cell
**/
public class CellTest extends TestCase {


    public static final String COVERED_CELL_DIDNT_RETURN_UNKNOWN_ON_GET_CONTENTS =
            "Covered cell didn't return UNKNOWN on getContents.";
    public static final String UNCOVERED_CELL_RETURNED_WRONG_VALUE =
            "An uncovered cell didn't return it's actual value on getIntValue";
    public static final String ILLEGAL_STATE_EXCEPTION_WRONG_MESSAGE =
            "The thrown IllegalStateException didn't have the expected message.";
    public static final String ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE =
            "The IllegalArgumentException raised did not have the expected message.";
    private final Random rand = new Random();

    public void testSetAndGetXCoordinateValidValues(){
        Cell cell = new Cell(0,0, Cell.CellContents.ONE);
        assertEquals(0, cell.getXCoordinate());
        cell.setXCoordinate(99);
        assertEquals(99, cell.getXCoordinate());
        cell.setXCoordinate(0);
        assertEquals(0, cell.getXCoordinate());
        cell.setXCoordinate(1);
        assertEquals(1, cell.getXCoordinate());
    }

    public void testSetAndGetYCoordinateValidValues(){
        Cell cell = new Cell(0,99, Cell.CellContents.SIX);
        assertEquals(99, cell.getYCoordinate());
        cell.setYCoordinate(0);
        assertEquals(0, cell.getYCoordinate());
        cell.setYCoordinate(1);
        assertEquals(1, cell.getYCoordinate());
        cell.setYCoordinate(99);
        assertEquals(99, cell.getYCoordinate());
    }

    public void testSetXCoordinateNegativeValueException(){
        Cell cell = new Cell(1,3, Cell.CellContents.MINE);
        int[] negativeValues = {-1, -9, -1000, Integer.MIN_VALUE};

        for (int negativeValue : negativeValues) {
            try {
                cell.setXCoordinate(negativeValue);
                fail("Setting x coordinate to " + negativeValue + " didn't raise expected exception.");
            } catch (IllegalArgumentException e) {
                assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                        Cell.X_COORDINATE_LESS_THAN_ZERO + negativeValue, e.getMessage());
            } catch (Exception e) {
                fail("Unexpected Exception Type thrown when setting xCoord to negative value: " + negativeValue);
            }
        }
    }

    public void testSetYCoordinateNegativeValueException(){
        Cell cell = new Cell(9,231, Cell.CellContents.EIGHT);
        int[] negativeValues = {-1, -99, -3000, Integer.MIN_VALUE};

        for (int negativeValue : negativeValues) {
            try {
                cell.setYCoordinate(negativeValue);
                fail("Setting y coordinate to " + negativeValue + " didn't raise expected exception.");
            } catch (IllegalArgumentException e) {
                assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                        Cell.Y_COORDINATE_LESS_THAN_ZERO + negativeValue, e.getMessage());
            } catch (Exception e) {
                fail("Unexpected Exception Type thrown when setting yCoord to negative value: " + negativeValue);
            }
        }
    }

    public void testConstructorExceptions(){
        int[] negativeValues = {-1, -2, -99, -1000, Integer.MIN_VALUE};
        for (int negativeValue : negativeValues) {
            try {
                new Cell(negativeValue, rand.nextInt(Integer.MAX_VALUE), randomCellContents());
                fail("Passing a negative xCoord of " + negativeValue + " to the Cell Constructor didn't raise the " +
                        "expected exception.");
            } catch (IllegalArgumentException e) {
                assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                        Cell.X_COORDINATE_LESS_THAN_ZERO + negativeValue, e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Unexpected Exception Type thrown when setting xCoord to negative value: " + negativeValue);
            }

            try{
                new Cell(rand.nextInt(Integer.MAX_VALUE), negativeValue, randomCellContents());
                fail("Passing a negative yCoord of " + negativeValue + " to the Cell Constructor didn't raise the "
                + "expected exception");
            }
            catch (IllegalArgumentException e){
                assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                        Cell.Y_COORDINATE_LESS_THAN_ZERO + negativeValue, e.getMessage());
            }
            catch (Exception e){
                e.printStackTrace();
                fail("Unexpected Exception Type thrown when initialising Cell with negative yCoord: " + negativeValue);
            }
        }

        try{
            new Cell(rand.nextInt(Integer.MAX_VALUE), rand.nextInt(Integer.MAX_VALUE), Cell.CellContents.UNKNOWN);
            fail("Initialising a Cell with a contents value of UNKNOWN didn't raise the expected exception.");
        }
        catch (IllegalArgumentException e){
            assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                    Cell.INITIALISE_CELL_UNKNOWN_VALUE, e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
            fail("Unexpected Exception Type thrown when intitialising a Cell with a contents value of UNKNOWN.");
        }

        try{
            new Cell(rand.nextInt(Integer.MAX_VALUE), rand.nextInt(Integer.MAX_VALUE), null);
            fail("Intialising a Cell with a null contents value didn't raise the expected exception.");
        }
        catch(IllegalArgumentException e){
            assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_WRONG_MESSAGE,
                    Cell.NULL_PARAMETER_PASSED_TO_CONSTRUCTOR, e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            fail("Unexpected Exception Type thrown when intitialising a Cell with a contents value of null.");
        }
    }

    public void testEquals(){
        assertTrue(new Cell(0,0, Cell.CellContents.MINE).equals(new Cell(0,0, Cell.CellContents.MINE)));
        assertTrue(new Cell(2001, 999, Cell.CellContents.EIGHT).equals(new Cell(2001, 999, Cell.CellContents.EIGHT)));

        // Check only coordinates, not contents are counted.
        assertTrue(new Cell(22, 23, Cell.CellContents.SEVEN).equals(new Cell(22, 23, Cell.CellContents.ONE)));
        assertTrue(new Cell(8, 8, Cell.CellContents.MINE).equals(new Cell(8, 8, Cell.CellContents.EIGHT)));

        assertFalse(new Cell(0, 0, Cell.CellContents.ZERO).equals(new Cell(1, 2, Cell.CellContents.FIVE)));
        assertFalse(new Cell(999, 998, Cell.CellContents.THREE).equals(new Cell(998, 999, Cell.CellContents.TWO)));

        assertFalse(new Cell(3, 32, Cell.CellContents.SIX).equals(new Cell(4,4, Cell.CellContents.SIX)));
        assertFalse(new Cell(5,55, Cell.CellContents.FIVE).equals(new Cell(5, 2, Cell.CellContents.FIVE)));

        Cell cellX = new Cell(20, 21, Cell.CellContents.ONE);
        //noinspection EqualsWithItself - Intended to test reflexivity of equals method.
        assertTrue(cellX.equals(cellX));
    }

    public void testHashCode(){
        Cell cell1 = new Cell(9, 21, Cell.CellContents.MINE);
        Cell cell2 = new Cell(9, 21, Cell.CellContents.FIVE);

        assertEquals(2903, cell1.hashCode()); // Calculated by hand
        assertEquals(cell1.hashCode(), cell2.hashCode());
    }

    public void testUncoverAndGetContents(){
        Cell cell1 = new Cell(0,0, Cell.CellContents.MINE);
        Cell cell2 = new Cell(99, 21, Cell.CellContents.EIGHT);
        Cell cell3 = new Cell(201, 222, Cell.CellContents.ZERO);
        Cell cell4 = new Cell(Integer.MAX_VALUE, Integer.MAX_VALUE, Cell.CellContents.FIVE);

        assertEquals(COVERED_CELL_DIDNT_RETURN_UNKNOWN_ON_GET_CONTENTS, cell1.getContents(), Cell.CellContents.UNKNOWN);
        assertEquals(COVERED_CELL_DIDNT_RETURN_UNKNOWN_ON_GET_CONTENTS, cell2.getContents(), Cell.CellContents.UNKNOWN);
        assertEquals(COVERED_CELL_DIDNT_RETURN_UNKNOWN_ON_GET_CONTENTS, cell3.getContents(), Cell.CellContents.UNKNOWN);
        assertEquals(COVERED_CELL_DIDNT_RETURN_UNKNOWN_ON_GET_CONTENTS, cell4.getContents(), Cell.CellContents.UNKNOWN);

        cell1.uncover(); cell2.uncover();
        cell3.uncover(); cell4.uncover();

        assertEquals(UNCOVERED_CELL_RETURNED_WRONG_VALUE, cell1.getContents(), Cell.CellContents.MINE);
        assertEquals(UNCOVERED_CELL_RETURNED_WRONG_VALUE, cell2.getContents(), Cell.CellContents.EIGHT);
        assertEquals(UNCOVERED_CELL_RETURNED_WRONG_VALUE, cell3.getContents(), Cell.CellContents.ZERO);
        assertEquals(UNCOVERED_CELL_RETURNED_WRONG_VALUE, cell4.getContents(), Cell.CellContents.FIVE);

        Cell[] cells = {cell1, cell2, cell3, cell4};
        for(Cell cell : cells) {
            try {
                cell.uncover();
                fail("Attempting to uncover an already uncovered cell didn't throw expected exception.");
            } catch (IllegalStateException e) {
                assertEquals(ILLEGAL_STATE_EXCEPTION_WRONG_MESSAGE,
                        Cell.ATTEMPTED_TO_UNCOVER_A_CELL_THAT_IS_ALREADY_UNCOVERED, e.getMessage());
            } catch (Exception e) {
                fail("Attempting to uncover a cell that is already uncovered didn't throw the expected type of exception.");
            }
        }

    }

    public void testToString(){
        Cell cell1 = new Cell(0,0, Cell.CellContents.MINE);
        Cell cell2 = new Cell(9,231, Cell.CellContents.EIGHT);
        Cell cell3 = new Cell(5,33, Cell.CellContents.ONE);
        Cell cell4 = new Cell(2222, 9919, Cell.CellContents.THREE);

        //Covered
        assertEquals("Cell: (0, 0) Contents: UNKNOWN", cell1.toString());
        assertEquals("Cell: (9, 231) Contents: UNKNOWN", cell2.toString());
        assertEquals("Cell: (5, 33) Contents: UNKNOWN", cell3.toString());
        assertEquals("Cell: (2222, 9919) Contents: UNKNOWN", cell4.toString());

        cell1.uncover(); cell2.uncover(); cell3.uncover(); cell4.uncover();

        //Uncovered
        assertEquals("Cell: (0, 0) Contents: MINE", cell1.toString());
        assertEquals("Cell: (9, 231) Contents: 8", cell2.toString());
        assertEquals("Cell: (5, 33) Contents: 1", cell3.toString());
        assertEquals("Cell: (2222, 9919) Contents: 3", cell4.toString());
    }

    /**
     * Tests the flag, removeFlag and isFlagged methods because they are all inherently linked. Does
     * not test exceptions.
     */
    public void testFlagMethodsHappyPath(){
        Cell cell = new Cell(24, 25, Cell.CellContents.FIVE);
        assertFalse(cell.isFlagged());
        cell.flag();
        assertTrue(cell.isFlagged());
        cell.removeFlag();
        assertFalse(cell.isFlagged());
    }

    public void testFlagMethodsExceptionsThrown(){
        Cell cell = new Cell(2, 99, Cell.CellContents.SIX);
        try{
            cell.removeFlag();
            fail("Unflagging a cell that is already unflagged didn't throw expected exception.");
        }
        catch(IllegalStateException e){
            assertEquals(ILLEGAL_STATE_EXCEPTION_WRONG_MESSAGE,
                    Cell.ATTEMPTED_TO_UNFLAG_CELL_ALREADY_UNFLAGGED, e.getMessage());
        }
        catch(Exception e){
            fail("Unexpected exception thrown when trying to unflag already unflagged cell.");
        }

        cell.flag();
        try{
            cell.flag();
            fail("Flagging a cell that is already flag didn't throw expected exception.");
        }
        catch(IllegalStateException e){
            assertEquals(ILLEGAL_STATE_EXCEPTION_WRONG_MESSAGE,
                    Cell.ATTEMPTED_TO_FLAG_CELL_ALREADY_FLAGGED, e.getMessage());
        }
        catch(Exception e){
            fail("Unexpected exception thrown when trying to flag an already flagged cell.");
        }

        cell.uncover();
        try{
            cell.removeFlag();
            fail("Trying to unflag an uncovered cell didn't throw expected exception.");
        }
        catch(IllegalStateException e){
            assertEquals(ILLEGAL_STATE_EXCEPTION_WRONG_MESSAGE,
                    Cell.ATTEMPTED_TO_UNFLAG_UNCOVERED_CELL, e.getMessage());
        }
        catch(Exception e){
            fail("Unexpected exception thrown when trying to unflag uncovered cell.");
        }

        Cell cell2 = new Cell(5,5, Cell.CellContents.THREE);
        cell2.uncover();
        try{
            cell2.flag();
            fail("Trying to flag uncovered cell didn't throw excpected exception.");
        }
        catch(IllegalStateException e){
            assertEquals(ILLEGAL_STATE_EXCEPTION_WRONG_MESSAGE,
                    Cell.ATTEMPTED_TO_FLAG_UNCOVERED_CELL, e.getMessage());
        }
        catch(Exception e){
            fail("Unexpected exception thrown when trying to flag uncovered cell.");
        }
    }

    private Cell.CellContents randomCellContents() {
        final Cell.CellContents[] cellContentsVals = new Cell.CellContents[]{
                Cell.CellContents.MINE,
                Cell.CellContents.ZERO,
                Cell.CellContents.ONE,
                Cell.CellContents.TWO,
                Cell.CellContents.THREE,
                Cell.CellContents.FOUR,
                Cell.CellContents.FIVE,
                Cell.CellContents.SIX,
                Cell.CellContents.SEVEN,
                Cell.CellContents.EIGHT
        };
        return  cellContentsVals[rand.nextInt(9)];
    }
}
