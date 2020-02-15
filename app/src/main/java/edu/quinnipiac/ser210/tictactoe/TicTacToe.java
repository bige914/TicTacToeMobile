
package edu.quinnipiac.ser210.tictactoe;

/**
* TicTacToe class implements the interface
 *
 * Creates background game board and holds
 * primary methods for playing
 *
* @author Ellsworth Evarts IV
* @date 2/14/2020
*/
import java.util.Random;

//import edu.quinnipiac.ser210.tictactoe.ITicTacToe;

public class TicTacToe implements ITicTacToe {

    // The game board and the game status
    // The game board and the game status
    private static final int ROWS = 3, COLS = 3; // number of rows and columns
    private int[][] board = new int[ROWS][COLS]; // game board in 2D array

    Random objGenerator = new Random();
    int randRow;
    int randCol;


    /**
     * clear board and set current player*/

    public TicTacToe(){

    }
    @Override
    public void clearBoard() {
        for(int i=0; i<9; i++) {
            setMove(0,i);
        }
    }

    public boolean isEmpty(int userIn) {//anti cheat method/sanity check
        int rowE;
        if(userIn>=0&&userIn<=2) {
            rowE=userIn;
            if(board[rowE][0]==0) {
                return true;
            }
        }
        else if(userIn>=3&&userIn<=5) {
            rowE=userIn-3;
            if(board[rowE][1]==0) {
                return true;
            }
        }
        else if(userIn>=6&&userIn<=8) {
            rowE=userIn-6;
            if(board[rowE][2]==0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setMove(int player, int location) {
        // TODO Auto-generated method stub
        if (location >=0 && location <=2) {
            board[location][0]=player;
            printBoard();
        }
        else if (location >=3 && location <= 5) {
            board[location-3][1]=player;
            printBoard();
        }
        else if (location >=6 && location <= 8) {
            board[location-6][2]=player;
            printBoard();
        }
    }

    @Override
    public int getComputerMove() {
        /////////////////////OFFENSE OUTER BOUNDARIES OPEN CENTER/////////////////////
        if(board[0][0]==2&&board[2][0]==2&&board[1][0]==0) {return 1;}//o=0,2 / 0=1
        if(board[0][0]==2&&board[0][2]==2&&board[0][1]==0) {return 3;}//o=0,6 / 0=3
        if(board[2][0]==2&&board[2][2]==2&&board[2][1]==0) {return 5;}//o=2,8 / 0=5
        if(board[0][2]==2&&board[2][2]==2&&board[1][2]==0) {return 7;}//o=6,8 / 0=7
        /////////////////////OFFENSE OUTER BOUNDARIES OPEN CENTER/////////////////////

        //////////////////OFFENSE CROSS SECTION AND DIAG OPEN CENTER//////////////////
        if(board[0][1]==2&&board[2][1]==2&&board[1][1]==0) {return 4;}//o=3,5 / 0=4
        if(board[1][0]==2&&board[1][2]==2&&board[1][1]==0) {return 4;}//o=1,7 / 0=4
        if(board[0][0]==2&&board[2][2]==2&&board[1][1]==0) {return 4;}//o=0,8 / 0=4
        if(board[2][0]==2&&board[0][2]==2&&board[1][1]==0) {return 4;}//o=2,6 / 0=4
        //////////////////OFFENSE CROSS SECTION AND DIAG OPEN CENTER//////////////////

        ///////////////////////OFFENSE DOUBLE GROUPINGS OPEN EDGE/////////////////////
        if(board[0][0]==2&&board[1][0]==2&&board[2][0]==0) {return 2;}//o=0,1 / 0=2
        if(board[1][0]==2&&board[2][0]==2&&board[0][0]==0) {return 0;}//o=1,2 / 0=0
        if(board[0][0]==2&&board[0][1]==2&&board[0][2]==0) {return 6;}//o=0,3 / 0=6
        if(board[0][2]==2&&board[0][1]==2&&board[0][0]==0) {return 0;}//o=3,6 / 0=0
        if(board[2][0]==2&&board[2][1]==2&&board[2][2]==0) {return 8;}//o=2,5 / 0=8
        if(board[2][1]==2&&board[2][2]==2&&board[2][0]==0) {return 2;}//o=5,8 / 0=2
        if(board[0][2]==2&&board[1][2]==2&&board[2][2]==0) {return 8;}//o=6,7 / 0=8
        if(board[1][2]==2&&board[2][2]==2&&board[0][2]==0) {return 6;}//o=7,8 / 0=6
        ///////////////////////OFFENSE DOUBLE GROUPINGS OPEN EDGE/////////////////////

        /////////////////////OFFENSE DOUBLE GROUPING CROSS SECTION////////////////////
        if(board[1][0]==2&&board[1][1]==2&&board[1][2]==0) {return 7;}//o=1,4 / 0=7
        if(board[1][1]==2&&board[1][2]==2&&board[1][0]==0) {return 1;}//o=4,7 / 0=1
        if(board[0][1]==2&&board[1][1]==2&&board[2][1]==0) {return 5;}//o=3,4 / 0=5
        if(board[1][1]==2&&board[2][1]==2&&board[0][1]==0) {return 3;}//o=4,5 / 0=3
        /////////////////////OFFENSE DOUBLE GROUPING CROSS SECTION////////////////////

        ///////////////////////////OFFENSE DOUBLE GROUPING DIAG///////////////////////
        if(board[0][0]==2&&board[1][1]==2&&board[2][2]==0) {return 8;}//o=0,4 / 0=8
        if(board[1][1]==2&&board[2][2]==2&&board[0][0]==0) {return 0;}//o=4,8 / 0=0
        if(board[2][0]==2&&board[1][1]==2&&board[0][2]==0) {return 6;}//o=2,4 / 0=6
        if(board[0][2]==2&&board[1][1]==2&&board[2][0]==0) {return 2;}//o=4,6 / 0=2
        ///////////////////////////OFFENSE DOUBLE GROUPING DIAG///////////////////////

        /////////////////////DEFENSE OUTER BOUNDARIES OPEN CENTER/////////////////////
        if(board[0][0]==1&&board[2][0]==1&&board[1][0]==0) {return 1;}//x=0,2 / 0=1
        if(board[0][0]==1&&board[0][2]==1&&board[0][1]==0) {return 3;}//x=0,6 / 0=3
        if(board[2][0]==1&&board[2][2]==1&&board[2][1]==0) {return 5;}//x=2,8 / 0=5
        if(board[0][2]==1&&board[2][2]==1&&board[1][2]==0) {return 7;}//x=6,8 / 0=7
        /////////////////////DEFENSE OUTER BOUNDARIES OPEN CENTER/////////////////////

        //////////////////DEFENSE CROSS SECTION AND DIAG OPEN CENTER//////////////////
        if(board[0][1]==1&&board[2][1]==1&&board[1][1]==0) {return 4;}//x=3,5 / 0=4
        if(board[1][0]==1&&board[1][2]==1&&board[1][1]==0) {return 4;}//x=1,7 / 0=4
        if(board[0][0]==1&&board[2][2]==1&&board[1][1]==0) {return 4;}//x=0,8 / 0=4
        if(board[2][0]==1&&board[0][2]==1&&board[1][1]==0) {return 4;}//x=2,6 / 0=4
        //////////////////DEFENSE CROSS SECTION AND DIAG OPEN CENTER//////////////////

        ///////////////////////DEFENSE DOUBLE GROUPINGS OPEN EDGE/////////////////////
        if(board[0][0]==1&&board[1][0]==1&&board[2][0]==0) {return 2;}//x=0,1 / 0=2
        if(board[1][0]==1&&board[2][0]==1&&board[0][0]==0) {return 0;}//x=1,2 / 0=0
        if(board[0][0]==1&&board[0][1]==1&&board[0][2]==0) {return 6;}//x=0,3 / 0=6
        if(board[0][2]==1&&board[0][1]==1&&board[0][0]==0) {return 0;}//x=3,6 / 0=0
        if(board[2][0]==1&&board[2][1]==1&&board[2][2]==0) {return 8;}//x=2,5 / 0=8
        if(board[2][1]==1&&board[2][2]==1&&board[2][0]==0) {return 2;}//x=5,8 / 0=2
        if(board[0][2]==1&&board[1][2]==1&&board[2][2]==0) {return 8;}//x=6,7 / 0=8
        if(board[1][2]==1&&board[2][2]==1&&board[0][2]==0) {return 6;}//x=7,8 / 0=6
        ///////////////////////DEFENSE DOUBLE GROUPINGS OPEN EDGE/////////////////////

        /////////////////////DEFENSE DOUBLE GROUPING CROSS SECTION////////////////////
        if(board[1][0]==1&&board[1][1]==1&&board[1][2]==0) {return 7;}//x=1,4 / 0=7
        if(board[1][1]==1&&board[1][2]==1&&board[1][0]==0) {return 1;}//x=4,7 / 0=1
        if(board[0][1]==1&&board[1][1]==1&&board[2][1]==0) {return 5;}//x=3,4 / 0=5
        if(board[1][1]==1&&board[2][1]==1&&board[0][1]==0) {return 3;}//x=4,5 / 0=3
        /////////////////////DEFENSE DOUBLE GROUPING CROSS SECTION////////////////////

        ///////////////////////////DEFENSE DOUBLE GROUPING DIAG///////////////////////
        if(board[0][0]==1&&board[1][1]==1&&board[2][2]==0) {return 8;}//x=0,4 / 0=8
        if(board[1][1]==1&&board[2][2]==1&&board[0][0]==0) {return 0;}//x=4,8 / 0=0
        if(board[2][0]==1&&board[1][1]==1&&board[0][2]==0) {return 6;}//x=2,4 / 0=6
        if(board[0][2]==1&&board[1][1]==1&&board[2][0]==0) {return 2;}//x=4,6 / 0=2
        ///////////////////////////DEFENSE DOUBLE GROUPING DIAG///////////////////////

        ///////////////////////////////////DEFENSE EARLY GAME SPECIALTY///////////////////////////////////
        if(board[1][1]==0&&(board[0][0]==1||board[2][0]==1||board[0][2]==1||board[2][2]==1)) {return 4;}

        if(board[0][0]==1&&board[2][2]==1&&board[1][1]==2&&board[0][1]==0) {return 3;}//x=0,8 / o=4 / 0=3
        if(board[0][0]==1&&board[2][2]==1&&board[1][1]==2&&board[1][2]==0) {return 7;}//x=0,8 / o=4 / 0=7
        if(board[0][0]==1&&board[2][2]==1&&board[1][1]==2&&board[1][0]==0) {return 1;}//x=0,8 / o=4 / 0=1
        if(board[0][0]==1&&board[2][2]==1&&board[1][1]==2&&board[2][1]==0) {return 5;}//x=0,8 / o=4 / 0=5

        if(board[0][2]==1&&board[2][0]==1&&board[1][1]==2&&board[0][1]==0) {return 3;}//x=2,6 / o=4 / 0=3
        if(board[0][2]==1&&board[2][0]==1&&board[1][1]==2&&board[1][0]==0) {return 1;}//x=2,6 / o=4 / 0=1
        if(board[0][2]==1&&board[2][0]==1&&board[1][1]==2&&board[1][2]==0) {return 7;}//x=2,6 / o=4 / 0=7
        if(board[0][2]==1&&board[2][0]==1&&board[1][1]==2&&board[2][1]==0) {return 5;}//x=2,6 / o=4 / 0=5

        if(board[1][1]==1&&board[0][0]==0) {return 0;}//x=4 / 0=0
        if(board[1][1]==1&&board[2][0]==0) {return 2;}//x=4 / 0=2
        if(board[1][1]==1&&board[0][2]==0) {return 6;}//x=4 / 0=6
        if(board[1][1]==1&&board[2][2]==0) {return 8;}//x=4 / 0=8
        ///////////////////////////////////DEFENSE EARLY GAME SPECIALTY///////////////////////////////////
        do {
            randRow = objGenerator.nextInt(3);
            randCol = objGenerator.nextInt(3);

            if(board[randRow][randCol]==0) {
                if (randRow >=0 && randRow <=2 && randCol == 0) {
                    return randRow;
                }
                else if (randRow >=0 && randRow <=2 && randCol == 1) {
                    return randRow+3;
                }
                else if (randRow >=0 && randRow <=2 && randCol == 2) {
                    return randRow+6;
                }
                break;
            }
        }
        while(board[randRow][randCol]!=0);

        return 0;
    }

    @Override
    public int checkForWinner() {
        ////////////////////////////USER WIN///////////////////////////////////////////////////
        if(board[0][0]==1&&board[1][0]==1&&board[2][0]==1) {return 2;}//top row user wins
        if(board[0][1]==1&&board[1][1]==1&&board[2][1]==1) {return 2;}//middle row user wins
        if(board[0][2]==1&&board[1][2]==1&&board[2][2]==1) {return 2;}//bottom row user wins

        if(board[0][0]==1&&board[0][1]==1&&board[0][2]==1) {return 2;}//column one user wins
        if(board[1][0]==1&&board[1][1]==1&&board[1][2]==1) {return 2;}//column two user wins
        if(board[2][0]==1&&board[2][1]==1&&board[2][2]==1) {return 2;}//column three user wins

        if(board[0][0]==1&&board[1][1]==1&&board[2][2]==1) {return 2;}//L to R diagonal user wins
        if(board[0][2]==1&&board[1][1]==1&&board[2][0]==1) {return 2;}//R to L diagonal user wins
        ////////////////////////////USER WIN///////////////////////////////////////////////////

        ////////////////////////////AI WIN///////////////////////////////////////////////////
        if(board[0][0]==2&&board[1][0]==2&&board[2][0]==2) {return 3;}//top row ai wins
        if(board[0][1]==2&&board[1][1]==2&&board[2][1]==2) {return 3;}//middle row ai wins
        if(board[0][2]==2&&board[1][2]==2&&board[2][2]==2) {return 3;}//bottom row ai wins

        if(board[0][0]==2&&board[0][1]==2&&board[0][2]==2) {return 3;}//column one ai wins
        if(board[1][0]==2&&board[1][1]==2&&board[1][2]==2) {return 3;}//column two ai wins
        if(board[2][0]==2&&board[2][1]==2&&board[2][2]==2) {return 3;}//column three ai wins

        if(board[0][0]==2&&board[1][1]==2&&board[2][2]==2) {return 3;}//L to R diagonal ai wins
        if(board[0][2]==2&&board[1][1]==2&&board[2][0]==2) {return 3;}//R to L diagonal ai wins
        ////////////////////////////AI WIN///////////////////////////////////////////////////

        ////////////////////////////Stalemate///////////////////////////////////////////////////
        if(board[0][0]!=0&&board[1][0]!=0&&board[2][0]!=0&&
                board[0][1]!=0&&board[1][1]!=0&&board[2][1]!=0&&
                board[0][2]!=0&&board[1][2]!=0&&board[2][2]!=0) {return 1;}//board is full nobody wins
        ////////////////////////////Stalemate///////////////////////////////////////////////////


        else {return 0;}

    }

    /**
     *  Print the game board*/
    public  void printBoard() {
        for (int col = 0; col < COLS; ++col) { //(int row = 0; row < ROWS; ++row)
            for (int row = 0; row < ROWS; ++row) //(int col = 0; col < COLS; ++col)
            {
                printCell(board[row][col]); // print each of the cells
                if (row != ROWS - 1) {//(col != COLS - 1)
                    System.out.print("|");   // print vertical partition
                }
            }
            System.out.println();
            if (col != COLS - 1) {//(row != ROWS - 1)
                System.out.println("-----------"); // print horizontal partition
            }
        }
        System.out.println();
    }


    public  void printCell(int content) {
        switch (content) {
            case EMPTY:  System.out.print("   "); break;
            case NOUGHT: System.out.print(" O "); break;
            case CROSS:  System.out.print(" X "); break;
        }
    }

}
