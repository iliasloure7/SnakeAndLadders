package logic.squares;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import logic.Pawn;
import logic.levels.Level;


/**
 *
 * @author maria
 */
public class GravityReversalSquare extends SpecialSquare {
    
    private final Level level;
    
    public GravityReversalSquare(String imageURL, Level level) {
        super(imageURL);
        this.level = level;
    }
    
    @Override
    public void specialty(Pawn pawn) {
        level.setSnakes(reverseHashMap(level.getSnakes()));
        level.setLadders(reverseHashMap(level.getLadders()));        
    }
    
    private HashMap<Point, Point> reverseHashMap(HashMap<Point,Point> currentHashMap) {
        HashMap<Point, Point> reversedHashMap = new HashMap<>();
        currentHashMap.forEach((point1, point2) -> {
            reversedHashMap.put(point2, point1);
        });
        return reversedHashMap;
    }
}
