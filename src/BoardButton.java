import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* BoardButton Class
 * 
 * This class extends JButton and is therefore instances of this class are the
 *  buttons which comprise the Minesweeper game board.
 * This class contains row and column data to identify the button's coordinates.
 * This class also implements MouseListener, which provides the functionality for the
 *  button.
 * 
 * TODO: 
 * r and c only get used in the implementation of mouselistener.
 * Instead of storing r and c and implementing MouseListener,
 *  The constructor should attach an anon MouseListener impelementation
 *  and discard r and c values.
 * 
 * Should we "extend JButton", or should we "contain a JButton" ? Hmm...
 *
 * Data protection
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

