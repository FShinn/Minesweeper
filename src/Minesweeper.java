import java.util.Random;

/* Minesweeper Class
 * 
 * Main class for the Minesweeper program
 * This class holds data and methods for the logic and management of
 * the core Minesweeper components. 
 *
 * TODO:
 * Put protections on data in this class
 */

class Minesweeper {
    static int height = 8;
    static int width = 8;
    static int mines = 10;
    static int remaining;
    static Boolean gameOver;
    static Cell[][] board;
    static Window gui;
    
    public static void main (String[] args) {
        // instanciates the GUI and starts a new game
        gui = new Window();
        newBoard();
    }
    
    
    public static void newBoard(int h, int w, int m) {
        // wrapper for newBoard with new properties
        height = h; 
        width = w; 
        mines = m;
        newBoard();
    }
    public static void newBoard() {
        // creates a new logical board
        board = new Cell[height][width];
        remaining = height*width - mines;
        gameOver = false;
        
        // create new cells for all board entries
        for (int r=0; r<height; r++) {
            for (int c=0; c<width; c++) {
                board[r][c] = new Cell();
            }
        }
        
        // lay mines in logical board
        int row,col;
        for (int m=Math.min(mines,height*width); m>0; m--) {
            row = new Random().nextInt(height);
            col = new Random().nextInt(width);
            if (board[row][col].isMine()) {
                m++; // spot already has mine, try again
            }
            else { // increase neighbor counts of neighbors
                board[row][col].setMine();
                for (int r=Math.max(0,row-1); r<Math.min(height,row+2); r++) {
                    for (int c=Math.max(0,col-1); c<Math.min(width,col+2); c++) {
                        board[r][c].incAdjacentMineCount();
                    }
                }
            }
        }
        
        // update gui with new board
        gui.resetBoard(height, width);
    }
    
    
    public static void reveal(int row, int col) {
        // logic for revealing a targeted cell
        if (board[row][col].isRevealed() || gameOver) {
            return;
        }
        board[row][col].reveal();
        remaining -= 1;
        if (board[row][col].isMine()) {
            lose();
        }
        else if (remaining == 0) {
            win();
        }
        else if (board[row][col].getAdjacentMineCount() == 0) {
            for (int r=Math.max(0,row-1); r<Math.min(height,row+2); r++) {
                for (int c=Math.max(0,col-1); c<Math.min(width,col+2); c++) {
                    reveal(r,c);
                }
            }
        }
    }
    public static void toggleFlag(int r, int c) {
        // flags a targeted cell
        board[r][c].toggleFlag();
    }
    public static void attachDisplay(BoardButton b, int r, int c) {
        // attaches display object BoardButton to logical Cell object
        board[r][c].attachDisplay(b);
    }
    
    
    private static void lose() {
        // game logic for when game is lost
        gui.gameLost();
        System.out.println("lose");
        gameOver = true;
    }
    private static void win() {
        // game logic for when game is won
        gui.gameWon();
        System.out.println("win");
        gameOver = true;
    }
}

