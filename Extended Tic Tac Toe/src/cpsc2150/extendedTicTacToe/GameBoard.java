package cpsc2150.extendedTicTacToe;

public class GameBoard extends AbsGameBoard {

    //row and column info
    private int boardHeight = 0;
    private int boardWidth = 0;

    private static final char blankChar = ' ';
    private char[][] gBoard;
    private int winVal = 0;

    /**
     * @pre 3 <= bH <= 100
     * @pre 3 <= bW <= 100
     * @pre wV <= bH
     * @pre wV <= bW
     * @post new blank array length & height = 8
     */
    GameBoard(int bH, int bW, int wV){
        boardHeight = bH;
        boardWidth = bW;
        winVal = wV;

        gBoard = new char[boardHeight][boardWidth];

        for(int i = 0; i < boardHeight;i++){
            for( int j = 0; j < boardWidth; j++){
                gBoard[i][j] = blankChar;
            }
        }
    }

    /**
     * @pre pos must be a valid board location
     * @param marker input board position
     * @param player - most recent player char
     * @post marker char = player char
     */
    public void placeMarker(BoardPosition marker, char player){
        gBoard[marker.getRow()][marker.getColumn()] = player;
    }

    /**
     * @pre pos within board boundaries
     * @param pos - current position being evaluated
     * @post whatsAtPos = char of gameboard at input position
     * @return the character at pos
     */
    public char whatsAtPos(BoardPosition pos){
        return gBoard[pos.getRow()][pos.getColumn()];
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
}
