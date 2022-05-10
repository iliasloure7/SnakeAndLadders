package logic.squares;

import java.awt.Image;
import javax.swing.ImageIcon;
import logic.*;
import Helpers.Helpers;
import ui.GameBoard;

/**
 * Αφηρημένη κλάση, στην οποία οι υποκλάσεις της θα διαχειρίζονται την ειδικότητητα
 * που εκτελεί το αντίστοιχο τετράγωνο.
 * @author maria
 */
public abstract class SpecialSquare {
    
    private final int SIZE = GameBoard.SQUARE_SIZE;
    
    private String imageURL;
    private ImageIcon icon;
    
    public SpecialSquare(String imageURL) {
        this.icon = Helpers.scaleImage(imageURL, SIZE, SIZE);
    }
    
    public abstract void specialty(Pawn pawn);
        
    public Image getImage() {
        return icon.getImage();
    }
    
    public int getSize() {
        return SIZE;
    }
    
}
