package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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
//        this.setLayoutParams(new GridView.LayoutParams(10, 10)); // TODO: Replace with XML definition if possible.
//        this.setPadding(1,1,1,1);
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
