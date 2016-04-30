package com.ojrdude.minesweeperwithassistant;

import java.util.Random;

/*
 * Class that represents a minefield. Its size and number of mines are
 * configurable.
 */
public class Minefield {

    private int width, height, numberOfMines;
    private Cell[][] field;

    public Minefield(int width, int height, int numberOfMines){
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;

        boolean[][] booleanFieldRepresentation = generateMinefield(width, height, numberOfMines);
        field = new Cell[width][height];
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                if(booleanFieldRepresentation[i][j]){
                    field[i][j] = new Cell(i, j, Cell.CellContents.MINE);
                }
                else{
                    Cell.CellContents
                            cellContents = calculateCellContents(i, j, booleanFieldRepresentation);
                    field[i][j] = new Cell(i, j, cellContents);
                }
            }
        }
    }

    private boolean[][] generateMinefield(int width, int height, int numberOfMines){
        boolean[][] field = new boolean[width][height];
        Random rand = new Random();
        for (int i = 0; i < numberOfMines; i++) {
            int randomWidth = rand.nextInt(width);
            int randomHeight = rand.nextInt(height);
            boolean minePlaced = false;
            while(!minePlaced){
                if (!field[randomWidth][randomHeight]){
                    field[randomWidth][randomHeight] = true;
                    minePlaced = true;
                }
                else{ // Move on to next cell.
                    if(randomWidth < width){
                        randomWidth++;
                    }
                    else if(randomHeight < height){
                        randomWidth =0;
                        randomHeight++;
                    }
                    else{
                        randomWidth = 0;
                        randomHeight = 0;
                    }
                }
            }
        }
        return field;
    }

    public void regenerateMinefield(){}{
        generateMinefield(width, height, numberOfMines);
    }


    private Cell.CellContents calculateCellContents(int cellXCoord, int cellYCoord, boolean[][] fieldRepresentation){
        int numberOfAdjMines = 0;
        int bottomXCoord = cellXCoord > 0 ?  cellXCoord - 1 : 0;
        int bottomYCoord = cellYCoord > 0 ? cellYCoord - 1 : 0;
        int topXCoord = cellXCoord < width ? cellXCoord + 1 : width;
        int topYCoord = cellYCoord < height ? cellYCoord + 1 : height;

        for(int i=bottomXCoord; i < topXCoord; i++ ){
            for(int j=bottomYCoord; j < topYCoord; j++) {
                if (fieldRepresentation[i][j] && !(i == cellXCoord && j == cellYCoord)) {
                    numberOfAdjMines++;
                }
            }
        }
        Cell.CellContents contents = Cell.CellContents.UNKNOWN;
            switch (numberOfAdjMines){
                case 0: contents =  Cell.CellContents.ZERO;
                    break;
                case 1: contents =  Cell.CellContents.ONE;
                    break;
                case 2: contents =  Cell.CellContents.TWO;
                    break;
                case 3: contents = Cell.CellContents.THREE;
                    break;
                case 4: contents =  Cell.CellContents.FOUR;
                    break;
                case 5: contents =  Cell.CellContents.FIVE;
                    break;
                case 6: contents = Cell.CellContents.SIX;
                    break;
                case 7: contents = Cell.CellContents.SEVEN;
                    break;
                case 8: contents = Cell.CellContents.EIGHT;
                    break;
                default: new RuntimeException("calculateCellContents counted more than 8 mines adjacent to a cell.");
        }
        return contents;
    }

}
