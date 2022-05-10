package logic;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Διαχειρίζεται το ζάρι.
 * @author ilias
 */
public class Dice {
    
    private String imageURL;
    private ImageIcon icon;
    private JLabel diceIcon;
    
    private int number;
    
    public Dice(String imageURL) {
        icon = new ImageIcon(getClass().getResource(imageURL));
        diceIcon = new JLabel(icon);
        diceIcon.setAlignmentX(CENTER_ALIGNMENT);
    }
    
    public JLabel getIcon() {
        return diceIcon;
    }
    
    public void roll() {
        Random randomNumber = new Random();
        this.number = randomNumber.nextInt(6) + 1;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
}
