package logic.squares;

import logic.*;

/**
 *
 * @author maria
 */
public class LoveSquare extends SpecialSquare {
     
    private final Pawn human;
    private final Pawn computer;
    
    public LoveSquare(String imageURL, Pawn human, Pawn computer) {
        super(imageURL);
        this.human = human;
        this.computer = computer;
    }
    
    @Override
    public void specialty(Pawn pawn) {
        if (pawn == human)
            this.setEnemyToStartPoint(computer);
        else
            this.setEnemyToStartPoint(human);
    }
    
    private void setEnemyToStartPoint(Pawn pawn) {
        if (pawn.getIsInReverseState()) pawn.setIsInReverseState(false);
        pawn.setX(0);
        pawn.setY(560);
        pawn.setRow(0);
    }
    
}
