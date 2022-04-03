package cpsc2150.extendedTicTacToe;

import java.util.*;

public class GameBoardMem extends AbsGameBoard {

    //row and column info
    private int boardHeight = 0;
    private int boardWidth = 0;

    private static final char blankChar = ' ';
    private Map<String, List<BoardPosition>> gBoard;
    private int winVal = 0;

    /**
     * @pre 3 <= bH <= 100
     * @pre 3 <= bW <= 100
     * @pre wV <= bH
     * @pre wV <= bW
     * @post new blank array length & height = 8
     */
    GameBoardMem(int bH, int bW, int wV) {
        boardHeight = bH;
        boardWidth = bW;
        winVal = wV;

        gBoard = new HashMap<String, List<BoardPosition>>();
    }

    /**
     * @pre pos must be a valid board location
     * @param marker input board position
     * @param player - most recent player char
     * @post marker char = player char
     */
    public void placeMarker(BoardPosition marker, char player){
        if(!gBoard.containsKey(String.valueOf(player))){
            gBoard.put(String.valueOf(player), new ArrayList<BoardPosition>());
        }
        gBoard.get(String.valueOf(player)).add(marker);
    }

    /**
     * @pre pos within board boundaries
     * @param pos - current position being evaluated
     * @post whatsAtPos = char of gameboard at input position
     * @return the character at pos
     */
    public char whatsAtPos(BoardPosition pos){
        for(Map.Entry<String, List<BoardPosition>> m: gBoard.entrySet()){
            if(((List<BoardPosition>)m.getValue()).contains(pos)){
                return ((String)m.getKey()).charAt(0);
            }
        }
        return blankChar;
    }

    /**
     * @post getNumRows = number of rows
     * @return the number of rows
     */
    public int getNumRows(){ return boardHeight; }

    /**
     * @post getNumColumns = number of columns
     * @return the number of columns
     */
    public int getNumColumns(){ return boardWidth; }

    /**
     * @post getNumToWin = winning marker length
     * @return the number of markers in a row needed to win
     */
    public int getNumToWin(){ return winVal; }

    /**
     * @param pos - input position
     * @param player - most recent player character
     * @post true = player char at pos
     * @return true if player is at input position
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        if(!gBoard.containsKey(String.valueOf(player))){
            return false;
        }
        return gBoard.get(String.valueOf(player)).contains(pos);
    }
}
