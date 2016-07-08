package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

/**
 * Abstract definition of a GameBoard fragment. Contains methods and properties that are
 * shared between all game board fragments of any size.
 */
public abstract class GameBoard extends Fragment {

    protected String TAG = "MSWA-UI-GameBoardAbstract";
    protected GameBoard9by9.OnCellClickListener parentActivityListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GameBoard9by9.OnCellClickListener) {
            parentActivityListener = (GameBoard9by9.OnCellClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCellClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentActivityListener = null;
    }

    /**
     * Calculates the R id value for a given coordinate.
     *
     * @param x The Cell's x coordinate
     * @param y The Cell's y coordinate
     * @return the R id value for that cell
     */
    protected int getIDForCoordinate(int x, int y) {
        Resources res = getResources();
        int id = res.getIdentifier(String.format(Locale.UK, "cell_%d_%d", x, y), "id", getContext().getPackageName());
        return id;
    }

    public void onClick(View v) {
        Log.d(TAG, "Click detected on view: " + v);

        String cellCoords = v.getTag().toString();
        String[] splitCoords = cellCoords.split(",");
        int x = Integer.valueOf(splitCoords[0]);
        int y = Integer.valueOf(splitCoords[1]);

        parentActivityListener.onCellClicked(x, y, (ImageButton) v);
    }

    /**
     * The containing activity must implement this interface so that it can be updated when a cell is clicked.
     */
    public interface OnCellClickListener {
        void onCellClicked(int x, int y, ImageButton cellImageButton);
    }
}
