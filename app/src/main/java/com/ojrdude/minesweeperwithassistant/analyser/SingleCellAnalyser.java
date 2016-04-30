package com.ojrdude.minesweeperwithassistant.analyser;

import com.ojrdude.minesweeperwithassistant.analyser.exceptions.TooManyFlagsException;
import com.ojrdude.minesweeperwithassistant.cell.Cell;
import com.ojrdude.minesweeperwithassistant.cell.CellContents;
import com.ojrdude.minesweeperwithassistant.cell.ThreeByThreeCellGrid;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * The SingleCellAnalyser class performs the simplest and cheapest possible analysis to find safe
 * moves. It looks at ThreeByThreeGrids of Cells and finds safe moves that can be inferred from
 * these.
 */
class SingleCellAnalyser {

    public static final String NULL_PARAMETER = "The parameter passed to the analyser cannot be null";
    public static final String NULL_CENTRAL_CELL = "The central cell in a ThreeByThree Grid cannot be null";
    public static final String TOO_MANY_NULL_CELLS =
            "At least three cells in the grid (not counting the central one) must be non-null";
    public static final String TOO_MANY_FLAGS = "The cell at the centre of the ThreByThreeCellGrid has too many" +
            " flags around it. Number in cell: %d; Number of flags: %d";

    /**
     * Parameterless constuctor for SingleCellAnalyser.
     */
    public SingleCellAnalyser(){
        // Nothing done at instantiation.
    }

    /**
     * Takes a grid of Cells (ThreeByThreeGrid) and returns which cells can be safely uncovered,
     * based on the number in the central cell and the known contents of the other cells.
     * @param grid A ThreeByThreeGrid of Cells to analyse. Must not be null. The central cell must
     *             not be null. At least three other cells must be non-null. The non-null cells must
     *             make a shape that is possible on a square/rectangle board. Any violation of these rules
     *             throws an IllegalArgumentException.
     *             If the central cell is UNKNOWN, then nothing can be inferred and no moves will be
     *             found/returned.
     * @return A Set of all cells that can be safely uncovered based on Single Cell Analysis.
     */
    public Set<Cell> findSafeCells(ThreeByThreeCellGrid grid){
        if(grid == null) {
            throw new IllegalArgumentException(NULL_PARAMETER);
        }
        if(!grid.isCellSetAt(ThreeByThreeCellGrid.GridLocation.CENTRE)){
            throw new IllegalArgumentException(NULL_CENTRAL_CELL);
        }
        if(countNumberOfCellsSet(grid) < 3){
            throw new IllegalArgumentException(TOO_MANY_NULL_CELLS);
        }

        Set<Cell> result = new HashSet<>();
        if(grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.CENTRE) == CellContents.UNKNOWN){
            return result;
        }

        int numberInCentre = grid.contentsOfCell(ThreeByThreeCellGrid.GridLocation.CENTRE).getIntValue();
        int numberOfFlags = countNumberOfFlags(grid);

        if(numberOfFlags >= numberInCentre){
            for(Cell cell : grid){
                if(!cell.isFlagged() && cell.getContents() == CellContents.UNKNOWN) {
                    result.add(cell);
                }
            }
        }

        if(numberOfFlags > numberInCentre){
            throw new TooManyFlagsException(grid.getCell(ThreeByThreeCellGrid.GridLocation.CENTRE),
                    String.format(Locale.UK, TOO_MANY_FLAGS, numberInCentre, numberOfFlags));
        }

        return result;
    }

    private int countNumberOfCellsSet(ThreeByThreeCellGrid grid){
        int total = 0;
        for (Cell cell : grid) {
            if (cell!=grid.getCell(ThreeByThreeCellGrid.GridLocation.CENTRE) && cell!=null){
                total++;
            }
        }
        return total;
    }

    private int countNumberOfFlags(ThreeByThreeCellGrid grid){
        int total = 0;
        for(Cell cell : grid) {
            if(cell.isFlagged()){
                total++;
            }
        }
        return total;
    }
}
