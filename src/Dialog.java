import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

/* Dialog Class
 * No description yet
 */

class Dialog extends JDialog {
    JPanel inputPanel, confirmPanel;
    JTextField height,width,mines;
    
    public Dialog (Window owner) {
        // create dialog
        super(owner, "Settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // add content
        getContentPane().add(createInputPanel(),BorderLayout.CENTER);
        getContentPane().add(createConfirmPanel(),BorderLayout.PAGE_END);
        
        // display window
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel createInputPanel() {
        // create panel
        inputPanel = new JPanel();
        inputPanel.setBackground(Color.LIGHT_GRAY);
        // pads the panel
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // params: rows, cols, hgap, vgap
        inputPanel.setLayout(new GridLayout(3, 2, 25, 7));
        
        // add content
        inputPanel.add(new JLabel("Height"));
        inputPanel.add((height = new JTextField()));
        inputPanel.add(new JLabel("Width"));
        inputPanel.add((width = new JTextField()));
        inputPanel.add(new JLabel("Mines"));
        inputPanel.add((mines = new JTextField()));
        
        return inputPanel;
    }
    
    private JPanel createConfirmPanel() {
        // create panel
        confirmPanel = new JPanel();
        confirmPanel.setBackground(Color.LIGHT_GRAY);
        
        // create and add confirmbutton
        JButton confirmbutton = new JButton("OK");
        // add reset functionality to button
        confirmbutton.addActionListener(
            new AbstractAction("newGame") {
                public void actionPerformed(ActionEvent e) {
                    Minesweeper.newBoard();
                }
            }
        );
        confirmPanel.add(confirmbutton);
        
        // create and add cancelbutton
        JButton cancelbutton = new JButton("Cancel");
        // add reset functionality to button
        cancelbutton.addActionListener(
            new AbstractAction("Cancel") {
                public void actionPerformed(ActionEvent e) {
                    Minesweeper.newBoard();
                }
            }
        );
        confirmPanel.add(cancelbutton);
        
        return confirmPanel;
    }
}

