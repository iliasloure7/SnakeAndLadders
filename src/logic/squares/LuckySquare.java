package logic.squares;

import logic.*;
/**
 *
 * @author maria
 */
public class LuckySquare extends SpecialSquare {
    
    private final RethrowDiceSquare rethrowDice;
    
    public LuckySquare(String imageURL, RethrowDiceSquare rethrowDice) {
        super(imageURL);
        this.rethrowDice = rethrowDice;
    }
    
    @Override
    public void specialty(Pawn pawn) {
        pawn.setIsInLuckyState(true);
        rethrowDice.specialty(pawn);
    }
    
}
