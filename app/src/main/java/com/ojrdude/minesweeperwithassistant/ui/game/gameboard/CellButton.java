package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.ojrdude.minesweeperwithassistant.cell.Cell;

/**
 * Class that represents a Cell "button" on the gameboard. It is an ImageButton, but it encapsulates
 * a Cell object.
 */
public class CellButton extends ImageButton {

    private Cell cell;

    public CellButton(Context context) {
        super(context);
    }

    public CellButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CellButton(Context context, Cell cell) {
        super(context);
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }
}
