package com.ojrdude.minesweeperwithassistant.minefield;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Unit tests for MinefieldGenerator
 */
public class MinefieldGeneratorTest {

    private static final String NO_EXCEPTION = "No exception thrown by minefieldGenerator.";
    private static final String UNEXPECTED_MESSAGE_FOR_EXCEPTION = "Unexpected message for exception.";
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception thrown by constructor.";

    private MinefieldGenerator minefieldGenerator;

    @Before
    public void setUp(){
        minefieldGenerator = new MinefieldGenerator(5, 6, 11);
    }

    @Test
    public void testGenerateMinefield(){
        for(int i=0; i<100; i++) {
            boolean[][] minefield = minefieldGenerator.generateMinefield();
            assertMinefieldDimensions(5, 6, minefield);
            assertMinefieldNumberOfMines(11, minefield);
        }
    }

    @Test
    public void testConstructorExceptionsTooSmallValues(){
        try{
            new MinefieldGenerator(0,5,9);
            fail(NO_EXCEPTION);
        }
        catch (IllegalArgumentException e){
            assertEquals(UNEXPECTED_MESSAGE_FOR_EXCEPTION, String.format(Locale.UK, MinefieldGenerator.INVALID_WIDTH, 0),
                    e.getMessage());
        }
        catch (Exception e){
            fail(UNEXPECTED_EXCEPTION);
        }

        try{
            new MinefieldGenerator(6, 0, 10);
            fail(NO_EXCEPTION);
        }
        catch (IllegalArgumentException e){
            assertEquals(UNEXPECTED_MESSAGE_FOR_EXCEPTION, String.format(Locale.UK, MinefieldGenerator.INVALID_HEIGHT, 0),
                    e.getMessage());
        }
        catch (Exception e){
            fail(UNEXPECTED_EXCEPTION);
        }

        try{
            new MinefieldGenerator(4, 5, 0);
            fail(NO_EXCEPTION);
        }
        catch (IllegalArgumentException e){
            assertEquals(UNEXPECTED_MESSAGE_FOR_EXCEPTION, MinefieldGenerator.INVALID_NUMBER_OF_MINES, e.getMessage());
        }
        catch (Exception e){
            fail(UNEXPECTED_EXCEPTION);
        }
    }

    @Test
    public void testConstructorExceptionsTooBigValues(){
        try{
            new MinefieldGenerator(101,5,9);
            fail(NO_EXCEPTION);
        }
        catch (IllegalArgumentException e){
            assertEquals(UNEXPECTED_MESSAGE_FOR_EXCEPTION, String.format(Locale.UK, MinefieldGenerator.INVALID_WIDTH, 101),
                    e.getMessage());
        }
        catch (Exception e){
            fail(UNEXPECTED_EXCEPTION);
        }

        try{
            new MinefieldGenerator(6, 101, 10);
            fail(NO_EXCEPTION);
        }
        catch (IllegalArgumentException e){
            assertEquals(UNEXPECTED_MESSAGE_FOR_EXCEPTION, String.format(Locale.UK, MinefieldGenerator.INVALID_HEIGHT, 101),
                    e.getMessage());
        }
        catch (Exception e){
            fail(UNEXPECTED_EXCEPTION);
        }

        try{
            new MinefieldGenerator(4, 5, 20);
            fail(NO_EXCEPTION);
        }
        catch (IllegalArgumentException e){
            assertEquals(UNEXPECTED_MESSAGE_FOR_EXCEPTION, MinefieldGenerator.INVALID_NUMBER_OF_MINES, e.getMessage());
        }
        catch (Exception e){
            fail(UNEXPECTED_EXCEPTION);
        }
    }

    @Test
    public void testMinefieldGeneratorAllowedValueEdgeCases(){
        new MinefieldGenerator(100, 100, 9999);
        new MinefieldGenerator(4, 5, 19);
    }

    @SuppressWarnings("SameParameterValue") // Only using method once but it's more reusable if
    // values are not hardcoded.
    private void assertMinefieldDimensions(int height, int width, boolean[][] minefield){
        assertEquals(height, minefield.length);
        for (boolean[] row : minefield) {
            assertEquals(width, row.length);
        }
    }

    @SuppressWarnings("SameParameterValue") // Only using method once but it's more reusable if
    // values are not hardcoded.
    private void assertMinefieldNumberOfMines(int expectedNumberOfMines, boolean[][] minefield){
        int numberOfMinesFound = 0;
        for (boolean[] row : minefield) {
            for(boolean cell : row){
                if (cell) {
                    numberOfMinesFound++;
                }
            }
        }
        assertEquals(expectedNumberOfMines, numberOfMinesFound);
    }
}
