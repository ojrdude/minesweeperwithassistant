package com.ojrdude.minesweeperwithassistant.analyser.exceptions;

import com.ojrdude.minesweeperwithassistant.Cell;

/**
 * An exception to be raised if a number on the gameboard is found to have too many flags around it.
 */
public final class TooManyFlagsException extends RuntimeException {
    public final String CELL_AT_FAULT = "Cell at fault";
    private Cell cellAtFault;

    public TooManyFlagsException(Cell cellAtFault, String message){
        super(message);
        this.cellAtFault = cellAtFault;
    }

    public Cell getCellAtFault(){
        return cellAtFault;
    }
}
