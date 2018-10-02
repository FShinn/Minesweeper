/* Cell Class
 * No description yet
 */

class Cell {
    Boolean revealed;
    Boolean mine;
    Boolean flag;
    int adjacentMineCount;
    BoardButton display;
    
    public Cell () {
        revealed = false;
        mine = false;
        flag = false;
        adjacentMineCount = 0;
    }
    
    public void setMine() { mine = true; }
    public Boolean isRevealed() { return revealed; }
    public Boolean isMine() { return mine; }
    public Boolean isFlagged() { return flag; }
    public void incAdjacentMineCount() { adjacentMineCount += 1; }
    public int getAdjacentMineCount() { return adjacentMineCount; }
    
    
    public void reveal() { 
        revealed = true; 
        display.updateDisplay(this); 
    }
    public void toggleFlag() { 
        flag = !flag; 
        display.updateDisplay(this); 
    }
    
    
    public void attachDisplay(BoardButton d) { 
        display = d; 
        display.updateDisplay(this);
    }
}

