package edu.quinnipiac.ser210.tictactoe;

/**
 * MainActivity class, launches UI component for playing game
 *
 * Creates background game board and holds
 * primary methods for playing
 *
 * @author Ellsworth Evarts IV
 * @date 2/14/2020
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static TicTacToe TTTboard = new TicTacToe();

    private int nRows;
    private int nCols;
    private boolean winState = false;//true if a win condition is met, this includes ties
    private float textSize= 60;

    private static final String EMPTY_BUTTON = " ";
    int currentState = TicTacToe.PLAYING;

    Button buttons[];
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nRows=3;
        nCols=3;

        buttons = new Button[nRows*nCols];
        createButtonGrid(R.id.grid);

        final Button startOverBtn = findViewById(R.id.button_startOver);
        startOverBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //add requirements to check if game won/ is complete
                onStartOverClick(v);
            }
        }
        );
    }

    private void initGame() {
        int arrLength= buttons.length;
        for(int i=0; i < arrLength; i++){
            buttons[i].setText(EMPTY_BUTTON);
            buttons[i].setTextColor(Color.BLACK);
        }
        TTTboard.clearBoard();
        winState = false;
        currentState = ITicTacToe.PLAYING;

    }

    private void createButtonGrid(int id) {
        GridLayout layout = (GridLayout) findViewById(id);
        layout.setColumnCount(nCols);
        layout.setRowCount(nRows);

        int count = 0;


        for(int c=0; c<nCols; c++){
            for (int r=0; r<nRows; r++){
                int pos = c*nCols + r;
                //dynamic button creation
                btn= new Button(this);
                //set tag for reference to button clicked
                btn.setTag(count);


                btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        onPlayerClick(v);
                    }
                });
                //style the buttons
                btn.setBackgroundColor(Color.rgb(211,211,211));
                btn.setMinHeight(0);
                btn.setMinimumHeight(0);
                btn.setText(EMPTY_BUTTON);
                btn.setTextSize(textSize);
                btn.setWidth(50);
                btn.setHeight(300);
                btn.setPadding(5,5,5,5);
                btn.getPaint().setStrokeWidth(5f);
                btn.setTextColor(Color.BLACK);
                btn.getPaint().setStyle(Paint.Style.STROKE);
                //add buttons to grid
                layout.addView(btn);
                //assign position of buttons in array
                buttons[pos]=btn;
                count++;
            }
        }

    }







    private void onPlayerClick(View v) {

        Log.d(v.getTag().toString(), "onPlayerClick: " + v.getTag().toString());//debug pos
        int id = getClickedButtonIndex(v); //find index of button to update

        currentState = TTTboard.checkForWinner();
        checkWinner();

        if(buttons[id].getText() == EMPTY_BUTTON && winState == false) {

            int uInput = id;
            if(TTTboard.isEmpty(uInput)) {
                TTTboard.setMove(1, uInput);
            }
            else{

            }

            int x = id;
            btn.setTextColor(Color.BLACK);
            buttons[x].setText("X");

            currentState = TTTboard.checkForWinner();
            checkWinner();
            aiPlay();
        }

        /*
        // debug tool for buttons on the phone (Toast)
        try {
            buttonTag = Integer.parseInt(v.getTag().toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        Context context = getApplicationContext();
        int buttonIntVal = id;
        String buttonLabel = Integer.toString(buttonIntVal);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, buttonLabel, duration);
        toast.show();
        */
    }

    public void onStartOverClick(View view) {
        if(winState == true){
            initGame();
        }

    }

    public void aiPlay(){
        currentState = TTTboard.checkForWinner();
        checkWinner();

        if(winState == false){
            int o = TTTboard.getComputerMove();
            TTTboard.setMove(2, o);
            //machine plays
            buttons[o].setTextColor(Color.RED);
            buttons[o].setText("O");

            currentState = TTTboard.checkForWinner();
            checkWinner();
        }

    }

    public int getClickedButtonIndex(View v){
        return Integer.parseInt(v.getTag().toString());
    }

    public void checkWinner(){
        if (currentState == ITicTacToe.CROSS_WON) {
            //System.out.println("'X' won! Bye!");
            winState = true;

        } else if (currentState == ITicTacToe.NOUGHT_WON) {
            //System.out.println("'O' won! Bye!");
            winState = true;

        } else if (currentState == ITicTacToe.TIE) {
            //System.out.println("It's a TIE! Bye!");
            winState = true;
        }
        else currentState = ITicTacToe.PLAYING;

    }
}
