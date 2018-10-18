import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* BoardButton Class
 * 
 * This class extends JButton and therefore instances of this class are the
 *  buttons which comprise the Minesweeper game board.
 * The constructor for this class requires row and column data to identify the
 *  button's coordinates.
 * 
 * This class must extend JButton rather than contain it because it must be
 *  considered as a JButton by the JPanel class containing it, specifically the 
 *  instance named 'boardpanel" in the GUI class.
 * A JButton 'as is' would not do either, as we need the added functionality given by
 *  the updateDisplay(..) function supplied here by the BoardButton class.
 */

class BoardButton extends JButton {
    private static final Color DEFAULTCOLOR = new JButton().getBackground();
    
    public BoardButton (int row, int col) {
        // create the button
        super();
        setMargin(new Insets(0,0,0,0));
        
        // add button functionality
        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                // button has been clicked, reveal or flag?
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Minesweeper.reveal(row,col);
                }
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    Minesweeper.toggleFlag(row,col);
                }
            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        });
        
        // attach this BoardButton to corresponding cell via Minesweeper class
        Minesweeper.attachDisplay(this, row, col);
    }
    
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
                setBackground(DEFAULTCOLOR);
            }
        }
    }
}

