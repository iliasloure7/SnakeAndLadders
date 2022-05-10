
package ui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import logic.Pawn;
import logic.squares.SpecialSquare;

/**
 *
 * @author maria
 */
public class GameBoard extends JPanel {
    
    public static final int SIZE = 600;
    public static final int SQUARE_SIZE = SIZE / 10;
   
    private Image image;
    private Pawn human;
    private Pawn computer;
    private HashMap<Point, SpecialSquare> specialSquares;
    
    /**
     * Ζωγραφίζει την εικόνα του επιτραπεζίου, των πιονιών 
     * και των ειδικών τετραγώνων στο γραφικό περιβάλλον.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if (human == null && computer == null) return;
        
        g.drawImage(this.image, 0, 0, SIZE, SIZE, this);

        g.drawImage(human.getImage(), human.getX(), human.getY(),  human.getSize(), 
                human.getSize(), this);
        
        g.drawImage(computer.getImage(), computer.getX(), computer.getY(), 
            computer.getSize(), computer.getSize(), this);
        
        this.specialSquares.forEach((point, square) -> {
            int x = (int) point.getX();
            int y = (int) point.getY();
            g.drawImage(square.getImage(), x, y, square.getSize(), square.getSize(), this);
        });
    }
    
    public void setImage(String boardImageURL) {
        ImageIcon icon = new ImageIcon(getClass().getResource(boardImageURL));
        this.image = icon.getImage();
    }
    
    public void setHuman(Pawn human) {
        this.human = human;
    }
    
    public void setComputer(Pawn computer) {
        this.computer = computer;
    }
    
    public void setSpecialSquares(HashMap<Point, SpecialSquare> specialSquares) {
        this.specialSquares = specialSquares;
    }
    
    public void draw() {
       repaint();
    }
}
