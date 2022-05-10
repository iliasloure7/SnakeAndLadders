package logic;

import java.awt.Image;
import javax.swing.ImageIcon;
import ui.GameBoard;
import Helpers.Helpers;

/**
 * Διαχειρίζεται τις καταστάσεις πιονιών.
 * @author maria
 */
public class Pawn {
    
    private final int SIZE = 40;
    
    private String imageURL;
    private String name;
    private ImageIcon icon;
         
    private int x;
    private int y;
    private int row = 0;
    
    private boolean isInRethrowState;
    private boolean isInLuckyState;
    private boolean isInReverseState;
    private boolean isInTurtleState;
     
    public Pawn(String imageURL) {
        this.imageURL = imageURL;
        this.icon = Helpers.scaleImage(imageURL, 40, 40);
        this.row = 0;
        this.x = 0;
        this.y = GameBoard.SIZE - SIZE;
        this.isInReverseState = false;
        this.isInLuckyState = false;
        this.isInTurtleState = false;
        this.isInRethrowState = false;
    } 
    
    /**
     * Χρησιμοποιείται σε περίπτωση σκάλας ή φιδιού.
     * @param x συντεταγμένη x 
     * @param y συντενταγμένη y
     */
    public void move(int x, int y) { 
        this.setX(x);
        this.setY(y);
        int newRow = 10 - (this.y / GameBoard.SQUARE_SIZE) - 1;
        this.setRow(newRow);
    }
    
    /**
     * Έλεγχει τη μετακίνηση του πιουνιού.
     * Υπάρχουν 2 περιπτώσεις ελέγχου.
     * Έαν το πιόνι έχει πατησει σε τετράγωνω reverse square τότε κινείται προς τα πίσω.
     * Αλλιώς εκτελεί κανονική μετακίνηση.
     * @param distance η απόσταση στην οποία μετακινείται το πιόνι. 
     */
    public void move(int distance) {
        if (this.isInReverseState) { 
            this.moveBackward(distance);
            return;
        }
        this.moveForward(distance);
    }
    
    /**
     * Εκτελεί την κίνηση του πιονιού προς τα εμπρός.
     * Ελέγχει επίσης τις περιπτώσεις κινήσεων είτε από δεξία είτε από αριστερά.
     * @param distance η απόσταση στην οποία μετακινείται το πιόνι.
     */
    private void moveForward(int distance) {
        if (this.row % 2 == 0) {
            this.x += distance;
            this.checkRightBounds();
            return;
        }   
        if (this.x - distance < 0 && this.y == 20) 
            return;
        this.x -= distance;
        this.checkLeftBounds();
    }
    
    /**
     * Εκτελεί την κίνηση του πιονιού προς τα πίσω σε περίπτωση που το πιόνι,
     * έχει πατήσει σε τετράγωνο τύπου reverse square.
     * @param distance η απόσταση στην οποία μετακινείται το πιόνι.
     */
    private void moveBackward(int distance) {
        if (this.row % 2 == 1) {
            this.x += distance;
            this.checkRightBounds();
            return;
        }
        if (this.x - distance < 0 && this.y == 560) {
            return;
        }
        this.x -= distance;
        if (this.x == 0 && this.y == 560) this.isInReverseState = false;
        this.checkLeftBounds();
    }
    
    /**
     * Ελέγχει τα όρια του επιτραπέζιου όταν το πιόνει εκτελεί κίνηση προς τα δεξιά.
     */
    private void checkRightBounds() {
        int currentPos = this.x;
        if (currentPos < GameBoard.SIZE) return;
        
        int newPos = currentPos - GameBoard.SIZE;
        this.x = 540 - newPos; 
        
        if (this.isInReverseState) { 
            this.y += GameBoard.SQUARE_SIZE;
            this.row--;
            return;
        } 
        this.y -= GameBoard.SQUARE_SIZE;
        this.row++;        
    }
    
     /**
     * Ελέγχει τα όρια του επιτραπέζιου όταν το πιόνει εκτελεί κίνηση προς τα αριστερά.
     */
    private void checkLeftBounds() {
        int currentPos = this.x;
        if (currentPos >= 0) return;
        
        int newPos = Math.abs(currentPos);
        this.x = newPos - GameBoard.SQUARE_SIZE;     
       
        if (this.isInReverseState) { 
            this.y += GameBoard.SQUARE_SIZE;
            this.row--;
            return;
        }
        this.y -= GameBoard.SQUARE_SIZE;
        this.row++;        
    }
    
    /**
     * @return το τετράγωνο στο οποίο βρίσκεται το πιόνι.  
     */
    public int findSquareNumber() {
        int square;
        int row = this.row;
        int x = this.x;    
        
        if (this.row % 2 == 0) 
            square = row * 10 + (x / GameBoard.SQUARE_SIZE + 1);
        else
            square = row * 10 + (10 - x / GameBoard.SQUARE_SIZE);      
        
        return square;
    }
    
    /**
     * Επαναφέρει τις καταστάσεις του πιονιού μετά απο επαννεκίνηση ή νέο παιχνίδι.
     */
    public void resetState() {
        this.isInRethrowState = false;
        this.isInLuckyState = false;
        this.isInReverseState = false;
        this.isInTurtleState = false;
    }
    
    public void moveToStartSquare() {
        this.x = 0;
        this.y = 560;
        this.row = 0;
    }
    
    public void moveToEndSquare() {
        this.x = 0;
        this.y = 20;
        this.row = 9;
    }
  
    public ImageIcon getIcon() {
        return icon;
    }
    
    public Image getImage() {
        return icon.getImage();
    }
    
    public int getSize() {
        return SIZE;
    }

    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getRow() {
        return row;
    }
    
    public void setRow(int row) { 
        this.row = row;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }  
    
    public boolean getIsInRethrowState() {
        return isInRethrowState;
    }
    
    public void setIsInRethrowState(boolean isInRethrowState) {
        this.isInRethrowState = isInRethrowState;
    } 
    
    public boolean getIsInLuckyState() {
        return isInLuckyState;
    }
    
    public void setIsInLuckyState(boolean isInLuckyState) {
        this.isInLuckyState = isInLuckyState;
    }
    
    public boolean getIsInReverseState() {
        return isInReverseState;
    }
    
    public void setIsInReverseState(boolean isInReverseState) { 
        this.isInReverseState = isInReverseState;
    }
    
    public boolean getIsInTurtleState() {
        return isInTurtleState;
    }
    
    public void setIsInTurtleState(boolean isInTurtleState) {
        this.isInTurtleState = isInTurtleState;
    }
}
