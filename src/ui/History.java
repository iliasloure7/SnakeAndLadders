package ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Διαχειρίζεται την αποθήκευσει των συμβάντων.
 * @author maria
 */
public class History extends JFrame {
    
    private final JPanel eventPanel;
    private final JScrollPane scrollPane;
    private final JLabel eventStateLabel;
    private final JLabel event;
            
    public History() {
        this.setTitle("History");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE );
        this.setLocation(SnakeAndLadders.FRAME_WIDTH, 0);
        this.setSize(500, 150);
        
        eventStateLabel = new JLabel("STATE");
        event = new JLabel("EVENT");
        
        eventStateLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        event.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        eventPanel = new JPanel();
        eventPanel.setLayout(new GridLayout(0, 2, 8, 8));
        eventPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
        
        eventPanel.add(eventStateLabel);
        eventPanel.add(event);
           
        scrollPane = new JScrollPane(eventPanel, 
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        this.add(scrollPane);
    }
    
    /**
     * Θέτει τα συμβάντα στο ιστορικό.
     * @param eventState κατάσταση πιονιού. (π.χ απλό-ειδικό τετράγωνο, φίδι, σκάλα)
     * @param event το τετράγωνο στο οποίο μετακινήθηκε ο παίκτης.
     */
    public void setEvent(String eventState, String event) {
        JTextField eventStateTextField = new JTextField();
        JTextField eventTextField = new JTextField();
        eventStateTextField.setText(eventState);
        eventStateTextField.setEditable(false);
        eventTextField.setText(event);
        eventTextField.setEditable(false);
        eventPanel.add(eventStateTextField);
        eventPanel.add(eventTextField, event);
        this.validate();
    }
    
    /**
     * Καθαρίζει το ιστορικό μέτα από επιλογή νέου ή επανεκκίνησης παιχνιδιού.
     */
    public void clear() {
        for (Component component : eventPanel.getComponents())
            if (component instanceof JTextField)
                eventPanel.remove(component);
        this.setVisible(false);
    }

}
