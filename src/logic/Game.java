package logic;

import Helpers.Helpers;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import logic.levels.Level;
import logic.squares.*;
import javax.swing.*;
import ui.*;

/**
 * Διαχειρίζεται τη λογική του παιχνιού.
 * @author maria
 */
public class Game {
    
    private boolean isInSimpleSquare;
    private boolean wasPreviouslyInLuckyState;
    private final GameBoard board;
    private final Level level;
    private final Pawn human, computer;
    private final History history;
    private final Dice dice;
    private final Timer computerMoveTimer;
    private TimerTask task;
    
    /**
     * Αρχικοποιεί όλα τα αντικείμενα που χρειάζονται στη λογική του παιχνιδιού.
     * @param board το επιτραπέζιο παιχνίδι.
     * @param level το επίπεδο το οποίο επέλεξε ο χρήστης.
     * @param human το πιόνι του χρήστη.
     * @param computer το πιόνι του υπολογιστή.
     * @param dice το ζάρι που ρίχνει κάθε παίκτης.
     * @param history το ιστορικό που διαχειρίζεται τα συμβάντα.
     * @param playerName το όνομα χρήστη.
     * @param computerName το όνομα του υπολογιστή.
     */
    public Game(GameBoard board, Level level, Pawn human, Pawn computer, 
            Dice dice, History history, String playerName, String computerName) {
        
        this.board = board;
        this.level = level;
        this.human = human;
        this.computer = computer;
        this.dice = dice;
        this.history = history;
        this.isInSimpleSquare = false;
        this.wasPreviouslyInLuckyState = false;
        this.human.setName(playerName);
        this.computer.setName(computerName);
        this.level.setHuman(human);
        this.level.setComputer(computer);
        this.level.initSpecialSquaresCoordinates();
        this.board.setImage(this.level.getBoardImageURL());
        this.board.setSpecialSquares(level.getSpecialSquares());
        this.board.setHuman(human);
        this.board.setComputer(computer);
        this.board.draw();
        this.computerMoveTimer = new Timer();
        this.history.setEvent("NO MOVE YET", "GAME STARTED");
        SnakeAndLadders.stopwatch.start();
    }
            
    public void newGame() {
        if (task != null) task.cancel();
        computer.moveToStartSquare();
        human.moveToStartSquare();
        history.clear();
        board.draw();
        Helpers.resetUIAfterNewGame(dice.getIcon());
    }
    
    public void restart() {
        if (task != null) task.cancel();
        human.resetState();
        human.moveToStartSquare();
        computer.resetState();
        computer.moveToStartSquare();
        level.initSnakesCoordinates();
        level.initLaddersCoordinates();
        history.clear();
        board.draw();
        SnakeAndLadders.stopwatch.reset();
        SnakeAndLadders.stopwatch.start();
        SnakeAndLadders.playerTurnLabel.setText(human.getName() + " TURN!");
        SnakeAndLadders.rollDiceBtn.setEnabled(true);
    }
  
    public void handleDiceRoll() {
        dice.roll();
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("../images/dice_" + dice.getNumber() + ".png"));
        dice.getIcon().setIcon(icon);
    }
    
    public void handlePlayersMove() { 
        this.checkTurtleState(human);
        this.handleMove(human, false, computer.getName());
        if (this.isInRethrowDiceSquare(human) || this.isInLuckySquare(human)) return;  
        if (this.isWinnerInLuckyState(human)) return;   
        if (this.isWinner(human)) return;
        this.handleComputerTurn();
    }
    
    /**
     * Διαχειρίζεται την κίνηση του υπολογιστή.
     * Υπάρχει ένας timer που ελέγχει το χρόνο που θα εκτελέσει την κίνηση του.
     */
    private void handleComputerTurn() {
        task = new TimerTask() {
            @Override
            public void run() {
                handleDiceRoll();
                checkTurtleState(computer);
                handleMove(computer, true, human.getName()); 
                if (isInRethrowDiceSquare(computer) || isInLuckySquare(computer)) {
                    handleComputerTurn(); 
                    return;
                }          
                if (isWinnerInLuckyState(computer)) return;
                if (isWinner(computer)) return;
            }
        };
        computerMoveTimer.schedule(task, 3000);
    }
    
    /**
     * Ελέγχει εάν ο παίκτης έχει πατήσει σε τετράγωνο τύπου TurtleSquare.
     * @param pawn ο παίκτης που εκτέλεσε την κίνηση.
     */
    private void checkTurtleState(Pawn pawn) {
        if (!pawn.getIsInTurtleState()) return;
        if (dice.getNumber() == 1) return;
        dice.setNumber(dice.getNumber() / 2);
    }
    
    /**
     * Διαχειρίζεται την κίνηση του εκάστοτε παίκτη.
     * @param pawn το πιόνι που εκτελεί την κίνηση.
     * @param isRollDiceEnabled δηλώνει εάν το κουμπί με το οποίο ο χρήστης ρίχνει το ζάρι,
     * πρέπει να είναι ενεργό ή όχι.
     * @param playerName το όνομα του παίκτη που έχει σειρά.
     */
    private void handleMove(Pawn pawn, boolean isRollDiceEnabled, String playerName) {
        pawn.move(dice.getNumber() * GameBoard.SQUARE_SIZE); 
        SnakeAndLadders.rollDiceBtn.setEnabled(isRollDiceEnabled);
        SnakeAndLadders.playerTurnLabel.setText(playerName + " TURN!");
        this.checkSquare(pawn);
        if (isInSimpleSquare) this.handleHistory(pawn, "SIMPLE_SQUARE");
        this.board.draw();
    }
    
    /**
     * Ελέγχει το είδος τετραγώνου στο οποίο μετακινήθηκε ο παίκτης.
     * @param pawn ο παίκτης που εκτέλεσε την κίνηση. 
     */
    private void checkSquare(Pawn pawn) {
        int x = pawn.getX();
        int y = pawn.getY();
        Point pointToSearch = new Point(x, y);
        this.checkSnake(pawn, pointToSearch);
        this.checkLadder(pawn, pointToSearch);
        this.checkSpecialSquare(pawn, pointToSearch);
    }
    
    /**
     * Ελέγχει για το εάν ένας παίκτης βρίσκεται σε τετράγωνο φιδιού.
     * @param pawn ο παίκτης που εκτέλεσε την κίνηση. 
     * @param point συντεταγμένες (x,y) που αναζητούνται  
     */
    private void checkSnake(Pawn pawn, Point point) {
        Point result = level.getSnakes().get(point);
        if (result == null) { isInSimpleSquare = true; return; }
        pawn.move((int) result.getX(), (int) result.getY());
        this.handleHistory(pawn, "SNAKE");
        isInSimpleSquare = false;
    }
    
    /**
     * Ελέγχει για το εάν ένας παίκτης βρίσκεται σε τετράγωνο σκάλας.
     * @param pawn ο παίκτης που εκτέλεσε την κίνηση. 
     * @param point συντεταγμένες (x,y) που αναζητούνται  
     */
    private void checkLadder(Pawn pawn, Point point) {
        if (pawn.getIsInTurtleState()) return;
        Point result = level.getLadders().get(point);
        if (result == null) { isInSimpleSquare = true; return; }
        pawn.move((int) result.getX(), (int) result.getY());
        this.handleHistory(pawn, "LADDER");
        isInSimpleSquare = false;
    }
    
    /**
     * Ελέγχει για το εάν ένας παίκτης βρίσκεται σε ειδικό τετράγωνο.
     * @param pawn ο παίκτης που εκτέλεσε την κίνηση. 
     * @param point συντεταγμένες (x,y) που αναζητούνται  
    */
    private void checkSpecialSquare(Pawn pawn, Point point) {
        point.setLocation(point.getX(), point.getY() - 20);
        SpecialSquare specialSquare = level.getSpecialSquares().get(point);
        if (specialSquare == null) { isInSimpleSquare = true; return; }
        specialSquare.specialty(pawn);
        if (specialSquare instanceof ExchangeSquare) 
            checkSpecialSquare(pawn, new Point(pawn.getX(), pawn.getY()));
        this.handleHistory(pawn, specialSquare.getClass().getSimpleName().toUpperCase());
        isInSimpleSquare = false;
    }
    
    /**
     * Αποθηκεύει το συμβάν στο ιστορικό.
     * @param pawn παίκτης που εκτέλεσε την κίνηση. 
     * @param eventState  κατάσταση πιονιού. (πχ απλό-ειδικό τετράγωνο, φίδι, σκάλα) 
     */
    private void handleHistory(Pawn pawn, String eventState) { 
        history.setEvent(eventState, pawn.getName() + " MOVED TO SQUARE " + pawn.findSquareNumber());
    }
     
    /**
     * Ελέγχει εάν ο παίκτης έχει πατήσει σε τετράγωνο τύπου RethrowDiceSquare.
     * @param pawn παίκτης που εκτέλεσε την κίνηση.
     * @return boolean τιμή.
     */
    private boolean isInRethrowDiceSquare(Pawn pawn) {
        if (pawn.getIsInRethrowState() && !pawn.getIsInLuckyState()) {
            pawn.setIsInRethrowState(false);
            return true;
        }
        return false;
    }
    
    /**
     * Ελέγχει έαν ο παίκτης έχει πατήσει σε τετράγωνο τύπου LuckySquare.
     * @param pawn παίκτης που εκτέλεσε την κίνηση.
     * @return boolean τιμή.
    */
    private boolean isInLuckySquare(Pawn pawn) {
        if (!pawn.getIsInRethrowState() && !pawn.getIsInLuckyState())
            return false;
        this.wasPreviouslyInLuckyState = true;
        pawn.setIsInRethrowState(false);
        pawn.setIsInLuckyState(false);
        return true;
    }
    
    /**
     * Ελέγχει εάν ο παίκτης είναι νικητής, εφόσον προηγουμένως βρισκόταν σε τετράγωνο
     * τύπου LuckySquare και έφερε ζαριά με αριθμό 6.
     * @param pawn παίκτης που εκτέλεσε την κίνηση.
     * @return boolean τιμή.
     */
    private boolean isWinnerInLuckyState(Pawn pawn) {
        if (!wasPreviouslyInLuckyState || dice.getNumber() != 6) { 
            this.wasPreviouslyInLuckyState = false;
            return false;
        }
        wasPreviouslyInLuckyState = false;
        this.handleHistory(pawn, "LUCKY_SQUARE WIN");
        pawn.moveToEndSquare();
        Helpers.showWinnerMessage(pawn);
        SnakeAndLadders.stopwatch.stop();
        return true;
    }
   
    /**
     * Ελέγχει έαν κάποιος παίκτης έχει φτάσει στο τερματισμό.
     * @param pawn παίκτης που εκτέλεσε την κίνηση. 
     * @return boolean τιμή.
     */
    private boolean isWinner(Pawn pawn) {
        if (pawn.getX() != 0 || pawn.getY() != 20) return false;
        this.handleHistory(pawn, "WIN");
        Helpers.showWinnerMessage(pawn);
        SnakeAndLadders.rollDiceBtn.setEnabled(false);
        SnakeAndLadders.stopwatch.stop();
        return true;
    }
}
