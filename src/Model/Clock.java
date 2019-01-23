package Model;

public interface Clock {
    int getSeconds();
    int getMinutes();
    int getHours();
    int setTime(int hours, int minutes, int seconds);
}