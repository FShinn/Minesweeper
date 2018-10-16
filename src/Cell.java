/* Cell Class
 * 
 * An instances of this class is a logical cell in the Minesweeper board.
 * Various cell properties are stored here
 * which are altered via the various behaviours provided.
 *
 * A corresponding display of type BoardButton should be attached to each
 * Cell instance, which will be updated when the Cell instance changes.
 */

class Cell {
    Boolean revealed;
    Boolean mine;
    Boolean flag;
    int adjacentMineCount;
    BoardButton display;
    
    public Cell () {
        // Constructs the cell and sets default properties
        revealed = false;
        mine = false;
        flag = false;
        adjacentMineCount = 0;
    }
    
    // various getter/setter methods for certain properties
    public void setMine() { mine = true; }
    public Boolean isRevealed() { return revealed; }
    public Boolean isMine() { return mine; }
    public Boolean isFlagged() { return flag; }
    // call incAdjacentMineCount when laying a mine in adjacent cell
    public void incAdjacentMineCount() { adjacentMineCount += 1; }
    public int getAdjacentMineCount() { return adjacentMineCount; }
    
    // methods which require updating the display
    public void reveal() {
        revealed = true; 
        display.updateDisplay(this); 
    }
    public void toggleFlag() { 
        flag = !flag; 
        display.updateDisplay(this); 
    }
    
    // method to attach the display component
    public void attachDisplay(BoardButton d) { 
        display = d; 
        display.updateDisplay(this);
    }
}

