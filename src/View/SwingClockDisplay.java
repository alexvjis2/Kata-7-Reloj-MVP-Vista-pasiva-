
package View;

import Presenter.ClockPresenter;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SwingClockDisplay implements ClockDisplay {

    private ClockPresenter presenter;
    private ClockPanel clockPanel;
    private int hours, minutes, seconds;
    
    @Override
    public void update(int hours, int minutes, int seconds) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        clockPanel.repaint();
    }
    
    private JFrame window;

    public SwingClockDisplay(ClockPresenter presenter) {
        hours = minutes = seconds = 0;
        this.presenter = presenter;
        createWindow();
        presenter.addDisplay(this);
    }

    private void createWindow() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        
        clockPanel = new ClockPanel();
        window.add(createSpeedControlMenu(), BorderLayout.NORTH);
        window.add(clockPanel, BorderLayout.CENTER);
        window.add(createSetMenu(), BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
    }
    
    private JPanel createSpeedControlMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
        
        JSlider speedSlider = new JSlider(0, 25, 1);
        JLabel speedValue = new JLabel("x1");
        
        speedSlider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                presenter.setSpeed(speedSlider.getValue());
                speedValue.setText("x" + speedSlider.getValue());
            }
        });
        menu.add(new JLabel("Speed"));
        menu.add(speedSlider);
        menu.add(speedValue);
        return menu;
    }
    
    private JPanel createSetMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
        
        JSpinner hours = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        JSpinner minutes = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        JSpinner seconds = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        JButton setButton = new JButton("SET");
        
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.setTime(
                        (Integer) hours.getValue(), 
                        (Integer) minutes.getValue(), 
                        (Integer) seconds.getValue()
                );
            }
        });
        menu.add(new JLabel("HH"));
        menu.add(hours);
        menu.add(new JLabel("MM"));
        menu.add(minutes);
        menu.add(new JLabel("SS"));
        menu.add(seconds);
        menu.add(setButton);
        return menu;
    }
    
    class ClockPanel extends JPanel {

        private int xCenter, yCenter;
        public ClockPanel() {
            super();
            this.setMinimumSize(new Dimension(250, 250));
            this.setPreferredSize(this.getMinimumSize());
            recalculateCenter();
        }
        
        private void recalculateCenter() {
            xCenter = this.getWidth() / 2;
            yCenter = this.getHeight() / 2;
        }
        
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            recalculateCenter();
            
            
            drawMinutesPointer((Graphics2D) g);
            drawHoursPointer((Graphics2D) g);
            drawSecondsPointer((Graphics2D) g);
        }
        
        private void drawSecondsPointer(Graphics2D g) {
            int xL = (int) (Math.cos(seconds*Math.PI/30f - Math.PI/2) * 100f);
            int yL = (int) (Math.sin(seconds*Math.PI/30f - Math.PI/2) * 100f);
            
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(1));
            g.drawLine(xCenter, yCenter, xCenter + xL, yCenter + yL);
        }
        private void drawMinutesPointer(Graphics2D g) {
            int xL = (int) (Math.cos(minutes*Math.PI/30f - Math.PI/2) * 90f);
            int yL = (int) (Math.sin(minutes*Math.PI/30f - Math.PI/2) * 90f);
            
            g.setColor(Color.CYAN);
            g.setStroke(new BasicStroke(4));
            g.drawLine(xCenter, yCenter, xCenter + xL, yCenter + yL);
        }
        private void drawHoursPointer(Graphics2D g) {
            int xL = (int) (Math.cos((hours%12)*Math.PI/6f - Math.PI/2) * 60f);
            int yL = (int) (Math.sin((hours%12)*Math.PI/6f - Math.PI/2) * 60f);
            
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(6));
            g.drawLine(xCenter, yCenter, xCenter + xL, yCenter + yL);
        }
    }
    
}
