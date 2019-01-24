package Model;

/**
 * Utility inmutable class. 
 * Represents time with whole ints (hours, minutes and seconds).
 * @author Álex
 */
public final class Time {
    private final int hours, minutes, seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int hours() { return hours; }
    public int minutes() { return minutes; }
    public int seconds() { return seconds; }
    
    public Time addSeconds(int secondsToAdd) {
        int minutesToAdd = (this.seconds + secondsToAdd) / 60;
        int hoursToAdd = (minutes + minutesToAdd) / 60;
        
        return new Time(
            (hours + hoursToAdd) % 24,
            (minutes + minutesToAdd) % 60,
            (seconds + secondsToAdd) % 60
        );
    }
    
    public Time addMinutes(int minutesToAdd) {
        return addSeconds(60*minutesToAdd); 
    }
    public Time addHours(int hoursToAdd) { 
        return addSeconds(3600*hoursToAdd); 
    }

    @Override
    public String toString() {
        return hours + ":" + minutes + ":" + seconds;
    }
    
    
}