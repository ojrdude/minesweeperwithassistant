package com.ojrdude.minesweeperwithassistant.ui.game;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.ojrdude.minesweeperwithassistant.R;


/**
 * The part of the UI that contains the new game button and the flags toggle.
 */
public class GameControls extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {


    private static final String TAG = "MSWA-UI-GameControls";
    private OnGameControlsChanged mListener;
    private Button newGameButton;
    private ToggleButton flagToggleButton;

    public GameControls() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_controls, container, false);
        flagToggleButton = (ToggleButton) view.findViewById(R.id.flagToggleButton);
        flagToggleButton.setOnCheckedChangeListener(this);
        newGameButton = (Button) view.findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGameControlsChanged) {
            mListener = (OnGameControlsChanged) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGameControlsChanged");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(TAG, "Registered change of state on Toggle Button: " + buttonView + ". IsChecked: " + isChecked);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "Detected click on view: " + v);
    }

    /**
     * Containing Activity must implement this interface so that it can be notified when
     * the game controls are modified.
     */
    public interface OnGameControlsChanged {
        void onFlagsToggle();

        void onNewGame();
    }
}
