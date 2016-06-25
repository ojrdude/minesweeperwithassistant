package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;

import com.ojrdude.minesweeperwithassistant.R;

public class GameBoard16by30 extends GameBoard {

    private View view;

    public GameBoard16by30() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game_board_16_by_30, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton[] buttons = new ImageButton[16*30];
        Activity activity = getActivity();
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new ImageButton(activity);
        }

        //Todo: make custom adapter by extending arrayadapter
        GridView gridView = (GridView) activity.findViewById(R.id.gameGrid);
        gridView.setAdapter(new GameBoardAdapter(activity, 16*30));
    }
}
