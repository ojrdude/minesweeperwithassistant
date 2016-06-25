package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

/**
 * Custom adapter to control the contents of a GameBoard Fragment's GridView
 */
public class GameBoardAdapter extends BaseAdapter {
    private Context context;
    private ImageButton[] cells;
    public GameBoardAdapter(Context context, int numCells){
        this.context = context;
        cells = new ImageButton[numCells];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new ImageButton(context);
        }
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
        ImageButton cell;
        if (convertView == null){
            cell = cells[position];
        }
        else {
            cell = (ImageButton) convertView;
        }
        return cell;
    }
}
