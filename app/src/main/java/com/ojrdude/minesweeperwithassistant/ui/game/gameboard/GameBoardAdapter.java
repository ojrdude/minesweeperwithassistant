package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.ojrdude.minesweeperwithassistant.minefield.Minefield;

import java.util.Locale;

/**
 * Custom adapter to control the contents of a GameBoard Fragment's GridView
 */
public class GameBoardAdapter extends BaseAdapter {
    private Context context;
    private CellButton[] cells;

    public GameBoardAdapter(Context context, Minefield minefield) {
        this.context = context;
        cells = new CellButton[minefield.getNumberOfCells()];
        int i = 0;
        for (int x = 0; x < minefield.getWidth(); x++) {
            for (int y = 0; y < minefield.getHeight(); y++) {
                cells[i] = new CellButton(context, minefield.getCellAtCoordinates(x, y));
                i++;
            }
        }
        Log.d("MSWA-UI", String.format(Locale.UK, "GameBoardAdapter Constructed for %dx%d grid", minefield.getWidth(),
                minefield.getHeight()));
    }

    @Override
    public int getCount() {
        return cells.length;
    }

    @Override
    public Object getItem(int position) {
        return cells[position];
    }

    @Override
    public long getItemId(int position) {
        return position; // TODO: Work out what this function should really do.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CellButton cell;
        if (convertView == null) {
            cell = cells[position];
        } else {
            cell = (CellButton) convertView;
        }
        return cell;
    }
}
