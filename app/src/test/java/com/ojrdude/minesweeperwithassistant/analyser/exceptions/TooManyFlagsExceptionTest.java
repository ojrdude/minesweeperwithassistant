package com.ojrdude.minesweeperwithassistant.analyser.exceptions;

import com.ojrdude.minesweeperwithassistant.Cell;

import junit.framework.TestCase;

/**
 * Unit tests for TooManyFlagsException
 */
public class TooManyFlagsExceptionTest extends TestCase {

    public void testConstructorAndGetCellAtFault(){
        Cell cellAtFault = new Cell(23, 24, Cell.CellContents.TWO);
        TooManyFlagsException exception = new TooManyFlagsException(cellAtFault, "A message");
        assertEquals(cellAtFault, exception.getCellAtFault());
        assertEquals("A message", exception.getMessage());
    }
}
