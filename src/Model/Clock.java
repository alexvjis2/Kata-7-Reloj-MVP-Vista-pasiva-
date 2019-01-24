package Model;

public interface Clock {
    void setTime(Time time);
    Time getTime();
    void tick();
}