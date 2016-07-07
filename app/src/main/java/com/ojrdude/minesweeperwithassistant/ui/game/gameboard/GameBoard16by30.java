package com.ojrdude.minesweeperwithassistant.ui.game.gameboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.ojrdude.minesweeperwithassistant.R;
import com.ojrdude.minesweeperwithassistant.minefield.Minefield;
import com.ojrdude.minesweeperwithassistant.minefield.MinefieldGenerator;

/**
 * Expert level grid. Dev temporarily abandoned in favour of 9by9 grid
 */
public class GameBoard16by30 extends GameBoard {

//    private View view;
//    private TableLayout gameBoard;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_game_board_16_by_30, container, false);
//        return view;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Activity activity = getActivity();
//
//        final MinefieldGenerator generator = new MinefieldGenerator(16, 30, 99);
//        final Minefield minefield = new Minefield(generator);
//
//        gameBoard = (TableLayout) activity.findViewById(R.id.gameBoard);
//        constructGameBoard(minefield);
//    }
//
//    private void constructGameBoard(Minefield minefield) {
//
////        gameBoard.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
////                gameBoard.getWidth() / minefield.getWidth() * minefield.getHeight()));
//
//        for (int row = 0; row < minefield.getHeight(); row++) {
//            TableRow tableRow = new TableRow(getActivity());
////            final TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
////                    gameBoard.getWidth(),
////                    gameBoard.getHeight() / minefield.getHeight());
//            final TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(0, 0, 0, 0);
//            tableRow.setLayoutParams(layoutParams);
//
//            for (int col = 0; col < minefield.getWidth(); col++) {
//                CellButton cell = new CellButton(getActivity(), minefield.getCellAtCoordinates(col, row));
//                cell.setImageResource(R.drawable.coveredcell);
////                cell.setScaleType(ImageView.ScaleType.FIT_XY);
//                cell.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                cell.setPadding(0, 0, 0, 0);
//                tableRow.addView(cell);
//            }
//            gameBoard.addView(tableRow);
//
//        }
//
//    }
}
