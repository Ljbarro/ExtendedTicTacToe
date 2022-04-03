package cpsc2150.extendedTicTacToe;

public class BoardPosition {

    private int row;
    private int col;

    /**
     * @param r - row input
     * @param c - column input
     * @post new BoardPosition object
     */
    BoardPosition(int r, int c){
        row = r;
        col = c;
    }

    /**
     * @post getRow = row
     * @return position row
     */
    public int getRow(){
        return row;
    }

    /**
     * @post getColumn = col
     * @return position column
     */
    public int getColumn(){
        return col;
    }

    /**
     * @post toString = "<row>,<column>"
     * @return "<row>,<column>"
     */
    @Override
    public String toString(){
        return row + "," + col;
    }

    /**
     * @param pos - board position being compared to
     * @return true if 2 board positions are the same, false otherwise
     */
    @Override
    public boolean equals(Object pos){
        if(pos instanceof BoardPosition){
            BoardPosition newPos = (BoardPosition)pos;
            return this.row == newPos.row && this.col == newPos.col;
        }
        else{
            return false;
        }
    }
}
