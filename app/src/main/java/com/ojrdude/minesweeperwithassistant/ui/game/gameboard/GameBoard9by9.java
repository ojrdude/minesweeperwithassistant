package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ojrdude.minesweeperwithassistant.R;

/**
 * The Beginner difficulty level GameBoard
 */
public class GameBoard9by9 extends GameBoard {


    public GameBoard9by9() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_board9_by_9, container, false);
    }

}
