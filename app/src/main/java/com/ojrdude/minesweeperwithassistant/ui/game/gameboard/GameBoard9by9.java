package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ojrdude.minesweeperwithassistant.R;

import java.util.HashSet;
import java.util.Set;

/**
 * The Beginner difficulty level GameBoard
 */
public class GameBoard9by9 extends GameBoard implements View.OnClickListener {


    private Set<ImageButton> cells;

    public GameBoard9by9() {
        TAG = "MSWA-UI-GameBoard9by9";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_board9_by_9, container, false);
        cells = new HashSet<>();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                ImageButton cell = (ImageButton) view.findViewById(getIDForCoordinate(x, y));
                cell.setOnClickListener(this);
                cells.add(cell);
            }
        }
        return view;
    }

}
