package logic.levels;

import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.ImageIcon;
import logic.squares.SpecialSquare;
import logic.Pawn;

/**
 * Αφηρημένη κλάση στην οποία οι υποκλάσεις της, θα διαχείριζονται 
 * τις συντεταγμένες των φιδιών, σκαλών και ειδικών τετραγώνων 
 * πάνω στην εικόνα του αντίστοιχου επιπέδου.
 * @author ilias
 */
public abstract class Level {
    
    private String boardImageURL;
    protected Pawn human, computer;
    protected HashMap<Point, Point> snakes;
    protected HashMap<Point, Point> ladders;
    protected HashMap<Point, SpecialSquare> specialSquares;
    
    public Level(String boardImageURL) {
        this.boardImageURL = boardImageURL;
        this.snakes = new HashMap<Point, Point>();
        this.ladders = new HashMap<Point, Point>();
        this.specialSquares = new HashMap<Point, SpecialSquare>();
    }
    
    public abstract void initSnakesCoordinates();
    public abstract void initLaddersCoordinates();
    public abstract void initSpecialSquaresCoordinates();
    
    public String getBoardImageURL() {
        return boardImageURL;
    }
    
    public HashMap<Point, Point> getSnakes() {
        return snakes;
    }
    
    public void setSnakes(HashMap<Point, Point> snakes) {
        this.snakes = snakes;
    }
    
    public HashMap<Point, Point> getLadders() {
        return ladders;
    }    
    
    public void setLadders(HashMap<Point, Point> ladders) {
        this.ladders = ladders;
    }
    
    public HashMap<Point, SpecialSquare> getSpecialSquares() {
        return specialSquares;
    }
    
    public void setHuman(Pawn human) {
        this.human = human;
    }
    
    public void setComputer(Pawn computer) {
        this.computer = computer;
    }
    
}
