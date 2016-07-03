package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.ojrdude.minesweeperwithassistant.R;
import com.ojrdude.minesweeperwithassistant.cell.Cell;

import java.util.Random;

/**
 * Class that represents a Cell "button" on the gameboard. It is an ImageButton, but it encapsulates
 * a Cell object.
 */
public class CellButton extends ImageButton {

    private Cell cell;

    public CellButton(Context context, Cell cell) {
        super(context);
        this.cell = cell;
        Random random = new Random();
        if(random.nextBoolean()) {
            this.setImageResource(R.drawable.coveredcell);
        }
        else{
            this.setImageResource(R.drawable.uncoveredcell1);
        }
    }

    public Cell getCell() {
        return cell;
    }
}
