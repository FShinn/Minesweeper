import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Dialog Class
 * No description yet
 */

class Dialog extends JDialog {
    JPanel inputPanel, confirmPanel;
    
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
        
        return inputPanel;
    }
    
    private JPanel createConfirmPanel() {
        // create panel
        confirmPanel = new JPanel();
        confirmPanel.setBackground(Color.GRAY);
        
        return confirmPanel;
    }
}

