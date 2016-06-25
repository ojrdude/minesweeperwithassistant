package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import com.ojrdude.minesweeperwithassistant.cell.Cell;

/**
 * Class that represents a Cell "button" on the gameboard. It is an ImageButton, but it encapsulates
 * a Cell object.
 */
public class CellButton extends ImageButton {

    private Cell cell;

    public CellButton(Context context, Cell cell) {
        super(context);
        this.cell = cell;
    }

}
