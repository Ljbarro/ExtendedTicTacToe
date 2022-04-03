package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard {

    /**
     * @post toString = current board state
     * @return the current board state
     */
    @Override
    public String toString() {

        //init.ing the string
        String str = "   ";

        //adding the top row to the string
        for(int i = 0; i < getNumColumns(); i++){
            str += String.format("%2s", i) + "|";
        }
        str += "\n";

        for (int i = 0; i < getNumRows(); i++){

            //printing the first part of each row
            str += String.format("%2s", i) + "|";

            for(int j = 0; j < getNumColumns(); j++){

                //adds the " |" chunk to each row for each column, and gives a newline
                BoardPosition pos = new BoardPosition(i, j);
                str += whatsAtPos(pos) + " |";

                if(j == getNumColumns() - 1){
                    str += "\n";
                }
            }
        }
        return str;
    }
}
