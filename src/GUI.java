import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* GUI Class
 *
 * An instance of this GUI class contains the main window, a JFrame object.
 * It manages the GUI for Minesweeper.
 */

class GUI {
    private JFrame window;
    private JPanel boardpanel;
    private JButton resetbutton;
    
    public GUI () {
        // create window
        window = new JFrame("Minesweeper");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        // menu bar
        window.setJMenuBar(createMenuBar());
        
        // add content
        window.getContentPane().add(createResetPanel(),BorderLayout.PAGE_START);
        window.getContentPane().add(createBoardPanel(),BorderLayout.CENTER);
        
        // display window
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    private JMenuBar createMenuBar() {
        // create menu bar 
        JMenuBar menuBar = new JMenuBar();
        
        // add options menu to menu bar
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        
        // add "new..." button to options menu
        JMenuItem menuItem = new JMenuItem("new...");
        menuItem.addActionListener(  // "new..." button functionality 
            new AbstractAction("new") {
                public void actionPerformed(ActionEvent e) {
                    Dialog dialog = new Dialog(window);
                }
            }
        );
        menu.add(menuItem);
        
        return menuBar;
    }
    
    private JPanel createResetPanel() {
        // create panel
        JPanel resetpanel = new JPanel();
        
        // create and add resetbutton
        resetbutton = new JButton("NEW");
        resetbutton.addActionListener( // reset button functionality 
            new AbstractAction("new") {
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
        
        // board details will be updated by Minesweeper.newGame()
        return boardpanel;
    }
    
    
    public void resetBoard(int height, int width) {
        // Minesweeper class calls this function when reseting the game/board
        // reset resetButton
        resetbutton.setText("NEW");
        
        // clear board panel and change grid and size for new dimensions
        boardpanel.removeAll();
        boardpanel.setLayout(new GridLayout(height, width));
        boardpanel.setPreferredSize(new Dimension(20*width,20*height));
        
        // reset BoardButton IDs and place new buttons into board
        for (int r=0; r<height; r++) {
            for (int c=0; c<width; c++) {
                boardpanel.add(new BoardButton(r,c));
            }
        }
        window.pack();
    }
    
    public void gameWon() {
        // call gameWon() to update display to indicate the game is won
        resetbutton.setText("WIN!");
        window.pack();
    }
    
    public void gameLost() {
        // call gameLost() to update display to indicate the game is lost
        resetbutton.setText("LOSE");
        window.pack();
    }
}

