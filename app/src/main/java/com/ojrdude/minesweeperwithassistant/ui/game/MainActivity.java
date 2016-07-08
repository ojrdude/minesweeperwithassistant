package com.ojrdude.minesweeperwithassistant.ui.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;

import com.ojrdude.minesweeperwithassistant.R;
import com.ojrdude.minesweeperwithassistant.cell.Cell;
import com.ojrdude.minesweeperwithassistant.cell.CellContents;
import com.ojrdude.minesweeperwithassistant.minefield.Minefield;
import com.ojrdude.minesweeperwithassistant.minefield.MinefieldGenerator;
import com.ojrdude.minesweeperwithassistant.ui.game.gameboard.GameBoard9by9;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GameControls.OnGameControlsChanged, GameBoard9by9.OnCellClickListener {

    private static final String TAG = "MSWA-UI-MainActivity";
    private Minefield minefield;
    private boolean flagMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreateCalled. savedInstanceState: " + savedInstanceState);
        setContentView(R.layout.activity_main);
        if (minefield == null) {
            final int width = 9;
            final int height = 9;
            final int numberOfMines = 10;
            Log.d(TAG, "Minefield is null. Generating new minefield of %dx%d with %d mines.");
            minefield = new Minefield(new MinefieldGenerator(width, height, numberOfMines));
        }
    }

    @Override
    public void onFlagsToggle(boolean flagModeChecked) {
        Log.d(TAG, "onFlagsToggle Triggered. flagMOdeChecked: " + flagModeChecked);
        flagMode = flagModeChecked;
    }

    @Override
    public void onNewGame() {
        Log.d(TAG, "onNewGame Triggered. Not yet implemented");
    }

    @Override
    public void onCellClicked(int x, int y, ImageButton cellImageButton) {
        Log.d(TAG, String.format(Locale.UK, "onCellClicked called for coordinates (%d,%d).", x, y));
        Cell cell = minefield.getCellAtCoordinates(x, y);
        if (!flagMode) {
            if (!cell.isFlagged() && cell.getContents() == CellContents.UNKNOWN) {
                cell.uncover();
                CellContents cellContents = cell.getContents();
                switch (cellContents) {
                    case MINE:
                        cellImageButton.setImageResource(R.drawable.mine);
                        mineUncovered();
                        break;
                    case ZERO:
                        cellImageButton.setImageResource(R.drawable.uncoveredcell0);
                        uncoverSurroundingCells(x, y);
                        break;
                    case ONE:
                        cellImageButton.setImageResource(R.drawable.uncoveredcell1);
                        break;
                    case TWO:
                        cellImageButton.setImageResource(R.drawable.uncoveredcell2);
                        break;
                    case THREE:
                        cellImageButton.setImageResource(R.drawable.uncoveredcell3);
                        break;
                    case FOUR:
                        cellImageButton.setImageResource(R.drawable.uncoveredcell4);
                        break;
                    case FIVE:
                        cellImageButton.setImageResource(R.drawable.uncoveredcell5);
                        break;
                    case SIX:
                        cellImageButton.setImageResource(R.drawable.uncoveredcell6);
                        break;
                    case SEVEN:
                        cellImageButton.setImageResource(R.drawable.uncoveredcell7);
                        break;
                    case EIGHT:
                        cellImageButton.setImageResource(R.drawable.uncoveredcell8);
                        break;
                    case UNKNOWN:
                        throw new IllegalStateException("Uncovered cell should never have UNKNOWN value.");
                }
            }
        } else {
            if(cell.getContents() == CellContents.UNKNOWN && cell.isFlagged()){
                cellImageButton.setImageResource(R.drawable.coveredcell);
                cell.removeFlag();
            }
            else if(cell.getContents()==CellContents.UNKNOWN && !cell.isFlagged()){
                cellImageButton.setImageResource(R.drawable.flag);
                cell.flag();
            }
        }
    }

    private void mineUncovered() {
        Log.d(TAG, "Mine Uncovered. Game Over. Not yet implemented.");
    }

    private void uncoverSurroundingCells(int x, int y) {
        Log.d(TAG, String.format(Locale.UK, "Uncovering cells around (%d,%d) as it contains a zero. Not yet implemented.", x, y));
    }
}
