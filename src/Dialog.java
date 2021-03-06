import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

/* Dialog Class
 * 
 * An instance of this class contains a JDialog object,
 * which in turn provides board configuration options.
 * It is instanciated whenever the user selects "new..." from the options menu.
 */

class Dialog {
    private JDialog dialog;
    private JTextField height,width,mines;
    
    public Dialog (JFrame owner) {
        // create dialog
        dialog  = new JDialog(owner, "Settings");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // add content
        dialog.getContentPane().add(createInputPanel(),BorderLayout.CENTER);
        dialog.getContentPane().add(createConfirmPanel(),BorderLayout.PAGE_END);
        
        // display window
        dialog.pack();
        dialog.setLocationRelativeTo(owner);
        dialog.setVisible(true);
    }
    
    private JPanel createInputPanel() {
        // create panel
        JPanel inputPanel = new JPanel();
        // pads the panel
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // params: rows, cols, hgap, vgap
        inputPanel.setLayout(new GridLayout(3, 2, 25, 7));
        
        // create JTextFields with current board properties
        height = new JTextField(Integer.toString(Minesweeper.getHeight()));
        width = new JTextField(Integer.toString(Minesweeper.getWidth()));
        mines = new JTextField(Integer.toString(Minesweeper.getMines()));
        
        // add content
        inputPanel.add(new JLabel("Height"));
        inputPanel.add(height);
        inputPanel.add(new JLabel("Width"));
        inputPanel.add(width);
        inputPanel.add(new JLabel("Mines"));
        inputPanel.add(mines);
        
        return inputPanel;
    }
    
    private JPanel createConfirmPanel() {
        // create panel
        JPanel confirmPanel = new JPanel();
        
        // create and add confirmbutton
        JButton confirmbutton = new JButton("OK");
        // add reset functionality to button
        confirmbutton.addActionListener(
            new AbstractAction("newGame") {
                public void actionPerformed(ActionEvent e) {
                    int h = Integer.parseInt(height.getText());
                    int w = Integer.parseInt(width.getText());
                    int m = Integer.parseInt(mines.getText());
                    Minesweeper.newBoard(h,w,m);
                    dialog.dispose();
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
                    dialog.dispose();
                }
            }
        );
        confirmPanel.add(cancelbutton);
        
        return confirmPanel;
    }
}

