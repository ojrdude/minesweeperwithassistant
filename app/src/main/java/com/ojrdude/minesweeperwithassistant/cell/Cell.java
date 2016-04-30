package com.ojrdude.minesweeperwithassistant.cell;

/**
 * The Cell class represents a cell on the Minesweeper game board. The values that a cell
 * can contain (i.e. the number of mines around the cell) are defined by the CellConstants
 * enumerated type.
 */
public class Cell {

    public static final String NULL_PARAMETER_PASSED_TO_CONSTRUCTOR = "Null parameter passed to constructor";
    public static final String ATTEMPTED_TO_UNCOVER_A_CELL_THAT_IS_ALREADY_UNCOVERED = "Attempted to uncover a cell that is already uncovered.";
    public static final String ATTEMPTED_TO_FLAG_UNCOVERED_CELL = "Attempted to flag a cell that is uncovered.";
    public static final String ATTEMPTED_TO_FLAG_CELL_ALREADY_FLAGGED = "Attempted to flag a cell that is already flag.";
    public static final String ATTEMPTED_TO_UNFLAG_UNCOVERED_CELL = "Attempted to unflag a cell that is uncovered.";
    public static final String ATTEMPTED_TO_UNFLAG_CELL_ALREADY_UNFLAGGED = "Attempted to unflag a cell that is already unflagged.";

    public static final String X_COORDINATE_LESS_THAN_ZERO = "Coordinates must be 0 or greater. XCoord: ";
    public static final String Y_COORDINATE_LESS_THAN_ZERO = "Coordinates must be 0 or greater. YCoord: ";
    public static final String INITIALISE_CELL_UNKNOWN_VALUE = "Cannot initialise a Cell with an Unknown value";
    private CellContents contents;
    private boolean uncovered;
    private boolean flagged;
    private int xCoordinate;
    private int yCoordinate;

    /**
     * Constructor for Cell class.
     * @param xCoordinate The X coordinate of this cell on the game board. Must be greater than 0.
     * @param yCoordinate The Y coordinate of this cell on the game board. Must be greater than 0.
     * @param contents What the cell "contains"; either a MINE, or a number which
     *                 counts the number of mines around cell. This parameter CANNOT be
     *                 UNKNOWN.
     * @throws IllegalArgumentException If a coordinate is less than zero is supplied, or if a
     *         "contents" value of UNKNOWN is provided.
     */
    public Cell(int xCoordinate, int yCoordinate, CellContents contents){
        if(contents==CellContents.UNKNOWN){
            throw new IllegalArgumentException(INITIALISE_CELL_UNKNOWN_VALUE);
        }
        if(contents == null){
            throw  new IllegalArgumentException(NULL_PARAMETER_PASSED_TO_CONSTRUCTOR);
        }
        if(xCoordinate < 0) {
            throw new IllegalArgumentException(X_COORDINATE_LESS_THAN_ZERO + xCoordinate);
        }
        if(yCoordinate < 0){
            throw new IllegalArgumentException(Y_COORDINATE_LESS_THAN_ZERO + yCoordinate);
        }
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.contents = contents;
        uncovered = false;
        flagged = false;
    }

    /**
     * Get the the contents of the cell. If the cell is not uncovered, UNKNOWN is returned.
     * @return The contents of the cell if uncovered, UNKNOWN if not.
     */
    public CellContents getContents(){
        if(uncovered){
            return contents;
        }
        else{
            return CellContents.UNKNOWN;
        }
    }

    /**
     * Accessor method for xCoordinate.
     * @return The xCoordinate of this cell.
     */
    public int getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Mutator method for xCoordinate. Sets the xCoordinate to x.
     * @param x The new xCoordinate for the cell, must be 0 or greater.
     * @throws IllegalArgumentException If the coordinate provided is less than 0.
     */
    public void setXCoordinate(int x) {
        if(x < 0){
            throw new IllegalArgumentException(X_COORDINATE_LESS_THAN_ZERO + x);
        }
        xCoordinate = x;
    }

    /**
     * Accessor method for yCoordinate.
     * @return The yCoordinate of this cell.
     */
    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Mutator method for yCoordinate. Sets the yCoordinate to y.
     * @param y The new yCoordinate for the cell, must be 0 or greater.
     * @throws IllegalArgumentException If the coordinate provided is less than 0.
     */
    public void setYCoordinate(int y) {
        if (y<0){
            throw new IllegalArgumentException(Y_COORDINATE_LESS_THAN_ZERO + y);
        }
        yCoordinate = y;
    }

    /**
     * Equals method, returns whether this Cell is equal to another, based on Coordinates;
     *
     * @param obj The object to compare this cell to.
     * @return True if the parameter object is equal to this Cell, false if not.
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Cell)){
            return false;
        }
        Cell objCell = (Cell) obj;
        return this.xCoordinate == objCell.getXCoordinate() &&
                this.yCoordinate == objCell.getYCoordinate();
    }

    /**
     * Generates a hashCode representation of the Cell.
     * @return this Cell's hashCode.
     */
    @Override
    public int hashCode(){
        int result = 23;
        int mult = 11;
        result = mult * result + xCoordinate;
        result = mult * result + yCoordinate;
        return result;
    }

    /**
     * Uncover a cell, making the cell's contents accessible.
     */
    public void uncover(){
        if(uncovered){
            throw new IllegalStateException(ATTEMPTED_TO_UNCOVER_A_CELL_THAT_IS_ALREADY_UNCOVERED);
        }
        uncovered = true;
    }

    /**
     * Mark (flag) a cell as containing a mine.
     */
    public void flag(){
        if(uncovered){
            throw new IllegalStateException(ATTEMPTED_TO_FLAG_UNCOVERED_CELL);
        }
        if(flagged){
            throw new IllegalStateException(ATTEMPTED_TO_FLAG_CELL_ALREADY_FLAGGED);
        }
        flagged = true;
    }

    /**
     * Remove the flag that marks the cell as containing a mine.
     */
    public void removeFlag(){
        if(uncovered){
            throw new IllegalStateException(ATTEMPTED_TO_UNFLAG_UNCOVERED_CELL);
        }
        if(!flagged){
            throw new IllegalStateException(ATTEMPTED_TO_UNFLAG_CELL_ALREADY_UNFLAGGED);
        }
        flagged = false;
    }

    /**
     * Gets whethre or not this cell is flagged, i.e. marked as containing a mine.
     * @return True if the cell is flagged, false if not.
     */
    public boolean isFlagged(){
        return flagged;
    }
    /**
     * Returns a String representation of this Cell.
     * @return The String representation of this cell.
     */
    public String toString(){
        String result =  "Cell: (" + xCoordinate + ", " + yCoordinate + ") Contents: ";
        if(uncovered){
            result+=contents;
        }
        else{
            result+=CellContents.UNKNOWN;
        }
        return  result;
    }
}
