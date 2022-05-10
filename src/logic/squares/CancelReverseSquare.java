package logic.squares;

import logic.Pawn;

/**
 *
 * @author maria
 */
public class CancelReverseSquare extends SpecialSquare {
    
    public CancelReverseSquare(String imageURL) {
        super(imageURL);
    }
    
    @Override
    public void specialty(Pawn pawn) { 
        pawn.setIsInReverseState(false);
    }
    
}
