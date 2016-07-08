package com.ojrdude.minesweeperwithassistant.ui.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.ojrdude.minesweeperwithassistant.R;
import com.ojrdude.minesweeperwithassistant.ui.game.gameboard.GameBoard9by9;

public class MainActivity extends AppCompatActivity implements GameControls.OnGameControlsChanged, GameBoard9by9.OnCellClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFlagsToggle() {

    }

    @Override
    public void onNewGame() {

    }

    @Override
    public void onCellClicked(int x, int y, ImageButton cellImageButton) {

    }
}
