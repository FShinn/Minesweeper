import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Window Class
 * 
 * An instance of this Window class is an object of JFrame type.
 * It is the primary window and manager of the GUI for Minesweeper.
 * Its primary responsibility is to set all the internal properties of a JFrame
 * object to the desired settings for the Minesweeper program.
 * 
 * TODO:
 * Should probably convert from "extending JFrame" to "containing JFrame"
 *
 * Data protection
 */

class Window extends JFrame {
    JPanel boardpanel, resetpanel;
    JButton resetbutton;
    
    public Window () {
        // create window
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // menu bar
        setJMenuBar(createMenuBar());
        
        // add content
        getContentPane().add(createResetPanel(),BorderLayout.PAGE_START);
        getContentPane().add(createBoardPanel(),BorderLayout.CENTER);
        
        // display window
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JMenuBar createMenuBar() {
        // create menu bar 
        JMenuBar menuBar = new JMenuBar();
        
        // add options menu to menu bar
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        
        // add "new..." button to options menu
        Window w = this;
        JMenuItem menuItem = new JMenuItem("new...");
        menuItem.addActionListener(  // "new..." button functionality 
            new AbstractAction("new") {
                public void actionPerformed(ActionEvent e) {
                    Dialog dialog = new Dialog(w);
                }
            }
        );
        menu.add(menuItem);
        
        return menuBar;
    }
    
    private JPanel createResetPanel() {
        // create panel
        resetpanel = new JPanel();
        resetpanel.setBackground(Color.LIGHT_GRAY);
        
        // create and add resetbutton
        resetbutton = new JButton("R");
        resetbutton.setMargin(new Insets(0,0,0,0));
        resetbutton.setPreferredSize(new Dimension(45,45));
        resetbutton.addActionListener( // reset button functionality 
            new AbstractAction("reset") {
                public void actionPerformed(ActionEvent e) {
                    Minesweeper.newBoard();
                }
            }
        );
        resetpanel.add(resetbutton,BorderLayout.CENTER);
        
        return resetpanel;
    }
    
    private JPanel createBoardPanel() {
        // create panel
        boardpanel = new JPanel();
        boardpanel.setBackground(Color.GRAY);
        
        // board details will be updated by Minesweeper.newGame()
        return boardpanel;
    }
    
    
    public void resetBoard(int height, int width) {
        // Minesweeper class calls this function when reseting the game/board
        // reset resetButton
        resetbutton.setText("R");
        
        // clear board panel and change grid and size for new dimensions
        boardpanel.removeAll();
        boardpanel.setLayout(new GridLayout(height, width));
        boardpanel.setPreferredSize(new Dimension(25*width,25*height));
        
        // reset BoardButton IDs and place new buttons into board
        for (int r=0; r<height; r++) {
            for (int c=0; c<width; c++) {
                boardpanel.add(new BoardButton(r,c));
            }
        }
        pack();
    }
    
    public void gameWon() {
        // call gameWon() to update display to indicate the game is won
        resetbutton.setText("WIN!");
        pack();
    }
    
    public void gameLost() {
        // call gameLost() to update display to indicate the game is lost
        resetbutton.setText("LOSE");
        pack();
    }
}

