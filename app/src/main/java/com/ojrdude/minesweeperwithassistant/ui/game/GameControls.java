package com.ojrdude.minesweeperwithassistant.ui.game;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ojrdude.minesweeperwithassistant.R;


/**
 * The part of the UI that contains the new game button and the flags toggle.
 */
public class GameControls extends Fragment {


    private OnGameControlsChanged mListener;

    public GameControls() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_controls, container, false);
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

    /**
     * Containing Activity must implement this interface so that it can be notified when
     * the game controls are modified.
     */
    public interface OnGameControlsChanged {
        void onFlagsToggle();
        void onNewGame();
    }
}
