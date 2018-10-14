import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* BoardButton Class
 * No description yet
 */

class BoardButton extends JButton implements MouseListener {
    int r, c; // row and column coordinates of this button in board
    static final Color defaultcolor = new JButton().getBackground();
    
    public BoardButton (int row, int col) {
        // create the button
        super();
        setMargin(new Insets(0,0,0,0));
        addMouseListener(this);
        
        // set row and col identifiers for button
        r = row;
        c = col;
        
        Minesweeper.attachDisplay(this, r, c);
    }
    
    public void mouseClicked(MouseEvent e) {
        // button has been clicked, reveal or flag?
        if (e.getButton() == MouseEvent.BUTTON1) {
            Minesweeper.reveal(r,c);
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            Minesweeper.toggleFlag(r,c);
        }
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
    
    public void updateDisplay(Cell cell) {
        // set button display according to cell properties
        if (cell.isRevealed()) {
            setEnabled(false);
            if (cell.isMine()) {
                setBackground(Color.RED);
            }
            else {
                setText("" + cell.getAdjacentMineCount());
            }
        }
        else {
            setEnabled(true);
            if (cell.isFlagged()) {
                setBackground(Color.ORANGE);
            }
            else {
                setBackground(defaultcolor);
            }
        }
    }
}

