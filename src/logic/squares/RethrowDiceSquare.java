package logic.squares;

import logic.*;
import ui.SnakeAndLadders;

/**
 *
 * @author maria
 */
public class RethrowDiceSquare extends SpecialSquare {
       
    public RethrowDiceSquare(String imageURL) {
        super(imageURL);
    }
    
    @Override
    public void specialty(Pawn pawn) {
        if (!pawn.getName().equals("COMPUTER")) { 
            SnakeAndLadders.rollDiceBtn.setEnabled(true);
            SnakeAndLadders.playerTurnLabel.setText(pawn.getName() + " TURN!");
            pawn.setIsInRethrowState(true);
            return;
        }
        pawn.setIsInRethrowState(true);
        SnakeAndLadders.rollDiceBtn.setEnabled(false);
        SnakeAndLadders.playerTurnLabel.setText(pawn.getName() + " TURN!");
    }   
   
}
