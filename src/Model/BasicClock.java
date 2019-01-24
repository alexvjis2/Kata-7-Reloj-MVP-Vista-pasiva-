
package Model;

public class BasicClock implements Clock {

    private Time time;

    public BasicClock(Time time) { this.time = time; }
   
    public BasicClock() { this(new Time(0, 0, 0)); }
    
    @Override
    public void setTime(Time time) { this.time = time; }

    @Override
    public Time getTime() { return time; }

    @Override
    public void tick() { this.time = time.addSeconds(1); }
}
