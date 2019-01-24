package Presenter;

import Model.BasicClock;
import View.SwingClockDisplay;

public class Main {

    public static void main(String[] args) {
        ClockPresenter presenter = new ClockPresenter( new BasicClock() );
        SwingClockDisplay display = new SwingClockDisplay(presenter);
    }
}
