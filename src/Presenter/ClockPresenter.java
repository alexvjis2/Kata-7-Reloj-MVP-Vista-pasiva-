package Presenter;

import Model.Clock;
import Model.Time;
import View.ClockDisplay;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class ClockPresenter {

    private final Clock clock;
    private final Set<ClockDisplay> displays;
    private Timer timer;

    public ClockPresenter(Clock clock) {
        this.clock = clock;
        displays = new HashSet<ClockDisplay>();
        timer = new Timer();
        setSpeed(1);
    }

    public void setSpeed(float multiplier) {
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(
            new TimerTask() {
                @Override
                public void run() { 
                    tick();
                    updateDisplays(clock.getTime());
                }
            },
            (int) (1000 / multiplier),
            (int) (1000 / multiplier)
        );
    }
    
    public void setTime(int hours, int minutes, int seconds) {
        Time time = new Time(hours, minutes, seconds);
        clock.setTime(time);
        updateDisplays(time);
    }
    
    public boolean addDisplay(ClockDisplay display) {
        return displays.add(display);
    }
    
    private void updateDisplays (Time time) {
        for (ClockDisplay display : displays) {
            display.update(time.hours(), time.minutes(), time.seconds());   
        };
    }
    
    private void tick() { clock.tick(); }
}
