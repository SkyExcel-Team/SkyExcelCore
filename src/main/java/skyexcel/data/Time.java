package skyexcel.data;

import org.bukkit.event.Listener;

public class Time implements Listener {


    private long SECOND_IN_MILLIS = 0;

    private long MINUTE_IN_MILLIS ;
    private long HOUR_IN_MILLIS;
    private long DAY_IN_MILLIS;

    public Time(int... times) {

        switch (times.length) {
            case 1:
                addDay(times[0]);
                break;
            case 2:
                addDay(times[0]);
                addHour(times[1]);
                break;
            case 3:
                addDay(times[0]);
                addHour(times[1]);
                addMinute(times[2]);
                break;
            case 4:
                addDay(times[0]);
                addHour(times[1]);
                addMinute(times[2]);
                addSecond(times[3]);
                break;
        }
    }


    public Time(long mill){
        setMillSecond(mill);
    }

    public long addDay(long days) {
        long milliseconds = days * 24 * 60 * 60 * 1000;
        SECOND_IN_MILLIS = milliseconds + SECOND_IN_MILLIS;
        return SECOND_IN_MILLIS;
    }

    public long addHour(long hour) {
        long milliseconds = hour * 60 * 60 * 1000;
        SECOND_IN_MILLIS = milliseconds + SECOND_IN_MILLIS;
        return SECOND_IN_MILLIS;
    }

    public long addMinute(long min) {
        long milliseconds = min * 60 * 1000;
        SECOND_IN_MILLIS = milliseconds + SECOND_IN_MILLIS;
        return SECOND_IN_MILLIS;
    }

    public long addSecond(long second) {
        long milliseconds = second * 1000;
        SECOND_IN_MILLIS = milliseconds + SECOND_IN_MILLIS;
        return SECOND_IN_MILLIS;
    }


    public long minDay(long days) {
        long milliseconds = days * 24 * 60 * 60 * 1000;
        SECOND_IN_MILLIS = SECOND_IN_MILLIS - milliseconds;
        return SECOND_IN_MILLIS;
    }

    public long minHour(long hour) {
        long milliseconds = hour * 60 * 60 * 1000;
        SECOND_IN_MILLIS = SECOND_IN_MILLIS - milliseconds;
        return SECOND_IN_MILLIS;
    }

    public long minMinute(long min) {
        long milliseconds = min * 60 * 1000;
        SECOND_IN_MILLIS = SECOND_IN_MILLIS - milliseconds;
        return SECOND_IN_MILLIS;
    }

    public long minSecond(long min) {
        long milliseconds = min * 1000;
        if(SECOND() != 0 || MINUTE() != 0 || HOUR() != 0 || DAY() != 0 )
        SECOND_IN_MILLIS = SECOND_IN_MILLIS - milliseconds;
        return SECOND_IN_MILLIS;
    }

    public void setMillSecond(long SECOND_IN_MILLIS) {
        this.SECOND_IN_MILLIS = SECOND_IN_MILLIS;
    }

    public long DAY() {
        return ((SECOND_IN_MILLIS / (1000 * 60 * 60 * 24)));
    }
    public long HOUR() {
        return ((SECOND_IN_MILLIS / (1000 * 60 * 60)) % 24);
    }

    public long MINUTE() {
        return ((SECOND_IN_MILLIS / (1000 * 60)) % 60);
    }

    public long SECOND() {
        return SECOND_IN_MILLIS / (1000) % 60;
    }
    public long SECOND_IN_MILLIS() {
        return SECOND_IN_MILLIS;
    }
}
