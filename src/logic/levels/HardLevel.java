package logic.levels;

import java.awt.Point;
import logic.squares.*;

/**
 *
 * @author ilias
 */
public final class HardLevel extends Level {
    
    public HardLevel(String boardImageURL) {
        super(boardImageURL);
        this.initSnakesCoordinates();
        this.initLaddersCoordinates();
    }
    
    @Override
    public void initSnakesCoordinates() {
        snakes.put(new Point(360, 440), new Point(240, 560));
        snakes.put(new Point(0, 380), new Point(120, 560));
        snakes.put(new Point(120,320), new Point(120,500));
        snakes.put(new Point(360,260), new Point(540,380));
        snakes.put(new Point(300,200), new Point(240,320));
        snakes.put(new Point(240,140), new Point(120,260));
        snakes.put(new Point(480,80), new Point(420,260));
        snakes.put(new Point(60,20), new Point(0,320));
    }

    @Override
    public void initLaddersCoordinates() {
        ladders.put(new Point(180, 560), new Point(240, 440));
        ladders.put(new Point(420,500), new Point(300,320));
        ladders.put(new Point(420,380), new Point(480,320));
        ladders.put(new Point(60,320), new Point(120,200));
        ladders.put(new Point(540,320), new Point(480,200));
        ladders.put(new Point(60,200), new Point(0,80));
        ladders.put(new Point(360,140), new Point(480,20));
    }
    
    @Override
    public void initSpecialSquaresCoordinates() {
        RethrowDiceSquare rethrowDice = new RethrowDiceSquare("../images/rethrow_dice.png");
        specialSquares.put(new Point(360, 480), new ExplosionSquare("../images/explosion.png"));
        specialSquares.put(new Point(480, 120), new ExplosionSquare("../images/explosion.png"));
        specialSquares.put(new Point(180, 300), new ExplosionSquare("../images/explosion.png"));
        specialSquares.put(new Point(0, 240), new LoveSquare("../images/love.png", human, computer));
        specialSquares.put(new Point(360, 180), new ExchangeSquare("../images/exchange.png", human, computer));
        specialSquares.put(new Point(240, 360), new LuckySquare("../images/lucky.png", rethrowDice));
        specialSquares.put(new Point(180, 120), new ReverseSquare("../images/reverse.png"));
        specialSquares.put(new Point(0, 480), new ReverseSquare("../images/reverse.png"));
        specialSquares.put(new Point(180, 240), new CancelReverseSquare("../images/reverse_cancel.png"));
        specialSquares.put(new Point(540, 480), new CancelReverseSquare("../images/reverse_cancel.png"));
        specialSquares.put(new Point(300, 60), new TurtleSquare("../images/turtle.png"));
        specialSquares.put(new Point(360, 300), new TurtleSquare("../images/turtle.png"));
        specialSquares.put(new Point(540, 180), new CancelTurtleSquare("../images/turtle_cancel.png"));
//        specialSquares.put(new Point(60, 240), new TurtleSquare("../images/turtle.png"));
    }

    
}
