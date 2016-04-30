package com.ojrdude.minesweeperwithassistant.cell;

/**
 * Enumerated type that represents the allowed values
 * of a cell (i.e. the number of mines known to surround the cell).
 * MINE represents a cell that itself contains a mine, and UNKNOWN represents
 * a covered cell.
 */
public enum CellContents {
    MINE(-1),
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    UNKNOWN(9);

    private static final String CELL_VALUE_OUT_OF_RANGE = "The parameter 'Value' must be between -1 and 9 inclusive. Illegal Value: ";
    private int intValue;

    /**
     * Constructor for CellContents, sets the value to the parameter given.
     *
     * @param intValue The numerical definition of the Cell value. The allowed
     *                 values are -1 to 9 inclusive. -1 symbolises a mine and 9
     *                 symbolises unknown (i.e. a covered cell). All other numbers
     *                 are the number that is displayed in the cell.
     */
    CellContents(int intValue) {
        if (intValue > 9 || intValue < -1) {
            throw new IllegalArgumentException(CELL_VALUE_OUT_OF_RANGE + intValue);
        }
        this.intValue = intValue;
    }

    /**
     * Accessor method for CellContents intValue.
     *
     * @return The intValue of the Cell's contents
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * Returns a String representation of the CellContents.
     *
     * @return A String a representation of the CellContents.
     */
    public String toString() {
        switch (intValue) {
            case -1:
                return "MINE";
            case 9:
                return "UNKNOWN";
            default:
                return String.valueOf(intValue);
        }
    }

    public static CellContents getCellContentsForValue(int value) {
        switch (value) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return EIGHT;
            default:
                throw new RuntimeException(CELL_VALUE_OUT_OF_RANGE);
        }

    }
}
