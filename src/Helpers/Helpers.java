package Helpers;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;
import logic.Pawn;
import ui.SnakeAndLadders;

/**
 * 
 * @author maria
 */
public class Helpers {
    
    /**
     * Ελέγχει έαν ο χρήστης έχει πληκτρολογήσει όνομα και αν έχει επιλέξει πιόνι.
     * Εάν δεν ισχύουν τα παραπάνω το παιχνίδι δεν ξεκινάει.
     * @param name όνομα του χρήστη.
     * @param pawn το πιόνι του χρήστη.
     * @return boolean τιμή.
     */
    public static boolean isGameValidated(String name, Pawn pawn) {
        JFrame messageFrame = new JFrame();      
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(messageFrame, "Please fill out your name.");  
            return false;
        }
        
        if (pawn == null) {
            JOptionPane.showMessageDialog(messageFrame, "Please choose pawn.");  
            return false;
        }
        
        return true;
    }
    
    public static void showWinnerMessage(Pawn pawn) {
        JFrame winnerMessage = new JFrame();
        JOptionPane.showMessageDialog(winnerMessage, pawn.getName() + " IS THE WINNER!");
    }
    
    public static void initUIAfterGameStarted(Pawn human, Pawn computer, JLabel diceIcon) {
        SnakeAndLadders.namePanel.setVisible(false);
        SnakeAndLadders.levelsPanel.setVisible(false);
        SnakeAndLadders.pawnOptionsPanel.setVisible(false);
        SnakeAndLadders.startBtn.setVisible(false);
        SnakeAndLadders.timerPanel.setVisible(true);
        SnakeAndLadders.pawnChoicesPanel.setVisible(true);
        SnakeAndLadders.playerTurnPanel.setVisible(true);
        diceIcon.setVisible(true);
        SnakeAndLadders.rollDiceBtn.setVisible(true);
        SnakeAndLadders.playerTurnLabel.setText(human.getName() + " TURN!");        
        Helpers.addPawnsChoices(human, computer);
    }
    
    public static void resetUIAfterNewGame(JLabel diceIcon) {
        SnakeAndLadders.namePanel.setVisible(true);
        SnakeAndLadders.levelsPanel.setVisible(true);
        SnakeAndLadders.pawnOptionsPanel.setVisible(true);
        SnakeAndLadders.startBtn.setVisible(true);
        SnakeAndLadders.timerPanel.setVisible(false);
        SnakeAndLadders.pawnChoicesPanel.setVisible(false);
        SnakeAndLadders.playerTurnPanel.setVisible(false);  
        SnakeAndLadders.rollDiceBtn.setVisible(false);
        SnakeAndLadders.stopwatch.reset();
        SnakeAndLadders.pawnChoicesPanel.removeAll();
        diceIcon.setIcon(new ImageIcon(Helpers.class.getResource("../images/animated_dice.gif")));
        diceIcon.setVisible(false);
    }
 
    public static JPanel createPanel(LayoutManager layout) {
        JPanel container = new JPanel();
        container.setLayout(layout);
        container.setMaximumSize(new Dimension(240, 80));
        container.setAlignmentX(CENTER_ALIGNMENT);
        return container;
    }
    
    /**
     * Προσθέτει στο pawnChoicesPanel τις επιλογές πιονών των παικτών και των ονομάτων.
     * @param human το πιόνι του χρήστη
     * @param computer το πιόνι του υπολογιστή.
     */
    private static void addPawnsChoices(Pawn human, Pawn computer) {       
        JLabel playerLabel = new JLabel(human.getName());
        JLabel computerLabel = new JLabel(computer.getName());
        Border whiteLine = BorderFactory.createLineBorder(Color.white, 3);
        SnakeAndLadders.pawnChoicesPanel.setBackground(new Color(242, 208, 222));
        SnakeAndLadders.pawnChoicesPanel.setBorder(whiteLine);
        SnakeAndLadders.pawnChoicesPanel.add(playerLabel);
        SnakeAndLadders.pawnChoicesPanel.add(new JLabel(human.getIcon()));
        SnakeAndLadders.pawnChoicesPanel.add(computerLabel);
        SnakeAndLadders.pawnChoicesPanel.add(new JLabel(computer.getIcon()));
    }
    
    public static JButton createButton(String buttonName) {
        JButton button = new JButton(buttonName);
        button.setMaximumSize(new Dimension(240, 40));
        button.setBackground(new Color(59, 89, 182));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(Color.white);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        return button;
    }
    
    public static ImageIcon scaleImage(String path, int w, int h) {
        URL resource = Helpers.class.getResource(path);
        ImageIcon icon;
        Image image;
        
        icon = new ImageIcon(resource);
        image = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        return icon;
    }
}
