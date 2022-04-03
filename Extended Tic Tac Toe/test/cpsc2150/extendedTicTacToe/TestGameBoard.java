package cpsc2150.extendedTicTacToe;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {

    private IGameBoard gbFactory(int boardHeight, int boardWidth, int winVal){
        return new GameBoard(boardHeight, boardWidth, winVal);
    }

    private char[][] arrFactory(int l, int w){
        char[][] array = new char[l][w];
        for(char[] row: array) {
            Arrays.fill(row, ' ');
        }
        return array;
    }

    private String arrToStr(char[][] array){
        String str = "   ";

        //first row
        for(int i = 0; i < array.length; i++){
            str += String.format("%2s", i) + "|";
        }
        str += "\n";

        for(int i = 0; i < array.length; i++){
            //first part of each row
            str += String.format("%2s", i) + "|";

            for(int j = 0; j < array[i].length; j++){
                //rest of the row
                str += array[i][j] + " |";

                if(j == array[i].length - 1){
                    str += "\n";
                }
            }
        }
        return str;
    }

    //_______Constructor_________________________________________________________

    //tests smallest possible constructor
    @Test
    public void testConstructor_3_3_3(){
        IGameBoard gb = gbFactory(3, 3, 3);
        char[][] arr = arrFactory(3, 3);

        assertEquals(gb.toString(), arrToStr(arr));
    }

    //tests medium size constructor
    @Test
    public void testConstructor_50_50_50(){
        IGameBoard gb = gbFactory(50, 50, 50);
        char[][] arr = arrFactory(50, 50);

        assertEquals(gb.toString(), arrToStr(arr));
    }

    //tests largest possible constructor
    @Test
    public void testConstructor_100_100_100(){
        IGameBoard gb = gbFactory(100, 100, 100);
        char[][] arr = arrFactory(100, 100);

        assertEquals(gb.toString(), arrToStr(arr));
    }

    //_________CheckSpace______________________________________________________________

    //check filled area
    @Test
    public void testCheckSpace_filled(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 1);
        gb.placeMarker(bp, 'X');
        assertFalse(gb.checkSpace(bp));
    }

    //check blank area
    @Test
    public void testCheckSpace_blank(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 1);

        assertTrue(gb.checkSpace(bp));
    }

    //check check area outside of array
    @Test
    public void testCheckSpace_outOfArray(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(4, 5);

        assertFalse(gb.checkSpace(bp));
    }

    //_________CheckHorizontalWin_______________________________________________________

    //testing CHW starting from far left
    //markers go from end to end
    @Test
    public void testCHW_left(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(2, i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 0);
        assertTrue(gb.checkHorizontalWin(bp, 'X'));
    }

    //testing CHW starting from far right
    //markers go from end to end
    @Test
    public void testCHW_right(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(2, i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 4);
        assertTrue(gb.checkHorizontalWin(bp, 'X'));
    }

    //testing CHW starting from middle
    //markers go from end to end
    @Test
    public void testCHW_middle(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(2, i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 2);
        assertTrue(gb.checkHorizontalWin(bp, 'X'));
    }

    //testing CHW with <winVal markers
    @Test
    public void testCHW_false(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 4; i++){
            BoardPosition bp = new BoardPosition(2, i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 2);
        assertFalse(gb.checkHorizontalWin(bp, 'X'));
    }

    //__________CheckVerticalWin______________________________________________________

    //testing CVW starting from top
    //markers go from end to end
    @Test
    public void testCVW_top(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(i, 2);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(0, 2);
        assertTrue(gb.checkVerticalWin(bp, 'X'));
    }

    //testing CVW starting from bottom
    //markers go from end to end
    @Test
    public void testCVW_bottom(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(i, 2);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(4, 2);
        assertTrue(gb.checkVerticalWin(bp, 'X'));
    }

    //testing CVW starting from middle
    //markers go from end to end
    @Test
    public void testCVW_middle(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(i, 2);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 2);
        assertTrue(gb.checkVerticalWin(bp, 'X'));
    }

    //testing CVW with <WinVal markers
    @Test
    public void testCVW_false(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 4; i++){
            BoardPosition bp = new BoardPosition(i, 2);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 2);
        assertFalse(gb.checkVerticalWin(bp, 'X'));
    }

    //__________CheckDiagonalWin_____________________________________________________

    //testing CDW / starting top right
    //markers go from end to end
    @Test
    public void testCDW_upRight_topRight(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(i, 4 - i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(0, 4);
        assertTrue(gb.checkDiagonalWin(bp, 'X'));
    }

    //testing CDW / starting bottom left
    //markers go from end to end
    @Test
    public void testCDW_upRight_bottomLeft(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(i, 4 - i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(4, 0);
        assertTrue(gb.checkDiagonalWin(bp, 'X'));
    }

    //testing CDW / starting middle
    //markers go from end to end
    @Test
    public void testCDW_upRight_middle(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(i, 4 - i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 2);
        assertTrue(gb.checkDiagonalWin(bp, 'X'));
    }
    //testing CDW / with <winVal markers
    @Test
    public void testCDW_upRight_false(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 4; i++){
            BoardPosition bp = new BoardPosition(i, 4 - i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 2);
        assertFalse(gb.checkDiagonalWin(bp, 'X'));
    }

    //testing CDW \ starting top left
    //markers go from end to end
    @Test
    public void testCDW_upLeft_topLeft(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(i, i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(0, 0);
        assertTrue(gb.checkDiagonalWin(bp, 'X'));
    }

    //testing CDW \ starting top left
    //markers go from end to end
    @Test
    public void testCDW_upLeft_bottomRight(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(i, i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(4, 4);
        assertTrue(gb.checkDiagonalWin(bp, 'X'));
    }

    //testing CDW \ starting middle
    //markers go from end to end
    @Test
    public void testCDW_upLeft_middle(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 5; i++){
            BoardPosition bp = new BoardPosition(i, i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 2);
        assertTrue(gb.checkDiagonalWin(bp, 'X'));
    }

    //testing CDW \ with <winVal markers
    @Test
    public void testCDW_upLeft_false(){
        IGameBoard gb = gbFactory(5, 5, 5);
        for(int i = 0; i < 4; i++){
            BoardPosition bp = new BoardPosition(i, i);
            gb.placeMarker(bp, 'X');
        }

        BoardPosition bp = new BoardPosition(2, 2);
        assertFalse(gb.checkDiagonalWin(bp, 'X'));
    }

    //__________CheckForDraw_____________________________________________________________

    //test CFD with empty game board
    @Test
    public void testCFD_empty(){
        IGameBoard gb = gbFactory(3, 3, 3);

        assertFalse(gb.checkForDraw());
    }

    //test CFD with full game board
    @Test
    public void testCFD_full(){
        IGameBoard gb = gbFactory(3, 3, 3);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                BoardPosition bp = new BoardPosition(i, j);
                gb.placeMarker(bp, 'X');
            }
        }
        assertTrue(gb.checkForDraw());
    }

    //test CFD with full game board except first value
    @Test
    public void testCFD_fullExceptFirst(){
        IGameBoard gb = gbFactory(3, 3, 3);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                BoardPosition bp = new BoardPosition(i, j);
                if(i != 0 && j != 0) {
                    gb.placeMarker(bp, 'X');
                }
            }
        }
        assertFalse(gb.checkForDraw());
    }

    //test CFD with just 1 char in board
    @Test
    public void testCFD_1Char(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(0, 0);
        gb.placeMarker(bp, 'X');

        assertFalse(gb.checkForDraw());
    }

    //__________WhatsAtPos_________________________________________________________________

    //testing WAP with a blank
    @Test
    public void testWAP_blank(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 1);

        assert(gb.whatsAtPos(bp) == ' ');
    }

    //testing WAP in the mid-top position
    @Test
    public void testWAP_top(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(0, 1);
        gb.placeMarker(bp, 'X');

        assert(gb.whatsAtPos(bp) == 'X');
    }

    //testing WAP in the mid-right position
    @Test
    public void testWAP_right(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 2);
        gb.placeMarker(bp, 'X');

        assert(gb.whatsAtPos(bp) == 'X');
    }

    //testing WAP in the mid-bottom position
    @Test
    public void testWAP_bottom(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(2, 1);
        gb.placeMarker(bp, 'X');

        assert(gb.whatsAtPos(bp) == 'X');
    }

    //testing WAP in the mid-left position
    @Test
    public void testWAP_left(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 0);
        gb.placeMarker(bp, 'X');

        assert(gb.whatsAtPos(bp) == 'X');
    }

    //__________IsPlayerAtPos___________________________________________________________

    //testing IPAP with a blank
    @Test
    public void testIPAP_blank(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 1);

        assertFalse(gb.isPlayerAtPos(bp, 'X'));
    }

    //testing IPAP in the middle top position
    @Test
    public void testIPAP_top(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(0, 1);
        gb.placeMarker(bp, 'X');

        assert(gb.isPlayerAtPos(bp, 'X'));
    }

    //testing IPAP in the middle right position
    @Test
    public void testIPAP_right(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 2);
        gb.placeMarker(bp, 'X');

        assert(gb.isPlayerAtPos(bp, 'X'));
    }

    //testing IPAP in the middle bottom position
    @Test
    public void testIPAP_bottom(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(2, 1);
        gb.placeMarker(bp, 'X');

        assert(gb.isPlayerAtPos(bp, 'X'));
    }

    //testing IPAP in the middle left position
    @Test
    public void testIPAP_left(){
        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 0);
        gb.placeMarker(bp, 'X');

        assert(gb.isPlayerAtPos(bp, 'X'));
    }

    //__________PlaceMarker____________________________________________________

    //testing PM for top middle value
    @Test
    public void testPM_top(){

        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(0, 1);
        gb.placeMarker(bp, 'X');


        char[][] arr = arrFactory(3, 3);
        arr[0][1] = 'X';

        assertEquals(gb.toString(), arrToStr(arr));
    }

    //testing PM for right middle value
    @Test
    public void testPM_right(){

        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 2);
        gb.placeMarker(bp, 'X');


        char[][] arr = arrFactory(3, 3);
        arr[1][2] = 'X';

        assertEquals(gb.toString(), arrToStr(arr));
    }

    //testing PM for bottom middle value
    @Test
    public void testPM_bottom(){

        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(2, 1);
        gb.placeMarker(bp, 'X');


        char[][] arr = arrFactory(3, 3);
        arr[2][1] = 'X';

        assertEquals(gb.toString(), arrToStr(arr));
    }

    //testing PM for left middle value
    @Test
    public void testPM_left(){

        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 0);
        gb.placeMarker(bp, 'X');


        char[][] arr = arrFactory(3, 3);
        arr[1][0] = 'X';

        assertEquals(gb.toString(), arrToStr(arr));
    }

    //testing PM for middle value
    @Test
    public void testPM_middle(){

        IGameBoard gb = gbFactory(3, 3, 3);
        BoardPosition bp = new BoardPosition(1, 1);
        gb.placeMarker(bp, 'X');


        char[][] arr = arrFactory(3, 3);
        arr[1][1] = 'X';

        assertEquals(gb.toString(), arrToStr(arr));
    }
}

