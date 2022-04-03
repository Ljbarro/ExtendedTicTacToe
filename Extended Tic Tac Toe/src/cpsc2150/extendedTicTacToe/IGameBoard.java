package cpsc2150.extendedTicTacToe;

public interface IGameBoard {
    //row info
    final int minRows = 3;
    final int maxRows = 100;

    //column info
    final int minCols = 3;
    final int maxCols = 100;

    //other info
    static final char blankChar = ' ';
    int winVal = 0;
    public final int minWinLength = 3;

    /**
     * @pre pos must be a valid board location
     * @param marker input board position
     * @param player - most recent player char
     * @post marker char = player char
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * @pre pos within board boundaries
     * @param pos - current position being evaluated
     * @post whatsAtPos = char of gameboard at input position
     * @return the character at pos
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * @post getNumRows = boardWidth
     * @return the number of rows
     */
    public int getNumRows();

    /**
     * @post getNumColumns = boardHeight
     * @return the number of columns
     */
    public int getNumColumns();

    /**
     * @post getNumToWin = winVal
     * @return the number of markers in a row needed to win
     */
    public int getNumToWin();

    /**
     * @post toString = current board state
     * @return the current board state
     */
    @Override
    public String toString();

    /**
     * @param pos - input position being checked for clearness
     * @return true if space @ pos is blank
     * @post checkSpace = true if pos = blank
     */
    public default boolean checkSpace(BoardPosition pos){
        if(pos.getRow() < this.getNumColumns() && pos.getRow() >= 0
                && pos.getColumn() < this.getNumRows() && pos.getColumn() >= 0) {
            return whatsAtPos(pos) == blankChar;
        }
        return false;
    }

    /**
     * @pre pos within board boundaries
     * @param pos - input position
     * @param player - most recent player character
     * @post true = player char at pos
     * @return true if player is at input position
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player){
        return whatsAtPos(pos) == player;
    }

    /**
     * @pre lastPos = valid position
     * @pre player = valid char
     * @param lastPos - last position played
     * @param player - most recent player char
     * @return true if latest placement results in a horizontal-based win
     * @post true = 5 length row of player char
     */
    public default boolean checkHorizontalWin(BoardPosition lastPos, char player){

        //doesWin counts how many player chars are in a row
        //inc and dec are values to move the read character right and left, respectively
        int doesWin = 1;
        int inc = 1, dec = -1;
        BoardPosition newRightPos = new BoardPosition(lastPos.getRow(), lastPos.getColumn() + inc);
        BoardPosition newLeftPos = new BoardPosition(lastPos.getRow(), lastPos.getColumn() + dec);

        //checks how many player chars are to the right of lastPos, and checks for win
        while(lastPos.getColumn() + inc < getNumColumns() &&
                isPlayerAtPos(newRightPos, player)){
            doesWin++;
            if(doesWin == getNumToWin()){
                return true;
            }
            inc++;
            newRightPos = new BoardPosition(lastPos.getRow(), lastPos.getColumn() + inc);
        }

        //checks how many player chars are to the left of lastPos, and checks for win
        while(lastPos.getColumn() + dec >= 0 &&
                isPlayerAtPos(newLeftPos, player)){
            doesWin++;
            if(doesWin == getNumToWin()){
                return true;
            }
            dec--;
            newLeftPos = new BoardPosition(lastPos.getRow(), lastPos.getColumn() + dec);
        }

        return false;
    }

    /**
     * @pre lastPos = valid position
     * @pre player = valid char
     * @param lastPos - last position played
     * @param player - char of most recent player
     * @return true if latest placement results in a vertical win
     * @post true = 5 length column of player char
     */
    public default boolean checkVerticalWin(BoardPosition lastPos, char player){

        //doesWin counts how many player chars are in a row
        //inc and dec are values to move the read character up and down, respectively
        int doesWin = 1;
        int inc = 1, dec = -1;
        BoardPosition newDownPos = new BoardPosition(lastPos.getRow() + inc, lastPos.getColumn());
        BoardPosition newUpPos = new BoardPosition(lastPos.getRow() + dec, lastPos.getColumn());

        //checks how many player chars are above of lastPos, and checks for win
        while(lastPos.getRow() + inc < getNumRows() &&
                isPlayerAtPos(newDownPos, player)){
            doesWin++;
            if(doesWin == getNumToWin()){
                return true;
            }
            inc++;
            newDownPos = new BoardPosition(lastPos.getRow() + inc, lastPos.getColumn());
        }

        //checks how many player chars are below of lastPos, and checks for win
        while(lastPos.getRow() + dec >= 0 &&
                isPlayerAtPos(newUpPos, player)){
            doesWin++;
            if(doesWin == getNumToWin()){
                return true;
            }
            dec--;
            newUpPos = new BoardPosition(lastPos.getRow() + dec, lastPos.getColumn());
        }

        return false;
    }

    /**
     * @pre lastPos = valid position
     * @pre player = valid char
     * @param lastPos - last position played
     * @param player - char of the player who went last
     * @return true if this player won on either diagonal category
     * @post true = 5 length diagonal of player char
     */
    public default boolean checkDiagonalWin(BoardPosition lastPos, char player){

        //doesWin counts how many player chars are in a row
        //inc and dec are values to move the read character right and left, respectively
        int doesWin = 1;
        int inc = 1, dec = -1;
        BoardPosition newUpLeftPos = new BoardPosition(lastPos.getRow() + dec, lastPos.getColumn() + dec);
        BoardPosition newUpRightPos = new BoardPosition(lastPos.getRow() + dec, lastPos.getColumn() + inc);
        BoardPosition newDownLeftPos = new BoardPosition(lastPos.getRow() + inc, lastPos.getColumn() + dec);
        BoardPosition newDownRightPos = new BoardPosition(lastPos.getRow() + inc, lastPos.getColumn() + inc);

        //checking the / direction
        //checks how many player chars are up and to the right of lastPos, and checks for win
        while(lastPos.getColumn() + inc < getNumColumns() && lastPos.getRow() + dec >= 0 &&
                isPlayerAtPos(newUpRightPos, player)){
            doesWin++;
            if(doesWin == getNumToWin()){
                return true;
            }
            inc++;
            dec--;
            newUpRightPos = new BoardPosition(lastPos.getRow() + dec, lastPos.getColumn() + inc);

        }

        //ugh, resetting inc and dec again because the last check made em wack
        inc = 1;
        dec = -1;

        //checks how many player chars are down and to the left of lastPos, and checks for win
        while(lastPos.getColumn() + dec >= 0 && lastPos.getRow() + inc < getNumRows() &&
                isPlayerAtPos(newDownLeftPos, player)){
            doesWin++;
            if(doesWin == getNumToWin()){
                return true;
            }
            inc++;
            dec--;
            newDownLeftPos = new BoardPosition(lastPos.getRow() + inc, lastPos.getColumn() + dec);
        }

        //resetting everything to check the \ diagonal direction
        doesWin = 1;
        inc = 1;
        dec = -1;

        //checks how many player chars are up and to the left of lastPos, and checks for win
        while(lastPos.getColumn() + dec >= 0 && lastPos.getRow() + dec >= 0
                &&
                isPlayerAtPos(newUpLeftPos, player)){
            doesWin++;
            if(doesWin == getNumToWin()){
                return true;
            }
            dec--;
            newUpLeftPos = new BoardPosition(lastPos.getRow() + dec, lastPos.getColumn() + dec);
        }

        //checks how many player chars are down and to the right of lastPos, and checks for win
        while(lastPos.getColumn() + inc < getNumColumns() && lastPos.getRow() + inc < getNumRows() &&
                isPlayerAtPos(newDownRightPos, player)){
            doesWin++;
            if(doesWin == getNumToWin()){
                return true;
            }
            inc++;
            newDownRightPos = new BoardPosition(lastPos.getRow() + inc, lastPos.getColumn() + inc);
        }

        return false;
    }

    /**
     * @param lastPos - last position a character was placed
     * @return true if this placement resulted in a win
     * @post true = any win condition true
     */
    public default boolean checkForWinner(BoardPosition lastPos){
        return checkHorizontalWin(lastPos, whatsAtPos(lastPos)) ||
                checkVerticalWin(lastPos, whatsAtPos(lastPos)) ||
                checkDiagonalWin(lastPos, whatsAtPos(lastPos));
    }

    /**
     * @post true = no space empty
     * @return true if all spaces have been filled
     */
    public default boolean checkForDraw(){
        for(int i = 0; i < getNumRows(); i++){
            for(int j = 0; j < getNumColumns(); j++){
                BoardPosition pos = new BoardPosition(i, j);
                if(checkSpace(pos)){
                    return false;
                }
            }
        }
        return true;
    }
}
