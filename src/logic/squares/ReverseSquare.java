package logic.squares;

import logic.Pawn;

/**
 *
 * @author maria
 */
public class ReverseSquare extends SpecialSquare {

    public ReverseSquare(String imageURL) {
        super(imageURL);
    }
    
    @Override
    public void specialty(Pawn pawn) { 
        pawn.setIsInReverseState(true);
    }
    
}
