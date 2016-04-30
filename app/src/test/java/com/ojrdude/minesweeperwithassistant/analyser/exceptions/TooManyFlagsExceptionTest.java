package com.ojrdude.minesweeperwithassistant.analyser.exceptions;

import com.ojrdude.minesweeperwithassistant.cell.Cell;
import com.ojrdude.minesweeperwithassistant.cell.CellContents;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Unit tests for TooManyFlagsException
 */
public class TooManyFlagsExceptionTest {

    @Test
    public void testConstructorAndGetCellAtFault(){
        Cell cellAtFault = new Cell(23, 24, CellContents.TWO);
        TooManyFlagsException exception = new TooManyFlagsException(cellAtFault, "A message");
        assertEquals(cellAtFault, exception.getCellAtFault());
        assertEquals("A message", exception.getMessage());
    }
}
