package cpsc2150.extendedTicTacToe;

import java.util.*;

import static cpsc2150.extendedTicTacToe.IGameBoard.*;

public class GameScreen{

    private static int invalid = -1;
    private static int rowIn = invalid;
    private static int colIn = invalid;
    private static int turnCount = -1;
    private static IGameBoard board;
    private static BoardPosition posIn = new BoardPosition(rowIn, colIn);
    private static Scanner userIn = new Scanner(System.in);
    private static List<Character> playerChar = new ArrayList<>();
    private static int numPlayers = 0;
    private static final int minPlayers = 2;
    private static final int maxPlayers = 10;

    /**
     *
     * @param args - input arguments
     * @post full games of extended tic tac toe have been played
     */
    static public void main(String[] args) {

        while(true) {
            //outer loop, for single game

            playerChar.clear();

            //starting at -1 because turnCount++ do some wack stuff
            turnCount = -1;

            //creating board and init.ing some variables
            board = boardFactory();

            while (true) {
                //inner loop, for single turn

                //handling player char
                turnCount++;
                if(turnCount == playerChar.size()){
                    turnCount = 0;
                }

                //printing board at turn start
                System.out.println(board.toString());

                //getting user row
                System.out.println("Player " + playerChar.get(turnCount) + " please enter your ROW");
                String rowInStr = userIn.nextLine();
                if (rowInStr.matches("[0-9]+")) {
                    rowIn = Integer.parseInt(rowInStr);
                } else {
                    rowIn = invalid;
                }

                //getting user column
                System.out.println("Player " + playerChar.get(turnCount) + " please enter your COLUMN");
                String colInStr = userIn.nextLine();
                if (colInStr.matches("[0-9]+")) {
                    colIn = Integer.parseInt(colInStr);
                } else {
                    colIn = invalid;
                }

                posIn = new BoardPosition(rowIn, colIn);

                //if row and column are invalid, this forces them
                //to re-input row and column till they're valid
                while (!board.checkSpace(posIn)) {
                    System.out.println("That space is unavailable, please pick again");

                    System.out.println("Player " + playerChar.get(turnCount) + " please enter your ROW");
                    rowInStr = userIn.nextLine();
                    if (rowInStr.matches("[0-9]+")) {
                        rowIn = Integer.parseInt(rowInStr);
                    } else {
                        rowIn = invalid;
                    }

                    System.out.println("Player " + playerChar.get(turnCount) + " please enter your COLUMN");
                    colInStr = userIn.nextLine();
                    if (colInStr.matches("[0-9]+")) {
                        colIn = Integer.parseInt(colInStr);
                    } else {
                        colIn = invalid;
                    }

                    posIn = new BoardPosition(rowIn, colIn);
                }

                //placing the player char at valid input on the board
                board.placeMarker(posIn, playerChar.get(turnCount));

                //this ends a single game
                if (board.checkForWinner(posIn) || board.checkForDraw()){
                    break;
                }
            }

            //win message, and possible restart
            if (board.checkForWinner(posIn)) {
                System.out.println("Player " + playerChar.get(turnCount) + " wins!");
                System.out.println(board.toString());
            }

            //draw message
            else if(board.checkForDraw()){
                System.out.println("It's a draw!");
                System.out.println(board.toString());
            }

            //possible restart
            System.out.println("Would you like to play again? Y/N");
            String restart = userIn.nextLine();
            restart = restart.toLowerCase();
            while (!restart.equals("y") && !restart.equals("n")){
                System.out.println("Sorry, that was invalid, please try again");
                System.out.println("Would you like to play again? Y/N");
                restart = userIn.nextLine();
                restart = restart.toLowerCase();
            }
            if (restart.equals("n")) {
                break;
            }
        }
    }

    /**
     * @return new game board for single game
     * @post 2 <= numPlayers <= 10
     * @post #playerChar = full list of player chars
     * @post 3 <= row <= 100
     * @post 3 <= col <= 100
     * @post 3 <= winVal <= (row & col)
     * @post board = GameBoard or GameBoardMem
     */
    //initializes all necessary variables for each new game start
    static IGameBoard boardFactory(){
        //creating the game board for each game start
        int row = 0, col = 0, win = 0;

        //player quantity handling
        System.out.println("How many players?");
        String playNum = userIn.nextLine();
        while(!playNum.matches("[0-9]+") || Integer.parseInt(playNum) < minPlayers
                || Integer.parseInt(playNum) > maxPlayers){

            if(!playNum.matches("[0-9]+")){
                System.out.println("Must be a number");
            }
            else if(Integer.parseInt(playNum) < minPlayers){
                System.out.println("Must be at least " + minPlayers + " players");
            }
            else{System.out.println("Must be " + maxPlayers + " players or fewer"); }

            System.out.println("How many players?");
            playNum = userIn.nextLine();
        }
        numPlayers = Integer.parseInt(playNum);


        //player chars handling
        for(int i = 0; i < numPlayers; i++){
            System.out.println("Enter character for player " + i);
            char newChar = Character.toUpperCase((userIn.nextLine()).charAt(0));

            while(playerChar.contains(newChar)) {
                System.out.println(newChar + " is already taken as a player token!");
                System.out.println("Enter character for player " + i);
                newChar = Character.toUpperCase((userIn.nextLine()).charAt(0));
            }
            playerChar.add(i, newChar);
        }


        //row handling
        System.out.println("How many rows?");
        String numRowStr = userIn.nextLine();
        while(!numRowStr.matches("[0-9]+") || Integer.parseInt(numRowStr) < minRows
                || Integer.parseInt(numRowStr) > maxRows){
            System.out.println("Rows must be between " + minRows + " and " + maxRows);
            System.out.println("How many rows?");
            numRowStr = userIn.nextLine();
        }
        row = Integer.parseInt(numRowStr);


        //column handling
        System.out.println("How many columns?");
        String numColStr = userIn.nextLine();
        while(!numColStr.matches("[0-9]+") || Integer.parseInt(numColStr) < minCols
                || Integer.parseInt(numColStr) > maxCols){
            System.out.println("Columns must be between " + minCols + " and " + maxCols);
            System.out.println("How many columns?");
            numColStr = userIn.nextLine();
        }
        col = Integer.parseInt(numColStr);


        //win length handling
        System.out.println("How many in a row to win?");
        String numWinStr = userIn.nextLine();
        while(!numWinStr.matches("[0-9]+") || Integer.parseInt(numWinStr) > row
                || Integer.parseInt(numWinStr) > col || Integer.parseInt(numWinStr) < minWinLength){
            System.out.println("winning length must be less than number of rows and number of columns"
                    + " and must be at least " + minWinLength);
            System.out.println("How many in a row to win?");
            numWinStr = userIn.nextLine();
        }
        win = Integer.parseInt(numWinStr);


        //board type handling
        System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
        Character bType = Character.toLowerCase((userIn.nextLine()).charAt(0));
        while(!bType.equals('f') && !bType.equals('m')){
            System.out.println("Please enter F or M");
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            bType = Character.toLowerCase((userIn.nextLine()).charAt(0));
        }

        if(bType.equals('f')){
            board = new GameBoard(row, col, win);
        }
        else{ board = new GameBoardMem(row, col, win); }

        return board;
    }
}
