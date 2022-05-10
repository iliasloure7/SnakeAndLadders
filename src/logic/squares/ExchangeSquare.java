package logic.squares;

import logic.*;
/**
 *
 * @author maria
 */
public class ExchangeSquare extends SpecialSquare {
    
    private final Pawn human;
    private final Pawn computer;
   
    public ExchangeSquare(String imageURL, Pawn human, Pawn computer) {
        super(imageURL);
        this.human = human;
        this.computer = computer;
    }
   
    @Override
    public void specialty(Pawn pawn) {
        if (pawn == human)
            this.exchange(human, computer);
        else
            this.exchange(computer, human);
    }
    
    private void exchange(Pawn currentPawn, Pawn pawnToExchange) {
        int tempX = currentPawn.getX();
        int tempY = currentPawn.getY();
        int tempRow = currentPawn.getRow();
        currentPawn.setX(pawnToExchange.getX());
        currentPawn.setY(pawnToExchange.getY());
        currentPawn.setRow(pawnToExchange.getRow());
        pawnToExchange.setX(tempX);
        pawnToExchange.setY(tempY);
        pawnToExchange.setRow(tempRow);
    }    
}
