
package logic.levels;

import java.awt.Point;
import logic.squares.*;

/**
 * 
 * @author ilias
 */
public final class EasyLevel extends Level {
    
    public EasyLevel(String boardImageURL) {
        super(boardImageURL);
        this.initSnakesCoordinates();
        this.initLaddersCoordinates();
    } 

    @Override
    public void initSnakesCoordinates() {
        snakes.put(new Point(240, 320), new Point(240, 560));
        snakes.put(new Point(120,440), new Point(180,500));
        snakes.put(new Point(480,260), new Point(420,380));
        snakes.put(new Point(360,200), new Point(420,440));
        snakes.put(new Point(540,80), new Point(540,320));
        snakes.put(new Point(60,20), new Point(180,440));
    }

    @Override
    public void initLaddersCoordinates() {
        ladders.put(new Point(60, 440), new Point(0, 200));
        ladders.put(new Point(420, 560), new Point(480, 440));
        ladders.put(new Point(240, 200), new Point(180, 20));
        ladders.put(new Point(360, 260), new Point(420, 200));
        ladders.put(new Point(480, 140), new Point(420, 20));
    }

    @Override
    public void initSpecialSquaresCoordinates() {
        RethrowDiceSquare rethrowDice = new RethrowDiceSquare("../images/rethrow_dice.png");
        specialSquares.put(new Point(540, 480), new ExplosionSquare("../images/explosion.png"));
        specialSquares.put(new Point(0, 360), new LoveSquare("../images/love.png", human, computer));
        specialSquares.put(new Point(360, 120), new ExchangeSquare("../images/exchange.png", human, computer));
        specialSquares.put(new Point(480, 180), rethrowDice);
        specialSquares.put(new Point(300, 480), rethrowDice);
        specialSquares.put(new Point(180, 180), new LuckySquare("../images/lucky.png", rethrowDice));
        specialSquares.put(new Point(420, 300), new GravityReversalSquare("../images/gravity_reversal.png", this));
        specialSquares.put(new Point(0, 420), new ReverseSquare("../images/reverse.png"));
        specialSquares.put(new Point(300, 480), new CancelReverseSquare("../images/reverse_cancel.png"));
        specialSquares.put(new Point(60, 240), new TurtleSquare("../images/turtle.png"));
        specialSquares.put(new Point(120, 120), new CancelTurtleSquare("../images/turtle_cancel.png"));
    }

}
