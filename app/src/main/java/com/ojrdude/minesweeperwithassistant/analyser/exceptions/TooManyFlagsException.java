package com.ojrdude.minesweeperwithassistant.analyser.exceptions;

import com.ojrdude.minesweeperwithassistant.cell.Cell;

/**
 * An exception to be raised if a number on the gameboard is found to have too many flags around it.
 */
public final class TooManyFlagsException extends RuntimeException {
    private final Cell cellAtFault;

    /**
     * Constructor for TooManyFlagsException.
     *
     * @param cellAtFault The cell which had too many flags around it.
     * @param message The message to construct the exception with.
     */
    public TooManyFlagsException(Cell cellAtFault, String message){
        super(message);
        this.cellAtFault = cellAtFault;
    }

    /**
     * Get the cell which had too many flags around it and caused the Exception to be thrown.
     * @return The Cell that had too many flags around it.
     */
    public Cell getCellAtFault(){
        return cellAtFault;
    }
}
