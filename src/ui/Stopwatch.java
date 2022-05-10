package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Διαχειρίζεται τη διάρκεια του κάθε παιχνιδιού.
 * @author maria
 */
public class Stopwatch {
    
    private final Timer timer;
    private JLabel timeLabel;
    
    private int elapsedTime;
    private int seconds;
    private int minutes;
    private int hours;
    
    private String secondsStr;
    private String minutesStr;
    private String hoursStr;
    
    public Stopwatch() { 
        this.elapsedTime += 1000;
        this.hours = (elapsedTime/3600000);
        this.minutes = (elapsedTime/60000) % 60;
        this.seconds = (elapsedTime/1000) % 60;
        this.toStringFormat();
        
        this.timeLabel = new JLabel();
        this.timeLabel.setText(hoursStr+":"+minutesStr+":"+secondsStr);
        this.timeLabel.setFont(new Font("Verdana", Font.BOLD, 26));
        this.timeLabel.setHorizontalAlignment(JLabel.CENTER);
        this.timeLabel.setForeground(Color.green);
        
        this.timer = new Timer(1000, (ActionEvent e) -> {
            this.elapsedTime += 1000;
            this.hours = (elapsedTime/3600000);
            this.minutes = (elapsedTime/60000) % 60;
            this.seconds = (elapsedTime/1000) % 60;
            this.toStringFormat();
            this.timeLabel.setText(hoursStr+":"+minutesStr+":"+secondsStr);
        });
    }
    
    private void toStringFormat() {
        secondsStr = String.format("%02d", seconds);
        minutesStr = String.format("%02d", minutes);
        hoursStr = String.format("%02d", hours);
    }
        
    public JLabel getTimeLabel() {
        return timeLabel;
    }
    
    public void start() {
        timer.start();
    }
    
    public void stop() {
        timer.stop();
    }
    
    public void reset() {
        timer.stop();
        elapsedTime=0;
        seconds =0;
        minutes=0;
        hours=0;
        this.toStringFormat();
    }

}
