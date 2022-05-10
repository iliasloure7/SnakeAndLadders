package logic.squares;

import logic.Pawn;

/**
 *
 * @author maria
 */
public class TurtleSquare extends SpecialSquare {
    
    public TurtleSquare(String imageURL) {
        super(imageURL);
    }
       
    @Override
    public void specialty(Pawn pawn) {
        pawn.setIsInTurtleState(true);
    }
    
}
