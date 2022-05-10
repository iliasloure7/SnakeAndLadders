package logic.squares;

import logic.*;


/**
 *
 * @author maria
 */
public class ExplosionSquare extends SpecialSquare {
    
    public ExplosionSquare(String imageURL) {        
        super(imageURL);
    }
    
    @Override
    public void specialty(Pawn pawn) {
        if (pawn.getIsInReverseState()) pawn.setIsInReverseState(false);
        pawn.moveToStartSquare();
    }
}
