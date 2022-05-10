package logic.squares;

import logic.Pawn;

/**
 *
 * @author maria
 */
public class CancelTurtleSquare extends SpecialSquare {
    
    public CancelTurtleSquare(String imageURL) {
        super(imageURL);
    }
    
    @Override
    public void specialty(Pawn pawn) {
        pawn.setIsInTurtleState(false);
    }
    
}
