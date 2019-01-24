package View;

public class TextClockDisplay implements ClockDisplay {

    @Override
    public void update(int hours, int minutes, int seconds) {
        String time = 
                String.format("%02d:%02d:%02d", hours, minutes, seconds);
        System.out.println(time);
    }
}