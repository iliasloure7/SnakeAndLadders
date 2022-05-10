package ui;

import Helpers.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import logic.*;
import logic.levels.*;

/**
 * Δημιουργεί το γραφικό περιβάλλον.
 * @author maria
 */
public class SnakeAndLadders extends JFrame {
    
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 660;
    
    public static Stopwatch stopwatch;
    public static JButton rollDiceBtn, startBtn;
    public static JPanel namePanel, levelsPanel, pawnOptionsPanel, 
            pawnChoicesPanel, pawnPanel, timerPanel;
    
    public static JLabel playerTurnLabel;
    public static JPanel playerTurnPanel;
    
    private Game game;
    private GameBoard board;
    private Pawn human, computer; 
    private History history;
    private Dice dice;
    private Level level;
    
    private JPanel sidePanel;
    
    private JLabel nameFieldLabel;
    private JTextField nameField;
    
    private JLabel pawnLabel, redPawn, greenPawn, bluePawn, magentaPawn;
    
    private JMenuBar menuBar;
    private JMenu gameMenu, optionsMenu, historyMenu;
    private JMenuItem newGameItem, restartItem, exitItem,
            showHistoryItem, hideHistoryItem;
       
    private final String[] levels = {"Easy", "Medium", "Hard"}; 
    private JLabel difficultyLabel;
    private JComboBox<String> levelsComboBox;

    public SnakeAndLadders() {
       this.initComponents();
    }

    private void initComponents() {
        this.setTitle("Snake and Ladders");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        optionsMenu = new JMenu("Options");
        historyMenu = new JMenu("History");
        
        newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener((ActionEvent e) -> {
            if (game != null) game.newGame();
        });
        
        restartItem = new JMenuItem("Restart");
        restartItem.addActionListener((ActionEvent e) -> {
            if (game != null) game.restart();
        });
        
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
                
        showHistoryItem = new JMenuItem("Show History");
        showHistoryItem.addActionListener((ActionEvent e) -> {
            history.setVisible(true);
        });
        
        hideHistoryItem = new JMenuItem("Hide History");
        hideHistoryItem.addActionListener((ActionEvent e) -> {
            history.setVisible(false);
        });
             
        nameFieldLabel = new JLabel("Name");
        nameField = new JTextField();
        namePanel = Helpers.createPanel(new GridLayout(2, 1));
              
        levelsPanel = Helpers.createPanel(new GridLayout(2,1));
        difficultyLabel = new JLabel("Choose difficulty");
        levelsComboBox = new JComboBox<>(levels);
        levelsComboBox.addActionListener((ActionEvent e) -> {
            int difficulty = levelsComboBox.getSelectedIndex();
            level = switch (difficulty) {
                case 0 -> new EasyLevel("../images/easy_board.jpg");
                case 1 -> new EasyLevel("../images/easy_board.jpg");
                default -> new HardLevel("../images/board.jpg");
            };
        });
        
        pawnLabel = new JLabel("Choose Pawn");
        pawnPanel = Helpers.createPanel(new GridLayout(1, 4));
        pawnPanel.setOpaque(false);
        pawnOptionsPanel = Helpers.createPanel(new GridLayout(2, 1));
        pawnOptionsPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        redPawn = new JLabel();
        redPawn.setIcon(Helpers.scaleImage("../images/red_pawn.png", 40, 40));
        redPawn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                human = new Pawn("../images/red_pawn.png");
                computer = new Pawn("../images/blue_pawn.png");
            }
        });
       
        greenPawn = new JLabel();
        greenPawn.setIcon(Helpers.scaleImage("../images/green_pawn.png", 40, 40));
        greenPawn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                human = new Pawn("../images/green_pawn.png");
                computer = new Pawn("../images/red_pawn.png");
            }
        });
       
        bluePawn = new JLabel();
        bluePawn.setIcon(Helpers.scaleImage("../images/blue_pawn.png", 40, 40));
        bluePawn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                human = new Pawn("../images/blue_pawn.png");
                computer = new Pawn("../images/magenta_pawn.png");
            }
        });
        
        magentaPawn = new JLabel();
        magentaPawn.setIcon(Helpers.scaleImage("../images/magenta_pawn.png", 40, 40));
        magentaPawn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                human = new Pawn("../images/magenta_pawn.png");
                computer = new Pawn("../images/green_pawn.png");
            }
        });
      
        startBtn = Helpers.createButton("START GAME");
        startBtn.addActionListener((ActionEvent e) -> {
            String humanName = nameField.getText().toUpperCase().trim();
            if (!Helpers.isGameValidated(humanName, human)) return;
            game = new Game(board, level, human, computer, dice, history, humanName, "COMPUTER");
            nameField.setText("");
            Helpers.initUIAfterGameStarted(human, computer, dice.getIcon());
        });
        
        rollDiceBtn = Helpers.createButton("ROLL DICE");
        rollDiceBtn.setVisible(false);
        rollDiceBtn.addActionListener((ActionEvent e) -> {
            game.handleDiceRoll();
            game.handlePlayersMove();
        }); 
        
        Border whiteLine = BorderFactory.createLineBorder(Color.white, 3);
        SnakeAndLadders.stopwatch = new Stopwatch();
        SnakeAndLadders.timerPanel = Helpers.createPanel(new GridBagLayout());
        SnakeAndLadders.timerPanel.setBorder(whiteLine);
        SnakeAndLadders.timerPanel.setMaximumSize(new Dimension(240, 60));
        SnakeAndLadders.timerPanel.setBackground(new Color(133, 17, 65));
        SnakeAndLadders.timerPanel.setVisible(false);
        
        SnakeAndLadders.pawnChoicesPanel = Helpers.createPanel(new GridLayout(2, 2));
        SnakeAndLadders.pawnChoicesPanel.setVisible(false);
        
        SnakeAndLadders.playerTurnLabel = new JLabel();
        SnakeAndLadders.playerTurnLabel.setForeground(Color.white);
        SnakeAndLadders.playerTurnLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        
        SnakeAndLadders.playerTurnPanel = Helpers.createPanel(new GridBagLayout());
        SnakeAndLadders.playerTurnPanel.setBorder(whiteLine);
        SnakeAndLadders.playerTurnPanel.setMaximumSize(new Dimension(240, 60));
        SnakeAndLadders.playerTurnPanel.setBackground(new Color(133, 17, 65));
        SnakeAndLadders.playerTurnPanel.setVisible(false);
                  
        dice = new Dice("../images/animated_dice.gif");
        dice.getIcon().setVisible(false);
        level = new EasyLevel("../images/easy_board.jpg");
        board = new GameBoard();
        history = new History();
                
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(250, 162, 198));
        sidePanel.setPreferredSize(new Dimension(286, FRAME_HEIGHT));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        
        gameMenu.add(newGameItem);
        gameMenu.add(restartItem);
        gameMenu.add(exitItem);        
        historyMenu.add(showHistoryItem);
        historyMenu.add(hideHistoryItem);
        optionsMenu.add(historyMenu);
        
        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);
        
        namePanel.add(nameFieldLabel);
        namePanel.add(nameField);
        namePanel.setOpaque(false);
        
        levelsPanel.add(difficultyLabel);
        levelsPanel.add(levelsComboBox);
        levelsPanel.setOpaque(false);
        
        pawnPanel.add(redPawn);
        pawnPanel.add(greenPawn);
        pawnPanel.add(bluePawn);
        pawnPanel.add(magentaPawn);
        
        pawnOptionsPanel.add(pawnLabel);
        pawnOptionsPanel.add(pawnPanel);
        pawnOptionsPanel.setOpaque(false);
        
        SnakeAndLadders.timerPanel.add(SnakeAndLadders.stopwatch.getTimeLabel());
        SnakeAndLadders.playerTurnPanel.add(SnakeAndLadders.playerTurnLabel);
             
        sidePanel.add(namePanel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sidePanel.add(levelsPanel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sidePanel.add(pawnOptionsPanel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sidePanel.add(startBtn);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sidePanel.add(SnakeAndLadders.timerPanel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sidePanel.add(SnakeAndLadders.pawnChoicesPanel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sidePanel.add(SnakeAndLadders.playerTurnPanel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sidePanel.add(dice.getIcon());
        sidePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        sidePanel.add(SnakeAndLadders.rollDiceBtn);
        
        this.setJMenuBar(menuBar);
        this.add(sidePanel, BorderLayout.EAST);
        this.add(board);
    }    
}
