import javax.swing.*;
import java.awt.*;

/* Window Class
 * No description yet
 */

class Window extends JFrame {
    JPanel boardpanel, resetpanel;
    
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
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        
        JMenuItem menuItem = new JMenuItem("new...");
        menu.add(menuItem);
        
        return menuBar;
    }
    
    private JPanel createResetPanel() {
        // create panel
        resetpanel = new JPanel();
        resetpanel.setBackground(Color.LIGHT_GRAY);
        
        // create and add resetbutton
        JButton resetbutton = new JButton("R");
        resetbutton.setMargin(new Insets(0,0,0,0));
        resetbutton.setPreferredSize(new Dimension(45,45));
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
        // clear board panel and change grid and size for new dimensions
        boardpanel.removeAll();
        boardpanel.setLayout(new GridLayout(height, width));
        boardpanel.setPreferredSize(new Dimension(25*height,25*width));
        
        // reset BoardButton IDs and place new buttons into board
        for (int r=0; r<height; r++) {
            for (int c=0; c<width; c++) {
                boardpanel.add(new BoardButton(r,c));
            }
        }
        pack();
    }
}

